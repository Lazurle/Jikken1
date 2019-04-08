// BP14003 秋山和哉　情報実験I

import java.util.*;

class Board{
    //staticはclassの中では誰でも利用可能
    //しかもほかのクラスからでもアクセス可能
    //newアクセス出来るのがクラス変数
    //クラス変数を作るときは「public static」だけだけど、今回は定数だからfinalをつけている。
public static final int BOARD_MAX_WIDTH = 500;
public static final int BOARD_MAX_HEIGHT = 400;

         ArrayList<Rectangle> array = new ArrayList<Rectangle>();

          private int width = BOARD_MAX_WIDTH;
          private int height = BOARD_MAX_HEIGHT;

}
