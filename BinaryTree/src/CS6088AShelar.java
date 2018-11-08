import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Date;

public class CS6088AShelar {

	static Lab8BTMethods lab = new Lab8BTMethods();
	static int array[] ;
	static int counter=0;

	public static void main(String[] args) throws NumberFormatException, IOException {
		// TODO Auto-generated method stub
		myInfo();
		lab.createBinaryTree();
		array = new int[numberOfNodes(lab.root)];
		System.out.println();
		System.out.println("Pre Order Travesal");
		lab.preOrder(lab.root);
		System.out.println("\n");
		System.out.println("Height of the Tree : "+lab.height(lab.root));
		System.out.print("\nThe Level Order of the Tree");
		lab.displayTree(lab.root);
		System.out.println("\n");
		System.out.println("Number of nodes in the tree : "+numberOfNodes(lab.root));
		System.out.println("\nLargest Value in the tree : "+largestNode(lab.root));
	}

	static int numberOfNodes(Lab8BTNode root){
		if(root == null)
			return 0;
		else 
			return(numberOfNodes(root.right) + numberOfNodes(root.left) + 1);
	}
	
	static int largestNode(Lab8BTNode root){
		int temp = 0;
		toArray(root);
		for (int i =0; i<array.length; i++) {
			if(array[i]>temp){
				temp = array[i];
			}
		}
		return temp;
	}
	
	private static void toArray(Lab8BTNode root) {
		// TODO Auto-generated method stub
		if(root!=null)
		{
			array[counter++] = root.element;
			largestNode(root.left);
			largestNode(root.right);
		}
	}

	private static void myInfo() {
		// TODO Auto-generated method stub
		Date today = new Date();
		System.out.println("Name : Tejasvini Shelar");
		System.out.println("Course Number : CS608 ");
		System.out.println("Date : "+today.toString());
	}


}
