package Utillities;

public class Point {
	/*Point class
	 * represents a point in space*/
	
	//attributes
	private int x;
	private int y;
	
	//Ctor
	public Point() {
		this.x = 0;
		this.y = 0;
	}
	
	//Ctor
	public Point(int newX, int newY) {
		this.x = newX;
		this.y = newY;
	}
	
	//set x of point
	public void setX(int newX) {
		this.x = newX;
	}
	
	//set y of point
	public void setY(int newY) {
		this.y = newY;
	}
	
	//get x value
	public int getX() {
		return this.x;
	}
	
	//get y value
	public int getY() {
		return this.y;
	}
}
