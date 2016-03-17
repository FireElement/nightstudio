package org.nightstudio.common.util.lang;

import org.apache.log4j.Logger;
import org.nightstudio.common.util.exception.sys.NSException;
import org.nightstudio.common.util.exception.sys.SystemInternalException;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;

public abstract class ClassScanner {
	private static Logger logger = Logger.getLogger(ClassScanner.class);
	
	abstract protected void handleScan(Class<?> clazz) throws NSException;
	
	public void scan() throws NSException {
		ClassLoader classLoader = VMUtil.getClassLoader();
		File file;
		String pack;
		Class<?> clazz;
		URL resource;
		String classPath;
		List<File> files = new ArrayList<File>(100);
		List<String> packages = new ArrayList<String>(100);
		Enumeration<URL> resources;
		try {
			resources = classLoader.getResources("/");
			while (resources.hasMoreElements()) {
				resource = resources.nextElement();
				files.add(new File(resource.getFile()));
				packages.add("");
			}
			while (files.size() > 0) {
				file = files.remove(0);
				pack = packages.remove(0);
				if (!file.exists()) {
					continue;
				}
				if (file.isDirectory()) {
					for (File tmpFile : file.listFiles()) {
						packages.add(0, pack + tmpFile.getName() + ".");
						files.add(0, tmpFile);
					}
				} else if (file.getName().endsWith(".class")) {
					classPath = pack.substring(0, pack.length() - 7);
					clazz = Class.forName(classPath);
					handleScan(clazz);
				}
			}
		} catch (IOException e) {
			logger.warn("", e);
			throw new SystemInternalException();
		} catch (ClassNotFoundException e) {
			logger.warn("", e);
			throw new SystemInternalException();
		}
	}
}
