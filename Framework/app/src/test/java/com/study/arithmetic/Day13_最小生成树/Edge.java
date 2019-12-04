package com.study.arithmetic.Day13_最小生成树;

import java.util.Map;

public class Edge {
    int start;
    int end;
    int weight;

    public Edge(int start, int end, int weight) {
        this.start = start;
        this.end = end;
        this.weight = weight;
    }

    public int getStart() {
        return start;
    }

    public void setStart(int start) {
        this.start = start;
    }

    public int getEnd() {
        return end;
    }

    public void setEnd(int end) {
        this.end = end;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof Edge)) {
            return false;
        }
        Edge edge = (Edge) o;
        return (valEquals(start, edge.start) && valEquals(end, edge.end) && valEquals(weight, edge.weight)) ||
                valEquals(start, edge.end) && valEquals(end, edge.start) && valEquals(weight, edge.weight);
    }

    /**
     * Test two values for equality.  Differs from o1.equals(o2) only in
     * that it copes with {@code null} o1 properly.
     */
    static final boolean valEquals(Object o1, Object o2) {
        return (o1 == null ? o2 == null : o1.equals(o2));
    }

    @Override
    public int hashCode() {
        return (start + end + weight) ^ 7;
    }
}