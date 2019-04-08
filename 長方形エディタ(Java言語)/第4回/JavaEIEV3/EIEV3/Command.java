package EIEV3;
import java.io.IOException;


public class Command {


	int x, y, w, h;										// x, y, width, height
	String color;										// color
	String intcol1, intcol2, newcol;					// contain of intersect
	boolean j;											// delete or not delete
	int del_num;										// delete number
	int mov_num /*mov_x=0, mov_y=0*/;					// move number, move_x, y, expand number
	int expsrk_num;										// expand/shrink number
//	double expsrk_x, expsrk_y;							// expand/shrink x, y
	int neww, newh;										// expsrk, newx, newy
	static int checker = 0;								// number of true(not deleted)
//	int intsec_1, intsec_2;								// number of intersect
//	int endof_w1, endof_w2, endof_h1, endof_h2,
//	max_x, max_y, intx, inty, intw, inth;				// contain of intersect


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
				System.out.println("既に長方形が作成されています\n");	/* Message Out */
			}
		}

	}

	// move ------------------------------------------------------------------------------------------------------------
	void move(String x, String y, int mov_num){

		Board b1 = new Board();

		int mov_x = Integer.parseInt(x);
		int mov_y = Integer.parseInt(y);

		//if
		if(Board.rectangles[mov_num].j == true
				&& ((mov_x + Board.rectangles[mov_num].x + Board.rectangles[mov_num].w) <= 500)
				&& ((mov_x + Board.rectangles[mov_num].x + Board.rectangles[mov_num].w) >= 0)
				&& ((mov_y + Board.rectangles[mov_num].y + Board.rectangles[mov_num].h) <= 400)
				&& ((mov_y + Board.rectangles[mov_num].y + Board.rectangles[mov_num].h) >= 0)
				&& Board.rec_num != 0){
			Board.rectangles[mov_num].x += mov_x;										/* Done */
			Board.rectangles[mov_num].y += mov_y; 										/* Done */
		}

	}

	// expsrk ----------------------------------------------------------------------------------------------------------
	void expsrk(String x, String y, int expsrk_num){

		Board b1 = new Board();

		double expsrk_x = Double.parseDouble(x);
		double expsrk_y = Double.parseDouble(y);

		neww = (int)Math.round(Board.rectangles[expsrk_num].w * expsrk_x);				/*  Done */
		// if
		if(Board.rectangles[expsrk_num].j == true
				&& neww <= 500 && Board.rec_num != 0)
			Board.rectangles[expsrk_num].w = neww;
		newh = (int)Math.round(Board.rectangles[expsrk_num].h * expsrk_y);				/*  Done */
		// if
		if(Board.rectangles[expsrk_num].j == true
				&& newh <= 400 && Board.rec_num != 0)
			Board.rectangles[expsrk_num].h = newh;

	}

	// delete ----------------------------------------------------------------------------------------------------------
	void delete(int del_num){

		if(Board.rec_num != 0){
			Board.rectangles[del_num].j = false;		// Deleted.
			Board.rectangles[del_num].x = 0;			// 0.
			Board.rectangles[del_num].y = 0;			// 0.
			Board.rectangles[del_num].w = 0;			// 0.
			Board.rectangles[del_num].h = 0;			// 0.
			checker--;
		}

	}

	// deleteAll -------------------------------------------------------------------------------------------------------
	void deleteAll(){

		if(Board.rec_num != 0){
			//////////////////////////////////
			for(int i=0 ; i<Board.rec_num ; i++){
				if(Board.rectangles[i].j == true){
					Board.rectangles[i].j = false;		// Deleted.
					Board.rectangles[i].x = 0;			// 0.
					Board.rectangles[i].y = 0;			// 0.
					Board.rectangles[i].w = 0;			// 0.
					Board.rectangles[i].h = 0;			// 0.
				}
			}
			checker = 0;
			//////////////////////////////////
		}

	}

	// displayBoard ----------------------------------------------------------------------------------------------------
	void displayBoard(){								// No Applet, No Change of before version.
		// No Applet, No Change of before version.
	}

	// intersect -------------------------------------------------------------------------------------------------------
	/*
	 * Phase.01: Choose 2 rectangles.
	 * Phase.02: Check intersect.
	 * Phase.03: make new rectangle.
	 * Phase.04: Change color
	 * Phase.05: Make new rectangle
	 * */
	void intersect() throws IOException{				// No Applet, No Change of before version.
		// No Applet, No Change of before version.
	}

	// exit ------------------------------------------------------------------------------------------------------------
	void exit(){										// No Applet, No Change of before version.
		// No Applet, No Change of before version.
	}
	////////////////////////////////////////////////////////////////////////////

}