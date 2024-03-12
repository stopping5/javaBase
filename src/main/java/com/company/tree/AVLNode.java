package com.company.tree;

import lombok.Data;

/**
 * 平衡二叉树节点
 *
 * @author stopping
 * @date 2024/3/12 2:39 PM
 */
@Data
public class AVLNode {
    /**
     * key
     * */
    private Integer key;
    /**
     * 值
     * */
    private Integer val;
    /**
     * 树高度
     * */
    private Integer high;
    /**
     * 左子树
     * */
    private AVLNode left;
    /**
     * 右子树
     * */
    private AVLNode right;

    public AVLNode(Integer key, Integer val) {
        this.key = key;
        this.val = val;
        this.high = 1;
    }
}
