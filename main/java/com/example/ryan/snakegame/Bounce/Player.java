package com.example.ryan.snakegame.Bounce;

import android.graphics.Canvas;
import android.graphics.Paint;

import com.example.ryan.snakegame.Location;

/**
 * Created by ryan on 3/11/2015.
 */
public class Player {
    private static Player ourInstance = new Player();

   public Location point;
    double velX;
    double velY;

    public static Player getInstance() {
        return ourInstance;
    }

    public Player(){
        point = new Location (0,0);
    }


    public Player(Location l) {

        point = l;
        velX = 0;
        velY = 0;
    }

    public void update()
    {
        velX *= .9;
        velY *= .9;

        point.changeX(velX);
        point.changeY(velY);
    }


    public void render(Canvas canvas,Paint paint)
    {

        if(canvas !=null) {
            canvas.drawCircle((float) point.x, (float) point.y, 50, paint);
        }



    }

}
