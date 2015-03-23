package com.example.ryan.snakegame.snakeComponents;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;
import com.example.ryan.snakegame.Location;
import com.example.ryan.snakegame.snakeComponents.collectables.Collect;
import com.example.ryan.snakegame.snakeComponents.obstacles.Obstacle;

import android.util.Log;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.Queue;
import java.util.TreeSet;

/**
 * Created by ryan on 2/13/2015.
 */
public class Snake {
    final String TAG = Snake.class.getSimpleName();

    private Queue<Part> parts;
    double velx;
    double vely;

    int w;
    int h;


    public TreeSet<Obstacle> obs;
    public TreeSet<Collect> coll;
    Location point;
    public Snake(double x,double y,int width, int height)
    {
        parts = new LinkedList<Part>();
        velx = x;
        vely = y;
        w= width;
        h= height;
        parts.add(new Part(new Location(width/2,height/2) ));

        point = new Location(width/2,height);
        obs= new TreeSet<Obstacle>();
        coll = new TreeSet<Collect>();
    }

    public void setObs(TreeSet<Obstacle> o)
    {
        obs = o;
    }
    public void setColl(TreeSet<Collect> o)
    {
        coll = o;
    }

    public void update(Canvas canvas,Paint paint)
    {
        checkEdge();
        addPart();
        render(canvas,paint);
    }


    private void addPart()
    {
        if(parts.size() > 10) {

            parts.poll();
        }
        Location first = point;
        Part temp  =new Part(new Location(first.x + velx, first.y + vely));
        parts.add(temp);
        point = temp.point;


    }

    private void render(Canvas canvas,Paint paint)
    {

        Part start = parts.peek();
        parts.offer(parts.poll());
        while(parts.peek() != start)
        {
            Part temp = parts.poll();
            temp.render(canvas,paint);
            parts.offer(temp);
        }
    }

    public void checkEdge()
    {
        Location first = point;
        double x = first.x;
        double y = first.y;
        if(x + velx > w )
        {
            velx *= -1;
        }
        else if(x + velx < 0  )
        {
            velx *= -1;
        }
        else if(y +vely > h )
        {
            vely *= -1;
        }
        else if(y + vely < 0  )
        {
            vely *= -1;
        }
        for(Obstacle ob : obs)
        {
            double obx = ob.point.x;
            double oby = ob.point.y;
            if(x + velx > obx && x + velx < obx + ob.sx && y +vely > oby && y + vely < oby + ob.sy )
            {
                if(x  <= obx || x >= obx + ob.sx) {
                velx *=-1;
                    Log.d(TAG,"hit x "+x +" "+y );
                }
                else
                {
                    Log.d(TAG,"hit y "+x +" "+ y );
                    vely*=-1;
                }
            }
        }

        // collectables
        Iterator<Collect> iter = coll.iterator();
        while(iter.hasNext())
        {
            Collect ob = iter.next();
            // Log.d(TAG,""+ob.point.x +" "+ ob.point.y);
            double obx = ob.point.x;

            double oby = ob.point.y;
            if(x + velx > obx && x + velx < obx + ob.sx && y +vely > oby && y + vely < oby + ob.sy )
            {
            ob.collected();
            iter.remove();
            }
        }

    }





}
