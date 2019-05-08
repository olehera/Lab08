package it.polito.tdp.dizionariograph.model;

import java.util.ArrayList;
import java.util.List;

import org.jgrapht.Graph;
import org.jgrapht.Graphs;
import org.jgrapht.graph.DefaultEdge;
import org.jgrapht.graph.SimpleGraph;

import it.polito.tdp.dizionariograph.db.WordDAO;

public class Model {
	
	private Graph<String, DefaultEdge> grafo;
	private List<String> vertici;
	private String verticeMax;

	public void createGraph(int numeroLettere) {
		
		grafo = new SimpleGraph<>(DefaultEdge.class);
		
		WordDAO dao = new WordDAO();
		vertici = new ArrayList<String>(dao.getAllWordsFixedLength(numeroLettere));
		Graphs.addAllVertices(grafo, vertici);
		
		for (String s1 : vertici)
			for (String s2 : vertici)
				if (!grafo.containsEdge(s1, s2) && confronta(s1, s2))
					grafo.addEdge(s1, s2);
	}

	public List<String> displayNeighbours(String parolaInserita) {
		List<String> vicini = new ArrayList<String>();
		
		for (String s : vertici)
			if (grafo.containsEdge(parolaInserita, s))
				vicini.add(s);
		
		return vicini;
	}

	public int findMaxDegree() {
		int max = 0;
		
		for (String s : vertici) {
			int grado = displayNeighbours(s).size();
			if (grado > max) {
				max = grado;
				verticeMax = s;
			}
		}
				
		return max;
	}
	
	public boolean confronta(String s1, String s2) {
		int conta = 0;
		
		for (int i=0; i<s1.length() ; i++) {
			if (s1.charAt(i)!=s2.charAt(i))
				conta++;
			if (conta > 1)
				break;
		}
		
		return (conta==1);
	}
	
	public int getVertexSize() {
		return grafo.vertexSet().size();
	}
	
	public int getEdgeSize() {
		return grafo.edgeSet().size();
	}

	public String getVerticeMax() {
		return verticeMax;
	}

}