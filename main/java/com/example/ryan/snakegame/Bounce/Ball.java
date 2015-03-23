package com.example.ryan.snakegame.Bounce;

import com.example.ryan.snakegame.Location;

/**
 * Created by ryan on 3/11/2015.
 */
public class Ball {
    Location point;

    Location vel;

    public void Ball(Location l, Location velocity )
    {
        point = l;
        vel = velocity;
    }


    public void render()
    {

    }
}
