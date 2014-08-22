package Silicium2014;

import java.util.ArrayList;
import java.util.Arrays;

public class Solution {

	public static void main (String[] args){
		int[] A = {1,3};
		int[] B = {1,5};
		int X = 6;
		int Y = 7;
		int K = 3;
		
		System.out.println(solution(X, Y, K, A, B));
	}

	public static int solution(int X, int Y, int K, int[] A, int[] B) {
		// write your code in Java SE 8
		CakeSlice[] slices = generateCakeSliceList(X, Y, A, B);
		Arrays.sort(slices);
		//printArray(slices);
		if(K > 0)
			return((int)slices[K-1].getArea());
		return -1;
	}

	public static CakeSlice[] generateCakeSliceList(int X, int Y, int[] A, int[] B){

		//ArrayList<CakeSlice> slices = new ArrayList<CakeSlice>();
		CakeSlice[] slices = new CakeSlice[(int)Math.pow((A.length + 1), 2)];
		int count = 0;
		int[] xValues = getTotalArray(X, A);
		int[] yValues = getTotalArray(Y, B);
		for(int i = 1; i < xValues.length ; i++){
			for(int j = 1 ; j < yValues.length ; j++){
				CakeSlice slice = new CakeSlice(xValues[i],yValues[j],xValues[i-1],yValues[j-1]);
				slice.computeArea();
				slices[count] = (slice);
				count++;
			}
		}
		return slices;


	}

	/**
	 * 
	 * @param endPoint
	 * @param slicePoint
	 * @return Array that has the (0,0) and (X,Y) points added
	 */
	public static int[] getTotalArray(int endPoint, int[] slicePoint){
		int[] values = new int[slicePoint.length +2 ];
		values[0] = 0;
		for(int i = 0; i < slicePoint.length ; i++){
			values[i+1] = slicePoint[i];
		}
		values[values.length - 1]=endPoint;
		return values;
	}


	public static void printArray(CakeSlice[] slices){
		for(int i = 0 ; i < slices.length ; i++){
			System.out.println(slices[i]);
		}
	}
}
