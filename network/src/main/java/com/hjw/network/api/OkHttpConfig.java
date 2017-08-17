package com.hjw.network.api;

import com.hjw.base.utils.StringUtils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by hjw on 2017/8/10.17:17
 */

public class OkHttpConfig {
    /*重连次数 总连接次数为 1+retryTimes 次*/
    private int retryTimes;

    /*是否需要缓存处理*/
    private boolean cache = false;

    /*备用服务器url*/
    private List<String> switchServerDomains;

    /*基础服务器url*/
    private String serverDomain;

    /*连接超时时间 秒*/
    private int connectionTime;

    /*读取超时时间 秒*/
    private int readTime;

    /*写入超时时间 秒*/
    private int writeTime;

    /*请求头*/
    private Map<String,String> headers;

    private OkHttpConfig(Builder builder){
        this.retryTimes = builder.retryTimes;
        this.cache = builder.cache;
        this.switchServerDomains = builder.switchServerDomains;
        this.serverDomain = builder.serverDomain;
        this.connectionTime = builder.connectionTime;
        this.readTime = builder.readTime;
        this.writeTime = builder.writeTime;
        this.headers = builder.headers;
    }

    public Map<String, String> getHeaders() {
        return headers;
    }

    public void setHeaders(Map<String, String> headers) {
        this.headers = headers;
    }

    public int getRetryTimes() {
        return retryTimes;
    }

    public void setRetryTimes(int retryTimes) {
        this.retryTimes = retryTimes;
    }

    public boolean isCache() {
        return cache;
    }

    public void setCache(boolean cache) {
        this.cache = cache;
    }

    public int getConnectionTime() {
        return connectionTime;
    }

    public void setConnectionTime(int connectionTime) {
        this.connectionTime = connectionTime;
    }

    public int getReadTime() {
        return readTime;
    }

    public void setReadTime(int readTime) {
        this.readTime = readTime;
    }

    public int getWriteTime() {
        return writeTime;
    }

    public void setWriteTime(int writeTime) {
        this.writeTime = writeTime;
    }

    public List<String> getSwitchServerDomains() {
        return switchServerDomains;
    }

    public void setSwitchServerDomains(List<String> switchServerDomains) {
        this.switchServerDomains = switchServerDomains;
    }

    public String getServerDomain() {
        return serverDomain;
    }

    public void setServerDomain(String serverDomain) {
        this.serverDomain = serverDomain;
    }

    @Override
    public boolean equals(Object obj) {
        if(obj == null){
            return false;
        }

        if(this.getClass() != obj.getClass()){
            return false;
        }

        OkHttpConfig config = (OkHttpConfig) obj;

        if(this.getRetryTimes() != config.getRetryTimes()){
            return false;
        }

        if(this.isCache() != config.isCache()){
            return false;
        }

        if(StringUtils.checkStringEquals(this.getServerDomain(),config.getServerDomain())){
            return false;
        }

        if(this.getConnectionTime() != config.getConnectionTime()){
            return false;
        }

        if(this.getReadTime() != config.getReadTime()){
            return false;
        }

        if(this.getWriteTime() != config.getWriteTime()){
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(retryTimes);
        sb.append(cache);
        sb.append(serverDomain);
        sb.append(connectionTime);
        sb.append(readTime);
        sb.append(writeTime);
        return sb.toString();
    }

    public static class Builder{
        /*重连次数*/
        private int retryTimes;

        /*是否需要缓存处理*/
        private boolean cache = false;

        /*备用服务器url*/
        private List<String> switchServerDomains;

        /*基础服务器url*/
        private String serverDomain;

        /*超时时间-默认6秒*/
        private int connectionTime = 10;

        private int readTime = 10;

        private int writeTime = 10;

        /*请求头*/
        private Map<String,String> headers;

        public Builder cache(boolean cache) {
            this.cache = cache;
            return this;
        }

        public Builder serverDomain(String serverDomain) {
            this.serverDomain = serverDomain;
            return this;
        }

        public Builder switchServerDomains(List<String> switchServerDomains) {
            this.switchServerDomains = switchServerDomains;
            return this;
        }

        public Builder retryTimes(int retryTimes) {
            this.retryTimes = retryTimes;
            return this;
        }

        public Builder connectionTime(int connectionTime) {
            this.connectionTime = connectionTime;
            return this;
        }

        public Builder readTime(int readTime) {
            this.readTime = readTime;
            return this;
        }

        public Builder writeTime(int writeTime) {
            this.writeTime = writeTime;
            return this;
        }

        public Builder headers(Map<String,String> headers) {
            this.headers = headers;
            return this;
        }

        public Builder addHeader(String name,String value) {
            if(null == this.headers){
                this.headers = new HashMap<String,String>();
            }
            this.headers.put(name,value);
            return this;
        }

        public OkHttpConfig build(){
            return new OkHttpConfig(this);
        }
    }
}
