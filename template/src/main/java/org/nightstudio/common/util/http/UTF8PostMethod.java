package org.nightstudio.common.util.http;

import org.apache.commons.httpclient.methods.PostMethod;
import org.nightstudio.common.util.constant.HttpClientConstant;

public class UTF8PostMethod extends PostMethod {   
	
    public UTF8PostMethod(String url){     
        super(url);     
    } 
    
    @Override    
    public String getRequestCharSet() {     
        return HttpClientConstant.CHARSET;
    }     

}
