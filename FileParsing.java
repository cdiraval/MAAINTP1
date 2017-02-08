/**
 * 
 *	@author Valens Iraguha 
 *	E-mail : valens.iraguha@etu.univ-paris-diderot.fr
 *	
 *	Méthodes algorithmiques pour l'accès à l'information numérique
 *	TP N°1 : Programmer PageRank sur les graphes du Web 
 *	
 */


import java.io.FileInputStream; 
import java.io.IOException; 
import java.nio.charset.Charset; 
import java.nio.charset.StandardCharsets; 
import java.util.Scanner; 

public class FileParsing { 
	
	private final static Charset ENCODING = StandardCharsets.UTF_8; 
	private static Scanner input; 
	
	FileParsing() { } 
	
	FileParsing(Graph graph, String filename) { 
		if(filename.endsWith("txt")) { 
			try { 
				input = new Scanner(new FileInputStream(filename), ENCODING.name()); 
				
				while(input.hasNextLine()) { 
					String line = input.nextLine(); 
					line = line.trim(); 
					
					String edgeData[] = line.split("\\s+"); 
					
					if(!edgeData[0].equals('#')) { 
						if (edgeData.length == 2) { 
							graph.addElement(edgeData[0].trim(), edgeData[1].trim());
						} 
						else if (edgeData.length == 0) { 
							graph.addOrphan(edgeData[0].trim());
						} 
						else { 
							
						}
					} 
					else { 
						
					}
					
				}
			} 
			catch(IOException IOExc) { 
				System.out.println("Exception => "+ filename + " does not find!!"); 
				System.exit(0);
			} 
			finally { 
				input.close();
			}
		} 
		else { 
			
		}
	}
}
