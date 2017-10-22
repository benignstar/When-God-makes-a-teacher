package ahn.mirim.firstiamnotme;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Rect;
import android.view.MotionEvent;

/**
 * Created by 안성현 on 2017-10-20.
 */

public class Next {
    public int x, y;
    public int w, h;
    public static Bitmap image;
    public Rect area;
    private static boolean canSelect;
    private boolean selected;
    private static Bitmap[] temp=new Bitmap[2];
    private static Bitmap main;

    public Next(){
        x=GameThread.width/32*27;
        y=GameThread.height/18;
        w=GameThread.width/16*2;
        h=GameThread.height/9;

        for(int i=0; i<2; i++) {
            temp[i] = BitmapFactory.decodeResource(GameThread.context.getResources(), R.drawable.next00+i);
            temp[i] = Bitmap.createScaledBitmap(temp[i], w, h, true);
        }
        main = BitmapFactory.decodeResource(GameThread.context.getResources(), R.drawable.main_button);
        main = Bitmap.createScaledBitmap(main, w, h, true);
        image=temp[0];
        area=new Rect(x, y, x+w, y+h);
        canSelect=false;
        selected=false;
    }

    static public void active(){
        canSelect=true;
        image=temp[1];
    }

    public void reset(){
        image=temp[0];
        canSelect=false;
        selected=false;
    }

    public void change(){
        image=main;
    }
    public boolean touchEvent(MotionEvent event){
        if(GameThread.RESULT==GameThread.step){
            if(event.getAction() == MotionEvent.ACTION_DOWN && canSelect){
                selected=true;
            }
            else if(event.getAction() == MotionEvent.ACTION_UP && selected && canSelect){
                GameThread.close();
                GameThread.reset();
            }
        }
        else
            if(event.getAction() == MotionEvent.ACTION_DOWN && canSelect){
                image=temp[1];
                selected=true;
            }
            else if(event.getAction() == MotionEvent.ACTION_UP && selected && canSelect){
                selected=false;
                if(GameThread.step<=GameThread.STEP3)
                  GameThread.nextStep();
                else if (GameThread.step==GameThread.STEP4){
                    Bowl.add(GameThread.spice.select_index);
                    GameThread.nextStep();
                }
                else if(GameThread.step==GameThread.STEP5){
                    Bowl.add(GameThread.fruits.select_index);
                    GameThread.nextStep();
                }
            }
        return true;
    }

}
