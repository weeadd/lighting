package leidian;


import leidian.obj.*;
import leidian.utils.GameUtils;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.Vector;





public class GameWin extends JFrame {

    //游戏状态 0未开始 1游戏中 2暂停 3.通关失败 4.通关成功
    static int state = 0;

    Image offScreenImage = null;//空白图片

    int width = 650;
    int height = 900;

    BackGround bgobj =new BackGround(GameUtils.bgImg,0,-900,2);//背景实例
    Player player = new Player(GameUtils.playerImg,290,550,20,30,20, this,0,20);
    EnemyLegion enemyLegion = null;
    ExplodeCollection explodeCollection = new ExplodeCollection();
    Boss boss = null;
    Boss smallBoss = null;
    Boss bigBoss = null;

    HitEnemy hitEnemy = new HitEnemy();
    HitPlayer hitPlayer = new HitPlayer();
    int bigBossCounter = 0;
    int smallBossKillCount = 0;
    int bossKillCount = 0;
    int playerX = 0;
    int playerY = 0;

    public void launch(){
        //设置窗口是否可见
        this.setVisible(true);
        //设置窗口大小
        this.setSize(width,height);
        //设置窗口位置
        this.setLocationRelativeTo(null);
        //设置窗口标题
        this.setTitle("雷电");



        this.addKeyListener(new KeyAdapter() {
            public void keyPressed(KeyEvent e)
            {
                int keyCode = e.getKeyCode();
                //按空格开始游戏或继续游戏
                if(keyCode == 32 && (state == 0||state == 2))
                {
                    state = 1;
                    enemyLegion = new EnemyLegion();
                    hitEnemy.addExplodeListener(new EnemyExplode());
                    repaint();
                }
                //失败时按空格重新开始游戏
                if(keyCode == 32 && state == 3)
                {
                    state = 1;
                    bgobj =new BackGround(GameUtils.bgImg,0,-900,2);//背景实例
                    player = new Player(GameUtils.playerImg,290,550,20,30,20, player.getFrame(),0,20);
                    enemyLegion = new EnemyLegion();
                    explodeCollection =new ExplodeCollection();
                    hitEnemy.addExplodeListener(new EnemyExplode());
                    repaint();
                }
                //按p暂停游戏
                if (keyCode == 80 && state == 1)
                {
                    state = 2;
                }
            }
        });
        if(state==1){

        }

        while (true){
            repaint();
            try {
                Thread.sleep(25);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }//开始后立刻移动背景倍加


    }


    @Override
    public void paint(Graphics g) {

        if(offScreenImage == null){
            offScreenImage = createImage(width,height);
        }
        Graphics gImage = offScreenImage.getGraphics();
        gImage.fillRect(0,0,width,height);

        if(state == 0)
        {
            gImage.drawImage(GameUtils.bgImg,0,0,null);
            gImage.drawImage(GameUtils.logoImg,100,150,null);
            gImage.setColor(Color.yellow);
            gImage.setFont(new Font("微软雅黑",Font.BOLD,40));
            gImage.drawString("按空格键开始游戏",150,450);
        }
        if (state == 1){
           bgobj.paintSelf(gImage);
           player.paintSelf(gImage);
           player.shoot(gImage);
           enemyLegion.paintEnemy(gImage);
           hitEnemy.hit(player,enemyLegion,explodeCollection,gImage);
           explodeCollection.paintExplode(gImage);
           if (player.getKillCount() >= 10){
               if (smallBoss == null)
                   smallBoss = new Boss(GameUtils.bossImg0,100,50,30,200,5,this,10,player,1);
               else {
                   if (smallBoss.getHP() > 0) {
                       smallBoss.paintSelf(gImage);
                       smallBoss.smallBossShoot(gImage,player);
                       smallBoss.setHP(player.shootAtBoss(smallBoss, gImage));
                       hitPlayer.hit(player,smallBoss,gImage);
                       smallBossKillCount = player.getKillCount();
                   }
                   else {
                       if (player.getKillCount() >= smallBossKillCount+10){
                           if (boss == null)
                               boss = new Boss(GameUtils.bossImg,100,50,30,300,3,this,15,player,2);
                           else {
                               if (boss.getHP() > 0) {
                                   boss.paintSelf(gImage);
                                   boss.bossShoot(gImage);
                                   boss.setHP(player.shootAtBoss(boss, gImage));
                                   hitPlayer.hit(player,boss,gImage);
                                   bossKillCount = player.getKillCount();
                               }
                               else {
                                   if (player.getKillCount() >= bossKillCount+10){
                                       if (bigBoss == null)
                                           bigBoss = new Boss(GameUtils.bigBossImg,100,50,30,200,1,this,20,player,3);
                                       else {
                                           if (bigBoss.getHP() > 0) {
                                               bigBoss.paintSelf(gImage);
                                               bigBoss.bigBossShoot(gImage,player);
                                               bigBoss.setHP(player.shootAtBoss(bigBoss, gImage));
                                               hitPlayer.hit(player,bigBoss,gImage);
                                               bigBossCounter++;
                                           }
                                           else {
                                               state = 4;
                                           }
                                       }
                                   }
                               }
                           }
                       }
                   }
               }
           }
           player.setHP(enemyLegion.hitPlayerCheck(player));
           playerX = player.getX();
           playerY = player.getY();
           if (player.getHP()<=0)
               state = 3;
        }

        if (state == 2)
        {
            player.setX(playerX);
            player.setY(playerY);
            gImage.drawImage(GameUtils.bgImg,0,bgobj.getY(),null);
            gImage.drawImage(GameUtils.playerImg,player.getX(),player.getY(),null);
            gImage.setColor(Color.red);
            gImage.setFont(new Font("仿宋",Font.BOLD,30));
            gImage.drawString("游戏暂停",270,450);
            gImage.drawString("按空格键继续游戏",205,480);
        }
        if (state == 3)
        {
            smallBoss = null;
            boss = null;
            bigBoss = null;
            gImage.drawImage(GameUtils.bgImg,0,bgobj.getY(),null);
            gImage.drawImage(GameUtils.loseImg,100,150,null);
            gImage.setColor(Color.red);
            gImage.setColor(Color.yellow);
            gImage.setFont(new Font("微软雅黑",Font.BOLD,40));
            gImage.drawString("按空格键重新开始游戏",125,480);
        }
        if (state == 4){
            gImage.drawImage(GameUtils.bgImg,0,bgobj.getY(),null);
            gImage.drawImage(GameUtils.winImg,90,150,null);
            gImage.drawImage(GameUtils.scoreImg,270,410,null);

            gImage.setColor(Color.yellow);
            gImage.setFont(new Font("微软雅黑",Font.BOLD,40));
            gImage.drawString(Integer.toString((player.killCount)*7),300,520);
        }

        g.drawImage(offScreenImage,0,0,null);
    }

    public static void main(String[] args) {
        GameWin gameWin = new GameWin();
        gameWin.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        gameWin.launch();
    }

}
