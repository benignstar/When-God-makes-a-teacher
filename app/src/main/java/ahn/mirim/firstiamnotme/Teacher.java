package ahn.mirim.firstiamnotme;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Rect;

/**
 * Created by 안성현 on 2017-10-21.
 */

public class Teacher extends Ingredient{
    public Teacher(GameThread gameThread, int num){
        float width=gameThread.getWidth();
        float height=gameThread.getHeight();

        x=(int)(width/16*5);
        y=(int)(height/18*3);
        w=(int)(width/16*6);
        h=(int)(height/9*7);
        if(!(num<0)) {
            image = BitmapFactory.decodeResource(gameThread.getContext().getResources(), R.drawable.teacher00 + num);
            image = Bitmap.createScaledBitmap(image, w, h, true);
        }
        else {
            image = BitmapFactory.decodeResource(gameThread.getContext().getResources(), R.drawable.fail);
            image = Bitmap.createScaledBitmap(image, w, h, true);
        }
    }
}
