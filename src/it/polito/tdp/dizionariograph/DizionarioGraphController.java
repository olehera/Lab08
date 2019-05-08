package it.polito.tdp.dizionariograph;

import java.net.URL;
import java.util.ResourceBundle;

import it.polito.tdp.dizionariograph.model.Model;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class DizionarioGraphController {

	private Model model;
	private int num;
	
	public void setModel(Model model) {
		this.model = model;
		num = 0;
	}
	
    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TextField txtNum;

    @FXML
    private TextField txtParola;

    @FXML
    private Button btnGenera;

    @FXML
    private Button btnVicini;

    @FXML
    private Button btnGrado;

    @FXML
    private TextArea txtResult;

    @FXML
    private Button btnReset;

    @FXML
    void doGeneraGrafo(ActionEvent event) {
    	try {
    		num = Integer.parseInt(txtNum.getText().trim());
    	} catch(NumberFormatException nfe) {
    		txtResult.setText("Devi inserire un numero intero!\n");
    		return ;
    	}
    	
    	model.createGraph(num);
    	txtResult.setText("Creato Grafo con "+model.getVertexSize()+" Vertici e "+model.getEdgeSize()+" Archi\n");
    	txtNum.setDisable(true);
    }

    @FXML
    void doTrovaVicini(ActionEvent event) {
    	String parola = txtParola.getText().trim();
    	
    	if (parola.length() != num) {
    		txtResult.setText("Devi inserire una parola della stessa lunghezza specificata");
    		return ;
    	}
    	
    	txtResult.clear();
    	for (String s : model.displayNeighbours(parola))
    		txtResult.appendText(s+"\n");
    }
    
	@FXML
    void doTrovaGradoMax(ActionEvent event) {
		txtResult.setText("Grado: "+model.findMaxDegree()+"\n");
		
		String vertice = model.getVerticeMax();
		txtResult.appendText("Vertice: "+vertice+"\n\n");
		
		for (String s : model.displayNeighbours(vertice))
    		txtResult.appendText(s+"\n");
    }
	
	@FXML
    void doReset(ActionEvent event) {
		txtResult.clear();
		txtNum.clear();
		txtParola.clear();
		txtNum.setDisable(false);
		this.model = new Model();
    }

    @FXML
    void initialize() {
        assert txtNum != null : "fx:id=\"txtNum\" was not injected: check your FXML file 'DizionarioGraph.fxml'.";
        assert txtParola != null : "fx:id=\"txtParola\" was not injected: check your FXML file 'DizionarioGraph.fxml'.";
        assert btnGenera != null : "fx:id=\"btnGenera\" was not injected: check your FXML file 'DizionarioGraph.fxml'.";
        assert btnVicini != null : "fx:id=\"btnVicini\" was not injected: check your FXML file 'DizionarioGraph.fxml'.";
        assert btnGrado != null : "fx:id=\"btnGrado\" was not injected: check your FXML file 'DizionarioGraph.fxml'.";
        assert txtResult != null : "fx:id=\"txtResult\" was not injected: check your FXML file 'DizionarioGraph.fxml'.";
        assert btnReset != null : "fx:id=\"btnReset\" was not injected: check your FXML file 'DizionarioGraph.fxml'.";
    }
}