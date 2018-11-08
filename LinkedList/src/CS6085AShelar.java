import java.io.*;
import java.util.*;

public class CS6085AShelar {

	static int partitionCount = 0;
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		myInfo();
		new MyLinkedList().performOperations();

	}

	private static void myInfo() {
		// TODO Auto-generated method stub
		Date today = new Date();
		System.out.println("Name : Tejasvini Shelar");
		System.out.println("Course Number : CS608 ");
		System.out.println("Date : "+today.toString());
	}
}

class ReadInput{

	ArrayList<Integer> l1 = new ArrayList<Integer>();
	int insertAtBeginY=-1, insertAtLastZ=-1, searchElementX=-1;

	public  void takeInput(String filename) throws IOException
	{
		FileInputStream istream = new FileInputStream(filename);
		Scanner sc = new Scanner(istream);
		int lineNumber=0;

		while (sc.hasNextLine() && lineNumber < 4)
		{
			String line = sc.nextLine();
			Scanner lineReader = new Scanner(line);

			if (lineNumber==0){//for reading the array
				while(lineReader.hasNextInt())
					l1.add(lineReader.nextInt());
				removeLast();// because the zero isnt included in the input
			}

			if (lineNumber==1)//takes the search element from the file
				searchElementX = lineReader.nextInt();

			if (lineNumber==2)//takes the element to be inserted in the begining
				insertAtBeginY = lineReader.nextInt();

			if (lineNumber==3)//takes the element to be inserted in the last
				insertAtLastZ = lineReader.nextInt();

			lineNumber++;
		}
	}

	public int[] getInputArray()
	{ 
		int[] array = new int[l1.size()];
		for (int i=0; i < array.length; i++)
		{
			array[i] = l1.get(i).intValue();
		}

		return array;		
	}

	//getters method to get the elements
	public int getInsertAtBeginningElement()
	{
		return insertAtBeginY;
	}
	public int getInsertAtLastElement()
	{
		return insertAtLastZ;
	}
	public int getSearchElement()
	{
		return searchElementX;
	}
	public  void removeLast()
	{
		l1.remove(l1.size()-1);
	}

}

class MyLinkedList{

	private class Node 
	{
		private int data; 
		private Node next;

		public Node(int data, Node next) 
		{
			this.data = data;
			this.next = next; 
		}
	}

	private Node head;

	// Constructs an empty list
	public MyLinkedList() 
	{
		head = null; 
	}

	// Returns true if the list is empty otherwise returns false
	public boolean isEmpty() 
	{
		return (head == null); 
	}

	// Inserts a new node at the beginning of this list.	
	public void insertAtBeginning(int element) 
	{
		head = new Node(element, head); 
	}

	// Returns the first element in the list.
	public int getFirstElement() 
	{
		if(head == null) 
		{
			System.out.println("Empty linked list"); 
			throw new IndexOutOfBoundsException();
		}
		return head.data; 
	}

	// Removes the first node in the list.
	public int removeFirstNode() 
	{ 
		int tmp = getFirstElement(); 
		head = head.next;
		return tmp;
	}

	// Empties linked list 
	public void clear() 
	{
		head = null; 
	}

	// Returns the length of the linked list
	public static int LLlength(Node head)
	{ 
		int length = 0;
		Node currentNode = head; 
		while(currentNode != null)
		{
			length++;
			currentNode = currentNode.next; 
		}
		return length; 
	}
	// Displays the linked list elements
	public static void display(Node head)
	{ 
		if(head == null) 
		{
			System.out.println("Empty linked list");
			throw new IndexOutOfBoundsException(); 
		}

		Node currentNode = head; 

		while(currentNode != null)
		{
			System.out.print(currentNode.data+" ");
			currentNode = currentNode.next; 
		}
		System.out.println("\n");
	}

	// Displays the linked list elements in reverse order 
	public static void displayReverse(Node head)
	{
		if(head == null){} 
		else
		{
			Node currentNode = head; 
			displayReverse(currentNode.next); 
			System.out.print(currentNode.data+" ");
		} 
	}

	//Displays the linked list's last element
	public static int getLastElement(Node head)
	{
		Node currentNode = head; 

		while(currentNode.next != null)
		{
			currentNode = currentNode.next; 
		}
		return currentNode.data;
	}

	//inserts the element at last
	public static void insertAtLast(Node head,int element)
	{
		Node newNode= new MyLinkedList().new Node(element,null);
		Node temp = head;
		while(temp.next != null)
		{
			temp = temp.next; 
		}
		temp.next = newNode;
		//return head;

	}
	//Tells if a specific element is in the Linked List or not
	public static boolean searchFor(Node head, int element)
	{
		Node currentNode = head; 
		boolean flag = false;
		while(currentNode != null)
		{
			if (currentNode.data == element)
			{
				flag = true;
				break;
			} 
			currentNode = currentNode.next;
		}
		return flag;
	}

	public void performOperations() throws IOException
	{
		ReadInput myinput  = new ReadInput();
		myinput.takeInput("dataForLinkedList.txt");
		int inputList[] = myinput.getInputArray();
		MyLinkedList list = new MyLinkedList();

		for(int a  = inputList.length-1; a >= 0 ; a--)
		{
			list.insertAtBeginning(inputList[a]);
		}

		System.out.print("\nThe Linked List : ");
		display(list.head);
		System.out.println("\nThe Size of the Linked List : " + LLlength(list.head));
		System.out.println("\nSearching For " + myinput.getSearchElement() +" : "+ searchFor(list.head , myinput.getSearchElement()));
		System.out.println("\nThe Last Element of the Linked List : "+ getLastElement(list.head));
		System.out.print("\nInserting "+ myinput.getInsertAtBeginningElement()+" At the beginning. ");
		list.insertAtBeginning(myinput.getInsertAtBeginningElement());
		display(list.head);
		System.out.print("\nInserting "+ myinput.getInsertAtLastElement()+" At the last. ");
		MyLinkedList.insertAtLast(list.head , myinput.getInsertAtLastElement());
		display(list.head);
		System.out.println("\n");

	} 
}