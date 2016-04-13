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
import java.net.URLEncoder;

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

    public static String getContent(String url, int method, Object... params) throws IOException, NSException {
        if (method == HttpClientConstant.Method.POST) {
            return getPostContent(url, params);
        } else {
            return getGetContent(url, params);
        }
    }

    public static String getGetContent(String url, Object... params) throws IOException, NSException {
        StringBuilder builder = new StringBuilder(100);
        builder.append(url);
        if (params != null && params.length > 1) {
            builder.append("?");
            for (int i = 0; i < params.length; i += 2) {
                if (i + 1 >= params.length) {
                    break;
                }
                builder.append(URLEncoder.encode(params[i].toString(), HttpClientConstant.CHARSET))
                        .append("=")
                        .append(URLEncoder.encode(params[i + 1].toString(), HttpClientConstant.CHARSET))
                        .append("&");
            }
            builder.setLength(builder.length() - 1);
        }
        return getContent(builder.toString());
    }

    public static String getPostContent(String url, Object... params) throws IOException, NSException {
        PostMethod postMethod = new UTF8PostMethod(url);
        postMethod.getParams().setParameter(HttpMethodParams.HTTP_CONTENT_CHARSET, HttpClientConstant.CHARSET);

        if (params != null && params.length > 1) {
            NameValuePair[] data = new NameValuePair[params.length / 2];
            NameValuePair pair;
            for (int i = 0; i < params.length; i += 2) {
                if (i + 1 >= params.length) {
                    break;
                }
                pair = new NameValuePair();
                pair.setName(params[i].toString());
                pair.setValue(params[i + 1].toString());
                data[i] = pair;
            }
            postMethod.setRequestBody(data);
        }
        return getContent(postMethod);
    }

    public static String getContent(String url) throws IOException, NSException {
        if (logger.isDebugEnabled()) {
            logger.debug("get url: " + url);
        }
        GetMethod getMethod = new GetMethod(url);
        return getContent(getMethod);
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
