package ahn.mirim.firstiamnotme;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.view.MotionEvent;

public class Spice extends Ingredient{
    private Bitmap check;
    private Rect area;
    private boolean press;
    private boolean selected;
    private Container container;
    private GameThread gameThread;
    private Bitmap temp[]=new Bitmap[2];


    public Spice(GameThread gameThread, int x, int y, int kind, Container container){
        this.x=x;
        this.y=y;
        this.container=container;
        this.gameThread=gameThread;

        w=(int)(gameThread.getWidth()/32*3);
        h=(int)(gameThread.getWidth()/32*6);

        temp[0]= BitmapFactory.decodeResource(gameThread.getContext().getResources(), R.drawable.spice00+kind);
        temp[0]=Bitmap.createScaledBitmap(temp[0], w, h, false);

        check=BitmapFactory.decodeResource(gameThread.getContext().getResources(), R.drawable.spice_selected);
        check=Bitmap.createScaledBitmap(check, w, h, false);

        temp[1]=Bitmap.createBitmap(temp[0].getWidth(), temp[0].getHeight(), temp[0].getConfig());
        Canvas canvas = new Canvas(temp[1]);
        canvas.drawBitmap(temp[0], 0, 0, null);
        canvas.drawBitmap(check, 0, 0, null);

        image=temp[0];

        recipe=kind;
        area=new Rect(x, y, x+w, y+h);
        press=false;
        selected=false;

    }

    public void draw(Canvas canvas){


        canvas.drawBitmap(image, x, y, null);
    }

    public void restore(){
        press=false;
        image=temp[0];
    }

    public boolean touchEvent(MotionEvent event){
        int x=(int)event.getX();
        int y=(int)event.getY();

        if(area.contains(x, y)) {
            if (event.getAction() == MotionEvent.ACTION_DOWN) {
                press=true;
                image=temp[1];
                return false;
            } else if (event.getAction() == MotionEvent.ACTION_UP && press) {
                press=false;
                container.add(this);
             //   image=temp[0];
                gameThread.revitalize();
                return true;
            }

        }
        return false;
    }
}
