package Model.Good;

import Model.World.*;

public class Grandpa extends Lives {
    private Formation<CalabashBrothers> command;
    public void plantCalabash(CalabashBrothers x[],int seeds)
    {

        for(int i=0;i<seeds;i++)
        {
            Attributes CalabashBrothersA=new Attributes(100,100,20,2,Attributes.livingStatus.live,Attributes.Group.good);
            String url=i+1+"";
            url="pic/"+url+".png";
            CalabashBrothersA.SetURL(url);
            Position temp=new Position(i+2,position.y+2);
            x[i]=new CalabashBrothers(temp,CalabashBrothersA,i+1);
        }
    }
    public void StartCommand(int number,CalabashBrothers x[])
    {
        command=new Formation<CalabashBrothers>(position,number,ground,x);
    }
    public void T1()
    {
        command.T1();
    }
    public void T2()
    {
        command.T2();
    }
    public void T3()
    {
        command.T3();
    }
    public void T4()
    {
        command.T4();
    }
    public void T5()
    {
        command.T5();
    }
    public void T6()
    {
        command.T6();
    }
    public void T7()
    {
        command.T7();
    }
    public void T8()
    {
        command.T8();
    }
    public Grandpa(Position x,Attributes z)
    {
        position=x;
        if(ground[x.x][x.y].GetIsOccupied()==false)
        {
            ground[x.x][x.y].SetALL(true,this);
        }
        attributes=z;
    }
}
