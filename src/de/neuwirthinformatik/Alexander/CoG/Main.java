package de.neuwirthinformatik.Alexander.CoG;

import java.awt.image.BufferedImage;
import java.io.File;

import javax.imageio.ImageIO;
import javax.swing.JOptionPane;

public class Main 
{
	public static void main(String[] args)
	{
		//load
		try{
		File outputfile = new File("tmp_ground.jpg");
		outputfile.createNewFile();
		BufferedImage img = 
		        ImageIO.read(Main.class.getResourceAsStream("resources/ground.jpg"));
		ImageIO.write(img, "jpg", outputfile);
		}
		catch(Exception e)
		{
			
		}
	
		Object[] options = {"Client",
		                    "Server",
		                    "Exit"};
		int n = JOptionPane.showOptionDialog(null,
		    "Server or Client?",
		    "Call of Gloop",
		    JOptionPane.YES_NO_CANCEL_OPTION,
		    JOptionPane.QUESTION_MESSAGE,
		    null,
		    options,
		    options[2]);
		switch(n)
		{
		case(0):new Client(JOptionPane.showInputDialog("Server IP:")).start();break;
		case(1):new Server().start();break;
		}
	}
}
