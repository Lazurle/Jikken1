// BP14003 秋山和哉　情報実験I
import java.lang.Math;

class Rectangle{

    private int width;
    private int height;
    private int x;
    private int y;
    private String color;

    //コンストラクタは初期値を代入するためのもの。-
    Rectangle(int width,int height,int x,int y,String color){
        this.width = width;
        this.height = height;
        this.x = x;
        this.y = y;
        this.color = color;
    }
    //getXは値を取り出すための
    public int getX(){
        return this.x;
    }
    //setXは値を代入するためのもの

    public void setX(int x){
        this.x = x;
    }

    public int getY(){
        return this.y;
    }
   public void setY(int y){
        this.y = y;

    }

    public int getHeight(){
        return this.height;
    }
  public void setHeight(int height){
        this.height = height;
    }

    public int getWidth(){
        return this.width;
    }

 public void setWidth(int width){
        this.width = width;
    }

    public String getColor(){
        return this.color;
    }

/* public String setColor(String color){
        this.color = color;
    }*/

}
