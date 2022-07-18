package com.unip.algoritmos_ordenacao.controllers;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import com.unip.algoritmos_ordenacao.util.TaskGerarCoordenadas;
import com.unip.algoritmos_ordenacao.util.UrlFxml;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressIndicator;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class SelecaoDadosController implements Initializable{

	@FXML
	private AnchorPane rootPane;
	@FXML
	private Stage stage;
	@FXML
	private ProgressIndicator prgIndicator;
	@FXML
	private Label lblCarregando;
	
	private TaskGerarCoordenadas task = new TaskGerarCoordenadas();
	
	private int qtndDados = 0;
	
	public SelecaoDadosController() {
		stage = new Stage();
		FXMLLoader fxmlLoader = new FXMLLoader(this.getClass().getResource(UrlFxml.VIEW_SELECAO_DADOS));
		fxmlLoader.setController(this);
		try {
			Scene scene = new Scene(fxmlLoader.load());
			stage.setScene(scene);
	        stage.setTitle("Selecionar quantidade de dados");
	        stage.show();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		
		prgIndicator.progressProperty().bind(task.progressProperty());
		
		task.setOnRunning(r ->{
			prgIndicator.setVisible(true);
			lblCarregando.setVisible(true);
		});
		task.setOnSucceeded(s ->{
			prgIndicator.setVisible(false);
			lblCarregando.setVisible(false);
			new OrdenarDadosController(qtndDados, task.getValue());
			stage.close();
		});
		task.setOnCancelled(s ->{
			prgIndicator.setVisible(false);
			lblCarregando.setVisible(false);
		});
		task.setOnFailed(s ->{
			prgIndicator.setVisible(false);
			lblCarregando.setVisible(false);
		});
	}

	@FXML
	private void gerarMil() {
		qtndDados = 1_000;
		gerarDados(qtndDados);
	}
	
	@FXML
	private void gerarDezMil() {
		qtndDados = 10_000;		
		gerarDados(qtndDados);
	}
	
    @FXML
    private void gerarCemMil() {
    	qtndDados = 100_000;
    	gerarDados(qtndDados);
    }

    @FXML
    private void gerarUmMilhao() {
    	qtndDados = 1_000_000;
    	gerarDados(qtndDados);
    }
	
    private void gerarDados(int qtdeDados) {
    	task.restart(qtdeDados);
    }
    
}
