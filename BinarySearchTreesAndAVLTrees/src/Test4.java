import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Test4 {
	public static void main(String[] args) throws NumberFormatException, IOException {
		// TODO Auto-generated method stub

		System.out.println("Enter Number :");
		BufferedReader br= new BufferedReader(new InputStreamReader(System.in));
		int num = Integer.parseInt(br.readLine());
		int fact=1;
		
		double starttime=System.nanoTime();
		for(int i=num;i>0;i--) {
			fact=fact*i;
		}
		double endtime=System.nanoTime();
		System.out.println(fact+"\nTime for Iteration :"+(endtime-starttime));

		starttime=System.nanoTime();
		System.out.println(factorial(num));
		endtime=System.nanoTime();
		System.out.println("\nTime for Recursion :"+(endtime-starttime));
	}
	
	public static int factorial(int num)
	{
		if(num==1)
			return 1;
		else
			return num*factorial(num-1);
	}
}