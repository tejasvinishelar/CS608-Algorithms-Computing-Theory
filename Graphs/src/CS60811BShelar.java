import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Scanner;
import java.util.Stack;
import java.util.List;
import java.util.Queue;

public class CS60811BShelar {

	public static void main(String arg[]) throws FileNotFoundException {
		myInfo();
		DFSClass dfs = new DFSClass();
		dfs.dfsoperation();
		BreadthFirstSearchExample bfs = new BreadthFirstSearchExample();
		bfs.bfsoperation();
		DijkstrasAlgorithm dij = new DijkstrasAlgorithm();
		dij.dijkstrasShortestPath();
	}

	private static void myInfo() {
		// TODO Auto-generated method stub
		Date today = new Date();
		System.out.println("Name : Tejasvini Shelar");
		System.out.println("Course Number : CS608 ");
		System.out.println("Date : "+today.toString());
	}

	static int[][] readFile(String filename) throws FileNotFoundException {
		ArrayList<Integer> list=new ArrayList<>(); 
		int inputdata[][] = null,i=0;
		File f1 = new File(filename);
		if(!f1.exists())
			System.out.println("input file not found");

		Scanner sc = new Scanner(f1);
		inputdata = new int[15][15];
		while(sc.hasNextInt()) {
			for (i=0; i<15; i++) {
				for(int j=0; j<15; j++) {
					inputdata[i][j] = sc.nextInt();
				}
			}
		}
	/*	System.out.println("\n");
		for (i=0; i<15; i++) {
			for (int j=0; j<15; j++) {
				System.out.print(inputdata[i][j]);
			}
			System.out.println("\n");
		}
*/
		return inputdata;
	}
}

class DijkstrasAlgorithm { 

	private static final int NO_PARENT = -1; 

	// Function that implements Dijkstra's 
	// single source shortest path 
	// algorithm for a graph represented  
	// using adjacency matrix 
	// representation 
	private static void dijkstra(int[][] adjacencyMatrix, 
			int startVertex) 
	{ 
		int nVertices = adjacencyMatrix[0].length; 

		// shortestDistances[i] will hold the 
		// shortest distance from src to i 
		int[] shortestDistances = new int[nVertices]; 

		// added[i] will true if vertex i is 
		// included / in shortest path tree 
		// or shortest distance from src to  
		// i is finalized 
		boolean[] added = new boolean[nVertices]; 

		// Initialize all distances as  
		// INFINITE and added[] as false 
		for (int vertexIndex = 0; vertexIndex < nVertices;  
				vertexIndex++) 
		{ 
			shortestDistances[vertexIndex] = Integer.MAX_VALUE; 
			added[vertexIndex] = false; 
		} 

		// Distance of source vertex from 
		// itself is always 0 
		shortestDistances[startVertex] = 0; 

		// Parent array to store shortest 
		// path tree 
		int[] parents = new int[nVertices]; 

		// The starting vertex does not  
		// have a parent 
		parents[startVertex] = NO_PARENT; 

		// Find shortest path for all  
		// vertices 
		for (int i = 1; i < nVertices; i++) 
		{ 

			// Pick the minimum distance vertex 
			// from the set of vertices not yet 
			// processed. nearestVertex is  
			// always equal to startNode in  
			// first iteration. 
			int nearestVertex = -1; 
			int shortestDistance = Integer.MAX_VALUE; 
			for (int vertexIndex = 0; 
					vertexIndex < nVertices;  
					vertexIndex++) 
			{ 
				if (!added[vertexIndex] && 
						shortestDistances[vertexIndex] <  
						shortestDistance)  
				{ 
					nearestVertex = vertexIndex; 
					shortestDistance = shortestDistances[vertexIndex]; 
				} 
			} 

			// Mark the picked vertex as 
			// processed 
			added[nearestVertex] = true; 

			// Update dist value of the 
			// adjacent vertices of the 
			// picked vertex. 
			for (int vertexIndex = 0; 
					vertexIndex < nVertices;  
					vertexIndex++)  
			{ 
				int edgeDistance = adjacencyMatrix[nearestVertex][vertexIndex]; 

				if (edgeDistance > 0
						&& ((shortestDistance + edgeDistance) <  
								shortestDistances[vertexIndex]))  
				{ 
					parents[vertexIndex] = nearestVertex; 
					shortestDistances[vertexIndex] = shortestDistance +  
							edgeDistance; 
				} 
			} 
		} 

		printSolution(startVertex, shortestDistances, parents); 
	} 

