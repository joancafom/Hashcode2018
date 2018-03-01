package com.hashcode2017.team333;

import java.util.SortedSet;

public interface EndPoint {

	public Integer getLatencyData();
	public Integer getLatencyCache(Integer c);
	public Integer getID();
	public SortedSet<Integer> getCacheServerList();
	public void addServerCache(Integer c, Integer l); //Needed this to construct
	
}
