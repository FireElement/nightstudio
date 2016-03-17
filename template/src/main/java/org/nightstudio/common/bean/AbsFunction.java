/**
 * $Id$
 * Copyright 2009-2010 Oak Pacific Interactive. All rights reserved.
 */
package org.nightstudio.common.bean;

import org.nightstudio.common.face.action.spi.NSWebAction;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * @author xuezhu.cao Initial Created at 2012-2-16
 */
public class AbsFunction {
    public static String REF = "Function";

    private String funcCode;
    private String funcName;
    private Class<? extends NSWebAction> action;
    private List<AbsFunction> subFunctions;

    private static Map<String, AbsFunction> functionMap = new HashMap<String, AbsFunction>(50);

    /**
     * @param funcCode
     * @param funcName
     */
    public AbsFunction(String funcCode, String funcName, Class<? extends NSWebAction> action) {
        super();
        this.funcCode = funcCode;
        this.funcName = funcName;
        this.action = action;
        this.subFunctions = new ArrayList<AbsFunction>(10);
        functionMap.put(this.funcCode, this);
    }

    public AbsFunction(String funcCode, String funcName, Class<? extends NSWebAction> action, AbsFunction father) {
        this(funcCode, funcName, action);
        if (father != null) {
            father.addSubFunction(this);
        }
    }

    public String getFuncCode() {
        return funcCode;
    }

    public void setFuncCode(String funcCode) {
        this.funcCode = funcCode;
    }

    public String getFuncName() {
        return funcName;
    }

    public void setFuncName(String funcName) {
        this.funcName = funcName;
    }

    public Class<? extends NSWebAction> getAction() {
        return action;
    }

    public void setAction(Class<? extends NSWebAction> action) {
        this.action = action;
    }

    public List<AbsFunction> getSubFunctions() {
        return subFunctions;
    }


    public void setSubFunctions(List<AbsFunction> subFunctions) {
        this.subFunctions = subFunctions;
    }

    public void addSubFunction(AbsFunction function) {
        if (function == null) {
            return;
        }
        if (this.subFunctions == null) {
            this.subFunctions = new ArrayList<AbsFunction>(10);
        }
        this.subFunctions.add(function);
    }

    public boolean hasSubFunctions() {
        if (this.subFunctions == null || this.subFunctions.size() == 0) {
            return false;
        }
        return true;
    }

    public static AbsFunction getFunction(String funcCode) {
        return functionMap.get(funcCode);
    }

}
