package leidian.obj;

import java.awt.*;

public class HitEnemy extends HitEventSource{
    public HitEnemy() {
    }

    @Override
    public void hit(Player player, EnemyLegion enemyLegion, ExplodeCollection explodeCollection, Graphics gImage) {

        for (int indexEnemy = 0;indexEnemy<enemyLegion.enemyVector.size();indexEnemy++) {
            for (int indexBullet = 0; indexBullet < player.playerBulletVector.size()
                    && indexEnemy<enemyLegion.enemyVector.size(); indexBullet++) {
                Enemy enemy = (Enemy) enemyLegion.enemyVector.elementAt(indexEnemy);//遍历每一个enemy
                PlayerBullet playerBullet = (PlayerBullet) player.playerBulletVector.elementAt(indexBullet);//遍历每一个子弹
                int shootAtx = -1;
                int shootAty = -1;

                //碰撞条件
                if ( (((playerBullet.x - enemy.x)<0 &&(Math.abs(playerBullet.x - enemy.x)< playerBullet.WIDTH)) ||
                        ((playerBullet.x - enemy.x)>0 && (Math.abs(playerBullet.x - enemy.x)< enemy.WIDTH)) ) &&
                        ( (playerBullet.y - enemy.y)< enemy.HEIGHT && (playerBullet.y - enemy.y)>0) ) {
                    shootAtx = enemy.x;
                    shootAty = enemy.y;
                    enemyLegion.enemyVector.remove(indexEnemy);
                    player.playerBulletVector.remove(indexBullet);
                    indexBullet--;
                    player.killCount++;
                }
                if (shootAtx > 0 && shootAty > 0) {
                    ExplodeEvent event = new ExplodeEvent(this,true);
                    this.notifies(shootAtx,shootAty,explodeCollection,event,gImage);
                }
            }
        }

    }

    @Override
    public void hit(Player player, Boss boss, Graphics gImage) {

    }
}
