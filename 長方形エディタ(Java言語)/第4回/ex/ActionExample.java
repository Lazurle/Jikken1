package example;
import java.applet.Applet;
import java.awt.TextField;
import java.awt.Label;
import java.awt.Button;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Color;	
import java.awt.Graphics;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class ActionExample extends Applet implements ActionListener {
    private final int SIZE = 4;
    private TextField[] valueFields = new TextField[SIZE];
    private Label[] labels = new Label[SIZE];			
    private double[] values = new double[SIZE];		
    private Button dispButton;				

    public void init( ) {
	labels[0] = new Label("width=");
	labels[1] = new Label("height= ");
	labels[2] = new Label("x =");
	labels[3] = new Label("y =");
	for (int i = 0; i < SIZE; i++) {
		this.add(labels[i]);
		valueFields[i] = new TextField("0",5);
		this.add(valueFields[i]);
	}
	this.dispButton = new Button("Disp");
	this.add(dispButton);
	dispButton.addActionListener(this);
   }

    public void actionPerformed(ActionEvent evt) {
	Button button = (Button)evt.getSource( );
	if (button == this.dispButton) {
	    for (int i = 0; i < SIZE; i++) {
		if (valueFields[i].getText( ).equals(""))
		    values[i] = 0;
		else
		    values[i] = new Double(valueFields[i].getText( )).floatValue( );
	    }
	    this.repaint();
	}
    }

    public void paint(Graphics g) {
	Font font = new Font("TimesRoman",Font.BOLD,24);
	g.setColor(Color.red);
	g.setFont(font);
	FontMetrics fm = g.getFontMetrics(font); 
	int h = fm.getHeight();
	g.drawString("•‚Í" + values[0], 10, 50+h);
	g.drawString("‚‚³‚Í" + values[1], 10, 50+h*2);
	g.drawString("xÀ•W‚Í" + values[2], 10, 50+h*3);
	g.drawString("yÀ•W‚Í" + values[3], 10, 50+h*4);
    }
}

