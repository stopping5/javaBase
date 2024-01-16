package com.company.struct;

/**
 * @Description: 数组结构测试
 * @Author stopping
 * @Date 2023/9/18 10:20 AM
 * @PackageName:com.company.struct
 * @ClassName: SequenceListTest
 */
public class SequenceListTest {

    public static void main(String[] args) {
        SequenceList<String> sequence = new SequenceList<>();
        sequence.add("hello");
        sequence.add("world");
        sequence.add("!");
        System.out.printf("sequence" + sequence);
    }
}
