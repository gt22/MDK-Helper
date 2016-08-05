package com.gt22.generator.templates.proxy;

import com.gt22.generator.templates.TemplateBase;

public class ClientServerProxyTemplate extends TemplateBase
{
	private String modid, author, mcversion;
	private boolean client;
	
	public ClientServerProxyTemplate(String author, String modid, String mcversion, boolean client)
	{
		this.modid = modid;
		this.author = author;
		this.mcversion = mcversion;
		this.client = client;
	}

	@Override
	public String getFileName()
	{
		return (client ? "Client" : "Server") + "Proxy";
	}

	@Override
	public String getContent()
	{
		String importmod = mcversion.equals("1.7.10") ? "cpw.mods.fml." : "net.minecraftforge.fml.";
		String sep  = System.getProperty("line.separator");
		return 	"package com." + author + "." + modid + ".proxy;" + sep
				+ sep
				+ "import " + importmod + "common.event.FMLInitializationEvent;" + sep
				+ "import " + importmod + "common.event.FMLPostInitializationEvent;" + sep
				+ "import " + importmod + "common.event.FMLPreInitializationEvent;" + sep
				+ sep
				+ "public class " + getFileName() + " extends CommonProxy" + sep
				+ "{" + sep
					+ "\t@Override" + sep
					+ "\tpublic void preInit(FMLPreInitializationEvent e)" + sep
					+ "\t{" + sep
						+ "\t\tsuper.preInit(e);" + sep
					+ "\t}" + sep
					+ sep
					+ "\t@Override" + sep
					+ "\tpublic void init(FMLInitializationEvent e)" + sep
					+ "\t{" + sep
						+ "\t\tsuper.init(e);" + sep
					+ "\t}" + sep
					+ sep
					+ "\t@Override" + sep
					+ "\tpublic void postInit(FMLPostInitializationEvent e)" + sep
					+ "\t{" + sep
						+ "\t\tsuper.postInit(e);" + sep
					+ "\t}" + sep
				+ "}" + sep;
	}
	
	
}
