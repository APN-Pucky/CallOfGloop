package de.neuwirthinformatik.Alexander.CoG.IO;

import static de.neuwirthinformatik.Alexander.CoG.Util.ArrayUtil.*;
import static de.neuwirthinformatik.Alexander.CoG.Util.Log.log;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.Random;

import javax.swing.JOptionPane;

import GLOOP.GLQuader;
import GLOOP.Sys;
import de.neuwirthinformatik.Alexander.CoG.Client;
import de.neuwirthinformatik.Alexander.CoG.GLOBAL;
import de.neuwirthinformatik.Alexander.CoG.Server;
import de.neuwirthinformatik.Alexander.CoG.Person.GLPersonC;
import de.neuwirthinformatik.Alexander.CoG.Person.GLPersonNC;
import de.neuwirthinformatik.Alexander.CoG.Util.BAC;

public class UDPClient implements Runnable
{
	public byte[] PCID = new byte[]{-1};
	public DatagramSocket ds;
	byte[] receiveData;
	byte[] sendData;
	String server;
	InetAddress ip;
	int port;
	ReceiveThread recthread;
	SendThread sendthread;
	
	public UDPClient(String server, int port)
	{
		this.server = server;
		this.port = port;
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
		boolean go = true;
		while(go)
		{
			go = false;
			try {
				ds = new DatagramSocket((new Random()).nextInt(2000)+3071);
			} catch (SocketException e) {
				// TODO Auto-generated catch block
				go = true;
			}
		}
		ip = InetAddress.getByName(server);
		receiveData = new byte[2048];
        sendData = new byte[2048];
        
         new Thread(recthread =new ReceiveThread()).start();
        //connect
        ds.send(new DatagramPacket(new byte[]{0},1,ip,port));
        
        new Thread(sendthread = new SendThread()).start();
	}
	
