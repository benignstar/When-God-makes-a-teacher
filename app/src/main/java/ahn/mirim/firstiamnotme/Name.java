package ahn.mirim.firstiamnotme;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

/**
 * Created by 안성현 on 2017-11-06.
 */

public class Name extends Ingredient{

    public Name(GameThread gameThread, int num){
        float width=gameThread.getWidth();
        float height=gameThread.getHeight();

        x=(int)(width/32*5+width/64);
        y=(int)(height/36);
        w=(int)(width/16*3);
        h=(int)(height/9);
        if(!(num<0)) {
            image = BitmapFactory.decodeResource(gameThread.getContext().getResources(), R.drawable.name00 + num);
            image = Bitmap.createScaledBitmap(image, w, h, true);
        }
        else {
            image = BitmapFactory.decodeResource(gameThread.getContext().getResources(), R.drawable.failtext);
            image = Bitmap.createScaledBitmap(image, w, h, true);
        }
    }
}
