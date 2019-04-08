import java.util.*;
import java.io.*;

class Command {
    BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    Board board = new Board();

    public boolean create() {
        if (board.array.size() >= Board.ARRAY_MAX_SIZE) {
            System.out.println("上限を超えています。");
            return true;
        }

        try {
            System.out.print("幅：");
            int width = Integer.parseInt(reader.readLine());
            System.out.print("高さ：");
            int height = Integer.parseInt(reader.readLine());
            System.out.print("x座標：");
            int x = Integer.parseInt(reader.readLine());
            System.out.print("y座標：");
            int y = Integer.parseInt(reader.readLine());
            System.out.print("色(red,blue,yellow,gray)：");
            String color = reader.readLine();

            if (!(color.equals("red") || color.equals("blue") || color.equals("yellow") || color.equals("gray"))) {
                //もし緑のとき０がかえってくるので　、！で１にかえてif文にする。
                System.out.println("指定した色以外が選択されました。");
                return false;//失敗したときはfalse
            }//例外は復帰不可能レベルぐらい、実行不可能レベルぐらいのやつ
            //マイナスの値がはいったぐらいでは不正ではないからif文

            if (hasWrongWidthOrHeight(width, height, x, y)) {
                System.out.println("幅または高さが不正です。");
                return false;
            } else if (isOverRectangle(width, height, x, y)) {
                System.out.println("ボードの幅を超えています。");
                return false;
            } else if (isPoint(width, height, x, y)) {
                System.out.println("点です。");
                return false;
            } else if (isLine(width, height, x, y)) {
                System.out.println("線です。");
                return false;
            } else if (hasSameRectangle(width, height, x, y)) {
                System.out.println("重複する四角形が存在します。");
                return false;
            }

            // 四角形を作成
            Rectangle rectangle = new Rectangle(width, height, x, y, color);
            // 四角形をBoardに追加
            board.array.add(rectangle);
            System.out.println("四角形を追加しました。");
            System.out.println("");

            return true;
        } catch (IOException e) {
            System.out.println("入力にエラーが発生しました：" + e);
            return false;
        } catch (NumberFormatException e) {
            System.out.println("整数以外の値が入力されました：" + e);
            return false;
        }
    }

    public boolean move() {
        if (board.array.isEmpty()) {
            System.out.println("四角形は存在しません。");
            return true;
        }

        try {
            System.out.print("何番目の四角形を移動しますか?：");
            int n = Integer.parseInt(reader.readLine());

            System.out.print("x軸方向への移動距離：");
            int dx = Integer.parseInt(reader.readLine());
            System.out.print("y軸方向への移動距離：");
            int dy = Integer.parseInt(reader.readLine());

            int width = board.array.get(n - 1).getWidth();
            int height = board.array.get(n - 1).getHeight();
            int x = board.array.get(n - 1).getX() + dx;
            int y = board.array.get(n - 1).getY() + dy;

            if (isOverRectangle(width, height, x, y)) {
                System.out.println("ボードの幅を超えています。");
                return false;
            } else if (hasSameRectangle(width, height, x, y)) {
                System.out.println("重複する四角形が存在します。");
                return false;
            }

            board.array.get(n - 1).setX(x);
            board.array.get(n - 1).setY(y);

        } catch (IOException e) {
            System.out.println("入力にエラーが発生しました：" + e);
            return false;
        } catch (NumberFormatException e) {
            System.out.println("整数以外の値が入力されました。：" + e);
            return false;
        } catch (IndexOutOfBoundsException e) {
            System.out.println("その番号の四角形は存在しません。" + e);
            return false;
        }

        return true;
    }

    public boolean expand_shrink() {
        if (board.array.isEmpty()) {
            System.out.println("四角形は存在しません。");
            return true;
        }

        System.out.print("何番目の四角形を拡大または縮小しますか?：");
        try {
            int n = Integer.parseInt(reader.readLine());
            System.out.print("x軸方向の倍率：");
            float mx = Float.parseFloat(reader.readLine());
            System.out.print("y軸方向の倍率：");
            float my = Float.parseFloat(reader.readLine());

            int width = Math.round(board.array.get(n - 1).getWidth() * mx);
            int height = Math.round(board.array.get(n - 1).getHeight() * my);
            int x = board.array.get(n - 1).getX();
            int y = board.array.get(n - 1).getY();

            if (hasWrongWidthOrHeight(width, height, x, y)) {
                System.out.println("幅または高さが不正です。");
                return false;
            } else if (isOverRectangle(width, height, x, y)) {
                System.out.println("ボードの幅を超えています。");
                return false;
            } else if (isPoint(width, height, x, y)) {
                System.out.println("点です。");
                return false;
            } else if (isLine(width, height, x, y)) {
                System.out.println("線です。");
                return false;
            } else if (hasSameRectangle(width, height, x, y)) {
                System.out.println("重複する四角形が存在します。");
                return false;
            }

            board.array.get(n - 1).setWidth(width);
            board.array.get(n - 1).setHeight(height);

        } catch (IOException e) {
            System.out.println("入力にエラーが発生しました：" + e);
            return false;
        } catch (NumberFormatException e) {
            System.out.println("倍精度浮動小数点以外の値が入力されました。：" + e);
            return false;
        } catch (IndexOutOfBoundsException e) {
            System.out.println("その番号の四角形は存在しません。" + e);
            return false;
        }

        return true;
    }

