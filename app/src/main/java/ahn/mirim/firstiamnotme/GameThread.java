package ahn.mirim.firstiamnotme;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Point;
import android.media.MediaPlayer;
import android.util.Log;
import android.view.Display;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.WindowManager;

public class GameThread extends Thread{
    private SurfaceHolder holder;
    private Context context;
    private float width, height;
    private boolean paused;

    private int stage;
    private Bitmap background[]=new Bitmap[7];
    private Container container;
    private Next next;

    private DeterminePhysique determinePhysique;
    private DetermineHeight determineHeight;
    private DetermineGender determineGender;
    private DetermineCharacter determineCharacter;
    private DetermineFavoriteColor determineFavoriteColor;
    private ShowResult showResult;

    private MediaPlayer player;

    public final static int STAGE_ONE_PHYSIQUE=0;
    public final static int STAGE_TWO_HEIGHT=1;
    public final static int STAGE_THREE_GENDER=2;
    public final static int STAGE_FOUR_CHARACTER=3;
    public final static int STAGE_FIVE_COLOR=4;
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

        for(int i=0; i<7; i++) {
            background[i] = BitmapFactory.decodeResource(context.getResources(), R.drawable.background00+i);
            background[i] = Bitmap.createScaledBitmap(background[i], (int)width, (int)height, true);
        }

        if(gameState != null){
            stage=gameState.stage;
            container=gameState.container;
            next=gameState.next;
            determinePhysique=gameState.determinePhysique;
            determineHeight=gameState.determineHeight;
            determineGender=gameState.determineGender;
            determineCharacter=gameState.determineCharacter;
            determineFavoriteColor=gameState.determineFavoriteColor;
            player=gameState.player;
            next.setGameThread(this);
            container.setGameThread(this);
        }
        else {
            stage=0;
            container=new Container(this);
            next=new Next(this);
            determinePhysique=new DeterminePhysique(this, container, next);
            determineHeight=new DetermineHeight(this, container, next);
            determineGender=new DetermineGender(this, container, next);
            determineCharacter=new DetermineCharacter(this, container, next);
            determineFavoriteColor=new DetermineFavoriteColor(this,container,next);
            player=MediaPlayer.create(context, R.raw.vacation_uke);

        }

        paused=false;

        player.setVolume(0.7f, 0.7f);
        player.setLooping(true);

        player.start();
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
                } catch (InterruptedException e) { e.printStackTrace(); }
            }
        }

    }

    public void draw(Canvas canvas){
        canvas.drawBitmap(background[stage], 0, 0, null);
        switch (stage){
            case STAGE_ONE_PHYSIQUE:
                determinePhysique.draw(canvas);
                break;
            case STAGE_TWO_HEIGHT:
                determineHeight.draw(canvas);
                break;
            case STAGE_THREE_GENDER:
                determineGender.draw(canvas);
                break;
            case STAGE_FOUR_CHARACTER:
                determineCharacter.draw(canvas);
                break;
            case STAGE_FIVE_COLOR:
                determineFavoriteColor.draw(canvas);
                break;
            case RESULT:
                showResult.draw(canvas);
                break;
        }
        holder.unlockCanvasAndPost(canvas);
    }

    public void pauseGame() {
        paused=true;
    }

    public void resumeGame() {
        paused=false;
    }

    public void exit(){
        pauseGame();
        musicStop();
        context.startActivity(new Intent(context, MainActivity.class));
        ((Activity)context).finish();
    }


    public boolean handleTouchEvent(MotionEvent event){
        switch (stage) {
            case STAGE_ONE_PHYSIQUE :
                determinePhysique.handleTouchEvent(event);
                break;
            case STAGE_TWO_HEIGHT:
                determineHeight.handleTouchEvent(event);
                break;
            case STAGE_THREE_GENDER:
                determineGender.handleTouchEvent(event);
                break;
            case STAGE_FOUR_CHARACTER:
                determineCharacter.handleTouchEvent(event);
                break;
            case STAGE_FIVE_COLOR:
                determineFavoriteColor.handleTouchEvent(event);
                break;
            case RESULT:
                showResult.handleTouchEvent(event);
                break;
        }

        return true;
    }

    public GameState getGameState() {
        GameState gameState=new GameState();
        gameState.stage=stage;
        gameState.container=container;
        gameState.next=next;
        gameState.determinePhysique=determinePhysique;
        gameState.determineHeight=determineHeight;
        gameState.determineGender=determineGender;
        gameState.determineCharacter=determineCharacter;
        gameState.determineFavoriteColor=determineFavoriteColor;
        gameState.player=player;
        return gameState;
    }

    public void stageUp(){
        stage++;
        next.inactivate();
        container.restore();
    }

    public void setResult(){
        showResult=new ShowResult(this, container.getCode());
    }

    public void musicStart(){
        player.start();
    }

    public void musicPause(){
        player.pause();
    }

    public void musicStop(){
        player.stop();
    }

    public int getStage(){
        return stage;
    }

    public void revitalize(){
        next.revitalize();
    }

    public float getWidth(){
        return width;
    }

    public float getHeight(){
        return height;
    }

    public Context getContext(){
        return context;
    }
}