	public void stop()
	{
		byte[] data = append(new byte[]{6},PCID);
		send(data);
		if(recthread !=null)recthread.cancel();
		if(sendthread !=null)sendthread.cancel();
	}
	public void send(byte[] data)
	{
		try {
			if(ds !=null)ds.send(new DatagramPacket(data,data.length,ip,port));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void position()
	{
		byte[] data = append(new byte[]{1},append(PCID,
				append(BAC.toByteArray(GLOBAL.pc.gibX()),
						append(BAC.toByteArray(GLOBAL.pc.gibY()),
								append(BAC.toByteArray(GLOBAL.pc.gibZ()),
										append(BAC.toByteArray(GLOBAL.pc.gibRotX()),
												append(BAC.toByteArray(GLOBAL.pc.gibRotY()),
														BAC.toByteArray(GLOBAL.pc.gibRotZ())
												)
										)
								)
						)
				)
		));
		send(data);
	}
	
	public void kill(int id)
	{
		//System.out.println("kill " + PCID[0]);
		byte[] data = append(new byte[]{5},append(PCID,
				new byte[]{(byte)id}));
		send(data);
	}
	
	public void shoot()
	{
		byte[] data = append(new byte[]{3},PCID);
		send(data);
		
	}
	
	public void updateMove()
	{
		byte[] data = append(new byte[]{2},append(PCID,
				BAC.toByteArray(GLOBAL.pc.move)));
		send(data);
		
	}
	
	class ReceiveThread implements Runnable
	{
		boolean running = true;

		@Override
		public void run() {
			// TODO Auto-generated method stub
			while(running)
			{
				try {
					receive();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		
		void receive() throws IOException
		{
			DatagramPacket dp = new DatagramPacket(receiveData,receiveData.length);
        	ds.receive(dp);
        	//update GLPersons
        	byte[] data = dp.getData();
        	log("rcv: " + data[1]);
        	/*for(byte d : data)
        	{
        		System.out.print(d + " ");
        	}
        	System.out.println("--------------------");*/
        	switch(data[0])
        	{
        	case(1):handlepos(data);break;
        	case(2):handleanim(data);break;
        	case(3):handleshoot(data);break;
        	case(4):handlemap(data);break;
        	case(5):handlekill(data);break;
        	case(6):GLOBAL.pncs[data[1]].setzePosition(0, -100, 0);break;
        	case(7):PCID[0]=data[1];break;
        	}
        	log("PCID: " + PCID[0]);
		}
		
		public void cancel()
		{
			running = false;
		}
		
		void handlemap(byte[] data)
		{
			int n = BAC.toInt(sub(data,1,4));
			//System.out.println(n);
			for(int i =0; i < n;i++)
			{
				new GLQuader(BAC.toDouble(sub(data,i*8*6+8*0+5,8)),BAC.toDouble(sub(data,i*8*6+8*1+5,8)),BAC.toDouble(sub(data,i*8*6+8*2+5,8)),BAC.toDouble(sub(data,i*8*6+8*3+5,8)),BAC.toDouble(sub(data,i*8*6+8*4+5,8)),BAC.toDouble(sub(data,i*8*6+8*5+5,8)));
			}
		}
		
		void handlekill(byte[] data)
		{
			//GLPersonNC p = GLOBAL.pncs[data[1]];
			//p.setzeFarbe(255, 0, 0);
			if(PCID[0]==data[2])
			{
				//System.out.println("killed");
				GLOBAL.pc.alive = false;
				stop();
				Sys.beenden();
				
				/*
				
				Object[] options = {"Respawn",
				                    "Exit"};
				int n = JOptionPane.showOptionDialog(null,
				    "You have been killed?",
				    "Call of Gloop",
				    JOptionPane.YES_NO_OPTION,
				    JOptionPane.QUESTION_MESSAGE,
				    null,
				    options,
				    options[1]);
				switch(n)
				{
				case(0):Client._this.start();break;
				case(1):Sys.beenden();break;
				}
				//stop();
		        //Sys.beenden();
				//System.exit(0);*/
			}
		}
		
		void handleshoot(byte[] data)
		{
			GLPersonNC p = GLOBAL.pncs[data[1]];
			p.pm_gun.shoot();
		}
		
		void handleanim(byte[] data)
		{
			GLPersonNC p = GLOBAL.pncs[data[1]];
			p.move = BAC.toInt(sub(data,2,4));
			//System.out.println(p.move +" "+p.lastmove);
		}
		
		void handlepos(byte[] data)
		{
			if(PCID[0]==data[1])
			{
				GLPersonC p = GLOBAL.pc;
				log("mov "+data[1]+" "+ BAC.toDouble(sub(data,2,8))+" "+BAC.toDouble(sub(data,2+8,8))+" "+BAC.toDouble(sub(data,2+8+8,8)));
				p.setzePosition(BAC.toDouble(sub(data,2,8)),BAC.toDouble(sub(data,2+8,8)),BAC.toDouble(sub(data,2+8+8,8)));
				p.setzeDrehung(/*BAC.toDouble(sub(data,2+8*3,8))*/0,BAC.toDouble(sub(data,2+8*4,8)),/*BAC.toDouble(sub(data,2+8*5,8))*/0);
			}
			else
			{
				GLPersonNC p = GLOBAL.pncs[data[1]];
				log("mov"+data[1]+" "+ BAC.toDouble(sub(data,2,8))+" "+BAC.toDouble(sub(data,2+8,8))+" "+BAC.toDouble(sub(data,2+8+8,8)));
				p.setzePosition(BAC.toDouble(sub(data,2,8)),BAC.toDouble(sub(data,2+8,8)),BAC.toDouble(sub(data,2+8+8,8)));
				p.setzeDrehung(/*BAC.toDouble(sub(data,2+8*3,8))*/0,BAC.toDouble(sub(data,2+8*4,8)),/*BAC.toDouble(sub(data,2+8*5,8))*/0);
			}
		}
	}
	
	class SendThread implements Runnable
	{
		boolean running = true;
		
		@Override
		public void run() {
			// TODO Auto-generated method stub
			log("pre");
			while(PCID[0]==-1){};
			log("post");
			while(running)
			{
				try {
					send();
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			
		}
		
		public void cancel()
		{
			running = false;
		}
		
		void send() throws InterruptedException
		{
			//send pos and rot
			Thread.sleep(50);
			log("senddata");
			position();
			updateMove();
		}
		
	}
}
