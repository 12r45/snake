/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.ryan.snakegame;
import java.lang.*;
/**
 *
 * @author ryan
 */
public class Location implements Comparable {
   public double x;
    public double y;
    public Location(double X, double Y)
    {
        x = X;
        y = Y;
    }
    
    public double getX()
    {
        return x;
    }
    
    public double getY()
    {
        return y;
    }
   
    public void changeX(double a)
    {
        x+=a;
    }
    
     public void changeY(double a)
    {
        y+=a;
    }
    
    public String toString()
    {
        return x+" "+y+" ";
    }
    
    public int compareTo(Object l)
    {
        Location lo =(Location) l;
        double f = lo.getX()- this.getX();
        
        return f==0 ? (int)(lo.getY()-this.getY()):(int)f;
    }
    public boolean equals(Object l)
    {
        Location lo =(Location) l;
        return lo.getX()== this.getX()? lo.getY() ==this.getY() : false;
    }
}
