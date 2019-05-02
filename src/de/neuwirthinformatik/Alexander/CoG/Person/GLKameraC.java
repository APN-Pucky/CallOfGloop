package de.neuwirthinformatik.Alexander.CoG.Person;

import java.awt.Cursor;
import java.awt.Point;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import java.awt.image.BufferedImage;
import java.lang.reflect.Field;

import javax.swing.JFrame;

import javax.media.opengl.awt.GLCanvas;

import GLOOP.GLKamera;
import GLOOP.GLMaus;
import GLOOP.GLVektor;

public class GLKameraC extends PM implements MouseMotionListener
{
	GLPersonC p;
    Robot r;
    public static int screen_xmid = -1;
    public static int screen_ymid = -1;
    public static int xpos = -1;
    public static int ypos = -1;
    GLKamera k;
    GLCanvas canvas;
    JFrame jf = null;
    Cursor blankCursor;
    boolean active = true;
    
    public GLKameraC(GLPersonC p,double x, double y, double z)
    {
    	this.p = p;
        k = new GLKamera(500,500);
    	//k = new GLKamera();
        k.setzePosition(x, y, z);
        k.setzeBlickpunkt(x, y, z+10);
        //dirty relefections
        Class c = k.getClass();
        Field f = null;
      
        try
        {
            r = new Robot();
            f = c.getDeclaredField("frame");
            f.setAccessible(true);
            jf = (JFrame)f.get(k);
            f = c.getDeclaredField("canvas");
            f.setAccessible(true);
            canvas = (GLCanvas)f.get(k);
        }
        catch(Exception e)
        {
            System.out.println(e);
            System.exit(1);
        }
        BufferedImage cursorImg = new BufferedImage(16, 16, BufferedImage.TYPE_INT_ARGB);
        
        blankCursor = Toolkit.getDefaultToolkit().createCustomCursor(cursorImg, new Point(0, 0), "blank cursor");
        jf.getContentPane().setCursor(blankCursor);
        screen_xmid = jf.getContentPane().getComponent(0).getWidth()/2;
        screen_ymid = jf.getContentPane().getComponent(0).getHeight()/2;
        //xpos = (int) jf.getContentPane().getComponent(0).getLocationOnScreen().getX();
        //ypos = (int) jf.getContentPane().getComponent(0).getLocationOnScreen().getY();
        //jf.setLocation(0, 0);
        focusMouse();
        //r.mouseMove(screen_xmid,screen_ymid);
        //r.mouseMove(0, 0);
        canvas.addMouseMotionListener(this);
    }
    
    public double gibX()
    {
        return k.gibX();
    }
    
    public double gibY()
    {
        return k.gibY();
    }
    
    public double gibZ()
    {
        return k.gibZ();
    }
    
    public GLVektor gibRichtung()
    {
    	return k.gibBlickrichtung();
    }
    
    public void setzePosition(double x, double y, double z)
    {
        verschiebe(x-gibX(),y-gibY(),z-gibZ());
    }
    
    public void verschiebe(double x, double y, double z)
    {
        GLVektor v = k.gibBlickpunkt();
        k.setzeBlickpunkt(v.x+x,v.y+y,v.z+z);
        k.setzePosition(gibX()+x,gibY()+y,gibZ()+z);
    }
    
    public void dreheDich(double ax, double ay, double az)
    {
        dreheDich(ax,ay,az,gibX(),gibY(),gibZ());
    }
    
    public void dreheDich(double ax, double ay, double az,double x, double y, double z)
    {
        GLVektor v = k.gibBlickpunkt();
        v.x -= x;
        v.y -= y;
        v.z -= z;
        v.drehe(ax, ay, az);
        k.setzeBlickpunkt(v.x+x,v.y+y,v.z+z);
        
        v = new GLVektor(gibX()-x,gibY()-y,gibZ()-z);
        v.drehe(ax, ay, az);
        k.setzePosition(v.x+x,v.y+y,v.z+z);
        
    }

	@Override
	public void mouseDragged(MouseEvent e) 
	{
		if(active)
		{
			if(e.getX()>screen_xmid+2)
			{
				p.dreheDich(0,(-e.getX()+screen_xmid)*0.1,0);
				focusMouse();
			}
			if(e.getX()<screen_xmid-2)
			{
				p.dreheDich(0,(-e.getX()+screen_xmid)*0.1,0);
				focusMouse();
			}
			jf.toFront();
		}
	}

	@Override
	public void mouseMoved(MouseEvent e) 
	{
		if(active)
		{
			if(e.getX()>screen_xmid+2)
			{
				p.dreheDich(0,(-e.getX()+screen_xmid)*0.1,0);
				focusMouse();
			}
			if(e.getX()<screen_xmid-2)
			{
				p.dreheDich(0,(-e.getX()+screen_xmid)*0.1,0);
				focusMouse();
			}
			jf.toFront();
		}
	}
	
	public void setActive(boolean b)
	{
		active = b;
		if(!b)
		{
			jf.getContentPane().setCursor(Cursor.getDefaultCursor());
		}
		else
		{
			focusMouse();
			jf.getContentPane().setCursor(blankCursor);
		}
	}
	
	public void focusMouse()
	{
        xpos = (int) jf.getContentPane().getComponent(0).getLocationOnScreen().getX();
        ypos = (int) jf.getContentPane().getComponent(0).getLocationOnScreen().getY();
		if(r!=null &&screen_xmid!=-1 && screen_ymid !=-1)r.mouseMove(xpos+screen_xmid,ypos+screen_ymid);
	}
}
