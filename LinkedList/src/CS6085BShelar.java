import java.util.*;
public class CS6085BShelar {

		public static void main(String[] args){
		int ans = 1;
		//Print my information
		myInfo();

		LinkedList<Integer> output= new LinkedList<Integer>();
		Scanner scanner = new Scanner(System.in);
		do {
			ans = 0;
			System.out.print("\n\nEnter the number for finding Factorial : ");
			int num = scanner.nextInt();
			output.clear();
			output.add(new Integer(1));
			for(int i=2;i<=num;i++)
				output=multiplication(i,output);
			System.out.println("Factorial of "+num+" is " + output);
			System.out.println("\nDo you want to continue (use 1 for repeat and 0 to stop) : ");
			ans = scanner.nextInt();

		}while(ans==1);
	}

	private static void myInfo() {
		// TODO Auto-generated method stub
		Date today = new Date();
		System.out.println("Name : Tejasvini Shelar");
		System.out.println("Course Number : CS608 ");
		System.out.println("Date : "+today.toString());
	}

	public static LinkedList<Integer> multiplication(int n, LinkedList lnum) {
		LinkedList<Integer> sum=new LinkedList<Integer>();
		LinkedList<Integer> result=new LinkedList<Integer>();
		Object digit;

		int lastlnum = lnum.size() -1;
		if(lastlnum == (-1)){
			System.exit(0);
		}

		sum=lnum;
		for(int count=1;count<n;count++){ 					
			result=new LinkedList<Integer>();
			int sumindex=sum.size()-1; 					
			int carry =0;
			for(int lindex=lastlnum;lindex>=0;lindex--){		

				digit=lnum.get(lindex);
				int x=((Integer)(digit)).intValue();
				digit=sum.get(sumindex--); 					//access corresponding digit in sum
				int y= ((Integer)(digit)).intValue();
				int z=x+y+carry; 							//add 2 digits and previous carry, call it z
				result.addFirst(new Integer(z%10)); 		//add (0-9) part of z
				if(z>9) carry=1;
				else carry=0; 				//see if a carry occurs
			}

			/* 	when we get here, we have added lnum to sum
 				now we have to do two things:
 				1) copy the rest of the sum int the result as sum must be equal
 				or longer than result so far

 				2) propagate any carry=1 through the remaining digits:
 				e.g. 9999 + 1 = 100000 (need to modify each place possibly) */

			if(carry==1 && sumindex<0)
				result.addFirst(new Integer(1));
			else if(sumindex>=0 && carry==0)
				while(sumindex>=0){
					digit=sum.get(sumindex--);
					int y=((Integer)(digit)).intValue();
					result.addFirst(new Integer(y));
				} else if(sumindex>=0 &&carry==1){
					while(sumindex>=0){
						digit=sum.get(sumindex--);
						int y=((Integer)(digit)).intValue();
						result.addFirst(new Integer((y+carry)%10));
						if((y+carry)>9) carry=1; else carry=0;
					}
					if (carry==1)
						result.addFirst(new Integer(1));
				}
			sum=result; 
		}
		return sum;
	}

}