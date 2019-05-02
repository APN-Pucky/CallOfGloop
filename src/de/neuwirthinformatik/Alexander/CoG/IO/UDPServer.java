package de.neuwirthinformatik.Alexander.CoG.IO;

import static de.neuwirthinformatik.Alexander.CoG.Util.ArrayUtil.append;
import static de.neuwirthinformatik.Alexander.CoG.Util.Log.log;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.util.Random;

import de.neuwirthinformatik.Alexander.CoG.GLOBAL;
import de.neuwirthinformatik.Alexander.CoG.Util.BAC;


public class UDPServer implements Runnable
{
	DatagramSocket ds;
	byte[] receiveData;
	byte[] sendData;
	
	InetAddress[] ips = new InetAddress[8];
	int[] ports = new int[8];
	
	public static final int spawn_area=3000;
		
	public UDPServer()
	{
		try {
			ds = new DatagramSocket(3070);
		} catch (SocketException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		receiveData = new byte[8*6];
        sendData = new byte[8*6];
	}
	
	public void run()
	{
		try {
			start();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void start() throws IOException
	{
        while(true)
        {
        	DatagramPacket dp = new DatagramPacket(receiveData,receiveData.length);
        	ds.receive(dp);
        	if(connected(dp))
        	{
        		forward(dp);
        		log("frwd: " + dp.getAddress().toString());
            	if(dp.getData()[0] == 6)disconnect(dp);
        	}
        	else
        	{
        		if(dp.getData()[0]==0 && connect(dp))
        		{
        			forward(dp);
        			byte[] data = append(new byte[]{1},append(new byte[]{getID(dp)},
        					append(BAC.toByteArray((double)new Random().nextInt(spawn_area/2)-spawn_area),
        							append(BAC.toByteArray((double)30),
        									append(BAC.toByteArray((double)new Random().nextInt(1000)-500),
        											append(BAC.toByteArray((double)0),
        													append(BAC.toByteArray((double)0),
        															BAC.toByteArray((double)0)
        													)
        											)
        									)
        							)
        					)
        			));
        			forward(new DatagramPacket(data,data.length,null,1000));
        			
        			double[] idata = new double[]{	//0,0,0,110,190,106,
        											//30,0,20,5,30,10,
        											//130,0,-520,445,30,10,
        											//-30,0,-320,335,30,10,
        											//230,0,1120,5,30,510,
        											//-630,0,320,5,530,310,
        											//430,0,-250,56,630,10
        											//middle, spikes
        											0,0,0,10,300,10,
        											1000,0,0,10,200,10,
        											2000,0,0,10,200,10,
        											-1000,0,0,10,200,10,
        											-2000,0,0,10,200,10,
        											0,0,1000,10,200,10,
        											0,0,2000,10,200,10,
        											0,0,-1000,10,200,10,
        											0,0,-2000,10,200,10,
        											//cubes
        											100,50,100,10,10,10,
        											-100,50,100,10,10,10,
        											100,50,-100,10,10,10,
        											-100,50,-100,10,10,10,
        											1000,50,1000,10,10,10,
        											-1000,50,1000,10,10,10,
        											1000,50,-1000,10,10,10,
        											-1000,50,-1000,10,10,10,
        											2000,50,2000,10,10,10,
        											-2000,50,2000,10,10,10,
        											2000,50,-2000,10,10,10,
        											-2000,50,-2000,10,10,10,
        											//plates
        											1000,0,0,2,100,1000,
        											-1000,0,0,2,100,1000,
        											0,0,1000,1000,100,2,
        											0,0,-1000,1000,100,2,
        											
        											3000,0,0,5,50,2000,
        											-3000,0,0,5,50,2000,
        											0,0,3000,2000,50,5,
        											0,0,-3000,2000,50,5,
        											//cross
        											0,0,0,0,40,10000,
        											0,0,0,10000,40,0,
        											
        										};
        			data = new byte[]{};
        			for(double i : idata)
        			{
        				data=append(data,BAC.toByteArray(i));
        			}
        			forward(new DatagramPacket(append(new byte[]{4},append(BAC.toByteArray((int)(idata.length/6)),data)),data.length+2,null,1000));
        		}
        		else
        		{
        			//Server full
        		}
        	}
        }
	}
	
	public byte getID(DatagramPacket dp)
	{
		for(byte i =0; i < ips.length;i++)
		{
			if(ips[i]!=null && check(dp,i))
			{
				return i;
			}
		}
		return -1;
	}
	
	public void forward(DatagramPacket dp) throws IOException
	{
		for(int i =0; i < ips.length;i++)
		{
			if(ips[i]!=null && !check(dp,i))
			{
				byte[] data = dp.getData();
				ds.send(new DatagramPacket(data,data.length,ips[i],ports[i]));
			}
		}
	}
	
	public boolean connected(DatagramPacket dp)
	{
		for(int i =0; i < ips.length;i++)
		{
			if(ips[i]!=null && check(dp,i))return true;
		}
		return false;
	}
	
	public void disconnect(DatagramPacket dp)
	{
		for(int i = 0; i<ips.length;i++)
		{
			if(ips[i] != null && check(dp,i))
			{
				ips[i]=null;
				ports[i]=0;
				return;
			}
		}
	}
	
	public boolean connect(DatagramPacket dp) throws IOException
	{
		for(int i = 0; i<ips.length;i++)
		{
			if(ips[i]==null)
			{
				System.out.println("Connect: " +dp.getAddress().toString() + ":"+dp.getPort());
				ips[i]=dp.getAddress();
				ports[i]=dp.getPort();
				ds.send(new DatagramPacket(new byte[]{7,(byte)i},2,ips[i],ports[i]));
				return true;
			}
		}
		return false;
	}
	
	public boolean check(DatagramPacket dp,int i)
	{
		return ips[i].equals(dp.getAddress()) && ports[i]==dp.getPort();
	}
}
