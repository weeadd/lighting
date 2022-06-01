package leidian.obj;

import leidian.GameWin;
import leidian.utils.GameUtils;

import java.awt.*;
import java.util.Timer;
import java.util.TimerTask;
import java.util.Vector;

public class Boss extends Role implements shoot{
    Vector bossBulletVector = new Vector();
    Vector smallBossBulletVector = new Vector();
    Vector bigBossBulletVector = new Vector();
    int HP;
    int[] direction = new int[3000];
    int index_d = 0;
    Player player=null;
    int mark=0;
    int counter =4;
    int boss;
    public Boss() {
    }

    public Boss(Image img, int x, int y, int HEIGHT, int WIDTH, int speed, GameWin frame,int HP0,Player player1,int boss) {
        super(img, x, y, HEIGHT, WIDTH, speed, frame);
        HP = HP0;
        player = player1;
        this.boss = boss;
    }

    //@Override
    /*public void paintSelf(Graphics gImage,int a) {
        if (mark==0) {
        counter = 4;
        mark = 1;
        java.util.Timer timer = new Timer();
        if (boss == 1)
            timer.schedule(new Boss.smallBossAddBullet(), 500, 200);
        else if (boss == 2)
            timer.schedule(new Boss.bossAddBullet(), 500, 200);
        else if (boss == 3){
            if (a==1){
                timer.schedule(new Boss.bossAddBullet(), 500, 200);
            }
            else {
                timer.schedule(new Boss.bigBossAddBullet(), 500, 400);
            }
        }

    }
        super.paintSelf(gImage);
        if(x>550 || x< 0){
            speed = -speed;
        }
        x+=speed;
    }*/

    public void paintSelf(Graphics gImage) {
        if (mark==0) {
            counter = 4;
            mark = 1;
            java.util.Timer timer = new Timer();
            if (boss == 1)
                timer.schedule(new Boss.smallBossAddBullet(), 500, 200);
            else if (boss == 2)
                timer.schedule(new Boss.bossAddBullet(), 500, 200);
            else if (boss == 3){
                timer.schedule(new Boss.bigBossAddBullet(), 500, 200);
            }
        }
        super.paintSelf(gImage);
        if(x>550 || x< 0){
            speed = -speed;
        }
        x+=speed;
    }


    class bossAddBullet extends TimerTask {
        @Override
        public void run() {
            if(counter>0){
                bossBulletVector.add(new BossBullet(GameUtils.bossBulletTmg,x+145,y+110,2,2,4,frame));
                bossBulletVector.add(new BossBullet(GameUtils.bossBulletTmg,x+85,y+170,2,2,4,frame));
                bossBulletVector.add(new BossBullet(GameUtils.bossBulletTmg,x+195,y+170,2,2,4,frame));
                bossBulletVector.add(new BossBullet(GameUtils.bossBulletTmg,x+85,y+170,2,2,4,frame));
                bossBulletVector.add(new BossBullet(GameUtils.bossBulletTmg,x+195,y+170,2,2,4,frame));
                counter--;
            }
            else {
                mark=0;
                cancel();
            }

        }
    }//添加子弹的Timer任务

    class smallBossAddBullet extends TimerTask {

        @Override
        public void run() {
            if(counter>0){
                smallBossBulletVector.add(new BossBullet(GameUtils.bossBulletTmg,x+85,y+100,10,10,5,frame));
                if (index_d<smallBossBulletVector.size()){
                    BossBullet smallBossBullet = (BossBullet)smallBossBulletVector.elementAt(index_d);
                    double distance = Math.sqrt(
                            ( Math.abs ( (double)player.x - (double) smallBossBullet.x)  * Math.abs ( (double)player.x - (double) smallBossBullet.x) )
                                    + ( Math.abs ( (double)player.y - (double) smallBossBullet.y)  * Math.abs ( (double)player.y - (double) smallBossBullet.y) )
                    );
                    direction[index_d]=(int) (  ((double)player.x - (double) smallBossBullet.x) *7.0/distance );
                    index_d++;
                }
                counter--;
            }
            else {
                mark=0;
                cancel();
            }
        }
    }//添加子弹的Timer任务

