import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Date;
import java.util.List;
import java.util.Scanner;
import java.util.StringTokenizer;

public class CS6088BShelar {

	static Lab8BTMethods lab = new Lab8BTMethods();
	static int array[] ;
	static int counter=0;
	static int level = 0;
	static int right = 0;
	static int left  = 0;
	static int numberOfNodesInLevel = 0;
	static int levelWithMaxNodes = -1;
	static File f1;
	static PrintWriter pw;

	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		myInfo();
		lab.createBinaryTree();
		array = new int[numberOfNodes(lab.root)];
		System.out.println();
		System.out.println("Pre Order Travesal");
		lab.preOrder(lab.root);
		System.out.println("\n");
		System.out.println("Height of the Tree = "+lab.height(lab.root));
		System.out.print("\nThe Level Order of the Tree");
		lab.displayTree(lab.root);
		System.out.println("\n");
		System.out.println("Number of nodes in the tree : "+numberOfNodes(lab.root));
		System.out.println("\nLargest Value in the tree : "+largestNode(lab.root));
		System.out.println("\nSum of Elements in the tree : "+sumOfElements(lab.root));
		System.out.println("Enter Node to search : ");
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int searchno = Integer.parseInt(br.readLine());
		if(searchFor(searchno))
			System.out.println(searchno+" is available in the tree");
		else
			System.out.println(searchno+" Not available in the tree");

		levelWithLargestNumberOfNodes(lab.root);
	}
	private static boolean searchFor(int searchno) {
		// TODO Auto-generated method stub
		for (int i =0; i<array.length; i++)
			if(array[i]==searchno)
				return true;
		return false;
	}

	private static int sumOfElements(Lab8BTNode root) {
		// TODO Auto-generated method stub
		int sum = 0;

		for(int i = 0;i<array.length;i++) {
			sum =sum + array[i];
		}
		return sum;
	}

	static int numberOfNodes(Lab8BTNode root){
		if(root == null)
			return 0;
		else 
			return(numberOfNodes(root.right) + numberOfNodes(root.left) + 1);
	}

	static int largestNode(Lab8BTNode root){
		int temp = 0;
		if(root!=null)
		{
			array[counter++] = root.element;
			largestNode(root.left);
			largestNode(root.right);
		}
		for (int i =0; i<array.length; i++) {
			if(array[i]>temp){
				temp = array[i];
			}
		}
		return temp;
	}
	private static void myInfo() {
		// TODO Auto-generated method stub
		Date today = new Date();
		System.out.println("Name : Tejasvini Shelar");
		System.out.println("Course Number : CS608 ");
		System.out.println("Date : "+today.toString());
	}

	static void levelWithLargestNumberOfNodes(Lab8BTNode root) throws Exception
	{

		f1 = new File("temp.txt");
		pw = new PrintWriter("temp.txt");
		boolean b1 = f1.createNewFile();
		if(!b1){
			for(int i = 0 ; i < (lab.height(root)); i++)
			{
				printElementsOnALevel(lab.root,i);
				pw.println();
			}
			pw.flush(); pw.close();
		}

		boolean b2 = f1.exists();

		if(b2)
		{
			Scanner sc = new Scanner(f1);
			int count = 0;
			String lev[] = new String[lab.height(root)];
			while (sc.hasNextLine())
			{
					lev[count] = sc.nextLine();
					count++;
			}
			
			int counttokens[] = new int[lab.height(root)];
			for(int i=0;i<lev.length;i++) {
				StringTokenizer tokens = new StringTokenizer(lev[i]," ");
				counttokens[i] = tokens.countTokens();
			}
			
			int maxValue = counttokens[0]; 

			for(int i=1;i < counttokens.length;i++)
			{ 
				if(counttokens[i] > maxValue)
				{ 
					maxValue = counttokens[i]; 
				}
			}
			
			System.out.println("\n\nLevels with maximum number of Nodes "+maxValue+" and nodes are as follows : " );

			for(int i=0;i<counttokens.length;i++) {
				if(maxValue == counttokens[i])
					System.out.println(lev[i]);
			}
			f1.delete();
		}

	}

	static void printElementsOnALevel(Lab8BTNode root, int lev)throws Exception
	{
		if(root == null) return;
		if(lev == 0) pw.print(root.element + " ");
		else if(lev>0){
			printElementsOnALevel(root.left, lev-1);
			printElementsOnALevel(root.right, lev-1);
		}
	}
}
