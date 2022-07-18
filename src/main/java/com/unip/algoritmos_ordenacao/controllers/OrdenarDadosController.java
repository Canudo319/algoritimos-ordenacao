package com.unip.algoritmos_ordenacao.controllers;

import java.io.IOException;
import java.net.URL;
import java.text.DecimalFormat;
import java.util.List;
import java.util.ResourceBundle;

import com.unip.algoritmos_ordenacao.util.Coordenada;
import com.unip.algoritmos_ordenacao.util.CoordenadaOrdenacao;
import com.unip.algoritmos_ordenacao.util.TaskOrdenarCoordenadas;
import com.unip.algoritmos_ordenacao.util.TipoOrdenacao;
import com.unip.algoritmos_ordenacao.util.UrlFxml;

import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressIndicator;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class OrdenarDadosController implements Initializable{

	@FXML
	private AnchorPane rootPane;
	@FXML
	private Stage stage;
	@FXML
    private ComboBox<CoordenadaOrdenacao> cmbTipoOrdenacao;
	@FXML
	private TableView<Coordenada> tblCoordenadas;
    @FXML
    private TableColumn<Coordenada, String> colLatitude, colLongitude;
    @FXML
    private Label lblTempo;
    @FXML
    private ProgressIndicator prgIndicator;
    
    private DecimalFormat df = new DecimalFormat("#0.000000");
    
    private TaskOrdenarCoordenadas task = new TaskOrdenarCoordenadas();
    
    private int qtdeDados;
    
    private long tempoInicial, tempoFinal;
    
    private List<Coordenada> coordenadas;
	
	public OrdenarDadosController(int qtdeDados, List<Coordenada> coordenadas) {
		this.qtdeDados = qtdeDados;
		this.coordenadas = coordenadas;
		stage = new Stage();
		FXMLLoader fxmlLoader = new FXMLLoader(this.getClass().getResource(UrlFxml.VIEW_ORDENACAO_DADOS));
		fxmlLoader.setController(this);
		try {
			Scene scene = new Scene(fxmlLoader.load());
			stage.setScene(scene);
	        stage.setTitle("Ordenação");
	        stage.show();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		
		cmbTipoOrdenacao.getItems().setAll(CoordenadaOrdenacao.values());
		cmbTipoOrdenacao.getSelectionModel().select(0);
		
		colLatitude.setCellValueFactory(callback ->{
			return new SimpleStringProperty(df.format(callback.getValue().latitude()));
		});
		colLongitude.setCellValueFactory(callback ->{
			return new SimpleStringProperty(df.format(callback.getValue().longitude()));
		});
		
		tblCoordenadas.setItems(FXCollections.observableList(coordenadas));
		
		task.setOnRunning(r ->{
			prgIndicator.setVisible(true);
		});
		task.setOnSucceeded(s ->{
			prgIndicator.setVisible(false);
			coordenadas = task.getValue();
			tblCoordenadas.setItems(FXCollections.observableList(coordenadas));
			tblCoordenadas.refresh();
			tempoFinal = System.currentTimeMillis();
			lblTempo.setText(String.format("Ordenou %d dados \nem %d ms usando %s SORT", qtdeDados,
					tempoFinal - tempoInicial, task.getTipoOrdenacao().getValue()));
		});
		task.setOnFailed(f ->{
			prgIndicator.setVisible(false);
			task.getException().printStackTrace();
			Alert alert = new Alert(AlertType.ERROR, task.getException().getMessage().trim());
			alert.showAndWait();
		});
		task.setOnCancelled(c ->{
			prgIndicator.setVisible(false);
		});
		
		prgIndicator.progressProperty().bind(task.progressProperty());
		
	}

	@FXML
	private void ordenarInsertion() {
		tempoInicial = System.currentTimeMillis();
		task.restart(coordenadas, cmbTipoOrdenacao.getSelectionModel().getSelectedItem(), TipoOrdenacao.INSERTION);
	}
	
	@FXML
	private void ordenarBubble() {
		tempoInicial = System.currentTimeMillis();
		task.restart(coordenadas, cmbTipoOrdenacao.getSelectionModel().getSelectedItem(), TipoOrdenacao.BUBBLE);
	}
	
	@FXML
	private void ordenarQuick() {
		tempoInicial = System.currentTimeMillis();
		task.restart(coordenadas, cmbTipoOrdenacao.getSelectionModel().getSelectedItem(), TipoOrdenacao.QUICK);
	}
	
	@FXML
	private void ordenarSelection() {
		tempoInicial = System.currentTimeMillis();
		task.restart(coordenadas, cmbTipoOrdenacao.getSelectionModel().getSelectedItem(), TipoOrdenacao.SELECTION);
	}
	
	@FXML
	private void voltar() {
		new SelecaoDadosController();
		stage.close();
	}
	
}
