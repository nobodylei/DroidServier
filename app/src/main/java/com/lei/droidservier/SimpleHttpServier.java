package com.lei.droidservier;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by yanle on 2018/3/7.
 */

public class SimpleHttpServier {
    private final WebConfiguration webConfiguration;
    private final ExecutorService threadPool;//线程池
    private boolean isEnable;
    private ServerSocket socket;

    public SimpleHttpServier(WebConfiguration webConfiguration) {
        this.webConfiguration = webConfiguration;
        threadPool = Executors.newCachedThreadPool();
    }
    /**
     * 启动server（异步）
     */
    public void startAsync() {
        isEnable = true;//标识服务允许执行
        new Thread(new Runnable() {
            @Override
            public void run() {
                doProcSync();
            }
        }).start();
    }

    /**
     * 停止server（异步）
     */
    public void stopAsync() {
        if(!isEnable) {
            return;
        }
        isEnable = false;
        try {
            socket.close();
            socket = null;
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void doProcSync() {
        try {
            InetSocketAddress socketAddress = new InetSocketAddress(webConfiguration.getPost());
            socket = new ServerSocket();
            socket.bind(socketAddress);
            while(isEnable) {
                //当远端有程序连接的时候就会返回一个socket
                final Socket remotePeer = socket.accept();
                threadPool.submit(new Runnable() {
                    @Override
                    public void run() {
                        onAcceptRemotePeer(remotePeer);
                    }
                });
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 处理远端连接
     * @param remotePeer
     */
    private void onAcceptRemotePeer(Socket remotePeer) {
        try {
            remotePeer.getOutputStream().write("hello".getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
