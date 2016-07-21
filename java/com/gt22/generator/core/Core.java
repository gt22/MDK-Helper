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
import com.gt22.generator.utils.FileUtils;
import com.gui.Gui;

public class Core
{
	private static class Packedges
	{
		File core;
		File proxy;

		public void init()
		{
			core.mkdirs();
			proxy.mkdirs();
		}
	}

	public static void generateMod(String modid, String name, String author, String mcversion, File moddir) throws IOException
	{
		Packedges pac = generatePackeges(moddir, modid, author);
		generateCore(pac.core, modid, name, author, mcversion);
		generateProxies(pac.proxy, modid, author, mcversion);
		generateResources(moddir, modid);
		
	}

	private static Packedges generatePackeges(File moddir, String modid, String author)
	{
		String sep = File.separator;
		File centralpackage = new File(moddir, "java" + sep + "com" + sep + author + sep + modid + sep);
		centralpackage.mkdirs();
		Packedges ret = new Packedges();
		ret.core = new File(centralpackage, "core" + sep);
		ret.proxy = new File(centralpackage, "proxy");
		ret.init();
		return ret;
	}

	private static void generateCore(File corepackage, String modid, String name, String author, String mcversion) throws IOException
	{
		try
		{
			File core = new File(corepackage, "Core.java");
			FileUtils.initFile(core);
			FileOutputStream corew = new FileOutputStream(core);
			corew.write(new CoreTemplate(modid, author, name, mcversion).toString().getBytes());
			corew.close();
		}
		catch (FileNotFoundException e)
		{
			e.printStackTrace();
		}
	}

	private static void generateProxies(File proxypackage, String modid, String author, String mcversion) throws IOException
	{
		File common = new File(proxypackage, "CommonProxy.java");
		FileUtils.initFile(common);
		FileOutputStream commonw = new FileOutputStream(common); 
		commonw.write(new CommonProxyTemplate(author, modid, mcversion).toString().getBytes());
		commonw.close();
		File client = new File(proxypackage, "ClientProxy.java");
		FileUtils.initFile(client);
		FileOutputStream clientw = new FileOutputStream(client);
		clientw.write(new ClientServerProxyTemplate(author, modid, mcversion, true).toString().getBytes());
		clientw.close();
		File server = new File(proxypackage, "ServerProxy.java");
		FileUtils.initFile(server);
		FileOutputStream serverw = new FileOutputStream(server);
		serverw.write(new ClientServerProxyTemplate(author, modid, mcversion, false).toString().getBytes());
		serverw.close();
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
	
	public static void main(String[] args)
	{
		try
		{
			Gui.init();
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}

}
