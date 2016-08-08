package com.gui;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import com.creatix.autodecompiler.AutoDecompiler;
import com.gt22.generator.Constants;

public class Gui
{
	public static void init()
	{
		try
		{
			if (AutoDecompiler.isWindows())
				UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		SwingUtilities.invokeLater(() -> new MainFrame("MDK Helper"));
	}
}
