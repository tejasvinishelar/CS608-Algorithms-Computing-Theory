import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;


public class CS60810AShelar {

	static BSTNode root;
	static int CBT[],sub=1,subLast;
	public static void main(String[] args) throws NumberFormatException, IOException {
		// TODO Auto-generated method stub
		int searchNo=1;
		myInfo();
		int inputData[];
		inputData=readFile("inputData10.txt");
		CBT=new int[inputData.length+1];
		buildCBT(inputData);
		System.out.println("\nHeight Of the tree : "+heightOfTree(sub));
		/*System.out.println("\nInorder Traversal Of the tree : ");
		inorderTraversal(sub);
		*/System.out.println("\n\nThe Number of Nodes in the tree : "+noOfNodes(sub));
		System.out.println("\nLargest Element in the tree : "+largestElement(sub));

		while(searchNo!=0){
			System.out.println("\nEnter an element to search :");
			BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
			searchNo=Integer.parseInt(br.readLine());
			if(searchNode(searchNo,sub))
				System.out.println(searchNo+" is Found");
			else
				System.out.println(searchNo+" is not Found");
		}
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
	private static boolean searchNode(int searchNo,int sub) {
		// TODO Auto-generated method stub
		if(CBT[sub]==searchNo)
			return true;
		else {
			for(int i=1;i<=subLast;i++) {
				if(CBT[i]==searchNo)
					return true;
			}
		}
		return false;
	}

	private static int largestElement(int sub) {
		// TODO Auto-generated method stub
		int large=0;
		while(sub<=subLast) {
			if(large<CBT[sub])
				large=CBT[sub];
			sub++;
		}
		sub=1;
		return large;
	}

	private static int noOfNodes(int sub) {
		// TODO Auto-generated method stub
		return subLast; 
	}

	private static void inorderTraversal(int sub) {
		// TODO Auto-generated method stub
		if(sub<=subLast){
			inorderTraversal(2*sub);
			System.out.print(" "+CBT[sub]);   
			inorderTraversal(2*sub+1);  
		}   
	}

	private static void buildCBT(int[] inputData) {
		// TODO Auto-generated method stub
		for(int i = 1; i <= inputData.length; i++)  
			CBT[i]=inputData[i-1];
		subLast=inputData.length;
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
