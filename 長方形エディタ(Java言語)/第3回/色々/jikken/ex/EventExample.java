package example;

import java.applet.Applet;
import java.awt.Graphics;
import java.awt.Color;
import java.awt.event.MouseListener;
import java.awt.event.MouseEvent;


public class EventExample extends Applet implements MouseListener{
/**
 * Mouse Event Example
 * 
*/
	private final int MAXNUMBER = 10;
	private int xPosList[] = new int[MAXNUMBER];
	private int yPosList[] = new int[MAXNUMBER];
	private int currentNumber = 0;
		
	public void init(){
		setBackground(Color.blue);
		addMouseListener(this);
	}

	public void mousePressed(MouseEvent evt){
	}

	public void mouseEntered(MouseEvent evt){
	}

	public void mouseClicked(MouseEvent evt){
		int x = evt.getX();
		int y = evt.getY();
		if (currentNumber < MAXNUMBER){
			addSpot(x,y);
		} else this.getGraphics().drawString("Over!", 100, this.getHeight() - 100);
	}

	public void mouseReleased(MouseEvent evt){
	}

	public void mouseExited(MouseEvent evt){
	}

	private void addSpot(int x, int y){
		xPosList[currentNumber] = x;
		yPosList[currentNumber] = y;
		currentNumber++;
		repaint();
	}
	
	public void paint(Graphics g) {
		for (int i = 0 ; i < currentNumber; i++){
		    g.setColor(Color.green);
		    g.fillOval(xPosList[i]-10, yPosList[i]-10, 20, 20);
		}
	}
}

