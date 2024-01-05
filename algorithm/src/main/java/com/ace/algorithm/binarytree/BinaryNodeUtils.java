package com.ace.algorithm.binarytree;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;
import java.util.Stack;
import java.util.concurrent.atomic.AtomicReference;

public class BinaryNodeUtils {

    public enum RecursionOrder {
        PreOrder,
        Inorder,
        PostOrder
    }


    public static void travel(BinaryNode node, RecursionOrder recursionOrder) {

        if (node == null) {
            return;
        }
        if (recursionOrder == RecursionOrder.PreOrder) {
            System.out.println(node.val);
        }

        travel(node.left, recursionOrder);
        if (recursionOrder == RecursionOrder.Inorder) {
            System.out.println(node.val);
        }

        travel(node.right, recursionOrder);
        if (recursionOrder == RecursionOrder.PostOrder) {
            System.out.println(node.val);
        }
    }

    /**
     * 是否是搜索二叉树
     *
     * @param head head
     * @return bool
     */
    public boolean isBalancedST(BinaryNode head) {
        return isBST(head, new AtomicReference<>(Integer.MIN_VALUE));
    }

    /**
     * 是否是搜索二叉树
     *
     * @param head head
     * @return bool
     */
    private static boolean isBST(BinaryNode head, AtomicReference<Integer> preVal) {
        if (head == null) {
            return true;
        }

        boolean isLeftBST = isBST(head.left, preVal);
        if (!isLeftBST) {
            return false;
        }

        if (head.val <= preVal.get()) {
            return false;
        } else {
            preVal.set(head.val);
        }

        return isBST(head.right, preVal);
    }

    /**
     * 是否满二叉树 递归套路
     * 1. 左边是
     * 2. 右边也是
     * 3. 左右高度一样
     *
     * @param head head
     */
    public static boolean isFullBTUseRecursion(BinaryNode head) {
        if (head == null) {
            return true;
        }
        ReturnTypeForFull f = isFullBTUseRecursionProcess(head);
        return f.nodesCount == (1 << f.height -1);

    }


    private static ReturnTypeForFull isFullBTUseRecursionProcess(BinaryNode head) {
        if (head == null) {
            return new ReturnTypeForFull(0, 0);
        }

        ReturnTypeForFull l = isFullBTUseRecursionProcess(head.left);
        ReturnTypeForFull r = isFullBTUseRecursionProcess(head.right);

        int height = Math.max(l.height,r.height) + 1;
        int nodesCount = l.nodesCount + r.nodesCount + 1;


        return new ReturnTypeForFull(height,nodesCount);
    }

    private record ReturnTypeForFull(int height, int nodesCount) {
    }

    /**
     * 是否是搜索二叉树 递归套路
     * 对于head节点,满足
     * 1.左树是
     * 2.右树也是
     * 3.左边max < 自己
     * 4.右边mix > 自己
     *
     * @param head head
     * @return bool
     */
    public static boolean isSearchBTUseRecursion(BinaryNode head) {


        return isBSTUseRecursionProcess(head).searched;
    }

    private static ReturnTypeForSearch isBSTUseRecursionProcess(BinaryNode head) {
        if (head == null) {
            return null;
        }

        ReturnTypeForSearch l = isBSTUseRecursionProcess(head.left);
        ReturnTypeForSearch r = isBSTUseRecursionProcess(head.right);

        int minVal = head.val;
        int maxVal = head.val;
        if (l != null) {
            minVal = Math.min(minVal, l.minVal);
            maxVal = Math.max(minVal, l.maxVal);
        }

        if (r != null) {
            minVal = Math.min(minVal, r.minVal);
            maxVal = Math.max(minVal, r.maxVal);
        }

        boolean searched = true;

        if (l != null && (!l.searched || l.maxVal >= head.val)) {
            searched = false;
        }

        if (r != null && (!r.searched || head.val >= r.minVal)) {
            searched = false;
        }

        return new ReturnTypeForSearch(searched, minVal, maxVal);
    }

    private record ReturnTypeForSearch(boolean searched, int minVal, int maxVal) {
    }

    /**
     * 是否是平衡二叉树 递归套路
     * 同时满足3个条件
     * 1. 左树是平衡二叉树
     * 2. 右树也是平衡二叉树
     * 3. 左树和右树的高度差不超过1
     *
     * @param head head
     * @return
     */
    public static boolean isBalancedBTUseRecursion(BinaryNode head) {
        return isBalancedBTProcess(head).balanced;

    }

