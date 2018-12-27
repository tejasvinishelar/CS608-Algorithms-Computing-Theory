import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;

public class CS6089AShelar {

	static BSTNode root;
	public static void main(String[] args) throws NumberFormatException, IOException {
		// TODO Auto-generated method stub
		int searchNo=1;
		myInfo();
		int inputData[];
		inputData=readFile("BSTData.txt");
		buildBST(inputData);
		System.out.println("\nInorder Traversal Of the tree : ");
		inorderTraversal(root);
		System.out.println("\n\nHeight Of the tree : "+heightOfTree(root));
		System.out.println("\nLevel Order Of the tree : ");
		levelOrder(root);
		System.out.println("\n\nThe Number of Nodes in the tree : "+noOfNodes(root));
		System.out.println("\nLargest Element in the tree : "+largestElement(root));
		System.out.println(" ");
		while(searchNo!=0){
			System.out.println("Enter an element to search :");
			BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
			searchNo=Integer.parseInt(br.readLine());
			if(searchNode(searchNo,root))
			System.out.println(searchNo+" is Found");
			else
				System.out.println(searchNo+" is not Found");
		}

	}

	private static int heightOfTree(BSTNode node) {
		// TODO Auto-generated method stub
		if(node==null)
			return 0;
		else {
			int lheight=heightOfTree(node.left);
			int rheight=heightOfTree(node.right);
			if(lheight<rheight)
				return rheight+1;
			else
				return lheight+1;
		}
	}

	private static boolean searchNode(int searchNo,BSTNode root) {
		// TODO Auto-generated method stub
		if(root==null)
			return false;
		if(root.element==searchNo)
			return true;
		else {
			if(searchNo<root.element)
				return searchNode(searchNo,root.left);
			else
				return searchNode(searchNo, root.right);
		}
	}

	private static int largestElement(BSTNode root) {
		// TODO Auto-generated method stub
		if(root==null)
			return 0;
		else {
			while(root.right!=null)
				root=root.right;
			return root.element;
		}
	}

	private static int noOfNodes(BSTNode root) {
		// TODO Auto-generated method stub
		if(root==null)
			return 0;
		else
			return( noOfNodes(root.right)+ noOfNodes(root.left)+1);
	}


	private static void levelOrder(BSTNode root2) {
		// TODO Auto-generated method stub
		for (int i=0; i<=4; i++) { 
			System.out.println();  
			printElementsOnALevel(root,i);
		}
	}

	private static void inorderTraversal(BSTNode node) {
		// TODO Auto-generated method stub
		if(node!=null){
			inorderTraversal(node.left);
			System.out.print(" "+node.element);   
			inorderTraversal(node.right);  
		}   

	}

	private static void buildBST(int[] inputData) {
		// TODO Auto-generated method stub
		for(int i = 0; i < inputData.length; i++)  
			insert(inputData[i]);
	}

	public static void insert(int element){ 
		root = insertRec(root, element); 
	}

	static BSTNode insertRec(BSTNode node, int element){
		if (node == null)
			node = new BSTNode(element);
		else{  
			if (element < node.element)
				node.left = insertRec(node.left, element);
			else 
				node.right = insertRec(node.right, element);
		}
		return node;  
	}  

	static void printElementsOnALevel (BSTNode node ,int level)  {
		if (node == null) 
			return;   
		if (level == 0) 
			System.out.print(node.element + " ");       
		else if (level > 0){    
			printElementsOnALevel(node.left, level-1);  
			printElementsOnALevel(node.right, level-1); 
		}
	}

	private static void myInfo() {
		// TODO Auto-generated method stub
		Date today = new Date();
		
		System.out.println("Name : Tejasvini Shelar");
		System.out.println("Course Number : CS608 ");
		System.out.println("Date : "+today.toString());
	}

	private static int[] readFile(String filename) throws FileNotFoundException {
		ArrayList<Integer> list=new ArrayList<>(); 
		int inputdata[],i=0;
		File f1 = new File(filename);
		if(!f1.exists())
			System.out.println("input file not found");

		Scanner sc = new Scanner(f1);
		while (sc.hasNextInt())
		{		
			list.add(sc.nextInt());
		}	
		inputdata = new int[list.size()];
		for(i=0;i<list.size();i++) {
			inputdata[i]=list.get(i);
		}
		return inputdata;

	}


}

class BSTNode  {      
	BSTNode left;   
	int element; 
	BSTNode right;  

	/* Constructor */ 
	public BSTNode(){}         

	/* Constructor */      
	public BSTNode(int n){
		left = null;
		element = n; 
		right = null;
	}
} 

