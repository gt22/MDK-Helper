package com.gt22.generator.core;

import java.io.File;
import java.io.IOException;
import com.gt22.generator.templates.BlockBaseTemplate;
import com.gt22.generator.templates.ClientServerProxyTemplate;
import com.gt22.generator.templates.CommonProxyTemplate;
import com.gt22.generator.templates.CoreTemplate;
import com.gt22.generator.templates.ItemBaseTemplate;
import com.gt22.generator.templates.ItemBlockRegistryTemplate;
import com.gt22.generator.templates.TileRegistryTemplate;
import com.gt22.generator.utils.FileUtils;

public class Writer
{
	private static class Packedges
	{
		File core;
		File proxy;
		File reg;
		File blocks;
		File items;
		public void init()
		{
			core.mkdirs();
			proxy.mkdirs();
			reg.mkdirs();
			blocks.mkdirs();
			items.mkdirs();
		}
	}

	public static void generateMod(String modid, String name, String author, String mcversion, File moddir, boolean gitgnore, boolean gtcore) throws IOException
	{
		Packedges pac = generatePackeges(moddir, modid, author);
		generateCore(pac.core, modid, name, author, mcversion, gtcore);
		generateProxies(pac.proxy, modid, author, mcversion);
		generateResources(moddir, modid);
		generateRegistry(pac.reg, modid, author, mcversion);
		if(gitgnore)
			createGitignore(moddir);
		if(gtcore)
			createGTCFeatures(moddir, modid, author, pac);
	}

	private static Packedges generatePackeges(File moddir, String modid, String author)
	{
		String sep = File.separator;
		File centralpackege = new File(moddir, "java" + sep + "com" + sep + author + sep + modid + sep);
		centralpackege.mkdirs();
		Packedges ret = new Packedges();
		ret.core = createPackge(centralpackege, "core");
		ret.proxy = createPackge(centralpackege, "proxy");
		ret.reg = createPackge(centralpackege, "registry");
		ret.blocks = createPackge(centralpackege, "blocks");
		ret.items = createPackge(centralpackege, "items");
		ret.init();
		return ret;
	}

	private static File createPackge(File centralpackege, String name)
	{
		return new File(centralpackege, name + File.separator);
	}
	
	private static void generateCore(File corepackage, String modid, String name, String author, String mcversion, boolean gtcore) throws IOException
	{
		new CoreTemplate(modid, author, name, mcversion, gtcore).write(corepackage);
	}

	private static void generateProxies(File proxypackage, String modid, String author, String mcversion) throws IOException
	{
		new CommonProxyTemplate(author, modid, mcversion).write(proxypackage);
		new ClientServerProxyTemplate(author, modid, mcversion, true).write(proxypackage);
		new ClientServerProxyTemplate(author, modid, mcversion, false).write(proxypackage);
	}
	

	private static void generateRegistry(File regpackege, String modid, String author, String mcversion) throws IOException
	{
		new ItemBlockRegistryTemplate(modid, author, mcversion, true).write(regpackege);
		new ItemBlockRegistryTemplate(modid, author, mcversion, false).write(regpackege);
		new TileRegistryTemplate(author, modid, mcversion).write(regpackege);
	}

	private static void generateResources(File moddir, String modid)
	{
		File resources = new File(moddir, "resources" + File.separator + "assets" + File.separator + modid + File.separator);
		resources.mkdirs();
		File textures = new File(resources, "textures");
		textures.mkdir();
		File lang = new File(resources, "lang");
		lang.mkdir();
	}
	
	private static void createGTCFeatures(File moddir, String modid, String author, Packedges pac) throws IOException
	{
		new ItemBaseTemplate(modid, author).write(pac.items);
		new BlockBaseTemplate(modid, author).write(pac.blocks);
	}
	
	private static void createGitignore(File modidr) throws IOException
	{
		File gitignore = new File(modidr, ".gitignore");
		FileUtils.initFile(gitignore);
		FileUtils.addLine(gitignore, "*");
		FileUtils.addLine(gitignore, "!java/");
		FileUtils.addLine(gitignore, "!resources/");
		FileUtils.addLine(gitignore, "!.gitignore");
		
	}

}
