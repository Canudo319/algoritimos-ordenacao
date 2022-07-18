package com.unip.algoritmos_ordenacao.util;

public enum TipoOrdenacao {
	INSERTION("INSERTION"),
	BUBBLE("BUBBLE"),
	QUICK("QUICK"),
	SELECTION("SELECTION");
	
	private final String value;
	
	private TipoOrdenacao(String value) {
		this.value = value;
	}

	public String getValue() {
		return value;
	}
	
}
