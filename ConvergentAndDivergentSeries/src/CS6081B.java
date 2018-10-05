import java.util.Date;

public class CS6081B {
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		Date today = new Date();			//Use of Date Class
		System.out.println("Name : TEJASVINI SHELAR");
		System.out.println("Course Number : CS608");
		System.out.println("Date : "+today.toString());			// Printing date
		
		float sumOfterms[]= {0,0,0,0,0,0,0,0,0,0}, difference=0; 
		int i=0,j=0,k=0;
		
		//series =  1- 1/3 + 1/5 - 1/7 +1/9  ... 

		for(i=1000;i<=10000;i+=1000) {	
			
			for(j=1;j<=i;j+=2) {	
				sumOfterms[k]=(float) (sumOfterms[k] + java.lang.Math.pow(j, -1));
				if(j<i) {
				j+=2;
				sumOfterms[k]=(float) (sumOfterms[k] - java.lang.Math.pow(j, -1));	
				}
			}
			
			System.out.println("\nSum of "+ (j-1) + " terms after multiplying by 4 : "+sumOfterms[k]*4 );
			k++;
		}
		
		//Printing Difference between successive sums
		System.out.println("\nDifference between successive sum values : ");
		for(i=1;i<sumOfterms.length;i++) {	
			
			difference = (float) (sumOfterms[i] - sumOfterms[i-1]);			
			System.out.println(difference);	
		}
		
		//Printing Conclusion
		System.out.println("\t\t **CONCLUSION ** \n\n The Difference between successive sum values is less than .0001 \n\n Therefore the given series is likely convergent ");
		
	}


}
