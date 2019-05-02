package de.neuwirthinformatik.Alexander.CoG.Person;

import GLOOP.GLQuader;

public class Scope extends PM
{
	public Scope(GLPersonC p, double x,double y , double z)
	{
		GLQuader q1= new GLQuader(x+0.5,y,z,0.1,0.05,0);
		GLQuader q2= new GLQuader(x,y+0.5,z,0.05,0.1,0);
		GLQuader q3= new GLQuader(x-0.5,y,z,0.1,0.05,0);
		GLQuader q4= new GLQuader(x,y-0.5,z,0.05,0.1,0);
		q1.setzeFarbe(0,	255,	0);
		q2.setzeFarbe(0,	255,	0);
		q3.setzeFarbe(0,	255,	0);
		q4.setzeFarbe(0,	255,	0);
		q1.setzeSelbstleuchten(0, 255, 0);
		q2.setzeSelbstleuchten(0, 255, 0);
		q3.setzeSelbstleuchten(0, 255, 0);
		q4.setzeSelbstleuchten(0, 255, 0);
		add(q1);
		add(q2);
		add(q3);
		add(q4);
	}
}
