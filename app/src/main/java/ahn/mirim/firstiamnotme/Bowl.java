package ahn.mirim.firstiamnotme;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Rect;
import android.util.Log;

/**
 * Created by 안성현 on 2017-10-19.
 */

public class Bowl {
    public int x, y;
    public int w, h;
    static public Bitmap image;
    public Rect area;
    static private Bitmap temp[]=new Bitmap[6];
    static String code;

    public Bowl(){
        x=GameThread.width/32;
        y=GameThread.height/9*4;
        w=GameThread.width/16*6;
        h=GameThread.height/9*4;

        for(int i=0; i<5; i++) {
            temp[i] = BitmapFactory.decodeResource(GameThread.context.getResources(), R.drawable.bowl00+i);
            temp[i] = Bitmap.createScaledBitmap(temp[i], w, h, true);
        }
        temp[5] = BitmapFactory.decodeResource(GameThread.context.getResources(), R.drawable.bowl04);
        temp[5] = Bitmap.createScaledBitmap(temp[5], w, h, true);
        code="";
        image=temp[0];
        area=new Rect(x, y, x+w, y+h);
    }

    static public void fill(){
        if(Cup.quantity!=0) {
            image = temp[GameThread.step + 1];
            code += Cup.quantity;
            Log.v("알림", code);
            GameThread.canSelect = false;
            Next.active();
        }
    }

    static public void add(int index){
        image = temp[GameThread.step + 1];
        code += index;
        Log.v("알림", code);
        GameThread.canSelect = false;
        Next.active();

    }

}
