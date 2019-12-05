package com.study.arithmetic.sort;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest {
    @Test
    public void addition_isCorrect() {
        assertEquals(4, 2 + 2);
    }

    @Test
    public void testSort() {
//        int[] array = new int[]{4, 7, 8, 10, 14, 21, 22, 36, 62, 77, 81, 91};
//        printList(array);
//        System.out.println("index = " + binarySearch(array, 0, array.length, 9));


        int[] list = new int[]{31, 68, 45, 90, 23, 39, 54, 68, 87, 76};
//        int[] array = new int[]{2, 4, 8, 9, 3, 5, 7, 10};
        printList(list);
//        quickSort(array, 0, array.length - 1);
        mergeSort(list, 0, list.length - 1);
        printList(list);
    }

    public int binarySearch(int[] array, int fromIndex, int toIndex, int key) {
        int low = fromIndex;
        int high = toIndex - 1; // 引用了左右开的原则概念
        while (low <= high) { // 不要使用递归，循环的效率比递归高
            int mid = (low + high) >> 1; // 取中间 x >> 1 = x / 2
            int midVal = array[mid];
            if (midVal > key) {
                // 查找左边
                high = mid - 1;
            } else if (midVal < key) {
                // 查找右边
                low = mid + 1;
            } else {
                // 查找到了
                return mid;
            }
        }
        return -(low + 1); // low+1表示找不到时停在了第low+1个元素的位置，+1保证返回的是负数
    }

    /**
     * 快速排序: Arrays.sort(array); 缺点：只能排顺序表，不能排链表 -- 前序
     *
     * @param array
     * @param begin
     * @param end
     */
    public void quickSort(int[] array, int begin, int end) {
        /**
         * 1、跟
         */
        if (begin >= end) {
            return;
        }

        int low = begin;
        int high = end;
        int x = array[begin];
        boolean direction = true; // true:从右往左查找 false:从左往右查找
        L1:
        while (low < high) {
            if (direction) { // 从右往左查找
                for (int i = high; i > low; i--) {
                    if (array[i] <= x) { // 缺点：1、如果有大量的相同数据，也会进行取值比较操作，性能就会下降
                        array[low++] = array[i];
                        high = i;
                        direction = !direction; // 更换方向
                        continue L1;
                    }
                }
                high = low; // 如果没有查找到，让两个指针重合
            } else {
                for (int i = low; i < high; i++) {
                    if (array[i] >= x) {
                        array[high--] = array[i];
                        low = i;
                        direction = !direction; // 更换方向
                        continue L1;
                    }
                }
                low = high; // 如果没有查找到，让两个指针重合
            }
        }
        array[low] = x;
        /**
         * 2、左
         */
        quickSort(array, begin, low - 1);
        /**
         * 3、右
         */
        quickSort(array, low + 1, end);
    }

    // 2 4 8 9    3 5 7 10
    public void merge(int[] array, int left, int middle, int right) {
        int leftSize = middle - left;
        int rightSize = right - middle + 1;
        int[] leftArray = new int[leftSize];
        int[] rightArray = new int[rightSize];
        for (int i = 0; i < leftSize; i++) {
            leftArray[i] = array[left + i];
        }
        for (int i = 0; i < rightSize; i++) {
            rightArray[i] = array[middle + i];
        }
        int indexLeft = 0, indexRight = 0, key = left;
        while (indexLeft < leftSize && indexRight < rightSize) {
            if (leftArray[indexLeft] < rightArray[indexRight]) {
                array[key] = leftArray[indexLeft++];
                key++;
            } else {
                array[key] = rightArray[indexRight++];
                key++;
            }
        }
        // 右边剩下的全部拷贝到排序数组中
        if (indexLeft >= leftSize) {
            for (int i = indexRight; i < rightSize; i++) {
                array[key] = rightArray[i];
                key++;
            }
        }
        // 左边剩下的全部拷贝到排序数组中
        if (indexRight >= rightSize) {
            for (int i = indexLeft; i < leftSize; i++) {
                array[key] = leftArray[i];
                key++;
            }
        }
    }

    /**
     * 归并排序 -- 后序
     * @param array
     * @param left
     * @param right
     */
    public void mergeSort(int[] array, int left, int right) {
        if (left == right) {
            return;
        }
        int middle = (left + right) >> 1;
        mergeSort(array, left, middle);
        mergeSort(array, middle + 1, right);
        merge(array, left, middle + 1, right);
    }

    public void printList(int[] list) {
        for (int i = 0; i < list.length; i++) {
            System.out.print(list[i] + " ");
        }
        System.out.println("\n---------");
    }
}