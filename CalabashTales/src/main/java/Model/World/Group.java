package Model.World;

import Model.Good.CalabashBrothers;
import Model.Good.Grandpa;

import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Group implements Runnable{
    public Set<Lives> members=new HashSet<Lives>();
    public Group()
    {

    }

    private void initThreads(){
        ExecutorService exec = Executors.newCachedThreadPool();
        for(Lives life: members){
            exec.execute(life);
        }
        exec.shutdown();
    }
    public boolean stillAlive()
    {
        for(Lives life: members){
            if(life.attributes.living== Attributes.livingStatus.live)
            {
                return true;
            }
        }
        return false;
    }
    @Override
    public void run()
    {
        initThreads();
    }
}
