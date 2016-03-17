package org.nightstudio.common.util.string;

public class StringUtil {
	public static String toLowerCaseInitial(String str) {
		return toCaseInitial(str, false);
	}
	
	public static String toUpperCaseInitial(String str) {
		return toCaseInitial(str, true);
	}
	
	public static String toCaseInitial(String str, boolean flag) {
		if (str == null) {
			return null;
		}
		if (str.length() == 0) {
			return str;
		}
		StringBuilder sb = new StringBuilder(str.length());
		if (flag) {
			sb.append(Character.toUpperCase(str.charAt(0)));
		} else {
			sb.append(Character.toLowerCase(str.charAt(0)));
		}
		if (str.length() > 1) {
			sb.append(str.substring(1));
		}
		return sb.toString();
	}
	
	public static String upperCaseToUnderLine(String str) {
		if (str == null) {
			return null;
		}
		StringBuilder sb = new StringBuilder(str.length());
		for (char c : str.toCharArray()) {
			if (Character.isLowerCase(c)) {
				sb.append(c);
			} else {
				if (sb.length() != 0) {
					sb.append('_');
				}
				sb.append(Character.toLowerCase(c));
			}
		}
		return sb.toString();
	}
	
	public static String underLineToUpperCase(String str) {
		if (str == null) {
			return null;
		}
		StringBuilder sb = new StringBuilder(str.length());
		boolean upper = false;
		for (char c : str.toCharArray()) {
			if (c == '_') {
				upper = true;
				continue;
			}
			if (upper) {
				sb.append(Character.toUpperCase(c));
			} else {
				sb.append(c);
			}
			upper = false;
		}
		return sb.toString();
	}
	
	public static String appendPrefix(String str, String prefix) {
		if (str == null) {
			return "";
		}
		if (!str.startsWith(prefix)) {
			return prefix + str;
		} else {
			return str;
		}
	}
	
	public static String removePrefix(String str, String prefix) {
		if (str == null) {
			return "";
		}
		if (str.startsWith(prefix)) {
			return str.substring(prefix.length());
		} else {
			return str;
		}
	}
	
	public static String appendPostfix(String str, String postfix) {
		if (str == null) {
			return "";
		}
		if (!str.endsWith(postfix)) {
			return str + postfix;
		} else {
			return str;
		}
	}
	
	public static String removePostfix(String str, String postfix) {
		if (str == null) {
			return "";
		}
		if (str.endsWith(postfix)) {
			return str.substring(0, str.length() - postfix.length());
		} else {
			return str;
		}
	}

    public static String getStrOfLength(char prefix, String str, int length) {
        StringBuilder builder = new StringBuilder(length);
        for (int i = 0; i < length; i++) {
            builder.append(prefix);
        }
        builder.append(str);
        String result = builder.substring(builder.length() - length);
        return result;
    }

    public static void main(String[] args) {
        System.out.println((long) Math.pow(10, 12));
        System.out.println(StringUtil.getStrOfLength('0', "123", 12));
    }
}
