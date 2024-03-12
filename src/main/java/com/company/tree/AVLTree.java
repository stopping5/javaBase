package com.company.tree;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

/**
 * 平衡二叉树
 *
 * @author stopping
 * @date 2024/3/12 2:38 PM
 */
public class AVLTree {
    private AVLNode root;

    private final StringBuilder visualizeBuilder;

    public AVLTree() {
        visualizeBuilder = new StringBuilder();
    }

    /**
     * 获取当前节点平衡因子(负值右侧不平衡，正值左侧不平衡)
     * @param root 节点
     * @return 平衡因子
     */
    public static Integer getAttribute(AVLNode root){
        return (root.getLeft() == null ? 0 : root.getLeft().getHigh())
                - (root.getRight() == null ? 0 : root.getRight().getHigh());
    }


    public static AVLNode balance(AVLNode node){
        //什么情况需要balance 平衡因子 a,｜a｜ > 1
        int attribute = getAttribute(node);
        if (Math.abs(attribute) > 1){
            //场景分发
            // -1 右侧重左旋
            if (attribute < 0){
                //avlNode的右子树的平衡因子只可能为-1|0|1
                //当它等于1时，需要先右旋
                if (getAttribute(node.getRight()) == 1){
                    node.setRight(rightRevolve(node.getRight()));
                }
                return leftRevolve(node);
            }
            // 平衡因子 > 1 左侧重 右旋
            else {
                if (getAttribute(node.getLeft()) == -1){
                    node.setLeft(leftRevolve(node.getLeft()));
                }
                return rightRevolve(node);
            }
        }
        return node;
    }

    /**
     * 左旋
     * @param avlNode
     * @return 左旋结果
     */
    public static AVLNode leftRevolve(AVLNode avlNode){
        AVLNode right = avlNode.getRight();
        //切换根节点右侧节点 -- 右节点左节点
        avlNode.setRight(right.getLeft());
        //根节点作为右节点子节点，右节点为根节点
        right.setLeft(avlNode);

        resetHeight(avlNode);

        resetHeight(right);

        return right;
    }

    /**
     * 右旋
     * @param avlNode
     * @return 右旋结果
     */
    public static AVLNode rightRevolve(AVLNode avlNode){
        AVLNode leftNode = avlNode.getLeft();

        avlNode.setLeft(leftNode.getRight());

        leftNode.setRight(avlNode);

        resetHeight(avlNode);

        resetHeight(leftNode);

        return leftNode;
    }
    /**
     * 计算节点高度（子左右树最高值+1）
     * @param node 节点
     */
    public static void resetHeight(AVLNode node){
        node.setHigh(Math.max(node.getLeft() != null ? node.getLeft().getHigh() : 0,
                node.getRight() != null ? node.getRight().getHigh() : 0) + 1);
    }


    private AVLNode insert(AVLNode newNode, AVLNode root) {
        if (root == null) {
            // 找到了正确的位置，直接返回新结点
            return newNode;
        }
        if (root.getKey() > newNode.getKey()){
            root.setLeft(insert(newNode,root.getLeft()));
        } else if (root.getKey() < newNode.getKey()) {
            root.setRight(insert(newNode,root.getRight()));
        }else{
            root.setVal(newNode.getVal());
            return newNode;
        }
        resetHeight(root);
        return balance(root);
    }

    public void insert(AVLNode node){
        root = insert(node,root);
    }

    public static void main(String[] args) {

        HashSet<Integer> keySet = new HashSet<>();
        for (int i = 0; i < 100; ++i) {
            keySet.add(getRandomKey());
        }
        AVLTree avlTree = new AVLTree(); // add
        Integer[] keyArray = new Integer[keySet.size()];
        keyArray = keySet.toArray(keyArray);
        System.out.println("keyArray总长度：" + keyArray.length);
        System.out.println("~~~~~~~~~~~~~~开始插入节点：");
        for (Integer integer : keyArray) {
            System.out.println("-----------当前节点总数：" + avlTree.countNodes());
            System.out.println("-----------本次将插入节点：" + integer);
            avlTree.insert(new AVLNode(integer, null));
            System.out.println("-----------插入后AVL树字符串描述：");
            //图形化解析网址：http://mshang.ca/syntree
            System.out.println(avlTree.getVisualizeString());
            System.out.println();
        }
    }


    /**
     * 设置用于二叉树图形化解析的builder
     * 图形化解析网址：http://mshang.ca/syntree
     */
    public String getVisualizeString() {
        visualizeBuilder.setLength(0);
        setVisualizeBuilder(root, visualizeBuilder);
        return visualizeBuilder.toString();
    }

    private void setVisualizeBuilder(AVLNode avlNode, StringBuilder builder) {
        if (avlNode == null) {
            builder.append("[null]");
            return;
        }
        builder.append("[").append(avlNode.getKey()).append("-").append(avlNode.getHigh());
        setVisualizeBuilder(avlNode.getLeft(), builder);
        setVisualizeBuilder(avlNode.getRight(), builder);
        builder.append("]");
    }

    public static Integer getRandomKey() {
        return (int) (Math.random() * 100);
    }

    /**
     * 获取二叉树的结点总数
     */
    public int countNodes() {
        return countNodes(root);
    }

    private int countNodes(AVLNode avlNode) {
        if (avlNode == null) {
            return 0;
        }
        return 1 + countNodes(avlNode.getLeft()) + countNodes(avlNode.getRight());
    }
}
