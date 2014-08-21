package Phosphorus2014;

import java.util.ArrayList;

public class Node {
	private int value;
	private ArrayList<Node> neighborNodes;
	private int numEscapePaths;
	private boolean isExit;
	private boolean isPrisoner;
	private boolean isGuard;
	private boolean alreadyVisited;
	
	public Node(){
		
	}

	
	public int getValue() {
		return value;
	}

	public void setValue(int value) {
		this.value = value;
	}

	public ArrayList<Node> getNeighborNodes() {
		return neighborNodes;
	}

	public void setNeighborNodes(ArrayList<Node> neighborNodes) {
		this.neighborNodes = neighborNodes;
	}

	public int getNumEscapePaths() {
		return numEscapePaths;
	}

	public void setNumEscapePaths(int numEscapePaths) {
		this.numEscapePaths = numEscapePaths;
	}

	public boolean isExit() {
		return isExit;
	}

	public void setExit(boolean isExit) {
		this.isExit = isExit;
	}

	public boolean isPrisoner() {
		return isPrisoner;
	}

	public void setPrisoner(boolean isPrisoner) {
		this.isPrisoner = isPrisoner;
	}

	public boolean isGuard() {
		return isGuard;
	}

	public void setGuard(boolean isGuard) {
		this.isGuard = isGuard;
	}

	public boolean isAlreadyVisited() {
		return alreadyVisited;
	}

	public void setAlreadyVisited(boolean alreadyVisited) {
		this.alreadyVisited = alreadyVisited;
	}

	@Override
	public String toString() {
		return "Node [value=" + value + ", neighborNodes=" + getNeighborNodeStringList(neighborNodes)
				+ ", numEscapePaths=" + numEscapePaths + ", isExit=" + isExit
				+ ", isPrisoner=" + isPrisoner + ", isGuard=" + isGuard
				+ ", alreadyVisited=" + alreadyVisited + "]";
	}
	
	public String getNeighborNodeStringList(ArrayList<Node> neighborNodes){
		StringBuilder neighbors = new StringBuilder();
		neighbors.append('[');
		for(int i = 0 ; i < neighborNodes.size() ; i++){
			neighbors.append(neighborNodes.get(i).getValue());
			if(i != neighborNodes.size() -1){
				neighbors.append(' ');
			}
		}
		neighbors.append(']');
		return neighbors.toString();
	}
	
	
	
}
