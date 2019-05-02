package de.neuwirthinformatik.Alexander.CoG.Person;
import java.util.*;

import GLOOP.GLObjekt;
//main -> 0
public class PMC extends PM
{
    private ArrayList<PM> pms = new ArrayList<PM>();
    
    public int add(PM pm)
    {
        pms.add(pm);
        return pms.size();
    }
    
    public int add(PM[] pma)
    {
        int r = -1;
        for(PM pm : pma)
        {
            r = add(pm);
        }
        return r;
    }
    
    public void remove(int i)
    {
        pms.remove(i);
    }
    
    public void remove(PM pm)
    {
        pms.remove(pm);
    }
    
    //PersonMembers
    //ggf synchronized gets
    public double gibX()
    {
        return pms.get(0).gibX();
    }
    
    public double gibY()
    {
        return pms.get(0).gibY();
    }
    
    public double gibZ()
    {
        return pms.get(0).gibZ();
    }
    
    public double gibRotX()
    {
        return pms.get(0).gibRotX();
    }
    
    public double gibRotY()
    {
        return pms.get(0).gibRotY();
    }
    
    public double gibRotZ()
    {
        return pms.get(0).gibRotZ();
    }
    
    public synchronized void dreheDich(double ax, double ay, double az)
    {
        for(PM pm : pms)
        {
            pm.dreheDich(ax,ay,az,gibX(),gibY(),gibZ());
        }
    }
    
    public synchronized void verschiebe(double x, double y, double z)
    {
        for(PM pm : pms)
        {
            pm.verschiebe(x,y,z);
        }
    }
    
    public synchronized void setzePosition(double x, double y, double z)
    {
        verschiebe(x-gibX(),y-gibY(),z-gibZ());
    }
    
    public synchronized void setzeDrehung(double ax, double ay, double az)
    {
        dreheDich(ax-gibRotX(),ay-gibRotY(),az-gibRotZ());
    }
    
    public boolean isMember(GLObjekt o)
    {

        for(PM pm : pms)
        {
            if(pm.isMember(o))return true;
        }
        return false;
    }
    
    public void setzeFarbe(int r, int g, int b)
    {
    	for(PM pm : pms)
    	{
    		pm.setzeFarbe(r,g,b);
    	}
    }
}
