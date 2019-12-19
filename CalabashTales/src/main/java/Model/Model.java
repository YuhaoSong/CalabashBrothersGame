package Model;

import Controller.Controller;
import Model.Bad.Bad;
import Model.Good.Good;
import Model.World.BattleGround;
import Model.World.Lives;
import Model.World.Position;
import javafx.application.Platform;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import org.omg.Messaging.SYNC_WITH_TRANSPORT;

import java.lang.management.ThreadInfo;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class Model implements Runnable {
    @Override
    public void run() {
        initThreads();
        new Thread(() -> {
            Image image = new Image("pic/win.png");
            while (true) {
                if (!good.stillAlive()) {
                    gameStatus = GameStatus.evilWin;
                    System.out.println("妖怪赢了");
                    image = new Image("pic/fail.png");
                    break;
                } else if (!bad.stillAlive()) {
                    gameStatus = GameStatus.justWin;
                    System.out.println("葫芦娃赢了");
                    break;
                }
            }
        });
    }

    enum GameStatus{
        going, justWin, evilWin
    }

    private static Model Model;
    private GameStatus gameStatus;//游戏状态
    private static BattleGround battleGround = new BattleGround();
    private ExecutorService exec = Executors.newCachedThreadPool();
    private Good good;
    private Bad bad;
    private AnchorPane pane;
    public static Model getInstance() {
        if (Model != null)
            return Model;
        else {
            return null;
//            throw new Exception("GameModel还没有初始化好");
        }
    }
    public Model(AnchorPane p) {
        Lives.init(battleGround.ground);
        bad = new Bad(battleGround.ground);
        good = new Good(battleGround.ground);
        this.pane=p;
        gameStatus = GameStatus.going;
        Model = this;
        /*BackgroundImage myBI = new BackgroundImage(new Image("pic/map.jpg",1024,1024,true,true),
                BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT, BackgroundSize.DEFAULT);
        pane.setBackground(new Background(myBI));*/
        System.out.print("first");
        for(Lives life: good.members){
            addImage(life);
        }
        for(Lives life: bad.members){
            addImage(life);
        }

    }
    public void start()
    {

    }
    void addImage(Lives x) {

        if(pane==null)
        {
            System.out.println("pane null");
        }
        if(x.GetImage()==null)
        {
            System.out.println("x.Position="+x.GetPosition().x);
            System.out.println("image null");
        }
        if(x.GetLabel()==null)
        {

            System.out.println("label null");
        }
        pane.getChildren().add(x.GetImage());
        pane.getChildren().add(x.GetLabel());

    }



    private void initThreads(){
        exec.execute(good);
        exec.execute(bad);
    }

}

