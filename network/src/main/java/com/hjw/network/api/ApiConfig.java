package com.hjw.network.api;

import java.util.List;

/**
 * Created by hjw on 2017/8/10.17:17
 */

public class ApiConfig {
    /*重连次数*/
    private int retryTimes;

    /*是否需要缓存处理*/
    private boolean cache = false;

    /*基础url*/
    private List<String> baseSwitchUrls;

    /*超时时间-默认6秒*/
    private int connectionTime;

    private int readTime;

    private int writeTime;

    private ApiConfig(Builder builder){
        this.retryTimes = builder.retryTimes;
        this.cache = builder.cache;
        this.baseSwitchUrls = builder.baseSwitchUrls;
        this.connectionTime = builder.connectionTime;
        this.readTime = builder.readTime;
        this.writeTime = builder.writeTime;
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

    public List<String> getBaseSwitchUrls() {
        return baseSwitchUrls;
    }

    public void setBaseSwitchUrls(List<String> baseSwitchUrls) {
        this.baseSwitchUrls = baseSwitchUrls;
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

    public static class Builder{
        /*重连次数*/
        private int retryTimes = 3;

        /*是否需要缓存处理*/
        private boolean cache = false;

        /*基础url*/
        private String baseUrl;

        /*基础url*/
        private List<String> baseSwitchUrls;

        /*超时时间-默认6秒*/
        private int connectionTime = 10;

        private int readTime = 10;

        private int writeTime = 10;

        public Builder cache(boolean cache) {
            this.cache = cache;
            return this;
        }

        public Builder baseUrl(String baseUrl) {
            this.baseUrl = baseUrl;
            return this;
        }

        public Builder retryTimes(int retryTimes) {
            this.retryTimes = retryTimes;
            return this;
        }

        public Builder baseSwitchUrls(List<String> baseSwitchUrls) {
            this.baseSwitchUrls = baseSwitchUrls;
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

        public ApiConfig build(){
            return new ApiConfig(this);
        }
    }
}
