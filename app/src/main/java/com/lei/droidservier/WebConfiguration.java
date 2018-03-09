package com.lei.droidservier;

/**
 * Created by yanle on 2018/3/7.
 */

public class WebConfiguration {
    /**
     * 端口
     */
    private int post;
    /**
     * 最大监听数
     */
    private int maxParallels;

    public int getPost() {
        return post;
    }

    public void setPost(int post) {
        this.post = post;
    }

    public int getMaxParallels() {
        return maxParallels;
    }

    public void setMaxParallels(int maxParallels) {
        this.maxParallels = maxParallels;
    }
}
