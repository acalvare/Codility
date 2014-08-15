import java.util.ArrayList;


public class Prisoner {

	private int startingPosition;
	private ArrayList<ArrayList<Integer>> exitList = new ArrayList<ArrayList<Integer>>();
	private ArrayList<Integer> exitPath;

	public Prisoner(int startingPosition){
		this.startingPosition = startingPosition;
	}
	public Prisoner(){
	}
	
	
	public ArrayList<ArrayList<Integer>> getPath(int prisoner, ArrayList<Integer> exitNodes, int[] A, int[] B){
		ArrayList<ArrayList<Integer>> exitLists = new ArrayList<ArrayList<Integer>>();
		
		//Gets the Paths going forward
		ArrayList<ArrayList<Integer>> forwardExitLists = getPathOneDirection(prisoner, exitNodes, A, B);
		
		//Gets the paths going backward
		ArrayList<ArrayList<Integer>> backwardExitLists = getPathOneDirection(prisoner, exitNodes, B, A);
		
		//Combines the forward and backward paths to get the total paths to an exit
		for(int i = 0 ; i < forwardExitLists.size(); i++){
			exitLists.add(forwardExitLists.get(i));
		}
		for(int i = 0 ; i < backwardExitLists.size(); i++){
			exitLists.add(backwardExitLists.get(i));
		}
		return exitLists;
	}
	
	public ArrayList<ArrayList<Integer>> getPathOneDirection(int prisoner, ArrayList<Integer> exitNodes, int[] A, int[] B){
		ArrayList<ArrayList<Integer>> exitLists = new ArrayList<ArrayList<Integer>>();
		for(int i = 0 ; i < A.length ; i++){
			if(A[i] == prisoner){
				//System.out.println("Found: "+prisoner);
				ArrayList<Integer> list = new ArrayList<Integer>();
				if(isExitNode(B[i], exitNodes)){
					list.add(B[i]);
					list.add(prisoner);
					exitLists.add(list);
					//System.out.println("Found end node with call : "+prisoner+" at : "+B[i]);
				}
				else{
					//System.out.println("recursive call with : "+B[i]);
					ArrayList<ArrayList<Integer>> recursiveList = getPathOneDirection(B[i], exitNodes, A, B);
					//System.out.println("waiting...");
					for(int j = 0 ; j< recursiveList.size() ; j++){
						//System.out.println("J: "+j);
						recursiveList.get(j).add(prisoner);
						exitLists.add(recursiveList.get(j));	
					}
				}
			}
		}

		return exitLists;
	}
	
	public boolean isExitNode(int value, ArrayList<Integer> exitNodes){
		for(int i = 0 ; i < exitNodes.size() ; i++){
			if(exitNodes.get(i) == value){
				return true;
			}
		}
		return false;
	}
	public ArrayList<ArrayList<Integer>> getExitList() {
		return exitList;
	}
	public void setExitList(ArrayList<ArrayList<Integer>> exitList) {
		this.exitList = exitList;
	}
	public int getStartingPosition() {
		return startingPosition;
	}
	public void setStartingPosition(int startingPosition) {
		this.startingPosition = startingPosition;
	}
	
	
	
}
