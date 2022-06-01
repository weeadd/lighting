package leidian.obj;

import leidian.GameWin;
import java.awt.*;

public class PlayerBullet extends Bullet{

    public PlayerBullet()
    {super();}

    public PlayerBullet(Image img, int x, int y, int HEIGHT, int WIDTH, int speed, GameWin frame) {
        super(img, x, y, HEIGHT, WIDTH, speed, frame);
    }
}
