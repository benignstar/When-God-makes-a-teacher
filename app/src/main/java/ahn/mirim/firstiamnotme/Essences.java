package ahn.mirim.firstiamnotme;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Rect;
import android.view.MotionEvent;

/**
 * Created by 안성현 on 2017-10-21.
 */

public class Essences {
    public int x[]=new int[2], y;
    public int w, h;
    public Bitmap image[]=new Bitmap[2];
    private Bitmap temp[]=new Bitmap[4];
    public Rect area[]=new Rect[2];

    public boolean selected[]=new boolean[2];
    private int saveIndex;

    public Essences(){
        w=GameThread.width/32*5;
        h=GameThread.width/16*3;
        x[0]=GameThread.width/16*8;
        x[1]=GameThread.width/16*8+(GameThread.width/16*4);
        y=GameThread.height/9*5;

        for(int i=0; i<4; i++){
            temp[i]= BitmapFactory.decodeResource(GameThread.context.getResources(), R.drawable.essence00+i);
            temp[i]= Bitmap.createScaledBitmap(temp[i], w, h, true);
        }

        image[0]=temp[0];
        image[1]=temp[1];

        area[0]=new Rect(x[0], y, x[0]+w, y+h);
        area[1]=new Rect(x[1], y, x[1]+w, y+h);

        selected[0]=false;
        selected[1]=false;
    }

    public boolean touchEvent(MotionEvent event, int index){
        if(event.getAction() == MotionEvent.ACTION_DOWN){
            image[index]=temp[index+2];
            selected[index]=true;
            saveIndex=index;
        }
        else if(event.getAction() == MotionEvent.ACTION_UP && selected[index] && saveIndex==index){
            restore(index);
            Bowl.add(index);
        }
        else if(event.getAction() == MotionEvent.ACTION_UP && saveIndex!=index){
            restore(saveIndex);
        }

        return true;
    }

    public void restore(int index){
        image[0]=temp[0];
        image[1]=temp[1];
        selected[index]=false;
    }


}
