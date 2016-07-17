package com.gt22.generator.templates;

import java.io.File;


public class CoreTemplate
{
	private String modid, author, name;
	public CoreTemplate(String modid, String author, String name)
	{
		this.modid = modid;
		this.name = name;
		this.author = author;
	}
	
	@Override
	public String toString()
	{
		String sep  = System.getProperty("line.separator");
		return "package com." + author + "." + modid + ".core;"
		+ sep
		+ sep
		+ "import com." + author + "." + modid + ".proxy.CommonProxy;" + sep
		+ "import cpw.mods.fml.common.Mod;" + sep
		+ "import cpw.mods.fml.common.Mod.EventHandler;" + sep
		+ "import cpw.mods.fml.common.Mod.Instance;" + sep
		+ "import cpw.mods.fml.common.SidedProxy;" + sep
		+ "import cpw.mods.fml.common.event.FMLInitializationEvent;" + sep
		+ "import cpw.mods.fml.common.event.FMLPostInitializationEvent;" + sep
		+ "import cpw.mods.fml.common.event.FMLPreInitializationEvent;" + sep
		+ sep
		+ "@Mod(modid = Core.modid, name = Core.modid, version = Core.version)" + sep
		+ "public class Core" + sep
		+ "{" + sep
		+ "public static final int majorversion = 1, minorversion = 0, mcversion = 1710, bugfixversion = 0;" + sep
		+ "public static final String modid = \""+ modid + "\", name = \"" + name + "\", version = majorversion + \".\" + minorversion + \".\" + mcversion + \".\" + bugfixversion;" + sep
		+ sep
		+ "@SidedProxy(clientSide = \"com." + author + ".\" + modid + \".proxy.ClientProxy\", serverSide = \"com." + author + ".\" + modid + \".proxy.ServerProxy\")" + sep
		+ "public static CommonProxy proxy;" + sep
		+ sep
		+ "@Instance(modid)" + sep
		+ "public static Core instance;" + sep
		+ sep
		+ "@EventHandler" + sep
		+ "public static void preInit(FMLPreInitializationEvent e)" + sep
		+ "{" + sep
		+ "\tproxy.preInit(e);" + sep
		+ "}" + sep
		+ sep
		+ "@EventHandler" + sep
		+ "public static void init(FMLInitializationEvent e)" + sep
		+ "{" + sep
		+ "\tproxy.init(e);" + sep
		+ "}" + sep
		+ sep
		+ "@EventHandler" + sep
		+ "public static void postInit(FMLPostInitializationEvent e)" + sep
		+ "{" + sep
		+ "\tproxy.postInit(e);" + sep
		+ "}" + sep
		+ sep	
		+ "}" + sep;
	}
}
