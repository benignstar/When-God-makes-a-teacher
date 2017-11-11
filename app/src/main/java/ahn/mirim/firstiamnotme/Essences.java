package ahn.mirim.firstiamnotme;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.view.MotionEvent;

/**
 * Created by 안성현 on 2017-10-21.
 */

public class Essences extends Ingredient{
    private Bitmap temp[]=new Bitmap[2];
    private Rect area;
    private boolean press;
    private Container container;
    private GameThread gameThread;

    public static final int MAN=0;
    public static final int WOMAN=1;

    public Essences(GameThread gameThread, int x, int y, int type, Container container){
        this.x=x;
        this.y=y;
        this.w=(int)(gameThread.getWidth()/32*5);
        this.h=(int)(gameThread.getWidth()/16*3);

        this.gameThread=gameThread;
        this.container=container;

        for(int i=0; i<2; i++){
            temp[i]= BitmapFactory.decodeResource(gameThread.getContext().getResources(), R.drawable.essence00+type*2+i);
            temp[i]= Bitmap.createScaledBitmap(temp[i], w, h, true);
        }

        recipe=type;
        image=temp[0];

        area=new Rect(x, y, x+w, y+h);
        press=false;
    }

    public void draw(Canvas canvas){
        canvas.drawBitmap(image, x, y, null);
    }

    public boolean touchEvent(MotionEvent event){
        int x=(int)event.getX();
        int y=(int)event.getY();

        if(area.contains(x, y)) {
            if (event.getAction() == MotionEvent.ACTION_DOWN) {
                press=true;
                image = temp[1];
            } else if (event.getAction() == MotionEvent.ACTION_UP && press) {
                press=false;
                image=temp[0];
                container.add(this);
                gameThread.revitalize();
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
