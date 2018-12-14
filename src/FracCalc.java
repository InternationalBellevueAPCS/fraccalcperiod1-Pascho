import java.util.*;
public class FracCalc {
    /**
     * Prompts user for input, passes that input to produceAnswer, then outputs the result.
     * @param args - unused
     */
    public static void main(String[] args) 
    {
        // TODO: Read the input from the user and call produceAnswer with an equation
        // Checkpoint 1: Create a Scanner, read one line of input, pass that input to produceAnswer, print the result.
        // Checkpoint 2: Accept user input multiple times.
    	Scanner console = new Scanner(System.in);
    	String input = " ";
    	while (!(input.equals("quit"))) {
    		System.out.println("Enter equation:");
    		input = console.nextLine();
    		String y =produceAnswer(input);
    		System.out.println(y);	
    	}
    }
    /**
     * produceAnswer - This function takes a String 'input' and produces the result.
     * @param input - A fraction string that needs to be evaluated.  For your program, this will be the user input.
     *      Example: input ==> "1/2 + 3/4"
     * @return the result of the fraction after it has been calculated.
     *      Example: return ==> "1_1/4"
     */
    // TODO: Implement this function to produce the solution to the input
    // Checkpoint 1: Return the second operand.  Example "4/5 * 1_2/4" returns "1_2/4".
    // Checkpoint 2: Return the second operand as a string representing each part.
    //               Example "4/5 * 1_2/4" returns "whole:1 numerator:2 denominator:4".
    // Checkpoint 3: Evaluate the formula and return the result as a fraction.
    //               Example "4/5 * 1_2/4" returns "6/5".
    //               Note: Answer does not need to be reduced, but it must be correct.
    // Final project: All answers must be reduced.
    //               Example "4/5 * 1_2/4" returns "1_1/5".
    public static String produceAnswer(String input)
    { 
    	if (input.indexOf(" ")<0) {
    		System.out.println("Incorrect input");
    		return " ";
    	}
    	//--------------VARIABLE DECLARATION--------------------
    	// "x" represents the first time we encounter a space in the input, I use this to determine where the first number is.
    	int x = input.indexOf(" ");
    	// The first number starts from the first index of the string and ends at the first space
    	String first_num = input.substring(0,x);
    	// the operand is one space after the first number
    	String operator = input.substring(x+1,x+2);
    	// the second number is one space after the operand, I don't know how long this number is so I leave the substring end 'open' to make sure I don't cut it off
    	String second_num = input.substring(x+3);
    	String whole_num_1 = find_whole_number(first_num);
    	String numerator_1 = find_numerator(first_num);
    	String denominator_1 = find_denominator(first_num);
    	String whole_num_2 = find_whole_number(second_num);
    	String numerator_2 = find_numerator(second_num);
    	String denominator_2 = find_denominator(second_num);  
    	//Converting the strings to Integers makes simplifiable and reducable 
    	int wn1 = Integer.parseInt(whole_num_1);
    	int n1 = Integer.parseInt(numerator_1);
    	int d1 = Integer.parseInt(denominator_1);
    	int wn2 = Integer.parseInt(whole_num_2);
    	int n2 = Integer.parseInt(numerator_2);
    	int d2 = Integer.parseInt(denominator_2);	
//---------------**ADDITION OPERATIONS**----------------
    	if (operator.equals("+")) {	
    		if (d1 == 1 && d2 == 1) {
    			int sum = wn1+wn2;
    			String ret = Integer.toString(sum);
    			return ret;
    		}
    	// If num is a mixed number, convert to normal fraction
    		n1 = MixedToFrac(wn1,n1,d1);
			n2 = MixedToFrac(wn2,n2,d2);
    		wn1 = 0;
        	// if denominators are not the same, covert them.
        	if (d1 != d2) {
        		int temp_1 = d1;
        		int temp_2 = d2;
        		d1*=temp_2;
        		n1*=temp_2;
        		d2*=temp_1;
        		n2*=temp_1;	
        	} 
        	n1+=n2;
        	//Combine Numerator and Denominator for return
        	//If both the numerator and Denominator are 0, return 0 instead of 0 + 0 
        	if (n1 == 0 || d1 == 0) {
        		return "0";
        	//Otherwise, return the answer as normal
        	} else
        		return simplify(wn1,n1,d1);
//------------------------------------------------------------------------------------------------------------------------------------------------
    	} else if (operator.equals("-")) {
    		// If both denominators are 1, then just treat the inputs as whole numbers
    		if (d1 == 1 && d2 == 1) {
    			int sum = wn1-wn2;
    			String ret = Integer.toString(sum);
    			return ret;
    		}
    	// If num1 is a mixed number, convert to normal fraction
    		n1 = MixedToFrac(wn1,n1,d1);
			n2 = MixedToFrac(wn2,n2,d2);  	
    		wn1 = 0;
        	// if denominators are not the same, covert them.
    		// Temp variables are used so that I don't convert a number before I need to use it for other numbers
        	if (d1 != d2) {
        		int temp_1 = d1;
        		int temp_2 = d2;
        		d1*=temp_2;
        		n1*=temp_2;
        		d2*=temp_1;
        		n2*=temp_1;	
        	} 
        	n1-=n2;
        	//If the numerator is greater than the denominator, convert to a mixed fraction
    		if (Math.abs(n1)>Math.abs(d1)) {
    			return simplify(wn1,n1,d1);
    		}
    		//Method for reducing, n3 and d3 are used so that I don't change a variable I need for reduction
    		if (n1 != 0 && n2 != 0) {
    			int n3 = reduce(n1,d1);
    			int d3 = reduce(d1,n1);
            	String answer = (Integer.toString(n3) + "/" + Integer.toString(d3));
            	return answer;
    		}
        	String answer = (Integer.toString(n1) + "/" + Integer.toString(d1));
        	//If both the numerator and denominator are 0, return 0 instead of 0 - 0
        	if (n1 == 0 || d1 == 0) {
        		return "0";
        	} else
        		return answer;
    	
//------------------------------------------------------------------------------------------------------------------------------------------------        	
    	} else if (operator.equals("*")) {
    		//If both numerators are 0, multiply the whole numbers
    		if (n1 == 0 && n2 == 0) {
    			wn1 *= wn2;
    			return (Integer.toString(wn1));
    					
    		}
    		
        	// If num1 is a mixed number, convert to normal fraction 
    		n1 =MixedToFrac(wn1,n1,d1);
    		n2 =MixedToFrac(wn2,n2,d2);
        	wn1 = 0;
    		n1*=n2;
    		d1*=d2;
    		//If numerator is greater than denominator, convert to mixed number
    		if (Math.abs(n1)>Math.abs(d1)) {
    			return simplify(wn1,n1,d1);
    		}
    		//Reduce 
    		if (n1 != 0 && n2 != 0) {
    			n1 = reduce(n1,d1);
    			d1 = reduce(d1,n1);
    		}
        	String answer = (Integer.toString(n1) + "/" + Integer.toString(d1));
        	//If both the numerator and denominator are 0, return 0 instead of 0 * 0
        	if (n1 == 0 || d1 == 0) {
        		return "0";
        	} else
        		return answer;
//-------------------------------------------------------------------------------------------------------------------------------------------------
    	} else if (operator.equals("/")) {
    		if (wn1 !=0 && wn2 != 0) {
    			if (wn1 == wn2) {
    				return "1";
    			}
    		}
    		//Any number divided by 1 will be itself
    		if (wn2 == 1) {
    			return (Integer.toString(wn1));
    		} else if (wn2 == -1) {
    			wn1 *= -1;
    			return (Integer.toString(wn1));
    		}
    		//If both numbers are negative the answer will be positive
    		if (wn1 < 0 && wn2 < 0) {
    			wn1*=-1;
    			wn2*=-1;
    		}
    		//If the whole number and one of the other numbers is 0, the answer will be 0
    		if (Math.abs(wn1) == 0) {
	    		if (Math.abs(n1) == 0 || Math.abs(d1) == 0) {
	    			return "0";
	    		}
    		}
    		//Process for whole numbers
    		if (n1 == 0 && n2 == 0 && wn1 != 0 && wn2 != 0) {
    			n1 = wn1;
    			d1 = wn2;		
        		if (Math.abs(n1)>Math.abs(d1)) {
        			return simplify(wn1,n1,d1);
        		}
           		String answer = (Integer.toString(wn1)+ "/" + Integer.toString(wn2));
    			return answer;
    			// if input is 2 negative whole numbers
    		} else if (n1 == 0 && n2 == 0 && wn1 < 0 && wn2 < 0) {
    			wn2 *= -1;
    			wn1 *= -1;
        		if (Math.abs(n1)>Math.abs(d1)) {
        			return simplify(wn1,n1,d1);
        		}
    			String answer = (Integer.toString(wn1)+ "/" + Integer.toString(wn2));
    			return answer;
    		}
    		//If the fraction is a mixed number, convert it to a normal fraction
    		n1 =MixedToFrac(wn1,n1,d1);
    		n2 =MixedToFrac(wn2,n2,d2);  
        	wn1 = 0;
        	if (n2 <0) {
        		n1*=d2;
        		n1*=-1;
        		d1*=n2;
        		d1*=-1;
        	} else {
    		n1*=d2;
    		d1*=n2;
        	}
        	//Simplify
    		if (n1 != 0 && d1 !=0) {
    			return simplify(wn1,n1,d1);
    		}
			String answer = (Integer.toString(n1)+ "/" + Integer.toString(d1));
			//if the answer would be 0 / 0, return 0
    		if (n1 == 0 && d1 == 0) {
    			return "0";
    		} else 
    			return answer;
    		//This should never return but if it does my grade is going down
    	} else
    		return "Something went wrong here";
    }
//---------------------------------------------------------------
    // TODO: Fill in the space below with helper methods
    //-----------------------------------------------------------
    //Method for finding the whole number of the entered parameter.
    public static String find_whole_number(String num)
    {
    	if ((num.indexOf("_") > 0) || (num.indexOf("/") < 0)){
	    	String[] pieces = num.split(" ");
	    	//Split the input by spaces to isolate numbers and operator
	    	String[] num_pieces = num.split("_");
	    	//Whole number is first index of the array
	    	String whole_num = num_pieces[0];
	    	return whole_num;
    	} else 
    		return "0";
    }
    //Method for determining the numerators of a given input
        public static String find_numerator(String num)
        {
        	//Checks if input is a mixed number to split
        	if ((num.indexOf("_")>0)){
    	    	String[] pieces = num.split(" ");
    	    	//Split the input by spaces to isolate numbers and operator
    	    	String[] num_pieces = num.split("_");
    	    	//Fraction is the second index of the array
    	    	String frac = num_pieces[1];
    	    	//Split the fraction into numerator and denominator as they are seperated by the slash
    	    	String[] num_split = frac.split("/");
    	    	//Assign Numerator 
    	    	String numerator = num_split[0];
    	    	return numerator;
    	    //-------------------------------------------
            //Checks if input is strictly a fraction 
    	    //-------------------------------------------
        } else if ((num.indexOf("/")>0)) {
        	String[] num_split = num.split("/");
        	String numerator = num_split[0];
        	return numerator;
        	//Checks if the input is strictly a whole number
        	} else 
        		return "0";
    }
        //Method for finding denominator of a
        public static String find_denominator(String num)
        {
        	if ((num.indexOf("_")>0)){
    	    	String[] pieces = num.split(" ");
    	    	//Split the input by spaces to isolate numbers and operator
    	    	String[] num_pieces = num.split("_");
    	    	//Fraction is the second index of the array
    	    	String frac = num_pieces[1];
    	    	//Split the fraction into numerator and denominator as they are seperated by the slash
    	    	String[] num_split = frac.split("/");
    	    	//Assign Denominator 
    	    	String denominator = num_split[1];
    	    	return denominator;
    	    //-------------------------------------------
            //Checks if input is strictly a fraction 
    	    //-------------------------------------------
        } else if ((num.indexOf("/")>0)) {
        	//stuff for fractions here
        	//splits the fraction at the "/" which gives numerator and denominator 
        	String[] num_split = num.split("/");
        	String denominator = num_split[1];
        	
        	return denominator;
        } else 
        	return "1";
    }
        public static int MixedToFrac(int wn1, int n1, int d1) {
		if (wn1 > 0) {
			n1 += d1*wn1;	
		}
		//Process for converting a negative mixed number fraction
		if (wn1 < 0) {
			wn1*=d1;
			n1 *= -1;
			n1+=wn1;
			return n1;
		}
		return n1;
		}
        //Method for Simplifying fractions
        public static String simplify(int wn, int n, int d) {
        	if (Math.abs(n) >= Math.abs(d)) {
        		// whole number is equal to n/d with no remainder
        		wn = n/d;
        		// Numerator is equal to remainder
        		n = n % d;
        	}
        	if (d<0) {
        		//Denominator should never be negative
        		d *= -1;
        	}
        	if (n<0 && wn >0) {
        		//If Numerator is negative but whole number isn't, switch it so it's correct
        		n*= -1;
        		wn*= -1;
        	}
        		//If both numerator and whole number are negative, change numerator to positive so it's correct
        	if (n<0 && wn<0) {
        		n*= -1;
        	}
        	//reduce, ns and ds stand for numerator/denominator simplified 
        	int ns = reduce(n,d);
        	int ds = reduce(d,n);
        	if (ns == ds) {
        		return "1";
        	}
        	//If the whole number is 0, don't have the 0_ in the return
        	if (wn == 0) {
        		return (Integer.toString(ns) + "/" + Integer.toString(ds));
        		//If both numerator and denominator are 0, return the whole number
        	} else if (ns == 0 || ds == 0) {
        		return Integer.toString(wn);
        	}
        	//Final return
        	return (Integer.toString(wn) + "_" + Integer.toString(ns) + "/" + Integer.toString(ds));
        }
    
        public static int reduce(int x, int y) {
        	int gcd = greatestCommonDivisor(x,y);
        	if (gcd != 0) {
        		return x/gcd;
        	} else
        		return x;
        	}
    /**
     * greatestCommonDivisor - Find the largest integer that evenly divides two integers.
     *      Use this helper method in the Final Checkpoint to reduce fractions.
     *      Note: There is a different (recursive) implementation in BJP Chapter 12.
     * @param a - First integer.
     * @param b - Second integer.
     * @return The GCD.
     */
    public static int greatestCommonDivisor(int a, int b)
    {
        a = Math.abs(a);
        b = Math.abs(b);
        int max = Math.max(a, b);
        int min = Math.min(a, b);
        while (min != 0) {
            int tmp = min;
            min = max % min;
            max = tmp;
        }
        return max;
    }
    /**
     * leastCommonMultiple - Find the smallest integer that can be evenly divided by two integers.
     *      Use this helper method in Checkpoint 3 to evaluate expressions.
     * @param a - First integer.
     * @param b - Second integer.
     * @return The LCM.
     */
    public static int leastCommonMultiple(int a, int b)
    {
        int gcd = greatestCommonDivisor(a, b);
        return (a*b)/gcd;
    }
}