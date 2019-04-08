package example;
import java.applet.Applet;
import java.awt.Checkbox;
import java.awt.CheckboxGroup;
import java.awt.Graphics;
import java.awt.Color;
import java.awt.event.ItemListener;
import java.awt.event.ItemEvent;		


public class CheckboxExample extends Applet implements ItemListener {

/**
 * ラジオボタンを定義する：Checkboxを使って択一式のラジオボタンをつくる。
 * Checkboxが選択されたことを感知するため、ItemListenerを実装する。
 */

	private Checkbox chx1, chx2, chx3, chx4;
	private boolean b1, b2, b3,b4;
	private Color color= Color.red;
/**
 * 色を指定するためのラジオボタンの生成：ここでは4つ生成。
 * Checkboxを必要な数だけ作成し、グループ化する。各Checkboxにはラベル、グループ、初期状態を設定する。
 * ここではredが選択されているということ。
 * 各CheckBoxが選択されていることを感知するためにItemListenerを登録する。
 * CheckBoxをアプレットに追加する。
 */

	public void init(){
	    CheckboxGroup cbg = new CheckboxGroup();
	    this.chx1 = new Checkbox("red",cbg,true);
	    this.chx1.addItemListener(this);
	    this.add(chx1);

	    this.chx2 = new Checkbox("blue",cbg,false);
	    this.chx2.addItemListener(this);
	    this.add(chx2);

	    this.chx3 = new Checkbox("yellow",cbg,false);
	    this.chx3.addItemListener(this);
	    this.add(chx3);

	    this.chx4 = new Checkbox("gray",cbg,false);
	    this.chx4.addItemListener(this);
	    this.add(chx4);
	}
/**
 * ラジオボタンが選択された時の動作
 * 選択されたボタンに応じて文字を表示する色をcolorに設定する。
 */

	public void itemStateChanged(ItemEvent e){
	    if(e.getItemSelectable() == chx1) color = Color.red;
	    if(e.getItemSelectable() == chx2) color = Color.blue;
	    if(e.getItemSelectable() == chx3) color = Color.yellow;
	    if(e.getItemSelectable() == chx4) color = Color.gray;

	    this.b1 = chx1.getState();
	    this.b2 = chx2.getState();
	    this.b3 = chx3.getState();
	    this.b4 = chx4.getState();
	    this.repaint();
	}

/**
 * 選択された色で文字を表示する。
 * すべてのボタンの状態を表示する。
*/	
	
	public void paint(Graphics g) {
		g.setColor(color);
		g.drawString("色は" + color, 10, 50);
		g.drawString("今の状態は" + b1+ b2+ b3 + b4, 10, 70);
            }
}
