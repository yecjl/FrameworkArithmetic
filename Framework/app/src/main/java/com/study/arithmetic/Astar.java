package com.study.arithmetic;

import android.graphics.Path;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;

public class Astar {
    public final static int DIRECT_VALUE = 10;
    public final static int OBLIQUE_VALUE = 14;
    // 开放列表  白格子 保存计算过的格子
    private Queue<Node> openList = new PriorityQueue<>();
    //关闭列表  黄格子 保存选中的格子
    private List<Node> closeList = new ArrayList<>();

    /**
     * 计算H值
     */
    private int calcH(Coord end, Coord coord) {
        return Math.abs(end.x - coord.x) + Math.abs(end.y - coord.y);
    }

    /**
     * 判断是否为终点
     */
    private boolean isEndNode(Coord end, Coord coord) {
        return coord != null && coord.equals(end);
    }

    /**
     * 从open中查找
     */
    private Node findNodeInOpen(Coord coord) {
        if (coord == null || openList.isEmpty()) {
            return null;
        }
        for (Node node : openList) {
            if (node.coord.equals(coord)) {
                return node;
            }
        }
        return null;
    }

    /**
     * 判断是否在close中
     */
    private boolean isCoordInClose(Coord coord) {
        return coord != null && isCoordInClose(coord.x, coord.y);
    }

    /**
     * 判断是否在close中
     */
    private boolean isCoordInClose(int x, int y) {
        if (closeList.isEmpty()) {
            return false;
        }
        for (Node node : closeList) {
            if (node.coord.x == x && node.coord.y == y) {
                return true;
            }
        }
        return false;
    }

    /**
     * 判断一个位置是否可以选择
     */
    private boolean canAddNodeToOpen(MapInfo mapInfo, int x, int y) {
        // 判断是否在地图的边境内
        if (x < 0 || x >= mapInfo.width || y < 0 || y >= mapInfo.height) return false;
        // 判断当前的点是否为墙 屏幕坐标和map坐标是相反的
        if (mapInfo.map[y][x] == MapUtils.WALL) return false;
        // 判断当前的点是否已经选择过了
        return !isCoordInClose(x, y);
    }

    /**
     * @param mapInfo
     */
    public void start(MapInfo mapInfo) {
        if (mapInfo == null) {
            return;
        }
        // 清空
        openList.clear();
        closeList.clear();
        openList.add(mapInfo.start);
        moveNodes(mapInfo);
    }

    private void moveNodes(MapInfo mapInfo) {
        while (!openList.isEmpty()) {
            if (isCoordInClose(mapInfo.end.coord)) {
                calcPath(mapInfo.end);
                return;
            }
            // 将最小的节点添加到close中
            Node minNode = openList.poll();
            closeList.add(minNode);
            // 向四周扩散
            addNeighborNodeInOpen(mapInfo, minNode);
        }
    }

    private void calcPath(Node end) {
        MapUtils.path = new Path();
        if (end != null) {
            MapUtils.path.moveTo(end.coord.x * 80 + 40, end.coord.y * 80 + 40);
        }
        while (end != null) {
            MapUtils.path.lineTo(end.coord.x * 80 + 40, end.coord.y * 80 + 40);
            // 把结果放到栈里面
            MapUtils.result.push(end);
            end = end.parent;
        }
    }

    private void addNeighborNodeInOpen(MapInfo mapInfo, Node currentNode) {
        int x = currentNode.coord.x;
        int y = currentNode.coord.y;
        addNeighborNodeInOpen(mapInfo, currentNode, new Coord(x, y - 1), DIRECT_VALUE);
        addNeighborNodeInOpen(mapInfo, currentNode, new Coord(x, y + 1), DIRECT_VALUE);
        addNeighborNodeInOpen(mapInfo, currentNode, new Coord(x + 1, y), DIRECT_VALUE);
        addNeighborNodeInOpen(mapInfo, currentNode, new Coord(x - 1, y), DIRECT_VALUE);

        addNeighborNodeInOpen(mapInfo, currentNode, new Coord(x + 1, y - 1), OBLIQUE_VALUE);
        addNeighborNodeInOpen(mapInfo, currentNode, new Coord(x + 1, y + 1), OBLIQUE_VALUE);
        addNeighborNodeInOpen(mapInfo, currentNode, new Coord(x - 1, y - 1), OBLIQUE_VALUE);
        addNeighborNodeInOpen(mapInfo, currentNode, new Coord(x - 1, y + 1), OBLIQUE_VALUE);
    }

    private void addNeighborNodeInOpen(MapInfo mapInfo, Node currentNode, Coord moveCoord, int directValue) {
        Node moveNode = findNodeInOpen(moveCoord);
        if (moveNode == null) {
            // 判断是否能够通过
            if (canAddNodeToOpen(mapInfo, moveCoord.x, moveCoord.y)) {
                Node endNode = mapInfo.end;
                int g = currentNode.g + directValue;
                int h = calcH(endNode.coord, moveCoord);
                // 判断当前的点是否是终点
                if (isEndNode(moveCoord, endNode.coord)) {
                    moveNode = endNode;
                    moveNode.parent = currentNode;
                    moveNode.g = g;
                    moveNode.h = h;
                } else {
                    moveNode = new Node(moveCoord, currentNode, g, h);
                }
                openList.add(moveNode);
            }
        }
    }
}
