package com.study.arithmetic.Day13_最小生成树;

import java.util.HashSet;

/**
 * @ClassName Kruskal
 * @Description TODO
 * @Author danke
 * @Date 2019-12-01 21:54
 * @Version 1.0
 */
class Kruskal {
    public static final int I = Integer.MAX_VALUE;
    public int verticeSize;
    public int[] vertice;
    public int[][] matrix;
    public int edgeSize;

    public Kruskal(int verticeSize) {
        this.verticeSize = verticeSize;
        this.matrix = new int[verticeSize][verticeSize];
        this.vertice = new int[verticeSize];
    }

    private Edge[] getEdges() {
        HashSet<Edge> edgeSet = new HashSet<>();
        for (int i = 0; i < verticeSize; i++) {
            for (int j = 0; j < verticeSize; j++) {
                if (matrix[i][j] != 0 && matrix[i][j] != I) {
                    edgeSet.add(new Edge(i, j, matrix[i][j]));
                }
            }
        }
        Edge[] edges = new Edge[verticeSize * verticeSize];
        edgeSize = edgeSet.size();
        return edgeSet.toArray(edges);
    }

    private void soreEdges(Edge[] edges) {
        for (int i = edgeSize - 1; i > 0; i--) {
            boolean flag = true;
            for (int j = 0; j < i; j++) {
                if (edges[j].weight > edges[j + 1].weight) {
                    Edge temp = edges[j];
                    edges[j] = edges[j + 1];
                    edges[j + 1] = temp;
                    flag = false;
                }
            }
            if (flag) { // 减少一定的排序次数
                break;
            }
        }
    }

    public int getEnd(int[] edges, int index) {
        while (edges[index] != 0) {
            index = edges[index];
        }
        return index;
    }

    public void kruskal() {
        Edge[] edges = getEdges();
        soreEdges(edges);
        Edge[] results = new Edge[edgeSize];
        int[] edge_temp = new int[edgeSize];
        int index = 0;
        for (int i = 0; i < edgeSize; i++) {
            int start = edges[i].start;
            int end = edges[i].end;
            int m = getEnd(edge_temp, start);
            int n = getEnd(edge_temp, end);
            if (m != n) {
                results[index++] = edges[i];
                if (m > n) {
                    int temp = m;
                    m = n;
                    n = temp;
                }
                edge_temp[m] = n;
            }
        }

        int lengh = 0;
        for (int i = 0; i < index; i++) {
            lengh += results[i].weight;
        }
        System.out.println("最小生成树的权重之和" + lengh);
        char[] chars = new char[verticeSize];
        chars[0] = 'A';
        chars[1] = 'B';
        chars[2] = 'C';
        chars[3] = 'D';
        chars[4] = 'E';
        chars[5] = 'F';
        chars[6] = 'G';

        for (int i = 0; i < index; i++) {
            System.out.printf("(%s, %s)---> %d \n", chars[results[i].start], chars[results[i].end], matrix[results[i].start][results[i].end]);
        }

    }
}
