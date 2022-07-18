package com.unip.algoritmos_ordenacao.util;

import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.function.BiConsumer;

public class Ordenacoes {

	//Atributo nescessario para atualizar o Progressbar do GUI de maneira Dinâmica
	private BiConsumer<Integer, Integer> progressUpdate;
	
	public void setProgressUpdate(BiConsumer<Integer, Integer> progressUpdate) {
        this.progressUpdate = progressUpdate;
    }
	
	public List<Coordenada> InsertSort(List<Coordenada> listaDados, CoordenadaOrdenacao orderby){
        
        for(int out = 0 ; out < listaDados.size() ; out++){
        	Coordenada temporaria = listaDados.get(out);
            int in = out;
            
            while(in > 0 && listaDados.get(in - 1).getCoordenada(orderby) >= temporaria.getCoordenada(orderby)){
                listaDados.set(in , listaDados.get(in - 1));
                in--;
            }
            
            listaDados.set(in, temporaria);
            if (progressUpdate != null) {
                progressUpdate.accept(out, listaDados.size());
            }
        }
        
        return listaDados;
    }
	
	public List<Coordenada> bubbleSort(List<Coordenada> listaDados, CoordenadaOrdenacao orderby){
		
		for(int out = listaDados.size() - 1; out > 1; out--){
            for(int in = 0 ; in < out ; in++){
                if(listaDados.get(in).getCoordenada(orderby) > listaDados.get(in + 1).getCoordenada(orderby)){
                    Collections.swap(listaDados, in, in + 1);
                }
            }
            
            if (progressUpdate != null) {
                progressUpdate.accept(listaDados.size() - out, listaDados.size());
            }
        }
		
		return listaDados;
	}
	
	public List<Coordenada> quickSort(List<Coordenada> listaDados, CoordenadaOrdenacao orderby){
		return quickSort(listaDados, 0, listaDados.size() - 1, orderby);
	}
	
	private List<Coordenada> quickSort(List<Coordenada> listaDados, int ini, int fim, CoordenadaOrdenacao orderby){
		
		if(ini >= fim) {
			return listaDados;
		}
		
		int pivotIndex = new Random().nextInt(fim - ini) + ini;
		double pivot = listaDados.get(pivotIndex).getCoordenada(orderby);
		Collections.swap(listaDados, pivotIndex, fim);

		int ponteiroEsq = particionar(listaDados, ini, fim, orderby, pivot);
		
		Collections.swap(listaDados, ponteiroEsq, fim);
		quickSort(listaDados, ini, ponteiroEsq - 1, orderby);
		quickSort(listaDados, ponteiroEsq + 1, fim, orderby);
		
		return listaDados;
	}

	private int particionar(List<Coordenada> listaDados, int ini, int fim, CoordenadaOrdenacao orderby, double pivot) {
		int ponteiroEsq = ini;
		int ponteiroDir = fim;
		
		while(ponteiroEsq < ponteiroDir) {
			
			while(listaDados.get(ponteiroEsq).getCoordenada(orderby) <= pivot && ponteiroEsq < ponteiroDir) {
				ponteiroEsq++;
			}
			
			while(listaDados.get(ponteiroDir).getCoordenada(orderby) >= pivot && ponteiroEsq < ponteiroDir) {
				ponteiroDir--;
			}
			
			Collections.swap(listaDados, ponteiroEsq, ponteiroDir);
		}
		return ponteiroEsq;
	}
	
	public List<Coordenada> selectSort(List<Coordenada> listaDados, CoordenadaOrdenacao orderby){
        
        for(int out = 0 ; out < listaDados.size() ; out++){
            int min = out;
            for(int in = out + 1 ; in < listaDados.size() ; in++){
                if(listaDados.get(min).getCoordenada(orderby) > listaDados.get(in).getCoordenada(orderby)){
                    min = in;
                }
                Collections.swap(listaDados, out, min);
            }
            
            if (progressUpdate != null) {
                progressUpdate.accept(out, listaDados.size());
            }
        }
        return listaDados;
    }
	
}
