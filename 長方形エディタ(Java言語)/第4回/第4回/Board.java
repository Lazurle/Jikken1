
public class Board {

	public static Rectangle[] rectangles = new Rectangle[999];
	public static int rec_num = 0;

	Board(Rectangle r){
		rectangles[rec_num] = r;
		rec_num++;
	}
	Board() {

	}

	public static boolean recchk(int x, int y, int w, int h){
		if(rec_num == 0) return true;
		for(int i=0 ; i<rec_num ; i++)
			if(x == rectangles[i].x && y == rectangles[i].y
			&& w == rectangles[i].w && h == rectangles[i].h) return false;
		return true;
	}

}
