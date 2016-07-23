package com.gt22.generator.templates;

public class TileRegistryTemplate extends TemplateBase
{
	private String author, modid, mcversion;
	
	public TileRegistryTemplate(String author, String modid, String mcversion)
	{
		this.author = author;
		this.modid = modid;
		this.mcversion = mcversion;
	}

	@Override
	public String getFileName()
	{
		return "TileRegistry";
	}

	@Override
	public String getContent()
	{
		String importmod = mcversion.equals("1.7.10") ? "cpw.mods.fml." : "net.minecraftforge.fml.";
		String sep  = System.getProperty("line.separator");
		return 	"package com." + author + "." + modid + ".registry;" + sep
				+ sep
				+ "import com." + author  + "." + modid + ".core.Core" + sep
				+ "import net.minecraft.tileentity.TileEntity;" + sep
				+ "import " + importmod + "common.registry.GameRegistry;" + sep
				+ sep
				+ "public class " + getFileName() + sep
				+ "{" + sep
					+ "\tpublic static void register(Class<? extends TileEntity> te)" + sep
					+ "\t{" + sep
						+ "\t\tGameRegistry.registerTileEntity(te, Core.modid + \".\" + te.getName());" + sep
					+ "\t}" + sep
					+ sep
					+ "\tpublic static final void init()" + sep
					+ "\t{" + sep
						+ sep
					+ "\t}" + sep
					+ sep
				+ "}";
	}
}
