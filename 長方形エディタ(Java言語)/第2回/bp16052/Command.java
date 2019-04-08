import java.io.*;

class Command{

    Rectangle create() {
        System.out.println("create:呼び出されました。");
	BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

	int x=0;
	int y=0;
	int height=0;
	int width=0;

	try{
	    System.out.println("x座標を入力してください。");
	    x = new Integer(in.readLine());
            //System.out.println("入力値は「" + x + "」です。");

	    System.out.println("y座標を入力してください。");
	    y = new Integer(in.readLine());
        
	    System.out.println("高さを入力してください。");
	    height = new Integer(in.readLine());
        
	    System.out.println("幅を入力してください。");
	    width = new Integer(in.readLine());
        }

	catch(IOException e) {
	    System.out.println("IO Error");
	    System.exit(1);
	}

	Rectangle r=new Rectangle(x,y,height,width);
	System.out.println("長方形の作成が完了しました。");
	return r;
    }

    void delete(int n,Board b){
	b.recs.remove(n-1);
	System.out.println(n+"番目の長方形を削除しました。");
    }
}