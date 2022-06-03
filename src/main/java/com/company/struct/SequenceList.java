package com.company.struct;

/**
 * @Description SequenceList
 * @Author stopping
 * @date: 2021/6/20 21:26
 */

public class SequenceList<T> {
    /**
     * 最大长度
     * */
    private static final int MAX_SIZE = 100;

    /**
     * 列表长度
     * */
    public int size;

    /**
     * 存储数组对象
     * */
    private T[] listArray;

    /**
     * 构造函数
     * */
    public SequenceList() {
        size = 0;
        listArray = (T[]) new Object[MAX_SIZE];
    }

    /**
     * 添加元素
     * */
    public int add(T t){
        int index = size;
        listArray[size++] = t;
        return index;
    }

    /**
     * 获取集合
     * */
    public T get(int i){
        sure(i);
        return listArray[i];
    }
    /**
     * 删除元素
     * */
    public boolean remove(int i){
        /**
         * 1 2 3 4 5
         *       ^
         * remove index -> 3
         * 1 2 3 5
         * */
        sure(i);
        for (int j = i ; j < size ; j++){
            listArray[j] = listArray[j+1];
        }
        subSize();
        return true;
    }

    /**
     * 检查下标是否异常
     * */
    private boolean sure(int i){
        if (i > MAX_SIZE){
            throw new RuntimeException("最长长度为"+MAX_SIZE);
        }
        if (i > size){
            throw new RuntimeException("下标越界异常");
        }
        return true;
    }

    /**
     *  长度下标减少
     * */
    private void subSize(){
        size --;
    }

    /**
     *  长度下标增加
     * */
    private void incSize(){
        size ++;
    }
}
