package com.gt22.generator.templates.registry;

import com.gt22.generator.templates.TemplateBase;

public class ItemBlockRegistryTemplate extends TemplateBase
{
	private String modid, author, mcversion;
	private boolean item, gtcore;
	
	public ItemBlockRegistryTemplate(String modid, String author, String mcversion, boolean item, boolean gtcore)
	{
		this.modid = modid;
		this.author = author;
		this.mcversion = mcversion;
		this.item = item;
		this.gtcore = gtcore;
	}

	@Override
	public String getFileName()
	{
		return (item ? "Item" : "Block") + "Registry";
	}
	
	private String getRegisterString()
	{
		String sep  = System.getProperty("line.separator");
		if(mcversion.equals("1.7.10"))
		{
			return "GameRegistry.register" + (item ? "Item(item, item.getUnlocalizedName().substring(5)" : "Block(block, block.getUnlocalizedName().substring(5)") + ");";
		}
		else
		{
			if(gtcore)
			{
				return (item ? "item" : "block") + ".register();";
			}
			else
			{
				return 	"GameRegistry.register" + (item ? "Item(item, item.getUnlocalizedName().substring(5)" : "Block(block, block.getUnlocalizedName().substring(5)") + ");" + sep
						+ "ModerlRegistry.register" + (item ? "Item(item" : "Block(block" + ");");
			}
		}
	}

	@Override
	public String getContent()
	{
		String importmod = mcversion.equals("1.7.10") ? "cpw.mods.fml." : "net.minecraftforge.fml.";
		String sep  = System.getProperty("line.separator");
		return 	"package com." + author + "." + modid + ".registry;" + sep
				+ sep
				+ "import com." + author  + "." + modid + ".core.Core;" + sep
				+ (gtcore ? "import com.gt22.gt22core.baseclasses." + (item ? "item.ItemBase" : "block.BlockBase") : "import net.minecraft." + (item ? "item.Item" : "block.Block")) + ";" + sep
				+ "import net.minecraft.block.material.Material;" + sep
				+ "import net.minecraft.item.ItemBlock;" + sep
				+ "import net.minecraft.item.ItemStack;" + sep
				+ "import " + importmod + "common.registry.GameRegistry;" + sep
				+ sep
				+ "public class " + getFileName() + sep
				+ "{" + sep
					+ sep
					+ "\tprivate static void register(" + (item ? "Item" + (gtcore ? "Base" : "") + " item" : "Block" + (gtcore ? "Base" : "") + " block") + ")" + sep
					+ "\t{" + sep
						+ "\t\t" + getRegisterString() + sep
					+ "\t}" + sep
					+ sep
					+ (item || !mcversion.equals("1.7.10") ? "" : "\tprivate static void register(Block block, Class <? extends ItemBlock> itemBlock)" + sep
					+ "\t{" + sep
						+ "\t\tGameRegistry.registerBlock(block, itemBlock, block.getUnlocalizedName().substring(5));" + sep
					+ "\t}" + sep
					+ sep)
					+ "\tpublic static void init()" + sep
					+ "\t{" + sep
						+ sep
					+ "\t}" + sep
				+ "}" + sep
		;
	}
}
