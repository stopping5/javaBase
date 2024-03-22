package com.company.sort;

import java.util.Arrays;

/**
 * @author stopping
 * @date 2024/3/21 2:02 PM
 */
public class QuickSortMySelf {

    public static void main(String[] args) {
        QuickSortMySelf   quickSortMySelf = new QuickSortMySelf();
        int [] num = {20,5,1,6,3,4,9,8,7,4,3,22,33,55,3,1,23,1233,2};
        quickSortMySelf.sort(num,0,num.length-1);
        System.out.println("执行完毕");
        for (int i : num) {
            System.out.print(i+",");
        }
    }


    public void sort(int [] num,int left,int right){
        //遍历整个二叉树
        if (left < right){
            //遍历子树逻辑
            int partition = partition(num, left, right);
            sort(num,left,partition-1);
            sort(num,partition+1,right);
        }
    }

    private Integer partition(int[] num, int left, int right) {
        int p = num[left];
        while (left < right){
            while (left < right && num[right] >= p){
                right--;
            }
            //p > num[right] 交换位置
            if (p > num[right]){
                num[left] = num[right];
                left++;
            }
            while (left < right && num[left] < p){
                left ++;
            }
            if (p < num[left]){
                num[right] = num[left];
                right--;
            }
        }
        //重置P位置，最终确认P的位置
        num[left] = p;
        return left;
    }
}
