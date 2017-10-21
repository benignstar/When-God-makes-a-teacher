package ahn.mirim.firstiamnotme;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Rect;
import android.view.MotionEvent;

public class Spice {
    public int x[]=new int[4], y[]=new int[2];
    public int w, h;
    public Bitmap image[]=new Bitmap[8];
    public Bitmap check;
    public Rect area[]=new Rect[8];
    public boolean select;
    public int select_index;
    public boolean selected[]=new boolean[8];
    private int saveIndex;

    public Spice(){

        for(int i=0; i<4; i++)
            x[i]=GameThread.width/32*15+i*(GameThread.width/16*2);
        for(int i=0; i<2; i++)
            y[i]=GameThread.height/9*3+i*(GameThread.height/9*3);

        w=GameThread.width/32*3;
        h=GameThread.width/32*6;

        for(int i=0; i<8; i++){
            image[i]= BitmapFactory.decodeResource(GameThread.context.getResources(), R.drawable.spice00+i);
            image[i]= Bitmap.createScaledBitmap(image[i], w, h, true);
        }

        check=BitmapFactory.decodeResource(GameThread.context.getResources(), R.drawable.spice_selected);
        check=Bitmap.createScaledBitmap(check, w, h, true);

        for(int i=0; i<8; i++) {
            area[i] = new Rect(x[i % 4], y[i / 4], x[i % 4] + w, y[i / 4] + h);
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
