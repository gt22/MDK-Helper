package com.gt22.generator.gui;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;
import com.gt22.generator.Constants;


public class Gui
{
	public static void init()
	{
		SwingUtilities.invokeLater(new Runnable()
		{
			@Override
			public void run()
			{
				JFrame frame = new MainFrame("Mod generator");
			}
		});	
	}
}
