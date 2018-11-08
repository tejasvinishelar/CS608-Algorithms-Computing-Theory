import java.util.*;
import java.io.*;

class CS6086AShelarManualHashmap {
	public static void main(String[] args) throws IOException{
		myInfo();
		infixtopostfix();
	}

	private static void myInfo() {
		// TODO Auto-generated method stub
		Date today = new Date();
		System.out.println("Name : Tejasvini Shelar");
		System.out.println("Course Number : CS608 ");
		System.out.println("Date : "+today.toString());
	}

	private static void infixtopostfix() throws FileNotFoundException {

		String infixExpression = "";
		myStack operatorsStack = new myStack(20);

		File f1 = new File("C:\\Users\\TejasviniAditya\\Documents\\Java\\Assignment6\\src\\inputData6A.txt");
		if(!f1.exists())
			System.out.println("input file not found");

		Scanner sc = new Scanner(f1);
		while (sc.hasNextLine())
		{		
			infixExpression = sc.nextLine();
			String output = "";
			for (int i = 0; i<infixExpression.length(); i++) {
				char currentChar = infixExpression.charAt(i);
				if(currentChar >= 'a' && currentChar <= 'z'){
					output += currentChar;
				}else{
					switch(currentChar){
					case '+' :
					case '-' :
						if(operatorsStack.isEmpty())
							operatorsStack.push(currentChar);
						else{
							while(!operatorsStack.isEmpty())
								output += (char) operatorsStack.pop();
							operatorsStack.push(currentChar);
						}
						break;
					case '*' :
					case '/' :
						if(operatorsStack.isEmpty() || operatorsStack.topElement() == '+' || operatorsStack.topElement() == '-')
							operatorsStack.push(currentChar);
						else{
							while(!operatorsStack.isEmpty() && operatorsStack.topElement() != '+' && operatorsStack.topElement() != '-')
								output += (char)operatorsStack.pop();
							operatorsStack.push(currentChar);
						}
						break;
					case '^' :
						if(operatorsStack.isEmpty() || operatorsStack.topElement() == '+' || operatorsStack.topElement() == '-'|| operatorsStack.topElement() == '*')
							operatorsStack.push(currentChar);
						else{
							while(!operatorsStack.isEmpty() && operatorsStack.topElement() != '+' && operatorsStack.topElement() != '-' && operatorsStack.topElement() == '*')
								output += (char)operatorsStack.pop();
							operatorsStack.push(currentChar);
						}
						break;
					case ' ' : break;
					}
				}
			}
			while(!operatorsStack.isEmpty())
				output += (char)operatorsStack.pop();
			System.out.println("\nThe Given Infix Expression: "+ infixExpression);
			System.out.println("Converted Postfix Expression: " + output +"\n");
		}

	}
}
class myStack{
	private int top;
	private char[] operatorsArray;

	public myStack(int size){
		operatorsArray = new char[size];
		top =- 1;
	}
	public void push(char op){
		top++;
		operatorsArray[top] = op;
	}
	public char pop(){
		return operatorsArray[top--];
	}
	int topElement(){
		return operatorsArray[top];
	}
	public boolean isEmpty(){
		return (top == -1);
	}
}
