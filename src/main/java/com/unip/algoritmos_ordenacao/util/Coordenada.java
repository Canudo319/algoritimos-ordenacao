package com.unip.algoritmos_ordenacao.util;

public record Coordenada(double latitude, double longitude) {
	
	public double getCoordenada(CoordenadaOrdenacao orderby) {
		return switch(orderby) {
		case LATITUDE -> this.latitude;
		case LONGITUDE -> this.longitude;
		};
	}
	
}
