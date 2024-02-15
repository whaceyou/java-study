package com.ace.algorithm.binarytree;


/**
 * 给定一个二叉树中,2个节点  node1 和 node2,找到他们的最低公共祖先节点
 * <a href="https://leetcode.cn/problems/lowest-common-ancestor-of-a-binary-tree/description/">二叉树的最近公共祖先</a>
 */
public class LowestCommonAncestor {


    public static void main(String[] args) {

    }


    /**
     * @param head head
     * @param n1   in head
     * @param n2   in head
     * @return Lowest Common Ancestor
     */
    public static BinaryNode lcaUserRecursion(BinaryNode head, BinaryNode n1, BinaryNode n2) {
        // base case
        if (head == null || head == n1 || head == n2) {
            return head;
        }

        BinaryNode l = lcaUserRecursion(head.left, n1, n2);
        BinaryNode r = lcaUserRecursion(head.right, n1, n2);

        if (l != null && r != null) {
            return head;
        }

        return l != null ? l : r;

    }

//    /**
//     *
//     * @param head head
//     * @param n1 in head
//     * @param n2 in head
//     * @return Lowest Common Ancestor
//     */
//    public static BinaryNode lca(BinaryNode head,BinaryNode n1,BinaryNode n2) {
//        Map<BinaryNode, BinaryNode> fatherMap = new HashMap<>();
//        fatherMap.put(head,head);
//        fillFatherMap(head,fatherMap);
//
//
//    }
//
//    private static void fillFatherMap(BinaryNode head, Map<BinaryNode, BinaryNode> fatherMap) {}
}
