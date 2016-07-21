package com.gt22.generator.templates;

import java.io.File;


public class CoreTemplate
{
	private String modid, author, name, mcversion;
	public CoreTemplate(String modid, String author, String name, String mcversion)
	{
		this.modid = modid;
		this.name = name;
		this.author = author;
		this.mcversion = mcversion;
	}
	
	@Override
	public String toString()
	{
		String importmod = mcversion.equals("1.7.10") ? "cpw.mods.fml." : "net.minecraftforge.fml.";
		String sep  = System.getProperty("line.separator");
		return "package com." + author + "." + modid + ".core;"
		+ sep
		+ sep
		+ "import com." + author + "." + modid + ".proxy.CommonProxy;" + sep
		+ "import " + importmod + "common.Mod;" + sep
		+ "import " + importmod + "common.Mod.EventHandler;" + sep
		+ "import " + importmod + "common.Mod.Instance;" + sep
		+ "import " + importmod + "common.SidedProxy;" + sep
		+ "import " + importmod + "common.event.FMLInitializationEvent;" + sep
		+ "import " + importmod + "common.event.FMLPostInitializationEvent;" + sep
		+ "import " + importmod + "common.event.FMLPreInitializationEvent;" + sep
		+ sep
		+ "@Mod(modid = Core.modid, name = Core.modid, version = Core.version)" + sep
		+ "public class Core" + sep
		+ "{" + sep
		+ "public static final int majorversion = 1, minorversion = 0, mcversion = " + generateVersion(mcversion) +", bugfixversion = 0;" + sep
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
	
	private static String generateVersion(String mcversion)
	{
		return mcversion.replace(".", "");
	}
}
