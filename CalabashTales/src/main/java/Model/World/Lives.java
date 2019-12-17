package Model.World;

import Model.Model;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import sun.font.EAttribute;

import java.util.Random;
import java.util.concurrent.TimeUnit;


public class Lives implements Runnable{
    static Image deadImage=new Image("pic/dead.png");
    protected Position position;
    protected Attributes attributes;
    protected Image myAppearance;
    Random direction=new Random();
    Random evade=new Random();
    protected static Tile ground[][];
    public static void init(Tile y[][])
    {
        ground=y;
    }
    public void run() {

        normalThread();
    }
    public Lives()
    {

    }
    public Lives(Position x, Attributes y)
    {
        attributes=y;
        position=x;
        this.myAppearance = new Image(this.attributes.URL);
    }
    public Attributes GetAttributes()
    {
        return attributes;
    }
    public void SetAttributes(Attributes x)
    {
        attributes=x;
    }
    public Position GetPosition()
    {
        return position;
    }
    public void SetPosition(Position x)
    {
        position =x;
    }
    public void normalThread()
    {
        new Thread(() -> {
            while (this.attributes.living== Attributes.livingStatus.live) {
                try {
                    randomWalk();
                    TimeUnit.MILLISECONDS.sleep(3000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

        }).start();
    }
    public void randomWalk()
    {
        int dir=direction.nextInt(5);
        Position dst=position;
        switch(dir)
        {
            case 0:break;//don't move
            case 1:{
                if(dst.x<1)
                {

                }else
                {
                    dst.x--;
                    break;
                }
            }
            case 2:{
                if(dst.x>=BattleGround.M-1)
                {

                }
                else
                {
                    dst.x++;
                    break;
                }

            }
            case 3:{
                if(dst.y>=BattleGround.N-1)
                {

                }
                else
                {
                    dst.y++;
                    break;
                }
            }
            case 4:{
                if(dst.y<1)
                {

                }
                else
                {
                    dst.y--;
                    break;
                }

            }
            default:break;
        }
        synchronized (ground)
        {
            if((ground[dst.x][dst.y].GetIsOccupied()==false))
            {
                walk(dst);
            }
            else
            {
                Lives Object=ground[dst.x][dst.y].GetWho();
                if(Object.attributes.group!=this.attributes.group)
                {
                    attack(Object);
                }
            }
        }
    }
    public void walk(Position x)
    {
        /*if(x.x>BattleGround.M||x.y>BattleGround.N)
        {
            System.out.println("wrong!     x.x="+x.x+"x.y="+x.y);
        }
        else
        {
            System.out.println("right!     x.x="+x.x+"x.y="+x.y);
        }*/
        ground[position.x][position.y].SetALL(false,null);
        ground[x.x][x.y].SetALL(true,this);
        this.position=x;

    }
    public void attack(Lives enemy)
    {
      enemy.defend(this.attributes.Ack);
    }
    public void defend(int ack)
    {
        int evadeSuccess=evade.nextInt(this.attributes.Evade);
        if(evadeSuccess!=1)
        {
            this.attributes.Hp-=ack;
        }
        if(this.attributes.Hp<=0)
        {
            die();
        }
    }
    public void die()
    {
        this.attributes.living= Attributes.livingStatus.dead;
        synchronized (ground)
        {
            ground[this.position.x][this.position.y].SetALL(false,null);
        }
    }
    public void showAppearance(GraphicsContext gc, double x, double y, double w,double h)
    {

        Image im=new Image(this.attributes.URL);
       // System.out.println(this.attributes.URL);
        if(this.attributes.living== Attributes.livingStatus.live) {
            gc.drawImage(im, x, y, w, h);
            gc.setFill(Color.valueOf("red"));
            gc.fillRect(x, y, w, w / 6);
            gc.setFill(Color.color(0, 1, 0));
            double hpRatio = attributes.Hp / attributes.maxHp;
            double hpLength = w * hpRatio;
            gc.fillRect(x, y, hpLength, w / 6);
        }else if (this.attributes.living== Attributes.livingStatus.dead){
//            System.out.println(getName() + "画自己坟墓");
            gc.drawImage(deadImage, x, y, w, h);
        }
    }





}

