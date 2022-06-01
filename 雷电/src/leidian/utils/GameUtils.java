package leidian.utils;

import java.awt.*;
import java.util.Vector;

public class GameUtils {
    //背景图片
    public static Image bgImg = Toolkit.getDefaultToolkit().getImage("imgs/bg.jpg");
    //boss图片
    public static Image bossImg = Toolkit.getDefaultToolkit().getImage("imgs/boss.gif");
    //爆炸图片
    public static Image explodeImg = Toolkit.getDefaultToolkit().getImage("imgs/explode/e6.gif");
    //我方战机图片
    public static Image playerImg = Toolkit.getDefaultToolkit().getImage("imgs/plane.png");
    //子弹图片
    public static Image bulletImg = Toolkit.getDefaultToolkit().getImage("imgs/shell.png");
    //敌方战机图片
    public static Image enemyImg = Toolkit.getDefaultToolkit().getImage("imgs/enemy.png");
    //Boss子弹图片
    public static Image bossBulletTmg = Toolkit.getDefaultToolkit().getImage("imgs/bullet.png");
    //小boss图片
    public static Image bossImg0 = Toolkit.getDefaultToolkit().getImage("imgs/boss0.png");
    //血条图片
    public static Image bloodImg = Toolkit.getDefaultToolkit().getImage("imgs/blood1.png");
    //失败图片
    public static Image loseImg = Toolkit.getDefaultToolkit().getImage("imgs/lose.png");
    //胜利图片
    public static Image winImg = Toolkit.getDefaultToolkit().getImage("imgs/win.png");
    //logo图片
    public static Image logoImg = Toolkit.getDefaultToolkit().getImage("imgs/logo.png");
    //bigBoss图片
    public static Image bigBossImg = Toolkit.getDefaultToolkit().getImage("imgs/bigboss.gif");
    //得分图片
    public static Image scoreImg = Toolkit.getDefaultToolkit().getImage("imgs/score.png");

    public static Vector ExplodeImg()
    {
        Vector explode = new Vector();
        for (int i = 1;i<=16;i++)
            explode.add(Toolkit.getDefaultToolkit().getImage("imgs/explode/e"+Integer.toString(i)+".gif"));
        return explode;
    }
}
