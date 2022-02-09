/**
 * Class Graph
 * @author Roberto E. Lopez-Herrejon
 * ETS MGL869
 */
package graph;

import java.io.*;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class Graph {

	
    public static int ch; // Character to read/write
	// **************************************************
	// Management of graph file for benchmarking
	// @feature BENCH
    public Reader inFile; // File handler for reading
    // Lists of vertices and edges
    public LinkedList<Vertex> vertices;
    public LinkedList<Edge> edges;

    /**
     * Constructor of the Graph class that initializes the vertices and edges.
     */
    public Graph() {
        vertices = new LinkedList<Vertex>();
        edges = new LinkedList<Edge>();
    }
 
    /**
     * Opens the benchmark file
     * @param FileName
     */
    public void openBenchmark( String FileName ) {
        try {
            inFile = new FileReader( FileName );
        } catch ( IOException e ) {
            System.out.println( "Your file " + FileName + " cannot be read" );
            e.printStackTrace();
            System.exit(0);
        }
    }

    /**
     * Attempts to close the benchmark file.
     * @throws IOException
     */
    public void stopBenchmark()
    {
    	try { inFile.close();
    	} catch(IOException e) {
    		System.out.println("Error while closing the benchmark file");
    		e.printStackTrace();
    		System.exit(0);
    	}
    }
    
    public int readNumber() throws IOException {
        int index =0;
        char[] word = new char[80];
        int ch=0;

        ch = inFile.read();
        while( ch==32 )
            ch = inFile.read(); // skips extra whitespaces

        while( ch!=-1 && ch!=32 && ch!=10 ) // while it is not EOF, WS, NL
        {
            word[index++] = ( char )ch;
            ch = inFile.read();
        }
        word[index]=0;

        String theString = new String( word );

        theString = theString.substring(0, index)
                             .trim();
        return Integer.parseInt( theString,10 );
    }
    
    /**
     * Adds an edge without weights
     * @param start vertex of the edge
     * @param end vertex of the edge
     */
    public void addAnEdge( Vertex start,  Vertex end) {
        Edge theEdge = new  Edge(start,end);
        addEdge( theEdge );
    }
   
    /** 
     * Adds an edge with weights
     * @param start vertex of the edge
     * @param end vertex of the edge
     */
    public void addAnEdge( Vertex start,  Vertex end, int weight ) {
        Edge theEdge = new Edge(start, end, weight );
        addEdge(theEdge);
    }
    
    /**
     * Adds a vertex to the list of vertices
     * @param v Vertex to add
     */
    public void addVertex( Vertex v ) {
        vertices.add( v );
    }
   
    /**
     * Adds an edge based from
     * @param the_edge
     */
    public void addEdge( Edge the_edge ) {
        Vertex start = the_edge.start;
        Vertex end = the_edge.end;
        edges.add( the_edge );
        
        start.addNeighbor( new  Neighbor( end,the_edge ) );
        
        // if the graph is undirected
        if (GraphMain.UNDIRECTED) end.addNeighbor( new  Neighbor( start,the_edge ) );
        // ---
    } //of addEdge
    
 
    /**
     * Finds a vertex given its name in the vertices list
     * @param theName
     * @return
     */
    public  Vertex findsVertex( String theName ) {
    
        // if we are dealing with the root
        if ( theName==null ) return null;
        
        for (Vertex theVertex : vertices)  
        	if ( theName.equals( theVertex.name ) ) return theVertex;
        return null;
    } // of findsVertex

     
    /**
     * Displays the list of vertices and edges
     */
    public void display() {
        int i;
                                   
        System.out.println( "******************************************" );
        System.out.println( "Vertices " );
        for ( i=0; i<vertices.size(); i++ )
            vertices.get( i )
                    .display();
         
        System.out.println( "******************************************" );
        System.out.println( "Edges " );
        for ( i=0; i<edges.size(); i++ )
            edges.get( i )
                 .display();
                
        System.out.println( "******************************************" );
    } // of display
    
     
   // **********************************************
   // **********************************************  
   // Implement your search methods here
    
    /**
     * Your implementation of DFS
     * Note that this methods displays each node in the traversal order
     * @param vertexName
     */
    public void DFS(String vertexName) {
        List<Vertex> ordered_vertices = new LinkedList<>();
        Vertex origin = findsVertex(vertexName);
        ordered_vertices.add(origin);
        exploreChildren(origin, ordered_vertices );
        for (Vertex vertex : ordered_vertices){
            System.out.print(vertex.name + " ");
        }
    } // of DFS
    
    private void exploreChildren(Vertex vertex, List<Vertex> visited_vertices){
        for (Edge edge : edges){
            if (edge.start == vertex && !visited_vertices.contains(edge.end)){
                visited_vertices.add(edge.end);
                exploreChildren(edge.end,visited_vertices);
            }
        }
    }
    
    
    /**
     * Your implementation of BFS
     * Note that this methods displays each node in the traversal order
     * @param vertexName
     */
    public void BFS(String vertexName) {
        List<Vertex> ordered_vertices = new LinkedList<>();
        ordered_vertices.add(findsVertex(vertexName));
        while ( ordered_vertices.size() < vertices.size()){
            for (int i = 0 ; i < ordered_vertices.size() ; i++){
                for (Edge edge : edges){
                    if (edge.start == ordered_vertices.get(i) && !ordered_vertices.contains(edge.end)){
                        ordered_vertices.add(edge.end);
                    }
                }
            }

        }
        for (Vertex vertex : ordered_vertices){
            System.out.print(vertex.name + " ");
        }
    } // of BFS    
    
    
} // of Graph
