package org.nightstudio.common.servlet.tags;

import org.apache.log4j.Logger;
import org.nightstudio.common.bean.Pagination;
import org.nightstudio.common.util.constant.ApplicationConstant;
import org.nightstudio.common.util.constant.RequestConstant;
import org.nightstudio.common.util.session.SessionUtil;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspTagException;
import javax.servlet.jsp.tagext.TagSupport;
import java.io.IOException;

public class PaginationTag extends TagSupport {
	private static Logger logger = Logger.getLogger(PaginationTag.class);
	private String action;
	private String param;
	private String form;
	
	@Override
	public int doStartTag() throws JspException {
		HttpServletRequest request = (HttpServletRequest) this.pageContext.getRequest();
		Pagination pagination = SessionUtil.getPagination(request);
		if (pagination == null) {
			return EVAL_BODY_INCLUDE;
		}
		long currPage = pagination.getCurrPage();
		long pageCount = pagination.getPageCount();
		int sideLinkCount = (ApplicationConstant.PAGINATION_LINK_COUNT - 1) / 2;
		long minLink = currPage - sideLinkCount;
		if (minLink < 1) {
			minLink = 1;
		}
		long maxLink = currPage + sideLinkCount;
		if (maxLink < (minLink - 1 + ApplicationConstant.PAGINATION_LINK_COUNT)) {
			maxLink = minLink - 1 + ApplicationConstant.PAGINATION_LINK_COUNT;
		}
		if (maxLink > pageCount) {
			maxLink = pageCount;
		}
		StringBuilder builder = new StringBuilder(500);
		if (form != null && !"".equals(form)) {
			builder.append("<script type=\"text/javascript\">");
			builder.append("	function submitPagination(action) {");
			builder.append("		document.").append(form).append(".action = action;");
			builder.append("		document.").append(form).append(".method = \"post\";");
			builder.append("		document.").append(form).append(".submit();");
			builder.append("		return false;");
			builder.append("	}");
			builder.append("</script>");
		}
		builder.append("<div>");
		String link;
		if (pagination.getCurrPage() != 1 && pagination.getCurrPage() != 0) {
			link = this.action + "?" + RequestConstant.PARAM_KEY_CURRENT_PAGE + "=1";
			if (param != null && !"".equals(param)) {
				link += "&" + param;
			}
			builder.append("<a ");
			if (form != null && !"".equals(form)) {
				builder.append("href=\"#\" onclick=\"return submitPagination(\'").append(link).append("\');\"");
			} else {
				builder.append("href=\"").append(link).append("\"");
			}
			builder.append(">").append("First</a>");
		} else {
			builder.append("First");
		}
		builder.append("&nbsp;");
		if (pagination.getCurrPage() != 1 && pagination.getCurrPage() != 0) {
			link = this.action + "?" + RequestConstant.PARAM_KEY_CURRENT_PAGE + "=" + (pagination.getCurrPage() - 1);
			if (param != null && !"".equals(param)) {
				link += "&" + param;
			}
			builder.append("<a ");
			if (form != null && !"".equals(form)) {
				builder.append("href=\"#\" onclick=\"return submitPagination(\'").append(link).append("\');\"");
			} else {
				builder.append("href=\"").append(link).append("\"");
			}
			builder.append(">").append("Prev</a>");
		} else {
			builder.append("Prev");
		}
		builder.append("&nbsp;");
		if (maxLink >= minLink) {
			for (long i = minLink; i <= maxLink; i++) {
				if (pagination.getCurrPage() != i) {
					link = this.action + "?" + RequestConstant.PARAM_KEY_CURRENT_PAGE + "=" + i;
					if (param != null && !"".equals(param)) {
						link += "&" + param;
					}
					builder.append("<a ");
					if (form != null && !"".equals(form)) {
						builder.append("href=\"#\" onclick=\"return submitPagination(\'").append(link).append("\');\"");
					} else {
						builder.append("href=\"").append(link).append("\"");
					}
					builder.append(">").append(i).append("</a>");
				} else {
					builder.append(i);
				}
				builder.append("&nbsp;");
			}
		} else {
			builder.append("1&nbsp;");
		}
		if (pagination.getCurrPage() != pageCount) {
			link = this.action + "?" + RequestConstant.PARAM_KEY_CURRENT_PAGE + "=" + (pagination.getCurrPage() + 1);
			if (param != null && !"".equals(param)) {
				link += "&" + param;
			}
			builder.append("<a ");
			if (form != null && !"".equals(form)) {
				builder.append("href=\"#\" onclick=\"return submitPagination(\'").append(link).append("\');\"");
			} else {
				builder.append("href=\"").append(link).append("\"");
			}
			builder.append(">").append("Next</a>");
		} else {
			builder.append("Next");
		}
		builder.append("&nbsp;");
		if (pagination.getCurrPage() != pageCount) {
			link = this.action + "?" + RequestConstant.PARAM_KEY_CURRENT_PAGE + "=" + pageCount;
			if (param != null && !"".equals(param)) {
				link += "&" + param;
			}
			builder.append("<a ");
			if (form != null && !"".equals(form)) {
				builder.append("href=\"#\" onclick=\"return submitPagination(\'").append(link).append("\');\"");
			} else {
				builder.append("href=\"").append(link).append("\"");
			}
			builder.append(">").append("Last</a>");
		} else {
			builder.append("Last");
		}
		builder.append("</div>");
		try {
			pageContext.getOut().write(builder.toString());
		} catch (IOException e) {
			logger.warn("", e);
			throw new JspTagException(e.getMessage());
		}
		return EVAL_BODY_INCLUDE;
	}

	public String getAction() {
		return action;
	}

	public void setAction(String action) {
		this.action = action;
	}
	
	public String getParam() {
		return param;
	}

	public void setParam(String param) {
		this.param = param;
	}
	
	public String getForm() {
		return form;
	}

	public void setForm(String form) {
		this.form = form;
	}
}
