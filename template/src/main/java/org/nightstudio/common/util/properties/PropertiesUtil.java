package org.nightstudio.common.util.properties;

import org.apache.log4j.Logger;
import org.nightstudio.common.util.constant.PropertiesConstant;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class PropertiesUtil extends HttpServlet {
	private static final long serialVersionUID = -7398676733318170341L;
	private static Logger logger = Logger.getLogger(PropertiesUtil.class);
	private static Properties prop = null;
	private static Object lock = new Object();
	
	@Override
	public void init() throws ServletException {
		initialize();
	}
	
	public static void initialize () {
		prop = new Properties();
		try {
			String prefix = System.getProperty(PropertiesConstant.WEB_APP_ROOT_KEY);
			if (prefix != null) {
				prop.setProperty(PropertiesConstant.ROOT_PATH, prefix);
				File folder = new File(prefix + "/WEB-INF/classes/");
				for (File file : folder.listFiles()) {
					if (file.isFile() && file.getName().endsWith(".properties")) {
						prop.load(new FileInputStream(file));
					}
				}
			}
		} catch (FileNotFoundException e) {
			logger.warn("", e);
			prop = null;
		} catch (IOException e) {
			logger.warn("", e);
			prop = null;
		}
	}
	
	public static String getProperty(String key) {
		synchronized (lock) {
			if (prop == null) {
				initialize();
				if (prop == null) {
					logger.warn("propperties is null");
					return null;
				}
			}
			return prop.getProperty(key);
		}
	}
	
	public static boolean getBoolProperty(String key) {
		String tmp = getProperty(key);
		if (tmp == null || "".endsWith(tmp)) {
			logger.warn("could not get property '" + key + "'");
			return false;
		}
		return Boolean.valueOf(tmp);
	}
	
	public static int getIntProperty(String key) {
		String tmp = getProperty(key);
		if (tmp == null || "".endsWith(tmp)) {
			logger.warn("could not get property '" + key + "'");
			return 0;
		}
		try {
			return Integer.parseInt(tmp);
		} catch (NumberFormatException e) {
			logger.warn("", e);
			return 0;
		}
	}
	
	public static long getLongProperty(String key) {
		String tmp = getProperty(key);
		if (tmp == null || "".endsWith(tmp)) {
			logger.warn("could not get property '" + key + "'");
			return 0;
		}
		try {
			return Long.parseLong(tmp);
		} catch (NumberFormatException e) {
			logger.warn("", e);
			return 0;
		}
	}
}
