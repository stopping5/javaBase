package com.company.jvm;

/**
 * 垃圾回收机制测试
 * @author stopping
 * @date 2024/2/27 11:17 AM
 */
public class GCTest {
    public static void main(String[] args) {
        byte[] allocation1, allocation2;
        allocation1 = new byte[80900 * 1024];

        allocation2 = new byte[900*1024];

    }
}
