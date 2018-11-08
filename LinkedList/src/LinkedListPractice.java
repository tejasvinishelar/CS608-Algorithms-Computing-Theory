
class LinkedListPractice{

	private static class Node{
		private int data;
		private Node next;

		public Node(int data,Node next) {
			this.data = data;
			this.next = next;

		}
	}

	private static Node head;

	public LinkedListPractice() {
		head = null;
	}

	public boolean isEmpty() {
		return(head==null);
	}

	public static void insertAtBeginnig(int Ele) {
		head = new Node(Ele,head);
	}

	public int getFirstElement() {
		if(head == null) {
			System.out.println("Empty Linked List");
			throw new ArrayIndexOutOfBoundsException();
		}

		return head.data;
	}

	public int removeFirstElement() {

		int temp = getFirstElement();
		head = head.next;
		return temp;
	}

	//Empties LinkedList
	public void clear() {
		head = null;
	}

	public static int llLength(Node head) {

		int length = 0;
		Node currentNode = head;

		while(currentNode!= null) {
			length++;
			currentNode = currentNode.next;
		}
		return length;
	}

	public static void display(Node head) {
		if(head == null) {
			System.out.println("Empty LL");
		}else {
			Node currentNode = head;

			while(currentNode!= null) {
				System.out.println(currentNode.data+" ");
				currentNode = currentNode.next;
			}
		}
	}

	//diplay reverse

	public static void reverseDisp(Node head) {
		if(head == null) {

		}
		else {
			Node currentNode = head;
			reverseDisp(currentNode.next);
			System.out.println(currentNode.data+" ");
		}
	}

	public static void addAtEnd(int ele) {
		if(head==null) {
			insertAtBeginnig(ele);
		}else {
			Node temp = head;
			while(temp.next!=null) {
				temp = temp.next;
			}
			temp.next = new Node(ele,null);
		}

	}

	public static int getLAstEle() {

		if(head == null) {
			System.out.println("Empty list");
		}
		Node temp = head;
		while(temp.next!=null)
			temp = temp.next;
		return temp.data;
	}

	public static boolean searchFor(int ele) {
		if(head==null) {
			System.out.println("Empty List");
		}
		Node temp = head;
		while(temp!=null) {
			if(temp.data==ele)
				return true;
			temp = temp.next;
		}
		
		return false;
		
	}
	
	public static int getEleAt(int pos) {

		if(head == null) {
			System.out.println("Empty list");
		}
		
		Node temp = head;
		int len = llLength(head);
		if(pos>len) {
			System.out.println("Out of list");
		}
		
		for(int i = 0;i<=pos-1;i++) {
			temp = temp.next;
		}
		return temp.data;
	}	
	
	
	public static void main(String arg[]) {

		LinkedListPractice list = new LinkedListPractice();
		list.insertAtBeginnig(10);
		list.insertAtBeginnig(20);
		list.insertAtBeginnig(30);
		list.insertAtBeginnig(40);
		list.insertAtBeginnig(50);
		list.addAtEnd(5);

		System.out.println("Last Ele "+getLAstEle());
		display(head);
		System.out.println("Length"+llLength(head));
		System.out.println("Search for 650 "+searchFor(650));
		System.out.println("Element at position 3 "+ getEleAt(3));
	}
}