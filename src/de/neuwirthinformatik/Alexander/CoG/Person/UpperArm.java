package de.neuwirthinformatik.Alexander.CoG.Person;
import GLOOP.*;
public class UpperArm extends PM
{
    GLPersonNC p;
    double x;
    double y;
    double z;
    double r;
    double sy;
    GLZylinder zylinder;
    
    public UpperArm(GLPersonNC p, double x, double y, double z, double r, double sy)
    {
        this.p = p;
        this.x = x;
        this.y = y;
        this.z = z;
        this.r = r;
        this.sy = sy;
        zylinder = new GLZylinder(x,y,z,r,sy);
        zylinder.dreheDich(90,0,0);
        add(zylinder);
    }
    
    public void update()
    {
        
    }
}
