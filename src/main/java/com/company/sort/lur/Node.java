package com.company.sort.lur;

import lombok.Data;

/**
 * 双向链表节点
 */
@Data
public class Node {

    private Integer key,val;

    private Node pre;

    private Node next;

    public Node(Integer key, Integer val) {
        this.key = key;
        this.val = val;
    }
}
