package leidian.obj;

import java.awt.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public abstract class HitEventSource {
    //事件源

   List<ExplodeListener> listener = new ArrayList<ExplodeListener>();


    //增加观察者方法
    public void addExplodeListener(ExplodeListener explode0) {
        listener.add(explode0);
    }

    //删除观察者方法
    public void removeExplodeListener(ExplodeListener explode0) {
        listener.remove(explode0);
    }

    public abstract void hit(Player player, EnemyLegion enemyLegion, ExplodeCollection explodeCollection , Graphics gImage);//事件触发器

    public abstract void hit(Player player, Boss boss,  Graphics gImage);

        protected void notifies(int x,int y,ExplodeCollection explodeCollection,ExplodeEvent e,Graphics gImage) {
        ExplodeListener explodeListener = null;
        Iterator<ExplodeListener> iterator = listener.iterator();

        while (iterator.hasNext()) {
            explodeListener = iterator.next();
            explodeListener.createExplode(x,y,explodeCollection,e, gImage);

        }
    }

}
