package com.example.ryan.snakegame.snakeComponents;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.drawable.shapes.Shape;

import com.example.ryan.snakegame.Location;
import android.util.Log;

/**
 * Created by ryan on 2/13/2015.
 */
public class Part {

    public Location point;
    final String TAG = Part.class.getSimpleName();
    public Part(Location p)
    {
        point =p;


    }

    public void render(Canvas canvas,Paint paint)
    {
        if(canvas !=null)
        canvas.drawCircle((float)point.x,(float)point.y,50,paint);
    }

}
