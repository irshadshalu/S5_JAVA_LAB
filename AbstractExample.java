/*
Lab 3 :Concept of abstract class and inheritance, 

*/
import java.util.*;
import java.math.*;
abstract class Shape{
	public int a,b;
	abstract public double Area();
	abstract public double Perimeter();
	
	public void print(String s){
		System.out.println(s + "'s Area : " + this.Area());
		System.out.println(s + "'s Perimeter : " + this.	Perimeter());
	}
		
}

class Rectangle extends Shape {
	Rectangle(int length,int width){
		a=length;
		b=width;
	}
	public double Area(){
		return a*b;
	}
	public double Perimeter(){
		return 2*(a+b);
	}
}

class Square extends Rectangle {
	Square(int side){
		super(side,side);
	}
}

class Ellipse extends Shape {
	Ellipse(int a,int b){
		this.a=a;
		this.b=b;
	}
	public double Area(){
		return Math.PI*a*b;
	}
	public double Perimeter(){
		return 2*Math.PI*Math.sqrt((a*a+b*b)/2);
	}
}

class Circle extends Ellipse {
	Circle(int radius){
		super(radius,radius);
	}
}

class AbstractExample {
	public static void main(String[] args) {
		Shape ref;
		Square s = new Square(5);
		Circle c = new Circle(5);
		Ellipse e = new Ellipse(3,4);
		Rectangle r = new Rectangle(3,4);
		ref=s;
		ref.print("Square");
		ref=c;
		ref.print("Circle");
		ref=e;
		ref.print("Ellipse");
		ref=r;
		ref.print("Rectangle");
	}
	
}
