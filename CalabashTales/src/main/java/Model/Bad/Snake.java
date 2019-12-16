package Model.Bad;

import Model.World.Attributes;
import Model.World.Lives;
import Model.World.Position;
import Model.World.Tile;

public class Snake extends Lives {
    public Snake(Position x,  Attributes z)
    {
        position=x;
         if(ground[x.x][x.y].GetIsOccupied()==false)
        {
            ground[x.x][x.y].SetALL(true,this);
        }
        attributes=z;
    }
}
