//BP14003 秋山和哉　第2回

import java.io.*;

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
        Command command = new Command();

        while (true) {//javaならwhileは１ではなくtrue
            try {
                System.out.println("操作を入力してください");
                System.out.println("1.create");
                System.out.println("2.move");
                System.out.println("3.expand/shrink");
                System.out.println("4.delete");
                System.out.println("5.deleteAll");
                //System.out.println("6.intersect");
                System.out.println("7.displayBoard");
                System.out.println("8.exit");

                String input = reader.readLine();
                int n = Integer.parseInt(input);

                switch (n) {
                    case CREATE :
                        while (true) {
                            if (command.create()) {//return trueすると、command.createの部分がtrueになる。
                                break;//whileから出るbreak
                            }
                        }
                        break;//switch文から出るbreak
                    case MOVE :
                        while (true) {
                            if (command.move()) {
                                break;
                            }
                        }
                        break;
                    case EXPAND_SHRINK :
                        while (true) {
                            if (command.expand_shrink()) {
                                break;
                            }
                        }
                        break;
                    case DELETE :
                        while (true) {
                            if (command.delete()) {
                                break;
                            }
                        }
                        break;
                    case DELETE_ALL :
                        while (true) {
                            if (command.deleteAll()) {
                                break;
                            }
                        }
                        break;
                        /*
                    case INTERSECT :
                        while (true) {
                            if (command.intersect()) {
                                break;
                            }
                        }
                        break;
                        */
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
            } catch (IOException e) {
                System.out.println("入力にエラーが発生しました：" + e);
            } catch (NumberFormatException e) {
                System.out.println("整数以外の値が入力されました：" + e);
            }
        }
    }
}
