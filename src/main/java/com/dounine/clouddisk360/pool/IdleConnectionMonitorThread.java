package com.dounine.clouddisk360.pool;

import java.util.concurrent.TimeUnit;

import org.apache.http.conn.HttpClientConnectionManager;

public class IdleConnectionMonitorThread extends Thread{

	private final HttpClientConnectionManager poolingHttpClientConnectionManager;  
    private volatile boolean shutdown;  
      
    public IdleConnectionMonitorThread(HttpClientConnectionManager poolingHttpClientConnectionManager) {  
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
