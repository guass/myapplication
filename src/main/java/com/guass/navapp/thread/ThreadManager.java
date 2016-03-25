package com.guass.navapp.thread;

import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * Created by guass on 2016/3/19.
 */
public class ThreadManager {
    private ThreadManager()
    {

    }

    private static ThreadManager instance = new ThreadManager();
    private ThreadPoolProx longPool;
    private ThreadPoolProx shortPool;

    public static ThreadManager getInstance()
    {
        return  instance;
    }

    // 联网比较耗时
    // cpu的核数*2+1
    public synchronized ThreadPoolProx creatLongPool()
    {
        if(longPool == null)
        {
            longPool = new ThreadPoolProx(5,5,5000L);
        }
        return longPool;
    }

    public synchronized ThreadPoolProx creatShortPool()
    {
        if(shortPool == null)
        {
            shortPool = new ThreadPoolProx(3,3,3000L);
        }
        return shortPool;
    }

    public class ThreadPoolProx{
        private ThreadPoolExecutor pool;
        private int corePoolSize;
        private int maximumPoolSize;
        private long time;



        public ThreadPoolProx(int corePoolSize, int maximumPoolSize, long time)
        {
            this.corePoolSize = corePoolSize;
            this.maximumPoolSize = maximumPoolSize;
            this.time = time;
        }

        public void execute(Runnable runnable)
        {
            if(pool == null)
            {
                //创建线程池
                // 创建线程池
				/*
				 * 1. 线程池里面管理多少个线程2. 如果排队满了, 额外的开的线程数3. 如果线程池没有要执行的任务 存活多久4.
				 * 时间的单位 5 如果 线程池里管理的线程都已经用了,剩下的任务 临时存到LinkedBlockingQueue对象中 排队
				 */
                pool = new ThreadPoolExecutor(corePoolSize, maximumPoolSize,time, TimeUnit.MILLISECONDS, new LinkedBlockingDeque<Runnable>());
            }

            pool.execute(runnable);

        }

        public void cancel(Runnable runnable)
        {
            if(pool != null && !pool.isShutdown()  && pool.isTerminated())
            {
                    pool.remove(runnable);
            }
        }
    }
}
