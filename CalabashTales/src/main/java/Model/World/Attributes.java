package Model.World;

public class Attributes
{
    public int maxHp;
    public int Hp;
    public int Ack;
    public int Evade;
    public livingStatus living;
    public Group group;
    public String URL;
    public enum livingStatus{
        live,dead
    }
    public enum Group{
        bad,good
    }
    public Attributes(int maxHPt,int Hpt,int Ackt,int Evadet,livingStatus lt,Group tg,String url)
    {
        maxHp=maxHPt;
        Hp=Hpt;
        Ack=Ackt;
        Evade=Evadet;
        living=lt;
        group=tg;
        URL=url;

    }
    public Attributes(int maxHPt,int Hpt,int Ackt,int Evadet,livingStatus lt,Group tg)
    {
        maxHp=maxHPt;
        Hp=Hpt;
        Ack=Ackt;
        Evade=Evadet;
        living=lt;
        group=tg;
    }
    public boolean SetURL(String url)
    {
        if(URL==null)
        {
            URL=url;
            return true;
        }
        else
        {
            return false;
        }
    }


}
