package org.nightstudio.common.util.page;

import org.nightstudio.common.util.constant.ApplicationConstant;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.util.Hashtable;
import java.util.Map;

public class PageUtil {
	private static Map<String, String> languageMap = new Hashtable<String, String>(100);
	
	public static String getPageResourcePath(HttpServletRequest request, String prefix, String defaultPath) {
		String language = request.getLocale().getLanguage().toLowerCase();
		String key = defaultPath + ":" + language;
		if (languageMap.containsKey(key)) {
			return languageMap.get(key);
		} else {
			String path = prefix + defaultPath;
			String rootPath = ApplicationConstant.ROOT_PATH;
			File folder = new File(rootPath + defaultPath.replace("/", File.separator).replace("default", language));
			if (folder.exists()) {
				path = path.replace("default", language);
			}
			languageMap.put(key, path);
			return path;
		}
	}
	
	public static String getPageResourcePath(HttpServletRequest request, String prefix, String defaultPath, String path) {
		return getPageResourcePath(request, prefix, defaultPath) + "/" + path;
	}
}
