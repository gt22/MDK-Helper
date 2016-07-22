package com.creatix.autodecompiler;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.lang.management.ManagementFactory;
import java.net.URL;
import java.util.Arrays;
import java.util.HashMap;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

public class AutoDecompiler {
	public static File work_dir;
	public static String forgeUrl;
	public static String forgeVersion;
	public static File batchFile;
	public static HashMap<String, String> forgeVersions;
	private static boolean eclipse, idea;
	
	public static void init()
	{
		forgeVersions = new HashMap<String, String>();
		//forgeVersions.put("1.6.4", "http://files.minecraftforge.net/maven/net/minecraftforge/forge/1.6.4-9.11.1.1345/forge-1.6.4-9.11.1.1345-src.zip");
		//forgeVersions.put("1.7.2", "http://files.minecraftforge.net/maven/net/minecraftforge/forge/1.7.2-10.12.2.1147/forge-1.7.2-10.12.2.1147-src.zip");
		forgeVersions.put("1.7.10", "http://files.minecraftforge.net/maven/net/minecraftforge/forge/1.7.10-10.13.4.1614-1.7.10/forge-1.7.10-10.13.4.1614-1.7.10-src.zip");
		forgeVersions.put("1.8", "http://files.minecraftforge.net/maven/net/minecraftforge/forge/1.8-11.14.4.1563/forge-1.8-11.14.4.1563-mdk.zip");
		forgeVersions.put("1.8.9", "http://files.minecraftforge.net/maven/net/minecraftforge/forge/1.8.9-11.15.1.1902-1.8.9/forge-1.8.9-11.15.1.1902-1.8.9-mdk.zip");
		forgeVersions.put("1.9", "http://files.minecraftforge.net/maven/net/minecraftforge/forge/1.9-12.16.1.1887/forge-1.9-12.16.1.1887-mdk.zip");
		forgeVersions.put("1.9.4", "http://files.minecraftforge.net/maven/net/minecraftforge/forge/1.9.4-12.17.0.1976/forge-1.9.4-12.17.0.1976-mdk.zip");
		forgeVersions.put("1.10", "http://files.minecraftforge.net/maven/net/minecraftforge/forge/1.10-12.18.0.2000-1.10.0/forge-1.10-12.18.0.2000-1.10.0-mdk.zip");
		forgeVersions.put("1.10.2", "http://files.minecraftforge.net/maven/net/minecraftforge/forge/1.10.2-12.18.1.2014/forge-1.10.2-12.18.1.2014-mdk.zip");
	}
	
	public static void setEclipse(boolean eclipse)
	{
		AutoDecompiler.eclipse = eclipse;
	}
	
	public static void setIdea(boolean idea)
	{
		AutoDecompiler.idea = idea;
	}
	
	public static void setWorkDir(String path)
	{
		if(path == null){
			error("Path is null"); 
			return;
		}
		work_dir = new File(path);
		if(!work_dir.isDirectory()){
			error(path + " - Is not Folder");
			work_dir = null;
			return;
		}
		if(!work_dir.exists()){
			error("Folder dosn't exists");
			work_dir = null;
			return;
		}
		if(!work_dir.canRead())
		{
			error("Can not be read folder, try to open by admin");
			work_dir = null;
			return;
		}
		if(work_dir.listFiles().length > 0)
		{
			error("Folder not empty");
			work_dir = null;
			return;
		}
	}
	
	public static void setForgeVersion(String versionKey)
	{
		if(!forgeVersions.containsKey(versionKey))
			error("Wrong forge version");
		forgeUrl = forgeVersions.get(versionKey);
		forgeVersion = versionKey;
	}
	
	public static boolean isWindows()
	{
		return System.getProperty("os.name").toLowerCase().contains("windows");
	}
	
	public static String getZipFile()
	{
		return System.getProperty("user.home")+"/tmp_forge.zip";
	}
	
