/**
 * 
 *	@author Valens Iraguha 
 *	E-mail : valens.iraguha@etu.univ-paris-diderot.fr
 *	
 *	Méthodes algorithmiques pour l'accès à l'information numérique
 *	TP N°1 : Programmer PageRank sur les graphes du Web 
 *	
 */


public class PageRank {

	public static void main(String[] args) { 
		if(args.length == 1) { 
			if(!args[0].endsWith("txt")) { 
				System.out.println(); 
				System.out.println("Le bon format pour exécuter mon code : java PageRank [graphe: *.txt]"); 
				System.out.println(); 
				System.exit(0);
			} 
			else { 
				String file = args[0]; 
				Graph graph = Graph.fileLoading(file);
				
				new PageRank(graph);
			}
		}
		else { 
			System.out.println(); 
			System.out.println("Le bon format pour exécuter mon code : java PageRank [graphe: *.txt]"); 
			System.out.println(); 
			System.exit(0);
		}
	}
	
	PageRank(Graph g) { 
		System.out.println("Nodes : " + Graph.getSetOfNodes().size() + " Edges : " + Graph.getSetOfEdges().size()); 
		int n = Graph.getSetOfNodes().size(); 
		int m = 0; 
		double[][] M = new double[n][n]; 
		Node[] nodes = Graph.getNodes();//new Node[n]; 
		
		for(int i = 0; i < n; i++) { 
			nodes[i].setOutDegree();
			int outDegree = nodes[i].getOutDegree();
			for(int j = 0; j < n; j++) { 
				if(nodes[i].getChildren().containsValue(nodes[j])) { 
					M[i][j] = 1.0/outDegree; 
					m++;
				} 
				else { 
					M[i][j] = 0;
				}
			}
		} 
		
		double[] C = new double[m]; 
		int[] L = new int[n+1]; 
		int[] I = new int[m];
		
		int k = 0; 
		//int t = 0; 
		boolean control = false;
		for(int i = 0; i < n; i++) { 
			int o = 0; 
			int p = k; 
			for(int j = 0; j < n; j++) { 
				if(M[i][j] != 0.0) { 
					C[k] = M[i][j]; 
					I[k] = j; 
					k++; 
				} 
				else { 
					o++;
				}
			} 
			if(control) { 
				L[i-1] = p;
				L[i] = p; 
				
				if(o == n) { 
					control = true;
				} 
				else { 
					control = false;
				}
			} 
			else { 
				L[i] = p; 
				if(o == n) { 
					control = true;
				}
			}
			
			o = 0; 
		} 
		L[n] = m; 
		
		double[] r = this.compute(C, L, I); 
		
		double p = this.proba(r); 
		
		System.out.println(p);
	}
	
	public double[] compute(double[] C, int[] L, int[] I) { 
		int n = Graph.getSetOfNodes().size(); 
		
		double[] x = new double[n];
		for (int i = 0; i < n; i++) { 
			if(i == 0) { 
				x[i] = 1.0;
			} 
			else { 
				x[i] = 0.0;
			}
		} 
		
		double[] y = new double[n]; 
		for (int i = 0; i < n; i++) { 
			y[i] = 0.0; 
		} 
		
		for (int i = 0; i < n; i++) {
			for (int j = L[i]; j < L[i+1]; j++) {
				y[I[j]] += C[j] * x[i];
			}
		} 
		
		for (int i = 0; i < n; i++) { 
			x[i] = y[i]; 
		} 
		
		return x;
	} 
	
	public double proba(double[] r) { 
		int n = Graph.getSetOfNodes().size(); 
		
		double count = 0;
		for (int i = 0; i < n; i++) { 
			count = count + r[i]*r[i]; 
		} 
		
		double p = Math.sqrt(count); 
		
		return p;
	}

}
