// BP14003 秋山和哉　情報実験I

import java.util.Scanner;

class RectangleEditor{
    public static void main(String[] args){

        Scanner stdIn = new Scanner(System.in);
        Command command = new Command();

        while(true){
            System.out.println("操作したいコマンドを入力してください。");
            System.out.println("1,create");

            System.out.println("4,delete");

            System.out.println("7,displayBoard");
            System.out.println("8,exit");

            int n = stdIn.nextInt();

            if (n == 1) {
                command.create();
            }
            else if (n == 4) {
                command.delete();
            }
            else if (n == 7) {
                command.displayBoard();
            }
            else if (n == 8) {
                command.exit();
            }

        }
    }
}
