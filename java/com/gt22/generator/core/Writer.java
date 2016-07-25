package com.gt22.generator.core;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream.GetField;
import com.gt22.generator.templates.ClientServerProxyTemplate;
import com.gt22.generator.templates.CommonProxyTemplate;
import com.gt22.generator.templates.CoreTemplate;
import com.gt22.generator.templates.ItemBlockRegistryTemplate;
import com.gt22.generator.templates.TileRegistryTemplate;
import com.gt22.generator.utils.FileUtils;
import com.gui.Gui;

public class Writer
{
	private static class Packedges
	{
		File core;
		File proxy;
		File reg;

		public void init()
		{
			core.mkdirs();
			proxy.mkdirs();
			reg.mkdirs();
		}
	}

	public static void generateMod(String modid, String name, String author, String mcversion, File moddir, boolean gitgnore) throws IOException
	{
		Packedges pac = generatePackeges(moddir, modid, author);
		generateCore(pac.core, modid, name, author, mcversion);
		generateProxies(pac.proxy, modid, author, mcversion);
		generateResources(moddir, modid);
		generateRegistry(pac.reg, modid, author, mcversion);
		if(gitgnore)
			createGitignore(moddir);
	}

	private static Packedges generatePackeges(File moddir, String modid, String author)
	{
		String sep = File.separator;
		File centralpackage = new File(moddir, "java" + sep + "com" + sep + author + sep + modid + sep);
		centralpackage.mkdirs();
		Packedges ret = new Packedges();
		ret.core = new File(centralpackage, "core" + sep);
		ret.proxy = new File(centralpackage, "proxy" + sep);
		ret.reg = new File(centralpackage, "registry" + sep);
		ret.init();
		return ret;
	}

	private static void generateCore(File corepackage, String modid, String name, String author, String mcversion) throws IOException
	{
		new CoreTemplate(modid, author, name, mcversion).write(corepackage);
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
