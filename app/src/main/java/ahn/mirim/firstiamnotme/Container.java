package ahn.mirim.firstiamnotme;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Rect;
import android.util.Log;

/**
 * Created by 안성현 on 2017-10-19.
 */

public class Container extends Ingredient{
    private int stage;
    private String code;
    private Bitmap temp[]=new Bitmap[5];
    private boolean refill;
    private GameThread gameThread;

    public Container(GameThread gameThread){
        this.gameThread=gameThread;
        stage=0;

        float width=gameThread.getWidth();
        float height=gameThread.getHeight();

        x=(int)(width/32);
        y=(int)(height/9*4);
        w=(int)(width/16*6);
        h=(int)(height/9*4);

        refill=false;

        for(int i=0; i<5; i++){
            temp[i]=BitmapFactory.decodeResource(gameThread.getContext().getResources(), R.drawable.bowl00+i);
            temp[i]=Bitmap.createScaledBitmap(temp[i], w, h, false);
        }
        image = temp[0];

        code="";
    }

    public void add(Ingredient ingredient){
        if(!refill) {
            if(gameThread.getStage()!=GameThread.STAGE_THREE_GENDER)
              image = temp[++stage];
            refill=true;
        } else {
            if(gameThread.getStage()==0) code="";
            else code=code.substring(0, gameThread.getStage());
        }
        code+=ingredient.recipe;
        Log.v("dff",code);
    }

    public void setGameThread(GameThread gameThread){
        this.gameThread=gameThread;
    }

    public void restore(){
        refill=false;
    }

    public String getCode(){
        return code;
    }
}