	// A utility function to print  
	// the constructed distances 
	// array and shortest paths 
	private static void printSolution(int startVertex, 
			int[] distances, 
			int[] parents) 
	{ 
		int nVertices = distances.length; 
		System.out.println("\n\nVertex\t Distance\tPath"); 

		for (int vertexIndex = 0;  
				vertexIndex < nVertices;  
				vertexIndex++)  
		{ 
			if (vertexIndex != startVertex)  
			{ 
				System.out.print("\n" +(char) (startVertex+'A') + " -> "); 
				System.out.print((char)(vertexIndex+'A') + " \t\t "); 
				System.out.print(distances[vertexIndex] + "\t\t"); 
				printPath(vertexIndex, parents); 
			} 
		} 
	} 

	// Function to print shortest path 
	// from source to currentVertex 
	// using parents array 
	private static void printPath(int currentVertex, 
			int[] parents) 
	{ 

		// Base case : Source node has 
		// been processed 
		if (currentVertex == NO_PARENT) 
		{ 
			return; 
		} 
		printPath(parents[currentVertex], parents); 
		System.out.print((char)(currentVertex +'A') + " "); 
	} 

	// Driver Code 
	public static void dijkstrasShortestPath() throws FileNotFoundException 
	{ 
		CS60811BShelar obj =new CS60811BShelar();
		int adjacency_matrix[][] =obj.readFile("inputData11B.txt"); 
		dijkstra(adjacency_matrix, 0); 
	} 
} 

class DFSClass{

	static ArrayList<DFSNode> dfsnodes=new ArrayList<>();

	public class DFSNode{
		char data;
		boolean visited;

		DFSNode(char data)
		{
			this.data=data;
		}
	}

	public ArrayList<DFSNode> findNeighbours(int adjacency_matrix[][],DFSNode x)
	{
		int nodeIndex=-1;
		ArrayList<DFSNode> neighbours=new ArrayList<>();
		for (int i = 0; i < dfsnodes.size(); i++) {
			if(dfsnodes.get(i).equals(x)){
				nodeIndex=i;
				break;
			}
		}
		if(nodeIndex!=-1){
			for (int j = 0; j < adjacency_matrix[nodeIndex].length; j++) {
				if(adjacency_matrix[nodeIndex][j]>0){
					neighbours.add(dfsnodes.get(j));
				}
			}
		}
		return neighbours;
	}

	public void dfs(int adjacency_matrix[][], DFSNode node) {
		System.out.print(node.data + "\t");
		ArrayList<DFSNode> neighbours = findNeighbours(adjacency_matrix, node);
		node.visited=true;
		for (int i = 0; i < neighbours.size(); i++) {
			DFSNode n = (DFSNode) neighbours.get(i);
			if (n != null && !n.visited) {
				dfs(adjacency_matrix, n);
			}
		}
	}


	public void dfsoperation() throws FileNotFoundException {
		CS60811BShelar obj =new CS60811BShelar();
		int adjacency_matrix[][] =obj.readFile("inputData11B.txt"); 

		DFSNode DFSNodeA = new DFSNode('A');
		DFSNode DFSNodeB = new DFSNode('B');
		DFSNode DFSNodeC = new DFSNode('C');
		DFSNode DFSNodeD = new DFSNode('D');
		DFSNode DFSNodeE = new DFSNode('E');
		DFSNode DFSNodeF = new DFSNode('F');
		DFSNode DFSNodeG = new DFSNode('G');
		DFSNode DFSNodeH = new DFSNode('H');
		DFSNode DFSNodeI = new DFSNode('I');
		DFSNode DFSNodeJ = new DFSNode('J');
		DFSNode DFSNodeK = new DFSNode('K');
		DFSNode DFSNodeL = new DFSNode('L');
		DFSNode DFSNodeM = new DFSNode('M');
		DFSNode DFSNodeN = new DFSNode('N');
		DFSNode DFSNodeO = new DFSNode('O');

		dfsnodes.add(DFSNodeA);
		dfsnodes.add(DFSNodeB);
		dfsnodes.add(DFSNodeC);
		dfsnodes.add(DFSNodeD);
		dfsnodes.add(DFSNodeE);
		dfsnodes.add(DFSNodeF);
		dfsnodes.add(DFSNodeG);
		dfsnodes.add(DFSNodeH);
		dfsnodes.add(DFSNodeI);
		dfsnodes.add(DFSNodeJ);
		dfsnodes.add(DFSNodeK);
		dfsnodes.add(DFSNodeL);
		dfsnodes.add(DFSNodeM);
		dfsnodes.add(DFSNodeN);
		dfsnodes.add(DFSNodeO);

		System.out.println("\n\nThe DFS traversal of the graph : ");
		dfs(adjacency_matrix, DFSNodeA);

	}
}

