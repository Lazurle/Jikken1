import java.io.*;
import java.lang.Integer;
class RectangleEditor{
    
    public static void main(String args[]){
	Command a=new Command();
	Board b=new Board();        
        while(true){
            System.out.println("操作を選択してください。(左の数字を入力)");
            System.out.println("1. 長方形の作成　　　　(create)");
            System.out.println("2. 長方形の移動　　　　(move)");
            System.out.println("3. 長方形の拡大・縮小　(expand/shrink)");
            System.out.println("4. 長方形の削除　　　　(delete)");
            System.out.println("5. すべて削除　　　　  (deleteAll)");
            System.out.println("6. 作成した長方形の表示(displayBoard)");
            System.out.println("7. 操作の終了　　　　　(exit)");
	    System.out.print("→ ");
	    BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
	    //命令commandの入力
	    String c="";
	    int command=0;

	    try{
		c=new String(in.readLine());
	    }
	    catch(IOException e) {
		System.out.println("IO Error");
		System.exit(1);
	    }

	    if(isNumber(c))command=Integer.parseInt(c);

	    //createの呼び出し
	    if(command==1){ 
		if(b.recs.size()==10)System.out.println("これ以上作成できません");
		else b.setRectangle(a.create(b));
	    }

	    //move
	    else if(command==2){
		a.move(b);
	    }

	    //expand
	    else if(command==3){
		a.expand_shrink(b);
	    }

	    //deleteの呼び出し
	    else if(command==4){
		a.delete(b);
	    }


	
	    //deleteAll
	    else if(command==5){
		b.recs.clear();
	    }	    

	    //displayBoardの呼び出し
	    else if(command==6){
		if(b.recs.isEmpty())System.out.println("要素がありません。");
		else b.showRectangle();
	    }

	    //exitで終了
	    else if(command==7)break;

	    //エラー：非対応な文字列の入力
	    else System.out.println("対応していない文字列が入力されました");
	}    
    }

    static boolean isNumber(String num) {
	try {
	    Integer.parseInt(num);
	    return true;
        } catch (NumberFormatException e) {
        return false;
	}
    }
  
}
