package EIEV3;

import java.io.*;
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

public class RectangleEditor extends Applet implements Runnable {
    BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    Command command = new Command();

    private int AppletWidth;
    private int AppletHeight;

    public void init() {
        AppletWidth = getSize().width;
        AppletHeight = getSize().height;
        setBackground(Color.gray);
        setLayout(null);
        Thread thread = new Thread(this);
        thread.start();
    }

    public void paint(Graphics g){
        g.setColor(Color.white);
        g.fillRect(0, 0, 500, 400);

        for (int i = 0; i < command.board.array.size(); i++) {
            if (command.board.array.get(i).getColor().equals("red")) {
                g.setColor(Color.red);
            } else if (command.board.array.get(i).getColor().equals("blue")) {
                g.setColor(Color.blue);
            } else if (command.board.array.get(i).getColor().equals("yellow")) {
                g.setColor(Color.yellow);
            } else if (command.board.array.get(i).getColor().equals("cyan")) {
                g.setColor(Color.cyan);
            } else if (command.board.array.get(i).getColor().equals("green")) {
                g.setColor(Color.green);
            } else if (command.board.array.get(i).getColor().equals("orange")) {
                g.setColor(Color.orange);
            } else if (command.board.array.get(i).getColor().equals("magenta")) {
                g.setColor(Color.magenta);
            } else if (command.board.array.get(i).getColor().equals("gray")) {
                g.setColor(Color.gray);
            }

            g.fillRect(command.board.array.get(i).getX(), command.board.array.get(i).getY(), command.board.array.get(i).getWidth(), command.board.array.get(i).getHeight());
        }
    }

    public void run() {
        while (true) {
            repaint();
            command.inputCommand();
        }
    }
}
