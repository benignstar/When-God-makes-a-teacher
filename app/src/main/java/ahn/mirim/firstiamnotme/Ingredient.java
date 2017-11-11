package ahn.mirim.firstiamnotme;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;

/**
 * Created by 안성현 on 2017-11-06.
 */

public class Ingredient {
    public int x, y;
    public int w, h;
    public Bitmap image;
    public int recipe;

    public Ingredient(){}

    public void draw(Canvas canvas){
        canvas.drawBitmap(image, x, y, null);
    }
}
