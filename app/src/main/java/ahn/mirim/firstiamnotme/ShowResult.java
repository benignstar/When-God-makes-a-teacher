package ahn.mirim.firstiamnotme;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.view.MotionEvent;

/**
 * Created by 안성현 on 2017-11-11.
 */

public class ShowResult {
    private Teacher teacher;
    private Name name;
    private Recipe recipe;
    private Exit exit;
    private boolean make;
    private Bitmap background;

    public ShowResult(GameThread gameThread, String code){
        recipe=new Recipe(gameThread);
        int num=recipe.check(code);
        if(num==-1) {
            make=false;
            background= BitmapFactory.decodeResource(gameThread.getContext().getResources(), R.drawable.background06);
        }
        else {
            background= BitmapFactory.decodeResource(gameThread.getContext().getResources(), R.drawable.background05);
            make=true;
        }
        background=Bitmap.createScaledBitmap(background, (int)gameThread.getWidth(), (int)gameThread.getHeight(), false);

        teacher=new Teacher(gameThread, num);
        name=new Name(gameThread, num);
        exit=new Exit(gameThread);
    }

    public void draw(Canvas canvas){
        canvas.drawBitmap(background, 0, 0, null);
        teacher.draw(canvas);
        if(make) name.draw(canvas);
        exit.draw(canvas);
    }

    public boolean handleTouchEvent(MotionEvent event){
        if(exit.touchEvent(event));
        return false;
    }
}
