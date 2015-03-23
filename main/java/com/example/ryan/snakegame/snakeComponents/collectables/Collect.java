package com.example.ryan.snakegame.snakeComponents.collectables;

import android.graphics.Canvas;
import android.graphics.Paint;

import com.example.ryan.snakegame.Location;
import com.example.ryan.snakegame.SnakeBoard;
import com.example.ryan.snakegame.snakeComponents.Snake;

/**
 * Created by ryan on 2/27/2015.
 */
public class Collect implements Comparable  {

    public int sx;
    public int sy;
    public Location point;
    public Snake snake;
    SnakeBoard board;
    public Collect(Location l,int x,int y, Snake s, SnakeBoard b)
    {
        sx = x;sy=y;
        point = l;
        snake = s;
        board= b;
    }

    public void render(Canvas canvas,Paint paint)
    {
        if(canvas !=null)
            canvas.drawRect((float)point.x,(float)point.y,(float)point.x + sx,(float)point.y + sy,paint);
    }

    public int compareTo(Object l)
    {
        Location lo =((Collect) l).point;
        double f = lo.getX()- point.getX();

        return f==0 ? (int)(lo.getY()-point.getY()):(int)f;
    }
    public boolean equals(Object l)
    {
        Location lo =((Collect) l).point;
        return lo.getX()== point.getX()? lo.getY() == point.getY() : false;
    }

    public void collected()
    {

    }


    public String toString()
    {
        return point.toString();
    }
}
