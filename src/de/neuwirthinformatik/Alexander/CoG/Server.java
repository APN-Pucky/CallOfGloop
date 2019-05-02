package de.neuwirthinformatik.Alexander.CoG;

import javax.swing.JOptionPane;

import de.neuwirthinformatik.Alexander.CoG.IO.UDPServer;

public class Server 
{
	public Server()
	{
		
	}
	
	public void start()
	{
    	new Thread(new UDPServer()).start();
    	JOptionPane.showMessageDialog(null, "Stop Server");
    	System.exit(0);
	}
}
