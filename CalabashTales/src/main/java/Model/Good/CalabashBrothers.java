package Model.Good;

import Model.World.Attributes;
import Model.World.Lives;
import Model.World.Position;
import Model.World.Tile;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;


public class CalabashBrothers extends Lives
{
    private info ID;
    public CalabashBrothers(Position x, Attributes z,int i)
    {
        position=x;
        if(ground[x.x][x.y].GetIsOccupied()==false)
        {
            ground[x.x][x.y].SetALL(true,this);
        }
        attributes=z;
        this.myAppearance = new ImageView();
        Image image = new Image(z.URL);
        //System.out.print(z.URL);
        myAppearance.setImage(image);
        myHp=new Label(attributes.Hp+"");
        myHp.setTextFill(Color.GREEN);
        SetPic();
        switch(i)
        {
            case 1: ID=info.first;break;
            case 2: ID=info.second;break;
            case 3: ID=info.third;break;
            case 4: ID=info.fourth;break;
            case 5: ID=info.fifth;break;
            case 6: ID=info.sixth;break;
            case 7: ID=info.seventh;break;


        }
    }

}
