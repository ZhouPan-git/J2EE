package com.yc;

import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.conn.ConnectTimeoutException;
import org.apache.http.conn.HttpHostConnectException;
import org.apache.http.conn.ssl.SSLContexts;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;



public class HttpClientUtil {
    private final org.slf4j.Logger log = org.slf4j.LoggerFactory.getLogger(HttpClientUtil.class.getName());
    private final int DEFAULT_TIMEOUT = 10000;

    private static HttpClientUtil ins;

    private HttpClient client;
    private int timeout = DEFAULT_TIMEOUT;

    private static int maxConnTotal = 500;   //最大不要超过1000
    private static int maxConnPerRoute = 200;//实际的单个连接池大小，

    private HttpClientUtil() {
        if (client == null) {
            client = HttpClients.createDefault();
        }
    }

    public static HttpClientUtil getInstance() {
        if (ins == null) {
            synchronized (HttpClientUtil.class) {
                if (ins == null) {
                    ins = new HttpClientUtil();
                }
            }
        }
        return ins;
    }

    public String doGetCustom(String uri) {
        String json = null;
        HttpResponse response = null;
        try {
            HttpGet request = new HttpGet(uri);
            RequestConfig config = RequestConfig.custom().setConnectionRequestTimeout(timeout)
                    .setConnectTimeout(timeout).setSocketTimeout(timeout).build();

            request.setConfig(config);
            client = HttpClients.custom().setDefaultRequestConfig(config)
                    .setMaxConnTotal(maxConnTotal)
                    .setMaxConnPerRoute(maxConnPerRoute).build();
            response = client.execute(request);
            if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
                json = EntityUtils.toString(response.getEntity(), StandardCharsets.UTF_8);
                if(log.isDebugEnabled()) {
                    log.debug("doGetCustom to {} response: {}", uri, json);
                }
            } else {
                log.warn("doGetCustom to {} response status code: {}, payload: {}", new Object[]{uri,
                        response.getStatusLine().getStatusCode(),
                        EntityUtils.toString(response.getEntity(), StandardCharsets.UTF_8)});
            }
        } catch (ConnectTimeoutException e) {
            log.error("doGetCustom to {} raise ConnectTimeoutException, {}", uri, e.getMessage());
        } catch (HttpHostConnectException e) {
            log.error("doGetCustom to {} raise HttpHostConnectException, {}", uri, e.getMessage());
        } catch (Exception e) {
            log.error("doGetCustom to {} raise exception ,{}", uri, e.getMessage());
        } finally {
            try {
                if (response != null) {
                    EntityUtils.consume(response.getEntity());
                }
            } catch (IOException e) {
                log.error("doGetCustom to {} raise final exception ,{}", uri, e.getMessage());
            }
        }
        return json;
    }

    public String doPostWithJsonResult(String uri, Map<String, String> paramMap) {
        String json = null;
        HttpResponse response = null;
        try {
            HttpPost request = new HttpPost(uri);
            RequestConfig config = RequestConfig.custom().setConnectionRequestTimeout(timeout)
                    .setConnectTimeout(timeout).setSocketTimeout(timeout).build();
            request.setConfig(config);
            List<NameValuePair> params = new ArrayList<>();
            if (paramMap != null && !paramMap.isEmpty()) {
                for (String key : paramMap.keySet()) {
                    params.add(new BasicNameValuePair(key, paramMap.get(key)));
                }
                request.setEntity(new UrlEncodedFormEntity(params, "UTF-8"));
            }
            response = client.execute(request);
            log.debug("Response status code: {}", response.getStatusLine().getStatusCode());
            if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
                json = EntityUtils.toString(response.getEntity(), StandardCharsets.UTF_8);
                if (log.isDebugEnabled()) {
                    log.debug("doPostWithJsonResult to {} response:: {}", uri, json);
                }
            } else {
                log.warn("doPostWithJsonResult to {} response statusCode: {}, payload: {}", new Object[]{uri,
                        response.getStatusLine().getStatusCode(),
                        EntityUtils.toString(response.getEntity(), StandardCharsets.UTF_8)});
            }
            request.releaseConnection();
        } catch (ConnectTimeoutException e) {
            log.error("doPostWithJsonResult to {} raise ConnectTimeoutException, {}", uri, e.getMessage());
        } catch (HttpHostConnectException e) {
            log.error("doPostWithJsonResult to {} raise HttpHostConnectException, {}", uri, e.getMessage());
        } catch (Exception e) {
            log.error("doPostWithJsonResult to {} raise exception ,{}", uri, e.getMessage());
            return null;
        } finally {
            try {
                if (response != null) {
                    EntityUtils.consume(response.getEntity());
                }
            } catch (IOException e) {
                log.error("doPostWithJsonResult to {} raise final exception ,{}", uri, e.getMessage());
            }
        }
        return json;
    }

    public String doPostWithJsonResult(String uri, String jsonParameters) {
        HttpPost request = new HttpPost(uri);
        RequestConfig config = RequestConfig.custom().setConnectionRequestTimeout(timeout)
                .setConnectTimeout(timeout).setSocketTimeout(timeout).build();
        request.setConfig(config);
        request.setEntity(new StringEntity(jsonParameters, ContentType.APPLICATION_JSON));
        HttpResponse response = null;
        String responseStr = null;
        try {
            response = client.execute(request);
            if (response.getStatusLine().getStatusCode() == 200) {//如果是200  表示成功
                responseStr = EntityUtils.toString(response.getEntity(), StandardCharsets.UTF_8);
                if (log.isDebugEnabled()) {
                    log.debug("doPostWithJsonResult to {} response:: {}", uri, responseStr);
                }
            } else {
                log.warn("doPostWithJsonResult to {} response statusCode: {}, payload: {}", new Object[]{uri,
                        response.getStatusLine().getStatusCode(), EntityUtils.toString(response.getEntity())});
            }
        } catch (ConnectTimeoutException e) {
            log.error("doPostWithJsonResult to {} raise ConnectTimeoutException, {}", uri, e.getMessage());
        } catch (HttpHostConnectException e) {
            log.error("doPostWithJsonResult to {} raise HttpHostConnectException, {}", uri, e.getMessage());
        } catch (Exception e) {
            log.error("doPostWithJsonResult to {} raise exception ,{}", uri, e.getMessage());
        }
        return responseStr;
    }

    public String doPost(String url, ArrayList<BasicNameValuePair> data) {
        HttpResponse response = null;
        try {
            //UrlEncodedFormEntity这个类是用来把输入数据编码成合适的内容
            //两个键值对，被UrlEncodedFormEntity实例编码后变为如下内容：param1=value1¶m2=value2
            //首先将参数设置为utf-8的形式
            UrlEncodedFormEntity entity = new UrlEncodedFormEntity(data,
                    HTTP.UTF_8);
            String result = "";
            HttpPost post = new HttpPost(url);//post方式
            RequestConfig config = RequestConfig.custom().setConnectionRequestTimeout(timeout)
                    .setConnectTimeout(timeout).setSocketTimeout(timeout).build();

            post.setConfig(config);
            post.setEntity(entity);//带上参数
            client = HttpClients.custom().setDefaultRequestConfig(config)
                    .setSslcontext(SSLContexts.custom().loadTrustMaterial(null, (x509Certificates, s) -> true).build())
                    .setMaxConnTotal(maxConnTotal)
                    .setMaxConnPerRoute(maxConnPerRoute).build();
            log.info("headers {}",post.getAllHeaders());
            response = client.execute(post);//响应结果
            if (response.getStatusLine().getStatusCode() == 200) {
                //把结果取出来是一个STRING类型的
                result = EntityUtils.toString(response.getEntity());
                if (log.isDebugEnabled()) {
                    log.debug("doPost to {} response: {}", url, result);
                }
            } else {
                log.warn("doPost to {} response statusCode: {}, payload: {}", new Object[]{url, response.getStatusLine().getStatusCode(),
                        EntityUtils.toString(response.getEntity())});
            }

            return result;
        } catch (ConnectTimeoutException e) {
            log.error("doPost to {} raise ConnectTimeoutException, {}", url, e.getMessage());
        } catch (HttpHostConnectException e) {
            log.error("doPost to {} raise HttpHostConnectException, {}", url, e.getMessage());
        } catch (Exception e) {
            log.error("doPost to {} raise exception ,{}", url, e.getMessage());
        } finally {
            try {
                if (response != null) {
                    EntityUtils.consume(response.getEntity());
                }
            } catch (IOException e) {
                log.error("doPost to {} raise final exception ,{}", url, e.getMessage());
            }
        }
        return null;
    }

    /**
     * @return the timeout
     */
    public int getTimeout() {
        return timeout;
    }

    /**
     * @param timeout the timeout to set
     */
    public void setTimeout(int timeout) {
        this.timeout = timeout;
    }

    public String doPost(String url, String jsonStr, int timeout) {
        setTimeout(timeout);
        return doPostWithJsonResult(url, jsonStr);
    }

    public String doGetCustom(String url, int timeout) {
        setTimeout(timeout);
        return doGetCustom(url);
    }

    public static void main(String[] args) {
        try {
//            throw new NullArgumentException(null);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return;
        } finally {
            System.out.println(111);
        }
    }


}