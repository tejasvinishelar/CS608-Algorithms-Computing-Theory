import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;


public class CS6089BShelar {

	static BSTNode bstroot;
	static AVLNode avlroot;
	static AVLTree avltree =new AVLTree();
	static BSTTree bsttree =new BSTTree();
	static double BSTTreeBuildTime;
	static double AVLTreeBuildTime;
	static double starttime;
	static double endtime;

	public static void main(String[] args) throws NumberFormatException, IOException {
		// TODO Auto-generated method stub
		myInfo();
		int inputData[];
		inputData=readFile("BSTData.txt");
		starttime=System.nanoTime();
		buildBST(inputData);
		double endtime=System.nanoTime();
		BSTTreeBuildTime=endtime-starttime;

		starttime=System.nanoTime();
		buildAVL(inputData);
		endtime=System.nanoTime();
		AVLTreeBuildTime=endtime-starttime;

		avlroot=avltree.returnroot();
		bstroot=bsttree.returnroot();
		performTasksOnTrees();
	}

	private static void performTasksOnTrees() throws NumberFormatException, IOException {
		// TODO Auto-generated method stub
		int searchNo=1;
		System.out.println("\nInorder Traversal Of the BST tree : ");
		bsttree.inorderTraversal(bstroot);

		System.out.println("\nInorder Traversal Of the AVL tree : ");
		avltree.inorderTraversal(avlroot);

		System.out.println("\n\nHeight Of the BST tree : "+bsttree.heightOfTree(bstroot));
		System.out.println("\nHeight Of the AVL tree : "+avltree.heightOfTree(avlroot));

		System.out.println("\nLevel Order Of the BST tree : ");
		bsttree.levelOrder(bstroot);

		System.out.println("\nLevel Order Of the AVL tree : ");
		avltree.levelOrder(avlroot);

		System.out.println("\nLargest Element in the BST tree : "+bsttree.largestElement(bstroot));
		System.out.println("\nLargest Element in the AVL tree : "+avltree.largestElement(avlroot));

		System.out.println("\nTime taken to build BST tree : "+BSTTreeBuildTime);
		System.out.println("\nTime taken to build AVL tree : "+AVLTreeBuildTime);

		System.out.println(" ");
		while(searchNo!=0){
			System.out.println("\nEnter an element to search :");
			BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
			searchNo=Integer.parseInt(br.readLine());
			starttime=System.nanoTime();
			if(bsttree.searchNode(searchNo,bstroot))
				System.out.println(searchNo+" is Found in BST Tree");
			else
				System.out.println(searchNo+" is not Found in BST Tree");
			endtime=System.nanoTime();
			System.out.println("Time taken to search in BST :"+(endtime-starttime));

			System.out.println(" ");
			starttime=System.nanoTime();
			if(avltree.searchNode(searchNo,avlroot))
				System.out.println(searchNo+" is Found in AVLTree");
			else
				System.out.println(searchNo+" is not Found in AVL Tree");
			endtime=System.nanoTime();
			System.out.println("Time taken to search in AVL :"+(endtime-starttime));
		}

	}

	private static void buildBST(int[] inputData) {
		// TODO Auto-generated method stub
		for(int i = 0; i < inputData.length; i++)  
			bsttree.insert(inputData[i]);
	}

