package ahn.mirim.firstiamnotme;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Point;
import android.view.Display;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.WindowManager;

public class GameThread extends Thread{
    private SurfaceHolder holder;
    static public Context context;
    static public int width, height;
    private static boolean paused=false;

    private Bitmap background[]=new Bitmap[3];
    public static Bowl bowl;
    public static Cup cup;
    public static Flour flour;
    public static Next next;
    public static Milk milk;
    public static Essences essences;
    public static Spice spice;
    public static Fruits fruits;
    public static Recipe recipe;
    public static Teacher teacher;
    static public int step;
    static int result;

    static public boolean canSelect;
    public final static int STEP1=0;
    public final static int STEP2=1;
    public final static int STEP3=2;
    public final static int STEP4=3;
    public final static int STEP5=4;
    public final static int RESULT=5;

    private final static int FRAME_PERIOD = 1000 / 60;

    public GameThread(SurfaceHolder holder, Context context, GameState gameState){
        this.holder=holder;
        this.context=context;

        Display display=((WindowManager)context.getSystemService(Context.WINDOW_SERVICE)).getDefaultDisplay();
        Point point=new Point();
        display.getSize(point);

        width=point.x;
        height=point.y;

        for(int i=0; i<3; i++) {
            background[i] = BitmapFactory.decodeResource(context.getResources(), R.drawable.background00+i);
            background[i] = Bitmap.createScaledBitmap(background[i], width, height, true);
        }




        if(gameState != null){
            bowl=gameState.bowl;
            cup=gameState.cup;
            flour=gameState.flour;
            next=gameState.next;
            milk=gameState.milk;
            essences=gameState.essences;
            spice=gameState.spice;
            fruits=gameState.fruits;
            recipe=gameState.recipe;
            teacher = gameState.teacher;
            step=gameState.step;
            canSelect=gameState.canSelect;
        }
        else {
            bowl=new Bowl();
            cup=new Cup();
            flour=new Flour();
            next=new Next();
            milk=new Milk();
            essences=new Essences();
            spice=new Spice();
            fruits=new Fruits();
            recipe=new Recipe();
            teacher = new Teacher();
            step=0;
            canSelect=true;
        }
        resumeGame();
    }

    @Override
    public void run(){
        Canvas canvas=null;
        long sleepTime = 0;
        long begin;
        long end;
        long diff;

        while(!paused){
            canvas=holder.lockCanvas();
            begin = System.currentTimeMillis();

            if(canvas!=null) {
                draw(canvas);
            }

            end = System.currentTimeMillis();
            diff = end - begin;
            sleepTime = FRAME_PERIOD - diff;
            if(sleepTime > 0) {
                try {
                    Thread.sleep(sleepTime);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } // try - catch
            } // if
        } // while

    }

    public void draw(Canvas canvas){
        canvas.drawBitmap(background[0], 0, 0, null);
        switch (step) {
            case STEP1:
                canvas.drawBitmap(bowl.image, bowl.x, bowl.y, null);
                canvas.drawBitmap(cup.image, cup.x, cup.y, null);
                canvas.drawBitmap(flour.image, flour.x, flour.y, null);
                canvas.drawBitmap(next.image, next.x, next.y, null);
                break;
            case STEP2 :
                canvas.drawBitmap(bowl.image, bowl.x, bowl.y, null);
                canvas.drawBitmap(cup.image, cup.x, cup.y, null);
                canvas.drawBitmap(milk.image, milk.x, milk.y, null);
                canvas.drawBitmap(next.image, next.x, next.y, null);
                break;
            case STEP3 :
                canvas.drawBitmap(bowl.image, bowl.x, bowl.y, null);
                for(int i=0; i<2; i++)
                    canvas.drawBitmap(essences.image[i], essences.x[i], essences.y, null);
                canvas.drawBitmap(next.image, next.x, next.y, null);
                break;
            case STEP4:
                canvas.drawBitmap(bowl.image, bowl.x, bowl.y, null);
                for(int i=0; i<8; i++)
                    canvas.drawBitmap(spice.image[i], spice.x[i%4], spice.y[i/4], null);
                if(spice.select)
                    canvas.drawBitmap(spice.check, spice.x[spice.select_index%4], spice.y[spice.select_index/4], null);
                canvas.drawBitmap(next.image, next.x, next.y, null);
                break;
            case STEP5:
                canvas.drawBitmap(bowl.image, bowl.x, bowl.y, null);
                for(int i=0; i<10; i++)
                    canvas.drawBitmap(fruits.image[i], fruits.x[i%5], fruits.y[i/5], null);
                if(fruits.select)
                    canvas.drawBitmap(fruits.check, fruits.x[fruits.select_index%5], fruits.y[fruits.select_index/5], null);
                canvas.drawBitmap(next.image, next.x, next.y, null);
                break;
            case RESULT :
                if(result!=-1)
                    canvas.drawBitmap(background[1], 0, 0, null);
                else canvas.drawBitmap(background[2], 0, 0, null);
                canvas.drawBitmap(teacher.image, teacher.x, teacher.y, null);
                canvas.drawBitmap(teacher.name, teacher.nx, teacher.ny, null);
                canvas.drawBitmap(teacher.text, teacher.nx, teacher.ny+teacher.nh, null);
                canvas.drawBitmap(next.image, next.x, next.y, null);

                break;

        }
        holder.unlockCanvasAndPost(canvas);
    }

