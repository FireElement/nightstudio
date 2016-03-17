package org.nightstudio.common.util.response;

import javax.servlet.http.HttpServletResponse;

public class ResponseUtil {
	public static void setJSONResponse(HttpServletResponse response) {
		response.setHeader("contentType", "text/json-comment-filtered");
		response.setContentType("text/json-comment-filtered");
	}
	
	public static void setXmlResponse(HttpServletResponse response) {
		response.setHeader("contentType", "text/xml");
		response.setContentType("text/xml");
	}
	
	public static String escapeHTML(String html) {
		if (html == null || "".equals(html)) {
			return "";
		}
		String result = html.replaceAll("&", "&amp;");
		result = result.replaceAll(">", "&gt;");
		result = result.replaceAll("<", "&lt;");
		result = result.replaceAll("'", "&apos;");
		result = result.replaceAll("\"", "&quot;");
		return result;
	}
	
	public static String unescapeHTML(String str) {
		if (str == null || "".equals(str)) {
			return "";
		}
		String result = str.replaceAll("&amp;", "&");
		result = result.replaceAll("&gt;", ">");
		result = result.replaceAll("&lt;", "<");
		result = result.replaceAll("&apos;", "'");
		result = result.replaceAll("&quot;", "\"");
		return result;
	}
	
	public static String escapeXML(String xml) {
		if (xml == null || "".equals(xml)) {
			return "";
		}
		String result = xml.replaceAll("&", "&amp;");
		result = result.replaceAll(">", "&gt;");
		result = result.replaceAll("<", "&lt;");
		result = result.replaceAll("'", "&apos;");
		result = result.replaceAll("\"", "&quot;");
		return result;
	}
	
	public static String unescapeXML(String str) {
		if (str == null || "".equals(str)) {
			return "";
		}
		String result = str.replaceAll("&amp;", "&");
		result = result.replaceAll("&gt;", ">");
		result = result.replaceAll("&lt;", "<");
		result = result.replaceAll("&apos;", "'");
		result = result.replaceAll("&quot;", "\"");
		return result;
	}
	
	public static String escapeJSON(String json) {
		if (json == null || "".equals(json)) {
			return "";
		}
		String result = json.replaceAll("\\\\", "\\\\\\\\");
		result = result.replaceAll("\"", "\\\\\"");
		result = result.replaceAll("\'", "\\\\\'");
		return result;
	}
}
