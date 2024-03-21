package com.company.sort.lur;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;

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

    public DoubleList() {
        this.head = new Node(0,0);
        this.tail = new Node(0,0);
        head.setNext(tail);
        tail.setPre(head);
        this.size = 0;
    }


    public void add(Node x){
        Node pre = tail.getPre();
        pre.setNext(x);
        tail.setPre(x);
        x.setNext(tail);
        x.setPre(pre);
        size++;
    }

    public void remove(Node x){
        Node pre = x.getPre();
        Node next = x.getNext();
        pre.setNext(next);
        next.setPre(pre);
        size--;
    }

    public Node removeFirst(){
        if (head.getNext() == tail){
            return null;
        }
        Node remove = head.getNext();
        remove(remove);
        return remove;
    }
}
