package org.nightstudio.common.servlet.tags;

import org.apache.log4j.Logger;
import org.nightstudio.common.util.page.image.ImageUtil;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspTagException;
import javax.servlet.jsp.tagext.TagSupport;
import java.io.IOException;

public class ImageTag extends TagSupport {
	private static Logger logger = Logger.getLogger(CssTag.class);
	private String path;

	@Override
	public int doStartTag() throws JspException {
		try {
			String text = "<image src=\""
					+ ImageUtil.getImagePath((HttpServletRequest) this.pageContext.getRequest(), path)
					+ "\" />";
			pageContext.getOut().write(text);
		} catch (RuntimeException e) {
			logger.error("", e);
			throw e;
		} catch (IOException e) {
			logger.warn("", e);
			throw new JspTagException(e.getMessage());
		} catch (Exception e) {
			logger.error("", e);
			throw new JspTagException(e.getMessage());
		}
		return EVAL_BODY_INCLUDE;
	}
	
	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}
	
}
