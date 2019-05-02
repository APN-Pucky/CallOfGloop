package de.neuwirthinformatik.Alexander.CoG.Util;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;

import javax.imageio.ImageIO;

//ByteArrayConverter
public class BAC 
{
	public static byte[] toByteArray(short v) 
	{
		byte[] bytes = new byte[2];
		ByteBuffer.wrap(bytes).putShort(v);
		return bytes;
	}
	
	public static byte[] toByteArray(int v) 
	{
		byte[] bytes = new byte[4];
		ByteBuffer.wrap(bytes).putInt(v);
		return bytes;
	}
	
	public static byte[] toByteArray(double v) 
	{
		byte[] bytes = new byte[8];
		ByteBuffer.wrap(bytes).putDouble(v);
		return bytes;
	}
	
	public static byte[] toByteArray(String v)
	{
		int len_s = v.length();
 		byte[] r = new byte[len_s];
 		for (int i=0;i<len_s;i++) 
 		{
 			r[i] =  (byte)v.charAt(i);
 		}
 		return r;
	}
	
	public static byte[] toByteArray(BufferedImage img)
	{
		ByteArrayOutputStream baos = new ByteArrayOutputStream(1000);
		byte[] data = null;
 		try 
 		{
			ImageIO.write(img, "jpg", baos);
			baos.flush();
			data = baos.toByteArray();
	 		baos.close();
		} 
 		catch (IOException e) 
		{
 			System.err.println(e);
			e.printStackTrace();
		}
 		return data;
	}
		
	public static short toShort(byte[] bytes) 
	{
		return ByteBuffer.wrap(bytes).getShort();
	}
	
	public static int toInt(byte[] bytes) 
	{
		return ByteBuffer.wrap(bytes).getInt();
	}
	
	public static double toDouble(byte[] bytes) 
	{
		return ByteBuffer.wrap(bytes).getDouble();
	}
	
	public static String toString(byte[] byte_string)
	{
		String r=new String();
 		for(int i=0; i<byte_string.length;i++) 
 		{
 			r +=(char) byte_string[i];
 		}
 		return r;
	}
	
	public static BufferedImage toImage(byte[] bytes)
	{
		BufferedImage img = null;
		try 
		{
			img = ImageIO.read(new ByteArrayInputStream(bytes));
		} 
		catch (IOException e) 
		{
			System.err.println(e);
			e.printStackTrace();
			System.exit(-1);
		}
		return img;
	}
}

