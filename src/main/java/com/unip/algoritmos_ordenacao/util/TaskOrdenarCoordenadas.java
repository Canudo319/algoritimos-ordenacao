package com.unip.algoritmos_ordenacao.util;

import java.util.List;

import javafx.concurrent.Service;
import javafx.concurrent.Task;

public class TaskOrdenarCoordenadas extends Service<List<Coordenada>>{
	
	private CoordenadaOrdenacao orderby;
	private TipoOrdenacao tipoOrdenacao;
	private List<Coordenada> listaDados;
	private Ordenacoes ordenacoes;
	
	public void restart(List<Coordenada> listaDados, CoordenadaOrdenacao orderby, TipoOrdenacao tipoOrdenacao) {
		this.listaDados = listaDados;
		this.orderby = orderby;
		this.tipoOrdenacao = tipoOrdenacao;
		ordenacoes = new Ordenacoes();
		super.restart();
	}
	
	@Override
	protected Task<List<Coordenada>> createTask() {
		return new Task<List<Coordenada>>() {
			@Override
			protected List<Coordenada> call() throws Exception {
				ordenacoes.setProgressUpdate((workDone, totalWork) ->
				updateProgress(workDone, totalWork));
				
				listaDados = switch (tipoOrdenacao) {
				case INSERTION -> ordenacoes.InsertSort(listaDados, orderby);
				case BUBBLE    -> ordenacoes.bubbleSort(listaDados, orderby);
				case QUICK     -> ordenacoes.quickSort(listaDados, orderby);
				case SELECTION -> ordenacoes.selectSort(listaDados, orderby);
				};
				
				updateValue(listaDados);
				return listaDados;
			};
		};
	}

	public TipoOrdenacao getTipoOrdenacao() {
		return tipoOrdenacao;
	}

}


