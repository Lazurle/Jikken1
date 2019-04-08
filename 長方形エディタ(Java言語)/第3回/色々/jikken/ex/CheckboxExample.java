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
 * ���W�I�{�^�����`����FCheckbox���g���đ��ꎮ�̃��W�I�{�^��������B
 * Checkbox���I�����ꂽ���Ƃ����m���邽�߁AItemListener����������B
 */

	private Checkbox chx1, chx2, chx3, chx4;
	private boolean b1, b2, b3,b4;
	private Color color= Color.red;
/**
 * �F���w�肷�邽�߂̃��W�I�{�^���̐����F�����ł�4�����B
 * Checkbox��K�v�Ȑ������쐬���A�O���[�v������B�eCheckbox�ɂ̓��x���A�O���[�v�A������Ԃ�ݒ肷��B
 * �����ł�red���I������Ă���Ƃ������ƁB
 * �eCheckBox���I������Ă��邱�Ƃ����m���邽�߂�ItemListener��o�^����B
 * CheckBox���A�v���b�g�ɒǉ�����B
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
 * ���W�I�{�^�����I�����ꂽ���̓���
 * �I�����ꂽ�{�^���ɉ����ĕ�����\������F��color�ɐݒ肷��B
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
 * �I�����ꂽ�F�ŕ�����\������B
 * ���ׂẴ{�^���̏�Ԃ�\������B
*/	
	
	public void paint(Graphics g) {
		g.setColor(color);
		g.drawString("�F��" + color, 10, 50);
		g.drawString("���̏�Ԃ�" + b1+ b2+ b3 + b4, 10, 70);
            }
}
