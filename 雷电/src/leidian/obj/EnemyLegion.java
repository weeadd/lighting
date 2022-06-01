package leidian.obj;

import leidian.utils.GameUtils;

import java.awt.*;
import java.util.Timer;
import java.util.TimerTask;
import java.util.Vector;

import java.util.Random;

public class EnemyLegion extends Role{
    public Vector enemyVector = new Vector();

    public EnemyLegion(){
        java.util.Timer timer = new Timer();
        timer.schedule(new EnemyLegion.addEnemy(),0,600);

    }

    class addEnemy extends TimerTask {
        @Override
        public void run() {
            Random random = new Random();
            int randomX = random.nextInt(550);
            int randomY = random.nextInt(100);
            enemyVector.add(new Enemy(GameUtils.enemyImg,randomX,randomY,30,100,5,frame));
        }
    }

    public void paintEnemy(Graphics gImage)
    {
        for (int index = 0;index<enemyVector.size();index++)
        {
            Enemy enemy = (Enemy) enemyVector.elementAt(index);
            enemy.paintSelf(gImage);
        }
    }

    public int hitPlayerCheck(Player player)
    {
        int damage = 0;
        for (int i =0;i<enemyVector.size();i++)
        {
            Enemy enemy = (Enemy) enemyVector.elementAt(i);
            if (enemy.hitPlayerCheck(player)) {
                damage++;
                enemyVector.remove(enemy);
            }
        }
        return player.HP - damage;
    }
}
