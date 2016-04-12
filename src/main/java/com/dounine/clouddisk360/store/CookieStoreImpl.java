package com.dounine.clouddisk360.store;

import org.apache.http.client.CookieStore;
import org.apache.http.cookie.Cookie;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

/**
 * Created by huanghuanlai on 16/4/11.
 */
public class CookieStoreImpl implements CookieStore{

    private List<Cookie> cookies = new ArrayList<>(0);
    /**
     * 去重
     */
    private boolean removeRepeat = true;
    public CookieStoreImpl(List<Cookie> cookies){
        this.cookies = cookies;
    }
    public CookieStoreImpl(List<Cookie> cookies,boolean removeRepeat){
        this.cookies = cookies;
        this.removeRepeat = removeRepeat;
    }

    @Override
    public void addCookie(Cookie cookie) {
        if(removeRepeat){
            Iterator<Cookie> ci = cookies.iterator();
            while(ci.hasNext()){
                if(ci.next().getName().equals(cookie.getName())){
                    ci.remove();
                }
            }
        }
        cookies.add(cookie);
    }

    @Override
    public List<Cookie> getCookies() {
        return cookies;
    }

    @Override
    public boolean clearExpired(Date date) {
        Iterator<Cookie> ci = cookies.iterator();
        while(ci.hasNext()){
            if(ci.next().getExpiryDate().equals(date)){
                ci.remove();
                return true;
            }
        }
        return false;
    }

    @Override
    public void clear() {
        cookies.clear();
    }
}
