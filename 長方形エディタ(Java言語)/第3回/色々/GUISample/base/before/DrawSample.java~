package GUISample.base.before;
import java.applet.Applet;
import java.awt.Graphics;
import java.awt.Color;

public class DrawSample extends Applet {
	public void init() {
		setBackground(Color.white);		// ”wŒiFİ’è
	}
	public void paint(Graphics g) {
		g.setColor(Color.blue);		// •`‰æFİ’è
		for(int i = 1; i < 10; i++){
			g.drawLine(50,  50*i, 450, 50*i);
		}
		g.setColor(Color.green);
		for(int i = 1; i < 9; i++){
			if (i%2==0){
				g.drawLine(100,  50*(i+1), 400, 50*i);
			} else{
				g.drawLine(100,  50*i, 400, 50*(i+1));
			}
		}
		
		g.setColor(Color.red);		// •`‰æFİ’è
		g.fillOval(50+(30/2), (50-30), 30, 30);
		g.fillOval(50+(30/2), (50-30)+8*50, 30, 30);
		
	}
}

