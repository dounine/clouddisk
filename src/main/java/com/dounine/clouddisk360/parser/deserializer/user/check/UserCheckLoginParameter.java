package com.dounine.clouddisk360.parser.deserializer.user.check;

import com.dounine.clouddisk360.parser.deserializer.BaseParameter;

public class UserCheckLoginParameter extends BaseParameter{

    /**
     * cookie失效是否清除本地cookieStore文件
     */
    private Boolean clearCacheStore=false;
    /**
     * cookie失效是否清除cookie缓存变量
     */
    private Boolean clearCacheObject=false;

    public Boolean isClearCacheObject() {
        return clearCacheObject;
    }

    public void setClearCacheObject(Boolean clearCacheObject) {
        this.clearCacheObject = clearCacheObject;
    }

    public Boolean isClearCacheStore() {
        return clearCacheStore;
    }

    public void setClearCacheStore(Boolean clearCacheStore) {
        this.clearCacheStore = clearCacheStore;
    }
}
