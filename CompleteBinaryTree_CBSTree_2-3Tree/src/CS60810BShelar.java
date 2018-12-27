import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;


public class CS60810BShelar {

	static BSTNode bstroot;
	static AVLNode avlroot;
	static AVLTree avltree =new AVLTree();
	static BSTTree bsttree =new BSTTree();
	static double BSTTreeBuildTime;
	static double AVLTreeBuildTime;
	static double CBSTreeBuildTime;
	static double starttime;
	static double endtime;
	static int CBSTree[];
	static int subLast;

	public static void main(String[] args) throws NumberFormatException, IOException {
		// TODO Auto-generated method stub
		myInfo();
		int inputData[];
		inputData=readFile("C:\\Users\\TejasviniAditya\\Documents\\Java\\CompleteBinaryTree_CBSTree_2-3Tree\\inputDataSample10.txt");
		starttime=System.nanoTime();
		buildBST(inputData);
		double endtime=System.nanoTime();
		BSTTreeBuildTime=endtime-starttime;

		starttime=System.nanoTime();
		buildAVL(inputData);
		endtime=System.nanoTime();
		AVLTreeBuildTime=endtime-starttime;

		starttime=System.nanoTime();
		buildCBSTree(inputData);
		System.out.println();
		//inorderTraverse(1);
		/*for(int i=0;i<CBSTree.length;i++)
			System.out.println(CBSTree[i]);
		 */endtime=System.nanoTime();
		 CBSTreeBuildTime=endtime-starttime;

		 avlroot=avltree.returnroot();
		 bstroot=bsttree.returnroot();
		 performTasksOnTrees();
	}

	private static void buildCBSTree(int[] inputData) {
		// TODO Auto-generated method stub
		CBSTree=new int[inputData.length+1];
		int NP,DP,index=1,j=1,nextnodesub;
		subLast=1;
		CBSTree[1]=inputData[0];

		for(int i=2;i<inputData.length+1;i++) {
			NP=findNP(inputData[i-1],1);
			//System.out.println("New Position :"+NP);
			DP=subLast+1;
			index=getIndex(); //subscript of rightmost node of CBS tree
			//System.out.println("Index :"+index);
			subLast=subLast+1;
			if(NP==DP) {
				CBSTree[NP]=inputData[i-1];
			}else {
				if(NP<=2*index+1) {
					//situation 1 NP is on last level-- pull one step backward
					j=DP;
					while(j<=NP) {
						nextnodesub=nextinOrder(j,index,NP);
						if(nextnodesub==NP) {
							CBSTree[j]=inputData[i-1];
							break;
						}
						else
							CBSTree[j]=CBSTree[nextnodesub];
						j=nextnodesub;
					}
				}
				else {
					//situation 2 NP is on next level - Push one step forward
					j=NP;
					int newnode=inputData[i-1],temp=0;
					while(j!=DP) {
						nextnodesub=nextinOrder(j,index,NP);
						temp=CBSTree[nextnodesub];
						CBSTree[nextnodesub]=newnode;
						newnode=temp;
						j=nextnodesub;
					}
				}
			}
			//System.out.println(inputData[i-1]);
		}
	}

	private static int nextinOrder(int sub,int index,int NP) {
		// TODO Auto-generated method stub
		if(sub==index) {
			return index*2+1;
		}
		if(2*sub+1<=subLast || NP==2*sub+1) {
			sub=2*sub+1;
			while(2*sub<=subLast || 2*sub==NP)sub=sub*2;
			return sub;
		}else {
			if(sub%2==0) return sub/2;
			else {
				while(sub%2!=0) sub=sub/2;
				return sub/2;
			}
		}
	}

	private static int getIndex() {
		// TODO Auto-generated method stub
		int i=1;
		while(i*2+1<=subLast) {
			i=i*2+1;
		}
		return i;
	}

	private static int findNP(int newnode,int pos) {
		// TODO Auto-generated method stub
		int NP=0;
		if(pos>subLast)
			return pos;
		if(CBSTree[pos]>newnode) {
			NP=findNP(newnode,pos*2);
		}else {
			NP=findNP(newnode,pos*2+1);
		}
		return NP;
	}

	private static int heightOfTree(int sub) {
		// TODO Auto-generated method stub
		int i=1,j=0;
		while(subLast>i) {
			i=i*2+1;
			j++;
		}
		return j;
	}
	private static void performTasksOnTrees() throws NumberFormatException, IOException {
		// TODO Auto-generated method stub
		System.out.println("\n\nHeight Of BST tree : "+bsttree.heightOfTree(bstroot));
		System.out.println("\nHeight Of AVL tree : "+avltree.heightOfTree(avlroot));
		System.out.println("\nHeight Of CBS tree : "+heightOfTree(1));

		System.out.println("\nTime taken to build BST tree : "+BSTTreeBuildTime+" nanoseconds");
		System.out.println("\nTime taken to build AVL tree : "+AVLTreeBuildTime+" nanoseconds");
		System.out.println("\nTime taken to build CBS tree : "+CBSTreeBuildTime+" nanoseconds");

		int searchKeys[]= {250,2504,2078,2158,3502,7138,6230,9661,1330,6136};
		List<Integer> foundKeys= new ArrayList<>();
		starttime=System.nanoTime();
		int j=0;
		for(int i=0;i<searchKeys.length;i++) {
			if(bsttree.searchNode(searchKeys[i],bstroot))
				foundKeys.add(searchKeys[i]);
		}
		endtime=System.nanoTime();
		System.out.println("\nTime taken to search in BST :"+(endtime-starttime)+" nanoseconds");
		System.out.println("Keys "+foundKeys+" found in BST :");
		
		foundKeys.clear();
		starttime=System.nanoTime();
		for(int i=0;i<searchKeys.length;i++) {
			if(avltree.searchNode(searchKeys[i],avlroot))
				foundKeys.add(searchKeys[i]);
		}
		endtime=System.nanoTime();
		System.out.println("Time taken to search in AVL :"+(endtime-starttime)+" nanoseconds");
		System.out.println("Keys "+foundKeys+" found in AVL");

		foundKeys.clear();
		starttime=System.nanoTime();
		for(int i=0;i<searchKeys.length;i++) {
			if(searchNodeInCBSTree(searchKeys[i],1))
				foundKeys.add(searchKeys[i]);
		}
		endtime=System.nanoTime();
		System.out.println("Time taken to search in CBSTree :"+(endtime-starttime)+" nanoseconds");
		System.out.println("Keys "+foundKeys+" found in CBS");

	}

	private static boolean searchNodeInCBSTree(int searchkey, int j) {
		// TODO Auto-generated method stub
		if(searchkey==CBSTree[j])
			return true;
		if(j==0 || j*2>CBSTree.length)
			return false;
		else {
			if(searchkey<CBSTree[j])
				return searchNodeInCBSTree(searchkey, j*2);
			else
				return searchNodeInCBSTree(searchkey, j*2+1);
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

class BSTNode{      
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

