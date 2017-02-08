/**
 * 
 *	@author Valens Iraguha 
 *	E-mail : valens.iraguha@etu.univ-paris-diderot.fr
 *	
 *	Méthodes algorithmiques pour l'accès à l'information numérique
 *	TP N°1 : Programmer PageRank sur les graphes du Web 
 *	
 */


import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map.Entry;

public class Graph { 
	private static String name;
	private static String type; 
	
	private static HashMap<String, Node> setOfNodes; 
	private static HashMap<Integer, Edge> setOfEdges; 
	private static ArrayList<String> setOfOrphan; 
	
	private int nodeNumber; 
	private int edgeNumber;
	
	public Graph() { 
		setOfNodes = new HashMap<String, Node> ();
		setOfEdges = new HashMap<Integer, Edge> (); 	
	} 
	
	public static Graph fileLoading(String filename) { 
		
		Graph graph = new Graph();
		
		new FileParsing(graph, filename);
		
		return graph;
	} 
	
	public void addElement(String parent, String child) { 
		if(!setOfNodes.containsKey(parent)) { 
			nodeNumber++;
			Node np = new Node(nodeNumber, parent, this); 
			
			if(!setOfNodes.containsKey(child)) {
				nodeNumber++;
				Node nc = new Node(nodeNumber, child, this); 
				  
				edgeNumber++;
				new Edge(edgeNumber, np.getNode(parent, this), nc.getNode(child, this), this);
			}
			else { 
				edgeNumber++;
				new Edge(edgeNumber, np.getNode(parent, this), setOfNodes.get(child), this);
			}
		}
		else { 
			if(!setOfNodes.containsKey(child)) {
				nodeNumber++;
				Node nc = new Node(nodeNumber, child, this); 
				
				edgeNumber++;
				new Edge(edgeNumber, setOfNodes.get(parent), nc.getNode(child, this), this);
			}
			else { 
				edgeNumber++;
				new Edge(edgeNumber, setOfNodes.get(parent), setOfNodes.get(child), this);
			}
		}
	}
	
	public void addOrphan(String child) { 
		setOfOrphan = new ArrayList<String>();
		setOfOrphan.add(child);
	}
	
	public int getSize() { 
		return setOfNodes.size();
	}
	
	public static void setName(String graphName) { 
		name = graphName;
	}
	
	public static String getName() { 
		return name;
	}
	
	public static void setType(String graphType) { 
		type = graphType;
	}
	
	public static String getType() { 
		return type;
	}
	
	public static void setSetOfNodes(String name, Node node) { 
		setOfNodes.put(name, node);
	}
	
	public static HashMap<String, Node> getSetOfNodes() { 
		return setOfNodes;
	} 
	
	public static Node[] getNodes() { 
		int taille = setOfNodes.size();
		Node nodes[] = new Node[taille]; 
		int i = 0;
		for(Entry<String, Node> entry : setOfNodes.entrySet()) {
			Node value = entry.getValue();
			nodes[i] = value; 
			i++;
		}
		return nodes; 	
	}
	
	public static void setSetOfEdges(Integer number, Edge edge) { 
		setOfEdges.put(number, edge);
	} 
	
	public static HashMap<Integer, Edge> getSetOfEdges() { 
		return setOfEdges;
	}
	
}
