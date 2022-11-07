package application;

// Edge class represents an edge connecting two nodes
public class Edge {
	
	Node node1;
	Node node2;	
		
	// Constructor that creates an Edge object with two nodes
	public Edge(Node node1, Node node2) {
		this.node1 = node1;
		this.node2 = node2;				
	}
	
	// Defines when two Edge objects are considered equals
	public boolean equals(Object obj) {
		
		 // If the second object is null, they are not equal
		 if(obj == null) return false;
		 
		 // If the second object is not an Edge, they are not equal
		 if(!(obj instanceof Edge)) return false;
		 
		 // Otherwise, cast the second object to an Edge
		 Edge other = (Edge) obj;		  
		 
		 // If two Edges have their 1st nodes equal and their 2nd nodes equal, then these Edges are equal 
		 if(node1.equals(other.node1) && node2.equals(other.node2)) {
			 return true;
		 }
		 // If the 1st node of the 1st Edge is equal to the 2nd node of the 2nd Edges and the same is true 
		 // for the other nodes of the two Edges, then these Edges are equal (because the Edge is undirected) 
		 else if(node1.equals(other.node2) && node2.equals(other.node1)) {
			 return true;
		 }
		 // In any other case, the Edges are not equal
		 else return false;		 
	}
	
	// Creates a hash code for the Edge object
	public int hashCode() {
		 int hash = 1;
		 hash = 37*hash + node1.hashCode();
		 hash = 37*hash + node2.hashCode();
		 return hash;
	}

}
