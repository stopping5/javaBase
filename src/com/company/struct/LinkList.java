package com.company.struct;

/**
 * @Description LinkList 链表
 * @Author stopping
 * @date: 2021/6/23 23:17
 */

public class LinkList<T> {
    /**
     * 数据域
     * */
    private T data;
    /**
     * 指针域
     * */
    private LinkList next;

    /**
     * 增加数据元素
     * */
    public void add(T t){
        this.next = new LinkList<>(t,next);
    }

    /**
     * 获取数据元素
     * */
    public T get(int i){
        int y = 0;
        //下一个指针域
        LinkList list = next;
        //数据域不为空且下标在范围内
        while (list.data != null && y<i){
            list = list.next;
            y++;
        }
        if (list.data == null || y>i){
            throw new RuntimeException("下标越界");
        }
        return (T) list.data;
    }
    public LinkList() {
    }

    public LinkList(T data, LinkList next) {
        this.data = data;
        this.next = next;
    }

    public LinkList(T data) {
        this.data = data;
    }
}
