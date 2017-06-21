package com.atguigu.p2p0224.common;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by Administrator on 2017/6/21.
 */

/*
*
* 线程池
*
* */
public class ThreadManager {

    private ThreadManager(){}

    private static ThreadManager manager = new ThreadManager();

    public static ThreadManager getInstance(){
        return manager;
    }

    /*
    *
    * wait 和 sleep的区别？
    *
    * (1) newCachedThreadPool
创建一个可缓存线程池，如果线程池长度超过处理需要，可灵活回收空闲线程，若无可回收，则新建线程

(2) newFixedThreadPool
创建一个定长线程池，可控制线程最大并发数，超出的线程会在队列中等待

(3)  newScheduledThreadPool
创建一个定时线程池，支持定时及周期性任务执行

4) newSingleThreadExecutor
创建一个单线程化的线程池，它只会用唯一的工作线程来执行任务，保证所有任务按照指定顺序执行
    *
    * */
    private ExecutorService service = Executors.newCachedThreadPool();

    public ExecutorService getThread(){
        return service;
    }
}
