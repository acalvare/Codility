package Phosphorus2014;
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


	public ArrayList<ArrayList<Integer>> combineArrayLists(ArrayList<ArrayList<Integer>> forwardPath, ArrayList<ArrayList<Integer>> backwardPath){
		ArrayList<ArrayList<Integer>> exitLists = new ArrayList<ArrayList<Integer>>();
		//Combines the forward and backward paths to get the total paths to an exit
		if(forwardPath != null){
			for(int i = 0 ; i < forwardPath.size(); i++){
				exitLists.add(forwardPath.get(i));
			}
		}
		if(backwardPath != null){
			for(int i = 0 ; i < backwardPath.size(); i++){
				exitLists.add(backwardPath.get(i));
			}
		}
		return exitLists;
	}
	public ArrayList<ArrayList<Integer>> getPath(int prisoner, ArrayList<Integer> exitNodes, int[] A, int[] B, int alreadyVisited){
		ArrayList<ArrayList<Integer>> exitListsForward = new ArrayList<ArrayList<Integer>>();
		ArrayList<ArrayList<Integer>> exitListsBackward = new ArrayList<ArrayList<Integer>>();

		for(int i = 0 ; i < A.length ; i++){
			if(A[i] == prisoner){
				//System.out.println("Found: "+prisoner);
				ArrayList<Integer> list = new ArrayList<Integer>();
				if(isExitNode(B[i], exitNodes)){
					list.add(B[i]);
					list.add(prisoner);
					exitListsForward.add(list);
					//System.out.println("Found end node with call : "+prisoner+" at : "+B[i]);
				}
				else{
					if(B[i] != alreadyVisited){
						ArrayList<ArrayList<Integer>> recursiveList = getPath(B[i], exitNodes, A, B, A[i]);
						//System.out.println("waiting...");
						for(int j = 0 ; j< recursiveList.size() ; j++){
							//System.out.println("J: "+j);
							recursiveList.get(j).add(prisoner);
							exitListsForward.add(recursiveList.get(j));	
						}
					}
				}
			}
		}

		for(int i = 0 ; i < B.length ; i++){
			if(B[i] == prisoner){
				//System.out.println("Found: "+prisoner);
				ArrayList<Integer> list = new ArrayList<Integer>();
				if(isExitNode(A[i], exitNodes)){
					list.add(A[i]);
					list.add(prisoner);
					exitListsBackward.add(list);
					//System.out.println("Found end node with call : "+prisoner+" at : "+B[i]);
				}
				else{

					if(A[i] != alreadyVisited){
						ArrayList<ArrayList<Integer>> recursiveList = getPath(A[i], exitNodes, A, B, B[i]);
						//System.out.println("waiting...");
						for(int j = 0 ; j< recursiveList.size() ; j++){
							//System.out.println("J: "+j);
							recursiveList.get(j).add(prisoner);
							exitListsBackward.add(recursiveList.get(j));	
						}
					}
				}
			}
		}

		return combineArrayLists(exitListsForward, exitListsBackward);
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
