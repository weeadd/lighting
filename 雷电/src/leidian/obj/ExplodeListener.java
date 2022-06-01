package leidian.obj;

import java.awt.*;
import java.util.EventListener;

interface ExplodeListener extends EventListener {
     void createExplode(int shootAtx, int shootAty, ExplodeCollection explodeCollection, ExplodeEvent e, Graphics gImage);
}
