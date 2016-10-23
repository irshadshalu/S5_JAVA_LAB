/*
Lab 1 : implementation of Stack

*/

import java.util.*;
public class Stack {
	private int[] s;
	private int maxsize,top;
	public Stack(int maxsize){
		this.maxsize=maxsize;
		this.s = new int[maxsize];
		this.top=0;
	}
	public boolean isEmpty(){
		return this.top==0;
	}
	public void push(int element) throws Exception{
		if(this.top == this.maxsize){
			Exception e=new Exception("Stack Overflow");
			throw e;
		}
		else{
			this.s[top]=element;
			this.top++;
		}
	}
	public int pop() throws Exception{
		if(this.top == 0){
			Exception e=new Exception("Stack Underflow");
			throw e;	
		}
		else{
			this.top--;
			return this.s[this.top];
		}
	}
	public static void main(String[] args)  {
		int choice,temp;
		Scanner in = new Scanner(System.in);
		System.out.print(" Enter max stack size : ");
		temp=in.nextInt();
		Stack s = new Stack(temp);
		System.out.print("\n 1. Push\n 2. Pop\n 3. CheckEmpty\n 4. Exit \n\n ");
		do{
			System.out.print("\nEnter Choice : ");
			choice=in.nextInt();
			if(choice==1){
				System.out.print(" \t\t Enter element : ");
				temp = in.nextInt();
				try{
					s.push(temp);
				}
				catch(Exception e){
					e.printStackTrace();	
				}
			}
			else if (choice==2){
			
				
				try{
					System.out.println(" \t\t Popped "+ s.pop());
				}
				catch( Exception e){
					e.printStackTrace();	
				}
					
				
			}
			else if(choice==3)
			{
				if(s.isEmpty())
					System.out.println(" Yes ");
				else
					System.out.println(" No ");
			}
			
				
		}
		while(choice!=4);
	}
	
}
