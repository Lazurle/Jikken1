// BP14003 秋山和哉　情報実験I

import java.util.*;
import java.lang.Math;

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
            System.out.print("色をred,blue,yellow,grayから指定してください : ");
            String color;
            do{
                System.out.println("色をred,blue,yellow,grayから選んで入力してください：");
                color = stdIn.next();
                if( ( color.equals("red") || color.equals("red") || color.equals("red") || color.equals("red") ) !=true){
                    System.out.println("無効なカラーです");
                }
            }while( ( color.equals("red") || color.equals("red") || color.equals("red") || color.equals("red") ) !=true);

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
                Rectangle rectangle = new Rectangle(width,height, x, y,color);
                board.array.add(rectangle);
                System.out.println("四角形を作成しました。");
                System.out.println("");
            }
        }
    }

    //第二回
    public void move(){
        if(board.array.size() == 0){
            System.out.println("");
            System.out.println("作成された四角形が一つもありません");
            System.out.println("");
            return;
        }

        Rectangle rec;
        int n,x_d,y_d;

        do{
            System.out.print("動かしたい四角形は何番目ですか？");
            n = stdIn.nextInt();

            if(board.array.size() < n){
                System.out.println("無効な入力です。");
            }
        }while(board.array.size() < n);

        rec = board.array.get(n-1);

        do{
            System.out.print("x座標をどれぐらい移動させますか？(整数)");
            x_d = stdIn.nextInt();
            System.out.print("y座標をどれぐらい移動させますか？(整数)");
            y_d = stdIn.nextInt();

            if(rec.getX() + x_d > board.BOARD_MAX_WIDTH || rec.getY() + y_d > board.BOARD_MAX_HEIGHT){
                System.out.println("無効な入力です。");
            }
        }while(rec.getX() + x_d > board.BOARD_MAX_WIDTH || rec.getY() + y_d > board.BOARD_MAX_HEIGHT);

        rec = board.array.get(n-1);
        //x_d = x_d + rec.getX();
        rec.setX(x_d + rec.getX());
        //    y_d = y_d + rec.getY();
        rec.setX(y_d + rec.getY());
        board.array.set(n-1, rec);

        System.out.println(n + "番目の四角形の横幅を" + x_d + " 、縦幅を" + y_d + " 拡大・縮小させました。");

    }

    public void expand_shrink(){
        if(board.array.size() == 0){
            System.out.println("");
            System.out.println("作成された四角形が一つもありません");
            System.out.println("");
            return;
        }

        Rectangle rec;
        int n;
        float x_d,y_d;

        do{
            System.out.print("拡張・縮小したい四角形は何番目ですか？");
            n = stdIn.nextInt();

            if(board.array.size() < n){
                System.out.println("無効な入力です。");
            }
        }while(board.array.size() < n);

        rec = board.array.get(n-1);

        do{
            System.out.print("x座標をどれぐらい拡張・縮小させますか？");
            x_d = stdIn.nextFloat();
            System.out.print("y座標をどれぐらい拡張・縮小させますか？");
            y_d = stdIn.nextFloat();

            if(rec.getWidth()*x_d > board.BOARD_MAX_WIDTH || rec.getHeight()*y_d > board.BOARD_MAX_HEIGHT){
                System.out.println("無効な入力です。");
            }
        }while(rec.getWidth()*x_d > board.BOARD_MAX_WIDTH || rec.getHeight()*y_d > board.BOARD_MAX_HEIGHT);

        rec = board.array.get(n-1);
        //x_d = x_d + rec.getX();
        rec.setWidth(Math.round(x_d*rec.getWidth()) );
        //    y_d = y_d + rec.getY();
        rec.setHeight(Math.round(y_d*rec.getHeight()) );
        board.array.set(n-1, rec);

        System.out.println(n + "番目の四角形のx座標を" + x_d + " 、y座標を" + y_d + " 移動させました。");

    }

    public void delete(){
        if(board.array.size() == 0){
            System.out.println("");
            System.out.println("作成された四角形が一つもありません");
            System.out.println("");
            return;
        }

        System.out.print("削除したい四角形は何番目ですか？");
        int n;
        do{
            n = stdIn.nextInt();
            if(board.array.size() < n){
                System.out.println("無効な入力です");
            }
        }while(board.array.size() < n);

        board.array.remove(n-1);
    }

    public void deleteAll(){
        if(board.array.size() == 0){
            System.out.println("");
            System.out.println("作成された四角形が一つもありません");
            System.out.println("");
            return;
        }

        board.array.clear();
        System.out.println("全ての長方形を削除しました。");
    }
    /*
public void intersect(){
    if(board.array.size() == 0){
        System.out.println("");
        System.out.println("作成された四角形が一つもありません");
        System.out.println("");
        return;
    }

System.out.println("");
System.out.println("２つの長方形R1,R2を指定して、重なり部分を抽出し、新たな長方形R3を配置します。");

Rectangle R1;
Rectangle R2;
Rectangle R3;
int n;

do{
System.out.println("R1とするのは何番目の四角形ですか？")
n = stdIn.nextInt();
if(board.array.size() < n){
    System.out.println("無効な入力です。");
}
}while(board.array.size() < n));
R1 = board.array.get(n-1);

do{
System.out.println("R2とするのは何番目の四角形ですか？")
n = stdIn.nextInt();
if(board.array.size() < n){
    System.out.println("無効な入力です。");
}
}while(board.array.size() < n));
R2 = board.array.get(n-1);



Rectangle rectangle = new Rectangle(width,height, x, y,color);
board.array.add(rectangle);
System.out.println("新しい四角形をR3として作成しました。");
System.out.println("");




}
*/


    public void displayBoard(){
        if(board.array.size() == 0){
            System.out.println("");
            System.out.println("作成された四角形が一つもありません");
            System.out.println("");
            return;
        }

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
            System.out.print("色:" + rectangle.getColor());

            System.out.println();//改行
        }
    }

    public void exit(){
        System.out.println("終了します。");
        System.exit(0);//終了する決まり文句
    }


}
