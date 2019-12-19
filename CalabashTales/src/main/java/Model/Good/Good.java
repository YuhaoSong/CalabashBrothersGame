package Model.Good;

import Model.World.Attributes;
import Model.World.Group;
import Model.World.Position;
import Model.World.Tile;

public class Good extends Group {
    Grandpa leader;
    public Good(Tile y[][])
    {
        super();
        Position x=new Position(5,2);
        Attributes A=new Attributes(100,100,20,2,Attributes.livingStatus.live,Attributes.Group.good);
        A.SetURL("pic/GrandPa.png");
        leader=new Grandpa(x,A);
        members.add(leader);
        CalabashBrothers CB[]=new CalabashBrothers[7];
        leader.plantCalabash(CB,7);
        for(int i=0;i<7;i++)
        {
            members.add(CB[i]);
        }
        leader.StartCommand(7,CB);
        leader.T1();
    }

}
