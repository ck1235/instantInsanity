public class Solve{

	private static Queue<Solution> queueOfSolutions;

	private static Solution solution;

	private static Solution solutionForBreadth;

	private static Queue<Solution> open;

	private static Queue<Solution> breadth;

	public static void main(String[] args){

		Cube cube1;
		cube1 = new Cube(new Color[]{Color.BLUE, Color.GREEN, Color.WHITE, Color.GREEN, Color.BLUE, Color.RED});

		Cube cube2;
		cube2 = new Cube(new Color[]{Color.WHITE, Color.GREEN, Color.BLUE, Color.WHITE, Color.RED, Color.RED});

		Cube cube3;
		cube3 = new Cube(new Color[]{Color.GREEN, Color.WHITE, Color.RED, Color.BLUE, Color.RED, Color.RED});

		Cube cube4;
		cube4 = new Cube(new Color[]{Color.BLUE, Color.RED, Color.GREEN, Color.GREEN, Color.WHITE, Color.WHITE});

		Cube[] list;
		list = new Cube[4];
		list[0] = cube1;
		list[1] = cube2;
		list[2] = cube3;
		list[3] = cube4;

		solution = new Solution(list);
		solutionForBreadth = new Solution(list);

		queueOfSolutions = new LinkedQueue<Solution>();

		// MAIN

		StudentInfo.display();


		long start, stop;

		System.out.println("\n" + "generateAndTest: ");
		start = System.currentTimeMillis(); // could also use nanoTime

		generateAndTest();

		stop = System.currentTimeMillis();
		System.out.println("Elapsed time: " + (stop-start) + " milliseconds");
		System.out.println("Size of queue of solutions: " + queueOfSolutions.size());


		System.out.println("\n" + "breadthFirstSearch: ");
		start = System.currentTimeMillis();

		breadthFirstSearch();

		stop = System.currentTimeMillis();
		System.out.println("Elapsed time: " + (stop-start) + " milliseconds");
		System.out.println("Size of queue of solutions: " + breadth.size());


	}

	/**
	* finds all the solutions to the instant insanity problem by exhaustively generating all the possible solutions
	* and returns a Queue with all the valid solutions to the problem
	*/
	public static Queue<Solution> generateAndTest(){

		if(solution.size()>4){
			System.out.println("There are more than 4 cubes so there are no possible solutions");
			return queueOfSolutions;
		}

		solution.resetNumberOfCalls();
        solution.getCube(0).reset();

		while(solution.getCube(0).hasNext()){
			solution.getCube(0).next();
			solution.getCube(1).reset();
			
			while(solution.getCube(1).hasNext()){
				solution.getCube(1).next();
				solution.getCube(2).reset();
				
				while(solution.getCube(2).hasNext()){
					solution.getCube(2).next();
					solution.getCube(3).reset();
					
					while(solution.getCube(3).hasNext()){
						solution.getCube(3).next();

						if(solution.isValid()){
							queueOfSolutions.enqueue(solution);
						}
					}
				}
			}
		}

		System.out.println("Number of calls to isValid(): " + solution.getNumberOfCalls());

		return queueOfSolutions;

	}

	/**
	* finds all the solutions by using the breadth-first-search algorithm and
	* returns a Queue that contains all the valid solutions
	*/
	public static Queue<Solution> breadthFirstSearch(){
		
		solutionForBreadth.resetNumberOfCalls();

		Solution current;
		Solution extentionSol;

		Cube[] cube = new Cube[1];
		cube[0] = solutionForBreadth.getCube(0);
		
		current = new Solution(cube);

		int numofIsValid = 0;

		open = new LinkedQueue<Solution>(); // initialized to contain all valid solutions


		while(current.getCube(0).hasNext()){

			current.getCube(0).next();
			cube[0] = current.getCube(0);

            Solution initialOpenSol = new Solution(cube);

			open.enqueue(initialOpenSol);

		}

		breadth = new LinkedQueue<Solution>();


		while(!open.isEmpty()){
			
			current = null;
			current = open.dequeue();

			for(int i=current.size() ; i < (current.size()+1) ; i++){
			
				solutionForBreadth.getCube(i).reset();
				while (solutionForBreadth.getCube(i).hasNext()){

					if(current.isValid(solutionForBreadth.getCube(i))){

						numofIsValid++;

						extentionSol = new Solution(current, solutionForBreadth.getCube(i));
				
						if(extentionSol.size()==4){
							breadth.enqueue(extentionSol);
						}
						else{
							open.enqueue(extentionSol);
						}
					}

				solutionForBreadth.getCube(i).next();
				
				}
			}
		}

		System.out.println("Number of calls to isValid(): " + numofIsValid);
		
		return breadth;

	}


}