package Phosphorus2014;
import java.util.ArrayList;
import java.util.Stack;


public class PrisonEscape {

	public static void main(String[] args){
		int[] A = {0,1,2,3,3,2,6,6};
		int[] B = {1,2,3,4,5,6,8,7};
		int[] C = {1,6};

		int[] D = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
		int[] E = {0, 0, 0, 2, 4, 3, 5, 5, 7, 1};
		int[] F = {3, 5};

		Node[] nodes = setUpNodeList(D, E, F);
		for(Node node : nodes){
			System.out.println(node);
		}
		System.out.println("Guard Count: "+getGuardCount(nodes));
		//ArrayList<Node> nodes = new ArrayList<Node>();
		//		System.out.println("Number of Guards: "+solution(A, B, C));
		//		System.out.println();
		//		System.out.println("Number of Guards: "+solution(D, E, F));
		//		for(Prisoner p : prisoners){
		//			System.out.println("Path for: "+p.getStartingPosition());
		//			printPaths(p.getExitList());
		//			System.out.println();
		//			System.out.println();
		//		}
		//solution(A,B,C);

	}

	public static Node[] setUpNodeList(int[] A, int[] B, int[] C){

		Node[] nodes = initializeNodes(A);
		nodes = setInitialNodes(A, B, nodes);
		nodes = setInitialExits(nodes);
		nodes = setInitialPrisoners(nodes, C);
		nodes = setInitialRequiredGuards(nodes);
		
		nodes = setAllGuards(nodes, C);

		return nodes;


	}
	
	public static int getGuardCount(Node[] nodes){
		int guardCount = 0;
		for(Node node : nodes){
			if(node.isGuard())
				guardCount++;
		}
		return guardCount;
	}
	public static Node[] setNextGuard(Node[] nodes){
		int max = 0;
		int maxIndex = -1;
		for(int i = 0 ; i < nodes.length ; i++){
			if(nodes[i].getNumEscapePaths() > max && !(nodes[i].isPrisoner())){
				max = nodes[i].getNumEscapePaths();
				maxIndex = i;
			}
			nodes[i].setNumEscapePaths(0);
		}
		//System.out.println("Max index: "+maxIndex);
		//System.out.println("Max value: "+max);
		if(maxIndex >= 0){
			nodes[maxIndex].setGuard(true);
		}
		else
			return null;
		return nodes;
	}
	
	public static Node[] setAllGuards(Node[] nodes, int[] C){
		
		Node[] temp = nodes;
		while(temp != null){
			nodes = getCurrentExitPaths(C, nodes);
			temp = setNextGuard(nodes);
			if(temp != null){
				nodes = temp;
			}
		}
		return nodes;
		
	}
	public static Node[] getCurrentExitPaths(int[] C, Node[] nodes){
		//System.out.println("In current paths");
		for(int i = 0 ; i < C.length ; i++){
			for(int j = 0 ; j < nodes.length ; j++){
				if(nodes[j].isExit()){
					//System.out.println("DFS for "+nodes[C[i]].getValue()+" to exit: "+nodes[j].getValue());
					depthFirstSearch(nodes, nodes[C[i]], nodes[j]);
				}
			}
		}
		return nodes;
	}

	public static Node[] initializeNodes(int[] A){
		Node[] nodes = new Node[A.length + 1];

		//Initializes all of the nodes with their values and their default neighbors (none)
		for(int i = 0 ; i < nodes.length ; i++){
			Node node = new Node();
			node.setValue(i);
			node.setNeighborNodes(new ArrayList<Node>());
			node.setNumEscapePaths(0);
			nodes[i] = node;
		}
		return nodes;
	}
	public static Node[] setInitialNodes(int[] A, int[] B, Node[] nodes){
		for(int i=0 ; i < A.length ; i++){
			ArrayList<Node> neighborNodesA = nodes[A[i]].getNeighborNodes();
			ArrayList<Node> neighborNodesB = nodes[B[i]].getNeighborNodes();
			neighborNodesA.add(nodes[B[i]]);
			neighborNodesB.add(nodes[A[i]]);
			nodes[A[i]].setNeighborNodes(neighborNodesA);
			nodes[B[i]].setNeighborNodes(neighborNodesB);

		}
		return nodes;
	}
	public static Node[] setInitialRequiredGuards(Node[] nodes){
		for(Node node : nodes){
			ArrayList<Node> neighbors = node.getNeighborNodes();
			for(Node neighbor : neighbors){
				if(node.isExit() && neighbor.isPrisoner()){
					node.setGuard(true);
				}
			}
		}
		return nodes;
	}

	public static Node[] setInitialExits(Node[] nodes){
		for(Node node : nodes){
			if(node.getNeighborNodes().size() == 1){
				node.setExit(true);
			}
		}
		return nodes;
	}

	public static Node[] setInitialPrisoners(Node[] nodes, int[] C){
		for(int prisoner : C){
			nodes[prisoner].setPrisoner(true);
		}
		return nodes;
	}
	public static Node[] depthFirstSearch(Node[] nodes, Node start, Node end){

		Stack<Node> stack = new Stack<Node>();
		stack.push(start);
		start.setAlreadyVisited(true);
		while(!stack.isEmpty()){
			start = stack.peek();
			//System.out.println("Start is : "+start.getValue());
			ArrayList<Node> neighbors = start.getNeighborNodes();
			Node potentioalNeighbor = null;
			for(Node neighbor : neighbors){
				if(!(neighbor.isAlreadyVisited()) && !(neighbor.isGuard())){
					potentioalNeighbor = neighbor;
				}
			}
			if(potentioalNeighbor != null){
				if(potentioalNeighbor == end){
				//	System.out.println("FOUND!");
					potentioalNeighbor.setAlreadyVisited(true);
					stack.push(potentioalNeighbor);
					for(Node node : stack){
						int currentPaths = nodes[node.getValue()].getNumEscapePaths();
						nodes[node.getValue()].setNumEscapePaths(currentPaths + 1);
					}
					for(Node node : nodes){
						node.setAlreadyVisited(false);
					}
					return nodes;
				}

				else{
					potentioalNeighbor.setAlreadyVisited(true);
					stack.push(potentioalNeighbor);
				}
			}
			else{
				//neighbor.setAlreadyVisited(true);
				stack.pop();
			}
		}
		for(Node node : nodes){
			node.setAlreadyVisited(false);
		}
		return nodes;



	}

}
