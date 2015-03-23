package com.example.ryan.snakegame;

/**
 * Created by ryan on 2/13/2015.
 */
import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.util.Log;
import android.view.*;

import com.example.ryan.snakegame.snakeComponents.Level;
import com.example.ryan.snakegame.snakeComponents.Snake;
import com.example.ryan.snakegame.snakeComponents.collectables.Collect;
import com.example.ryan.snakegame.snakeComponents.obstacles.Obstacle;

import java.io.File;
import java.util.TreeSet;

public class SnakeBoard extends  SurfaceView implements SurfaceHolder.Callback{
    public SnakeThread thread;
    final String TAG = SnakeBoard.class.getSimpleName();
    int currX;
    int currY;
    Paint paint;
    Snake snake;
    TreeSet<Obstacle> obs;
    public TreeSet<Collect> coll;
    Level level;
    int levelNum;
    SharedPreferences levels;
    public static final String PREFS_NAME = "levels";
    //Obstacle ob;
    public SnakeBoard(Context context, SharedPreferences pref){
        super(context);
        getHolder().addCallback(this);
        thread = new SnakeThread(getHolder(),this);
        setFocusable(true);
        paint = new Paint();
        levelNum = 0;
        levels = pref;
        level = new Level(paint, getHeight(),getWidth(), this);


    }

    public void setCords(int x, int y)
    {
        currX = x;
        currY = y;
    }

    public SnakeBoard  (Context context, AttributeSet attrs)
    {   super(context,attrs);}

    public SnakeBoard (Context context, AttributeSet attrs, int defStyleAttr)
    {
        super(context,attrs,defStyleAttr);
    }
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        return super.onTouchEvent(event);
    }

    public void release()
    {
        double x = currX -getWidth()/2 ;
       // double factor = x > (getHeight() - currY) ? x : (getHeight() - currY);

        double deltaX = x ;
        double deltaY = getHeight() - (currY);
        double factor = 100/ Math.sqrt(deltaX * deltaX + deltaY *deltaY);
        level.snake = new Snake(deltaX * factor,deltaY * factor,getWidth() ,getHeight());

       // Log.d(TAG,x / ((factor))  +" "+(-1*(getHeight() - currY)/factor) );
      // level.snake = new Snake((-1*x / (factor))*100,(-1*(getHeight() - currY)/factor)*100,getWidth() ,getHeight());

    }

    public void update()
    {
    }

    public void render(Canvas canvas)
    {
        if(levelNum ==0 && getHeight() != 0)
        {
            levelUp();
        }

        onDraw(canvas);
        level.update(canvas,getHeight(),getWidth());
    }

    @Override
    protected void onDraw (Canvas canvas)
    {
        if(canvas !=null) {
            canvas.drawARGB(255, 50, 50, 100);
            paint.setARGB(255,0,255,0);
            paint.setTextSize(55);
            canvas.drawText((getWidth() / 2 - currX ) + " " + (getHeight()-currY), getWidth() / 2, getHeight() / 2, paint);
            canvas.drawLine(getWidth() / 2, getHeight(),currX,currY,paint);
        }
    }

    public void levelUp()
    {
        levelNum++;


       String str = levels.getString("l"+levelNum,"");

        Log.d(TAG,str);

        level = new Level(paint, getHeight(),getWidth(), this, str);


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

}
