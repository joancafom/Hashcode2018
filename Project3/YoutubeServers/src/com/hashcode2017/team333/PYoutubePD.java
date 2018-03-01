package com.hashcode2017.team333;

import java.util.List;

import us.lsi.pd.AlgoritmoPD.Sp;
import us.lsi.pd.ProblemaPD;

/* 
 * Basically, nodes are videos and edges are the caches where to save
 * them. If the list indication where to put the videos is empty, that is
 * because that video is not cached, but placed in the data center
 */

public class PYoutubePD implements ProblemaPD<List<Integer>, List<Integer>>{

	private int index;
	private Integer savedTime;
	
	@Override
	public us.lsi.pd.ProblemaPD.Tipo getTipo() {
		
		return Tipo.Max;
	}

	@Override
	public int size() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public boolean esCasoBase() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Sp<List<Integer>> getSolucionCasoBase() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Sp<List<Integer>> seleccionaAlternativa(List<Sp<List<Integer>>> ls) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ProblemaPD<List<Integer>, List<Integer>> getSubProblema(List<Integer> a, int np) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Sp<List<Integer>> combinaSolucionesParciales(List<Integer> a, List<Sp<List<Integer>>> ls) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<List<Integer>> getAlternativas() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int getNumeroSubProblemas(List<Integer> a) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public List<Integer> getSolucionReconstruida(Sp<List<Integer>> sp) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Integer> getSolucionReconstruida(Sp<List<Integer>> sp, List<List<Integer>> ls) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Double getObjetivoEstimado(List<Integer> a) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Double getObjetivo() {
		// TODO Auto-generated method stub
		return null;
	}

}
