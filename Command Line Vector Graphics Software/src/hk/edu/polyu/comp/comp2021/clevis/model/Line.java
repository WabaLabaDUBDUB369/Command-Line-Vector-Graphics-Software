package hk.edu.polyu.comp.comp2021.clevis.model;


//Class for creating lines
@SuppressWarnings("ALL")
public class Line extends Clevis{
	
	private double x1, x2, y1, y2, length;
	private double minx1, minx2, miny1, miny2, corrY;


	public Line(double x1, double y1, double x2, double y2, String name){
		super(name);
		this.x1 = x1;
		this.y1 = y1;
		this.x2 = x2;
		this.y2 = y2;
		this.length = Math.sqrt(Math.pow((x1 - y1), 2) + Math.pow((x2 - y2), 2));
		if(x1<=x2) {
			this.minx1= x1;
			this.minx2 = x2;
			this.corrY = y1;
		}else {
			this.minx1 = x2;
			this.minx2 = x1;
			this.corrY = y2;
		}
		if(y1<=y2) {
			this.miny1= y1;
			this.miny2 = y2;
		}else {
			this.miny1 = y2;
			this.miny2 = y1;
		}
		
		
		
	}

	//Finds Area
	public double findArea() {
		return 0;
	}

	//Finds Perimeter
	public double findPerimeter() {
		return 0;
	}	
	
	
	public double getX1(){
		return x1;
	}
	public double getY1(){
		return y1;
	}
	public double getX2(){
		return x2;
	}
	public double getY2(){
		return y2;
	}
	
	
	public double getminX1(){
		return minx1;
	}
	public double getminY1(){
		return miny1;
	}
	public double getminX2(){
		return minx2;
	}
	public double getminY2(){
		return miny2;
	}
	
	public double getcorrY(){
		return corrY;
	}
	
	public void moveX1(double m){
		this.x1 = this.x1 + m;
	}
	public void moveY1(double n){
		this.y1 = this.y1 + n;
	}
	public void moveX2(double m){
		this.x2 = this.x2 + m;
	}
	public void moveY2(double n){
		this.y2 = this.y2 + n;
	}	
	public double getLen(){
		return length;
	}
	

	@Override
	public String toString() {
		return "Line with Name: " + getId() + "\nLine Length = " + getLen() 
		+ "\n(X1, Y1): ("+ getX1() + ", " + getY1() + ")"+ "\n(X2, Y2): ("+ getX2() + ", " + getY2() + ")\n";
	}
}

