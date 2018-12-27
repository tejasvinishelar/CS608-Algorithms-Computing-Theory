import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;

import java.util.List;
import java.util.Stack;

public class CS60811AShelar {

	static List<Node> nodes = new ArrayList();
	static class Node {
		char data;
		boolean visited;

		public Node(char data) {
			this.data = data;
		}
	}

	public static ArrayList findNeighbours(int adjacency_matrix[][], Node x) {
		int nodeIndex = -1;

		ArrayList neighbours = new ArrayList();
		for (int i = 0; i < nodes.size(); i++) {
			if (nodes.get(i).equals(x)) {
				nodeIndex = i;
				break;
			}
		}

		if (nodeIndex != -1) {
			for (int j = 0; j < adjacency_matrix[nodeIndex].length; j++) {
				if (adjacency_matrix[nodeIndex][j] >0) {
					neighbours.add(nodes.get(j));
				}
			}
		}
		return neighbours;
	}

	// Recursive DFS
	public static void dfs(int adjacency_matrix[][], Node node) {

		System.out.print(node.data + "\t");
		ArrayList<Node> neighbours = findNeighbours(adjacency_matrix, node);
		node.visited=true;
		for (int i = 0; i < neighbours.size(); i++) {
			Node n = (Node) neighbours.get(i);
			if (n != null && !n.visited) {
				dfs(adjacency_matrix, n);
			}
		}
	}
	public static void main(String arg[]) throws FileNotFoundException {

		myInfo();
		int adjacency_matrix[][] =readFile("inputData11A.txt"); 
		Node nodeA = new Node('A');
		Node nodeB = new Node('B');
		Node nodeC = new Node('C');
		Node nodeD = new Node('D');
		Node nodeE = new Node('E');
		Node nodeF = new Node('F');
		Node nodeG = new Node('G');
		Node nodeH = new Node('H');
		Node nodeI = new Node('I');
		Node nodeJ = new Node('J');
		Node nodeK = new Node('K');
		Node nodeL = new Node('L');
		Node nodeM = new Node('M');
		Node nodeN = new Node('N');
		Node nodeO = new Node('O');

		nodes.add(nodeA);
		nodes.add(nodeB);
		nodes.add(nodeC);
		nodes.add(nodeD);
		nodes.add(nodeE);
		nodes.add(nodeF);
		nodes.add(nodeG);
		nodes.add(nodeH);
		nodes.add(nodeI);
		nodes.add(nodeJ);
		nodes.add(nodeK);
		nodes.add(nodeL);
		nodes.add(nodeM);
		nodes.add(nodeN);
		nodes.add(nodeO);
		 
		System.out.println("\n\nThe DFS traversal of the graph: ");
		dfs(adjacency_matrix, nodeA);
	}

	private static void myInfo() {
		// TODO Auto-generated method stub
		Date today = new Date();

		System.out.println("Name : Tejasvini Shelar");
		System.out.println("Course Number : CS608 ");
		System.out.println("Date : "+today.toString());
	}

	private static int[][] readFile(String filename) throws FileNotFoundException {
		ArrayList<Integer> list=new ArrayList<>(); 
		int inputdata[][] = null,i=0;
		File f1 = new File(filename);
		if(!f1.exists())
			System.out.println("input file not found");

		Scanner sc = new Scanner(f1);
		inputdata = new int[15][15];
		while(sc.hasNextLine()) {
			for (i=0; i<15; i++) {
				String[] line = sc.nextLine().trim().split(" ");
				for (int j=0; j<line.length; j++) {
					inputdata[i][j] = Integer.parseInt(line[j]);
				}
			}
		}
		return inputdata;
	}

}

