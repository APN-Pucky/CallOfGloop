package de.neuwirthinformatik.Alexander.CoG.Person;
import GLOOP.*;
import java.util.*;
//index 0 -> main
public class PM
{
    private ArrayList<GLObjekt> objects = new ArrayList<GLObjekt>();

    private double rotx=0,roty=0,rotz=0;
    
    public int add(GLObjekt o)
    {
        objects.add(o);
        return objects.size();
    }
    
    public void remove(int i)
    {
        objects.remove(i);
    }
    
    public void remove(GLObjekt o)
    {
        objects.remove(o);
    }
    
    //...GLObjekt Methoden
    public double gibX()
    {
        return objects.get(0).gibX();
    }
    
    public double gibY()
    {
        return objects.get(0).gibY();
    }
    
    public double gibZ()
    {
        return objects.get(0).gibZ();
    }
    
    public double gibRotX()
    {
        return rotx;
    }
    
    public double gibRotY()
    {
        return roty;
    }
    
    public double gibRotZ()
    {
        return rotz;
    }
    
    public void dreheDich(double ax, double ay, double az)
    {
    	rotx+=ax;
    	roty+=ay;
    	rotz+=az;
        for(GLObjekt o : objects)
        {
            o.drehe(ax,ay,az);
        }
    }
    
    public void dreheDich(double ax, double ay, double az, double x, double y, double z)
    {
    	rotx+=ax;
    	roty+=ay;
    	rotz+=az;
        for(GLObjekt o : objects)
        {
            o.drehe(ax,ay,az,x,y,z);
        }
    }
    
    public void verschiebe(double x, double y, double z)
    {
        for(GLObjekt o : objects)
        {
            o.verschiebe(x,y,z);
        }
    }
    
    public void setzePosition(double x, double y, double z)
    {
        verschiebe(x-gibX(),y-gibY(),z-gibZ());
    }
    
    public boolean isMember(GLObjekt obj)
    {

        for(GLObjekt o : objects)
        {
            if(o==obj)return true;
        }
        return false;
    }
    
    public void setzeFarbe(int r, int g, int b)
    {
    	for(GLObjekt o : objects)
    	{
    		o.setzeFarbe(r,g,b);
    	}
    }
    
}
