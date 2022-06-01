package leidian.obj;

import leidian.utils.GameUtils;

import java.awt.*;
import java.util.Vector;

public class HitPlayer extends HitEventSource{
    public HitPlayer() {
    }

    @Override
    public void hit(Player player, Boss boss, Graphics gImage) {
        Vector vector = new Vector();
        if (boss.boss == 1)
            vector = boss.smallBossBulletVector;
        else if (boss.boss == 2)
            vector = boss.bossBulletVector;
        else if (boss.boss == 3)
            vector = boss.bigBossBulletVector;
        for (int index = 0;index<vector.size();index++){
            BossBullet bossBullet = (BossBullet) vector.elementAt(index);


            int w = bossBullet.WIDTH + player.WIDTH;
            int h = bossBullet.HEIGHT + player.HEIGHT;

            if (Math.abs(bossBullet.y-player.y+ bossBullet.HEIGHT)<h
                    && Math.abs(bossBullet.y-player.y-player.HEIGHT)<h
                    &&  Math.abs(bossBullet.x-player.x+bossBullet.WIDTH)<w
                    && Math.abs(bossBullet.x-player.x-player.WIDTH)<w)
            {

                //boss.smallBossBulletVector.remove(index);
                if (boss.boss == 1) {
                    boss.smallBossBulletVector.remove(index);
                    player.setHP(player.getHP()-1);
                }
                else if (boss.boss == 2 && bossBullet.paint) {
                    bossBullet.setPaint(false);
                    player.setHP(player.getHP()-1);
                }
                else if (boss.boss == 3 && bossBullet.paint){
                    bossBullet.setPaint(false);
                    player.setHP(player.getHP()-1);
                }

            }
        }
    }

    @Override
    public void hit(Player player, EnemyLegion enemyLegion, ExplodeCollection explodeCollection, Graphics gImage) {

    }
}
