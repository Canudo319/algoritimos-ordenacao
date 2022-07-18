package com.unip.algoritmos_ordenacao.util;

public enum CoordenadaOrdenacao {
	LATITUDE("LATITUDE"),
	LONGITUDE("LONGITUDE");
	
	private final String value;
	
	private CoordenadaOrdenacao(String value) {
		this.value = value;
	}

	public String getValue() {
		return value;
	}
	
}
