package application;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.nio.file.*;
import java.io.*; 

// Network class represents an undirected network
public class Network {
	
	// ArrayLists that store all the nodes (nodesList) and the edges (edgesList) of the network
	ArrayList <Node> nodesList;
	ArrayList <Edge> edgesList;
	
	
	// Default constructor that creates an empty network.
	public Network() {
		this.nodesList = new ArrayList<Node>();
		this.edgesList = new ArrayList<Edge>();		
	}
	
	
	
	/* It reads a file containing a list of protein interactions and creates a Network out of them.
	   It includes self-interactions and duplicate interactions only once.
	   It accepts as an argument the full path of a file */
	public void createNetworkFromFile(String filepath) {
		
		// Creating BufferedReader in order to read the file
		Path file = Paths.get(filepath);
		try(BufferedReader reader = Files.newBufferedReader(file)) {
			 String line = null;
			 
			 // Reads the file line by line
			 while((line = reader.readLine()) != null) {
				 
				 String[] elements = line.split("\t");	// Splits every line into two elements						  
				 
				 // 1st element of the splitting is the 1st node, and the 2nd element is the 2nd node
				 Node n1 = new Node(elements[0]);
				 Node n2 = new Node(elements[1]);	
				 
				 // Adds the read interaction in the network by using "addNetworkInteraction" method, presented next
				 this.addNetworkInteraction(n1, n2);
			 }
			 reader.close();
		}
		catch(IOException x) {
			 System.err.format("IOException: %s%n", x);
		}
	}
	
	
	
	/* Adds a new interaction to the Network when it is called, either by the aforementioned "createNetworkFromFile" method,
	   or by the Main class (when the user add manually an interaction to the network). 
	   It doesn't allow the addition of an interaction that is already contained in the (undirected) network.
	   It returns 1 for a successful interaction addition or returns 0 if the interaction already exists.
	*/
	public int addNetworkInteraction(Node node1, Node node2) {
		
		// Creating a new edge and checking if the nodes and the edge are already contained in the network 
		Edge e = new Edge(node1,node2);
		boolean exists1 = nodesList.contains(node1);
		boolean exists2 = nodesList.contains(node2);		
		boolean exists3 = edgesList.contains(e);
		
		// When the given edge (interaction) already exists, return 0
		if(exists3 == true) {		
			return 0;			
		}else { // Given edge is not contained in the network
			
			// When 1st node doesn't already exist, it is added to network nodesList
			if(exists1 == false) {
				nodesList.add(node1);
			}
			
			// When 2nd node doesn't already exist, it is added to network nodesList
			exists2 = nodesList.contains(node2);
			if(exists2 == false) {
				nodesList.add(node2);
			}	
			
			// Adding given interaction to network edgesList
			edgesList.add(e);	
			
			// Interaction added successfully, return 1
			return 1;
		}
	} 			
		
	
	
	// It returns (to Main class) the degree of a user given node - if node doesn't exist in the network, it returns -1
	public int getNodeDegree(Node n) {
		
		Edge e;
		
		// Node doesn't exist in the network nodesList, return -1
		if(nodesList.contains(n) == false) {
			return -1;
		}
		
		// Otherwise, check how many edges are connected to the given node
		int numOfEdges = 0;
		for(int i=0; i<edgesList.size(); i++) {
			e = edgesList.get(i);
			
			// Check if the given node is the 1st or the 2nd node of a specific edge
			if(e.node1.equals(n) || e.node2.equals(n)) {
				numOfEdges++;
			}
			
			// Check if the given node is the 1st and the 2nd node of a specific edge - for the case of self-interaction
			if(e.node1.equals(n) && e.node2.equals(n)){
				numOfEdges++;
			}
		}
		
		// Returning the number of edges are connected to the given node, i.e. node degree 
		return numOfEdges;
	}
	
	// b) It returns (to Main class) the average degree of all nodes in the network.
	public double getAvgNodeDegree() {
		
		// Sum of the degrees of every node in the network 
		double sum = 0.0;
		// Average degree of all nodes in the network
		double avgND = 0.0;		
		
		// For every network node, get its degree by calling "getNodeDegree" method and added to the sum
		for(Node n : nodesList) {
			sum = sum + this.getNodeDegree(n);			
		}
		
		// Divide the sum by the total number of nodes in the network
		avgND = sum / nodesList.size();
		return avgND;
	}

	
	
	/* It finds the hubs of the network, ie the nodes of highest degree, and returns to Main class
	   the value of the highest degree and the names of all nodes having that degree. These results are 
	   printed in GUI by Main class.	   
	*/
	public ArrayList <String> findNetworkHubs() {	
		
		// HashMap where key is the node degree and value is an ArrayList containing the nodes that have this node degree  
		HashMap<Integer,ArrayList<Node>> nodesDegrees = new HashMap<Integer,ArrayList<Node>>();
		Integer key;		
		
		// Initialization of HashMap by inserting all the existing node degrees as a key and an empty ArrayList as value 
		// Node degree is calculated by using "getNodeDegree" method
		for(Node n : nodesList) {
			key = this.getNodeDegree(n);
			nodesDegrees.put(key, new ArrayList<Node>());			
		}
		
		// Adding nodes based on their degree to every ArrayList of the HashMap
		for(Node n : nodesList) {
			key = this.getNodeDegree(n);
			nodesDegrees.get(key).add(n);			
		}
		 
		// Getting the value of the highest node degree
		Integer max = Collections.max(nodesDegrees.keySet());
				
		// Creating an ArrayList that contains in String format the highest node degree and the nodes that have this degree.
		// First element of the ArrayList is the highest node degree and the rest elements are the nodes having this degree. 
		ArrayList <String> results = new ArrayList <String>();
		results.add(max.toString());
		for(Node n : nodesDegrees.get(max)) {
			results.add(n.getNodeName());			
		}
		
		// Return results to Main class in order to be presented in GUI
		return results;
		
	}
	
	
	
	/* It saves the full degree distribution of the network in a file in the form of a table. The first column of the table 
	   contains the degree and the second column the number of nodes having that degree.
	   It accepts as an argument the full path of the file where the full degree distribution is going to be written.
	*/
	public void getFullDegreeDistributionArray(String filepath) { 
			
		// HashMap where key is the node degree and value is the number of nodes that have this node degree  
		HashMap<Integer,Integer> degreesDistr = new HashMap<Integer,Integer>();
		Integer key, previousValue;		
		
		// Initialization of HashMap by inserting node degree as a key and 0 as a value 
		// Node degree is calculated by using "getNodeDegree" method
		for(Node n : nodesList) {
			key = this.getNodeDegree(n);
			degreesDistr.put(key, 0);			
		}
				
		// Increasing the number of nodes for every node degree  
		for(Node n : nodesList) {
			key = this.getNodeDegree(n);
			previousValue = degreesDistr.get(key);		
			degreesDistr.put(key, previousValue+1);
		}
						
		// Creating BufferedWriter in order to export the results to a file
		Path file = Paths.get(filepath);
		try(BufferedWriter writer = Files.newBufferedWriter(file)){			
			writer.write("Node Degree\t\t  Number of Nodes\n");
			for(Integer k : degreesDistr.keySet()) {				
				writer.write("\t"+k+"\t\t\t"+degreesDistr.get(k)+ "\n");
			}			
		 	writer.close();
		}
		catch(IOException x) {			
			System.err.format("IOException: %s%n", x);
		}			
	}	
}
