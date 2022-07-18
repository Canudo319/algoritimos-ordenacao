package com.unip.algoritmos_ordenacao.controllers;

import java.io.IOException;

import com.unip.algoritmos_ordenacao.util.UrlFxml;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class IntegrantesController{

	@FXML
	private AnchorPane rootPane;
	@FXML
	private Stage stage;
	
	public IntegrantesController() {
		stage = new Stage();
		FXMLLoader fxmlLoader = new FXMLLoader(this.getClass().getResource(UrlFxml.VIEW_INTEGRANTES));
		fxmlLoader.setController(this);
		try {
			Scene scene = new Scene(fxmlLoader.load());
			stage.setScene(scene);
	        stage.setTitle("Integrantes");
	        stage.show();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
