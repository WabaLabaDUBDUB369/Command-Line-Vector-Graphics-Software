package hk.edu.polyu.comp.comp2021.clevis.model;


//Class for creating Squares
@SuppressWarnings("ALL")
public class Square extends Clevis{

	private double sideAlpha, x, y, x1, y1;
	
	public Square(double x, double y, double sideAlpha, String name){
		super(name);
		this.sideAlpha = sideAlpha;
		this.x = x;
		this.y = y;
		this.x1 = x + sideAlpha;
		this.y1 = y + sideAlpha;

	}
	/**
	 * Default constructor for the square object.
	 */


	//Finds Area
	public double findArea() {
		return sideAlpha * sideAlpha;
	}

	//Finds Perimeter
	public double findPerimeter() {
		return 4 * sideAlpha;
	}


	public double getSide(){
		return sideAlpha;
	}
	public double getX(){
		return x;
	}
	public double getY(){
		return y;
	}
	public double getX1(){
		return x1;
	}
	public double getY1(){
		return y1;
	}
	public void moveX(double m) {
		this.x = this.x + m;
	}
	public void moveY(double n) {
		this.y = this.y + n;
	}

	public void setSideAlpha(double sideAlpha){
		this.sideAlpha = sideAlpha;
	}


	@Override
	public String toString() {
		return "Square with Name: " + getId() + "\nArea = " + findArea() + ", \nPerimeter = " + findPerimeter()
		+ "\nSide Length = " + getSide() + "\nTop-left (x,y): ("+ getX() + ", " + getY() + ")\n";
	}
}
