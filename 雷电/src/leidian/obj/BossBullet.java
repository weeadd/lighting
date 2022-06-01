package leidian.obj;

import leidian.GameWin;

import java.awt.*;

public class BossBullet extends Bullet{

    boolean paint = true;

    boolean hasOccoured;

    double speedx;

    double speedy;

    public void setSpeedx(double speedx) {
        this.speedx = speedx;
    }

    public void setSpeedy(double speedy) {
        this.speedy = speedy;
    }

    public void setPaint(boolean paint) {
        this.paint = paint;
    }

    public void setHasOccoured(boolean hasOccoured) {
        this.hasOccoured = hasOccoured;
    }

    public BossBullet(Image img, int x, int y, int HEIGHT, int WIDTH, int speed, GameWin frame) {
        super(img, x, y, HEIGHT, WIDTH, speed, frame);
    }

    public BossBullet() {
    }

    public void paintSelf(Graphics gImage){
        if (paint)
            super.paintSelf(gImage);
    }
}
