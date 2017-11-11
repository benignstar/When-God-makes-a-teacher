package ahn.mirim.firstiamnotme;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Rect;
import android.view.MotionEvent;

/**
 * Created by 안성현 on 2017-10-19.
 */

public class MeasuringCup extends Ingredient {
    private Bitmap temp[];
    private Rect area;
    private int amount;
    private int max;
    private boolean press;
    private Container container;
    private GameThread gameThread;

    public static final int FLOUR=0;
    public static final int MILK=1;


    public MeasuringCup(GameThread gameThread, Container container, int type){
        float width=gameThread.getWidth();
        float height=gameThread.getHeight();

        this.container=container;
        this.gameThread=gameThread;
        x=(int)(width/16*8);
        y=(int)(height/18*9);
        w=(int)(width/32*5);
        h=(int)(height/18*7);

        amount=0;

        if(type==FLOUR) {
            temp=new Bitmap[5];
            for (int i = 1; i < 5; i++) {
                temp[i] = BitmapFactory.decodeResource(gameThread.getContext().getResources(), R.drawable.cup00 + i);
                temp[i] = Bitmap.createScaledBitmap(temp[i], w, h, false);
            }
            max=4;
        }
        else if(type==MILK){
            temp=new Bitmap[4];
            for (int i = 1; i < 4; i++) {
                temp[i] = BitmapFactory.decodeResource(gameThread.getContext().getResources(), R.drawable.cup00 + i+4);
                temp[i] = Bitmap.createScaledBitmap(temp[i], w, h, false);
            }
            max=3;
        }
        temp[0] = BitmapFactory.decodeResource(gameThread.getContext().getResources(), R.drawable.cup00);
        temp[0] = Bitmap.createScaledBitmap(temp[0], w, h, false);

        image=temp[0];
        area=new Rect(x, y, x+w, y+h);
        press=false;
    }

    public void fill(){
        amount++;
        if(amount>max) amount=1;
        image=temp[amount];
    }


    public void restore(){
       press=false;
    }

    public boolean touchEvent(MotionEvent event){
        int x=(int)event.getX();
        int y=(int)event.getY();

        if(area.contains(x, y)) {
            if (event.getAction() == MotionEvent.ACTION_DOWN) {
                press=true;
            } else if (event.getAction() == MotionEvent.ACTION_UP && press) {
                press = false;
                if(amount!=0) {
                    recipe=amount;
                    container.add(this);
                    gameThread.revitalize();
                }
            }
            return true;
        }
        return false;
    }
}
