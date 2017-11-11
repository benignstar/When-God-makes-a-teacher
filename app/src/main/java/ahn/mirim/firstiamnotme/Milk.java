package ahn.mirim.firstiamnotme;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Rect;
import android.view.MotionEvent;


public class Milk extends Ingredient{
    private Bitmap temp[]=new Bitmap[2];
    private Rect area;
    private boolean press;
    private MeasuringCup measuringCup;

    public Milk(GameThread gameThread, MeasuringCup measuringCup){
        float width=gameThread.getWidth();
        float height=gameThread.getHeight();

        this.measuringCup=measuringCup;

        x=(int)(width/16*12);
        y=(int)(height/9*2);
        w=(int)(width/32*5);
        h=(int)(height/9*6);

        for(int i=0; i<2; i++) {
            temp[i] = BitmapFactory.decodeResource(gameThread.getContext().getResources(), R.drawable.milk00 +i);
            temp[i] = Bitmap.createScaledBitmap(temp[i], w, h, true);
        }
        image=temp[0];

        area=new Rect(x, y, x+w, y+h);
        press=false;
    }

    public boolean touchEvent(MotionEvent event){
        int x=(int)event.getX();
        int y=(int)event.getY();

        if(area.contains(x, y)) {
            if (event.getAction() == MotionEvent.ACTION_DOWN) {
                image = temp[1];
                press = true;
            } else if (event.getAction() == MotionEvent.ACTION_UP && press) {
                restore();
                measuringCup.fill();
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