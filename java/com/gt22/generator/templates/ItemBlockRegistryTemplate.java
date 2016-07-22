package com.gt22.generator.templates;

public class ItemBlockRegistryTemplate extends TemplateBase
{
	private String modid, author, mcversion;
	boolean item;
	
	public ItemBlockRegistryTemplate(String modid, String author, String mcversion, boolean item)
	{
		this.modid = modid;
		this.author = author;
		this.mcversion = mcversion;
		this.item = item;
	}

	@Override
	public String getFileName()
	{
		return (item ? "Item" : "Block") + "Registry";
	}

	@Override
	public String getContent()
	{
		String importmod = mcversion.equals("1.7.10") ? "cpw.mods.fml." : "net.minecraftforge.fml.";
		String sep  = System.getProperty("line.separator");
		return 	"package com.gt22.elementalmagic.registry;" + sep
				+ sep
				+ "import net.minecraft." + (item ? "item.Item" : "block.Block") + ";" + sep
				+ "import net.minecraft.block.material.Material;" + sep
				+ "import net.minecraft.item.ItemBlock;" + sep
				+ "import net.minecraft.item.ItemStack;" + sep
				+ "import " + importmod + "common.registry.GameRegistry;" + sep
				+ sep
				+ "public class " + getFileName() + sep
				+ "{" + sep
					+ sep
					+ "\tprivate static void register(" + (item ? "Item item" : "Block block") + ")" + sep
					+ "\t{" + sep
						+ "\t\tGameRegistry.register" + (item ? "Item(item, item.getUnlocalizedName().substring(5)" : "Block(block, block.getUnlocalizedName().substring(5)") + ");" + sep
					+ "\t}" + sep
					+ sep
					+ (item ? "" : "\tprivate static void register(Block block, Class <? extends ItemBlock> itemBlock)" + sep
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
