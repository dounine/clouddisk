package com.dounine.clouddisk360.pool;

import org.apache.http.conn.HttpClientConnectionManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.TimeUnit;

public class IdleConnectionMonitorThread extends Thread{

    private static final Logger LOGGER = LoggerFactory.getLogger(IdleConnectionMonitorThread.class);
	private final HttpClientConnectionManager poolingHttpClientConnectionManager;  
    private boolean shutdown;
      
    public IdleConnectionMonitorThread(final HttpClientConnectionManager poolingHttpClientConnectionManager) {
        super();  
        this.poolingHttpClientConnectionManager = poolingHttpClientConnectionManager;  
    }  
  
    @Override  
    public void run() {  
        try {  
            while (!shutdown) {  
                synchronized (this) {  
                    wait(5000);  
                    // Close expired connections  
                    poolingHttpClientConnectionManager.closeExpiredConnections();  
                    // Optionally, close connections  
                    // that have been idle longer than 30 sec  
                    poolingHttpClientConnectionManager.closeIdleConnections(30, TimeUnit.SECONDS);  
                }  
            }  
        } catch (InterruptedException ex) {
            LOGGER.error("Error",ex);
            // terminate  
        }  
    }  
      
    public void shutdown() {  
        shutdown = true;  
        synchronized (this) {  
            notifyAll();  
        }  
    }  
	
}
