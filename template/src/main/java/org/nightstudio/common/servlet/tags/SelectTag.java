/**
 * $Id$
 * Copyright 2009-2010 Oak Pacific Interactive. All rights reserved.
 */
package org.nightstudio.common.servlet.tags;

import org.apache.log4j.Logger;
import org.nightstudio.common.util.lang.ObjectUtil;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspTagException;
import javax.servlet.jsp.tagext.TagSupport;
import java.io.IOException;
import java.util.List;


/**
 * @author xuezhu.cao Initial Created at 2012-4-9
 */
public class SelectTag extends TagSupport {
    private static final long serialVersionUID = 3026390903291870436L;
    private static Logger logger = Logger.getLogger(SelectTag.class);
    private List<?> items;
    private String label;
    private String value;
    private String attrName;
    private boolean haveBlank;
    
    @Override
    public int doStartTag() throws JspException {
        try {
            HttpServletRequest request = (HttpServletRequest) this.pageContext.getRequest();
            
            StringBuilder builder = new StringBuilder();
            Object labelValue;
            Object valueValue;
            Object attrValue;
            builder.append("<select name=\"").append(attrName).append("\">");
            if (haveBlank) {
                builder.append("<option value=\"\"></option>");
            }
            for (Object item : items) { 
                labelValue = ObjectUtil.getFieldValue(item, label);
                valueValue = ObjectUtil.getFieldValue(item, value);
                attrValue = request.getAttribute(attrName);
                builder.append("<option value=\"").append(valueValue).append("\"");
                if (valueValue != null && attrValue != null && valueValue.toString().equals(attrValue.toString())) {
                    builder.append(" selected ");
                }
                builder.append(">");
                builder.append(labelValue);
                builder.append("</option>");                   
            }
            builder.append("</select>");
            
            pageContext.getOut().write(builder.toString());
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

    
    /**
     * @return the items
     */
    public List<?> getItems() {
        return items;
    }

    
    /**
     * @param items the items to set
     */
    public void setItems(List<?> items) {
        this.items = items;
    }

    
    /**
     * @return the label
     */
    public String getLabel() {
        return label;
    }

    
    /**
     * @param label the label to set
     */
    public void setLabel(String label) {
        this.label = label;
    }

    
    /**
     * @return the value
     */
    public String getValue() {
        return value;
    }

    
    /**
     * @param value the value to set
     */
    public void setValue(String value) {
        this.value = value;
    }


    
    /**
     * @return the attrName
     */
    public String getAttrName() {
        return attrName;
    }


    
    /**
     * @param attrName the attrName to set
     */
    public void setAttrName(String attrName) {
        this.attrName = attrName;
    }


    
    /**
     * @return the haveBlank
     */
    public boolean isHaveBlank() {
        return haveBlank;
    }


    
    /**
     * @param haveBlank the haveBlank to set
     */
    public void setHaveBlank(boolean haveBlank) {
        this.haveBlank = haveBlank;
    }
}