    public boolean delete() {
        if (board.array.isEmpty()) {
            System.out.println("四角形は存在しません。");
            return true;
        }

        System.out.print("何番目の四角形を削除しますか?:");

        try {
            int n = Integer.parseInt(reader.readLine());
            board.array.remove(n - 1);
            System.out.println(n + "番目の四角形を削除しました。");
            return true;
        } catch (IOException e) {//readline
            System.out.println("入力にエラーが発生しました：" + e);
            return false;
        } catch (NumberFormatException e) {//parseInt
            System.out.println("整数以外の値が入力されました。：" + e);
            return false;
        } catch (IndexOutOfBoundsException e) {//arraylistの例外
            System.out.println("その番号の四角形は存在しません。" + e);
            return false;
        }
    }
    //has,isはあまり決まってない　感

    public boolean deleteAll() {
        if (board.array.isEmpty()) {
            System.out.println("四角形は存在しません。");
            return false;
        } else {
            board.array.clear();
            System.out.println("すべての長方形を削除しました。");
        }
        return true;
    }

    public boolean intersect() {
        if (board.array.isEmpty()) {
            System.out.println("四角形は存在しません。");
            return true;
        } else if (board.array.size() >= Board.ARRAY_MAX_SIZE) {
            System.out.println("上限を超えています。");
            return true;
        }
    }

    public void displayBoard() {
        Iterator iterator = board.array.iterator();
        //arrayListクラスの中のインスタンスメソッドにiterator()メソッドがある。
        //iteratorはクラスにもある、arraylistの中にはiteratorメソッドがある。
        //イテレーターメソッドはイテレータクラスのためにあるようなもの

        //arrayListに格納されているものをさいしょから最後まで出すのに使っている。

        while (iterator.hasNext()) {//hasNextはイテレータークラスの中のメソッド
            Rectangle rectangle = (Rectangle)iterator.next();
            //iterator.next()で、格納してるもの自体を取り出している。
            //(Rectangle)はキャスト、iteratorネクストで返ってくるのは全部オブジェクトクラスになってしまう。
            //これをRectangleに直してあげている。iterator.next()を使うときはキャストを使う

            System.out.print("横幅:" + rectangle.getWidth() + " ");
            System.out.print("縦幅:" + rectangle.getHeight() + " ");
            System.out.print("x:" + rectangle.getX() + " ");
            System.out.print("y:" + rectangle.getY() + " ");
            System.out.print("色:" + rectangle.getColor() + " ");
            System.out.println("");
        }
    }

    public void exit() {
        System.out.println("プログラムを終了します。");
        System.exit(0);
    }


    // 追加関数：不正な幅または高さであるかどうかを判定
    public boolean hasWrongWidthOrHeight(int width, int height, int x, int y) {
        if (width <= 0 || height <= 0) {
            return true;
        }
        return false;
    }

    // 追加関数 : 四角形がボードの範囲内かどうかを判定
    public boolean isOverRectangle(int width, int height, int x, int y) {
        if ((width + x) > Board.BOARD_MAX_WIDTH || (height + y) > Board.BOARD_MAX_HEIGHT || x < Board.BOARD_MIN_WIDTH || y < Board.BOARD_MIN_HEIGHT) {
            return true;
        } else {
            return false;
        }
    }

    // 追加関数 : 四角形が線ではないことを判定
    public boolean isLine(int width, int height, int x, int y) {
        if (width == 1 || height == 1) {
            return true;
        } else {
            return false;
        }
    }

    // 追加関数 : 四角形が点ではないことを判定
    public boolean isPoint(int width, int height, int x, int y) {
        if (width == 1 && height == 1) {
            return true;
        } else {
            return false;
        }
    }

    // 追加関数：重複する長方形が存在するかどうか
    public boolean hasSameRectangle(int width, int height, int x, int y) {
        Iterator iterator = board.array.iterator();

        while (iterator.hasNext()) {
            Rectangle rectangle = (Rectangle)iterator.next();
            if (rectangle.getWidth() == width && rectangle.getHeight() == height && rectangle.getX() == x && rectangle.getY() == y) {
                return true;
            }
        }
        return false;
    }
}
