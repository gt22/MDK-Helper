package com.gt22.generator.templates.registry;

import com.gt22.generator.templates.TemplateBase;

public class ModelRegistryTemplate extends TemplateBase
{

	private static String author, modid;

	public ModelRegistryTemplate(String author, String modid)
	{
		this.author = author;
		this.modid = modid;
	}

	@Override
	public String getFileName()
	{
		return "ModelRegistry";
	}

	@Override
	public String getContent()
	{
		String sep  = System.getProperty("line.separator");
		return "package com." + author + "." + modid + ".registry;" + sep
				+ "import java.util.ArrayList;" + sep
				+ "import net.minecraft.block.Block;" + sep
				+ "import net.minecraft.client.Minecraft;" + sep
				+ "import net.minecraft.client.resources.model.ModelResourceLocation;" + sep
				+ "import net.minecraft.item.Item;" + sep
				+ "import net.minecraft.item.ItemStack;" + sep
				+ "import com." + author + "." + modid + "core.Core;" + sep
				+ sep
				+ "public class BlockRenderRegister {" + sep
					+ sep
					+ "\tpublic static void register(Item i, int meta)" + sep
					+ "\t{" + sep
						+ "\t\tMinecraft.getMinecraft().getRenderItem().getItemModelMesher().register(i, meta, new ModelResourceLocation(Core.modid + \":\" + i.getUnlocalizedName(), \"inventory\"));" + sep
					+ "\t}" + sep
					+ sep
					+ "\tpublic static void register(Block block)" + sep
					+ "\t{" + sep
						+ "\t\tif(!Item.getItemFromBlock(block).getHasSubtypes())" + sep
						+ "\t\t{" + sep
							+ "\t\t\tregister(block, 0);" + sep
							+ "\t\t\treturn;" + sep
						+ "\t\t}" + sep
						+ "\t\tArrayList<ItemStack> items = new ArrayList<ItemStack>();" + sep
						+ "\t\tblock.getSubBlocks(Item.getItemFromBlock(block), Core.tab, items);" + sep
						+ "\t\tfor(int i = 0; i < items.size(); i++)" + sep
						+ "\t\t{" + sep
							+ "\t\t\tregister(block, i);" + sep
						+ "\t\t}" + sep
					+ "\t}" + sep
					+ sep
					+ "\tpublic static void registerItem(Item item)" + sep
					+ "\t{" + sep
						+ "\t\tif(!item.getHasSubtypes())" + sep
						+ "\t\t{" + sep
							+ "\t\t\tregister(item, 0);" + sep
							+ "\t\t\treturn;" + sep
						+ "\t\t}" + sep
						+ "\t\tArrayList<ItemStack> items = new ArrayList<ItemStack>();" + sep
						+ "\t\titem.getSubItems(item, Core.tab, items);" + sep
						+ "\t\tfor(int i = 0; i < items.size(); i++)" + sep
						+ "\t\t{" + sep
							+ "\t\t\tregister(item, i);" + sep
						+ "\t\t}" + sep
					+ "\t}" + sep
				+ "}";
	}
}
