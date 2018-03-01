package ti3.xmen.pl;

import ti3.xmen.ProblemaXmen;
import us.lsi.pl.AlgoritmoPLI;

public class XmenPLI {

	// TODO

	public static String getConstraints() {
		String s = "max: ";
		Integer numXmen = ProblemaXmen.listaXmen.size();
		Integer numMut = ProblemaXmen.listaMutantes.size();

		Boolean first = true;

		// Funcion Objetivo

		for (int i = 0; i < numXmen; i++) {

			for (int j = 0; j < numMut; j++) {

				if (first){
					first = false;
					s += getResultadoPeleaPrimera(i, j);
				}
				else{
					s += getResultadoPelea(i, j);
				}

				s += AlgoritmoPLI.getVariable("x", i, j);

			}
		}

		s += ";\n\n";

		// Restriccion: "Los mutantes enemigos solo pelean una vez cada uno"

		first = true;

		for (int j = 0; j < numMut; j++) {
			for (int i = 0; i < numXmen; i++) {
				if (first)
					first = false;
				else
					s += " + ";

				s += AlgoritmoPLI.getVariable("x", i, j);
			}

			s += " - 1 = 0";
			s += ";\n";
			first = true;
		}

		s += "\n\n";

		// Restriccion: "Los XMen solo pueden luchar un numero determinado de
		// veces, indicado por numBatalla"

		first = true;

		for (int i = 0; i < numXmen; i++) {
			for (int j = 0; j < numMut; j++) {
				if (first)
					first = false;
				else
					s += " + ";

				s += AlgoritmoPLI.getVariable("x", i, j);
			}

			s += " - " + ProblemaXmen.listaXmen.get(i).getNumBatallas() + " <= 0";
			s += ";\n";
			first = true;
		}

		s += "\n";

		// Restriccion: "Los líderes sólo pueden luchar con líderes y los no
		// líderes con no líderes"

		first = true;

		for (int i = 0; i < numXmen; i++) {

			for (int j = 0; j < numMut; j++) {

				if (first)
					first = false;
				else
					s += " + ";

				s += getIgualdadCategorias(i, j) + AlgoritmoPLI.getVariable("x", i, j);

			}
		}

		s += " = 0;\n\n";

		// Comprobar Compatibilidad Tipos Mutantes

		first = true;

		for (int i = 0; i < numXmen; i++) {

			for (int j = 0; j < numMut; j++) {

				if (first)
					first = false;
				else
					s += " + ";

				s += batallaNoCompatible(ProblemaXmen.listaXmen.get(i).getHielo(),
						ProblemaXmen.listaMutantes.get(j).getFuego()) + AlgoritmoPLI.getVariable("x", i, j);
			}

		}
		s += " = 0";

		s += ";\n";
		first = true;

		for (int i = 0; i < numXmen; i++) {

			for (int j = 0; j < numMut; j++) {

				if (first)
					first = false;
				else
					s += " + ";

				s += batallaNoCompatible(ProblemaXmen.listaXmen.get(i).getFuego(),
						ProblemaXmen.listaMutantes.get(j).getHielo()) + AlgoritmoPLI.getVariable("x", i, j);
			}

		}

		s += " = 0";

		s += ";\n";
		first = true;

		for (int i = 0; i < numXmen; i++) {

			for (int j = 0; j < numMut; j++) {

				if (first)
					first = false;
				else
					s += " + ";

				s += batallaNoCompatible(ProblemaXmen.listaXmen.get(i).getFuerza(),
						ProblemaXmen.listaMutantes.get(j).getMateria()) + AlgoritmoPLI.getVariable("x", i, j);
			}

		}

		s += " = 0";

		s += ";\n";
		first = true;

		for (int i = 0; i < numXmen; i++) {

			for (int j = 0; j < numMut; j++) {

				if (first)
					first = false;
				else
					s += " + ";

				s += batallaNoCompatible(ProblemaXmen.listaXmen.get(i).getMateria(),
						ProblemaXmen.listaMutantes.get(j).getFuerza()) + AlgoritmoPLI.getVariable("x", i, j);
			}

		}

		s += " = 0";

		s += ";\n\n";
		first = true;

		s += "bin ";

		for (int i = 0; i < numXmen; i++) {

			for (int j = 0; j < numMut; j++) {

				if (first)
					first = false;
				else
					s += ",";

				s += AlgoritmoPLI.getVariable("x", i, j);

			}
		}

		s += ";";

		return s;
	}

	private static String getResultadoPeleaPrimera(Integer i, Integer j) {

		String res;

		if (ProblemaXmen.listaXmen.get(i).getSumaCaracteristicas() == ProblemaXmen.listaMutantes.get(j)
				.getSumaCaracteristicas()) {
			res = "0";
		} else {
			res = ProblemaXmen.listaXmen.get(i).getSumaCaracteristicas() > ProblemaXmen.listaMutantes.get(j)
					.getSumaCaracteristicas() ? "" : " - ";
		}

		return res;
	}

	private static String getResultadoPelea(Integer i, Integer j) {
		String res;

		if (ProblemaXmen.listaXmen.get(i).getSumaCaracteristicas() == ProblemaXmen.listaMutantes.get(j)
				.getSumaCaracteristicas()) {
			res = " + 0";
		} else {
			res = ProblemaXmen.listaXmen.get(i).getSumaCaracteristicas() > ProblemaXmen.listaMutantes.get(j)
					.getSumaCaracteristicas() ? " + " : " - ";
		}

		return res;
	}

	private static Integer getIgualdadCategorias(Integer i, Integer j) {

		Integer liderI = 0;
		Integer liderJ = 0;

		if (ProblemaXmen.listaXmen.get(i).isLider()) {
			liderI = 1;
		}

		if (ProblemaXmen.listaMutantes.get(j).isLider()) {
			liderJ = 1;
		}

		return Math.abs(liderI - liderJ);
	}

	private static Boolean esTipo(Integer c) {

		return c >= 4 ? true : false;
	}

	//Devuelve 1 si la batalla no es compatible, 0 si lo es.
	private static Integer batallaNoCompatible(Integer cantidadPropX, Integer cantidadPropM) {

		return esTipo(cantidadPropX) && esTipo(cantidadPropM) ? 1 : 0;
	}
}
