package com.dounine.clouddisk360.pool;

import org.apache.http.conn.HttpClientConnectionManager;

import java.util.concurrent.TimeUnit;

public class IdleConnectionMonitorThread extends Thread{

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
            // terminate  
        	Thread.currentThread().interrupt();
        }  
    }  
      
    public void shutdown() {  
        shutdown = true;  
        synchronized (this) {  
            notifyAll();  
        }  
    }  
	
}
