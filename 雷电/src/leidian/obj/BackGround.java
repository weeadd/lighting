package leidian.obj;

import java.awt.*;

public class BackGround extends Role {

    public BackGround() { super();}
    public BackGround(Image img, int x, int y, int speed) {
        super(img, x, y, speed);
    }

    @Override
    public void paintSelf(Graphics gImage){
        super.paintSelf(gImage);
        y+=speed;//向下移动背景
        if(y>=0)
        {
            y = -900;
        }//背景图片的循环
    }
}
