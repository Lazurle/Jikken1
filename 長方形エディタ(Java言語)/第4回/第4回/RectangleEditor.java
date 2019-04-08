
import java.applet.Applet;
import java.awt.Button;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Label;
import java.awt.Point;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;


public class RectangleEditor extends Applet implements ActionListener, MouseListener{

	TextField tf_x = new TextField(), tf_y = new TextField(),
			tf_w = new TextField(), tf_h = new TextField(), tf_color = new TextField();
	TextField tf_m_x = new TextField(), tf_m_y = new TextField();
	TextField tf_e_x = new TextField(), tf_e_y = new TextField();

	Button button_create, button_delete,
	button_deleteAll, button_move, button_expsrk, button_intersect;

	Label label_create = new Label("(x, y, w, h, Color)=");
	Label label_move = new Label("(x, y)=");
	Label label_expsrk = new Label("(x, y)=");

	int AppWidth, AppHeight;
	int mouse_counter = 0;
	int rec_selected;

	public void init(){
		AppWidth = getSize().width;
		AppHeight = getSize().height;
		setBackground(Color.gray);


		setLayout(null);

		button_create = new Button("Create");
		button_create.addActionListener(this);
		button_create.setBounds(520, 20, 100, 30);
		label_create.setBounds(630, 20, 100, 30);
		tf_x.setBounds(730, 20, 100, 30);
		tf_y.setBounds(830, 20, 100, 30);
		tf_w.setBounds(930, 20, 100, 30);
		tf_h.setBounds(1030, 20, 100, 30);
		tf_color.setBounds(1130, 20, 100, 30);

		button_delete = new Button("Delete");
		button_delete.addActionListener(this);
		button_delete.setBounds(520, 60, 100, 30);

		button_deleteAll = new Button("deleteAll");
		button_deleteAll.addActionListener(this);
		button_deleteAll.setBounds(520, 100, 100, 30);

		button_move = new Button("Move");
		button_move.addActionListener(this);
		button_move.setBounds(520, 140, 100, 30);
		label_move.setBounds(630, 140, 100, 30);
		tf_m_x.setBounds(730, 140, 100, 30);
		tf_m_y.setBounds(830, 140, 100, 30);

		button_expsrk = new Button("Exp/Srk");
		button_expsrk.addActionListener(this);
		button_expsrk.setBounds(520, 180, 100, 30);
		label_expsrk.setBounds(630, 180, 100, 30);
		tf_e_x.setBounds(730, 180, 100, 30);
		tf_e_y.setBounds(830, 180, 100, 30);

		button_intersect = new Button("Intersect");
		button_intersect.addActionListener(this);
		button_intersect.setBounds(520, 220, 100, 30);


		add(button_create);
		add(button_delete);
		add(button_deleteAll);
		add(button_move);
		add(button_expsrk);
		add(button_intersect);
		add(label_create);
		add(label_move);
		add(label_expsrk);
		add(tf_x);
		add(tf_y);
		add(tf_w);
		add(tf_h);
		add(tf_color);
		add(tf_m_x);
		add(tf_m_y);
		add(tf_e_x);
		add(tf_e_y);;
	}

	public void paint(Graphics g){
		g.setColor(Color.white);
		g.fillRect(0, 0, 500, 400);
		Rectangle[] paintrec = Board.rectangles;

		if(mouse_counter == 1){
			Rectangle material = paintrec[rec_selected];
			g.setColor(Color.black);
			g.drawRect(material.x, material.y, material.w, material.h);
		}

		for(int i=0; i<Board.rec_num ; i++){
			if(paintrec[i].j == true){
				if(paintrec[i].color.equals("red"))				g.setColor(Color.red);
				else if(paintrec[i].color.equals("blue")) 		g.setColor(Color.blue);
				else if(paintrec[i].color.equals("yellow")) 	g.setColor(Color.yellow);
				else if(paintrec[i].color.equals("cyan")) 		g.setColor(Color.cyan);
				else if(paintrec[i].color.equals("green")) 		g.setColor(Color.green);
				else if(paintrec[i].color.equals("orange")) 	g.setColor(Color.orange);
				else if(paintrec[i].color.equals("magenta")) 	g.setColor(Color.magenta);
				else if(paintrec[i].color.equals("gray")) 		g.setColor(Color.gray);
				g.fillRect(paintrec[i].x, paintrec[i].y, paintrec[i].w, paintrec[i].h);
			}
		}
	}



	public void mouseClicked(MouseEvent e) {

		Point p = e.getPoint();
		Rectangle[] mouse_rec = Board.rectangles;
		if((p.x >= 0 && p.x <= 500) && (p.y >= 0 && p.y <= 400)){
			if(mouse_counter == 0){
				for(int i = Board.rec_num ; i>=0 ; i--){
					if(Board.rectangles[i].j == true){
						if(p.x >= mouse_rec[i].x && p.x <= (mouse_rec[i].x + mouse_rec[i].w)
								&& p.y >= mouse_rec[i].y && p.y <= (mouse_rec[i].y + mouse_rec[i].h)){
							rec_selected = i;
							mouse_counter++;
							repaint();
							break;
						}
					}
				}
			}
			if(mouse_counter == 1){
				for(int i = Board.rec_num ; i>=0 ; i--){
					if(Board.rectangles[i].j == true){
						if(p.x >= mouse_rec[i].x && p.x <= (mouse_rec[i].x + mouse_rec[i].w)
								&& p.y >= mouse_rec[i].y && p.y <= (mouse_rec[i].y + mouse_rec[i].h)){
							if(rec_selected == i){
								mouse_counter--;
								rec_selected = 0;
								repaint();
								break;
							}
						}
					}
				}
			}
		}
		repaint();
	}

	public void mousePressed(MouseEvent e) {
	}
	public void mouseReleased(MouseEvent e) {
	}
	public void mouseEntered(MouseEvent e) {
	}
	public void mouseExited(MouseEvent e) {
	}

	public void actionPerformed(ActionEvent e) {

		Button button = (Button)e.getSource();
		Command cmd = new Command();

		// create
		if(button == button_create){
			String x = tf_x.getText().trim();
			String y = tf_y.getText().trim();
			String w = tf_w.getText().trim();
			String h = tf_h.getText().trim();
			String c = tf_color.getText().trim();
			cmd.create(x, y, w, h, c);
		}


		if(button == button_delete){
			cmd.delete(rec_selected);
		}


		if(button == button_deleteAll){
			cmd.deleteAll();
		}


		if(button == button_move){
			String x = tf_m_x.getText().trim();
			String y = tf_m_y.getText().trim();
			cmd.move(x, y, rec_selected);
		}


		if(button == button_expsrk){
			String x = tf_e_x.getText().trim();
			String y = tf_e_y.getText().trim();
			cmd.expsrk(x, y, rec_selected);
		}

	
		if(button == button_intersect){
			System.out.println("Now Developping.\n");
		}
		mouse_counter = 0;
		rec_selected = 0;
		repaint();
	}
}
