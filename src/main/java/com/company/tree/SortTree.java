package com.company.tree;

/**
 * 二叉排序树实现
 */
public class SortTree {

    /**
     * 添加节点
     * @param root 根节点
     * @param data 节点数据
     * @return 是否添加
     */
    public static boolean addNode(TreeNode root,Integer data){
        TreeNode treeNode = new TreeNode(data);
        //根节点为空则当前数据为根节点
        if (root == null){
            root = treeNode;
            return true;
        }

        //根节点大于数据，数据插入左数
        if (root.getData() > data){
            if (root.getLeft() == null){
                root.setLeft(treeNode);
                return true;
            }else{
                return addNode(root.getLeft(),data);
            }
        }
        //根节点小于数据，插入右数
        else if (root.getData() < data){
           if (root.getRight() == null){
               root.setRight(treeNode);
               return true;
           }else{
               return addNode(root.getRight(),data);
           }
        }
        //等于根节点停止插入
        else{
            return false;
        }
    }

    /**
     * 中序遍历
     * @param root
     */
    public static void midSort(TreeNode root){
        if (root == null){
            return;
        }
        midSort(root.getLeft());
        System.out.print(root.getData() + " ");
        midSort(root.getRight());
    }

    /**
     * 二叉排序树检索
     * @param root
     * @param targetNum
     * @return
     */
    public static boolean searchData(TreeNode root,Integer targetNum){
        if (root == null){
            return false;
        }
        if (root.getData().equals(targetNum)){
            return true;
        }else if (root.getData() > targetNum){
            return searchData(root.getLeft(),targetNum);
        }else if (root.getData() < targetNum){
            return searchData(root.getRight(),targetNum);
        }else {
            return false;
        }
    }

    public static void main(String[] args) {
        Integer [] data = {24,5,34,67,88,93,21};
        TreeNode root = new TreeNode(20);
        for (Integer d : data) {
            System.out.print(d+" ");
            addNode(root,d);
        }
        System.out.println("");
        System.out.println("mid sort result：");
        midSort(root);
        addNode(root,2);
        System.out.println("add 2 ,mid sort");
        midSort(root);
        Integer target = 11;
        //二叉排序树查找
        System.out.println("exist target num:"+target+","+searchData(root,target));
    }
}
