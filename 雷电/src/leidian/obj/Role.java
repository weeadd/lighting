package leidian.obj;

import java.awt.*;


import leidian.GameWin;

public class Role {
    Image img;
   public int x;
   public int y;
   public int HEIGHT;
   public int WIDTH;
   public int speed;
    GameWin frame;


    //getter & setter
    public Image getImg() {
        return img;
    }

    public void setImg(Image img) {
        this.img = img;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getHEIGHT() {
        return HEIGHT;
    }

    public void setHEIGHT(int HEIGHT) {
        this.HEIGHT = HEIGHT;
    }

    public int getWIDTH() {
        return WIDTH;
    }

    public void setWIDTH(int WIDTH) {
        this.WIDTH = WIDTH;
    }

    public double getSpeed() {
        return speed;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }

    public GameWin getFrame() {return frame;}

    public void setFrame(GameWin frame) {this.frame = frame;}

    public Role(Image img, int x, int y, int HEIGHT, int WIDTH, int speed,GameWin frame) {
        this.img = img;
        this.x = x;
        this.y = y;
        this.WIDTH = WIDTH;
        this.HEIGHT = HEIGHT;
        this.speed = speed;
        this.frame = frame;
    }//有全部参数的构造方法

    public Role(Image img, int x, int y, int speed) {
        this.img = img;
        this.x = x;
        this.y = y;
        this.speed = speed;
    }//方法构造的背景

    public Role() {

    }

    public void paintSelf(Graphics gImage){
        gImage.drawImage(img,x,y,null);
    }//绘制自身图像

}
