package de.neuwirthinformatik.Alexander.CoG.Person;
import GLOOP.*;
public class Knee extends PM
{
    GLPersonNC p;
    double x;
    double y;
    double z;
    double r;
    GLKugel kugel;
    
    public Knee(GLPersonNC p, double x, double y, double z, double r)
    {
        this.p = p;
        this.x = x;
        this.y = y;
        this.z = z;
        this.r = r;
        kugel = new GLKugel(x,y,z,r);
        add(kugel);
    }
    
    public void update()
    {
        
    }
}
