import java.io.*;

//シーケンス...
//ステートマシン...
//クラス図...

class RectangleEditor {
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

    public static void main(String[] args) {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        //例外の時、バファードリーダーのが便利
//SYstem.inとはターミナルから入力することを標準入力
//俺たちが入力したことをインプットストリームリーダーが読み込んでる
        Command command = new Command();

        while (true) {
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

                String input = reader.readLine();
                int n = Integer.parseInt(input);//parseIntの引数にいれた値を読み込んで
                //整数の値に変換してるだけ

                switch (n) {
                    case CREATE :
                        while (!command.create()) {//command.createがfaleseを出したら繰り返す
                            command.create();
                        }
                        break;
                    case MOVE :
                        while (!command.move()) {
                            command.move();
                        }
                        break;
                    case EXPAND_SHRINK :
                        while (!command.expand_shrink()) {
                            command.expand_shrink();
                        }
                        break;
                    case DELETE :
                        while (!command.delete()) {
                            command.delete();
                        }
                        break;
                    case DELETE_ALL :
                        while (!command.deleteAll()) {
                            command.deleteAll();
                        }
                        break;
                    case INTERSECT :
                        while (!command.intersect()) {
                            command.intersect();
                        }
                        break;
                    case DISPLAY_BOARD :
                        command.displayBoard();
                        break;
                    case EXIT :
                        command.exit();
                        break;
                    default :
                        System.out.println("1-8の数字を入力して下さい。");
                        break;
                }
            } catch (IOException e) {//バファードラインのリードラインメソットが出すエラー
                System.out.println("入力にエラーが発生しました：" + e);
            } catch (NumberFormatException e) {//ParseIntが出すエラー
                System.out.println("整数以外の値が入力されました：" + e);
            }
        }
    }
}
