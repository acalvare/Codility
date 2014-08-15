import java.math.BigInteger;
import java.util.ArrayList;


// you can use System.out.println for debugging purposes, e.g.
// System.out.println("this is a debug message");

class Solution {
    public int solution(int[] A) {
        // write your code in Java SE 8
        ArrayList<Integer> equil = new ArrayList<Integer>();
        long[] forwardSum = getForwardSumArray(A);
        long[] backwardSum = getBackwardSumArray(A);
        for(int i = 0; i<A.length ; i++){
            
                if(forwardSum[i] == backwardSum[i]){
                    equil.add(i);
                   // System.out.println("EP FOUND! at "+i+" Forward: "+forwardSum[i]+", Backward: "+ backwardSum[i]);
                }
                else{
                   // System.out.println("Forward: "+forwardSum[i]+", Backward: "+ backwardSum[i]);
                }
            
        }
        if( equil.size() < 1)
            return -1;
        else
            return equil.get(0);
        
        
    }
    
    public long[] getForwardSumArray(int[] input){
     long sum = 0;
     long[] sumArray = new long[input.length];
     for(int i = 0; i < input.length; i++){
         sum = sum + input[i];
         sumArray[i] = sum; 
        // System.out.println("Forward i: "+i+", Value: "+sum);
     }
     return sumArray;
    }
    
    public long[] getBackwardSumArray(int[] input){
     long sum = 0;
     long[] sumArray = new long[input.length];
     for(int i = input.length-1; i >= 0; i--){
         sum = sum + input[i];
         sumArray[i] = sum;   
         //System.out.println("Backward i: "+i+", Value: "+sum);
     }
     return sumArray;
    }
        
}