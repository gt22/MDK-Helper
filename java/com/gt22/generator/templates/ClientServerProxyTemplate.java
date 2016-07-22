package com.gt22.generator.templates;

public class ClientServerProxyTemplate
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
	public String toString()
	{
		String importmod = mcversion.equals("1.7.10") ? "cpw.mods.fml." : "net.minecraftforge.fml.";
		String sep  = System.getProperty("line.separator");
		return "package com." + author + "." + modid + ".proxy;" + sep
		+ sep
		+ "import " + importmod + "common.event.FMLInitializationEvent;" + sep
		+ "import " + importmod + "common.event.FMLPostInitializationEvent;" + sep
		+ "import " + importmod + "common.event.FMLPreInitializationEvent;" + sep
		+ sep
		+ "public class " + (client ? "ClientProxy" : "ServerProxy") + " extends CommonProxy" + sep
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
