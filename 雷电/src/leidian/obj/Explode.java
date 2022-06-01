package leidian.obj;
import leidian.utils.GameUtils;

import java.awt.*;
import java.util.Timer;
import java.util.TimerTask;

public class Explode extends Role{
    int x;
    int y;
    int i;
    ExplodeCollection explodeCollection;
    public boolean mark = false;
    Graphics gImage;

    public Explode(int x0, int y0, ExplodeCollection explodeCollection1,Graphics gImage1)
    {
        x = x0;
        y = y0;
        explodeCollection=explodeCollection1;
        gImage=gImage1;
        mark=false;
        //java.util.Timer explodeTimer = new Timer();
        //explodeTimer.schedule(new paintExplode(),0,25);
    }

    class paintExplode extends TimerTask {
        @Override
        public void run() {
            if (i<=15){
                Image image = (Image) GameUtils.ExplodeImg().elementAt(i);
                gImage.drawImage(image,x,y,null);
                i++;
            }

        }
    }

    @Override
    public void paintSelf(Graphics gImage) {
        super.paintSelf(gImage);
        java.util.Timer explodeTimer = new Timer();
        explodeTimer.schedule(new paintExplode(),0,25);
    }
}
