/**
 * 
 *	@author Valens Iraguha 
 *	E-mail : valens.iraguha@etu.univ-paris-diderot.fr
 *	
 *	Méthodes algorithmiques pour l'accès à l'information numérique
 *	TP N°1 : Programmer PageRank sur les graphes du Web 
 *	
 */


public class Edge { 
	private int number; 
	private Node parent; 
	private Node child; 

	public Edge(int number, Node parent, Node child, Graph graph) { 
		this.number = number;
		this.parent = parent;
		this.child = child;
		Graph.setSetOfEdges(number, this); 	
	}
	
	public void setNumber(int number) { 
		this.number = number;
	}
	
	public int getNumber() { 
		return this.number;
	}
	
	public Node getParent() { 
		return parent;
	}

	public Node getChild() {
		return child;
	}
	
	public String toString() { 
		return new String(this.getParent().getName() + " -> " + this.getChild().getName());
   }

}
