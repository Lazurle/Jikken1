import java.io.*;

class RectangleEditor{
    
    public static void main(String args[]){
	Command a=new Command();
	Board b=new Board();        
        while(true){
            System.out.println("操作を選択してください。");
            System.out.println("長方形の作成　　　　(→ 「create」を入力)");
            System.out.println("長方形の削除　　　　(→ 「delete」を入力)");
            System.out.println("作成した長方形の表示(→ 「displayBoard」を入力)");
            System.out.println("操作の終了　　　　　(→ 「exit」を入力)");
	    BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
	    //命令commandの入力
	    String command=null;
	    try{
		command=new String(in.readLine());
	    }
	    catch(IOException e) {
		System.out.println("IO Error");
		System.exit(1);
	    }

	    //createの呼び出し
	    if(command.equals("create")){ 
		b.setRectangle(a.create());
	    }

	    //deleteの呼び出し
	    else if(command.equals("delete")){
		if(b.recs.isEmpty())System.out.println("要素がありません。");
		else {
		    int n=0;
		    System.out.println("何番目の長方形を削除しますか？(1~"+b.recs.size()+"番目まで)");
		    try{
			n=new Integer(in.readLine());
		    }
		    catch(IOException e) {
			System.out.println("IO Error");
			System.exit(1);
		    }
		    if(n>b.recs.size()){
			System.out.println("存在しません。"+b.recs.size()+"番目以下を指定してください");
		    }
		    else a.delete(n,b);
		}
	    }	    

	    //displayBoardの呼び出し
	    else if(command.equals("displayBoard")){
		if(b.recs.isEmpty())System.out.println("要素がありません。");
		else b.showRectangle();
	    }

	    //exitで終了
	    else if(command.equals("exit"))break;

	    //エラー：非対応な文字列の入力
	    else System.out.println("対応していない文字列が入力されました");
	}    
    }

  
}
