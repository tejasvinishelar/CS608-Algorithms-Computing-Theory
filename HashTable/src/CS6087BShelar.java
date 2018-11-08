import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedList;
import java.util.Scanner;

class Hashtable{
	int key;
	LinkedList<String> value = new LinkedList<>();

	public Hashtable() {
		// TODO Auto-generated constructor stub
	}

	public Hashtable(int key, LinkedList<String> value){
		this.key = key;
		this.value = value;
	}
}

public class CS6087BShelar {
	static Hashtable hashtable[];
	static int size = 0;

	public static void main(String[] args) throws NumberFormatException, IOException {
		// TODO Auto-generated method stub
		myInfo();

		ArrayList<String> nameofcities = new ArrayList<>(); 
		nameofcities = readFile();

		hashtable = new Hashtable[getHashSizeNumber(nameofcities.size())];
		buildHashtable(nameofcities);
		
		System.out.println("\n\nWhat is Table Size you used : "+hashtable.length);
		System.out.println("\nWhat is the Hash Code for these : ");
		System.out.println("\tBoston : "+gethashcode("Boston"));
		System.out.println("\tBrussels : "+gethashcode("Brussels"));
		System.out.println("\tPleasantville : "+gethashcode("Pleasantville"));

		System.out.println("\n\nIs Boston Found?\n "+cityfoundornot("Boston")+" Found in hashtable ");
		System.out.println("\n\nIs Brussels Found?\n "+cityfoundornot("Brussels")+" Found in hashtable ");
		System.out.println("\n\nIs Shanghai Found?\n "+cityfoundornot("Shanghai")+" Found in hashtable ");
	}

	private static int getHashSizeNumber(int size) {
		// TODO Auto-generated method stub
		int num = size*2+1;
		int primeflag=0;
		for(int i =2;i<num/2;i++) {
			if(num%i == 0) {
				num++;
				primeflag=1;
				i=1;
			}
		}
		return num;
	}

	private static String cityfoundornot(String city) {
		// TODO Auto-generated method stub
		int flag = 0;
		for(int i=0;i<hashtable.length;i++) {
			if(hashtable[i].value.contains(city)) {
				flag = 0;
				break;
			}
			else
				flag = 1;
		}
		if(flag==0)
			return "Yes";
		else
			return "NOT";
	}

	private static void buildHashtable(ArrayList<String> nameofcities) {
		// TODO Auto-generated method stub
		for(int i=0;i<hashtable.length;i++) {
			hashtable[i] = new Hashtable();
		}
		for(String value : nameofcities) {
			int key = gethashcode(value);
			hashtable[key].key = key;
			hashtable[key].value.add(value);
		}
	}

	private static int gethashcode(String value) {
		// TODO Auto-generated method stub
		int M = hashtable.length;
		long mask = 0x7ffffffffL;
		return (int) ((value.hashCode()&mask) % M);
	}


	private static ArrayList<String> readFile() throws FileNotFoundException {
		// TODO Auto-generated method stub
		File f1 = new File("inputData7B.txt");
		ArrayList<String> listofcities = new ArrayList<>();
		if(!f1.exists())
			System.out.println("input file not found");

		String line = "";
		Scanner sc = new Scanner(f1);
		while (sc.hasNextLine())
		{		
			line = sc.nextLine();
			listofcities.add(line);
		}
		return listofcities;
	}

	private static void myInfo() {
		// TODO Auto-generated method stub
		Date today = new Date();
		System.out.println("Name : Tejasvini Shelar");
		System.out.println("Course Number : CS608 ");
		System.out.println("Date : "+today.toString());
	}
}
