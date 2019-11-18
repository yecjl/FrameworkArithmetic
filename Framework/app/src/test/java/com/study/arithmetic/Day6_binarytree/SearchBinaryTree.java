package com.study.arithmetic.Day6_binarytree;

import java.util.NoSuchElementException;

/**
 * 功能：二叉排序树
 * <p>
 * Created by danke on 2018/12/7.
 */
public class SearchBinaryTree {

    public TreeNode root;

    public TreeNode put(int data) {
        if (root == null) {
            root = new TreeNode(data);
            return root;
        } else {
            TreeNode node = this.root;
            TreeNode parentNode = null;
            while (node != null) {
                parentNode = node;
                if (node.data > data) {
                    node = node.leftChild;
                } else if (node.data < data) {
                    node = node.rightChild;
                } else { // 按照标准的排序树，是不允许插入相同值的，所以直接返回node （看业务需求）
                    return node;
                }
            }
            TreeNode insertNode = new TreeNode(data);
            insertNode.parent = parentNode;
            if (parentNode.data > data) {
                parentNode.leftChild = insertNode;
            } else if (parentNode.data < data) {
                parentNode.rightChild = insertNode;
            }
            return insertNode;
        }
    }

    /**
     * 查找对应数据的Node
     * @param data
     * @return
     */
    public TreeNode searchNode(int data) {
        if (root == null) {
            return null;
        }

        TreeNode node = this.root;
        while (node != null) {
            if (node.data > data) {
                node = node.leftChild;
            } else if (node.data < data) {
                node = node.rightChild;
            } else {
                return node; // 如果data是对象的话，就写equals方法
            }
        }

        return null;
    }

