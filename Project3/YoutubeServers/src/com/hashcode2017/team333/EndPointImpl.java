package com.hashcode2017.team333;

import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.SortedSet;
import java.util.TreeSet;

public class EndPointImpl implements EndPoint {

	private Integer id;
	private Integer latencyData;
	private Map<Integer,Integer> serverLatency;
	
	public EndPointImpl(Integer id, Integer latencyData){
		this.id = id;
		this.latencyData = latencyData;
		this.serverLatency = new HashMap<>();
	}
	
	public EndPointImpl(Integer id, Integer latencyData, Map<Integer,Integer> serverLatency){
		this.id = id;
		this.latencyData = latencyData;
		this.serverLatency = serverLatency;
	}
	
	public EndPointImpl (String cadena){
		String[] datos = cadena.split(",");
		
		if (datos.length != 2) {
			throw new IllegalArgumentException("El formato de la cadena para EndPoint no es válido");
		}
		
		this.id = new Integer(datos[0].trim());
		this.latencyData = new Integer(datos[1].trim());
		this.serverLatency = new HashMap<>();
	}
	
	public Integer getLatencyData() {
		// TODO Auto-generated method stub
		return latencyData;
	}
	@Override
	public Integer getLatencyCache(Integer c) {
		
		return serverLatency.containsKey(c) ? serverLatency.get(c) : -1;
	}
	@Override
	public Integer getID() {
		// TODO Auto-generated method stub
		return id;
	}
	@Override
	public SortedSet<Integer> getCacheServerList() {
		
		SortedSet<Integer> res = new TreeSet<>(Comparator.comparing(x->serverLatency.get(x)));
		res.addAll(serverLatency.keySet());
		return res;
	}

	@Override
	public void addServerCache(Integer c, Integer l) {
		this.serverLatency.put(c, l);
		
	}
	
	public String toString(){
		return "(" + this.id + "," + latencyData + ")";
	}
	
	
	
}
