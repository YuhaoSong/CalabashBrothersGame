package Model.Bad;

import Model.World.Attributes;
import Model.World.Group;
import Model.World.Position;
import Model.World.Tile;

public class Bad extends Group{
    Snake leader;
    Scorpion coleader;
    public Bad(Tile y[][])
    {


            super();
            Position x=new Position(18,10);
            Attributes A=new Attributes(100,100,20,2,Attributes.livingStatus.live,Attributes.Group.bad);
            A.SetURL("pic/Snake.png");
            leader=new Snake(x,A);
            members.add(leader);
            Position x1=new Position(15,2);
            Attributes A1=new Attributes(100,100,20,2,Attributes.livingStatus.live,Attributes.Group.bad);
            A1.SetURL("pic/Scorpion.png");
            coleader=new Scorpion(x1,A1);
            members.add(coleader);
            Sidekicks SD[]=new Sidekicks[7];
            coleader.summonSidekicks(SD,7);
            for(int i=0;i<7;i++)
            {
                members.add(SD[i]);
            }
            coleader.StartCommand(7,SD);
            coleader.T1();


    }
}
