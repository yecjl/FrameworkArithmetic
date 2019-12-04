package com.study.arithmetic.Day11_图论;

import java.util.LinkedList;

public class Graph {
    public int[] vertices;//顶点集
    public int[][] matrix;//图的边的信息
    public int verticesSize;

    public boolean[] visited; // 是否选择

    public static final int MAX_VALUE = Integer.MAX_VALUE;
    public static final int NONE_VALUE = -1;

    public Graph(int verticesSize) {
        this.verticesSize = verticesSize;
        vertices = new int[verticesSize];
        matrix = new int[verticesSize][verticesSize];
        visited = new boolean[verticesSize];

        for (int i = 0; i < verticesSize; i++) {
            vertices[i] = i;
        }
    }

    /**
     * 计算v1到v2的权重(路径长度)
     */
    public int getWeight(int v1, int v2) {
        int weight = this.matrix[v1][v2];
        return weight == 0 ? 0 : (weight == MAX_VALUE ? NONE_VALUE : weight);
    }

    /**
     * 获取顶点
     */
    public int[] getVertices() {
        return vertices;
    }

    /**
     * 获取出度
     */
    public int getOutDegree(int v) {
        int degree = 0;
        for (int i = 0; i < verticesSize; i++) {
            if (matrix[v][i] != 0 && matrix[v][i] != MAX_VALUE) {
                degree++;
            }
        }
        return degree;
    }

    /**
     * 获取入度
     */
    public int getInDegree(int v) {
        int degree = 0;
        for (int i = 0; i < verticesSize; i++) {
            if (matrix[i][v] != 0 && matrix[i][v] != MAX_VALUE) {
                degree++;
            }
        }
        return degree;
    }

    /**
     * 查找到当前顶点的第一个可选的顶点
     *
     * @param vertex
     * @return
     */
    public int getFirstVertex(int vertex) {
        for (int i = 0; i < verticesSize; i++) {
            if (matrix[vertex][i] != 0 && matrix[vertex][i] != MAX_VALUE) {
                return i;
            }
        }
        return NONE_VALUE;
    }

    /**
     * 查找到当前顶点，index对应的下一个可选的顶点
     *
     * @param vertex
     * @param index
     * @return
     */
    public int getNextVertex(int vertex, int index) {
        for (int i = index + 1; i < verticesSize; i++) {
            if (matrix[vertex][i] != 0 && matrix[vertex][i] != MAX_VALUE) {
                return i;
            }
        }
        return NONE_VALUE;
    }

    /**
     * 深度优先(很象二叉树的前序)
     */
    private void dfs(int vertex) {
        visited[vertex] = true;
        int nextVertex = getFirstVertex(vertex);
        while (nextVertex != NONE_VALUE) {
            if (!visited[nextVertex]) {
                System.out.println(nextVertex);
                dfs(nextVertex);
            }
            nextVertex = getNextVertex(vertex, nextVertex);
        }
    }

    /**
     * 深度优先(很象二叉树的前序)
     */
    public void dfs() {
        for (int i = 0; i < verticesSize; i++) {
            if (!visited[i]) {
                System.out.println(i);
                dfs(i);
            }
        }
    }

    /**
     * 广度优先：用队列的算法
     */
    public void bfs() {
        LinkedList<Integer> list = new LinkedList<>();
        for (int i = 0; i < verticesSize; i++) {
            if (!visited[i]) {
                System.out.println(i);
                bfs(i, list);
            }
        }
    }

    private void bfs(int vertex, LinkedList<Integer> list) {
        visited[vertex] = true;
        for (int i = 0; i < verticesSize; i++) {
            if (matrix[vertex][i] != 0 && matrix[vertex][i] != MAX_VALUE && !visited[i]) {
                list.offer(i);
            }
        }
        if (!list.isEmpty()) {
            int poll = list.poll();
            System.out.println(poll);
            bfs(poll, list);
        }
    }

}
