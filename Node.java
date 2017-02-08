/**
 * 
 *	@author Valens Iraguha 
 *	E-mail : valens.iraguha@etu.univ-paris-diderot.fr
 *	
 *	Méthodes algorithmiques pour l'accès à l'information numérique
 *	TP N°1 : Programmer PageRank sur les graphes du Web 
 *	
 */


import java.util.HashMap;
import java.util.Map.Entry;

public class Node { 
	private String name;
	private int number;
	private int degree; 
	Graph graph;
	
	public String getName() { 
		return name; 	
	} 
	
	public Node(int nodeNumber, String name, Graph graph) { 
		this.number = nodeNumber;
		this.name = name; 
		this.graph = graph; 
		Graph.setSetOfNodes(name, this); 	
	} 
	
	public Node getNode(String name, Graph graph) { 
		return Graph.getSetOfNodes().get(name); 	
	} 
	
	public void setNumber(int number) { 
		this.number = number;
	}
	
	public int getNumber() { 
		return this.number;
	}
	
	public void setDegree() { 
		this.degree = this.getNeighbors().size(); 
	}
	
	public void setDegree(int degree) { 
		this.degree = degree; 
	}
	
	public int getDegree() { 
		return this.degree;
	}
	
	public HashMap<Integer, Node> getParents() { 
		HashMap<Integer, Node> parents = new HashMap<Integer, Node>(); 
		
		for(Entry<Integer, Edge> entry : Graph.getSetOfEdges().entrySet()) {
			Integer key = entry.getKey();
			Edge value = entry.getValue();
			if(value.getChild().getName().equals(this.getName())) { 
				parents.put(key, value.getParent()); 	
			}
		} 
		return parents; 	
	}
	
	public HashMap<Integer, Node> getChildren() { 
		HashMap<Integer, Node> children = new HashMap<Integer, Node>(); 
		
		for(Entry<Integer, Edge> entry : Graph.getSetOfEdges().entrySet()) { 
			Integer key = entry.getKey();
			Edge value = entry.getValue();
			if(value.getParent().getName().equals(this.getName())) { 
				children.put(key, value.getChild()); 	
			}
		} 
		return children; 
	} 
	
	public HashMap<Integer, Node> getNeighbors() { 
		HashMap<Integer, Node> neighbors = new HashMap<Integer, Node>(); 
		
		for(Entry<Integer, Edge> entry : Graph.getSetOfEdges().entrySet()) { 
			Integer key = entry.getKey();
			Edge value = entry.getValue();
			if(value.getParent().getName().equals(this.getName())) { 
				neighbors.put(key, value.getChild()); 	
			}
		} 
		
		for(Entry<Integer, Edge> entry : Graph.getSetOfEdges().entrySet()) { 
			Integer key = entry.getKey();
			Edge value = entry.getValue();
			if(value.getChild().getName().equals(this.getName())) { 
				neighbors.put(key, value.getParent()); 	
			}
		}
		return neighbors; 
	} 
	
	public String toString() { 
		return new String("   Noeud: "+ this.getName()); 	
	}
	
}
