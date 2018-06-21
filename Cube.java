public class Cube{

	private Color up, front, right, back, left, down;
	private Color[] copyOfFaces;
	private int numberOfOrientations;


    /**
     * Constructor to create a new cube with specific colours for its faces
     * @param faces
     *            array specifies the colors in the following order: up, front, right, back, left, down
     */
	public Cube(Color[] faces){
		up = faces[0];
		front = faces[1];
		right = faces[2];
		back = faces[3];
		left = faces[4];
		down = faces[5];

		copyOfFaces = new Color[faces.length];

		for(int i=0; i<faces.length; i++){
			copyOfFaces[i] = faces[i];
		}

		numberOfOrientations = 0;
	}

    /**
     * Constructor initializes this cube to be an identical but independent copy of other
     * @param other
     *            another Cube, designated by other
     */
	public Cube(Cube other){

		this.up = other.getUp();
		this.front = other.getFront();
		this.right = other.getRight();
		this.back = other.getBack();
		this.left = other.getLeft();
		this.down = other.getDown();

		copyOfFaces = new Color[6];

		copyOfFaces[0] = up;
		copyOfFaces[1] = front;
		copyOfFaces[2] = right;
		copyOfFaces[3] = back;
		copyOfFaces[4] = left;
		copyOfFaces[5] = down;

	}

	/**
	* returns up
	*/
	public Color getUp(){
		return up;
	}

	/**
	* returns front
	*/
	public Color getFront(){
		return front;
	}

	/**
	* returns right
	*/
	public Color getRight(){
		return right;
	}

	/**
	* returns back
	*/
	public Color getBack(){
		return back;
	}
	
	/**
	* returns left
	*/
	public Color getLeft(){
		return left;
	}

	/**
	* returns down
	*/
	public Color getDown(){
		return down;
	}

	/**
	* returns a String of the state of the cube in the following order:
	* [up, front, right, back, left, down]
	*/
	public String toString(){
		String string;

		string = "[" + this.getUp() + ", " + this.getFront() + ", " +
		this.getRight() + ", " + this.getBack() + ", " + this.getLeft() + ", "
		+ this.getDown() + "]";

		return string;

	}

	/**
	* returns true if and only if a call to the method next would succeed, and false otherwise
	*/
	public boolean hasNext(){

		if(numberOfOrientations > 24){
			return false;
		}
		return true;
	}

	/**
	* each call to next() changes the orientation of the cube
	*/
	public void next(){

		if(numberOfOrientations == 0){
			this.identity();
		}
		else if(numberOfOrientations > 24){
			throw new IllegalStateException("No more transformations left");
		}
		else if(numberOfOrientations == 4 || numberOfOrientations == 8 || numberOfOrientations == 20){
			this.rightRoll();
		}
		else if(numberOfOrientations == 12 || numberOfOrientations == 16){
			this.leftRoll();
		} else {
			this.rotate();
		}
	}

	/**
	* puts the cube back in its original orientation (the orientation the cube had
	* when it was first created)
	*/
	public void reset(){
		numberOfOrientations = 0;
		this.identity();
	}

	/**
	* rotates the cube to the right around the top-bottom axis
	* so that the left side is now facing front
	*/
	private void rotate(){
		Color temp;
		temp = front;
		front = left;
		left = back;
		back = right;
		right = temp;
		numberOfOrientations++;
		
	}

	/**
	* rolls the cube to the right around the back-front axis
	* so that the left side is now up
	*/
	private void rightRoll(){
		Color temp;
		temp = up;
		up = left;
		left = down;
		down = right;
		right = temp;
		numberOfOrientations++;
	}

	/**
	* rolls the cube to the left around the back-front axis
	* so that the right side is now up
	*/
	private void leftRoll(){
		Color temp;
		temp = up;
		up = right;
		right = down;
		down = left;
		left = temp;
		numberOfOrientations++;
	}

	/**
	* returns all the faces to their original state (colours)
	*/
	private void identity(){
		up = copyOfFaces[0];
		front = copyOfFaces[1];
		right = copyOfFaces[2];
		back = copyOfFaces[3];
		left = copyOfFaces[4];
		down = copyOfFaces[5];
		numberOfOrientations++;	
	}


	/**
	* returns a deep copy of the Cube
	*/
	public Cube copy(){
		Cube newCube;
		newCube = new Cube(this);
		return newCube;
	}

}