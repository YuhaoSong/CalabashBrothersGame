package Model.World;

import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.application.Platform;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.util.Duration;

import java.io.*;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
import java.util.concurrent.TimeUnit;

public class Replay implements Runnable{
    File file;
    BufferedReader in;
    AnchorPane pane;
    public HashMap<Integer,PicInfo> pics=new HashMap<Integer,PicInfo>();

    public Replay(File file, AnchorPane pane) {

        try{in = new BufferedReader(new InputStreamReader(new FileInputStream(file),"Unicode"));}
        catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        this.pane=pane;
        String t= null;
        try {
            t = in.readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
        String[] strA=t.split("\t");
        for(String s :strA)
        {
            String[] a=s.split("_");
            int id=Integer.parseInt(a[0]);
            String Url=a[1];
            int Hp=Integer.parseInt(a[2]);
            int x=Integer.parseInt(a[3]);
            int y=Integer.parseInt(a[4]);
            Position z=new Position(x,y);
            initPic(id,Url,Hp,z);
        }
        showadd();
    }

    @Override
    public void run() {
        String t=null;
        try {
            t = in.readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
        while(t!=null)
        {

            String[] strA=t.split("\t");
            for(String s :strA)
            {
                String[] a=s.split("_");
                int id=Integer.parseInt(a[0]);
                int Hp=Integer.parseInt(a[1]);
                int x=Integer.parseInt(a[2]);
                int y=Integer.parseInt(a[3]);
                Position z=new Position(x,y);
                PicInfo tr=pics.get(id);
                Platform.runLater(new Runnable(){
                    public void run(){
                        if(tr.view==null)
                        {
                            System.out.print("wrong id="+id+"\n");
                        }else
                        {
                            System.out.print("wtf"+id+"\n");
                        }
                        ImageView cView = tr.view;
                        Label bView = tr.l;
                        Timeline t = new Timeline();
                        t.getKeyFrames().addAll(
                                new KeyFrame(Duration.ZERO,new KeyValue(cView.xProperty(),tr.position.x*50)),
                                new KeyFrame(new Duration(500),new KeyValue(cView.xProperty(), z.x *50)),
                                new KeyFrame(Duration.ZERO,new KeyValue(cView.yProperty(),tr.position.y*50+10)),
                                new KeyFrame(new Duration(500),new KeyValue(cView.yProperty(),z.y*50+10)),
                                new KeyFrame(Duration.ZERO,new KeyValue(bView.translateXProperty(),tr.position.x *50+5)),
                                new KeyFrame(new Duration(500),new KeyValue(bView.translateXProperty(),z.x *50+5)),
                                new KeyFrame(Duration.ZERO,new KeyValue(bView.translateYProperty(),tr.position.y*50-6)),
                                new KeyFrame(new Duration(500),new KeyValue(bView.translateYProperty(),z.y*50-6))

                        );

                        t.play();
                        tr.position=z;
                    }
                });
            }
            try {
                TimeUnit.MILLISECONDS.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            try {
                t = in.readLine();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void initPic(int id,String Url,int Hp,Position position)
    {
        PicInfo pi=new PicInfo();

       // pi.id=id;

        ImageView t= new ImageView();
        Image image = new Image(Url);
        t.setImage(image);
        pi.view=t;

        Label l;
        l=new Label(Hp+"");
        l.setTextFill(Color.GREEN);
        l.setFont(new Font("Arial", 16));
        l.setWrapText(true);
        pi.l=l;

        pi.position=position;

        pi.Hp=Hp;

        pics.put(id,pi);
    }

    public void showadd()
    {
        for(PicInfo pi: pics.values()){
           pane.getChildren().add(pi.view);
           pane.getChildren().add(pi.l);
           pi.view.setX(pi.position.x*50);
           pi.view.setY(pi.position.y*50+10);
           pi.view.setFitHeight(1000/BattleGround.N-10);
           pi.view.setFitWidth(1000/BattleGround.M);
           pi.l.setTranslateX(pi.position.x*50+5);
           pi.l.setTranslateY(pi.position.y*50-6);
        }
    }
  /*  public void Replay(File file, AnchorPane pane) throws IOException
    {
        in = new BufferedReader(new InputStreamReader(new FileInputStream(file),"Unicode"));
        this.pane=pane;
        String t= null;
        try {
            t = in.readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
        String[] strA=t.split(" ");
        for(String s :strA)
        {
            String[] a=s.split("_");
            int id=Integer.parseInt(a[0]);
            String Url=a[1];
            int Hp=Integer.parseInt(a[2]);
            int x=Integer.parseInt(a[3]);
            int y=Integer.parseInt(a[4]);
            Position z=new Position(x,y);
            initPic(id,Url,Hp,z);
        }
        showadd();
    }*/
}
