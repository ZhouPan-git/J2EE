package com.yc;

/**
 * @Author zp
 * @Date 2023/8/2 18:53
 * @PackageName:com.yc
 * @ClassName: TrustManager
 * @Description:
 * @Version 1.0
 */

import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocketFactory;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.security.cert.X509Certificate;

public class BypassSSLCertificateVerificationExample {

    public static void main(String[] args) {
        // 绕过SSL证书验证
        bypassSSLCertificateVerification();

        // 测试HTTPS请求
        testHttpsRequests();
    }

    private static void bypassSSLCertificateVerification() {
        try {
            // 创建一个TrustManager数组，并设置为null，这样会绕过证书验证
            javax.net.ssl.TrustManager[] trustAllCertificates = new javax.net.ssl.TrustManager[] {
                    new javax.net.ssl.X509TrustManager() {
                        public X509Certificate[] getAcceptedIssuers() {
                            return null;
                        }
                        public void checkClientTrusted(X509Certificate[] certs, String authType) {
                        }
                        public void checkServerTrusted(X509Certificate[] certs, String authType) {
                        }
                    }
            };

            // 获取TLS类型的SSLContext实例，并初始化
            SSLContext sslContext = SSLContext.getInstance("TLS");
            sslContext.init(null, trustAllCertificates, new java.security.SecureRandom());

            // 获取SSLSocketFactory并设置为默认
            SSLSocketFactory socketFactory = sslContext.getSocketFactory();
            HttpsURLConnection.setDefaultSSLSocketFactory(socketFactory);

            // 设置主机名验证逻辑，这里简单地验证主机名
            HttpsURLConnection.setDefaultHostnameVerifier((hostname, session) -> {
                return true; // 绕过主机名验证，信任所有主机名
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void testHttpsRequests() {
        String[] urls = {
                "https://plugins.gradle.org/m2/io/spring/nohttp/nohttp-checkstyle/0.0.5.RELEASE/nohttp-checkstyle-0.0.5.RELEASE.pom",
                "https://jcenter.bintray.com/io/spring/nohttp/nohttp-checkstyle/0.0.5.RELEASE/nohttp-checkstyle-0.0.5.RELEASE.pom",
                "https://repo.spring.io/plugins-release/io/spring/nohttp/nohttp-checkstyle/0.0.5.RELEASE/nohttp-checkstyle-0.0.5.RELEASE.pom",
                "https://plugins.gradle.org/m2/org/jetbrains/kotlin/kotlin-compiler-embeddable/1.3.72/kotlin-compiler-embeddable-1.3.72.pom",
                "https://repo.spring.io/plugins-release/org/jetbrains/kotlin/kotlin-compiler-embeddable/1.3.72/kotlin-compiler-embeddable-1.3.72.pom",
                "https://plugins.gradle.org/m2/org/jetbrains/kotlin/kotlin-annotation-processing-gradle/1.3.72/kotlin-annotation-processing-gradle-1.3.72.pom",
                "https://jcenter.bintray.com/org/jetbrains/kotlin/kotlin-annotation-processing-gradle/1.3.72/kotlin-annotation-processing-gradle-1.3.72.pom",
                "https://plugins.gradle.org/m2/com/google/code/gson/gson/2.8.5/gson-2.8.5.pom",
                "https://jcenter.bintray.com/com/google/code/gson/gson/2.8.5/gson-2.8.5.pom",
                "https://repo.spring.io/plugins-release/com/google/code/gson/gson/2.8.5/gson-2.8.5.pom"
                // 添加更多URL，每个URL代表一系列需要绕过验证的HTTPS请求
        };

        for (String url : urls) {
            try {
                URL endpointUrl = new URL(url);
                HttpsURLConnection conn = (HttpsURLConnection) endpointUrl.openConnection();
                conn.setRequestMethod("GET");

                // 处理响应
                BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
                String line;
                while ((line = reader.readLine()) != null) {
                    System.out.println(line);
                }
                reader.close();

                conn.disconnect();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
