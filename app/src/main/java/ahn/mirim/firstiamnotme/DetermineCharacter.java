package ahn.mirim.firstiamnotme;

import android.graphics.Canvas;
import android.view.MotionEvent;

/**
 * Created by 안성현 on 2017-11-10.
 */

public class DetermineCharacter {
    private Container container;
    private Spice spices[];
    private Next next;

    public DetermineCharacter(GameThread gameThread, Container container, Next next){
        this.container=container;
        this.next=next;

        spices=new Spice[8];

        float width=gameThread.getWidth();
        float height=gameThread.getHeight();


        for (int x = 0; x < 4; x++) {
            for(int y=0; y<2; y++) {
                spices[y*4+x]=new Spice(gameThread, (int)(width/32*15)+((int)(width/16*2)*x), (int)(height/9*3)+((int)(height/9*3)*y), y*4+x, container);
            }
        }

    }

    public void draw(Canvas canvas){
        for(int i=0; i<8; i++)
            spices[i].draw(canvas);
        container.draw(canvas);
        next.draw(canvas);
    }

    public void restore(){

        next.restore();
    }

    public boolean handleTouchEvent(MotionEvent event) {
        for(int i=0; i<8; i++) {
            if (spices[i].touchEvent(event));
            else if (event.getAction() == MotionEvent.ACTION_UP)
                spices[i].restore();
        }
        if(next.touchEvent(event));
        else if (event.getAction() == MotionEvent.ACTION_UP) restore();
        return false;
    }
}
