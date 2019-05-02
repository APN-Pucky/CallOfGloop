package de.neuwirthinformatik.Alexander.CoG.Util;

public class Log {
	static boolean log = false;
	public static void log(String s)
	{
		if(log)System.out.println("[Log]: " + s);
	}
}
