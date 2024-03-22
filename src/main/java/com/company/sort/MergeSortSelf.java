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

    public void sort(int [] nun,int left,int right){
        if (left == right){
            return;
        }
        int mid = left + (right - left ) / 2;
        sort(nun, left, mid);
        sort(nun,mid+1,right);
        merge(nun,left,right,mid);
    }

    private void merge(int[] num, int left, int right,int mid) {
        for (int i = left; i <= right ; i++) {
            temp[i] = num[i];
        }
        int i = left,j = mid+1;
        for (int p = left; p <= right; p++) {
            //归并排序由mid切分的数组，左右两端保持有序，i越界则导致读取右侧数组到num中导致数据重复
            //i到左侧尽头
            if (i == mid + 1){
                num[p] = temp[j++];
            }
            //j到右侧尽头
            else if (j == right + 1) {
                num[p] = temp[i++];
            } else if (temp[i] > temp[j]){
                num[p] = temp[j++];
            } else{
                num[p] = temp[i++];
            }
        }
    }

}