class BreadthFirstSearchExample
{ 
	private Queue<Node> queue;
	static ArrayList<Node> nodes=new ArrayList<Node>();
	static class Node
	{
		char data;
		boolean visited;

		Node(char data)
		{
			this.data=data;
		}
	}

	public BreadthFirstSearchExample()
	{
		queue = new LinkedList<Node>();
	}

	// find neighbors of node using adjacency matrix
	// if adjacency_matrix[i][j]==1, then nodes at index i and index j are connected
	public ArrayList<Node> findNeighbours(int adjacency_matrix[][],Node x)
	{
		int nodeIndex=-1;

		ArrayList<Node> neighbours=new ArrayList<Node>();
		for (int i = 0; i < nodes.size(); i++) {
			if(nodes.get(i).equals(x))
			{
				nodeIndex=i;
				break;
			}
		}

		if(nodeIndex!=-1)
		{
			for (int j = 0; j < adjacency_matrix[nodeIndex].length; j++) {
				if(adjacency_matrix[nodeIndex][j]>0)
				{
					neighbours.add(nodes.get(j));
				}
			}
		}
		return neighbours;
	}

	public void bfs(int adjacency_matrix[][], Node node)
	{
		queue.add(node);
		node.visited=true;
		while (!queue.isEmpty())
		{

			Node element=queue.remove();
			System.out.print(element.data + "\t");
			ArrayList<Node> neighbours=findNeighbours(adjacency_matrix,element);
			for (int i = 0; i < neighbours.size(); i++) {
				Node n=neighbours.get(i);
				if(n!=null && !n.visited)
				{
					queue.add(n);
					n.visited=true;
				}
			}

		}
	}

	public static void bfsoperation() throws FileNotFoundException
	{
		CS60811BShelar obj = new CS60811BShelar();
		int adjacency_matrix[][] =obj.readFile("inputData11B.txt"); 

		Node DFSNodeA = new Node('A');
		Node DFSNodeB = new Node('B');
		Node DFSNodeC = new Node('C');
		Node DFSNodeD = new Node('D');
		Node DFSNodeE = new Node('E');
		Node DFSNodeF = new Node('F');
		Node DFSNodeG = new Node('G');
		Node DFSNodeH = new Node('H');
		Node DFSNodeI = new Node('I');
		Node DFSNodeJ = new Node('J');
		Node DFSNodeK = new Node('K');
		Node DFSNodeL = new Node('L');
		Node DFSNodeM = new Node('M');
		Node DFSNodeN = new Node('N');
		Node DFSNodeO = new Node('O');

		nodes.add(DFSNodeA);
		nodes.add(DFSNodeB);
		nodes.add(DFSNodeC);
		nodes.add(DFSNodeD);
		nodes.add(DFSNodeE);
		nodes.add(DFSNodeF);
		nodes.add(DFSNodeG);
		nodes.add(DFSNodeH);
		nodes.add(DFSNodeI);
		nodes.add(DFSNodeJ);
		nodes.add(DFSNodeK);
		nodes.add(DFSNodeL);
		nodes.add(DFSNodeM);
		nodes.add(DFSNodeN);
		nodes.add(DFSNodeO);


		System.out.println("\n\nThe BFS traversal of the graph : ");
		BreadthFirstSearchExample bfsExample = new BreadthFirstSearchExample();
		bfsExample.bfs(adjacency_matrix, DFSNodeA);
	}
}

