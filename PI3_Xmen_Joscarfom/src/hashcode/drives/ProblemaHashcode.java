package hashcode.drives;

import java.util.ArrayList;
import java.util.List;

import us.lsi.stream.Stream2;


public class ProblemaHashcode {
	
	public static List<Ride> rides;
	public Integer numRows;
	public Integer numCols;
	public Integer numVehicles;
	public Integer numRides;
	public Integer bonus;
	public Integer steps;
	
	
	public ProblemaHashcode(String file){
		super();
		leerRide(file);
	}

	private void leerRide(String file) {
		List<String> ls = Stream2.fromFile(file).toList();
		rides = new ArrayList<Ride>();
		boolean firstLine = true;
		int index = 0;
		for(String s : ls){
			if(firstLine){
				String[] at = Stream2.fromString(s, " ").toArray((int x)->new String[x]);
				numRows = new Integer(at[0]);
				numCols = new Integer(at[1]);
				numVehicles = new Integer(at[2]);
				numRides = new Integer(at[3]);
				bonus = new Integer(at[4]);
				steps = new Integer(at[5]);
				firstLine=false;
			}
			else{
				rides.add(new Ride(s, index));
				index++;
			}
		}
		
	}
	
	public static ProblemaHashcode create(String file) {		
		return new ProblemaHashcode(file);
	}

}
