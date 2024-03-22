package com.company.sort.lur;

import lombok.Data;

/**
 * 双向链表节点
 */
public class Node {

    public Integer key,val;

    public Node pre;

    public Node next;

    public Node(Integer key, Integer val) {
        this.key = key;
        this.val = val;
    }
}
