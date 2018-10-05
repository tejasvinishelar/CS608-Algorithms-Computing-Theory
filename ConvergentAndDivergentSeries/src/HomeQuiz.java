
public class HomeQuiz {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		int sum = 0;
		sum = sigma(5);
		System.out.println(sum);
		/*for(int i=2;i<=21;i++) {
			sum+=i;
		}*/
		//System.out.println(sum/2);
	}

	private static int sigma(int n) {
		// TODO Auto-generated method stub
		if(n==1)
		return 1;
		else
			return n+sigma(n-1);
		
	}

	
}
