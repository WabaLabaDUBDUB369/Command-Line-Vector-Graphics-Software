package hk.edu.polyu.comp.comp2021.clevis.model;


//Class for creating circles
@SuppressWarnings("ALL")
public class Circle extends Clevis{
	
	private double x, y, x1, y1;
	private double radius;

	public Circle(double x1, double y1, double radius, String name){
		super(name);
		this.x = x1;
		this.y = y1;
		this.radius = radius;
		this.x1 = x1 + radius;
		this.y1 = y1 + radius;
	}	
	

	//Finds Area
	public double findArea() {
		return Math.PI * Math.pow(radius, 2);
	}

	//Finds Perimeter
	public double findPerimeter() {
		return Math.PI * (2 * radius);
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
	
	
	public void moveX1(double m){
		this.x = this.x + m;
	}
	public void moveY1(double n){
		this.y = this.y + n;
	}
	public double getRad(){
		return radius;
	}

	@Override
	public String toString() {
		return "Circle with Name: " + getId() + "\nCircle Radius = " + getRad() + "\nCenter (x,y): ("+ getX() + ", " + getY() + ")\n";
	}
}

