package application;

// Node class represents a single node of a network.
public class Node {
	
	private String nd_name;
	
	// Default constructor that creates a Node object with empty name
	public Node() {
		nd_name = "";		
	}
	
	// 2nd constructor that creates a Node object with a specific name
	public Node(String nodeName) {
		nd_name = nodeName;
	}
		
	// Returns the node name
	public String getNodeName() {
		return nd_name;
	}
	
	// Defines when two Node objects are considered equals
	public boolean equals(Object obj) {
		
		 // If the second object is null, they are not equal
		 if(obj == null) return false;
		 
		 // If the second object is not a Node, they are not equal
		 if(!(obj instanceof Node)) return false;
		 
		// Otherwise, cast the second object to a Node
		 Node other = (Node) obj;
		 
		// If the two nodes have different name, then they are not equal 
		if(!nd_name.equals(other.nd_name)) return false;
		 
		// In other case, the objects are equal
		return true;
		 
		}
	
	// Creates a hash code for the Node object
	public int hashCode() {
		 int hash = 1;
		 hash = 37*hash + nd_name.hashCode();
		 return hash;
	}

}
