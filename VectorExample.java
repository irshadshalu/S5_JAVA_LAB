/*

Lab 4  Polymorphism : Define base class Vector and use it to inherit for real and complex vectors with standard operations
	and use base class object to display them.

*/
import java.util.*; 
import java.math.*;
abstract class Vector{
	public int length;
	abstract Vector add(Vector a);
	abstract double[] getReal();
	abstract double[] getImaginary();
	abstract Vector sub(Vector a);
	
}
class RealVector extends Vector{
	public double[] real;
	public void input(Scanner in){

		System.out.println("Enter number of elements: ");
		int n = in.nextInt();
		this.length = n;
		real = new double[n];
		System.out.println("Enter elements: ");
		
		for (int i=0; i<n; i++) {
			real[i]=in.nextFloat();
		}
	}
	public  void setReal(double[] real){
		this.real=real;
	}
	public  double[] getReal(){
		return this.real;
	}
	public  double[] getImaginary(){
		return new double[this.length];
	}
	public String toString(){
		String res="[";
		for (int i=0;i<real.length ; i++ ) {
			res+=(String.format("%.2f", real[i])+", ");
		}
		res+="]";
		return res;
	}
	public Vector add(Vector a){
		double[] res;
		if(this.length!= a.length)
		{
			System.out.println(" Incompatible vectors");
			return null;
		}
		else {
			double[] temp=a.getReal();
			res = new double[this.length];
			for (int i=0; i<this.length; i++) {
				res[i]=this.real[i]+temp[i];
			}
		}
		RealVector r=new RealVector();
		r.setReal(res);
		return r;

	}
	public Vector sub(Vector a){
		double[] res;
		if(this.length!= a.length)
		{
			System.out.println(" Incompatible vectors");
			return null;
		}
		else {
			double[] temp=a.getReal();
			res = new double[this.length];
			for (int i=0; i<this.length; i++) {
				res[i]=this.real[i]-temp[i];
			}
		}
		RealVector r=new RealVector();
		r.setReal(res);
		return r;

	}

}
class ComplexVector extends RealVector{
	public double[] imaginary;
	public void input(Scanner in){

		System.out.println("Enter number of elements: ");
		int n = in.nextInt();
		this.length = n;
		real = new double[n];
		imaginary = new double[n];
		System.out.println("Enter elements: A+iB ");
		
		for (int i=0; i<n; i++) {
			real[i]=in.nextFloat();
			imaginary[i]=in.nextFloat();
		}
	}
	public  void setImaginary(double[] imaginary){
		this.imaginary=imaginary;
	}
	public  double[] getImaginary(){
		return this.imaginary;
	}
	public String toString(){
		String res="[";
		for (int i=0;i<real.length ; i++ ) {
			res+=(String.format("%.2f", real[i])+" + i"+String.format("%.2f", imaginary[i])+", ");
		}
		res+="]";
		return res;
	}
	public Vector add(Vector a){
		double[] resreal,resimg;

		if(this.length!= a.length)
		{
			System.out.println(" Incompatible vectors");
			return null;
		}
		else {
			double[] tempreal=a.getReal();
			double[] tempimg=a.getImaginary();
			resreal = new double[this.length];
			resimg = new double[this.length];
			for (int i=0; i<this.length; i++) {
				resreal[i]=this.real[i]+tempreal[i];
				resimg[i]=this.imaginary[i]+tempimg[i];
			}
		}
		ComplexVector r=new ComplexVector();
		r.setReal(resreal);
		r.setImaginary(resimg);
		return r;

	}

}

class VectorExample {
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		Vector ref1,ref2;
		ComplexVector cv = new ComplexVector();
		RealVector rv = new RealVector();
		System.out.println(" Enter RealVector: ");
		rv.input(in);

		System.out.println(" Enter ComplexVector: ");
		cv.input(in);
		
		ref1=cv;
		ref2=rv;
		System.out.println(ref1.add(ref2));
	}
	
}
