package com.study.arithmetic.majiang;

import java.util.LinkedList;
import java.util.List;

/**
 * 功能：麻将类
 * <p>
 * Created by danke on 2018/11/28.
 */
public class Mahjong {
    /**
     * 类型：万，条，筒
     */
    public int suit;
    /**
     * 点数：一，二，三
     */
    public int rank;

    public static int SUIT_WANG = 1; // 花色：万
    public static int SUIT_TIAO = 2; // 花色：条
    public static int SUIT_TONG = 3; // 花色：筒

    public Mahjong(int suit, int rank) {
        this.rank = rank;
        this.suit = suit;
    }

    @Override
    public String toString() {
        StringBuffer sb = new StringBuffer();
        if (suit == SUIT_WANG) {
            sb.append("万");
        } else if (suit == SUIT_TIAO) {
            sb.append("条");
        } else if (suit == SUIT_TONG) {
            sb.append("筒");
        }
        sb.append(rank);
        return sb.toString();
    }
}