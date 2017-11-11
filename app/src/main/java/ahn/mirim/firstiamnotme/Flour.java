package ahn.mirim.firstiamnotme;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Rect;
import android.view.MotionEvent;

/**
 * Created by 안성현 on 2017-10-19.
 */

public class Flour extends Ingredient{
    private MeasuringCup measuringCup;
    private Bitmap temp[]=new Bitmap[2];
    private Rect area;
    private boolean press;


    public Flour(GameThread gameThread, MeasuringCup measuringCup){
        float width=gameThread.getWidth();
        float height=gameThread.getHeight();

        this.measuringCup=measuringCup;

        x=(int)(width/16*11);
        y=(int)(height/9*3);
        w=(int)(width/16*4);
        h=(int)(height/9*5);

        for(int i=0; i<2; i++) {
            temp[i] = BitmapFactory.decodeResource(gameThread.getContext().getResources(), R.drawable.flour00 +i);
            temp[i] = Bitmap.createScaledBitmap(temp[i], w, h, true);
        }
        image=temp[0];

        area=new Rect(x, y, x+w, y+h);
        press=false;
    }

    public boolean touchEvent(MotionEvent event){
        int x=(int)event.getX();
        int y=(int)event.getY();

        if(area.contains(x, y)){
            if(event.getAction()==MotionEvent.ACTION_DOWN){
                image=temp[1];
                press=true;
            } else if(event.getAction()==MotionEvent.ACTION_UP && press){
                measuringCup.fill();
                restore();

            }
            return true;
        }
        return false;
    }

    public void restore(){
        image=temp[0];
        press=false;
    }
}
