package leidian.obj;

import leidian.GameWin;

import java.awt.*;

public abstract class Bullet extends Role{


    public Bullet(Image img, int x, int y, int HEIGHT, int WIDTH, int speed, GameWin frame) {
        super(img, x, y, HEIGHT, WIDTH, speed, frame);
    }

    public Bullet()
    {super();}

    @Override
    public void paintSelf(Graphics gImage) {
        super.paintSelf(gImage);
    }


}
