package EIEV3;

import java.applet.Applet;
import java.awt.Graphics;
import java.awt.Color;
import java.awt.*;

public class RectangleEditor extends Applet implements Runnable{
    Board brd;
    Thread aThread;
    
    public void init(){
        aThread = new Thread(this);
        Dimension d =getSize();
        brd=new Board(d.width,d.height);
        aThread.start();
            setBackground(Color.white);
    }
    
    public void paint(Graphics g){     
        Rectangle r=new Rectangle(0,0,0,0,null);
        for(int i=0;i<brd.rects.size();i++){
            r = brd.rects.get(i);  //描画する長方形のデータを取得
            if(r.color.equals("red"))g.setColor(Color.red);
            else if(r.color.equals("blue"))g.setColor(Color.blue);
            else if(r.color.equals("yellow"))g.setColor(Color.yellow);
            else g.setColor(Color.red);
        }
        g.fillRect(r.x, r.y, r.width, r.height);	
    }
    
/*    public void main(String argc[]){
        run();
    }*/
    
    public void run(){
	Input in=new Input();

	while(in.input(brd)){
	   repaint();
    }
    }    
  
}
