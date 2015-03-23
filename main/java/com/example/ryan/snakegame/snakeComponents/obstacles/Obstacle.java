package com.example.ryan.snakegame.snakeComponents.obstacles;

import android.graphics.Canvas;
import android.graphics.Paint;

import com.example.ryan.snakegame.Location;

/**
 * Created by ryan on 2/14/2015.
 */
public class Obstacle implements Comparable {
    public int sx;
    public int sy;
    public Location point;
    public Obstacle(Location l,int x,int y )
    {
        sx = x;sy=y;
        point = l;
    }
    public void render(Canvas canvas,Paint paint)
    {
        if(canvas !=null)
            canvas.drawRect((float)point.x,(float)point.y,(float)point.x + sx,(float)point.y + sy,paint);
    }

    public int compareTo(Object l)
    {
        Location lo =((Obstacle) l).point;
        double f = lo.getX()- point.getX();

        return f==0 ? (int)(lo.getY()-point.getY()):(int)f;
    }
    public boolean equals(Object l)
    {
        Location lo =((Obstacle) l).point;
        return lo.getX()== point.getX()? lo.getY() == point.getY() : false;
    }
    public String toString()
    {
        return point.toString();
    }

}
