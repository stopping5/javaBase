package com.company.sort.lur;

import java.util.LinkedHashMap;

/**
 * 使用LinkHashMap实现LRU
 *
 * @author stopping
 * @date 2024/3/22 9:50 AM
 */
public class LinkHashMapCache {

    private LinkedHashMap<Integer,Integer> cache =  new LinkedHashMap<>();

    private Integer cap;

    /**
     * 将key置为最近使用
     * @param key key
     */
    private void makeRecently(int key){
        Integer val = cache.get(key);
        cache.remove(key);
        cache.put(key,val);
    }

    public Integer get(Integer key){
        if (!cache.containsKey(key)){
            return -1;
        }
        makeRecently(key);
        return cache.get(key);
    }

    public void put(Integer ket,Integer val){
        if (cache.containsKey(ket)){
            cache.put(ket,val);
            makeRecently(ket);
            return;
        }

        if (cap == cache.size()){
            //删除最近最少使用
            Integer next = cache.keySet().iterator().next();
            cache.remove(next);
        }

        cache.put(ket,val);
    }

}
