package com.gt22.generator.templates;


public class BlockBaseTemplate extends TemplateBase
{
	public String author, modid;

	public BlockBaseTemplate(String modid, String author)
	{
		this.author = author;
		this.modid = modid;
	}
	
	@Override
	public String getFileName()
	{
		return "BlockBase" + modid;
	}

	@Override
	public String getContent()
	{
		String sep  = System.getProperty("line.separator");
		return "package com." + author + "." + modid + ".blocks;" + sep
		+ sep
		+ "import com." + author + "." + modid + ".core.Core;" + sep
		+ "import com.gt22.gt22core.baseclasses.block.BlockBase;" + sep
		+ "import com.gt22.gt22core.utils.ToolClass;" + sep
		+ "import net.minecraft.block.material.Material;" + sep
		+ sep
		+ "public class " + getFileName() + " extends BlockBase" + sep
		+ "{" + sep
			+ sep
			+ "\tpublic " + getFileName() + "(Material mat, float hardness, float resistance, String unlocName, ToolClass tool, int harvestlevel)" + sep
			+ "\t{" + sep
				+ "\t\tsuper(mat, hardness, resistance, unlocName, Core.instance, 0, tool, harvestlevel);" + sep
			+ "\t}" + sep
		+ "}";
	}

}
