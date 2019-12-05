package com.study.arithmetic.Day2;

import java.util.NoSuchElementException;

/**
 * 功能：自己写的LinkedList
 * <p>
 * Created by danke on 2018/12/1.
 */
public class LinkedList<E> {
    transient int size = 0; // 大小
    /**
     * 头结点
     */
    transient Node<E> first; // 前驱
    /**
     * 尾结点
     */
    transient Node<E> last; // 后继

    /**
     * Constructs an empty array.
     */
    public LinkedList() {
    }

    /**
     * 添加数据
     * @param e
     * @return
     */
    public boolean add(E e) {
        linkLast(e);
        return true;
    }

    /**
     * 添加到index的位置
     * @param index
     * @param element
     * @return
     */
    public void add(int index, E element) {
        checkPositionIndex(index);
        if (index == size) {
            // 加到最后一位
            linkLast(element);
        } else {
            // 加到指定位置
            linkBefore(element, node(index));
        }
    }

    /**
     * 加到指定位置
     * @param element 需要添加的元素
     * @param succ 指定的节点
     */
    private void linkBefore(E element, Node<E> succ) {
        Node<E> prev = succ.prev;
        Node<E> newNode = new Node<>(prev, element, succ);
        succ.prev = newNode;
        if (prev  == null) {
            first = newNode;
        } else {
            prev.next = newNode;
        }
        size++;
    }

    /**
     * 从尾部添加数据
     * @param e
     */
    private void linkLast(E e) {
        Node<E> l = last;
        Node<E> newNode = new Node<>(l, e, null);
        last = newNode;
        if (l == null) {
            first = newNode;
        } else {
            l.next = newNode;
        }
        size++;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public Object[] toArray() {
        Object[] result = new Object[size];
        int i = 0;
        for (Node<E> x = first; x != null ; x = x.next) {
            result[i++] = x.item;
        }
        return result;
    }

    public E remove() {
        return removeFirst();
    }

    private E removeFirst() {
        final Node<E> f = first;
        if (f == null) {
            throw new NoSuchElementException();
        }
        return unlinkFirst(f);
    }

    private E unlinkFirst(Node<E> f) {
        E element = f.item;
        Node<E> next = f.next;
        f.item = null;
        f.next = null;  // help GC
        first = next;
        if (next == null) {
            last = null;
        } else {
            next.prev = null;
        }
        size--;
        return element;
    }



    public boolean addAll(LinkedList<? extends E> c) {
        return addAll(size, c);
    }

    private boolean addAll(int index, LinkedList<? extends E> c) {
        checkPositionIndex(index);

        Object[] a = c.toArray();
        int numNew = a.length;
        if (numNew == 0) {
            return false;
        }

        Node<E> pred, succ;
        if (index == size) {
            pred = last;
            succ = null;
        } else {
            succ = node(index);
            pred = succ.prev;
        }
        for (Object o : a) {
            @SuppressWarnings("unchecked") E e = (E) o;
            Node<E> newNode = new Node<E>(pred, e, null);
            if (pred == null) {
                first = newNode;
            } else {
                pred.next = newNode;
            }
            pred = newNode;  // 更新最后的节点
        }
        if (succ == null) {
            last = pred;
        } else {
            pred.next = succ;
            succ.prev = pred;
        }
        size += numNew;
        return false;
    }

    /**
     * 获取结点：链式结构查找数据比较困难，需要循环
     * 单向链表和双向链表的区别就在此处：双向链表的查找会比单向链表快
     * @param index
     * @return
     */
    private Node<E> node(int index) {
        if (index < (size >> 1)) { // 如果index在整个链表的前半部分，从前往后找 (size >> 1 = size / 2)
            Node<E> x = first;
            for (int i = 0; i < index; i++) {
                x = x.next;
            }
            return x;
        } else {  // 如果index在整个链表的后半部分，从后往前找
            Node<E> x = last;
            for (int i = size - 1; i > index; i--) {
                x = x.prev;
            }
            return x;
        }
    }

    private void checkElementIndex(int index) {
        if (!(isElementIndex(index))) {
            throw new IndexOutOfBoundsException(outOfBoundsMsg(index));
        }
    }

    /**
     * 判断是否index是否越界
     * @param index
     */
    private void checkPositionIndex(int index) {
        if (!(isPositionIndex(index))) {
            throw new IndexOutOfBoundsException(outOfBoundsMsg(index));
        }
    }

    private boolean isElementIndex(int index) {
        return index >= 0 && index < size;
    }

    private boolean isPositionIndex(int index) {
        return index >= 0 && index <= size;
    }

    private String outOfBoundsMsg(int index) {
        return "Index: " + index + ", Size: " + size;
    }

    public int size() {
        return size;
    }

    public E get(int index) {
        checkElementIndex(index);
        return node(index).item;
    }

    /**
     * 删除指定位置
     * @param index
     * @return
     */
    public E remove(int index) {
        checkElementIndex(index);
        return unlink(node(index));
    }

    /**
     * 删除指定位置的元素
     * @param x
     * @return
     */
    private E unlink(Node<E> x) {
        E item = x.item;
        Node<E> prev = x.prev;
        Node<E> next = x.next;
        if (prev == null) {
            first = next;
        } else {
            prev.next = next;
            x.prev = null;
        }
        if (next == null) {
            last = prev;
        } else {
            next.prev = prev;
            x.next = null;
        }
        x.item = null;
        size--;
        return item;
    }

    /**
     * 结点
     * @param <E>
     */
    protected static class Node<E> {
        E item; // 数据域
        Node<E> next; // 后继
        Node<E> prev; // 前驱

        Node(Node<E> prev, E element, Node<E> next) {
            this.item = element;
            this.next = next;
            this.prev = prev;
        }
    }
}
