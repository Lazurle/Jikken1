package EIEV3;
import java.util.ArrayList;
import java.util.List;
import java.awt.Color;


class Board{
    int width;
    int height;
    Color boardColor;
    List<Rectangle> rects=new ArrayList<Rectangle>(10);
   
    Board(int width,int height){
	this.width=width;
	this.height=height;
	this.boardColor=Color.white;
    }
    

    void setRectangle(Rectangle r){
	Rectangle rect;
	rects.add(r);  

	for(int i=0;i<rects.size()-1;i++){	 
	    rect=rects.get(i);

	    if(r.x==rect.x && r.y==rect.y 
	       && r.width==rect.width && r.height==rect.height){
		System.out.println("同一の長方形があるため作成を中止しました。");
		rects.remove(rects.size()-1);
		break;
	    }

	}
    }

    void showRectangle(){
	Rectangle r;
       	for(int j=0;j<rects.size();j++){
	    r = rects.get(j);  //表示する長方形のデータを取得

	    System.out.println("長方形"+j);
	    System.out.println("ｘ座標："+r.x);
	    System.out.println("ｙ座標："+r.y);	    
	    System.out.println("幅："+r.width);
	    System.out.println("高さ："+r.height);
	    System.out.println("色："+r.color);
	}
    }
 
}