	private static void buildAVL(int[] inputData) {
		// TODO Auto-generated method stub
		for(int i = 0; i < inputData.length; i++)  
			avltree.insertStart(inputData[i]);

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

class BSTNode {      
	BSTNode left;   
	int element; 
	BSTNode right;  

	public BSTNode(){}         

	public BSTNode(int n){
		left = null;
		element = n; 
		right = null;
	}
}

class BSTTree{
	static BSTNode root;
	BSTTree(){
		root=null;
	}

	public void insert(int element){ 
		root = insertRec(root, element); 
	}

	public BSTNode insertRec(BSTNode node, int element){
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

	public void printElementsOnALevel (BSTNode node ,int level)  {
		if (node == null) 
			return;   
		if (level == 0) 
			System.out.print(node.element + " ");       
		else if (level > 0){    
			printElementsOnALevel(node.left, level-1);  
			printElementsOnALevel(node.right, level-1); 
		}
	}
	public BSTNode returnroot() {
		// TODO Auto-generated method stub
		return root;
	}

	public int heightOfTree(BSTNode node) {
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

	public boolean searchNode(int searchNo,BSTNode root) {
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

	public int largestElement(BSTNode root) {
		// TODO Auto-generated method stub
		if(root==null)
			return 0;
		else {
			while(root.right!=null)
				root=root.right;
			return root.element;
		}
	}

	public void levelOrder(BSTNode root2) {
		// TODO Auto-generated method stub
		for (int i=0; i<=4; i++) { 
			System.out.println();  
			printElementsOnALevel(root,i);
		}
	}

	public void inorderTraversal(BSTNode node) {
		// TODO Auto-generated method stub
		if(node!=null){
			inorderTraversal(node.left);
			System.out.print(" "+node.element);   
			inorderTraversal(node.right);  
		}   
	}
}
class AVLNode{
	AVLNode left;
	int data;
	AVLNode right;
	int height;

	public AVLNode() {
		left=null;
		data=0;
		right=null;
		height=0;
	}
	public AVLNode(int n) {
		left=null;
		data=n;
		right=null;
		height=0;
	}
}

class AVLTree{
	static AVLNode root;
	AVLTree(){
		root=null;
	}

	void insertStart(int element) {
		root=insert(element,root);
	}

	AVLNode insert(int newEle, AVLNode node) {
		if(node==null)
			node=new AVLNode(newEle);
		else if(newEle<node.data) {
			node.left=insert(newEle,node.left);
			if(heightOfSubtree(node.left)-heightOfSubtree(node.right)==2) {
				if(newEle<node.left.data)
					node=singleRightRotation(node);
				else
					node=doubleRightRotation(node);
			}
		}
		else if(newEle > node.data) {
			node.right=insert(newEle,node.right);
			if(heightOfSubtree(node.right)-heightOfSubtree(node.left)==2) {
				if(newEle>node.right.data)
					node=singleLeftRotation(node);
				else
					node=doubleLeftRotation(node);
			}
		}
		else {
			System.out.println("Element already exist in the tree");
		}
		node.height=larger(heightOfSubtree(node.left),heightOfSubtree(node.right))+1;
		return node;
	}

	public AVLNode returnroot() {
		return root;
	}
	private int heightOfSubtree(AVLNode node) {
		if(node==null)
			return -1;
		else return(node.height);
	}

	int larger(int a, int b) {
		if(a>b)
			return a;
		else
			return b;

	}

	private AVLNode singleRightRotation(AVLNode node) {
		AVLNode temp =node.left;
		node.left=temp.right;
		temp.right=node;
		node.height=larger(heightOfSubtree(node.left),heightOfSubtree(node.right))+1;
		temp.height=larger(heightOfSubtree(temp.left),temp.height)+1;
		return temp;
	}

	private AVLNode singleLeftRotation(AVLNode node) {
		AVLNode temp =node.right;
		node.right=temp.left;
		temp.left=node;
		node.height=larger(heightOfSubtree(node.left),heightOfSubtree(node.right))+1;
		temp.height=larger(heightOfSubtree(temp.left),node.height)+1;
		return temp;
	}

	private AVLNode doubleRightRotation(AVLNode node) {
		return singleRightRotation(node);
	}

	private AVLNode doubleLeftRotation(AVLNode node) {
		node.right=singleRightRotation(node.right);
		return singleLeftRotation(node);
	}

	public int heightOfTree(AVLNode avlroot) {
		// TODO Auto-generated method stub
		if(avlroot==null)
			return 0;
		else {
			int lheight=heightOfTree(avlroot.left);
			int rheight=heightOfTree(avlroot.right);
			if(lheight<rheight)
				return rheight+1;
			else
				return lheight+1;
		}
	}

	public boolean searchNode(int searchNo,AVLNode avlroot) {
		// TODO Auto-generated method stub
		if(avlroot==null)
			return false;
		if(avlroot.data==searchNo)
			return true;
		else {
			if(searchNo<avlroot.data)
				return searchNode(searchNo,avlroot.left);
			else
				return searchNode(searchNo, avlroot.right);
		}
	}

	public int largestElement(AVLNode root) {
		// TODO Auto-generated method stub
		if(root==null)
			return 0;
		else {
			while(root.right!=null)
				root=root.right;
			return root.data;
		}
	}

	public void levelOrder(AVLNode root) {
		// TODO Auto-generated method stub
		for (int i=0; i<=4; i++) { 
			System.out.println();  
			printElementsOnALevel(root,i);
		}
	}

	void printElementsOnALevel (AVLNode node ,int level)  {
		if (node == null) 
			return;   
		if (level == 0) 
			System.out.print(node.data + " ");       
		else if (level > 0){    
			printElementsOnALevel(node.left, level-1);  
			printElementsOnALevel(node.right, level-1); 
		}
	}
	public void inorderTraversal(AVLNode avlroot) {
		// TODO Auto-generated method stub
		if(avlroot!=null){
			inorderTraversal(avlroot.left);
			System.out.print(" "+avlroot.data);   
			inorderTraversal(avlroot.right);  
		}   

	}

}