    /**
     * 删除节点
     * @param delNode
     */
    public void deleteNode(TreeNode delNode) {
        TreeNode treeNode = searchNode(delNode.data);
        if (delNode == null || treeNode == null) {
            throw new NoSuchElementException();
        }
        if (root == null) {
            return;
        }
        TreeNode parentNode = treeNode.parent;
        // 1、是叶子节点 1) 如果当前只有一个节点，那么root置空 2) 直接就删除父亲的（左/右孩子)
        if (treeNode.leftChild == null && treeNode.rightChild == null) {
            if (parentNode == null) {
                // 当前是root节点
                this.root = null;
            } else {
                // 当前是非root节点
                if (parentNode.leftChild == treeNode) {
                    parentNode.leftChild = null;
                } else if (parentNode.rightChild == treeNode) {
                    parentNode.rightChild = null;
                }
            }
            treeNode.parent = null;
        }
        // 2、当前删除的节点只有左孩子
        else if (treeNode.leftChild != null && treeNode.rightChild == null) {
            if (parentNode == null) {
                // 1、root = childNode 2、childNode.parent = null 3、node 断掉 right、left、parent
                this.root = treeNode.leftChild;
                treeNode.leftChild.parent = null;
            } else {
                // 1、parentNode.child --> childNode 2、childNode.parent --> parentNode 3、node 断掉 right、left、parent
                if (parentNode.leftChild == treeNode) {
                    parentNode.leftChild = treeNode.leftChild;
                } else if (parentNode.rightChild == treeNode) {
                    parentNode.rightChild = treeNode.leftChild;
                }
                treeNode.leftChild.parent = parentNode;
                treeNode.parent = null;
            }
            treeNode.leftChild = null;
        }
        // 3、当前删除的节点只有右孩子
        else if (treeNode.leftChild == null && treeNode.rightChild != null) {
            if (parentNode == null) {
                // 1、root = childNode 2、childNode.parent = null 3、node 断掉 right、left、parent
                this.root = treeNode.rightChild;
                treeNode.rightChild.parent = null;
            } else {
                // 1、parentNode.child --> childNode 2、childNode.parent --> parentNode 3、node 断掉 right、left、parent
                if (parentNode.leftChild == treeNode) {
                    parentNode.leftChild = treeNode.rightChild;
                } else if (parentNode.rightChild == treeNode){
                    parentNode.rightChild = treeNode.rightChild;
                }
                treeNode.rightChild.parent = parentNode;
                treeNode.parent = null;
            }
            treeNode.rightChild = null;
        }
        // 4、当前删除的节点既有左孩子又有右孩子
        else if (treeNode.leftChild != null && treeNode.rightChild != null) {
            // 1、如果rightChild没有左子节点, 直接拿rightChild顶上
            if (treeNode.rightChild.leftChild == null) {
                if (parentNode == null) {
                    // 1、root = rightChild
                    // 1、rightChild.parent = null
                    // 1、rightChild.left --> leftChild 2、leftChild.parent --> rightChild
                    // 1、node 断掉 right、left
                    this.root = treeNode.rightChild;
                    treeNode.rightChild.parent = null;
                    treeNode.rightChild.leftChild = treeNode.leftChild;
                    treeNode.leftChild.parent = treeNode.rightChild;
                    treeNode.leftChild = null;
                    treeNode.rightChild = null;
                } else {
                    // 1、parentNode.leftChild/rightChild --> rightChild 2、rightChild.parent --> parentNode
                    if (parentNode.leftChild == treeNode) {
                        parentNode.leftChild = treeNode.rightChild;
                    } else if (parentNode.rightChild == treeNode) {
                        parentNode.rightChild = treeNode.rightChild;
                    }
                    treeNode.rightChild.parent = parentNode;
                    // 1、rightChild.left --> leftChild 2、leftChild.parent --> rightChild
                    treeNode.rightChild.leftChild = treeNode.leftChild;
                    treeNode.leftChild.parent = treeNode.rightChild;
                    // 1、node 断掉 right、left、parent
                    treeNode.parent = null;
                    treeNode.leftChild = null;
                    treeNode.rightChild = null;
                }
            }
            // 2、如果有左右child, 查找到rightChild上的最小左孩子，顶上去
            else {
                // 1、查找到rightChild最小的左孩子
                TreeNode minLeftChild = treeNode.rightChild.leftChild;
                while (minLeftChild.leftChild != null) {
                    minLeftChild = minLeftChild.leftChild;
                }

                if (parentNode == null) {
                    // 1、minChild.left --> leftChild 2、leftChild.parent --> minChild
                    minLeftChild.leftChild = treeNode.leftChild;
                    treeNode.leftChild.parent = minLeftChild;
                    // 1、minChild.right.parent --> minChild.parent.left  2、minChild.parent.left -->  minChild.right
                    TreeNode minOfRightChild = minLeftChild.rightChild;
                    if (minOfRightChild != null) {
                        minOfRightChild.parent = minLeftChild.parent;
                    }
                    minLeftChild.parent.leftChild = minOfRightChild;
                    // 1、minChild.right --> rightChild 2、rightChild.parent --> minChild
                    minLeftChild.rightChild = treeNode.rightChild;
                    treeNode.rightChild.parent = minLeftChild;
                    // 1、minChild.parent = null
                    minLeftChild.parent = null;
                    // 1、root = minChild
                    this.root = minLeftChild;
                    // 1、node 断掉 right、left、parent
                    treeNode.leftChild = null;
                    treeNode.rightChild = null;

                } else {
                    // 1、minChild.left --> leftChild 2、leftChild.parent --> minChild
                    minLeftChild.leftChild = treeNode.leftChild;
                    treeNode.leftChild.parent = minLeftChild;
                    // 1、minChild.right.parent --> minChild.parent.left  2、minChild.parent.left -->  minChild.right
                    TreeNode minOfRightChild = minLeftChild.rightChild;
                    minOfRightChild.parent = minLeftChild.parent;
                    minLeftChild.parent.leftChild = minOfRightChild;
                    // 1、minChild.right --> rightChild 2、rightChild.parent --> minChild
                    minLeftChild.rightChild = treeNode.rightChild;
                    treeNode.rightChild.parent = minLeftChild;
                    // 1、minChild.parent --> parent.(left/right) 2、parent.(left/right) --> minChild
                    if (parentNode.leftChild == treeNode) {
                        minLeftChild.parent = parentNode;
                        parentNode.leftChild = minLeftChild;
                    } else if (parentNode.rightChild == treeNode) {
                        minLeftChild.parent = parentNode;
                        parentNode.rightChild = minLeftChild;
                    }
                    // 1、node 断掉 right、left、parent
                    treeNode.parent = null;
                    treeNode.leftChild = null;
                    treeNode.rightChild = null;
                }
            }
        }
    }

    /**
     * 中序遍历
     * @param root
     */
    public void midOrderTraverse(TreeNode root) {
        if (root == null) {
            return;
        }
        midOrderTraverse(root.leftChild);
        System.out.print(root.data + " ");
        midOrderTraverse(root.rightChild);
    }

    public static class TreeNode {
        int data;
        TreeNode leftChild;
        TreeNode rightChild;
        TreeNode parent;

        public TreeNode(int data) {
            this.data = data;
        }

        public TreeNode(int data, TreeNode leftChild, TreeNode rightChild, TreeNode parent) {
            this.data = data;
            this.leftChild = leftChild;
            this.rightChild = rightChild;
            this.parent = parent;
        }
    }

}
