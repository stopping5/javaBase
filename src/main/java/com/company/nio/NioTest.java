package com.company.nio;


import java.nio.Buffer;
import java.nio.ByteBuffer;

/**
 * NIO API 测试
 * @Classname: NioTest
 * @Date: 2022/10/27 5:25 下午
 * @author: stopping
 */
public class NioTest {
    public static void main(String[] args) {
        String hello = "hello";
        ByteBuffer byteBuffer = ByteBuffer.allocate(1024);
        show(byteBuffer,"capacity");

        byteBuffer.put(hello.getBytes());
        show(byteBuffer,"put");

        //切换到读取数据模式 potion = 0 limit = position
        byteBuffer.flip();
        show(byteBuffer,"flip");

        //读取数据
        byte[] b  = new byte[byteBuffer.limit()];
        byteBuffer.get(b);
        System.out.println("读取数据:"+new String(b));
        show(byteBuffer,"get");

        //重置读取起点
        byteBuffer.rewind();
        show(byteBuffer,"rewind");

        //清空缓存区
        byteBuffer.clear();
        show(byteBuffer,"clear");
    }

    private static void show(ByteBuffer byteBuffer,String title) {
        System.out.println("--------------"+title+"------------");
        System.out.println("capacity："+byteBuffer.capacity());
        System.out.println("limit："+byteBuffer.limit());
        System.out.println("position："+byteBuffer.position());
    }
}
