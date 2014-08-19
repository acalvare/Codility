import java.util.ArrayList;


public class PrisonChallenge {

	public static void main(String[] args){
		int[] A = {0,1,2,3,3,2,6,6};
		int[] B = {1,2,3,4,5,6,8,7};
		int[] C = {1,6};
		Guard g = new Guard();
		ArrayList<Integer> guardList;
		ArrayList<Integer> exitNodes = getExitNodes(A, B);
		ArrayList<Prisoner> prisoners = setUpPrisoners(C, exitNodes, A, B);
		ArrayList<ArrayList<Integer>> totalExitPaths = combinePrisonerExitLists(prisoners);
		guardList = g.getMinimumNumberOfGuards(totalExitPaths, C, A);
		System.out.println("GuardList: ");
		printList(guardList);

//		for(Prisoner p : prisoners){
//			System.out.println("Path for: "+p.getStartingPosition());
//			printPaths(p.getExitList());
//			System.out.println();
//			System.out.println();
//		}
		//solution(A,B,C);

	}
	public static int solution(int[] A, int[] B, int[] C) {
		// printList(getExitNodes(A,B));
		//Checks to see if it is possible to catch all prisoners
		if(anyPrisonersOnExitNodes(getExitNodes(A, B), C))
			return -1;
		else
			return 0;
	}

	/**
	 * Returns an array list with all of the exit nodes
	 * @param arrayA
	 * @param arrayB
	 * @return
	 */
	public static ArrayList<Prisoner> setUpPrisoners(int[] C, ArrayList<Integer> exitNodes, int[] A, int[] B){
		ArrayList<Prisoner> prisoners = new ArrayList<Prisoner>();
		for(int i = 0; i<C.length ; i++){
			Prisoner prisoner = new Prisoner();
			prisoner.setExitList(prisoner.getPath(C[i], exitNodes, A, B));
			prisoner.setStartingPosition(C[i]);
			prisoners.add(prisoner);
		}
		return prisoners;
	}

	public static ArrayList<Integer> getExitNodes(int[] arrayA, int[] arrayB){
		ArrayList<Integer> exits = new ArrayList<Integer>();
		int[] countArray = new int[arrayA.length+1];
		for(int i = 0; i < arrayA.length ; i++){
			// System.out.println(i);
			if(countArray[arrayA[i]] <= 0){
				countArray[arrayA[i]] = 1;
			}
			else{
				countArray[arrayA[i]] += 1;
			}
			if(countArray[arrayB[i]] <= 0){
				countArray[arrayB[i]] = 1;
			}
			else{
				countArray[arrayB[i]] += 1;
			}
		}

		for(int i = 0 ; i<countArray.length ; i++){
			if(countArray[i] == 1){
				exits.add(i);
			}
			//System.out.println("CA:["+i+" : "+countArray[i]+"]");
		}
		return exits;
	}

	public static boolean anyPrisonersOnExitNodes(ArrayList<Integer> exitNodes, int[] prisoners){
		for(int i=0 ; i< exitNodes.size(); i ++){
			for(int j = 0 ; j < prisoners.length ; j++){
				if(exitNodes.get(i) == prisoners[j])
					return true;
			}
		}
		return false;
	}


	public static ArrayList<ArrayList<Integer>> combinePrisonerExitLists(ArrayList<Prisoner> prisoners){

		ArrayList<ArrayList<Integer>> combinedExitList = new ArrayList<ArrayList<Integer>>();
		for(Prisoner p : prisoners){
			ArrayList<ArrayList<Integer>> currentExitList = p.getExitList();
			for(int i = 0 ; i < currentExitList.size(); i++){
				combinedExitList.add(currentExitList.get(i));
			}

		}
		printPaths(combinedExitList);
		return combinedExitList;
	}
	/**
	 * Prints out the contents of an array list
	 * @param list
	 */
	public static void printList(ArrayList<Integer> list){
		System.out.print("[ ");
		for(int i = 0 ; i < list.size() ; i++){
			System.out.print(list.get(i)+" ");
		}
		System.out.print("] ");
	}

	public static void printPaths(ArrayList<ArrayList<Integer>> pathLists){
		for(int i = 0 ; i < pathLists.size() ; i ++){
			printList(pathLists.get(i));
		}
		System.out.println(" ");

	}
}
