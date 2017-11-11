package ahn.mirim.firstiamnotme;

import android.graphics.Canvas;
import android.view.MotionEvent;

/**
 * Created by 안성현 on 2017-11-10.
 */

public class DetermineGender {
    private Container container;
    private Next next;
    private Essences essences[]=new Essences[2];

    public DetermineGender(GameThread gameThread, Container container, Next next){
        this.container=container;
        this.next=next;

        float width=gameThread.getWidth();
        float height=gameThread.getHeight();

        for(int i=0; i<2; i++)
            essences[i] = new Essences(gameThread, (int) (width / 16 * 8)+ ((int) (width / 16 * 4)*i), (int) (height / 9 * 5), i, container);

    }

    public void draw(Canvas canvas){
        container.draw(canvas);
        for(int i=0; i<2; i++)
            essences[i].draw(canvas);
        next.draw(canvas);
    }

    public boolean handleTouchEvent(MotionEvent event) {
        for(int i=0; i<2; i++) {
            if (essences[i].touchEvent(event))
                return true;
            else if (event.getAction() == MotionEvent.ACTION_UP)
                essences[i].restore();
        }
        if(next.touchEvent(event));
        else if (event.getAction() == MotionEvent.ACTION_UP) restore();
        return false;
    }

    public void restore(){
        for(int i=0; i<2; i++)
            essences[i].restore();
        next.restore();
    }

}
