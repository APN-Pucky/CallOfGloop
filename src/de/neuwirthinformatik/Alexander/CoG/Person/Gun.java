package de.neuwirthinformatik.Alexander.CoG.Person;

import GLOOP.GLZylinder;

public class Gun extends PM 
{
	GLZylinder zy1;
	GLZylinder zy2;
	GLZylinder zy3;
	public final double range=3000;
	boolean cooldown = false;
	
	public Gun(GLPersonNC p,double x,double y,double z)
	{
		zy1 = new GLZylinder(x,y-5,z+3,1,10);
		zy1.drehe(90, 0, 0);
		add(zy1);
		zy2 = new GLZylinder(x,y-7,z,0.5,5);
		add(zy2);
		zy3 = new GLZylinder(x,y-5-range/2,z+3,0.75,range);
		zy3.setzeSichtbarkeit(false);
		zy3.setzeSelbstleuchten(0, 0, 255);
		zy3.drehe(90, 0, 0);
		zy3.setzeFarbe(0, 0, 255);
		add(zy3);
	}

	public boolean shoot() 
	{
		if(cooldown)return false;
		zy3.setzeSichtbarkeit(true);
		cooldown = true;
		new Thread(new Cooldown()).start();
		return true;
	}
	
	class Cooldown implements Runnable
	{

		@Override
		public void run() 
		{
			try {
				Thread.sleep(200);
			} catch (InterruptedException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			zy3.setzeSichtbarkeit(false);
			try {
				Thread.sleep(500);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			cooldown = false;
		}
	}
}
