package hk.edu.polyu.comp.comp2021.clevis.model;


//Class for creating rectangles
@SuppressWarnings("ALL")
public class Rectangle extends Clevis{

	private double x, y, w, h, x1, y1;

	public Rectangle(double x, double y, double sideAlpha, double sideBravo, String name){
		super(name);
		this.x = x;
		this.y = y;
		this.w = sideAlpha;
		this.h = sideBravo;
		this.x1 = x + sideAlpha;
		this.y1 = y + sideBravo;
	}

	//Finds Area
	public double findArea() {
		return w * h;
	}

	//Finds Perimeter
	public double findPerimeter() {
		return 2 * w + 2 * h;
	}


	public void setSideBravo(double sideBravo) {
		this.h = sideBravo;
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
	public double getSideAlpha() {
		return w;
	}
	public double getSideBravo() {
		return h;
	}

	
	@Override
	public String toString() {
		return "Rectangle with Name: " + getId() +"\nArea = " + findArea() + ", \nPerimeter = "
				+ findPerimeter() + "\nSideA = " + getSideAlpha() + ", \nSideB = " + getSideBravo() + "\nTop-left (x,y): ("+ getX() + ", " + getY() + ")\n";
	}
}
