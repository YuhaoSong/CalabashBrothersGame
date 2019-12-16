package Model;

import Controller.Controller;
import Model.Bad.Bad;
import Model.Good.Good;
import Model.World.BattleGround;
import Model.World.Lives;
import javafx.application.Platform;
import javafx.scene.canvas.Canvas;
import javafx.scene.image.Image;

import java.lang.management.ThreadInfo;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class Model implements  Runnable {
    enum GameStatus{
        going, justWin, evilWin
    }

    private static Model Model;
    private GameStatus gameStatus;//游戏状态
    private static BattleGround battleGround = new BattleGround();
    private ExecutorService exec = Executors.newCachedThreadPool();
    private Canvas canvas;
    private Good good;
    private Bad bad;
    public static Model getInstance() {
        if (Model != null)
            return Model;
        else {
            return null;
//            throw new Exception("GameModel还没有初始化好");
        }
    }

    public Model(Canvas canvas) {
        Lives.init(battleGround.ground);
        bad = new Bad(battleGround.ground);
        good = new Good(battleGround.ground);

        gameStatus = GameStatus.going;
        this.canvas = canvas;
        Model = this;
    }

    private void displayBoard(){
        double width = this.canvas.getWidth();
        double height = this.canvas.getHeight();
        int n = BattleGround.M;

        double boardWidth = height * 6 / 7;
        double startLayoutX = (width - height) / 2 + 70;
        double startLayoutY = (height - boardWidth) / 2;
        double creatureSize = boardWidth / n;

        Image image = new Image("map.jpg");
        synchronized (battleGround) {
            this.canvas.getGraphicsContext2D().drawImage(image, 0, 0, image.getWidth(), image.getHeight());
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    double x = startLayoutX + i * creatureSize;
                    double y = startLayoutY + j * creatureSize;
                    if (!(this.battleGround.ground[j][i].GetIsOccupied())) {
                        this.battleGround.ground[j][i].GetWho().showAppearance(this.canvas.getGraphicsContext2D(), x, y, creatureSize);
                    }
                }
            }
        }
    }
    public void run() {
        initThreads();
        new Thread(() -> {
            int i = 0;
            Image image = new Image("win.png");
            while (true){
                if(!good.stillAlive()){
                    gameStatus = GameStatus.evilWin;
                    System.out.println("妖怪赢了");
                    image = new Image("fail.png");
                    break;
                }else if(!bad.stillAlive()){
                    gameStatus = GameStatus.justWin;
                    System.out.println("葫芦娃赢了");
                    break;
                }
                try {
                    i++;
                    System.out.println("第" + i + "次");
                    synchronized (Model.class){
                        Model.class.wait();
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            displayBoard();
            Image finalImage = image;
            Platform.runLater(()->{
           //daying tupian
            });
            System.out.println("Game Over");
          //qingli xianc
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();
        new Thread(()->{
            while (gameStatus == GameStatus.going){
                Platform.runLater(this::displayBoard);
                try {
                    TimeUnit.MILLISECONDS.sleep(50);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

    private void initThreads(){
        exec.execute(good);
        exec.execute(bad);
    }

}

