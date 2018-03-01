package ti3.xmen.ag;

import java.util.List;
import java.util.stream.IntStream;

import ti3.xmen.Mutante;
import ti3.xmen.ProblemaXmen;
import us.lsi.ag.AlgoritmoAG;
import us.lsi.ag.agchromosomes.ChromosomeFactory;
import us.lsi.ag.agchromosomes.ChromosomeFactory.ChromosomeType;
import us.lsi.ag.agchromosomes.IndexChromosome;
import us.lsi.ag.agstopping.StoppingConditionFactory;
import us.lsi.ag.agstopping.StoppingConditionFactory.StoppingConditionType;
import us.lsi.algoritmos.Algoritmos;

public class TestXmenAG {

	public static void main(String[] args){
		
		AlgoritmoAG.ELITISM_RATE  = 0.30;
		AlgoritmoAG.CROSSOVER_RATE = 0.8;
		AlgoritmoAG.MUTATION_RATE = 0.7;
		AlgoritmoAG.POPULATION_SIZE = 200;
		StoppingConditionFactory.NUM_GENERATIONS = 500;
		StoppingConditionFactory.SOLUTIONS_NUMBER_MIN = 10;
		StoppingConditionFactory.FITNESS_MIN = 100.0;
		StoppingConditionFactory.stoppingConditionType = StoppingConditionType.GenerationCount;

		ProblemaXmen.create("Mutantes.txt");
		System.out.println("------");
		System.out.println("Listado X-Men: " + ProblemaXmen.listaXmen);
		System.out.println("------");
		System.out.println("Listado Mutantes: " + ProblemaXmen.listaMutantes);
		System.out.println("------");
		System.out.println("Solución:");
		ProblemaXmenAG p = new ProblemaXmenAG();
		
		AlgoritmoAG ap = Algoritmos.createAG(ChromosomeType.IndexPermutationSubList, p);
		ap.ejecuta();
		IndexChromosome cr = ChromosomeFactory.asIndex(ap.getBestFinal());
		List<Mutante> solucion = p.getSolucion(cr);
		
		Integer mitad = solucion.size() / 2;
		
		IntStream.range(0, mitad).boxed().forEach(x-> System.out.println("XMen: " + solucion.get(x) +  " vs Mutante: " + solucion.get(x+mitad)));
		
		System.out.println("Fitness de la mejor solución: " + ap.getBestFinal().fitness());
		System.out.println("------");
	}	

}
