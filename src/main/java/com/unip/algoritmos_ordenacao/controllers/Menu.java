package com.unip.algoritmos_ordenacao.controllers;

import java.io.IOException;

import com.unip.algoritmos_ordenacao.util.UrlFxml;

import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 * JavaFX App
 */
public class Menu extends Application {

	@FXML
	private AnchorPane rootPane;
    private static Scene scene;
    
    @Override
    public void start(Stage stage) throws IOException {
    	FXMLLoader fxmlLoader = new FXMLLoader(this.getClass().getResource(UrlFxml.VIEW_MENU));
    	fxmlLoader.setController(this);
        scene = new Scene(fxmlLoader.load());
        stage.setScene(scene);
        stage.setTitle("Menu");
        stage.initStyle(StageStyle.UNDECORATED);
        stage.show();
    }
    
    @FXML
    private void start() {
    	Stage thisStage = (Stage) scene.getWindow();
    	thisStage.close();
    	new SelecaoDadosController();
    }
    
    @FXML
    private void goToIntegrantes() {
    	rootPane.setDisable(true);
    	new IntegrantesController();
    	rootPane.setDisable(false);
    }
    
    @FXML
    private void closeAplication() {
    	Stage thisStage = (Stage) scene.getWindow();
    	thisStage.close();
    }
    
    public static void main(String[] args) {
        launch();
    }

}