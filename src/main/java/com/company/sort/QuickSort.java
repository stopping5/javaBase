package com.company.sort;

/**
 * @Description QuickSort 快排算法
 * @Author stopping
 * @date: 2021/5/11 0:11
 */

public class QuickSort {
    public static void main(String[] args) {
        int [] num = {20,5,1,6,3,4,9,8,7,4,3,22,33,55,3,1,23,1233,2};
        quickSort(num,0,num.length-1);
        System.out.println("执行完毕");
        for (int i : num) {
            System.out.print(i+",");
        }
    }
    public static void quickSort(int[] num,int low,int high){
       if(low < high){
           int partition = partition(num, low, high);
           quickSort(num,low,partition-1);
           quickSort(num,partition+1,high);
       }
    }
    /**
     * 核心思想：low、high与关键字比较k
     * low < k -> low++ (符合排序)
     * low > k -> 不符合排序，low与high交换，交由high侧处理
     * high>= k -> 符合排序 high++
     * high< k -> 不符合排序,low和high交换
     *
     * low == high -> 获取中间值 k的下标
     * */
    public static int partition(int[] num, int low, int high){
      int n = num[low];
        //前后双指针法，控制low、high指针达成目标
        while (low < high){
            while (low < high && n <= num[high]){
                high--;
            }
            if (n > num[high]){
                num[low] = num[high];
                low++;
            }

            while (low < high && n >= num[low]){
                low++;
            }

            if (n < num[low]){
                num[high] = num[low];
                high--;
            }
        }
        num[low] = n;
        return low;
    }
}
