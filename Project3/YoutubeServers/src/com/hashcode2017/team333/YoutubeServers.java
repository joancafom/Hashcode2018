package com.hashcode2017.team333;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.SortedSet;
import java.util.TreeSet;
import java.util.stream.Collectors;

public class YoutubeServers {
	
	private static List<Integer> dataServerVideos; //List of all the videos (all are in the Data Center) (index --> ID, l[index]--> Size)
	private static List<EndPoint> listEndPoints; //List Endpoints
	private static Integer numCach;//Number of cacheServers of storage storageCache
	private static Integer storageCach; //The storage of each Cache Server
	private static Map<Integer,List<RequestDescription>> endPointsToRequests; //Map every endPoint which its requestDescriptions
	private static List<Integer> res; // Index --> Cache ID res[index] --> List with all the videos
	

	public static void main(String[] args) {
	
		System.out.println("Reading the input file...");
		instantiateProblem(readFile("res/me_at_the_zoo.in"));
		System.out.println("Problem Instantiated!");
		System.out.println("jj");
	}
	
	private static List<String> readFile(String fileName){
		
		List<String> res = null;
		
		try{
			res = Files.lines(Paths.get(fileName)).collect(Collectors.toList());
		}
		catch (IOException e){
			System.out.println("There was an error opening the file: " + fileName);
			System.out.println("Error: " + e.getMessage());
			e.printStackTrace();
		}
		catch (Exception e){
			System.out.println("There was an unexpected error: " + e.getMessage());
			e.printStackTrace();
		}
		
		return res;
		
	}
	
	private static void instantiateProblem(List<String> ins){
		
		if(ins == null){
			throw new IllegalArgumentException("Instructions weren't read properly");
		}
		
		dataServerVideos = new ArrayList<Integer>();
		listEndPoints = new ArrayList<EndPoint>();
		endPointsToRequests = new HashMap<>();
		
		int lc = 0; //Lines Count
		String[] datos; //The data of the current line
		Integer numVideos,numEndP,numReqD; //Aux values (header of the file)
		
		//#1 - We read the header, which is composed of 5 fields
		datos = ins.get(lc).split(" "); 
		if(datos.length != 5){
			throw new IllegalArgumentException("El formato del header no es válido" + ins.get(lc));
		}
			
		numVideos = new Integer(datos[0].trim());
		numEndP = new Integer(datos[1].trim());
		numReqD = new Integer(datos[2].trim());
		numCach = new Integer(datos[3].trim());
		storageCach = new Integer(datos[4].trim());
		
		System.out.println("NumVideos: " + numVideos + "\nnumEndPoints: " + numEndP + "\nnumRequesDescriptions: " 
		+ numReqD + "\nnumCacheServers and their available storage: " + numCach + " , " + storageCach + "\n");
		
		//#2 - Then we read the different Videos
		lc++;
		
		datos = ins.get(lc).split(" ");
		
		if(datos.length != numVideos){
			throw new IllegalArgumentException("El número de videos no coincide con el indicado en el header (" + numVideos + "): " + ins.get(lc));
		}
		
		for(int i = 0; i<numVideos; i++){
			dataServerVideos.add(new Integer (datos[i].trim()));
		}
		
		//#3 - Now we read the EndPoints
		Integer connectedCachesAux;
		String[] aux;
		
		for(int i = 0; i < numEndP; i++){
			lc++;
			datos = ins.get(lc).split(" ");
			
			if(datos.length != 2){
				throw new IllegalArgumentException("El número de argumentos que describe el EndPoint no es correcto(2): " + datos.length);
			}
			
			EndPoint endP = new EndPointImpl(i + ", " + datos[0]);
			connectedCachesAux = new Integer(datos[1].trim());
			
			for(int j = 0; j < connectedCachesAux; j++){
				lc++;
				aux = ins.get(lc).split(" ");
				
				if(aux.length != 2){
					throw new IllegalArgumentException("El número de argumentos que describe el cacheServer del EndPoint no es correcto(2): " + aux.length);
				}
				
				endP.addServerCache(new Integer(aux[0].trim()), new Integer(aux[1].trim()));
				
			}
			
			//if(connectedCachesAux != endP.getCacheServerList().size()) Optional, takes time
			
			listEndPoints.add(endP);
			
		}
				
		//#Lastly we read the RequestDescriptions

		for(int i = 0; i < numReqD; i++){
			lc++;
			datos = ins.get(lc).split(" ");
			
			if(datos.length != 3){
				throw new IllegalArgumentException("El número de argumentos que describe la RequestDescription no es correcto(3): " + datos.length);
			}
			
			RequestDescription reqD = new RequestDescriptionImpl(new Integer(datos[2].trim()), new Integer(datos[0].trim()));
			
			Integer idEndP = new Integer(datos[1].trim());
			
			if(endPointsToRequests.containsKey(idEndP)){
				endPointsToRequests.get(idEndP).add(reqD);
			}
			else{
				List<RequestDescription> listReqD = new ArrayList<>();
				listReqD.add(reqD);
				endPointsToRequests.put(idEndP, listReqD);
			}
			
		}
		
		//We check that this is the end of the File
		System.out.println("Is this the end of the File?: " + (ins.size() - lc == 1));
	
	}
	
	private static Set<EndPoint> getEndPointsToCache(Integer cacheServer){
		
		return listEndPoints.stream().filter(e-> e.getCacheServerList().contains(cacheServer)).collect(Collectors.toSet());
	}

}
