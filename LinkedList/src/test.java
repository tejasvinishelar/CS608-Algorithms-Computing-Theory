
public class test {

	public static void main(String[] args) {
        int arr[] = {4,3,1,2};
    	int res = minimumSwaps(arr);
    	System.out.println(res);
    }
    // Complete the minimumSwaps function below.
    static int minimumSwaps(int[] arr) {
    	int minSwap = 0;
        int hp =0 ;
        int postion = arr.length; 

           while(postion > 0){
               //check each number and swap if the no muched
               if(arr[postion-1] == postion){
                   // go to the next postion becaus the postion did not changed
                   postion--;
               }else {
                   // itterate and find where the number is and check the second number

                   // find the postion 
                      hp = postion;
                     int brk = 1;

                   while(hp > 0 && brk != 0){
                       if(arr[hp-1] == postion){
                           // swap
                           int temp = arr[postion-1];
                           arr[postion-1] = arr[hp-1];
                           arr[hp-1] = temp;
                           brk = 0;
                       }else{
                           hp--;
                       }                    
                   }               
                   // count the swap 
                   minSwap++;
                   // postion decrement
                   postion--;
               }
           }
         return minSwap;

    }


    
}
