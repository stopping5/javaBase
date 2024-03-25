package com.company.sort;

/**
 * 归并排序实现
 * @author stopping
 * @date 2024/3/22 4:33 PM
 */
public class MergeSortSelf {

    public static int [] temp;

    public static void main(String[] args) {
        MergeSortSelf sortSelf = new MergeSortSelf();
        int [] num = {3,4,1,2,5,6};
        temp = new int[num.length];
        sortSelf.sort(num,0, num.length-1);
        System.out.println("执行完毕");
        for (int i : num) {
            System.out.print(i+",");
        }
    }

    /**
     * 排序函数，将数组根据中点切隔两个分组，每个分组保持组内有序，进行合并。直到整合数组合并完成则完成排序
     * 类似于二叉树的后序遍历
     * @param nun
     * @param left
     * @param right
     */
    public void sort(int [] nun,int left,int right){
        if (left == right) {
            // 单个元素不用排序
            return;
        }
        int mid = left + (right - left) / 2;
        sort(nun,left,mid);
        sort(nun,mid+1,right);
        merge(nun,left,right,mid);
    }

    /**
     * 合并函数
     * @param num
     * @param left
     * @param right
     * @param mid
     */
    private void merge(int[] num, int left, int right,int mid) {
        //备份数据到temp数组
        for (int i = left; i <= right; i++) {
            temp[i] = num[i];
        }
        //合并操作 两个子分组从各自起点遍历判断是否符合
        int s1 = left;
        int s2 = mid + 1;

        for (int i = left; i <= right; i++) {
            if ( s1 == mid + 1 ){
                num[i] = temp[s2++];
            } else if (s2 == right + 1) {
                num[i] = temp[s1++];
            } else if (temp[s1] > temp[s2]) {
                num[i] = temp[s2++];
            }else {
                num[i] = temp[s1++];
            }
        }
    }

}
