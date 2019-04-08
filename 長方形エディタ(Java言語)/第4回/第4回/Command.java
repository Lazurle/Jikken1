import java.io.IOException;

public class Command {


	int x, y, w, h;
	String color;
	String intcol1, intcol2, newcol;
	boolean j;
	int del_num;
	int mov_num;
	int expsrk_num;
	int neww, newh;
	static int checker = 0;


	void create(String x, String y, String w, String h, String c){
		Board b1 = new Board();

		int paint_x = Integer.parseInt(x);
		int paint_y = Integer.parseInt(y);
		int paint_w = Integer.parseInt(w);
		int paint_h = Integer.parseInt(h);

		if(paint_x >= 0 && paint_x < 499 && paint_y >= 0 && paint_y < 399 && checker <= 10){
			if(Board.recchk(paint_x, paint_y, paint_w, paint_h)){
				Rectangle r = new Rectangle(paint_x, paint_y, paint_w, paint_h, c);
				Board board = new Board(r);
				checker++;
			}
			else{
				System.out.println("既に長方形が作成されています.\n");
			}
		}

	}

	void move(String x, String y, int mov_num){

		Board b1 = new Board();

		int mov_x = Integer.parseInt(x);
		int mov_y = Integer.parseInt(y);


		if(Board.rectangles[mov_num].j == true
				&& ((mov_x + Board.rectangles[mov_num].x + Board.rectangles[mov_num].w) <= 500)
				&& ((mov_x + Board.rectangles[mov_num].x + Board.rectangles[mov_num].w) >= 0)
				&& ((mov_y + Board.rectangles[mov_num].y + Board.rectangles[mov_num].h) <= 400)
				&& ((mov_y + Board.rectangles[mov_num].y + Board.rectangles[mov_num].h) >= 0)
				&& Board.rec_num != 0){
			Board.rectangles[mov_num].x += mov_x;
			Board.rectangles[mov_num].y += mov_y;
		}

	}

	void expsrk(String x, String y, int expsrk_num){

		Board b1 = new Board();

		double expsrk_x = Double.parseDouble(x);
		double expsrk_y = Double.parseDouble(y);

		neww = (int)Math.round(Board.rectangles[expsrk_num].w * expsrk_x);

		if(Board.rectangles[expsrk_num].j == true
				&& neww <= 500 && Board.rec_num != 0)
			Board.rectangles[expsrk_num].w = neww;
		newh = (int)Math.round(Board.rectangles[expsrk_num].h * expsrk_y);
		if(Board.rectangles[expsrk_num].j == true
				&& newh <= 400 && Board.rec_num != 0)
			Board.rectangles[expsrk_num].h = newh;

	}

	void delete(int del_num){

		if(Board.rec_num != 0){
			Board.rectangles[del_num].j = false;
			Board.rectangles[del_num].x = 0;
			Board.rectangles[del_num].y = 0;
			Board.rectangles[del_num].w = 0;
			Board.rectangles[del_num].h = 0;
			checker--;
		}

	}

	void deleteAll(){

		if(Board.rec_num != 0){
			//////////////////////////////////
			for(int i=0 ; i<Board.rec_num ; i++){
				if(Board.rectangles[i].j == true){
					Board.rectangles[i].j = false;
					Board.rectangles[i].x = 0;
					Board.rectangles[i].y = 0;
					Board.rectangles[i].w = 0;
					Board.rectangles[i].h = 0;
				}
			}
			checker = 0;
		}

	}

	void displayBoard(){
	}


	void intersect() throws IOException{

	}

	void exit(){

	}
}
