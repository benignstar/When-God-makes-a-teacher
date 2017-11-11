package ahn.mirim.firstiamnotme;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Rect;
import android.view.MotionEvent;

/**
 * Created by 안성현 on 2017-10-20.
 */

public class Next extends Ingredient{
    public Rect area;
    private boolean disabled;
    private boolean press;
    private Bitmap[] temp=new Bitmap[2];

    private GameThread gameThread;

    public Next(GameThread gameThread){
        this.gameThread=gameThread;

        float width=gameThread.getWidth();
        float height=gameThread.getHeight();

        x=(int)(width/32*27);
        y=(int)(height/18);
        w=(int)(width/16*2);
        h=(int)(height/9);

        for(int i=0; i<2; i++) {
            temp[i] = BitmapFactory.decodeResource(gameThread.getContext().getResources(), R.drawable.next00+i);
            temp[i] = Bitmap.createScaledBitmap(temp[i], w, h, true);
        }
        image=temp[0];
        area=new Rect(x, y, x+w, y+h);

        disabled=true;
        press=false;
    }

    public void revitalize(){
        disabled=false;
        image=temp[1];
    }

    public void inactivate(){
        disabled=true;
        image=temp[0];
    }
    public void restore(){
        if(disabled)
            image = temp[0];
        press=false;
    }

    public boolean touchEvent(MotionEvent event){
        int x=(int)event.getX();
        int y=(int)event.getY();

        if(area.contains(x,y)) {
            if(!disabled){
                if(event.getAction()==MotionEvent.ACTION_DOWN){
                    press=true;
                } else if(event.getAction()==MotionEvent.ACTION_UP && press){
                    press=false;
                    if(gameThread.getStage()==GameThread.STAGE_FIVE_COLOR)
                        gameThread.setResult();
                    gameThread.stageUp();
                }
                return true;
            }
        }
        return false;
    }

    public void setGameThread(GameThread gameThread){
        this.gameThread=gameThread;
    }

}
