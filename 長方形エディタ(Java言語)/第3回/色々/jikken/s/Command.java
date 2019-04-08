import java.io.*;
import java.lang.Math;

class Command{
    BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

    Rectangle create(Board b) {
        System.out.println("create:呼び出されました。");	

	int x=0;
	int y=0;
	int height=0;
	int width=0;
	String color="";

	try{
	    System.out.println("x座標を入力してください。");
	    x = new Integer(in.readLine());
		   
	    System.out.println("y座標を入力してください。");
	    y = new Integer(in.readLine());
	    
	    while(height<=0){
		System.out.println("高さを入力してください。");
		height = new Integer(in.readLine());
		if(height<=0)System.out.println("1以上の数を入力してください");
	    }
	    while(width<=0){
		System.out.println("幅を入力してください。");
		width = new Integer(in.readLine());
		if(width<=0)System.out.println("1以上の数を入力してください");
	    }
	    //色の選択
	    while(true){
		System.out.println("色を入力してください。(red/blue/yellow/gray)");
		color = new String(in.readLine());
		if(color.equals("red")||color.equals("blue")||
		   color.equals("yellow")||color.equals("gray"))break;

		else System.out.println("red/blue/yellow/grayのどれかを入力してください");
	    }
        }

	catch(IOException e) {
	    System.out.println("IO Error");
	    System.exit(1);
	}

	Rectangle r=new Rectangle(x,y,height,width,color);
	if(checkRect(b,r)){
	    System.out.println("長方形の作成が完了しました。");
	    return r;
	}
	else {
	    System.out.println("ボードから長方形がはみ出しました。入力をやり直してください。");
	    return create(b);
	}
    }

    void delete(Board b){
	int n=choiceRect(b);
	if(n!=0){
	    b.recs.remove(n-1);
	    System.out.println(b.recs.size()+"番目の長方形を削除しました。");
	}
    }


    void move(Board b){
	int n=choiceRect(b);
	int x=0;
	int y=0;

	if(n!=0){
	    try{
		System.out.println("x方向に動かす距離を入力してください");
		x = new Integer(in.readLine());
		System.out.println("y方向に動かす距離を入力してください");
		y = new Integer(in.readLine());
	    }
	    catch(IOException e) {
		System.out.println("IO Error");
		System.exit(1);
	    }

	    Rectangle r=b.recs.get(n-1);
	    r.x=r.x+x;
	    r.y=r.y+y;
	    if(checkRect(b,r))b.recs.set(n-1,r);
	    else {
		System.out.println("ボードから長方形がはみ出しました。入力をやり直してください。");
		move(b);
	    }
	}
    }

    void expand_shrink(Board b){
	int n=choiceRect(b);
	float mx=0;
	float my=0;

	if(n!=0){
	    try{
		System.out.println("幅の倍率を入力してください");
		mx = new Float(in.readLine());
		System.out.println("高さの倍率を入力してください");
		my = new Float(in.readLine());
	    }
	    catch(IOException e) {
		System.out.println("IO Error");
		System.exit(1);
	    }

	    Rectangle r=b.recs.get(n-1);
	    r.width= Math.round(r.width*mx);
	    r.height= Math.round(r.height*my);

	    if(checkRect(b,r))b.recs.set(n-1,r);
	    else {
		System.out.println("ボードから長方形がはみ出しました。入力をやり直してください。");
		expand_shrink(b);
	    }
	}
    }

    int choiceRect(Board b){
	if(b.recs.isEmpty()){
	    System.out.println("要素がありません。");
	    return 0;
	}
	else {
	    int n=0;
	    System.out.println("何番目の長方形を指定しますか？(1~"+b.recs.size()+"番目まで)");
	    while(true){
		try{
		    n=new Integer(in.readLine());
		}
		catch(IOException e) {
		    System.out.println("IO Error");
		    System.exit(1);
		}

		if(n>b.recs.size()||n==0){
		    System.out.println("存在しません。1~"+b.recs.size()+"番目を指定してください");
		}
		else return n;
	    }
	    
	}
    }

    boolean checkRect(Board b,Rectangle r){
	if(r.x+r.width>b.width || r.y+r.height>b.height)return false;
	else if(r.width<=0 || r.height<=0)return false;
	else if(r.x<=0 || r.y<=0)return false;
	else return true;
    }

}//end class