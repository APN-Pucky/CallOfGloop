package de.neuwirthinformatik.Alexander.CoG.Person;
import GLOOP.*;
public class Hand extends PM
{
    GLPersonNC p;
    double x;
    double y;
    double z;
    double r;
    double sz;
    GLZylinder zylinder;
    
    public Hand(GLPersonNC p, double x, double y, double z, double r, double sz)
    {
        this.p = p;
        this.x = x;
        this.y = y;
        this.z = z;
        this.r = r;
        this.sz = sz;
        zylinder = new GLZylinder(x,y,z,r,sz);
        add(zylinder);
    }
    
    public void update()
    {
        
    }
}
