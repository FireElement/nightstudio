package org.nightstudio.common.converter.json;

import org.nightstudio.common.util.constant.ApplicationConstant;
import org.nightstudio.common.util.constant.ResponseConstant;
import org.springframework.http.HttpOutputMessage;
import org.springframework.http.converter.HttpMessageNotWritableException;
import org.springframework.http.converter.json.MappingJacksonHttpMessageConverter;

import java.io.IOException;

/**
 * Created by caoxuezhu01 on 15-3-11.
 */
public class NSJacksonConverter extends MappingJacksonHttpMessageConverter {
    @Override
    protected void writeInternal(Object o, HttpOutputMessage outputMessage) throws IOException, HttpMessageNotWritableException {
        outputMessage.getHeaders().set(ResponseConstant.HEADER_ACCESS_CONTROL, ApplicationConstant.CLIENT_DOMAIN);
        super.writeInternal(o, outputMessage);
    }
}
