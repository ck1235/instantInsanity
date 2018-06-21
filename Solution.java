public class Solution{

	private Cube[] listOfCubes;
	private Solution soln;
	private int numberOfCubes;
	private int numberOfCalls;

    /**
     * Constructor that initializes this solution using the cubes provided in the array cubes
     * @param cubes
     *            array of cubes
     */
	public Solution(Cube[] cubes){
		listOfCubes = new Cube[cubes.length];

		for(int i=0; i<cubes.length; i++){
			listOfCubes[i] = cubes[i].copy();
   		}

	}

    /**
     * Constructor that initializes this solution using specified information
     * @param other, c
     *            receives partial solution (other) and a cube (c)
     */
	public Solution(Solution other, Cube c){

		if(c == null){
			throw new NullPointerException("Cube cannot be null.");
		}

		this.listOfCubes = new Cube[other.listOfCubes.length+1];

		for(int i=0; i < other.listOfCubes.length+1; i++){
			if(i == other.listOfCubes.length){
				this.listOfCubes[i] = c.copy();
			} else {
				this.listOfCubes[i] = other.getCube(i);
			}
		}

		soln = new Solution(this.listOfCubes);

	}

	/**
	* returns the number of cubes that are stored in this solution
	*/
	public int size(){
		int numberOfCubes = 0;
		for(int i=0; i < listOfCubes.length; i++){
			numberOfCubes++;
		}
		return numberOfCubes;
	}

	/**
	* returns the reference of the Cube at the specified position
	*/
	public Cube getCube(int pos){
		return listOfCubes[pos];
	}

	/**
	* returns true if each side of the pile of cubes has no duplicated color and false otherwise
	*/
	public boolean isValid(){
		numberOfCalls++;

		if(size() == 0 || size() == 1){
			return true;
		}

		for(int i=0; i<listOfCubes.length; i++){
			for(int j=0; j<listOfCubes.length; j++){
				if(i != j && getCube(i).getFront().equals(getCube(j).getFront())){
					return false;
				}
				if(i != j && getCube(i).getRight().equals(getCube(j).getRight())){
					return false;
				}
				if(i != j && getCube(i).getLeft().equals(getCube(j).getLeft())){
					return false;
				}
				if(i != j && getCube(i).getBack().equals(getCube(j).getBack())){
					return false;
				}
			}
		}

		return true;

	}

	/**
	* returns true if the solution will remain valid when adding the cube designated
	* by next to the solution, and false otherwise
	*/
	public boolean isValid(Cube next){
		numberOfCalls++;

		if(next==null){
			throw new NullPointerException("Cube cannot be null");
		}

		// if(!this.isValid()){
		// 	return false;
		// }

		for(int i=0; i<listOfCubes.length; i++){
			if(next.getFront().equals(getCube(i).getFront())){
				return false;
			}
			if(next.getRight().equals(getCube(i).getRight())){
				return false;
			}
			if(next.getLeft().equals(getCube(i).getLeft())){
				return false;
			}
			if(next.getBack().equals(getCube(i).getBack())){
				return false;
			}
		}

		return true;

	}

	/**
	* returns the total number of calls to the method isValid of any obj
	* of the class Solution since the last call to the method resetNumberOfCalls()
	*/
	public int getNumberOfCalls(){
		return numberOfCalls;
	}

	/**
	* resets numberOfCalls to 0
	*/
	public void resetNumberOfCalls(){
		numberOfCalls = 0;
	}

	/**
	* returns a String representation of the solution
	*/
	public String toString(){
		String string = "[";

		for(int i=0; i<listOfCubes.length; i++){
			
			string = string + this.getCube(i);
			
			if (i != listOfCubes.length-1){
				string = string + ", ";
			}
		}

		string = string + "]";

		return string;

	}

}

