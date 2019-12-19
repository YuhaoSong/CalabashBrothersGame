package Model.World;

import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.application.Platform;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.text.Font;
import javafx.util.Duration;
import org.omg.Messaging.SYNC_WITH_TRANSPORT;

import java.util.Random;
import java.util.concurrent.TimeUnit;


public class Lives implements Runnable{
    public static int num=0;
    public int id;
    static Image deadImage=new Image("pic/dead.png");
    protected Position position;
    protected Attributes attributes;
    protected ImageView myAppearance;
    protected Label myHp;

    Random direction=new Random();
    Random evade=new Random();
    protected static Tile ground[][];
    public static int rx[]=new int[8];
    public static int ry[]=new int[8];
    public static void init(Tile y[][])
    {
        ground=y;
        rx[0]=0;ry[0]=1;
        rx[1]=1;ry[1]=0;
        rx[2]=1;ry[2]=1;
        rx[3]=-1;ry[3]=-1;
        rx[4]=-1;ry[4]=0;
        rx[5]=0;ry[5]=-1;
        rx[6]=-1;ry[6]=1;
        rx[7]=1;ry[7]=-1;

    }
    public void run() {
        normalThread();
    }
    public Lives()
    {

    }
    public Lives(Position x, Attributes y)
    {

        id=num;
        num=num+1;
        System.out.print("id==="+id+"\n");
        attributes=y;
        position=x;
        this.myAppearance = new ImageView();
        Image image = new Image(y.URL);
        System.out.print(y.URL);
        myAppearance.setImage(image);
        myHp=new Label("blood:"+attributes.Hp+"");
        myHp.setTextFill(Color.BLACK);
        SetPic();
    }
    public void SetPic()
    {
        myAppearance.setX(position.x*50);
        myAppearance.setY(position.y*50+10);
        myAppearance.setFitHeight(1000/BattleGround.N-10);
        myAppearance.setFitWidth(1000/BattleGround.M);
        myHp.setFont(new Font("Arial", 16));
        myHp.setWrapText(true);
        myHp.setTranslateX(position.x*50+5);
        myHp.setTranslateY(position.y*50-6);
        System.out.print(myHp.getTranslateX());
       // myHp.set
    }

    public ImageView GetImage()
    {
        return this.myAppearance;
    }
    public Label GetLabel(){return this.myHp;}
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
                   // System.out.println("in running");
                    TimeUnit.MILLISECONDS.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

        }).start();
    }
    public void randomWalk()
    {
        Lives life=this;
        int dir=direction.nextInt(5);
        Position dst=new Position(position);
        Position old=new Position(position);
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


        }
        for(int i=0;i<8;i++)
        {
            if((position.x+rx[i]>=0)&&(position.y+ry[i]>=0)&&(position.y+ry[i]<BattleGround.N)&&(position.x+rx[i]<BattleGround.M)&&(ground[position.x+rx[i]][position.y+ry[i]].GetIsOccupied()==true))
            {
                Lives Object=ground[position.x+rx[i]][position.y+ry[i]].GetWho();
                if(Object.attributes.group!=this.attributes.group)
                {
                    attack(Object);
                }
            }
        }
        Platform.runLater(new Runnable(){
            public void run(){
                ImageView cView = life.myAppearance;
                Label bView = life.myHp;
                Timeline t = new Timeline();
                t.getKeyFrames().addAll(
                        new KeyFrame(Duration.ZERO,new KeyValue(cView.xProperty(),old.x*50)),
                        new KeyFrame(new Duration(500),new KeyValue(cView.xProperty(), life.position.x *50)),
                        new KeyFrame(Duration.ZERO,new KeyValue(cView.yProperty(),old.y*50+10)),
                        new KeyFrame(new Duration(500),new KeyValue(cView.yProperty(),life.position.y*50+10)),
                        new KeyFrame(Duration.ZERO,new KeyValue(bView.translateXProperty(),old.x *50+5)),
                        new KeyFrame(new Duration(500),new KeyValue(bView.translateXProperty(),life.position.x *50+5)),
                        new KeyFrame(Duration.ZERO,new KeyValue(bView.translateYProperty(),old.y*50-6)),
                        new KeyFrame(new Duration(500),new KeyValue(bView.translateYProperty(),life.position.y*50-6))

                );
                t.play();
            }
        });
    }
    public void walk(Position x)
    {
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
            int Hp=this.attributes.Hp;
            //System.out.print("cuurent jhp="+this.attributes.Hp);
            Platform.runLater(new Runnable(){
                public void run()
                {
                   myHp.setText(Hp+"");
                   myHp.setTextFill(Color.RED);
                }});

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
        ImageView cView = this.myAppearance;
        Label bView =this.myHp;
        Platform.runLater(new Runnable(){
            public void run(){


                Timeline t = new Timeline();
                t.getKeyFrames().addAll(
                        new KeyFrame(Duration.ZERO,new KeyValue(cView.rotateProperty(),0)),
                        new KeyFrame(new Duration(500),new KeyValue(cView.rotateProperty(),180)),
                        new KeyFrame(Duration.ZERO,new KeyValue(cView.opacityProperty(),1)),
                        new KeyFrame(new Duration(500),new KeyValue(cView.opacityProperty(),0)),
                        new KeyFrame(Duration.ZERO,new KeyValue(bView.opacityProperty(),1)),
                        new KeyFrame(new Duration(500),new KeyValue(bView.opacityProperty(),0))

                );
                t.play();
            }
        });
    }

}

