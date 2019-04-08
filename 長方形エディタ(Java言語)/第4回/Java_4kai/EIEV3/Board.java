package EIEV3;


public class Board {

	public static Rectangle[] rectangles = new Rectangle[999]; 		// 配列に長方形を格納(最大10個)
	public static int rec_num = 0; 										// "static int: 変更が保存される"(Memo)

	Board(Rectangle r){
		rectangles[rec_num] = r;
		rec_num++;
	}
	Board(){
		
	}


	public static boolean recchk(int x, int y, int w, int h){
		if(rec_num == 0) return true;
		for(int i=0 ; i<rec_num ; i++)
			if(x == rectangles[i].x && y == rectangles[i].y
			&& w == rectangles[i].w && h == rectangles[i].h) return false;
		return true;
	}

}