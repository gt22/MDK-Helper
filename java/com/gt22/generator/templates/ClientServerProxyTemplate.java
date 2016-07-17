package com.gt22.generator.templates;

public class ClientServerProxyTemplate
{
	private String modid, author;
	private boolean client;
	
	public ClientServerProxyTemplate(String author, String modid, boolean client)
	{
		this.modid = modid;
		this.author = author;
		this.client = client;
	}
	
	@Override
	public String toString()
	{
		String sep  = System.getProperty("line.separator");
		return "package com." + author + "." + modid + ".proxy;" + sep
		+ sep
		+ "import cpw.mods.fml.common.event.FMLInitializationEvent;" + sep
		+ "import cpw.mods.fml.common.event.FMLPostInitializationEvent;" + sep
		+ "import cpw.mods.fml.common.event.FMLPreInitializationEvent;" + sep
		+ sep
		+ "public class " + (client ? "ClientProxy" : "ServerProxy") + " extends CommonProxy" + sep
		+ "{" + sep
		+ "@Override" + sep
		+ "public void preInit(FMLPreInitializationEvent e)"
		+ "{" + sep
		+ "super.preInit(e);" + sep
		+ "}" + sep
		+ sep
		+ "@Override" + sep
		+ "public void init(FMLInitializationEvent e)" + sep
		+ "{" + sep
		+ "super.init(e);" + sep
		+ "}" + sep
		+ sep
		+ "@Override" + sep
		+ "public void postInit(FMLPostInitializationEvent e)" + sep
		+ "{"
		+ "super.postInit(e);" + sep
		+ "}" + sep
		+ "}" + sep;
	}
}
