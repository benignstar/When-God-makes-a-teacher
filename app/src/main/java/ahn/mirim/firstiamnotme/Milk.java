package ahn.mirim.firstiamnotme;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Rect;
import android.view.MotionEvent;


public class Milk {
    public int x, y;
    public int w, h;

    public Bitmap image;
    private Bitmap temp[]=new Bitmap[2];

    public Rect area;
    public boolean selected;

    public Milk(){
        x=GameThread.width/16*12;
        y=GameThread.height/9*2;
        w=GameThread.width/32*5;
        h=GameThread.height/9*6;

        for(int i=0; i<2; i++) {
            temp[i] = BitmapFactory.decodeResource(GameThread.context.getResources(), R.drawable.milk00 +i);
            temp[i] = Bitmap.createScaledBitmap(temp[i], w, h, true);
        }
        image=temp[0];

        area=new Rect(x, y, x+w, y+h);
        selected=false;
    }

    public boolean touchEvent(MotionEvent event){
        if(event.getAction() == MotionEvent.ACTION_DOWN){
            image=temp[1];
            selected=true;
        }
        else if(event.getAction() == MotionEvent.ACTION_UP && selected){
            restore();
            GameThread.cup.fill();
        }
        return true;
    }

    public void restore(){
        image=temp[0];
        selected=false;
    }
}