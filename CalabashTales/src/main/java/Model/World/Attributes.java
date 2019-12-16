package Model.World;

public class Attributes
{
    int maxHp;
    int Hp;
    int Ack;
    int Evade;
    livingStatus living;
    Group group;
    String URL;
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
