package EIEV3;

import java.util.*;
import java.io.*;

class Command {
    public static final int CREATE = 1;
    public static final int MOVE = 2;
    public static final int EXPAND_SHRINK = 3;
    public static final int DELETE = 4;
    public static final int DELETE_ALL = 5;
    public static final int INTERSECT = 6;
    public static final int DISPLAY_BOARD = 7;
    public static final int EXIT = 8;
    public static final int INPUT_MIN_LENGTH = 1;
    public static final int INPUT_MAX_LENGTH = 8;

    BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    Board board = new Board();

    public void inputCommand() {
        try {
            System.out.println("操作を入力してください");
            System.out.println("1.create");
            System.out.println("2.move");
            System.out.println("3.expand/shrink");
            System.out.println("4.delete");
            System.out.println("5.deleteAll");
            System.out.println("6.intersect");
            System.out.println("7.displayBoard");
            System.out.println("8.exit");

            int n = Integer.parseInt(reader.readLine());

            switch (n) {
                case CREATE :
                    while (true) {
                        if (this.create()) {
                            break;
                        }
                    }
                    break;
                case MOVE :
                    while (true) {
                        if (this.move()) {
                            break;
                        }
                    }
                    break;
                case EXPAND_SHRINK :
                    while (true) {
                        if (this.expand_shrink()) {
                            break;
                        }
                    }
                    break;
                case DELETE :
                    while (true) {
                        if (this.delete()) {
                            break;
                        }
                    }
                    break;
                case DELETE_ALL :
                    while (true) {
                        if (this.deleteAll()) {
                            break;
                        }
                    }
                    break;
                case INTERSECT :
                    while (true) {
                        if (this.intersect()) {
                            break;
                        }
                    }
                    break;
                case DISPLAY_BOARD :
                    this.displayBoard();
                    break;
                case EXIT :
                    this.exit();
                    break;
                default :
                    System.out.println("1-8の数字を入力して下さい。");
                    break;
            }
        } catch (IOException e) {
            System.out.println("入力にエラーが発生しました：" + e);
        } catch (NumberFormatException e) {
            System.out.println("整数以外の値が入力されました：" + e);
        }
    }

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
                System.out.println("指定した色以外が選択されました。");
                return false;
            }

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
    }

    public boolean deleteAll() {
        if (board.array.isEmpty()) {
            System.out.println("四角形は存在しません。");
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
        return true;
    }

    public void displayBoard() {
        Iterator iterator = board.array.iterator();

        while (iterator.hasNext()) {
            Rectangle rectangle = (Rectangle)iterator.next();
            System.out.print("横幅:" + rectangle.getWidth() + " ");
            System.out.print("縦幅:" + rectangle.getHeight() + " ");
            System.out.print("x:" + rectangle.getX() + " ");
            System.out.print("y:" + rectangle.getY() + " ");
            System.out.print("色:" + rectangle.getColor() + " ");
            System.out.println("");
        }
    }

    public boolean exit() {
        System.out.println("プログラムを終了します。");
        return true;
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
