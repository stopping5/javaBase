# 一、为什么要使用Future
1. Thread 构造只支持runnable作为参数
2. 但是runnable不支持返回值故选择callable
3. FutureTask继承Future、Runnable,并且构造函数支持callable
# 二、Future的优缺点
1. 优点
2. 缺点
    - get() 阻塞 
      -- 若其他线程需要获取上一个线程的结果则需等待get()返回结果则阻塞线程  
      -- get(time,unit) 过时函数 
    - isDone() 获取FutureTask状态，true则完成线程，可以通过get()获取结果