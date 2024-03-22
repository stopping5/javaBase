package com.company.sort.lur;

import lombok.Data;

/**
 * 双向链表节点
 */
public class Node {
    /**
     * 节点key、val
     */
    public Integer key,val;
    /**
     * 前序指针
     * */
    public Node pre;
    /**
     * 后序指针
     * */
    public Node next;

    /**
     * 构造函数
     * @param key
     * @param val
     */
    public Node(Integer key, Integer val) {
        this.key = key;
        this.val = val;
    }
}
