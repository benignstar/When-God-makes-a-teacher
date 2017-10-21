package ahn.mirim.firstiamnotme;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Rect;
import android.view.MotionEvent;

/**
 * Created by 안성현 on 2017-10-21.
 */

public class Fruits {
    public int x[]=new int[5], y[]=new int[2];
    public int w, h;
    public Bitmap image[]=new Bitmap[10];
    public Bitmap check;
    public Rect area[]=new Rect[10];
    public boolean select;
    public int select_index;
    public boolean selected[]=new boolean[10];
    private int saveIndex;

    public Fruits(){

        for(int i=0; i<5; i++)
            x[i]=GameThread.width/32*15+i*(GameThread.width/32*3);
        for(int i=0; i<2; i++)
            y[i]=GameThread.height/9*5+i*(GameThread.height/18*3);

        w=GameThread.width/32*3;
        h=GameThread.width/32*3;

        for(int i=0; i<10; i++){
            image[i]= BitmapFactory.decodeResource(GameThread.context.getResources(), R.drawable.color00+i);
            image[i]= Bitmap.createScaledBitmap(image[i], w, h, true);
        }

        check=BitmapFactory.decodeResource(GameThread.context.getResources(), R.drawable.color_selected);
        check=Bitmap.createScaledBitmap(check, w, h, true);

        for(int i=0; i<10; i++) {
            area[i] = new Rect(x[i % 5], y[i / 5], x[i % 5] + w, y[i / 5] + h);
            selected[i]=false;
        }

        select_index=-1;
        select=false;
    }

    public boolean touchEvent(MotionEvent event, int index){
        if(event.getAction() == MotionEvent.ACTION_DOWN){
            selected[index]=true;
            saveIndex=index;
        }
        else if(event.getAction() == MotionEvent.ACTION_UP && selected[index] && saveIndex==index){
            select_index=index;
            select=true;
            Next.active();
        }
        else if(event.getAction() == MotionEvent.ACTION_UP && saveIndex!=index){
            selected[saveIndex]=false;
        }

        return true;
    }
}
