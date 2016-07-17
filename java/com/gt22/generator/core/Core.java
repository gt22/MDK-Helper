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

	private static File generateModDir(BufferedReader in) throws IOException
	{
		System.out.println("Enter mod directory (From current file) (use " + File.separator + " separate directories, and ../ to go up by one directory");
		String directory = new File(".").getAbsolutePath();
		directory = directory.substring(0, directory.length() - 2) + File.separator + in.readLine();
		File modfolder = new File(directory);
		modfolder.mkdirs();
		return modfolder;
	}

	private static void generateMod(BufferedReader in, File moddir) throws IOException
	{
		System.out.println("Enter modid of mod");
		String modid = in.readLine();
		System.out.println("Enter mod name");
		String name = in.readLine();
		System.out.println("Enter mod author name");
		String author = in.readLine();
		Packedges pac = generatePackeges(moddir, modid, author);
		generateCore(pac.core, modid, name, author);
		generateProxies(pac.proxy, modid, author);
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

	private static void generateCore(File corepackage, String modid, String name, String author) throws IOException
	{
		try
		{
			File core = new File(corepackage, "Core.java");
			FileUtils.initFile(core);
			FileOutputStream corew = new FileOutputStream(core);
			corew.write(new CoreTemplate(modid, author, name).toString().getBytes());
			corew.close();
		}
		catch (FileNotFoundException e)
		{
			e.printStackTrace();
		}
	}

	private static void generateProxies(File proxypackage, String modid, String author) throws IOException
	{
		File common = new File(proxypackage, "CommonProxy.java");
		FileUtils.initFile(common);
		FileOutputStream commonw = new FileOutputStream(common); 
		commonw.write(new CommonProxyTemplate(author, modid).toString().getBytes());
		commonw.close();
		File client = new File(proxypackage, "ClientProxy.java");
		FileUtils.initFile(client);
		FileOutputStream clientw = new FileOutputStream(client);
		clientw.write(new ClientServerProxyTemplate(author, modid, true).toString().getBytes());
		clientw.close();
		File server = new File(proxypackage, "ServerProxy.java");
		FileUtils.initFile(server);
		FileOutputStream serverw = new FileOutputStream(server);
		serverw.write(new ClientServerProxyTemplate(author, modid, false).toString().getBytes());
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
			BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
			File moddir = generateModDir(in);
			generateMod(in, moddir);
			in.close();
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}

}
