package GUISample.base.after;
import java.applet.Applet;
import java.awt.Graphics;
import java.awt.Color;


public class DrawSample extends Applet {
	private int APPLETWIDTH;
	private	int APPLETHEIGHT;
	private	int SPACE;
	private	int INTERVAL;
	private int REPEAT;
	private	int BALLWIDTH;
	private	int BALLHEIGHT;
	
	public void init() {
		APPLETWIDTH = getSize().width;
		APPLETHEIGHT = getSize().height;
		SPACE = 50;
		INTERVAL = 50;
		REPEAT = APPLETHEIGHT/INTERVAL;
		BALLWIDTH = 30;
		BALLHEIGHT = 30;
		setBackground(Color.white);		// îwåiêFê›íË
	}

	public void paint(Graphics g) {
		g.setColor(Color.blue);		// ï`âÊêFê›íË
		for(int i = 1; i < REPEAT; i++){
			g.drawLine(SPACE,  INTERVAL*i, APPLETWIDTH - SPACE, INTERVAL*i);
		}
		g.setColor(Color.green);
		for(int i = 1; i < REPEAT -1; i++){
			if (i%2==0){
				g.drawLine(SPACE*2,  INTERVAL*(i+1), APPLETWIDTH - SPACE*2, INTERVAL*i);
			} else{
				g.drawLine(SPACE*2,  INTERVAL*i, APPLETWIDTH - SPACE*2, INTERVAL*(i+1));
			}
		}
		
		g.setColor(Color.red);		// ï`âÊêFê›íË
		g.fillOval(SPACE+(BALLWIDTH/2), (INTERVAL-BALLHEIGHT), BALLWIDTH, BALLHEIGHT);
		g.fillOval(SPACE+(BALLWIDTH/2), (INTERVAL-BALLHEIGHT)+(REPEAT-2)*INTERVAL, BALLWIDTH, BALLHEIGHT);
		
	}
}

