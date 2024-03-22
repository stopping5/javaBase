package com.company.sort;

/**
 * @author stopping
 * @date 2024/3/21 2:02 PM
 */
public class QuickSortMySelf {

    private Integer count = 1;

    public static void main(String[] args) {
        QuickSortMySelf quickSortMySelf = new QuickSortMySelf();
        int num [] = {5,3,2,7,8,4,1,6};
        quickSortMySelf.sort(num,0,num.length-1);

        System.out.printf("result:");

        for (int n : num){
            System.out.print(n + " ");
        }
    }

    public void sort(int [] num,int low,int high){
        if (low < high){
            int current = partition(num,low,high);
            sort(num,low,current-1);
            sort(num,current + 1,high);
        }
    }

    private Integer partition(int[] num, int low, int high){
        int k = num[low];

        while (low < high){

            while (low < high && k <= num[high]){
                high --;
            }
            //num[high] > k 交互元素
            if (k > num[high]){
                num[low] = num[high];
                low++;
            }

            while (low < high && k > num[low]){
                low ++;
            }

            if (k < num[low]){
                num[high] = num[low];
                high --;
            }
        }
        num[low] = k;
        System.out.printf("当前对比元素 = " + k + ",第" + count++ + "轮排序结果:");
        for (int n : num){
            System.out.print(n + " ");
        }
        System.out.println(" ");
        return low;
    }

    private void swap(int [] num,int l,int h){
        int temp = num[l];
        num[l] = num[h];
        num[h] = temp;
    }
}
