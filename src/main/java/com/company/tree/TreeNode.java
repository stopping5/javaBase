package com.company.tree;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class TreeNode {

    /**
     * 数据
     */
    private Integer data;

    /**
     * 右子数
     */
    private TreeNode left;

    /**
     * 左子数
     */
    private TreeNode right;

    public TreeNode(Integer data) {
        this.data = data;
    }
}
