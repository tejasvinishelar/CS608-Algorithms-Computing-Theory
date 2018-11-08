import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Date;
import java.util.HashMap;
import java.util.Scanner;

class hashMap{
	int ssn;
	String address;

	public hashMap() {
		// TODO Auto-generated constructor stub
	}

	public hashMap(int ssn, String address){
		this.ssn = ssn;
		this.address = address;
	}

}

public class CS6087AShelarManualHashmap {
	static hashMap hashmap[] = new hashMap[500];
	static int size = 0;

	public static void main(String[] args) throws NumberFormatException, IOException {
		// TODO Auto-generated method stub
		String keyVal[] = {};
		int key =0;
		myInfo();

		keyVal = readFile();
		System.out.println(hashmap[14].ssn);
		BufferedReader br =new BufferedReader(new InputStreamReader(System.in));
		System.out.println("1. Enter Key : ");
		key = Integer.parseInt(br.readLine());

		boolean ans = searchKey(key);
		if(ans)
			System.out.println(key+" found in hashMap");
		else
			System.out.println(key+" NOT found in hashMap");

		
		System.out.println("2. Enter Key to search its value :");
		key = Integer.parseInt(br.readLine());
		ans = searchKey(key);
		if(ans)
			System.out.println("Value of key "+key+" is "+findValue(key));
		else
			System.out.println(key+" NOT found in hashMap");

		System.out.println("Size of hashmap"+size);
		System.out.println("3. Enter Key to remove :");
		key = Integer.parseInt(br.readLine());
		ans = searchKey(key);
		if(ans)
			remove(key);
		else
			System.out.println(key+" NOT found in hashMap");

		
		System.out.println("4. Data in key - value format");
		display();
	}

	private static void display() {
		// TODO Auto-generated method stub
		for(int i=0;i<size;i++) {
			System.out.println(hashmap[i].ssn+" - "+hashmap[i].address);
		}
	}

	private static void remove(int key) {
		// TODO Auto-generated method stub
		for(int i = 0;i<size;i++) {
			if(hashmap[i].ssn==key) {
				for(int j=i;j<size;j++)
				hashmap[j] = hashmap[j+1];
				size--;
			}
		}
	}

	private static String findValue(int key) {
		// TODO Auto-generated method stub
		for(int i = 0;i<size;i++) {
			if(hashmap[i].ssn==key)
				return hashmap[i].address;
		}
		return null;
	}

	private static boolean searchKey(int key) {
		// TODO Auto-generated method stub
		for(int i = 0;i<size;i++) {
			if (hashmap[i].ssn == key)
				return true;
		}
		return false;		
	}

	private static String[] readFile() throws FileNotFoundException {
		// TODO Auto-generated method stub
		File f1 = new File("C:\\Users\\TejasviniAditya\\Documents\\Java\\Assignment7\\src\\inputData7A.txt");
		if(!f1.exists())
			System.out.println("input file not found");

		String line = "";
		String[] keyvalue = {};
		Scanner sc = new Scanner(f1);
		while (sc.hasNextLine())
		{		
			line = sc.nextLine();
			System.out.println(line);
			keyvalue = line.split(":");
			put(keyvalue,size++);
		}

		return keyvalue;
	}

	private static void put(String[] keyvalue, int i) {
		// TODO Auto-generated method stub
		hashMap keyValue = new hashMap(Integer.parseInt(keyvalue[0]),keyvalue[1]);
		hashmap[i] = keyValue;
	}

	private static void myInfo() {
		// TODO Auto-generated method stub
		Date today = new Date();
		System.out.println("Name : Tejasvini Shelar");
		System.out.println("Course Number : CS608 ");
		System.out.println("Date : "+today.toString());
	}

}
