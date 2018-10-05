import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;



public class LinearSearch {

	public static void main(String[] args) throws NumberFormatException, IOException {
		// TODO Auto-generated method stub
		int intList[] = {10,20,30,40,50};
		
		System.out.printf("Enter a number to search : ");
		InputStreamReader isr = new InputStreamReader(System.in);
		BufferedReader br = new BufferedReader(isr);
		int searchNo = Integer.parseInt(br.readLine());
		
		for(int i=0;i<5;i++) {
			if(intList[i]==searchNo) {
				System.out.println(searchNo+"found at "+i+1);
			
			}
			
		}
		
		
		
		

	}

}
