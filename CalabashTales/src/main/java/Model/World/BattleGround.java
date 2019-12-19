package Model.World;

import Model.Bad.Scorpion;
import Model.Bad.Sidekicks;
import Model.Bad.Snake;
import Model.Good.CalabashBrothers;
import Model.Good.Grandpa;
import Model.World.Tile;

import java.io.*;
import java.util.concurrent.TimeUnit;

public class BattleGround implements  Runnable{
    BufferedWriter out;
    public static final int M=20;
    public static final int N=20;
    public static Tile[][] ground=new Tile[M][N];
    public static boolean end=false;
    public BattleGround() throws FileNotFoundException, UnsupportedEncodingException {
        out= new BufferedWriter(new OutputStreamWriter(new FileOutputStream("d:\\2.txt"),"Unicode"));
        for(int i=0;i<M;i++)
        {
            for(int j=0;j<N;j++)
            {
              ground[i][j]=new Tile();
            }
        }

    }

    public void setend(boolean x)
    {
        end=x;
    }
    @Override
    public void run() {
        for(int i=0;i<M;i++)
        {
            for(int j=0;j<N;j++)
            {
                String in;
                if(ground[i][j].GetIsOccupied()==true)
                {
                    Lives one=ground[i][j].GetWho();
                    in=one.id+"_"+one.attributes.URL+"_"+one.attributes.Hp+"_"+one.position.x+"_"+one.position.y+"\t";
                    try {
                        out.write(in);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
        try {
            out.newLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            TimeUnit.MILLISECONDS.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        while(end!=true)
        {
            for(int i=0;i<M;i++)
            {
                for(int j=0;j<N;j++)
                {
                    String in;
                    if(ground[i][j].GetIsOccupied()==true)
                    {
                        Lives one=ground[i][j].GetWho();
                        in=one.id+"_"+one.attributes.Hp+"_"+one.position.x+"_"+one.position.y+"\t";
                        try {
                            out.write(in);
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
            try {
                out.newLine();
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                TimeUnit.MILLISECONDS.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }
}
