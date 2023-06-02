package com.ruoyi.sd.kit;

import org.apache.http.ssl.SSLContextBuilder;
import org.apache.http.ssl.TrustStrategy;

import javax.net.ssl.SSLContext;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.charset.Charset;
import java.rmi.RemoteException;
import java.security.KeyManagementException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.time.Duration;
import java.util.Map;

/**
 * HttpClientByJDk11
 *
 * @author tao
 * @version 1.0
 * @description jdk自带httpclient工具类
 * @date 2023/4/4 15:23
 */
public class HttpClientByJDk11 {
    private static Charset getDefaultCharset() {
        return Charset.forName("utf-8");
    }

    /**
     * get请求
     *
     * @param url
     * @param header
     * @return
     */
    public static String doGet(String url, Map<String, String> header) throws RemoteException {
        var builder = HttpRequest.newBuilder().uri(URI.create(url)).GET();
        buildHeader(header, builder);
        return execute(builder, getDefaultCharset());
    }

    /**
     * post请求
     *
     * @param url
     * @param header
     * @param body
     * @return
     */
    public static String doPost(String url, Map<String, String> header, String body) throws RemoteException {
        var bodyPublisher = HttpRequest.BodyPublishers.ofString(body, getDefaultCharset());
        var builder = HttpRequest.newBuilder().uri(URI.create(url)).POST(bodyPublisher);
        buildHeader(header, builder);
        return execute(builder, getDefaultCharset());
    }

    /**
     * form表单
     *
     * @param url
     * @param header
     * @param pd
     * @return
     */
    public static String doPostForm(String url, Map<String, String> header, Map<String, String> pd, Charset charset) throws RemoteException {
        if (charset == null) {
            charset = getDefaultCharset();
        }
        var nov = new StringBuilder();
        if (pd != null) {
            pd.forEach((k, v) -> {
                nov.append(k);
                nov.append("=");
                nov.append(v);
                nov.append("&");
            });
        }
        nov.append("tmp=tmp");
        var bodyPublisher = HttpRequest.BodyPublishers.ofString(nov.toString(), charset);
        var builder = HttpRequest.newBuilder().uri(URI.create(url)).POST(bodyPublisher);
        buildHeader(header, builder);
        builder.setHeader("Content-Type", "application/x-www-form-urlencoded");
        return execute(builder, charset);
    }


    private static void buildHeader(Map<String, String> header, HttpRequest.Builder builder) {
        if (header != null && header.size() > 0) {
            for (String key : header.keySet()) {
                builder.setHeader(key, header.get(key));
            }
        }
        builder.setHeader("Content-Type", "application/json");
    }

    /**
     * 针对某些https请求，忽略证书验证
     *
     * @return
     * @throws NoSuchAlgorithmException
     * @throws KeyStoreException
     * @throws KeyManagementException
     */
    private static SSLContext getSSLContext() throws NoSuchAlgorithmException, KeyStoreException, KeyManagementException {
        //使用 loadTrustMaterial() 方法实现一个信任策略，信任所有证书
        var sslContext = new SSLContextBuilder().loadTrustMaterial(null, new TrustStrategy() {
            // 信任所有
            @Override
            public boolean isTrusted(X509Certificate[] x509Certificates, String s) throws CertificateException {
                return true;
            }
        }).build();
        return sslContext;
    }

    private static String execute(HttpRequest.Builder builder, Charset charset) throws RemoteException {
        var request = builder.timeout(Duration.ofMinutes(3)).build();
        try {
            var client = HttpClient.newBuilder().sslContext(getSSLContext()).version(HttpClient.Version.HTTP_1_1).build();
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString(charset));
            String result = null;
            if (response.statusCode() == 200) {
                result = response.body();
            } else {
                result = response.body();
            }
            return result;
        } catch (Exception e) {
            throw new RemoteException("调用失败");
        }
    }
}
