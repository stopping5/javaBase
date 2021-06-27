package com.company.struct;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @Description ListDemo
 * @Author stopping
 * @date: 2021/6/20 21:39
 */

public class ListDemo {
    public static void main(String[] args) {
        /*SequenceList<Integer> data = new SequenceList<>();
        System.out.println("in->"+data.add(1));
        System.out.println("in->"+data.add(2));
        System.out.println("in->"+data.add(3));
        data.add(4);
        data.add(5);

        data.remove(2);

        for (int i = 0; i < data.size; i++) {
            System.out.println(data.get(i));
        }*/

        LinkList list = new LinkList();
        list.add(1);
        list.add(2);
        list.add(3);
        list.add(4);
        System.out.println(list.get(1));
    }
}
