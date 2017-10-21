package ahn.mirim.firstiamnotme;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Rect;
import android.view.MotionEvent;

/**
 * Created by 안성현 on 2017-10-19.
 */

public class Cup {
    public int x, y;
    public int w, h;
    public Bitmap image;
    private Bitmap temp[]=new Bitmap[8];
    public Rect area;
    public static int quantity;
    public boolean selected;

    public Cup(){
        x=GameThread.width/16*8;
        y=GameThread.height/18*9;
        w=GameThread.width/32*5;
        h=GameThread.height/18*7;
        quantity=0;

        for(int i=0; i<8; i++){
            temp[i]= BitmapFactory.decodeResource(GameThread.context.getResources(), R.drawable.cup00+i);
            temp[i]=Bitmap.createScaledBitmap(temp[i],w,h, true);
        }

        image=temp[0];

        area=new Rect(x, y, x+w, y+h);
        selected=false;
    }

    public void fill(){
        quantity++;
        if(GameThread.step==0 && quantity>4)
            quantity=1;
        else if(GameThread.step==1 && quantity>3){
            quantity=1;
        }
        image=temp[GameThread.step*4+quantity];
    }

    public void empty(){
        quantity=0;
        image=temp[0];
    }

    public void restore(){
        selected=false;
    }

    public boolean touchEvent(MotionEvent event){
        if(event.getAction() == MotionEvent.ACTION_DOWN){
            selected=true;
        }
        else if(event.getAction() == MotionEvent.ACTION_UP){
            selected=false;
            Bowl.fill();

        }
        return true;
    }
}
