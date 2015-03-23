package com.example.ryan.snakegame.snakeComponents.collectables;

import android.graphics.Canvas;
import android.graphics.Paint;

import com.example.ryan.snakegame.Location;
import com.example.ryan.snakegame.SnakeBoard;
import com.example.ryan.snakegame.snakeComponents.Snake;

/**
 * Created by ryan on 2/27/2015.
 */
public class Finish extends Collect {

    public Finish(Location l,int x,int y, Snake s, SnakeBoard b)
    {
        super(l,x,y,s, b);
    }

    public void collected()
    {
        super.board.levelUp();
    }


}
