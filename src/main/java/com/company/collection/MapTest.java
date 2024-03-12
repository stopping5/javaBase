package com.company.collection;

import java.util.HashMap;
import java.util.Map;

/**
 * hashmap测试
 *
 * @author stopping
 * @date 2024/3/7 10:59 AM
 */
public class MapTest {

    public static void main(String[] args) {
        Map<Integer,String> hm = new HashMap<>();
        hm.put(1,"a");
        hm.put(2,"b");
        hm.put(3,"c");
        hm.put(4,"d");
        hm.put(5,"f");
        hm.put(6,"e");
        hm.put(7,"r");
        hm.put(8,"y");
        hm.put(9,"i");
        hm.put(10,"p");
        hm.put(11,"b");
        hm.put(12,"g");
        hm.put(13,"e");
        hm.put(14,"q");
        hm.put(65,"z");
        hm.put(33,"j");
        hm.put(32,"k");

        String s = hm.get(33);
        System.out.println(s);
    }
}
