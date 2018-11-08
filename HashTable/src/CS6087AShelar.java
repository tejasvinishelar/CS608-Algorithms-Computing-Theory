import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Date;
import java.util.HashMap;
import java.util.Scanner;

public class CS6087AShelar {

	static HashMap<String, String> hashmap = new HashMap(500);
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		// TODO Auto-generated method stub

		myInfo();
		readFile();
		BufferedReader br= new BufferedReader(new InputStreamReader(System.in));
		
		System.out.println("\n\nEnter Key to search :");
		String searchkey = br.readLine();
		if(hashmap.containsKey(searchkey))
			System.out.println(searchkey+ " key is found");
		else
			System.out.println(searchkey +" key is not found ");
		
		System.out.println("Enter key to find its value : ");
		searchkey = br.readLine();
		if(hashmap.get(searchkey) == null)
			System.out.println("Key is not present in Hashmap");
		else 
			System.out.println("Value of key "+searchkey+" is "+hashmap.get(searchkey).toString());
	
		System.out.println("Enter key to remove : ");
		searchkey = br.readLine();
		if(hashmap.get(searchkey) == null)
			System.out.println("Key is not present in Hashmap");
		else 
			System.out.println("Key is removed successfully");
	
		System.out.println("\nDisplay HashMap using println\n\n"+hashmap);
		
		System.out.println("\n\nDisplay HashMap using entryset()\n\n"+hashmap.entrySet());
	}

	private static void readFile() throws FileNotFoundException {
		// TODO Auto-generated method stub
		File f1 = new File("inputData7A.txt");
		if(!f1.exists())
			System.out.println("input file not found");

		String line = "";
		String[] keyvalue = {};
		Scanner sc = new Scanner(f1);
		while (sc.hasNextLine())
		{		
			line = sc.nextLine();
			keyvalue = line.split(":");
			hashmap.put(keyvalue[0], keyvalue[1]);
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
