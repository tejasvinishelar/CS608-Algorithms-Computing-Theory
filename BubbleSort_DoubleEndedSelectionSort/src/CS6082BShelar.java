import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

public class CS6082BShelar {

	static int[] selectionSortList;
	static int[] bubbleSortList;
	static int[] dblendSortList;
	static int i,j,temp,length;

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		myInfo();
		
		List<Integer> unsortedInputList;
		long startTime,endTime;
		int count;

		//Read Elements from file
		unsortedInputList = readUnsortedListFromFile();
		length = unsortedInputList.size();

		//Convert List to array
		selectionSortList = new int[unsortedInputList.size()];
		bubbleSortList = new int[unsortedInputList.size()];
		dblendSortList = new int[unsortedInputList.size()];

		for(i=0;i<length;i++) {
			selectionSortList[i] = unsortedInputList.get(i);
			bubbleSortList[i] = unsortedInputList.get(i);
			dblendSortList[i] = unsortedInputList.get(i);
		}                                                                                               

		
		//**** Sorting using BUBBLE SORT
		startTime = System.nanoTime();
		count=bubbleSort(bubbleSortList);
		endTime = System.nanoTime();
		System.out.println("Time taken for Bubble Sort : "+(endTime-startTime));
		System.out.println("Number of Comparisons for Bubble Sort : "+count);
		
		
		//**** Sorting using SELECTION SORT
		startTime = System.nanoTime();
		count=selectionSort(selectionSortList);
		endTime = System.nanoTime();
		System.out.println("Time taken for Selection Sort : "+(endTime-startTime));
		System.out.println("Number of Comparisons for Selection Sort : "+count);
		
		
		//**** Sorting using DOUBLE-ENDED SELECTION SORT
		startTime = System.nanoTime();
		count=doubleEndedSelectionSort(dblendSortList);
		endTime = System.nanoTime();
		System.out.println("Time taken for Double Ended Selection Sort : "+(endTime-startTime));
		System.out.println("Number of Comparisons for Double Ended Selection Sort : "+count);
		
		
		//**** Use sort() function
		startTime = System.nanoTime();
		Collections.sort(unsortedInputList); 
		endTime = System.nanoTime();
		System.out.println("\n\nResult after sort() function :\t"+unsortedInputList.get(100)+"\t"+unsortedInputList.get(125)+"\t"+unsortedInputList.get(250)+"\t"+unsortedInputList.get(700)+"\t"+unsortedInputList.get(900));
		System.out.println("Time taken for sort() function : "+(endTime-startTime));
		
		
	}		//End of main()

	private static int doubleEndedSelectionSort(int[] dblendSortList2) {
		// TODO Auto-generated method stub
		int count = 0;
		for(j = 0 ; j < length/2; j++)
		{

			//Step 1: Pairwise sorting
			for(i = j; i < (length/2); i++)
			{
				if(dblendSortList[i] > dblendSortList[length-i-1])
				{
					count++;
					temp = dblendSortList[i];
					dblendSortList[i] = dblendSortList[length-i-1];
					dblendSortList[length-i-1] = temp;

				}
			}

			//Step 2: Finding the smallest element
			int small = j;
			for(i = small; i <=length/2; i++)
			{
				if(dblendSortList[i]<dblendSortList[small])
				{
					small=i;
					count++;
				}
			}
			temp = dblendSortList[small];
			dblendSortList[small] = dblendSortList[j];
			dblendSortList[j] = temp;

			//Step 3: Finding the largest element
			int max = dblendSortList.length/2;

			for(i = dblendSortList.length/2 ; i < length-j; i++)
			{
				if(dblendSortList[i]>dblendSortList[max])
				{
					max=i;
					count++;

				}
			}
			temp = dblendSortList[max];
			dblendSortList[max] = dblendSortList[length-j-1];
			dblendSortList[length-j-1] = temp;

			//for middle two elements
			if(dblendSortList[(length-1)/2]>dblendSortList[((length-1)/2)+1])
			{
				count++;
				temp = dblendSortList[(length-1)/2];
				dblendSortList[(length-1)/2] = dblendSortList[((length-1)/2)+1];
				dblendSortList[((length-1)/2)+1] = temp;
			}
		}
		System.out.println("\n\nResult after Double-Ended Selection Sort :\t"+dblendSortList[100]+"\t"+dblendSortList[125]+"\t"+dblendSortList[250]+"\t"+dblendSortList[700]+"\t"+dblendSortList[900]);
		return count;

		
	}

	private static int selectionSort(int[] selectionSortList2) {
		// TODO Auto-generated method stub
		int count = 0;
		for(i=0;i<length-1;i++) {
			for(j=i+1;j<length;j++) {
				if(selectionSortList[j]<selectionSortList[i]) {
					count++;
					temp = selectionSortList[i];
					selectionSortList[i] = selectionSortList[j];
					selectionSortList[j] = temp;
				}
			}
		}
		System.out.println("\n\nResult after Selection Sort :\t"+selectionSortList[100]+"\t"+selectionSortList[125]+"\t"+selectionSortList[250]+"\t"+selectionSortList[700]+"\t"+selectionSortList[900]);
		return count;
	}

	private static void myInfo() {
		// TODO Auto-generated method stub
		Date today = new Date();
		System.out.println("Name : Tejasvini Shelar");
		System.out.println("Course Number : CS608 ");
		System.out.println("Date : "+today.toString());
	}

	private static int bubbleSort(int[] bubbleSortList) {
		// TODO Auto-generated method stub
		int count = 0;
		for(i=0;i<length-1;i++) {
			for(j=0;j<length-i-1;j++) {
				if(bubbleSortList[j]>bubbleSortList[j+1]) {
					count++;
					temp = bubbleSortList[j];
					bubbleSortList[j] = bubbleSortList[j+1];
					bubbleSortList[j+1] = temp;
					//swap(bubbleSortList[j],bubbleSortList[j+1]);
				}
			}
		}
		System.out.println("\n\nResult after Bubble Sort :\t"+bubbleSortList[100]+"\t"+bubbleSortList[125]+"\t"+bubbleSortList[250]+"\t"+bubbleSortList[700]+"\t"+bubbleSortList[900]);
		return count;
	}

	//**** read file of integers and return list of unsorted elements
	static List<Integer> readUnsortedListFromFile() {
		List<Integer> unsortedList = new ArrayList<>();
		try {

			FileInputStream istream = new FileInputStream("inputData2B.txt");
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



}
