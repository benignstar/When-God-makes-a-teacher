package ahn.mirim.firstiamnotme;

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

    public ShowResult(GameThread gameThread, String code){
        recipe=new Recipe(gameThread);
        int num=recipe.check(code);
        teacher=new Teacher(gameThread, num);
        name=new Name(gameThread, num);
        exit=new Exit(gameThread);
    }

    public void draw(Canvas canvas){
        teacher.draw(canvas);
        name.draw(canvas);
        exit.draw(canvas);
    }

    public boolean handleTouchEvent(MotionEvent event){
        if(exit.touchEvent(event));
        return false;
    }
}
