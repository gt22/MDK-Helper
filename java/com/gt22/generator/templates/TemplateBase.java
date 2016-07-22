package com.gt22.generator.templates;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import com.gt22.generator.utils.FileUtils;

public abstract class TemplateBase
{
	public abstract String getFileName();
	public abstract String getContent();
	public void write(File dir) throws IOException
	{
		File f = new File(dir, getFileName() + ".java");
		FileUtils.initFile(f);
		FileOutputStream fw = new FileOutputStream(f);
		fw.write(getContent().getBytes());
		fw.close();
	}
}