	public static void downloadForge()
	{
		try{
			URL link = new URL(forgeUrl);
			InputStream in = new BufferedInputStream(link.openStream());
			ByteArrayOutputStream out = new ByteArrayOutputStream();
			byte[] buf = new byte[1024];
				int n = 0;
		 	while ((n=in.read(buf)) != -1)
		 	{
		 			out.write(buf, 0, n);
		 	}
		 	out.close();
		 	in.close();
		 	byte[] response = out.toByteArray();
		 	FileOutputStream fos = new FileOutputStream(getZipFile());
		 	fos.write(response);
		 	fos.close();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	
	public static void unzipForgeFile()
	{
		try{
			File destDir = new File(work_dir.getAbsolutePath());
	        if (!destDir.exists()) {
	            destDir.mkdirs();
	        }
	        ZipInputStream zipIn = new ZipInputStream(new FileInputStream(getZipFile()));
	        ZipEntry entry = zipIn.getNextEntry();
	        while (entry != null) {
	            String filePath = work_dir.getAbsolutePath() + File.separator + entry.getName();
	            if (!entry.isDirectory()) {
	                extractFile(zipIn, filePath);
	            } else {
	                File dir = new File(filePath);
	                dir.mkdir();
	            }
	            zipIn.closeEntry();
	            entry = zipIn.getNextEntry();
	        }
	        zipIn.close();
	        new File(getZipFile()).delete();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	private static void extractFile(ZipInputStream zipIn, String filePath) throws IOException {
        BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(filePath));
        byte[] bytesIn = new byte[4096];
        int read = 0;
        while ((read = zipIn.read(bytesIn)) != -1) {
            bos.write(bytesIn, 0, read);
        }
        bos.close();
    }
	public static void generateBatch()
	{
		try{
			batchFile = new File(work_dir.getAbsolutePath()+"/decompile.bat");
			batchFile.createNewFile();
			FileWriter batchWriter = new FileWriter(batchFile);
			long ram = ((com.sun.management.OperatingSystemMXBean) ManagementFactory.getOperatingSystemMXBean()).getTotalPhysicalMemorySize()/(1024*1024);
			if((forgeVersion.contains("1.8")) ||(forgeVersion.contains("1.9")) || (forgeVersion.contains("1.10"))){
				if(ram > 2000){
					String a = work_dir.getAbsolutePath();
					batchWriter.append(a.substring(0, a.indexOf(':'))+":\n");
					batchWriter.append("cd "+work_dir.getAbsolutePath()+"\n");
					batchWriter.append(work_dir.getAbsolutePath()+"\\gradlew -Dorg.gradle.jvmargs=-Xmx2048m setupDecompWorkspace");
					if(eclipse)
						batchWriter.append(" eclipse");
					if(idea)
						batchWriter.append(" idea");
					batchWriter.append("\nexit /b");
				}
				else
				{
					error("To decompiling mc 1.8 and later need 2Gb RAM and more, you have "+ram);
				}
			}
			else
			{
				String a = work_dir.getAbsolutePath();
				batchWriter.append(a.substring(0, a.indexOf(':'))+":\n");
				batchWriter.append("cd "+work_dir.getAbsolutePath()+"\n");
				batchWriter.append(work_dir.getAbsolutePath()+"\\gradlew setupDecompWorkspace");
				if(eclipse)
					batchWriter.append(" eclipse");
				if(idea)
					batchWriter.append(" idea");
				batchWriter.append("\nexit /b");
			}
			batchWriter.close(); 
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	
	public static void executeDecompiler()
	{
		try {
			Runtime.getRuntime().exec(new String[]{"cmd.exe", "/c", "start "+batchFile.getAbsolutePath()});
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	private static void error(Object o)
	{
		System.out.println("[ERROR]"+o);
	}
	public static void decompile()
	{
	      AutoDecompiler.downloadForge();
	      AutoDecompiler.unzipForgeFile();
	      AutoDecompiler.generateBatch();
	      AutoDecompiler.executeDecompiler();
	}
}
