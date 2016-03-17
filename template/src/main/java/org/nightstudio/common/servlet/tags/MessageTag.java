package org.nightstudio.common.servlet.tags;

import org.nightstudio.common.util.page.message.MessageUtil;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.BodyTagSupport;
import java.io.IOException;
import java.util.Collection;

public class MessageTag extends BodyTagSupport {
	private String key;
	private Object args;
	private String content;
	
	public void setKey(String key) {
		this.key = key;
	}

	public void setArgs(Object args) {
		this.args = args;
	}

	@Override
	public int doStartTag() throws JspException {
		content = key;
		return super.doStartTag();
	}
	
	@Override
	public int doAfterBody() throws JspException {
		try {
			content = readBodyContent();
		}
		catch (IOException e) {
			throw new JspException("", e);
		}
		return (SKIP_BODY);
	}

	@Override
	public int doEndTag() throws JspException {
		content = MessageUtil.m((HttpServletRequest) this.pageContext.getRequest(), content, this.resolveArgs(this.args));
		try {
			pageContext.getOut().write(content);
		} catch (IOException e) {
			throw new JspException("", e);
		}
		return super.doEndTag();
	}

	@SuppressWarnings("rawtypes")
	protected Object[] resolveArgs(Object args) throws JspException {
		if (args instanceof String) {
			return new String[]{(String) args};
		} else if (args instanceof Object[]) {
			return (Object[]) args;
		} else if (args instanceof Collection) {
			return ((Collection) args).toArray();
		} else if (args != null) {
			return new Object[] {args};
		} else {
			return null;
		}
	}
	
	protected String readBodyContent() throws IOException {
		return this.bodyContent.getString();
	}

	protected void writeBodyContent(String content) throws IOException {
		this.bodyContent.getEnclosingWriter().print(content);
	}
}
