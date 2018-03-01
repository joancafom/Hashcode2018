package com.hashcode2017.team333;

public class RequestDescriptionImpl implements RequestDescription {

	public Integer times;
	public Integer idVideo;

	public RequestDescriptionImpl(Integer t, Integer iv) {
		this.times = t;
		this.idVideo = iv;
	}

	public RequestDescriptionImpl(String cadena) {
		String[] datos = cadena.split(" ");

		if (datos.length != 2) {
			throw new IllegalArgumentException("El formato de la cadena para RequestDescription no es válido");
		}
		
		this.idVideo = new Integer(datos[0].trim());
		this.times = new Integer(datos[1].trim());
	}

	@Override
	public Integer getTimes() {
		// TODO Auto-generated method stub
		return times;
	}

	@Override
	public Integer getIdVideo() {
		// TODO Auto-generated method stub
		return idVideo;
	}
	
	public String toString(){
		return "(" + this.idVideo + "," + this.times + ")";
	}

}
