package hashcode.test;

import hashcode.drives.ProblemaHashcode;
import hashcode.drives.Ride;

public class RidesTest {

	public static void main(String[] args) {
		ProblemaHashcode problem = new ProblemaHashcode("a_example.in");
		System.out.println("Numero de Rows: "+problem.numRows);
		System.out.println("numero de Cols: "+problem.numCols);
		System.out.println("Numero de Vehiculos: "+problem.numVehicles);
		System.out.println("Bonus: "+problem.bonus);
		System.out.println("Numero de Steps: "+problem.steps);
		System.out.println("Rides:");
		int index =1;
		for(Ride r:problem.rides){
			System.out.println("Ride Number "+index);
			System.out.println("Ride from ["+r.getStartRow()+","+r.getStartColumn()+"]");
			System.out.println("Ride to ["+r.getEndRow()+","+r.getEndColumn()+"]");
			System.out.println("Earliest start: "+r.getEarliestStart());
			System.out.println("Latest finish: "+r.getLatestFinish());
			System.out.println("Steps: "+r.getSteps());
			index++;
		}
	}

}
