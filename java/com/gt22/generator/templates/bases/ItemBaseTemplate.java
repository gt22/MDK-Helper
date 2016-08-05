package com.gt22.generator.templates.bases;

import com.gt22.generator.templates.TemplateBase;

public class ItemBaseTemplate extends TemplateBase
{

	private String modid, author;
	public ItemBaseTemplate(String modid, String author)
	{
		this.modid = modid;
		this.author = author;
	}
	
	@Override
	public String getFileName()
	{
		return "ItemBase" + modid;
	}

	@Override
	public String getContent()
	{
		String sep  = System.getProperty("line.separator");
		return "package com." + author + "." + modid + ".items;" + sep
		+ sep
		+ "import com." + author + "." + modid + ".core.Core;" + sep
		+ "import com.gt22.gt22core.baseclasses.item.ItemBase;" + sep
		+ sep
		+ "public class " + getFileName() + " extends ItemBase" + sep
		+ "{" + sep
			+ sep
			+ "\tpublic " + getFileName() + "(String unlocName)" + sep
			+ "\t{" + sep
				+ "\tsuper(unlocName, Core.instance);" + sep
			+ "\t}" + sep
		+ "}" + sep;
	}

}
