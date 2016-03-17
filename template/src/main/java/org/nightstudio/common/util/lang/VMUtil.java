package org.nightstudio.common.util.lang;

public class VMUtil {
	public static ClassLoader getClassLoader() {
		return Thread.currentThread().getContextClassLoader();
	}
}
