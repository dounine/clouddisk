package com.dounine.clouddisk360.pool;

import org.apache.http.HttpClientConnection;
import org.apache.http.HttpHost;
import org.apache.http.client.protocol.HttpClientContext;
import org.apache.http.config.Registry;
import org.apache.http.config.RegistryBuilder;
import org.apache.http.conn.ConnectTimeoutException;
import org.apache.http.conn.ConnectionRequest;
import org.apache.http.conn.routing.HttpRoute;
import org.apache.http.conn.socket.ConnectionSocketFactory;
import org.apache.http.conn.socket.PlainConnectionSocketFactory;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.ssl.SSLContextBuilder;
import org.apache.http.ssl.SSLContexts;
import org.apache.http.ssl.TrustStrategy;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLSession;
import java.io.IOException;
import java.security.cert.X509Certificate;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

public class PoolingHttpClientConnection {

	private static final Logger LOGGER = LoggerFactory.getLogger(PoolingHttpClientConnection.class);

	private static PoolingHttpClientConnectionManager CM;
	
	static{
		initHttpClientConnectionManager();
	}

	public static void initHttpClientConnectionManager() {
		if(null!=CM){
			return;
		}
		try {
			final SSLContextBuilder builder = SSLContexts.custom();
			builder.loadTrustMaterial(null, new TrustStrategy() {
				@Override
				public boolean isTrusted(X509Certificate[] chain, String authType)
						throws java.security.cert.CertificateException {
					return true;
				}
			});

			final SSLConnectionSocketFactory sslsf = new SSLConnectionSocketFactory(builder.build(), new HostnameVerifier() {
				@Override
				public boolean verify(String hostname, SSLSession session) {
					return true;
				}
			});
			final Registry<ConnectionSocketFactory> socketFactoryRegistry = RegistryBuilder.<ConnectionSocketFactory> create()
					.register("https", sslsf).register("http", new PlainConnectionSocketFactory()).build();
			CM = new PoolingHttpClientConnectionManager(socketFactoryRegistry);
		} catch (Exception e) {
			LOGGER.error("Error",e);
		}

		CM.setMaxTotal(200);// 设置最大连接数
		CM.setDefaultMaxPerRoute(20);// 设置每个路由默认连接数

		final String[] routes = new String[] {"yunpan.360.cn","c38.yunpan.360.cn"};
		final HttpClientContext context = HttpClientContext.create();
		for (final String url : routes) {
			final HttpRoute yunpanRoute = new HttpRoute(new HttpHost(url, 80));
			// 获取新的连接. 这里可能耗费很多时间
			ConnectionRequest connRequest = CM.requestConnection(yunpanRoute, null);
			CM.setMaxPerRoute(yunpanRoute, 100);
			// 10秒超时
			try {
				final HttpClientConnection conn = connRequest.get(3, TimeUnit.SECONDS);
				if (!conn.isOpen()) {
					LOGGER.info(url+" -> 路由连接中.");
					CM.connect(conn, yunpanRoute, 100,context);// httpClientContext在些初始化有问题
					LOGGER.info(url+" -> 路由已连接.");
					CM.routeComplete(conn, yunpanRoute, context);  
				}
			} catch (ConnectTimeoutException e) {
				LOGGER.error("连接超时");
			} catch (InterruptedException e) {
				LOGGER.error("Error",e);
			} catch (ExecutionException e) {
				LOGGER.error("Error",e);
			} catch (IOException e) {
				LOGGER.error("Error",e);
			}
		}

		new IdleConnectionMonitorThread(CM).start();// 连接回收策略
	}

	public static PoolingHttpClientConnectionManager getInstalce() {
		return CM;
	}

}
