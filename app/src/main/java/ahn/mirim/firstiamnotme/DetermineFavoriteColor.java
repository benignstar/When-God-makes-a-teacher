package ahn.mirim.firstiamnotme;

import android.graphics.Canvas;
import android.view.MotionEvent;

/**
 * Created by 안성현 on 2017-11-10.
 */

public class DetermineFavoriteColor {
    private Container container;
    private Fruit fruits[];
    private Next next;

    public DetermineFavoriteColor(GameThread gameThread, Container container, Next next){
        this.container=container;
        this.next=next;

        fruits=new Fruit[10];

        float width=gameThread.getWidth();
        float height=gameThread.getHeight();

        for (int x = 0; x < 5; x++) {
            for(int y=0; y<2; y++) {
                fruits[y*5+x]=new Fruit(gameThread, (int)(width/32*15)+((int)(width/32*3)*x), (int)(height/9*5)+((int)(height/18*3)*y), y*5+x, container);
            }
        }
    }

    public void draw(Canvas canvas){
        for(int i=0; i<10; i++) fruits[i].draw(canvas);
        next.draw(canvas);
        container.draw(canvas);
    }

    public boolean handleTouchEvent(MotionEvent event) {
        for(int i=0; i<10; i++) {
            if (fruits[i].touchEvent(event));
            else if (event.getAction() == MotionEvent.ACTION_UP)
                fruits[i].restore();
        }
        if(next.touchEvent(event));
        else if (event.getAction() == MotionEvent.ACTION_UP) restore();
        return false;
    }

    public void restore(){
        next.restore();
    }
}
