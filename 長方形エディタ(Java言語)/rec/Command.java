package rec;

import java.util.*;
import java.io.*;
import java.lang.*;

public class Command {
    BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    Board board = new Board();

    public boolean create(){
        if(board.array.size() >= board.ARRAY_MAX_SIZE){
            System.out.println("作成できる上限を超えています。createできません。");
            return true;
        }

        try {
            System.out.println("― 長方形を作成します。 ―");
            System.out.println("作成したい位置 x を入力してください。=> ");
            int x = Integer.parseInt(reader.readLine());
            System.out.println("作成したい位置 y を入力してください。=> ");
            int y = Integer.parseInt(reader.readLine());
            System.out.println("横の長さ width を入力してください。=> ");
            int width = Integer.parseInt(reader.readLine());
            System.out.println("縦の長さ height を入力してください。=> ");
            int height = Integer.parseInt(reader.readLine());
            System.out.println("指定する色 color を入力してください。(red,blue,yellow,gray可)=> ");
            String color = reader.readLine();

            if(isOverRectangle(x, y, width, height)){
                System.out.println("作成される長方形がボードの長さを超えています。");
                return false;
            }else if(isPoint(width, height)){
                System.out.println("作成される長方形が 点 です。");
                return false;
            }else if(isLine(width, height)){
                System.out.println("作成される長方形が 線 です。");
                return false;
            }else if(hasSameRectangle(x, y, width, height)){
                System.out.println("重複する長方形が存在します。");
                return false;
            }else if(isWrongXorY(x, y)){
                System.out.println("位置が負の値、または0で不正です。");
                return false;
            }else if(hasNoColor(color)){
                System.out.println("その色の四角形は作成できません。");
                return false;
            }
            Rectangle rec = new Rectangle(x,y,width,height,color);
            board.array.add(rec);
            System.out.println("x座標 : " + rec.getX() + ",y座標 : " + rec.getY() + "の位置に、");
            System.out.println("横の長さ" + rec.getWidth() + ",縦の長さ" + rec.getHeight() + "の");
            System.out.println("色が" + rec.getColor() + "の長方形が１つ作成されました。");

            return true;
        } catch (NumberFormatException e){
            System.out.println("整数以外の値が入力されました。");
            return false;
        } catch (IOException e){
            System.out.println("入力にエラーが発生しました。");
            return false;
        }
    }

    public boolean move(){
        System.out.println("何番目の四角形を移動しますか？ =>");
        try {
            int n = Integer.parseInt(reader.readLine());
            System.out.println("移動する値(x座標)=> ");
            int x = Integer.parseInt(reader.readLine());
            System.out.println("移動する値(y座標)=> ");
            int y = Integer.parseInt(reader.readLine());
            int dx = board.array.get(n - 1).getX() + x;
            int dy = board.array.get(n - 1).getY() + y;
            int width = board.array.get(n - 1).getWidth();
            int height = board.array.get(n - 1).getHeight();
            if(isOverRectangle(dx, dy, width, height)){
                System.out.println("作成される長方形がボードの長さを超えています。");
                return false;
            }else if(hasSameRectangle(dx, dy, width, height)){
                System.out.println("重複する長方形が存在します。");
                return false;
            }else if(isWrongXorY(dx, dy)){
                System.out.println("位置が負の値、または0で不正です。");
                return false;
            }
            board.array.get(n - 1).setX(dx);
            board.array.get(n - 1).setY(dy);

            System.out.println(n + "番目の四角形のx座標を" + x + ", y座標を" + y +"移動しました。");
            return true;
        } catch (IOException e){
            System.out.println("無効な入力です。");
            return false;
        } catch (NumberFormatException e){
            System.out.println("解析可能な整数を含みません、無効な入力です。");
            return false;
        } catch (IndexOutOfBoundsException e){
            System.out.println("その番号の四角形は存在しません。");
            return false;
        }

    }

