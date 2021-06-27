package com.company.leecode;

/**
 * @Description AddSolution
 * 给你两个 非空 的链表，表示两个非负的整数。它们每位数字都是按照 逆序 的方式存储的，并且每个节点只能存储 一位 数字。
 * 请你将两个数相加，并以相同形式返回一个表示和的链表。
 * 你可以假设除了数字 0 之外，这两个数都不会以 0 开头。
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/add-two-numbers
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * @Author stopping
 * @date: 2021/5/13 11:51
 */
class ListNode<T> {
    T val;
    ListNode next;
    ListNode() {}
    ListNode(T val) { this.val = val; }
    ListNode(T val, ListNode next) { this.val = val; this.next = next; }

    public void add(T val){
        if (null == this.val){
            this.val = val;
            return;
        }
        if (null ==this.next){
            this.next = new ListNode(val);
            return;
        }
        ListNode listNode = finalListNode(this.next);
        listNode.add(val);
    }

    public T get(int index){
        ListNode temp = this;
        for (int i = 0; i < index; i++) {
            temp = next(temp);
        }
        if (temp == null){
            return null;
        }
        return (T) temp.val;
    }

    public ListNode next(ListNode node){
        return node.next;
    }
    public ListNode finalListNode(ListNode node){
        //最后一个节点
        if (node.next == null){
            return node;
        }
        finalListNode(node.next);
        return null;
    }

    public T getVal() {
        return val;
    }

    public void setVal(T val) {
        this.val = val;
    }

    public ListNode getNext() {
        return next;
    }

    public void setNext(ListNode next) {
        this.next = next;
    }
}
public class AddSolution {

    public static void main(String[] args) {
        ListNode l1 = new ListNode(2);
        l1.add(4);
        l1.add(3);
        ListNode l2 = new ListNode(5);
        l2.add(6);
        l2.add(4);
        ListNode<Integer> result = addTwoNumbers(l1,l2);
        System.out.println("result:"+result);
    }

    static ListNode addTwoNumbers(ListNode<Integer> l1, ListNode<Integer> l2) {
        ListNode listNode = new ListNode();
        int carry = 0;
        int i = 0;
        for (;;){
            if (l1.get(i) == null){
                break;
            }
            int sun = l1.get(i)+l2.get(i)+carry;
            carry = sun / 10;
            listNode.add(sun % 10);
            i++;
        }
        return listNode;
    }
}
