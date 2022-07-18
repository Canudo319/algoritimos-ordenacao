package com.unip.algoritmos_ordenacao.util;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javafx.concurrent.Service;
import javafx.concurrent.Task;

public class TaskGerarCoordenadas extends Service<List<Coordenada>>{

	private int qtdeDeDados;
	
	public void restart(int qtdeDeDados) {
		this.qtdeDeDados = qtdeDeDados;
		super.restart();
	}
	
	@Override
	protected Task<List<Coordenada>> createTask() {
		return new Task<List<Coordenada>>() {
			@Override
			protected List<Coordenada> call() throws Exception {
				ArrayList<Coordenada> listaCoordenadas = new ArrayList<>();
				
				for(int i = 0 ; i < qtdeDeDados ; i++){
			        updateProgress(i, qtdeDeDados);
					listaCoordenadas.add(geraCoordenada());
			    }
				
				updateValue(listaCoordenadas);
				return listaCoordenadas;
			}
		};
	}
	
	private Coordenada geraCoordenada() {
		Random rnd = new Random();
		
		double precisao = rnd.nextDouble();
        precisao = BigDecimal.valueOf(precisao).setScale(6,RoundingMode.HALF_UP).doubleValue();
        double latitude = ((rnd.nextInt(7 - 3) + 3) + precisao)*-1;
        
        precisao = rnd.nextDouble();
        precisao = BigDecimal.valueOf(precisao).setScale(6,RoundingMode.HALF_UP).doubleValue();
        double longitude = ((rnd.nextInt(64 - 60) + 60) + precisao)*-1;
        
        return new Coordenada(latitude, longitude);
        
	}

}


