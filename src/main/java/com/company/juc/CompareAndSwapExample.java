package com.company.juc;

import sun.misc.Unsafe;

public class CompareAndSwapExample {
    private static Unsafe unsafe = getUnsafe();
    private long valueOffset;
    private int value;

    public CompareAndSwapExample() {
        // 假设我们有一个int类型的value变量，我们想要通过CAS操作来更新它
        try {
            // 获取value变量在对象内存中的偏移量
            valueOffset = unsafe.objectFieldOffset(getClass().getDeclaredField("value"));
        } catch (NoSuchFieldException e) {
            throw new Error(e);
        }
    }

    public boolean cas(int expectedValue, int newValue) {
        return unsafe.compareAndSwapInt(this, valueOffset, expectedValue, newValue);
    }

    public int getValue() {
        return value;
    }

    public static void main(String[] args) {
        CompareAndSwapExample casExample = new CompareAndSwapExample();
        casExample.value = 10; // 初始值

        // 假设我们期望的值是10，想要更新为20
        int expectedValue = 10;
        int newValue = 20;

        // 使用CAS操作更新value
        boolean swapped = casExample.cas(expectedValue, newValue);

        // 输出结果
        if (swapped) {
            System.out.println("Value swapped successfully to " + casExample.getValue());
        } else {
            System.out.println("Value did not match expected value, swap failed.");
        }
    }

    // 获取Unsafe实例的辅助方法
    private static Unsafe getUnsafe() {
        try {
            return Unsafe.getUnsafe();
        } catch (SecurityException se) {
            throw new AssertionError(se);
        }
    }
}