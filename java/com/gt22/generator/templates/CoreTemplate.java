package com.gt22.generator.templates;



public class CoreTemplate extends TemplateBase
{
	private String modid, author, name, mcversion;
	private boolean gtcore;
	public CoreTemplate(String modid, String author, String name, String mcversion, boolean gtcore)
	{
		this.modid = modid;
		this.name = name;
		this.author = author;
		this.mcversion = mcversion;
		this.gtcore = gtcore;
	}
	
	@Override
	public String getContent()
	{
		String importmod = mcversion.equals("1.7.10") ? "cpw.mods.fml." : "net.minecraftforge.fml.";
		String sep  = System.getProperty("line.separator");
		return "package com." + author + "." + modid + ".core;"
			+ sep
			+ sep
			+ "import com." + author + "." + modid + ".proxy.CommonProxy;" + sep
			+ "import net.minecraft.creativetab.CreativeTabs;" + sep
			+ "import net.minecraft.item.Item;" + sep
			+ "import net.minecraft.init.Items;" + sep
			+ "import " + importmod + "common.Mod;" + sep
			+ "import " + importmod + "common.Mod.EventHandler;" + sep
			+ "import " + importmod + "common.Mod.Instance;" + sep
			+ "import " + importmod + "common.SidedProxy;" + sep
			+ "import " + importmod + "common.event.FMLInitializationEvent;" + sep
			+ "import " + importmod + "common.event.FMLPostInitializationEvent;" + sep
			+ "import " + importmod + "common.event.FMLPreInitializationEvent;" + sep
			+ (gtcore ? "import com.gt22.gt22core.interfaces.IMod;" + sep : "")
			+ sep
			+ "@Mod(modid = Core.modid, name = Core.modid, version = Core.version)" + sep
			+ "public class " + getFileName() + (gtcore ? " implements IMod" : "") + sep
			+ "{" + sep
				+ "\tpublic static final int majorversion = 1, minorversion = 0, mcversion = " + generateVersion(mcversion) +", bugfixversion = 0;" + sep
				+ "\tpublic static final String modid = \""+ modid + "\", name = \"" + name + "\", version = majorversion + \".\" + minorversion + \".\" + mcversion + \".\" + bugfixversion;" + sep
				+ sep
				+ "\t@SidedProxy(clientSide = \"com." + author + ".\" + modid + \".proxy.ClientProxy\", serverSide = \"com." + author + ".\" + modid + \".proxy.ServerProxy\")" + sep
				+ "\tpublic static CommonProxy proxy;" + sep
				+ sep
				+ "\t@Instance(modid)" + sep
				+ "\tpublic static Core instance;" + sep
				+ sep
				+ "\tpublic static CreativeTabs tab = new CreativeTabs(modid)" + sep
				+ "\t{"
				+ sep
					+ "\t\t@Override" + sep
					+ "\t\tpublic Item getTabIconItem()" + sep
					+ "\t\t{" + sep
						+ "\t\t\treturn Items.apple;" + sep
					+ "\t\t}" + sep
					+ sep
				+ "\t};"
				+ (gtcore ? 
				  "\t@Override" + sep
				+ "\tpublic CreativeTabs[] getCreativeTabs()" + sep
				+ "\t{" + sep
					+ "\t\treturn new CreativeTabs[]" + sep
					+ "\t\t{ tab };" + sep
				+ "\t}" + sep
				+ sep
				+ "\t@Override" + sep
				+ "\tpublic String getModid()" + sep
				+ "\t{" + sep
					+ "\treturn modid;" + sep
				+ "\t}" + sep : "")
				+ sep
				+ "\t@EventHandler" + sep
				+ "\tpublic static void preInit(FMLPreInitializationEvent e)" + sep
				+ "\t{" + sep
					+ "\t\tproxy.preInit(e);" + sep
				+ "\t}" + sep
				+ sep
				+ "\t@EventHandler" + sep
				+ "\tpublic static void init(FMLInitializationEvent e)" + sep
				+ "\t{" + sep
					+ "\t\tproxy.init(e);" + sep
				+ "\t}" + sep
				+ sep
				+ "\t@EventHandler" + sep
				+ "\tpublic static void postInit(FMLPostInitializationEvent e)" + sep
				+ "\t{" + sep
					+ "\t\tproxy.postInit(e);" + sep
				+ "\t}" + sep
				+ sep	
			+ "}" + sep;
	}
	
	@Override
	public String getFileName()
	{
		return "Core";
	}
	
	private static String generateVersion(String mcversion)
	{
		return mcversion.replace(".", "");
	}
}
