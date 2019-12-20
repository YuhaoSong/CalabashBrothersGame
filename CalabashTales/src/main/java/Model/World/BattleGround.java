package Model.World;

import Model.Bad.Scorpion;
import Model.Bad.Sidekicks;
import Model.Bad.Snake;
import Model.Good.CalabashBrothers;
import Model.Good.Grandpa;
import Model.World.Tile;
import org.omg.Messaging.SYNC_WITH_TRANSPORT;

import java.io.*;
import java.util.*;
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
        HashMap<Integer, Boolean> count=new HashMap<Integer,Boolean>();
        for(int i=0;i<M;i++)
        {
            for(int j=0;j<N;j++)
            {
                String in;
                if(ground[i][j].GetIsOccupied()==true)
                {
                    Lives one=ground[i][j].GetWho();
                    in=one.id+"_"+one.attributes.URL+"_"+one.attributes.Hp+"_"+one.position.x+"_"+one.position.y+"\t";
                    count.put(one.id,false);
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
                       // System.out.print(one.attributes.Hp+"\n");
                        //System.out.print(in+"\n");
                        count.put(one.id,true);
                        try {
                            out.write(in);
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
            System.out.print("\n");
            Iterator c=count.entrySet().iterator();
           // for(Map.Entry<Integer, Boolean> entry: count.entrySet())
            while(c.hasNext())
            {
                Map.Entry<Integer, Boolean> entry=(Map.Entry<Integer, Boolean>) c.next();
                if(entry.getValue()==false)
                {
                    try {
                        out.write("die_"+entry.getKey());
                        System.out.print("somebody dieeeeeeeeeeeeeeeeeeeeeeeeeee"+entry.getKey());
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    c.remove();
                }

            }
            for(int key:count.keySet())
            {
               count.put(key,false);
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
