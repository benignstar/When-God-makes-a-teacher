package ahn.mirim.firstiamnotme;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

/**
 * Created by 안성현 on 2017-10-17.
 */

public class GameSurfaceView extends SurfaceView implements SurfaceHolder.Callback{
    private GameThread gameThread;
    private GameState gameState;

    public GameSurfaceView(Context context, AttributeSet attrs) {
        super(context, attrs);

        gameThread=new GameThread(getHolder(), getContext(), null);
        getHolder().addCallback(this);
    }

    @Override
    public void surfaceCreated(SurfaceHolder surfaceHolder) {
        if(gameThread.getState().toString().equals("TERMINATED")){
            gameThread=new GameThread(getHolder(), getContext(), gameState);
            gameThread.start();
        } else {
            gameThread.start();
        }
    }

    @Override
    public void surfaceChanged(SurfaceHolder surfaceHolder, int i, int i1, int i2) {

    }

    @Override
    public void surfaceDestroyed(SurfaceHolder surfaceHolder) {
        gameState=gameThread.getGameState();
        gameThread.pauseGame();
        gameThread.musicPause();
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if(gameThread != null && gameThread.getState() != Thread.State.TERMINATED){
            return gameThread.handleTouchEvent(event);
        }
        else return false;
    }
}
