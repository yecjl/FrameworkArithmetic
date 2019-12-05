package com.study.arithmetic.Day2;

import org.junit.Test;

import java.util.Map;

import static com.study.arithmetic.Day2.Mahjong.SUIT_TIAO;
import static com.study.arithmetic.Day2.Mahjong.SUIT_TONG;
import static com.study.arithmetic.Day2.Mahjong.SUIT_WANG;
import static org.junit.Assert.assertEquals;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest {
    private int ORDER_BY_SUIT = 0;  // 按照花色排序
    private int ORDER_BY_RANK = 1;  // 按照点数排序

    @Test
    public void addition_isCorrect() {
        assertEquals(4, 2 + 2);
    }

    @Test
    public void testMahjong() {
        LinkedList<Mahjong> list = new LinkedList<>();
        list.add(new Mahjong(SUIT_WANG, 7));
        list.add(new Mahjong(SUIT_TONG, 9));
        list.add(new Mahjong(SUIT_TONG, 6));
        list.add(new Mahjong(SUIT_TIAO, 9));
        list.add(new Mahjong(SUIT_TONG, 1));
        list.add(new Mahjong(SUIT_WANG, 4));
        list.add(new Mahjong(SUIT_WANG, 2));
        list.add(new Mahjong(SUIT_WANG, 1));
        list.add(new Mahjong(SUIT_WANG, 3));
        list.add(new Mahjong(SUIT_TONG, 9));
        list.add(new Mahjong(SUIT_TONG, 5));
        list.add(new Mahjong(SUIT_TIAO, 6));
        list.add(new Mahjong(SUIT_TIAO, 8));
        list.add(new Mahjong(SUIT_TONG, 9));
        list.add(list.size, new Mahjong(SUIT_WANG, 9));
        printList(list);

        System.out.println("\n------------");
        list.remove(0);

        printList(list);

        System.out.println("\n------------");

        radixSort(list);

        printList(list);
    }

    public void radixSort(LinkedList<Mahjong> list) {
        // 1、按照点数分组
        sortGroup(list, ORDER_BY_RANK, 9);
        // 2、按照类型分组
        sortGroup(list, ORDER_BY_SUIT, 3);
    }

    /**
     * 根据对应的orderBy进行分类合并
     *
     * @param list
     * @param orderBy
     * @param groupNumber
     */
    private void sortGroup(LinkedList<Mahjong> list, int orderBy, int groupNumber) {
        LinkedList<Mahjong>[] numList = new LinkedList[groupNumber];
        for (int i = 0; i < numList.length; i++) {
            numList[i] = new LinkedList<>();
        }
        while (!list.isEmpty()) {
            Mahjong remove = list.remove(); // 将麻将取出
            int index = remove.rank - 1; // 下标为点数-1
            if (orderBy == ORDER_BY_SUIT) { // 下标为花色-1
                index = remove.suit - 1;
            }
            numList[index].add(remove); // 放到对应链表中
        }
        // 将链表组拼在一起
        for (int i = 0; i < numList.length; i++) {
            list.addAll(numList[i]);
        }
    }

    /**
     * 打印
     *
     * @param list
     */
    private void printList(LinkedList<Mahjong> list) {
        if (list != null && !list.isEmpty()) {
            for (LinkedList.Node<Mahjong> x = list.first; x != null; x = x.next) {
                System.out.print(x.item + " ");
            }
        }
//        for (int i = 0; i < array.size(); i++) {
//            System.out.print(array.get(i) + " ");
//        }
    }
}