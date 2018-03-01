package ti3.xmen.pl;

import ti3.xmen.ProblemaXmen;
import us.lsi.algoritmos.Algoritmos;
import us.lsi.pl.AlgoritmoPLI;

public class TestXmenPLI {

	public static void main(String[] args) {
		ProblemaXmen.create("Mutantes.txt");
		System.out.println("------");
		System.out.println("X-Men: " + ProblemaXmen.listaXmen);
		System.out.println("------");
		System.out.println("Mutantes: " + ProblemaXmen.listaMutantes);
		System.out.println("------");

		String r = XmenPLI.getConstraints();
		System.out.println("Especificación LPSolve:");
		System.out.println(r);
		System.out.println("------\n");
		
		AlgoritmoPLI a = Algoritmos.createPLI("Mutantes.txt");
		a.setConstraints(r);
		a.ejecuta();
		
		System.out.println("Objetivo Solución = " + a.getObjetivo());
		System.out.println("Emparejamientos: \n");
		
		int nV = a.getNumVar();
		
		for (int pos = 0; pos < nV; pos++){
			
			Double x = a.getSolucion()[pos];
			
			if (x == 1.0) {
				System.out.println("Mutante " + ProblemaXmen.listaMutantes.get(new Integer(a.getName(pos).substring(3, 4))) + " VS X-Men " 
				+ ProblemaXmen.listaXmen.get(new Integer(a.getName(pos).substring(1, 2))));
			}

		}
	}
}