    public static void nextStep(){
        step++;
        cup.empty();
        next.reset();
        canSelect=true;
        if(step==RESULT) {
            result = recipe.check(Bowl.code);
            teacher.setTeacher(result);

            next.active();
            next.change();
        }

    }
    public static void pauseGame() {
        paused=true;
    }

    public void resumeGame() {
        paused=false;
    }

    public static void reset(){
        bowl=new Bowl();
        cup=new Cup();
        flour=new Flour();
        next=new Next();
        milk=new Milk();
        essences=new Essences();
        spice=new Spice();
        fruits=new Fruits();
        recipe=new Recipe();
        teacher = new Teacher();
        step=0;
        canSelect=true;
    }
    public static void close(){
        pauseGame();
        context.startActivity(new Intent(context, MainActivity.class));
        ((Activity)context).finish();
    }
    public boolean handleTouchEvent(MotionEvent event){
        int x, y;

        x=(int) event.getX();
        y=(int) event.getY();

        if(canSelect) {
            switch (step) {
                case STEP1 :
                    if (flour.area.contains(x, y))
                        return flour.touchEvent(event);

                    else if (cup.area.contains(x, y))
                        return cup.touchEvent(event);

                    else if (event.getAction() == MotionEvent.ACTION_UP) {
                        flour.restore();
                        cup.restore();
                    }
                    break;
                case STEP2 :
                    if (milk.area.contains(x, y))
                        return milk.touchEvent(event);

                    else if (cup.area.contains(x, y))
                      return cup.touchEvent(event);

                    else if (event.getAction() == MotionEvent.ACTION_UP) {
                        milk.restore();
                        cup.restore();
                    }
                    break;
                case STEP3 :

                    if (essences.area[0].contains(x, y))
                        return essences.touchEvent(event, 0);

                    else if (essences.area[1].contains(x, y))
                        return essences.touchEvent(event, 1);

                    else if (event.getAction() == MotionEvent.ACTION_UP) {
                        essences.restore(0);
                        essences.restore(1);
                    }
                    break;
                case STEP4:
                    if(spice.area[0].contains(x, y)){
                        spice.touchEvent(event, 0);
                    }

                    else if(spice.area[1].contains(x, y)){
                        spice.touchEvent(event, 1);
                    }
                    else if(spice.area[2].contains(x, y)){
                        spice.touchEvent(event, 2);
                    }
                    else if(spice.area[3].contains(x, y)){
                        spice.touchEvent(event, 3);
                    }
                    else if(spice.area[4].contains(x, y)){
                        spice.touchEvent(event, 4);
                    }
                    else if(spice.area[5].contains(x, y)){
                        spice.touchEvent(event, 5);
                    }
                    else if(spice.area[6].contains(x, y)){
                        spice.touchEvent(event, 6);
                    }
                    else if(spice.area[7].contains(x, y)){
                        spice.touchEvent(event, 7);
                    }

                    if(spice.select)
                        if (next.area.contains(x, y))
                            return next.touchEvent(event);
                    break;
                case STEP5:
                    if(fruits.area[0].contains(x, y)){
                        fruits.touchEvent(event, 0);
                    }
                    else if(fruits.area[1].contains(x, y)){
                        fruits.touchEvent(event, 1);
                    }
                    else if(fruits.area[2].contains(x, y)){
                        fruits.touchEvent(event, 2);
                    }
                    else if(fruits.area[3].contains(x, y)){
                        fruits.touchEvent(event, 3);
                    }
                    else if(fruits.area[4].contains(x, y)){
                        fruits.touchEvent(event, 4);
                    }
                    else if(fruits.area[5].contains(x, y)){
                        fruits.touchEvent(event, 5);
                    }
                    else if(fruits.area[6].contains(x, y)){
                        fruits.touchEvent(event, 6);
                    }
                    else if(fruits.area[7].contains(x, y)){
                        fruits.touchEvent(event, 7);
                    }
                    else if(fruits.area[8].contains(x, y)){
                        fruits.touchEvent(event, 8);
                    }
                    else if(fruits.area[9].contains(x, y)){
                        fruits.touchEvent(event, 9);
                    }

                    if(fruits.select)
                        if (next.area.contains(x, y))
                            return next.touchEvent(event);
                    break;
                case RESULT:
                    if (next.area.contains(x, y))
                        return next.touchEvent(event);
            }
        }

        else if (next.area.contains(x, y))
            return next.touchEvent(event);


        return true;
    }

    public GameState getGameState() {
        GameState gameState=new GameState();
        gameState.bowl=bowl;
        gameState.cup=cup;
        gameState.flour=flour;
        gameState.next=next;
        gameState.milk=milk;
        gameState.essences=essences;
        gameState.spice=spice;
        gameState.fruits=fruits;
        gameState.recipe=recipe;
        gameState.teacher=teacher;
        gameState.step=step;
        gameState.canSelect=canSelect;
        return gameState;
    }
}
