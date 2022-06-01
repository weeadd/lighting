package leidian.obj;

import leidian.GameWin;
import leidian.utils.GameUtils;

import java.awt.*;
import java.util.Random;

public class Enemy extends Role{

    Random random = new Random();
    int addSpeed = random.nextInt(5);


    public Enemy(Image img, int x, int y, int HEIGHT, int WIDTH, int speed, GameWin frame) {
        super(img, x, y, HEIGHT, WIDTH, speed, frame);
    }

    public Enemy(){
        super();
    }


    public void paintSelf(Graphics gImage)
    {
        super.paintSelf(gImage);
        setY(getY()+speed+addSpeed);
    }

    public boolean hitPlayerCheck(Player player)
    {
        int w = WIDTH + player.WIDTH;
        int h = HEIGHT + player.HEIGHT;
        if (Math.abs(y-player.y+HEIGHT)<h && Math.abs(y-player.y-player.HEIGHT)<h
                &&  Math.abs(x-player.x+WIDTH)<w && Math.abs(x-player.x-player.WIDTH)<w)
        {
            return true;
        }
        return false;
    }
}
