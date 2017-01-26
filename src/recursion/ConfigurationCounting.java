package recursion;
import java.util.*;

/*
 * Author: Adam Staples 100978589
 * Submission Date: Jan 25th 2017
 * 
 * Program Description:
 * Class that imitates the effects of a channel node system that has channels accessing each node once.
 * Every possible combination is created as well as how many times that combination can occur.
 * 
 * The class takes no input variables however, to change the number of total nodes and channels simply
 * change the STARTNODES and STARTCHANNELS class variable located at the top of the class and re run
 * the program.
 */

public class ConfigurationCounting
{
	//number representing the total number of combinations
	private static long sum = 0; 
	//the number of starting nodes **CHANGE THIS TO CHANGE TOTAL NODES**
	private final int STARTNODES = 3;  
	//the number of starting channels **CHANGE THIS TO CHANGE TOTAL CHANNELS**
    private final int STARTCHANNELS = 3; 
    //array used to hold the different combinations
	private int[] array;
	
	/*
	 * Constructor to set up and call the recursive function
	 */
	public ConfigurationCounting(){
		array = new int[STARTCHANNELS];
		generateCombinations(STARTNODES,0, array);
		System.out.println("The total combinations is equal to: " + sum);
	}
//~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
	/*
	 *  returns the total number of sums currently found
	 * 
	 * @ret a long representing the total number of combinations
	 */
	public long getSum(){
		return sum;
	}
//~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
	/*
	 * Computes the factorial of a given number
	 * 
	 * @param1 the number that you wish to find the factorial of
	 * @ret the factorial of @param1 as a long
	 */
	public long factorial(int n) { 													
        int fact = 1; 	
        if(n == 0){
        	return 1;
        }
        for (int i = 1; i <= n; i++) { 
            fact *= i;
        }
        return fact;
    }
//~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
	/*
	 * Computes the total number of times a certain combination can be created using the binomial coefficient (n choose r/ nCr)
	 * obtained using the factorial method above 
	 * 
	 * @param1 the combination that you wish to find the number of times it can be made
	 * @ret a long representing the total number of ways a combination can be made
	 */
	public long binomial(int[] array){
		long ret = 1;
		int n =  STARTNODES;
		for(int r: array){
			ret = (long) (ret * (factorial(n) / (factorial(r)*factorial(n-r))));
			n = n-r;
		}
		return ret;
	}
	
//~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
	/*
	 * Recursive function used to calculate nodes and channel combinations
	 * 
	 * @param1 an int that is equal to the amount of remaining Nodes
	 * @param2 an int that corresponds with the channel that nodes will be added to during this recursion step
	 * @param3 an array of ints that houses the nodes and when the base case is reached is equal to the combinations of channels to nodes
	 * 
	 * @ret a boolean value which returns true when the base case is reached.
	 * 
	 */
	
	public Boolean generateCombinations(int nodes, int currentChannel, int[] combinations){
		//base case (no more channels to assign nodes too) k = K-1
		if (currentChannel == STARTCHANNELS-1){ 
			//assign the final channel with remaining nodes
			combinations[currentChannel] = nodes;
			//aquire the total number of ways to make this combination
			long binom = binomial(combinations);
			//add to total sum
			sum += binom;
			//print final information
			System.out.println(binom + " set(s) with occupancies: " + Arrays.toString(combinations));
	        return true;
	    }else{
	    	//for loop that assigns nodes to each channel when it is reached
	    	for(int i = 0; i<=nodes ;i++){
	    		combinations[currentChannel] = i;
	    		//recurse to the next channel
	    		generateCombinations(nodes-i, (currentChannel+1), combinations);
	    	}
	    } 
       return false;
    }
	
//~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
	/*
	 * Main function that runs the class
	 */
    public static void main(String [] args){ 																						
        ConfigurationCounting count = new ConfigurationCounting();		
    }
}
