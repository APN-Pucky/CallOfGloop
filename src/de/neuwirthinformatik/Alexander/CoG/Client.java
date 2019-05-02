package de.neuwirthinformatik.Alexander.CoG;

import GLOOP.GLBoden;
import GLOOP.GLLicht;
import GLOOP.GLMaus;
import GLOOP.GLObjekt;
import GLOOP.GLTastatur;
import GLOOP.Sys;
import de.neuwirthinformatik.Alexander.CoG.IO.UDPClient;
import de.neuwirthinformatik.Alexander.CoG.Person.GLKameraC;
import de.neuwirthinformatik.Alexander.CoG.Person.GLPersonC;
import de.neuwirthinformatik.Alexander.CoG.Person.GLPersonNC;

public class Client 
{
	public static Client _this;
	String serverip;
	GLTastatur tasta;
	GLMaus maus;
	GLPersonC p;
	
	public Client(String server)
	{
		_this = this;
		if(server.equals(""))server="localhost";
		serverip = server;
		p = new GLPersonC(0,30,0,7,20,4,3,5,2,7,5,2,7,2,5,6,2,5);
        GLOBAL.pc = p;
        GLOBAL.pncs[0] = new GLPersonNC(0,-100,0,7,20,4,3,5,2,7,5,2,7,2,5,6,2,5);
        GLOBAL.pncs[1] = new GLPersonNC(0,-100,0,7,20,4,3,5,2,7,5,2,7,2,5,6,2,5);
        GLOBAL.pncs[2] = new GLPersonNC(0,-100,0,7,20,4,3,5,2,7,5,2,7,2,5,6,2,5);
        GLOBAL.pncs[3] = new GLPersonNC(0,-100,0,7,20,4,3,5,2,7,5,2,7,2,5,6,2,5);
        GLOBAL.pncs[4] = new GLPersonNC(0,-100,0,7,20,4,3,5,2,7,5,2,7,2,5,6,2,5);
        GLOBAL.pncs[5] = new GLPersonNC(0,-100,0,7,20,4,3,5,2,7,5,2,7,2,5,6,2,5);
        GLOBAL.pncs[6] = new GLPersonNC(0,-100,0,7,20,4,3,5,2,7,5,2,7,2,5,6,2,5);
        GLOBAL.pncs[7] = new GLPersonNC(0,-100,0,7,20,4,3,5,2,7,5,2,7,2,5,6,2,5);

        tasta = new GLTastatur();
        maus = new GLMaus();
        
        new GLLicht();
        new GLBoden("tmp_ground.jpg");
	}
	
	public void start()
	{
		new Thread(new ClientThread()).start();
	}
	
	class ClientThread implements Runnable
	{
		public void run()
		{
	        //tasta = new GLTastatur();
	        //maus = new GLMaus();
	        UDPClient udpc = new UDPClient(serverip,3070);
	    	new Thread(udpc).start();
	        //new Thread(new Runnable(){public void run(){GLPersonC p3 = new GLPersonC(0,20,0,7,20,4,3,5,2,7,5,2,7,2,5,6,2,5);}}).start();
	        //GLPersonNC p = new GLPersonNC(0,30,0,7,20,4,3,5,2,7,5,2,7,2,5,6,2,5);
	        //new GLSchwenkkamera();
	        //GLPersonNC p2 = new GLPersonNC(0,30,-50,7,20,4,3,5,2,7,5,2,7,2,5,6,2,5);
	        //p2.arms[0].dreheDich(-110,0,0);
	        //p.dreheDich(0, 0, 0);
	    	//GLKameraC k = new GLKameraC();
	    	//GLKamera k = new GLSchwenkkamera(500,500);
	    	//k.setzePosition(p.pm_head.gibX(),p.pm_head.gibY(),p.pm_head.gibZ());
	        //
	        //p.legs[1].dreheDich(-45,0,0);
	        //p.lower_legs[1].dreheDich(20,0,0);
	        int npcount=0;
	        long lasttime=System.currentTimeMillis();
	        
	        //render time based move
	        while(!tasta.esc() && p.alive)
	        {
	           	if(maus.linksklick())
	           	{
	           		p.pm_cam.setActive(true);
	           		if(p.pm_gun.shoot())
	           		{
		           		udpc.shoot();
	               		GLObjekt o = Sys.gibObjekt(GLKameraC.screen_xmid, GLKameraC.screen_ymid);
	               		//System.out.println("shoot");
	               		if(o!=null && Math.sqrt(Math.pow(o.gibX()-p.pm_gun.gibX(),2)+Math.pow(o.gibY()-p.pm_gun.gibY(),2)+Math.pow(o.gibZ()-p.pm_gun.gibZ(),2))<p.pm_gun.range)
	               		{
	                   		for(int i = 0; i < GLOBAL.pncs.length;i++)
	                   		{
	                   			if(GLOBAL.pncs[i].isMember(o))
	                   			{
	                   				udpc.kill(i);
	                           		//System.out.println("kill");
	                   			}
	                   		}               			
	               		}
	           		}
	           	}
	           	
	           	
	           	npcount++;
	           	if(npcount>=5)p.move = 0;
	           	
	           	if(tasta.istGedrueckt('p'))
	           	{
	           		p.pm_cam.setActive(false);
	           	}
	           	if(tasta.istGedrueckt('w'))
	           	{
	           		p.move = 1;
	           		npcount=0;
	          	}
	           	if( tasta.istGedrueckt('s'))
	           	{
	           		p.move = 2;
	           		npcount=0;
	           	}
	           	if(tasta.istGedrueckt('a'))
	           	{
	           		p.move = 3;
	           		npcount=0;
	           	}
	           	if(tasta.istGedrueckt('d'))
	           	{
	           		p.move = 4;
	           		npcount=0;
	           	}
	           	if(tasta.istGedrueckt(' '))
	           	{
	           		p.move = 0;
	           		npcount=0;
	           	}
	           	long delta = System.currentTimeMillis()-lasttime;
	           	if(p.move ==1)p.move(0, 0, 0.15*delta);
	           	if(p.move ==2)p.move(0, 0, -0.15*delta);
	           	if(p.move ==3)p.move(0.15*delta, 0, 0);
	           	if(p.move ==4)p.move(-0.15*delta, 0, 0);
	           	//if(p.move != p.lastmove)udpc.updateMove();//fire
	           	p.update();
	           	for(GLPersonNC nc : GLOBAL.pncs)
	           	{
	           		nc.update();
	           	}
	           	//p.dreheDich(0, 0.01, 0);
	           	lasttime = System.currentTimeMillis();
	            Sys.warte();	
	        }
		    if(p.alive)
		    {
		    	udpc.stop();
		    	//Client._this.start();
		        Sys.beenden();
		    }
		}
	}
}
