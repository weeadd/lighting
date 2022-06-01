package leidian.obj;

import leidian.GameWin;
import leidian.utils.GameUtils;

import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.Timer;
import java.util.TimerTask;
import java.util.Vector;

public class Player extends Role implements shoot{

    String currentKey = "stop";//默认为停止的currentKey，接受键盘监听传来的值，代表了移动状态
    public int killCount;//= 0;
    int HP;
    int state;
    public Vector playerBulletVector = new Vector();
    public Player(Image img, int x, int y, int HEIGHT, int WIDTH, int speed, GameWin frame, int killCount0,int HP) {
        super(img, x, y, HEIGHT, WIDTH, speed, frame);
        killCount = killCount0;
        this.HP = HP;

        java.util.Timer timer = new Timer();
        timer.schedule(new addBullet(),0,300);//向Vector里不断添加子弹

        Timer moveTimer = new Timer();
        moveTimer.schedule(new move(),0,50);//不断调用move方法检索currentKey的状态，做出移动

        this.frame.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                switch (e.getKeyCode()) {
                    case 37: {
                        if (Player.super.x>=20)
                            currentKey = "left";
                        break;
                    }
                    case 38: {
                        if (Player.super.y>=60)
                            currentKey = "up";
                        break;
                    }
                    case 39: {
                        if (Player.super.x<=590)
                            currentKey = "right";
                        break;
                    }
                    case 40: {
                        if (Player.super.y<=830)
                            currentKey = "down";
                        break;
                    }
                    default:{
                        currentKey = "stop";
                        break;
                    }
                }
            }//按下键盘修改currentKey

            @Override
            public void keyReleased(KeyEvent e) {
                switch (e.getKeyCode()) {
                    case 37: {
                        if (Player.super.x>=20)
                            currentKey = "stop";
                        break;
                    }
                    case 38: {
                        if (Player.super.y>=60)
                            currentKey = "stop";
                        break;
                    }
                    case 39: {
                        if (Player.super.x<=590)
                            currentKey = "stop";
                        break;
                    }
                    case 40: {
                        if (Player.super.y<=830)
                            currentKey = "stop";
                        break;
                    }
                    default:{
                        currentKey = "stop";
                        break;
                    }
                }
            }//松开键盘将currentKey恢复为不动

        });//为当前frame添加键盘监听

    }
    //全参构造方法，GameWin中调用



    public Player() {
        super();
    }//无参构造方法

    public Player(int x, int y) {
    }

    public int getKillCount() {
        return killCount;
    }

    public int getHP() {
        return HP;
    }

    public void setHP(int HP) {
        this.HP = HP;
    }

    public void shoot(Graphics gImage)
    {
        for (int index = 0;index<playerBulletVector.size();index++)
        {
            PlayerBullet playerBullet = (PlayerBullet)playerBulletVector.elementAt(index);

            if (playerBullet.y>=0)
                playerBullet.paintSelf(gImage);

            //子弹的移动
            playerBullet.setY(playerBullet.y-playerBullet.speed);

            //删除超出屏幕的子弹
            if (playerBullet.y<0)
            {
                playerBulletVector.remove(index);
                index--;
            }
        }
    }

    @Override
    public void shoot(Graphics gImage, Player player) {
    }

    public int shootAtBoss(Boss boss,Graphics gImage)
    {
            for (int indexBullet = 0; indexBullet < playerBulletVector.size(); indexBullet++) {
                PlayerBullet playerBullet = (PlayerBullet) playerBulletVector.elementAt(indexBullet);

                //碰撞条件
                if ((((playerBullet.x - boss.x)<0 &&(Math.abs(playerBullet.x - boss.x)< playerBullet.WIDTH)) ||
                        ((playerBullet.x - boss.x)>0 && (Math.abs(playerBullet.x - boss.x)< boss.WIDTH)) ) &&
                        ( (playerBullet.y - boss.y)< boss.HEIGHT && (playerBullet.y - boss.y)>0) ) {
                    playerBulletVector.remove(indexBullet);
                    indexBullet--;
                    killCount++;
                    return boss.getHP()-1;
                }

            }

            return boss.getHP();
    }//移除与BOSS碰撞的子弹，返回新的HP

    class addBullet extends TimerTask {
        @Override
        public void run() {
            playerBulletVector.add(new PlayerBullet(GameUtils.bulletImg,x+57,y-10,2,2,10,frame));
        }
    }//添加子弹的Timer任务

    class move extends TimerTask {
        @Override
        public void run() {
            switch (currentKey) {
                case "left": {

                    if (Player.super.x>=20)
                    Player.super.x -= speed;
                    break;
                }
                case "up": {
                    if (Player.super.y>=60)
                    Player.super.y -= speed;

                    break;
                }
                case "right": {
                    if (Player.super.x<=590)
                    Player.super.x += speed;

                    break;
                }
                case "down": {
                    if (Player.super.y<=830)
                    Player.super.y += speed;

                    break;
                }
                default:
                    break;
            }
        }
    }//添加子弹的Timer任务
    public void paintSelf(Graphics gImage){
        super.paintSelf(gImage);
        gImage.drawImage(GameUtils.bloodImg,0,50,null);
        gImage.setColor(Color.gray);
        gImage.fillRect((int) (12.7 * (double)HP),53,254 - (int) (12.7 * (double) HP),9);
        gImage.setColor(Color.yellow);
        gImage.setFont(new Font("微软雅黑",Font.BOLD,15));
        gImage.drawString(Integer.toString(killCount*7),35,87);
    }

    @Override
    public void bossShoot(Graphics gImage) {

    }
}
