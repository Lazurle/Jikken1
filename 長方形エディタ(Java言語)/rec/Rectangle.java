package rec;

import java.util.*;
import java.io.*;

public class Rectangle {
    private int x = 0;
    private int y = 0;
    private int width = 0;
    private int height = 0;
    private String color = null;
    Rectangle(int x,int y,int widht,int height,String color){
        this.x = x;
        this.y = y;
        this.width = widht;
        this.height = height;
        this.color = color;
    }

    public void setX(int x){
        this.x = x;
    }
    public void setY(int y){
        this.y = y;
    }
    public void setWidth(int width){
        this.width = width;
    }
    public void setHeight(int height){
        this.height = height;
    }
    public void setColor(String color){
        this.color = color;
    }

    public int getX(){
        return this.x;
    }
    public int getY(){
        return this.y;
    }
    public int getWidth(){
        return this.width;
    }
    public int getHeight(){
        return this.height;
    }
    public String getColor(){
        return this.color;
    }
}
