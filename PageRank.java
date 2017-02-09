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
	
	private int n = Graph.getSetOfNodes().size(); 
	private double epsilon = 0.77;

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
		int m = 0; 
		double[][] M = new double[n][n]; 
		Node[] nodes = Graph.getNodes();
		
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
		
		this.calcul(M, m);
	}
	
	public void calcul(double[][] M, int m) { 
		
		double[] C = new double[m]; 
		int[] L = new int[n+1]; 
		int[] I = new int[m];
		
		int k = 0; 
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
		
		double[] vecteur = new double[n];
		for (int i = 0; i < n; i++) { 
			if(i == 0) { 
				vecteur[i] = 1.0;
			} 
			else { 
				vecteur[i] = 0.0;
			}
		} 
		
		double q = 0.0; 
		int step = 0;
		
		do { 
			double[] r = this.produit(C, L, I, vecteur); 
			q = this.norme(r); 
			step++; 
			
			for (int i = 0; i < n; i++) { 
				vecteur[i] = r[i]; 
			} 
		} while(q < epsilon);
		
		System.out.println();
		
		for (int i = 0; i < n; i++) { 
			if(i == 0) { 
				System.out.print("Le vecteur Final : ( " + vecteur[i] + ", ");
			} 
			else if(i == n-1) { 
				System.out.print(vecteur[i] + " )"); 
			} 
			else { 
				System.out.print(vecteur[i] + ", "); 
			} 
		} 
		
		System.out.println();
		
		System.out.println("Le nombre de pas effectué avant la convergence : " + step); 
		
		System.out.println();
	}
	
	public double[] produit(double[] C, int[] L, int[] I, double[] x) { 
		double[] y = new double[n]; 
		
		for (int i = 0; i < n; i++) { 
			y[i] = 0.0; 
		} 
		
		for (int i = 0; i < n; i++) {
			for (int j = L[i]; j < L[i+1]; j++) {
				y[I[j]] += C[j] * x[i];
			}
		} 
		
		return y;
	} 
	
	public double norme(double[] r) { 
		double count = 0.0; 
		
		for (int i = 0; i < n; i++) { 
			count = count + r[i]*r[i]; 
		} 
		
		double p = Math.sqrt(count); 
		
		return p;
	}

}