    private static ReturnTypeForBalanced isBalancedBTProcess(BinaryNode head) {
        if (head == null) {
            return new ReturnTypeForBalanced(true, 0);
        }
        ReturnTypeForBalanced left = isBalancedBTProcess(head.left);
        ReturnTypeForBalanced right = isBalancedBTProcess(head.right);
        int height = Math.max(left.height, right.height) + 1;
        boolean balanced = left.balanced && right.balanced && (Math.abs(left.height - right.height) < 2);
        return new ReturnTypeForBalanced(balanced, height);
    }

    private record ReturnTypeForBalanced(boolean balanced, int height) {
    }

    /**
     * 是否完全二叉树
     * 使用宽度遍历,满足
     * 1.是否碰到左右不双全节点(l == null || r ==null)
     * 2.碰到不双全节点之后,后续的节点必须全是叶子节点(left == null && right ==null)
     *
     * @param head head
     * @return bool
     */
    public boolean isCompletionBT(BinaryNode head) {
        if (head == null) {
            return true;
        }
        Queue<BinaryNode> queue = new LinkedList<>();
        queue.add(head);

        boolean existUnCompletionNode = false;
        BinaryNode l;
        BinaryNode r;
        while (!queue.isEmpty()) {
            head = queue.poll();
            l = head.left;
            r = head.right;

            // 如果发现不双全节点 并且 当前节点不是叶子节点
            if (existUnCompletionNode && !(l == null && r == null)) {
                return false;
            }

            // 如果存在右不存在左 必定不是完全二叉树
            if ((l == null && r != null)) {
                return false;
            }

            if (l != null) {
                queue.offer(l);
            }
            if (r != null) {
                queue.offer(r);
            }

            if (l == null || r == null) {
                existUnCompletionNode = true;
            }
        }


        return true;
    }


    public static void travel(BinaryNode node) {
        travel(node, RecursionOrder.PreOrder);
    }

    /**
     * 使用非递归,改写先序的递归徐
     *
     * @param head er
     */
    public static void preOrderUnRecursion(BinaryNode head) {
        if (head == null) {
            return;
        }
        Stack<BinaryNode> stack = new Stack<>();
        stack.push(head);
        while (!stack.isEmpty()) {
            BinaryNode pop = stack.pop();
            System.out.println(pop.val);
            if (pop.right != null) {
                stack.push(pop.right);
            }
            if (pop.left != null) {
                stack.push(pop.left);
            }
        }
    }

    /**
     * 改为后续
     *
     * @param head head
     */
    public static void postOrderUnRecursion(BinaryNode head) {
        if (head != null) {
            Stack<BinaryNode> stack = new Stack<>();
            Stack<BinaryNode> collect = new Stack<>();
            stack.push(head);
            while (!stack.isEmpty()) {
                head = stack.pop();
                collect.push(head);
                if (head.left != null) {
                    stack.push(head.left);
                }
                if (head.right != null) {
                    stack.push(head.right);
                }
            }

            while (!collect.isEmpty()) {
                System.out.println(collect.pop());
            }
        }

    }

    /**
     * 1 一直江左节点压栈
     * 2 压完了  弹出 并继续压弹出节点的所有左节点
     */
    public static void inOrderUnRecursion(final BinaryNode head) {
        if (head != null) {
            Stack<BinaryNode> stack = new Stack<>();
            BinaryNode cur = head;
            while (!stack.isEmpty() || cur != null) {
                if (cur != null) {
                    stack.push(cur);
                    cur = cur.left;
                } else {
                    cur = stack.pop();
                    System.out.println(cur.val);
                    cur = cur.right;
                }
            }
        }

    }

    /**
     * 宽度优先遍历
     * 找出最宽的层
     *
     * @param head head
     */
    public static int widthOrderUnRecursion(BinaryNode head) {
        if (head == null) {
            return 0;
        }
        Queue<BinaryNode> queue = new LinkedList<>();
        queue.offer(head);
        Map<BinaryNode, Integer> levelMap = new HashMap<>();
        levelMap.put(head, 1);
        int curLevel = 1;
        int curLevelNodesCount = 0;
        int max = Integer.MIN_VALUE;
        while (!queue.isEmpty()) {
            BinaryNode cur = queue.poll();
            Integer curNodeLevel = levelMap.get(cur);
            if (curNodeLevel == curLevel) {
                curLevelNodesCount++;
            } else {
                max = Math.max(max, curLevelNodesCount);
                curLevel++;
                curLevelNodesCount = 1;

            }
//            System.out.println(cur.val);
            if (cur.left != null) {
                levelMap.put(cur.left, curNodeLevel + 1);
                queue.offer(cur.left);
            }
            if (cur.right != null) {
                levelMap.put(cur.right, curNodeLevel + 1);
                queue.offer(cur.right);
            }
        }
        return max;
    }

}
