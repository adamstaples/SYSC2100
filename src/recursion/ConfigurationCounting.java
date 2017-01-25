package recursion;
import java.util.*;
import java.math.*;


public class ConfigurationCounting
{
//~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~	
	//int factorial calculator
	public long sum = 0; 
	public int iterations;
	public final int STARTNODES = 3;  																	//set number of channels
    public final int STARTCHANNELS = 2; 
	public int[] array;
	public int currentChannel;
	
	public ConfigurationCounting(){
		iterations = 0;
		array = new int[STARTCHANNELS];
		generateCombinations(STARTNODES,STARTCHANNELS,STARTCHANNELS-1, array);
	}
	
	public long getSum(){
		return sum;
	}
	
	public void setSum(long i){
		sum = i;
	}
	
	
	public int factorial(int n) { 													//factorial function, cannot be used for high numbers, but for our purposes will be sufficient
        int fact = 1; 																		// this  will be the result
        for (int i = 1; i <= n; i++) { 
            fact *= i;
        }
        return fact;
    }
//~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
	//Multinomial calculation function (sets of combos)
	public long binomial(int numNodes, int[] array){
		int size = array.length;
		int num = factorial(numNodes); 														
		long den = 1;																	
		if(numNodes > numNodes-size){
			size = numNodes-size;
			for(int i = 1, m = numNodes; i<=size;i++,m--){
				den = den*m/i;
			}
			return den;
		}
		return -1;
	}
	
//~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
	//Recursive function used to calculate nodes and channel combinations
	
	public int generateCombinations(int nodes, int channels, int currentChannel, int[] combinations){
		int count = 0;
		
		
		if (nodes == 0){															
			System.out.println("FOUND IT " + Arrays.toString(combinations));
	        return combinations[currentChannel] = nodes;
	    }else{
	    	for(int i = currentChannel; i>=0 ;i--){
	    		for(int c: combinations){
	    			count = count+c;
	    		}
	    		combinations[currentChannel] = (STARTNODES +1) -nodes;
	    		generateCombinations(nodes-1, channels, i, combinations);
	    	}

	    		//generateCombinations(nodes, channels, currentChannel-1, combinations);
	    }
       return 0;
    }
	
//~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
	//Main function
    public static void main(String [] args){ 												// main function												//set array. Channels is max need elements
        ConfigurationCounting count = new ConfigurationCounting();
        System.out.println("The total combinations is equal to: " + count.getSum()); 					//print out sum
    }
}
