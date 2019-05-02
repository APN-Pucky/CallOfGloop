package de.neuwirthinformatik.Alexander.CoG.Person;
import GLOOP.*;

public class GLPersonC extends GLPersonNC
{
	public GLKameraC pm_cam;
	public Scope pm_scope;
	public boolean alive = true;
    
    public GLPersonC(double x, double y, double z, double body_r, double body_sy,double neck_r, double neck_sy, double head_r, double leg_r, double upperleg_sy, double lowerleg_sy,double foot_r, double foot_sz, double arm_r, double upperarm_sy, double lowerarm_sy, double hand_r, double hand_sz)
    {
        //head_r/2 no /2
    	super( x,  y,  z,  body_r,  body_sy, neck_r,  neck_sy,  head_r,  leg_r,  upperleg_sy,  lowerleg_sy, foot_r,  foot_sz,  arm_r,  upperarm_sy,  lowerarm_sy,  hand_r,  hand_sz);
    	pm_cam = new GLKameraC(this,x,y+body_sy/2+neck_sy+head_r/2,z);
    	pm_scope = new Scope(this,x,y+body_sy/2+neck_sy+head_r/2,z+10);
        add(pm_cam);
        add(pm_scope);
    }
    
    public synchronized void move(double x, double y, double z)
    {
    	GLVektor v = pm_cam.gibRichtung();
    	v.normalisiere();
    	v.multipliziere(z);
    	this.verschiebe(v.x, v.y, v.z);
    	v = pm_cam.gibRichtung();
    	v.normalisiere();
    	v.drehe(0, 90, 0);
    	v.multipliziere(x);
    	this.verschiebe(v.x, v.y, v.z);
    	v = pm_cam.gibRichtung();
    	v.normalisiere();
    	v.drehe(90, 0, 0);
    	v.multipliziere(y);
    	this.verschiebe(v.x, v.y, v.z);
    }
    
    public void update()
    {
        //dreheDich(0,0.1,0);
    }
}
