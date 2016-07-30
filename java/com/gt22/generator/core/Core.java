package com.gt22.generator.core;

import java.io.File;
import java.io.IOException;
import com.gui.Gui;

public class Core
{
	public static void main(String[] args) throws IOException
	{
		if(args[0].equals("--default") || args[0].equals("-d"))
		{
			Writer.generateMod("q", "q", "q", "1.7.10", new File("F:modding\\eclipse\\test"), false, true);
		}
		else
		{
			System.out.println(args[0]);
		}
		Gui.init();
	}
}
