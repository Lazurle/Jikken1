// BP14003 秋山和哉　情報実験I

class Rectangle{

    private int width;
    private int height;
    private int x;
    private int y;

    //コンストラクタは初期値を代入するためのもの。
    Rectangle(int width,int height,int x,int y){
        this.width = width;
        this.height = height;
        this.x = x;
        this.y = y;
    }
    //getXは値を取り出すための
    public int getX(){
        return this.x;
    }
    //setXは値を代入するためのもの
/*
    public int setX(int x){
        this.x = x;
    }*/
    public int getY(){
        return this.y;
    }
/*    public int setY(int y){
        this.y = y;
    }*/

    public int getHeight(){
        return this.height;
    }
/*    public int setHeight(int height){
        this.height = height;
    }*/

    public int getWidth(){
        return this.width;
    }

/*    public int setWidth(int width){
        this.width = width;
    }*/
}
