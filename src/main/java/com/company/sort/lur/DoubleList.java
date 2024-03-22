package com.company.sort.lur;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;

/**
 * 双向链表
 */
@Data
public class DoubleList {
    /**
     * 链表头、尾节点
     */
    private Node head,tail;
    /**
     * 链表数量
     */
    private Integer size;

    /**
     * 构造函数
     * 初始化头、尾节点以及前后关系
     */
    public DoubleList() {
        this.head = new Node(0,0);
        this.tail = new Node(0,0);
        head.next = tail;
        tail.pre = head;
        this.size = 0;
    }

    /**
     * 添加节点
     * @param x 节点
     */
    public void add(Node x){
        Node pre = tail.pre;
        pre.next = x;
        tail.pre = x;
        x.next = tail;
        x.pre = pre;
        size++;
    }

    /**
     * 删除节点
     * @param x
     */
    public void remove(Node x){
        Node pre = x.pre;
        Node next = x.next;
        pre.next = next;
        next.pre = pre;
        size--;
    }

    /**
     * 删除第一个节点，头指针指向的节点
     * @return 删除的节点
     */
    public Node removeFirst(){
        if (head.next == tail){
            return null;
        }
        Node remove = head.next;
        remove(remove);
        return remove;
    }
}
