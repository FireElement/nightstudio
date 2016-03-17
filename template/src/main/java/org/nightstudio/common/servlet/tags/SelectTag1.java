/**
 * $Id: SelectTag1.java 81035 2012-05-10 11:29:37Z xuezhu.cao@XIAONEI.OPI.COM $
 * Copyright 2009-2010 Oak Pacific Interactive. All rights reserved.
 */
package org.nightstudio.common.servlet.tags;

import org.apache.log4j.Logger;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspTagException;
import javax.servlet.jsp.tagext.TagSupport;
import java.io.IOException;


/**
 * @author xuezhu.cao Initial Created at 2012-5-9
 */
public class SelectTag1 extends TagSupport {
    private static final long serialVersionUID = -8780922284683308985L;
    private static Logger logger = Logger.getLogger(SelectTag1.class);
    private String name;
    private Object[] labels;
    private Object[] vvalues;
    private Object selectedValue;
    private boolean haveBlank = false;
    
    @Override
    public int doStartTag() throws JspException {
        try {
            StringBuilder builder = new StringBuilder();
            builder.append("<select name=\"").append(name).append("\">");
            if (haveBlank) {
                builder.append("<option value=\"\"></option>");
            }
            if (labels != null && vvalues != null) {
                int i = 0;
                while (i < labels.length && i < vvalues.length) {
                    if (labels[i] == null || vvalues[i] == null) {
                        continue;
                    }
                    builder.append("<option value=\"").append(vvalues[i].toString()).append("\"");
                    if (selectedValue != null && selectedValue.toString().equals(vvalues[i].toString())) {
                        builder.append(" selected ");
                    }
                    builder.append(">");
                    builder.append(labels[i].toString());
                    builder.append("</option>");    
                    i++;
                }
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
     * @return the name
     */
    public String getName() {
        return name;
    }

    
    /**
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    
    /**
     * @return the labels
     */
    public Object[] getLabels() {
        return labels;
    }

    
    /**
     * @param labels the labels to set
     */
    public void setLabels(Object[] labels) {
        this.labels = labels;
    }

    
    /**
     * @param values the values to set
     */
    public void setVvalues(Object[] vvalues) {
        this.vvalues = vvalues;
    }

    
    /**
     * @return the selectedValue
     */
    public Object getSelectedValue() {
        return selectedValue;
    }

    
    /**
     * @param selectedValue the selectedValue to set
     */
    public void setSelectedValue(Object selectedValue) {
        this.selectedValue = selectedValue;
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

