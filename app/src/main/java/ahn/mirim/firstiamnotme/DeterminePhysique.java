package ahn.mirim.firstiamnotme;

import android.graphics.Canvas;
import android.view.MotionEvent;

/**
 * Created by 안성현 on 2017-11-07.
 */

public class DeterminePhysique {
    private Container container;
    private MeasuringCup measuringCup;
    private Flour flour;
    private Next next;

    public DeterminePhysique(GameThread gameThread, Container container, Next next){
        this.container=container;
        this.next=next;
        measuringCup=new MeasuringCup(gameThread, container, MeasuringCup.FLOUR);
        flour=new Flour(gameThread, measuringCup);
    }

    public void draw(Canvas canvas){
        container.draw(canvas);
        measuringCup.draw(canvas);
        flour.draw(canvas);
        next.draw(canvas);
    }

    public void restore(){
        flour.restore();
        measuringCup.restore();
        next.restore();
    }

    public boolean handleTouchEvent(MotionEvent event) {
        if(flour.touchEvent(event));
        else if(measuringCup.touchEvent(event));
        else if(next.touchEvent(event));
        else if (event.getAction() == MotionEvent.ACTION_UP) restore();
        return false;
    }
}
