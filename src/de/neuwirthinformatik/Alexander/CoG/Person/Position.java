package de.neuwirthinformatik.Alexander.CoG.Person;

import GLOOP.GLKugel;

public class Position extends PM
{
	
	public Position(GLPersonNC p, double x, double y ,double z)
	{
		add(new GLKugel(x,y,z,0));
	}
}
