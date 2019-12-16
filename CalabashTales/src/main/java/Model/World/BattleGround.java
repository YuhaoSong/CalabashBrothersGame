package Model.World;

import Model.Bad.Scorpion;
import Model.Bad.Sidekicks;
import Model.Bad.Snake;
import Model.Good.CalabashBrothers;
import Model.Good.Grandpa;
import Model.World.Tile;

public class BattleGround {

    public static final int M=20;
    public static final int N=20;
    public static Tile[][] ground=new Tile[M][N];
    public BattleGround()
    {
        for(int i=0;i<M;i++)
        {
            for(int j=0;j<N;j++)
            {
                ground[i][j]=new Tile();
            }

        }
    }
    public void print()
    {
        for(int i=0;i<16;i++)
        {
            for(int j=0;j<16;j++)
            {
                if(ground[i][j].GetIsOccupied()==true)
                {
                    System.out.print("@");
                }
                else
                {
                    System.out.print(" ");
                }
            }
            System.out.print("\n");
        }
    }
}
