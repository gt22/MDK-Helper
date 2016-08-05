package com.gt22.generator.templates.proxy;

import com.gt22.generator.templates.TemplateBase;

public class CommonProxyTemplate extends TemplateBase
{
	private String author, modid, mcversion;
	public CommonProxyTemplate(String author, String modid, String mcversion)
	{
		this.author = author;
		this.modid = modid;
		this.mcversion = mcversion;
	}

	@Override
	public String getFileName()
	{
		return "CommonProxy";
	}

	@Override
	public String getContent()
	{
		String importmod = mcversion.equals("1.7.10") ? "cpw.mods.fml." : "net.minecraftforge.fml.";
		String sep  = System.getProperty("line.separator");
		return 	"package com." + author + "." + modid + ".proxy;" + sep
				+ sep 
				+ "import com." + author + "." + modid + ".registry.ItemRegistry;" + sep
				+ "import com." + author + "." + modid + ".registry.BlockRegistry;" + sep
				+ "import com." + author + "." + modid + ".registry.TileRegistry;" + sep
				+ "import " + importmod + "common.event.FMLInitializationEvent;" + sep
				+ "import " + importmod + "common.event.FMLPostInitializationEvent;" + sep
				+ "import " + importmod + "common.event.FMLPreInitializationEvent;" + sep
				+ sep
				+ "public class " + getFileName() + sep
				+ "{" + sep
					+ "\tpublic void preInit(FMLPreInitializationEvent e)" + sep
					+ "\t{" + sep
						+ "\t\tItemRegistry.init();" + sep
						+ "\t\tBlockRegistry.init();" + sep
						+ "\t\tTileRegistry.init();" + sep
					+ "\t}" + sep
					+ sep
					+ "\tpublic void init(FMLInitializationEvent e)" + sep
					+ "\t{" + sep
						+ sep
					+ "\t}" + sep
					+ sep
					+ "\tpublic void postInit(FMLPostInitializationEvent e)" + sep
					+ "\t{" + sep
						+ sep
					+ "\t}" + sep
				+ "}" + sep;
	}
}
