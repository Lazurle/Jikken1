// BP14003 秋山和哉　情報実験I

import java.util.*;

class Command{
    Scanner stdIn = new Scanner(System.in);
    Board board = new Board();

    public void create(){
        //作成したボード（インスタンス）の中のarrayを参照しているのでこれは小文字。
        if(board.array.size() >= 10){
            System.out.println("作成出来る四角形の上限を超えています。");
        }else{
            //値を取得する（入力する）
            System.out.print("横縦 : ");
            int width = stdIn.nextInt();
            System.out.print("縦幅 : ");
            int height = stdIn.nextInt();
            System.out.print("x座標 : ");
            int x = stdIn.nextInt();
            System.out.print("y座標 : ");
            int y = stdIn.nextInt();

            Iterator iterator = board.array.iterator();

            //hasNextはあるのかどうかを確認するためのもの（あればtrueを返す）
            while(iterator.hasNext()){
                Rectangle rectangle = (Rectangle)iterator.next();
                if(rectangle.getWidth() == width && rectangle.getHeight() == height && rectangle.getX() == x && rectangle.getY() == y){
                    System.out.println("既に同じ四角形が作成されています。");
                    return;//returnをするとここでcreateが終わる。
                }
            }

            //警告文
            // 作成したインスタンスではない、元々のクラスの定数だから大文字
            if(x+width > Board.BOARD_MAX_WIDTH || y+height > Board.BOARD_MAX_HEIGHT){
                System.out.println("作成される四角形がボードの幅を超えています。");
            }else if(width == 1 && height == 1){
                System.out.println("点です");
            }else if(width == 1 || height == 1){
                System.out.println("線です");
            }else{
                Rectangle rectangle = new Rectangle(width,height, x, y);
                board.array.add(rectangle);
                System.out.println("四角形を作成しました。");
                System.out.println("");
            }
        }
    }

    public void delete(){
        System.out.print("削除したい四角形は何番目ですか？");
        int n = stdIn.nextInt();
        board.array.remove(n-1);
    }

    public void displayBoard(){
        Iterator iterator = board.array.iterator();

        //hasNextはあるのかどうかを確認するためのもの（あればtrueを返す）
        while(iterator.hasNext()){
            //rectangleの中に次の値をぶちこんでいくのがnext
            //iteratorを使うときに使うやつ↓
            Rectangle rectangle = (Rectangle)iterator.next();
            System.out.print("横幅:" + rectangle.getWidth());
            System.out.print("縦幅:" + rectangle.getHeight());
            System.out.print("x座標:" + rectangle.getX());
            System.out.print("y座標:" + rectangle.getY());

            System.out.println();//改行
        }
    }

    public void exit(){
        System.out.println("終了します。");
        System.exit(0);//終了する決まり文句
    }


}
