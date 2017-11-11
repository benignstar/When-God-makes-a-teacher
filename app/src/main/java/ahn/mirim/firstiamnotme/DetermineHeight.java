package ahn.mirim.firstiamnotme;

import android.graphics.Canvas;
import android.view.MotionEvent;

/**
 * Created by 안성현 on 2017-11-10.
 */

public class DetermineHeight {
    private Container container;
    private MeasuringCup measuringCup;
    private Milk milk;
    private Next next;

    public DetermineHeight(GameThread gameThread, Container container, Next next){
        this.container=container;
        measuringCup=new MeasuringCup(gameThread, container, MeasuringCup.MILK);
        milk=new Milk(gameThread, measuringCup);
        this.next=next;
    }

    public void draw(Canvas canvas){
        container.draw(canvas);
        measuringCup.draw(canvas);
        milk.draw(canvas);
        next.draw(canvas);
    }

    public void restore(){
        milk.restore();
        measuringCup.restore();
        next.restore();
    }

    public boolean handleTouchEvent(MotionEvent event) {
        if(milk.touchEvent(event));
        else if(measuringCup.touchEvent(event));
        else if(next.touchEvent(event));
        else if (event.getAction() == MotionEvent.ACTION_UP)
            restore();
        return false;
    }
}
