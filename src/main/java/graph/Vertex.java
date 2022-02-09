/**
 * Vertex class 
 * @author Roberto E. Lopez-Herrejon
 * ETS MGL869
 */
package graph;

// undirected and Directed have the same code


import java.util.LinkedList;
import java.util.List;

public class Vertex {
    public List<Neighbor> neighbors;
    public String name;
    
    // Hint: You may add an attribute to indicate if the vertex has been visited or not
    
    public Vertex() {
        name      = null;
        neighbors = new LinkedList<Neighbor>();
        
    }

    
    public  Vertex assignName( String name ) {
        this.name = name;
        return this;
    }
   
    public void addNeighbor( Neighbor n ) {
        neighbors.add( n );
    }

    public boolean equals(Object o) {
    	boolean result = false;
    	    	if (!(o instanceof Vertex)) return result;
    	Vertex v = (Vertex) o;
    	if (v.name==this.name) result = true;
    	return result;
    }
   
    public void display() {
    	
        System.out.print( " Node " + name + " connected to: " );

        for (Neighbor theNeighbor : neighbors) {

        	if (GraphMain.UNDIRECTED) System.out.print( theNeighbor.end.name + ", " );
        	
            Vertex v = theNeighbor.end;
            System.out.print( v.name + ", " );
            
        } // for all the vertices
        
        System.out.println();
    } // of display
    
    
    
    
} // class Vertex
