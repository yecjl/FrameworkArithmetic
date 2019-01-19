package com.study.arithmetic.Day1.card;

/**
 * 功能：
 * <p>
 * Created by danke on 2019/1/18.
 */
public class Cards implements Comparable {
    private int pokerColors; // 花色
    private int cardPoints; // 点数

    public Cards(int pokerColors, int cardPoints) {
        this.pokerColors = pokerColors;
        this.cardPoints = cardPoints;
    }

    /**
     * 比较对象的大小
     * @param o
     * @return
     */
    @Override
    public int compareTo(Object o) {
        Cards c = (Cards) o;
        if (this.cardPoints > c.cardPoints) {
            return 1;
        } else if (this.cardPoints < c.cardPoints) {
            return -1;
        }
        if (this.pokerColors > c.pokerColors) {
            return 1;
        } else if (this.pokerColors < c.pokerColors) {
            return -1;
        }
        return 0;
    }

    @Override
    public String toString() {
        return "Cards{" +
                "pokerColors=" + pokerColors +
                ", cardPoints=" + cardPoints +
                '}';
    }
}
