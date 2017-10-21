package ahn.mirim.firstiamnotme;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Rect;

/**
 * Created by 안성현 on 2017-10-21.
 */

public class Teacher {
    public int x, y;
    public int w, h;
    public int nx, ny;
    public int nw, nh;
    static public Bitmap image;
    static public Bitmap name;
    static public Bitmap text;

    public Teacher(){
        x=GameThread.width/16*5;
        y=GameThread.height/9;
        w=GameThread.width/16*6;
        h=GameThread.height/9*7;

        nx=GameThread.width/32;
        ny=GameThread.height/9;
        nw=GameThread.width/16*4;
        nh=GameThread.height/9;

        image = BitmapFactory.decodeResource(GameThread.context.getResources(), R.drawable.teacher00);
        image = Bitmap.createScaledBitmap(image, w, h, true);
        name = BitmapFactory.decodeResource(GameThread.context.getResources(), R.drawable.name00);
        name = Bitmap.createScaledBitmap(name, nw, nh, true);
        text = BitmapFactory.decodeResource(GameThread.context.getResources(), R.drawable.text);
        text = Bitmap.createScaledBitmap(text, nw, nh * 2, true);


    }

    public void setTeacher(int num){
        if(num!=-1) {
            image = BitmapFactory.decodeResource(GameThread.context.getResources(), R.drawable.teacher00 + num);
            image = Bitmap.createScaledBitmap(image, w, h, true);
            name = BitmapFactory.decodeResource(GameThread.context.getResources(), R.drawable.name00 + num);
            name = Bitmap.createScaledBitmap(name, nw, nh, true);
            text = BitmapFactory.decodeResource(GameThread.context.getResources(), R.drawable.text);
            text = Bitmap.createScaledBitmap(text, nw, nh * 2, true);
        }

        else {
            image = BitmapFactory.decodeResource(GameThread.context.getResources(), R.drawable.fail);
            image = Bitmap.createScaledBitmap(image, w, h, true);
            name = BitmapFactory.decodeResource(GameThread.context.getResources(), R.drawable.failtext);
            name = Bitmap.createScaledBitmap(name, nw, nh, true);
            text = BitmapFactory.decodeResource(GameThread.context.getResources(), R.drawable.text01);
            text = Bitmap.createScaledBitmap(text, nw, nh * 2, true);
        }


    }
}
