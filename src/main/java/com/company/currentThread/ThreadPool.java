package com.company.currentThread;

import java.util.concurrent.*;

/**
 * 线程池
 */
public class ThreadPool {

    public static ExecutorService WORK_EXECUTOR = new ThreadPoolExecutor(
            2,
            5,
            1L,
            TimeUnit.SECONDS,
            new LinkedBlockingQueue<>(10),
            Executors.defaultThreadFactory(),
            new ThreadPoolExecutor.CallerRunsPolicy());
}
