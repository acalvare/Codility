import java.util.ArrayList;


public class Guard {

	public Guard(){

	}

	public ArrayList<Integer> getMinimumNumberOfGuards(ArrayList<ArrayList<Integer>> totalExitPaths, int[] prisoners, int[] A){
		ArrayList<Integer> guards = new ArrayList<Integer>();
		//System.out.println("In minnumguards");
		while(totalExitPaths.size() > 1){
			//System.out.println("TotalExitPaths size: "+totalExitPaths.size());
			int node = getNextRequiredGuard(totalExitPaths, prisoners, A);
			System.out.println("Removing node : " +node);
			guards.add(node);
			totalExitPaths = removePathContainingNode(node, totalExitPaths);
			
		}
		return guards;

	}

	public boolean isPrisonerBlock(int[] prisoners, int node){
		for(int prisoner : prisoners){
			if(prisoner == node){
				return true;
			}
		}
		return false;
	}

	public ArrayList<ArrayList<Integer>> removePathContainingNode(int node, ArrayList<ArrayList<Integer>> exitPaths){
		ArrayList<ArrayList<Integer>> modifiedExitPaths = new ArrayList<ArrayList<Integer>>();

		//Adds all exit paths to the modifiedExitPath that do not contain the node
		for(int i = 0 ; i < exitPaths.size() ; i++){
			boolean valueFound = false;
			for(int j = 0 ; j<exitPaths.get(i).size(); j++){
				//System.out.println("Current value is: "+exitPaths.get(i).get(j) + " Looking for : "+node);
				if(exitPaths.get(i).get(j) == node){
					valueFound = true;
					//System.out.println("Value Found! : "+node);
				}
			}
			if(!valueFound){
				modifiedExitPaths.add(exitPaths.get(i));
			}
		}
		//System.out.println("Modified Exit Paths size: "+modifiedExitPaths.size());
		return modifiedExitPaths;
	}

	public int getNextRequiredGuard(ArrayList<ArrayList<Integer>> totalExitPaths, int[] prisoners, int[] A){
		int guard;
		int[] countArray = new int[A.length + 1];
		int maxNumber = Integer.MIN_VALUE;
		int maxIndex = 0;
		for(int i = 0 ; i < totalExitPaths.size() ; i++){
			ArrayList<Integer> currentPath = totalExitPaths.get(i);
			if(currentPath.size() == 2){
				guard = currentPath.get(0);
				return guard;
			}
			else{
				for(int j = 0 ; j < currentPath.size() ; j++){
					countArray[currentPath.get(j)]++;

				}
			}
		}
		for(int i = 0; i < countArray.length ; i++){
			if(countArray[i] > maxNumber){
				if(!isPrisonerBlock(prisoners, i)){
					maxNumber = countArray[i];
					maxIndex = i;
				}
			}
		}
		guard = maxIndex;
		//System.out.println("Current max is : "+maxIndex+" with occurances: "+maxNumber);
		return guard;
	}
}
