/**
 * $Id: HttpUtil.java 71814 2012-03-13 10:15:30Z xuezhu.cao@XIAONEI.OPI.COM $
 * Copyright 2009-2011 Oak Pacific Interactive. All rights reserved.
 */
package org.nightstudio.common.util.http;

import org.apache.commons.httpclient.*;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.params.HttpMethodParams;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.nightstudio.common.util.constant.HttpClientConstant;
import org.nightstudio.common.util.exception.errorcode.ErrorCode;
import org.nightstudio.common.util.exception.sys.NSException;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Map;

/**
 * @author xuezhu.cao Initial Created at 2011-10-27
 */
public class HttpUtil {
    private static Log logger = LogFactory.getLog(HttpUtil.class);
    private static HttpClient client = null;

    protected static HttpClient getHttpClient() {
        if (client == null) {
            client = new HttpClient();

            client.getParams().setIntParameter("http.socket.timeout", HttpClientConstant.HTTP_SOCKET_TIMEOUT);
            client.getHttpConnectionManager().getParams().setConnectionTimeout(HttpClientConstant.HTTP_CONNECTION_TIMEOUT);
            client.getHttpConnectionManager().getParams().setSoTimeout(HttpClientConstant.HTTP_SO_TIMEOUT);
            client.getHttpConnectionManager().getParams().setParameter(HttpMethodParams.HTTP_CONTENT_CHARSET, HttpClientConstant.HTTP_CONTENT_CHARSET);
            client.getHttpConnectionManager().getParams().setBooleanParameter("http.protocol.expect-continue", false);
        }
        return client;
    }

    public static String getContent(String url) throws IOException, NSException {
        GetMethod getMethod = new GetMethod(url);
        return getContent(getMethod);
    }

    public static String getContent(String url, Map<String, String> params) throws IOException, NSException {
        PostMethod postMethod = new UTF8PostMethod(url);
        postMethod.getParams().setParameter(HttpMethodParams.HTTP_CONTENT_CHARSET, HttpClientConstant.CHARSET);

        if (params != null && params.size() > 0) {
            NameValuePair[] data = new NameValuePair[params.size()];
            String[] keys = params.keySet().toArray(new String[0]);
            NameValuePair pair;
            String key;
            for (int i = 0; i < params.size(); i++) {
                pair = new NameValuePair();
                key = keys[i];
                pair.setName(key);
                pair.setValue(params.get(key));
                data[i] = pair;
            }
            postMethod.setRequestBody(data);
        }
        return getContent(postMethod);
    }

    protected static String getContent(HttpMethod method) throws IOException, NSException {
        try {
            HttpClient httpClient = getHttpClient();

            method.getParams().setParameter(HttpMethodParams.SO_TIMEOUT, HttpClientConstant.HTTP_SO_TIMEOUT);

            int statusCode = httpClient.executeMethod(method);
            String content = getContent(method.getResponseBodyAsStream());
            if (statusCode != HttpStatus.SC_OK) {
                logger.warn(String.format("statusCode: %s; content %s", statusCode, content));
                throw new NSException(ErrorCode.HTTP_EXCEPTION, statusCode);
            }

            if (logger.isDebugEnabled()) {
                logger.debug("content: " + content);
            }

            return content;
        } catch (HttpException e) {
            logger.error("", e);
            throw e;
        } catch (NSException e) {
            logger.error("", e);
            throw e;
        } finally {
            method.releaseConnection();
        }
    }

    public static String getContent(InputStream is) throws IOException {
        InputStreamReader reader = null;

        try {
            char[] chars = new char[HttpClientConstant.HTTP_READ_BUFFER_SIZE];
            int length;
            reader = new InputStreamReader(is, HttpClientConstant.CHARSET);

            StringBuffer buffer = new StringBuffer(500);
            while ((length = reader.read(chars, 0, HttpClientConstant.HTTP_READ_BUFFER_SIZE)) != -1) {
                buffer.append(chars, 0, length);
            }

            return buffer.toString();
        } catch (IOException e) {
            logger.error("", e);
            throw e;
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e) {
                    logger.error("", e);
                }
            }
        }
    }
}
