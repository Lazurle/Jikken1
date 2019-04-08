import java.util.ArrayList;
import java.util.List;
 
class Board{
    int width;
    int height;
    List<Rectangle> recs=new ArrayList<Rectangle>(10);
    //Rectangle recs[]=new Rectangle[10];
    //    int i=0;


    void setRectangle(Rectangle r){
	recs.add(r);
	//i++;
    }

    void showRectangle(){
	Rectangle r;
       	for(int j=0;j<recs.size();j++){
	    r = recs.get(j);  //表示する長方形のデータを取得

	    System.out.println("長方形"+j);
	    System.out.println("ｘ座標："+r.x);
	    System.out.println("ｙ座標："+r.y);
	    System.out.println("高さ："+r.height);
	    System.out.println("幅："+r.width);
	}
    }
 
}