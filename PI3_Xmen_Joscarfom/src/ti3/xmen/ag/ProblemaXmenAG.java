package ti3.xmen.ag;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import ti3.xmen.Mutante;
import ti3.xmen.ProblemaXmen;
import us.lsi.ag.ProblemaAGIndex;
import us.lsi.ag.agchromosomes.IndexChromosome;

// TODO

public class ProblemaXmenAG implements ProblemaAGIndex<List<Mutante>> {

	// Lista que contiene [xmen, mutantes]
	public static List<Mutante> listAll;

	// Lista que contiene a los xmen, apareciendo en ella
	// el número de veces que numBatallas() indica
	public static List<Mutante> listXmenExtended;

	public ProblemaXmenAG() {

		listAll = new ArrayList<>();

		listXmenExtended = new ArrayList<>(ProblemaXmen.listaXmen);

		Integer multiplicity;

		for (Mutante m : ProblemaXmen.listaXmen) {
			multiplicity = m.getNumBatallas();

			while (multiplicity > 1) {
				listXmenExtended.add(m);
				multiplicity--;
			}
		}

		listAll.addAll(listXmenExtended);
		listAll.addAll(ProblemaXmen.listaMutantes);

	}

	public Integer getObjectsNumber() {

		return listAll.size();
	}

	public Double fitnessFunction(IndexChromosome cr) {

		List<Mutante> lm = cr.decode().stream().map(x -> listAll.get(x)).collect(Collectors.toList());

		
		// Definimos ahora dos listas, que representarán a los xmen y mutantes
		// del Chromosoma actual

		List<Mutante> currentXmen = new ArrayList<>(lm);
		currentXmen.removeAll(ProblemaXmen.listaMutantes);

		List<Mutante> currentMutants = new ArrayList<>(lm);
		currentMutants.removeAll(listXmenExtended);

		Double fitness = 0.0;

		// Función Objetivo: El total de Batallas Ganadas sea máximo
		//max: Sum from i,j = 1 to #mutants of v(i,j) [whether won or not]
		Double fo = 0.0;

		for (int i = 0; i < currentMutants.size(); i++) {

			fo += getResultadoPelea(i, i + currentXmen.size(), lm);
		}

		// Restricción 1: Los mutantes deben luchar 1 y sólo 1 vez
		// # of mutants in this chromosome = # total mutants (all of 'em must appear)
		Integer fr1;
		Integer r1;

		r1 = currentMutants.size() - ProblemaXmen.listaMutantes.size();

		fr1 = r1 * r1;

		// Restricción 2: Ya que los mutantes luchan 1 y sólo 1 vez, debe
		// existir por tanto el mismo número de Xmen
		// # of xmen in this chromosome = # total xmen
		Integer fr2;
		Integer r2;

		r2 = currentXmen.size() - ProblemaXmen.listaMutantes.size();

		fr2 = r2 * r2;

		// Restricción 3: Las primeras n posiciones estarán ocupadas por Xmen
		// Sum from k = 1 to #currentXmen (of this chromosome) of esMutante_k (1 if it's mutant) = 0 
		Integer fr3;
		Integer r3 = 0;

		for (int i = 0; i < currentXmen.size(); i++) {
			r3 += esMutante(lm.get(i));
		}
		
		fr3 = r3 * r3;
		
		//Restricción 4: Los líderes sólo luchan con los líderes, y los no líderes con los no líderes
		// Sum from i = 1, j = 1 + #currentXmen to #currentMutants of Xij (1 if they are from different categories) = 0
		Integer fr4;
		Integer r4 = 0;
		
		for(int i = 0; i < currentMutants.size(); i++){
			
			if(lm.get(i).isLider() != lm.get(i + currentXmen.size()).isLider()){
				r4 += 1;
			}
		}
		
		fr4 = r4*r4;
		
		//Restricción 5: Batallas Compatibles
		//Sum from i = 1 to #currentMutants of batallaNoCompatible (1 if it's not compatible) = 0
		
		Integer fr5;
		Integer r5 = 0;
		
		for(int i = 0; i < currentMutants.size(); i++){
			r5 += batallaNoCompatible(lm.get(i).getHielo(),lm.get(i + currentXmen.size()).getFuego());
			r5 += batallaNoCompatible(lm.get(i).getFuego(),lm.get(i + currentXmen.size()).getHielo());
			r5 += batallaNoCompatible(lm.get(i).getFuerza(),lm.get(i + currentXmen.size()).getMateria());
			r5 += batallaNoCompatible(lm.get(i).getMateria(),lm.get(i + currentXmen.size()).getFuerza());
		}
		
		fr5 = r5*r5;

		
		//As fri is a restriction we, in some way, 'punish' the algorithm when something wrong happens
		//fri are all positive as after they're multiplied by a negative number
		//The algorithm tries to reduce these effects
		fitness = fo - 10 * (fr1 + fr2 + fr3 + fr4 + fr5);
		
		return fitness;
	}

	public List<Mutante> getSolucion(IndexChromosome cr) {

		return cr.decode().stream().map(x -> listAll.get(x)).collect(Collectors.toList());
	}

	// Métodos auxiliares

	private static Integer getResultadoPelea(Integer i, Integer j, List<Mutante> lm) {

		Integer res;
		
		if(lm.get(i).getSumaCaracteristicas() == lm.get(j).getSumaCaracteristicas()){
			res = 0;
		}
		else{
			res = lm.get(i).getSumaCaracteristicas() > lm.get(j).getSumaCaracteristicas() ? 1 : -1;
		}
		
		return res;
	}

	private static Integer esMutante(Mutante m) {

		return ProblemaXmen.listaMutantes.contains(m) ? 1 : 0;
	}
	
	private static Integer batallaNoCompatible(Integer cantidadPropX, Integer cantidadPropM) {

		return esTipo(cantidadPropX) && esTipo(cantidadPropM) ? 1 : 0;
	}
	
	private static Boolean esTipo(Integer c) {

		return c >= 4 ? true : false;
	}

}
