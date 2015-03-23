package com.example.ryan.snakegame.Bounce;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import com.example.ryan.snakegame.Location;
import com.example.ryan.snakegame.snakeComponents.Snake;
import com.example.ryan.snakegame.snakeComponents.collectables.Collect;
import com.example.ryan.snakegame.snakeComponents.obstacles.Obstacle;

import java.util.ArrayList;
import java.util.TreeSet;

/**
 * Created by ryan on 3/11/2015.
 */
public class Board extends SurfaceView implements SurfaceHolder.Callback{

    public BThread thread;
    final String TAG = Board.class.getSimpleName();
    int currX;
    int currY;
    Paint paint;

    ArrayList<Player> players;

    public Board(Context context){
        super(context);
        getHolder().addCallback(this);
        thread = new BThread(getHolder(),this);
        setFocusable(true);
        paint = new Paint();
        players = new ArrayList<Player>();

        players.add(new Player(new Location(0,0)));
    }

    public Board  (Context context, AttributeSet attrs)
    {   super(context,attrs);}

    public Board (Context context, AttributeSet attrs, int defStyleAttr)
    {
        super(context,attrs,defStyleAttr);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {


            Log.d(TAG,event.getX() +" "+event.getY() );
            Location loc = players.get(0).point;
            double deltaX = event.getX() - loc.getX();
            double deltaY = event.getY() - loc.getY();
            double factor = 10/ Math.sqrt(deltaX * deltaX + deltaY *deltaY);
            players.get(0).velX =deltaX * factor;
            players.get(0).velY = deltaY * factor;



        return true;
    }

    @Override
    public void surfaceCreated(SurfaceHolder surfaceHolder) {


    }


    @Override
    public void surfaceChanged(SurfaceHolder surfaceHolder, int i, int i2, int i3) {

    }

    @Override
    public void surfaceDestroyed(SurfaceHolder surfaceHolder) {
        boolean retry = true;
        while (retry) {
            try {
                thread.join();
                retry = false;
            } catch (InterruptedException e) {
                // try again shutting down the thread
            }
        }
    }

    public void killTread()
    {
        boolean retry = true;
        while (retry) {
            try {
                thread.join();
                retry = false;
            } catch (InterruptedException e) {
                // try again shutting down the thread
            }
        }
    }

    public void startThread()
    {
        thread.setRunning(true);

        thread.start();
    }

    public void render(Canvas canvas)
    {
        onDraw(canvas);

        for(Player play : players)
        {
            play.update();
            play.render(canvas,paint);

        }

    }

     public void update()
     {

     }

        @Override
     protected void onDraw (Canvas canvas) {
            if (canvas != null) {
                canvas.drawARGB(255, 50, 50, 100);
                paint.setARGB(255, 0, 255, 0);
            }
        }
}
