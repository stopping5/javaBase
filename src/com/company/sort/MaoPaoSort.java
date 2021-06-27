package com.company.sort;

/**
 * @Description MaoPaoSort 冒泡算法排序
 * @Author stopping
 * @date: 2021/5/10 23:32
 */

public class MaoPaoSort {
    /**
     * 升序
     * */
    public static void main(String[] args) {
        int [] num = {2,5,1,6,3,4,9,8,7};
        System.out.println("数组长度："+num.length);
        for (int i = 0;i < num.length-1 ; i++){
                for (int j = 0; j < num.length-i-1; j++) {
                    if (num[j]>num[j+1]){
                        //前面大于后面 swag
                        int temp = num[j];
                        num[j] = num[j+1];
                        num[j+1] = temp;
                    }
                }
                System.out.println("排序第"+i+"次数");
                for (int a : num) {
                    System.out.print(a+",");
                }
                System.out.println("");
        }
    }
}
