import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

public class CS6084AShelar {

	static int partitionCount = 0;
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		myInfo();

		int i;
		List<Integer> unsortedInputList;

		unsortedInputList = readUnsortedListFromFile();

		//Convert leftist to array
		int[] A = new int[unsortedInputList.size()];
		for(i=0;i<unsortedInputList.size();i++) {
			A[i] = unsortedInputList.get(i);
		}

		int n = A.length;
		quicksort(0,n-1,A);
		System.out.print("\nElements of Sorted List A[10] A[25] A[50] A[70] A[90] After Quick Sort : "+A[10]+" "+A[25]+" "+A[50]+" "+A[70]+" "+A[90]);
		System.out.println("\n\nNumber of times partition mathod called : "+partitionCount);
		//**** Sorting using sort() function on unsorted list
		Collections.sort(unsortedInputList); 	
		System.out.print("\nElements of Sorted List A[10] A[25] A[50] A[70] A[90] by sort() function : ");

		System.out.print(unsortedInputList.get(10)+" "+unsortedInputList.get(25)+" "+unsortedInputList.get(50)+" "+unsortedInputList.get(70)+" "+unsortedInputList.get(90));

	}

	private static void myInfo() {
		// TODO Auto-generated method stub
		Date today = new Date();
		System.out.println("Name : Tejasvini Shelar");
		System.out.println("Course Number : CS608 ");
		System.out.println("Date : "+today.toString());
	}

	//**** read file of integers and return list of unsorted elements
	static List<Integer> readUnsortedListFromFile() {
		List<Integer> unsortedList = new ArrayList<>();
		try {

			FileInputStream istream = new FileInputStream("inputData4A.txt");
			Scanner input = new Scanner(istream);
			while(input.hasNextInt()) {
				unsortedList.add(input.nextInt());	
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		return unsortedList;
	}

	static int partition(int left, int right, int [] a){
		partitionCount++;
		int pivot = a[left];
		int up=left;
		int down = right;
		while(up<down){
			while(up<down && a[down]>pivot)
				down--;
			a[up] = a[down];
			while(up<down && a[up]<=pivot)
				up++;
			a[down]= a[up];
		}
		a[up] = pivot; 
		return up;
	} 

	static void quicksort(int left, int right, int[] a){
		int p; 
		if (left < right){ 
			p = partition (left,right,a);
			quicksort(left,p-1,a);
			quicksort(p+1,right,a);
		}   
	}

}