    public boolean expand_shrink(){
        System.out.print("何番目の四角形を拡大/縮小しますか？ =>");
        try {
            int n = Integer.parseInt(reader.readLine());
            System.out.println("横幅 の拡大/縮小する値を（小数点可）で入力してください。=> ");
            float mx = Float.parseFloat(reader.readLine());
            System.out.println("縦幅 の拡大/縮小する値を（小数点可）で入力してください。=> ");
            float my = Float.parseFloat(reader.readLine());
            int x = board.array.get(n - 1).getX();
            int y = board.array.get(n - 1).getY();
            float a_width = board.array.get(n - 1).getWidth()*mx;
            float a_height = board.array.get(n - 1).getHeight()*my;
            int width = Math.round(a_width);
            int height = Math.round(a_height);
            if(isOverRectangle(x, y, width, height)){
                System.out.println("作成される長方形がボードの長さを超えています。");
                return false;
            }else if(isPoint(width, height)){
                System.out.println("拡大/縮小される長方形が 点 です。");
                return false;
            }else if(isLine(width, height)){
                System.out.println("拡大/縮小される長方形が 線 です。");
                return false;
            }else if(hasSameRectangle(x, y, width, height)){
                System.out.println("重複する長方形が存在します。");
                return false;
            }
            System.out.println(n + "番目の四角形の横幅が" + width + "になり縦幅が" + height + "になりました。");
            System.out.println();
            return true;
        } catch (IOException e){
            System.out.println("不正な入力です。");
            return false;
        } catch (NumberFormatException e){
            System.out.println("不正な値が入力されました。");
            e.printStackTrace();
            return false;
        }
    }

        public boolean delete(){
            try {
                System.out.print("何番目の四角形を削除しますか？=>");
                int n = Integer.parseInt(reader.readLine());
                board.array.remove(n - 1);
                System.out.println(n + "番目の四角形を削除しました。");
                return true;
            } catch (NumberFormatException e){
                System.out.println("整数以外の値が入力されました。");
                return false;
            } catch (IOException e){
                System.out.println("不正な入力です。");
                return false;
            } catch (IndexOutOfBoundsException e){
                System.out.println("その番号の四角形は存在しません。");
                return false;
            }
        }

        public boolean deleteAll(){
            if(board.array.isEmpty()){
                System.out.println("ボードに四角形は存在しません。");
                return true;
            }
            board.array.clear();
            System.out.println("すべての長方形を削除しました。");
            return true;
        }

        public boolean displayBoard(){
            Iterator iterator = board.array.iterator();

            try{
                for(int i = 1;iterator.hasNext();i++){
                    Rectangle rectangle = (Rectangle)iterator.next();
                    System.out.println(i + "番目の四角形");
                    System.out.println("横幅 : " + rectangle.getWidth());
                    System.out.println("縦幅 : " + rectangle.getHeight());
                    System.out.println("位置x: " + rectangle.getX());
                    System.out.println("位置y: " + rectangle.getY());
                    System.out.println("色 　: " + rectangle.getColor());
                    System.out.println();
                }
                return true;
            } catch (NullPointerException e){
                System.out.println("表示する四角形がありません。");
                return false;
            }
        }
        public void exit(){
            System.out.println("プログラムを終了します。");
            System.exit(0);
        }

        public boolean isOverRectangle(int x, int y, int width, int height){
            if(x + width >= board.BOARD_MAX_WIDTH || y + height >= board.BOARD_MAX_HEIGHT){
                return true;
            }
            return false;
        }

        public boolean hasWrongWidthOrHeight(int width, int height){
            if(width <= 1 || height <= 1){
                return true;
            }
            return false;
        }

        public boolean isWrongXorY(int x, int y){
            if(x < 1 || y < 1){
                return true;
            }
            return false;
        }

        public boolean isPoint(int width, int height){
            if(width == 1 && height == 1){
                return true;
            }
            return false;
        }

        public boolean isLine(int width, int height){
            if(width == 1 || height == 1){
                return true;
            }
            return false;
        }

        public boolean hasSameRectangle(int x, int y, int width, int height){
            Iterator iterator = board.array.iterator();
            while(iterator.hasNext()){
                Rectangle rectangle;
                rectangle = (Rectangle)iterator.next();
                if(rectangle.getX() == x && rectangle.getY() == y && rectangle.getWidth() == width && rectangle.getHeight() == height){
                    return true;
                }

            }
            return false;
        }

        public boolean hasRectangle(int n){
            if(board.array.size() < n || n < board.ARRAY_MIN_SIZE){
                return true;
            }
            return false;
        }

        public boolean hasNoColor(String color){
            String red = "red";
            String blue = "blue";
            String yellow = "yellow";
            String gray = "gray";
            if(color.equals(red) || color.equals(blue) || color.equals(yellow) || color.equals(gray)){
                return false;
            }
            return true;
        }

}
