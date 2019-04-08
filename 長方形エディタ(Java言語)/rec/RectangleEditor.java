package rec;

import java.io.*;

public class RectangleEditor {
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

    public static void main(String args[]){

        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        Command command = new Command();
        while(true){
            try {
                System.out.println();
                System.out.println("◆操作一覧◆");
                System.out.println("1, create");
                System.out.println("2, move");
                System.out.println("3, expand/shrink");
                System.out.println("4, delete");
                System.out.println("5, deleteAll");
                System.out.println("6, intersect(未実装)");
                System.out.println("7, displayBoard");
                System.out.println("8, exit");
                System.out.println();
                System.out.print("操作を入力してください。=>");
                String input = reader.readLine();
                int n = Integer.parseInt(input);
                if(n >= INPUT_MIN_LENGTH && n <= INPUT_MAX_LENGTH){
                    switch(n){
                        case CREATE:
                        while(true){
                            if(command.create()){
                                break;
                            }
                        }
                        break;

                        case MOVE:
                        while(true){
                            if(command.move()){
                                break;
                            }
                        }
                        break;

                        case EXPAND_SHRINK:
                        while(true){
                            if(command.expand_shrink()){
                                break;
                            }
                        }
                        break;

                        case DELETE:
                        while(true){
                            if(command.delete()){
                                break;
                            }
                        }
                        break;

                        case DELETE_ALL:
                        while(true){
                            if(command.deleteAll()){
                                break;
                            }
                        }
                        break;

                        case INTERSECT:
                            System.out.println("intersectは未実装です。");
                            break;
                        case DISPLAY_BOARD:
                            command.displayBoard();
                            break;

                        case EXIT:
                            command.exit();
                            break;
                }
            }else{
            System.out.println("無効な操作です、1~8で操作を選択してください。");
        }
    } catch (IOException e){
        System.out.println("入力にエラーが発生しました。");
        System.out.println();
    } catch (NumberFormatException e) {
        System.out.println("整数以外の値が入力されました。");
        System.out.println();
    }
}
}
}