    class bigBossAddBullet extends TimerTask {
        @Override
        public void run() {
            if(counter>0){
                bigBossBulletVector.add(new BossBullet(GameUtils.bossBulletTmg,x+50,y+160,2,2,5,frame));
                bigBossBulletVector.add(new BossBullet(GameUtils.bossBulletTmg,x+220,y+160,2,2,5,frame));
                counter--;
            }
            else {
                mark=0;
                cancel();
            }
        }
    }//添加子弹的Timer任务

    public void bossShoot(Graphics gImage)
    {
        for (int index = 0;index<bossBulletVector.size()-4;index+=5)
        {
            BossBullet bossBullet = (BossBullet)bossBulletVector.elementAt(index);
            BossBullet bossBullet_left = (BossBullet)bossBulletVector.elementAt(index+1);
            BossBullet bossBullet_right = (BossBullet)bossBulletVector.elementAt(index+2);
            BossBullet bossBullet_left1 = (BossBullet)bossBulletVector.elementAt(index+3);
            BossBullet bossBullet_right1 = (BossBullet)bossBulletVector.elementAt(index+4);
            if (bossBullet.y<=900)
                bossBullet.paintSelf(gImage);

            if (bossBullet_left.y<=900)
                bossBullet_left.paintSelf(gImage);

            if (bossBullet_right.y<=900)
                bossBullet_right.paintSelf(gImage);

            if (bossBullet_left1.y<=900)
                bossBullet_left1.paintSelf(gImage);

            if (bossBullet_right1.y<=900)
                bossBullet_right1.paintSelf(gImage);
            //子弹的移动
            bossBullet.setY(bossBullet.y+bossBullet.speed);
            bossBullet_left.setX(bossBullet_left.x-2);
            bossBullet_left.setY(bossBullet_left.y+bossBullet_left.speed);
            bossBullet_right.setX(bossBullet_right.x+2);
            bossBullet_right.setY(bossBullet_right.y+bossBullet_right.speed);
            bossBullet_left1.setX(bossBullet_left1.x-1);
            bossBullet_left1.setY(bossBullet_left1.y+bossBullet_left1.speed);
            bossBullet_right1.setX(bossBullet_right1.x+1);
            bossBullet_right1.setY(bossBullet_right1.y+bossBullet_right1.speed);

        }
    }

    public void smallBossShoot(Graphics gImage,Player player) {
        for (int index = 0;index<smallBossBulletVector.size();index++)
        {
            BossBullet smallBossBullet = (BossBullet)smallBossBulletVector.elementAt(index);
            if (smallBossBullet.y<=900) {
                smallBossBullet.paintSelf(gImage);
            }
            smallBossBullet.setX(smallBossBullet.x +  direction[index]);

            //子弹的移动
            smallBossBullet.setY(smallBossBullet.y+smallBossBullet.speed);
            smallBossBulletVector.setElementAt(smallBossBullet,index);

        }
    }

