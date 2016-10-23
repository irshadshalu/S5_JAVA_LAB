/*
Lab 1 : implementation of Queue

*/
import java.util.*;

public class Queue {
	private int[] q;
	private int rear,front,maxsize;
	public Queue(int maxsize){
		this.maxsize=maxsize+1;
		this.rear=0;
		this.front=0;
		q=new int[maxsize+1];
	}
	public boolean isEmpty(){
		return this.rear==this.front;
	}
	public void enqueue(int element) throws Exception {
		if(this.front == (this.rear+1)%maxsize){
		
			Exception e= new Exception(" \t\t\t Queue Overflow ");
			throw e;
		}
		else{
			this.q[rear]=element;
			this.rear= (this.rear+1)%maxsize;
		}
	}
	public int dequeue() throws Exception {
		if(isEmpty()){
			Exception e= new Exception(" \t\t\t Queue Underflow ");
			throw e;
		}
		else{
			int temp= this.q[this.front];
			this.front= (this.front+1)%maxsize;
			return temp;
		}
	}
	public static void main(String[] args) {
		int choice,temp;
		Scanner in = new Scanner(System.in);
		System.out.print(" Enter max queue size : ");
		temp=in.nextInt();
		Queue a = new Queue(temp);
		System.out.print("\n 1. Enqueue\n 2. Dequeue\n 3. Isempty\n 4. Exit \n\n ");
		do{
			System.out.print("\n  Enter Choice : ");
			choice=in.nextInt();
			if(choice==1){
				System.out.print(" \t\t\t Enter element : ");
				temp = in.nextInt();
				try{
				a.enqueue(temp);
				}
				catch(Exception e){
					e.printStackTrace();
				}
				
			}
			else if (choice==2){
			
				try{
					System.out.println(" \t\t\t Removed "+ a.dequeue());
				}
				catch(Exception e){
					e.printStackTrace();
				}
				
			}
			else if(choice==3)
			{
				if(a.isEmpty())
					System.out.println(" \t\t\t Yes ");
				else
					System.out.println(" \t\t\t No ");
			}
			else 
				break;
			
				
		}
		while(choice!=4);
	}
	
}
