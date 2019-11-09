package com.study.arithmetic;

import android.util.Log;

/**
 * @Description: 包含地图所需的所有输入数据
 */
public class MapInfo
{
	public int[][] map; // 二维数组的地图
	public int width; // 地图的宽
	public int height; // 地图的高
	public Node start; // 起始结点
	public Node end; // 最终结点
	
	public MapInfo(int[][] map, int width, int height, Node start, Node end)
	{
		this.map = map;
		this.width = width;
		this.height = height;
		this.start = start;
		this.end = end;
		Log.i("jett","初始化地图成功");
	}
}
