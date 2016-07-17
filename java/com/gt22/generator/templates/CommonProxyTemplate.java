package com.gt22.generator.templates;

public class CommonProxyTemplate
{
	private String author, modid;
	public CommonProxyTemplate(String author, String modid)
	{
		this.author = author;
		this.modid = modid;
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
			+ "public class CommonProxy" + sep
			+ "{" + sep
			+ "public void preInit(FMLPreInitializationEvent e)" + sep
			+ "{" + sep
			+ sep
			+ "}" + sep
			+ sep
			+ "public void init(FMLInitializationEvent e)" + sep
			+ "{" + sep
			+ sep
			+ "}" + sep
			+ sep
			+ "public void postInit(FMLPostInitializationEvent e)" + sep
			+ "{" + sep
			+ sep
			+ "}" + sep
			+ "}" + sep;
	}
}
