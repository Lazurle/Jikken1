// BP14003 秋山和哉　情報実験I

import java.util.Scanner;

class RectangleEditor{
    public static void main(String[] args){

        Scanner stdIn = new Scanner(System.in);
        Command command = new Command();

        while(true){
            System.out.println("操作したいコマンドを入力してください。");
            System.out.println("1,create");
            System.out.println("2,move");
            System.out.println("3,expand/shrink");
            System.out.println("4,delete");
            System.out.println("5,deleteAll");

            System.out.println("6,intersect");

            System.out.println("7,displayBoard");
            System.out.println("8,exit");

            int n = stdIn.nextInt();

            if (n == 1) {
                command.create();
            }
            else if(n == 2){
                command.move();
            }

            else if(n == 3){
                command.expand_shrink();
            }
            else if (n == 4){
                command.delete();
            }
            else if(n == 5){
                command.deleteAll();
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
