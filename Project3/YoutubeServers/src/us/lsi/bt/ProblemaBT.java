package us.lsi.bt;



/**
 * <p>Interface que debe implementar un problema para ser resuelto por la t�cnica de Backtracking </p>
 * 
 * <p>La documentaci�n puede encontarse en el: <a href="../../../document/Tema15.pdf" target="_blank">Tema15</a></p>
 * 
 * @author Miguel Toro
 *
 * @param <S> Tipo de la Soluci�n
 * @param <A> Tipo de la Alternativa
 */
public interface ProblemaBT<S, A> {
	
	public enum Tipo{Min,Max,Otro}
	
	
	/**
	 * @return El tipo del problema: minimiza, maximiza o busca un valor que involucra a todas las alternativas
	 *
	 * 
	 **/
	Tipo getTipo();	
	/**
	 * @return El estado inicial
	 */
	EstadoBT<S,A> getEstadoInicial();	
}

