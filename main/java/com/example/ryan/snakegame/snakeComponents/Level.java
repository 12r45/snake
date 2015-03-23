package com.example.ryan.snakegame.snakeComponents;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.Log;

import com.example.ryan.snakegame.Location;
import com.example.ryan.snakegame.SnakeBoard;
import com.example.ryan.snakegame.snakeComponents.collectables.Collect;
import com.example.ryan.snakegame.snakeComponents.obstacles.Obstacle;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.TreeSet;

/**
 * Created by ryan on 3/16/2015.
 */
public class Level {

    final String TAG = Level.class.getSimpleName();

    public TreeSet<Obstacle> obs;
    public TreeSet<Collect> coll;
    public Snake snake;
    Paint paint;
    double width;
    double height;
    SnakeBoard board;

    public Level(Paint p, double h, double w, SnakeBoard b)
    {
        obs= new TreeSet<Obstacle>();
        coll = new TreeSet<Collect>();
        paint = p;
        width  = w;
        height = h;
        board = b;
    }

    public Level(Paint p, double h, double w, SnakeBoard b, String file)
    {   this(p,h,w,b);
        readFile(file);
    }


    public void update( Canvas canvas,double h, double w) {

        width  = w;
        height = h;

        if (snake != null) {
            snake.update(canvas, paint);
        }

        if (snake != null && snake.obs.size() != obs.size()) {
            snake.setObs(obs);
            snake.setColl(coll);
        }

        if (obs != null) {
            for (Obstacle ob : obs) {
                ob.render(canvas, paint);
            }
        }

        if (coll != null) {
            for (Collect ob : coll) {

                ob.render(canvas, paint);
            }
        }
/*
        if (obs.size() < 1 && height > 0) {
            double x = Math.random() * width;
            double y = Math.random() * (height - 200);
            obs.add(new Obstacle(new Location(x, y), 100, 300));
            //Log.d(TAG,"obs cords "+x+" "+y +" "+ getHeight() );
        }

        if (coll.size() < 1 && height > 0) {
            double x = Math.random() * width;
            double y = Math.random() * (height - 200);
            coll.add(new Collect(new Location(x, y), 100, 100, snake, board));

        }
        */
    }

    public void render()
    {

    }

    public void readFile(String file)
    {
        Log.d(TAG,height +" "+ width);

            Scanner scann = new Scanner(file);
            while(scann.hasNextLine())
            {
                String line = scann.nextLine();

                Scanner scan = new Scanner(line);

                if(scan.next().equals("obs"))
                {
                   double x = 0;
                   double y = 0;
                   int sx = 0;
                   int sy = 0;
                   if(scan.next().equals("half"))
                   {
                       Log.d(TAG,"x half");
                       x = width/2;
                   }
                   else if(scan.hasNextInt())
                   {
                       Log.d(TAG,"x num");
                       x = scan.nextInt();
                   }


                   if(scan.next().equals("half"))
                   {
                       Log.d(TAG,"y half");
                       y = height/2;
                   }
                   else if(scan.hasNextInt())
                   {
                       Log.d(TAG,"y num");
                       y = scan.nextInt();
                   }
                   if(scan.hasNextInt())
                   {
                        sx = scan.nextInt();
                   }
                   if(scan.hasNextInt())
                   {
                        sy = scan.nextInt();
                   }
                   obs.add(new Obstacle(new Location(x,y),sx,sy));
                }

            }






    }


}