    public void bigBossShoot(Graphics gImage,Player player){
        for (int index = 0;index<bigBossBulletVector.size()-1;index+=2)
        {
            BossBullet bossBullet_Left = (BossBullet) bigBossBulletVector.elementAt(index);
            BossBullet bossBullet_Right = (BossBullet) bigBossBulletVector.elementAt(index+1);

            if (bossBullet_Left.y<900)
                bossBullet_Left.paintSelf(gImage);

            //子弹的移动
            double l = Math.sqrt((bossBullet_Left.x-player.x)*(bossBullet_Left.x-player.x)
                    +(bossBullet_Left.y-player.y)*(bossBullet_Left.y-player.y));
            double speedx = bossBullet_Left.speed*(bossBullet_Left.x-player.x)/l;
            double speedy = bossBullet_Left.speed*(bossBullet_Left.y-player.y)/l;
            if (bossBullet_Left.y < player.y-20 && !bossBullet_Left.hasOccoured) {
                bossBullet_Left.speedx = speedx;
                bossBullet_Left.speedy = speedy;
            }
            else
                bossBullet_Left.setHasOccoured(true);

            bossBullet_Left.setX(bossBullet_Left.getX() - (int) bossBullet_Left.speedx);
            bossBullet_Left.setY(bossBullet_Left.getY() - (int) bossBullet_Left.speedy);
            //删除超出屏幕的子弹
            if (bossBullet_Left.y>900||bossBullet_Left.x<0||bossBullet_Left.x>650)
            {
                bossBullet_Left.setPaint(true);
            }

            if (bossBullet_Right.y<900)
                bossBullet_Right.paintSelf(gImage);

            //子弹的移动
            double l1 = Math.sqrt((bossBullet_Right.x-player.x)*(bossBullet_Right.x-player.x)+(bossBullet_Right.y-player.y)*(bossBullet_Right.y-player.y));
            double speedx1 = bossBullet_Right.speed*(bossBullet_Right.x-player.x)/l1;
            double speedy1 = bossBullet_Right.speed*(bossBullet_Right.y-player.y)/l1;
            if (bossBullet_Right.y < player.y-20 && !bossBullet_Right.hasOccoured) {
                bossBullet_Right.speedx = speedx1;
                bossBullet_Right.speedy = speedy1;
            }
            else
                bossBullet_Right.setHasOccoured(true);

            bossBullet_Right.setX(bossBullet_Right.getX() - (int) bossBullet_Right.speedx);
            bossBullet_Right.setY(bossBullet_Right.getY() - (int) bossBullet_Right.speedy);
            //删除超出屏幕的子弹
            if (bossBullet_Right.y>900||bossBullet_Right.x<0||bossBullet_Right.x>650)
            {
                bossBullet_Right.setPaint(true);
            }

        }
    }

    public void bigBossShoot1(Graphics gImage)
    {
        for (int index = 0;index<bigBossBulletVector.size()-4;index+=5)
        {
            BossBullet bossBullet = (BossBullet)bigBossBulletVector.elementAt(index);
            BossBullet bossBullet_left = (BossBullet)bigBossBulletVector.elementAt(index+1);
            BossBullet bossBullet_right = (BossBullet)bigBossBulletVector.elementAt(index+2);
            BossBullet bossBullet_left1 = (BossBullet)bigBossBulletVector.elementAt(index+3);
            BossBullet bossBullet_right1 = (BossBullet)bigBossBulletVector.elementAt(index+4);
            if (bossBullet.y<=900)
                bossBullet.paintSelf(gImage);

            if (bossBullet_left.y<=900)
                bossBullet_left.paintSelf(gImage);

            if (bossBullet_right.y<=900)
                bossBullet_right.paintSelf(gImage);

            if (bossBullet_left1.y<=900)
                bossBullet_left1.paintSelf(gImage);

            if (bossBullet_right1.y<=900)
                bossBullet_right1.paintSelf(gImage);
            //子弹的移动
            bossBullet.setY(bossBullet.y+bossBullet.speed);
            bossBullet_left.setX(bossBullet_left.x-2);
            bossBullet_left.setY(bossBullet_left.y+bossBullet_left.speed);
            bossBullet_right.setX(bossBullet_right.x+2);
            bossBullet_right.setY(bossBullet_right.y+bossBullet_right.speed);
            bossBullet_left1.setX(bossBullet_left1.x-1);
            bossBullet_left1.setY(bossBullet_left1.y+bossBullet_left1.speed);
            bossBullet_right1.setX(bossBullet_right1.x+1);
            bossBullet_right1.setY(bossBullet_right1.y+bossBullet_right1.speed);
        }
    }

    @Override
    public void shoot(Graphics gImage, Player player) {

    }

    public int getHP()
    {
        return HP;
    }

    public void setHP(int HP0)
    {
        HP = HP0;
    }
}
