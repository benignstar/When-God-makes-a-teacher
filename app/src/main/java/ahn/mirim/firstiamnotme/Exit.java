package ahn.mirim.firstiamnotme;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Rect;
import android.view.MotionEvent;

/**
 * Created by 안성현 on 2017-11-11.
 */

public class Exit extends Ingredient{
    private GameThread gameThread;
    private Rect area;
    private boolean press;

    public Exit(GameThread gameThread){
        this.gameThread=gameThread;

        float width=gameThread.getWidth();
        float height=gameThread.getHeight();

        x=(int)(width/32*27);
        y=(int)(height/18);
        w=(int)(width/16*2);
        h=(int)(height/9);


        image=BitmapFactory.decodeResource(gameThread.getContext().getResources(), R.drawable.main_button);
        image = Bitmap.createScaledBitmap(image, w, h, true);
        area=new Rect(x, y, x+w, y+h);

        press=false;
    }

    public boolean touchEvent(MotionEvent event){
        int x=(int)event.getX();
        int y=(int)event.getY();

        if(area.contains(x,y)) {
            if(event.getAction()==MotionEvent.ACTION_DOWN){
                press=true;
            } else if(event.getAction()==MotionEvent.ACTION_UP && press){
                press=false;
                gameThread.exit();
            }
            return true;
        }
        return false;
    }
}
