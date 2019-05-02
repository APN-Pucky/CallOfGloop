package de.neuwirthinformatik.Alexander.CoG.Person;

import GLOOP.GLObjekt;
import GLOOP.GLVektor;

public class ViewDirection extends PM {

	GLVektor v;
    public ViewDirection()
    {
    	v = new GLVektor(0,0,1);
    }
    //...GLObjekt Methoden
    public double gibX()
    {
        return v.x;
    }
    
    public double gibY()
    {
        return v.y;
    }
    
    public double gibZ()
    {
        return v.z;
    }
    
    public double gibRotX()
    {
        return v.x;
    }
    
    public double gibRotY()
    {
        return v.y;
    }
    
    public double gibRotZ()
    {
        return v.z;
    }
    
    public void dreheDich(double ax, double ay, double az)
    {
    	v.drehe(ax, ay, az);
    }
    
    public void dreheDich(double ax, double ay, double az, double x, double y, double z)
    {
    	v.drehe(ax, ay, az);
    }
    
    public void verschiebe(double x, double y, double z)
    {
        
    }
    
    public void setzePosition(double x, double y, double z)
    {
        
    }
}
