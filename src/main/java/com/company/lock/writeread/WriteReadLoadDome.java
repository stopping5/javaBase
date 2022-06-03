package com.company.lock.writeread;

import java.sql.Time;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * @Description WriteReadLoadDome 读写锁案例实现
 * 读写锁特征
 * - 写写 互斥
 * - 读写 互斥
 * - 读读 共享
 *  编写一个读写缓存，对写同步线程，对读线程共享.验证读写共享。
 * @Author stopping
 * @date: 2021/4/18 21:03
 */
class MyCache{
    //volatile特性
    private volatile Map<String,Object> cache = new HashMap<>();

    ReentrantReadWriteLock rwLock = new ReentrantReadWriteLock();

    public Object get(String key)  {
        Object o = null;
        //rwLock.readLock().lock();
        try{
            System.out.println("正在读数据，"+key);
            TimeUnit.MILLISECONDS.sleep(300);
            o = cache.get(key);
            System.out.println("读取数据完毕...");
        }catch (Exception e){
            System.out.println("读取数据失败");
        }finally {
            //rwLock.readLock().unlock();
        }
        return o;
    }

    public void set(String key,Object val)  {
        //rwLock.writeLock().lock();
        try {
            System.out.println("正在写入数据，"+key);
            TimeUnit.MILLISECONDS.sleep(300);
            cache.put(key,val);
            System.out.println("写入数据完毕...");
        }catch (Exception e){
            System.out.println("写入数据异常");
        }finally {
            //rwLock.writeLock().unlock();
        }
    }
}

public class WriteReadLoadDome {

    public static void main(String[] args) throws Exception {
        MyCache cache = new MyCache();
        for (int i = 1 ; i <= 5 ; i ++){
            final int temp = i;
            new Thread(()->{
                cache.set(temp+"","no."+temp);
            },String.valueOf(temp)).start();
        }
        for (int i = 1 ; i <= 5 ; i ++){
            final int temp = i;
            new Thread(()->{
                cache.get(temp+"");
            },String.valueOf(temp)).start();
        }
    }
}
