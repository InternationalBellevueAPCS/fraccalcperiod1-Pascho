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
    	while (!(input.equals("0 + 0"))) {
    	System.out.println("Enter equation:");
    	System.out.println("Input like this: 2 + 2,    2 + 2_1/3");
    	System.out.println("Enter '0 + 0' to quit");
    	input = console.nextLine();
    	produceAnswer(input);
    	}
    }
    /**
     * produceAnswer - This function takes a String 'input' and produces the result.
     * @param input - A fraction string that needs to be evaluated.  For your program, this will be the user input.
     *      Example: input ==> "1/2 + 3/4"
     * @return the result of the fraction after it has been calculated.
     *      Example: return ==> "1_1/4"
     */
    public static String produceAnswer(String input)
    { 
    	// "x" represents the first time we encounter a space in the input, I use this to determine where the first number is.
    	int x = input.indexOf(" ");
    	// The first number starts from the first index of the string and ends at the first space
    	String first_num = input.substring(0,x);
    	// the operand is one space after the first number
    	String operator = input.substring(x,x+2);
    	// the second number is one space after the operand, I don't know how long this number is so I leave the substring end 'open' to make sure I dont cut it off
    	String second_num = input.substring(x+3);

    	//Checkpoint 2 stuff
    	//Checks if the number is a mixed number by scanning for underscore
    	if ((second_num.indexOf("_")>0)){
    		
	    	String[] pieces = input.split(" ");
	    	//Split the input by spaces to isolate numbers and operator
	    	String[] num_2_pieces = second_num.split("_");
	    	//Whole number is first index of the array
	    	String whole_num = num_2_pieces[0];
	    	//Fraction is the second index of the array
	    	String frac = num_2_pieces[1];
	    	//Split the fraction into numerator and denominator as they are seperated by the slash
	    	String[] num_2_split = frac.split("/");
	    	//Assign Numerator 
	    	String numerator = num_2_split[0];
	    	//Assign Denominator 
	    	String denominator = num_2_split[1];
	    	//Compile variables for return
	    	String checkpoint2 = ("whole:" + whole_num + " " + "numerator:" + numerator + " " + "denominator:" + denominator );
	    	System.out.println(checkpoint2);
	    	return checkpoint2;
        // TODO: Implement this function to produce the solution to the input
        // Checkpoint 1: Return the second operand.  Example "4/5 * 1_2/4" returns "1_2/4".
        // Checkpoint 2: Return the second operand as a string representing each part.
        //               Example "4/5 * 1_2/4" returns "whole:1 numerator:2 denominator:4".
        // Checkpoint 3: Evaluate the formula and return the result as a fraction.
        //               Example "4/5 * 1_2/4" returns "6/5".
        //               Note: Answer does not need to be reduced, but it must be correct.
        // Final project: All answers must be reduced.
        //               Example "4/5 * 1_2/4" returns "1_1/5".
        

        //Checks if the input is strictly a fraction 
    } else if ((second_num.indexOf("/")>0)) {
    	//stuff for fractions here
    	//splits the fraction at the "/" which gives numerator and denominator 
    	String[] num_2_split = second_num.split("/");
    	String numerator = num_2_split[0];
    	//Assigning Numerator from array
    	String denominator = num_2_split[1];
    	//Assigning Denominator from array
    	String checkpoint2 = ("whole:" + "0" +" "+ "numerator:" + numerator + " " + "denominator:" + denominator );
    	//Assigns values to variable for return
    	System.out.println(checkpoint2);
    	return checkpoint2;
    //Executes if second number is just an integer 
    } else if ((second_num.indexOf("/") == -1)) {
    	//assign variables for return
    	String checkpoint2 = ("whole:" + second_num + " " + "numerator:0" + " denominator:1");
    	System.out.println(checkpoint2);
    	return checkpoint2;
    } else {
    	String s = ("Inalid input!");
    	System.out.println(s);
    	return s;
    }
    	
    	
    }

    // TODO: Fill in the space below with helper methods
    
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
