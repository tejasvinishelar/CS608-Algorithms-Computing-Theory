import java.util.*;

import java.io.*;
public class CS6086BShelar{
	public static void main(String[] args) throws Exception {

		myInfo();

		String line;
		Double[] operators = new Double[4];

		//to read all the data
		File f1 = new File("infixData6B.txt");
		if(!f1.exists())
			System.out.println("input file not found");

		Scanner sc = new Scanner(f1);
		while (sc.hasNextLine())
		{		
			line = sc.nextLine();
			String s = sc.nextLine();
			Scanner s1 = new Scanner(s);
			for (int i=0; i<4 ; i++ ) 
			{
				operators[i] = s1.nextDouble();
			}

			System.out.println("\n\nThe Given Infix Expression : "+line);
			System.out.print("Given Values for operands : ");
			for (Double i: operators  ) 
				System.out.print (i + " ");
			line = line.replace("a",String.valueOf(operators[0]));
			line = line.replace("b",String.valueOf(operators[1]));
			line = line.replace("c",String.valueOf(operators[2]));
			line = line.replace("d",String.valueOf(operators[3]));	
			System.out.println("\nValue of the expression: "+ new EvaluateInfix().evaluate(line));
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

class EvaluateInfix
{
	public static String evaluateEquation(String e) {

		//String e = "(100* 7+(7*7 ))";
		e = e.replaceAll("\\s","");

		StringTokenizer tokens = new StringTokenizer(e, "()^*/+-", true);

		//to seperate all the operands and operators 
		ArrayList<String> l1 = new ArrayList<String>();
		while(tokens.hasMoreTokens()){
			l1.add(tokens.nextToken());
			l1.add (" ");
		}
		String[] array = new String[l1.size()];
		for (int i=0; i < array.length; i++)
			array[i] = l1.get(i);

		StringBuffer buffer = new StringBuffer();
		for (int i = 0; i < array.length; i++) {
			buffer.append(array[i]);
		}
		return buffer.toString();
	}
	public static double evaluate(String expression)
	{
		String exps = evaluateEquation(expression);
		System.out.println("\nFinal Expression: "+exps);

		Stack<Double> valueStack = new Stack<Double>();

		// Stack for Operators
		Stack<String> opStack = new Stack<String>();

		Scanner sc = new Scanner(exps);

		while (sc.hasNext())
		{
			String tkn = sc.next();

			if(tkn.equals(" "))
			{
				continue;
			}

			//number found, put int to values stack
			if(tkn.matches("\\d+\\.\\d+")||tkn.matches("\\d+"))
			{
				valueStack.push(Double.valueOf(tkn));
				//System.out.println("peek after add : "+ values.peek());
			}

			// Current token is an opening brace, push it to 'opStack'
			else if (tkn.equals("("))
				opStack.push(tkn);

			// Closing brace encountered, solve entire brace
			else if (tkn.equals(")"))
			{
				while (!(opStack.peek()).equals("("))
					valueStack.push(applyOp(opStack.pop(), valueStack.pop(), valueStack.pop()));
				opStack.pop();
			}

			// Current token is an operator.
			else if(tkn.equals("^") || tkn.equals("*") || tkn.equals("/") || tkn.equals("+") || tkn.equals("-"))
			{
				while (!opStack.empty() && hasPrecedence(tkn, opStack.peek()))
				{
					valueStack.push(applyOp(opStack.pop(), valueStack.pop(), valueStack.pop()));
				}


				// Push current token to operator stack.
				opStack.push(tkn);
			}
		}
		while (!opStack.empty())
			valueStack.push(applyOp(opStack.pop(), valueStack.pop(), valueStack.pop()));

		// Top of 'values' contains result, return it
		return valueStack.pop();

	}

	public static boolean hasPrecedence(String op1, String op2)
	{
		if (op2.equals("(") || op2.equals(")"))
			return false;
		if ( (op1.equals("^")) && (op2.equals("+")|| op2.equals("-")))
			return false;
		if ( (op1.equals("^") ) && (op2.equals("*")|| op2.equals("/")))
			return false;
		if ( (op1.equals("*")) && (op2.equals("+")|| op2.equals("-")))
			return false;
		if ( (op1.equals("/")) && (op2.equals("+")|| op2.equals("-")))
			return false;
		else
			return true;
	}

	public static double applyOp(String op, double b, double a)
	{
		switch (op)
		{
		case "^":
			return Math.pow(a,b);
		case "+":
			return a + b;
		case "-":
			return a - b;
		case "*":
			return a * b;
		case "/":
			if (b == 0)
				throw new
				UnsupportedOperationException("Cannot divide by zero");
			return a / b;
		}
		return 0;
	}
}

