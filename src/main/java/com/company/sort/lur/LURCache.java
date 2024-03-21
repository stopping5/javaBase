package com.company.sort.lur;

import java.util.HashMap;
import java.util.Objects;

/**
 * 最近最少使用 https://mp.weixin.qq.com/s/b0YVCccJ8mFP6lI-1NiQOQ
 */
public class LURCache {
    private HashMap<Integer,Node> map;

    private final DoubleList list;

    private Integer cap;

    public LURCache(Integer cap) {
        this.map = new HashMap<>();
        this.list = new DoubleList();
        this.cap = cap;
    }

    /**
     * 将key提升为最近使用
     * @param key
     */
    private void makeRecently(int key){
        Node node = map.get(key);
        list.remove(node);
        list.add(node);
    }

    /* 添加最近使用的元素 */
    private void addRecently(int key, int val){
        Node node = new Node(key,val);
        map.put(key,node);
        list.add(node);
    }
    /* 删除某一个 key */
    private void deleteKey(int key){
        Node node = map.get(key);
        list.remove(node);
        map.remove(key);
    }

    /* 删除最久未使用的元素 */
    private void removeLeastRecently() {
        Node node = list.removeFirst();
        map.remove(node.getKey());
    }

    public int get(int key){
        if (!map.containsKey(key)){
            return -1;
        }
        makeRecently(key);
        Node node = map.get(key);
        return node.getVal();
    }

    public void put(int key,int val){
        if (map.containsKey(key)){
            makeRecently(key);
            return;
        }
        //不存在的情况
        if (Objects.equals(cap, list.getSize())){
            //超出容量，移除最近最少使用
            removeLeastRecently();
            cap--;
        }

        addRecently(key,val);
        cap++;

    }

    public static void main(String[] args) {
        LURCache cache = new LURCache(2);

        cache.put(1,1);
        cache.put(2,2);
        cache.put(3,3);

        int i = cache.get(2);
        System.out.println("获取值 = " + i);
    }

}
