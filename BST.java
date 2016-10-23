
/*
Lab 2 : implementation of abstract data type - Binary Search Tree

*/
import java.util.*;

class TreeNode{
	int value;
	TreeNode left,parent,right;
	public TreeNode(int value,TreeNode parent){
		this.parent = parent;
		this.value 	= value;
		this.left 	= null;
		this.right 	= null;
	}
}
public class BST{
	public TreeNode root;
	public BST(){
		root = null;
	}
	public void inorder(TreeNode x){
		if(x != null){
			inorder(x.left);
			System.out.println(x.value);
			inorder(x.right);	
		
		}
	}
	public void postorder(TreeNode x){
		if(x != null){
			postorder(x.left);
			postorder(x.right);	
			System.out.println(x.value);
		
		}
	}
	public void preorder(TreeNode x){
		if(x != null){
			System.out.println(x.value);
			preorder(x.left);
			preorder(x.right);	
		
		}
	}
	public void insert(TreeNode z){
		TreeNode x = root,y = null;
		while( x != null ){
			y=x;
			if( z.value < x.value )
				x = x.left;
			else
				x = x.right;
		}
		z.parent = y;
		if(y == null)
			root = z;
		else if( z.value < y.value )
			y.left = z;
		else
			y.right = z;	 
	}
	public TreeNode search(int key){
		TreeNode x = root;
		while( x != null && x.value != key){
			if( key < x.value )
				x = x.left;
			else
				x = x.right;
		}
		return x;
	}
	private void transplant(TreeNode u,TreeNode v){
		if(u.parent == null)
			root = v;
		else if ( u.parent.left == u)
			u.parent.left = v;
		else
			u.parent.right = v;
		if(v != null)
			v.parent = u.parent;
	}
	public void delete(TreeNode z){
		if ( z.right == null )
			transplant(z,z.left);
		else if( z.left == null)
			transplant(z,z.right);
		else{
			TreeNode y = z.right;
			while ( y.left != null )
				y = y.left;
			if(y.parent != z){
				transplant(y,y.right);
				y.right=z.right;
				y.right.parent=z.right;
			}
			transplant(z,y);
			y.left = z.left;
			y.left.parent = y;
		
		}
	}
	public static void main(String[] args){
		int choice,temp;
		Scanner in = new Scanner(System.in);
		BST tree = new BST();
		System.out.print("\n 1. Insert\n 2. Search\n 3. Delete\n 4. Inorder\n 5. Preorder\n 6. Postorder\n 7. Exit \n\n ");
		do{
			System.out.print("\nEnter Choice : ");
			choice=in.nextInt();
			if(choice==1){
				System.out.print(" \t\t Enter element : ");
				temp = in.nextInt();
				tree.insert(new TreeNode(temp,null));
			}
			else if (choice==2){
				System.out.println(" \t\t Enter key to search ");
				temp = in.nextInt();
				if(tree.search(temp)!= null)
					System.out.println(" \t\t Found ");
				else
					System.out.println(" \t\t Not found ");					
			}
			else if(choice==3)
			{
				
				System.out.println(" \t\t Enter value to delete ");
				temp = in.nextInt();
				TreeNode todelete = tree.search(temp);
				if(todelete == null )
					System.out.println(" \t\t Element Not found ");	
				else
					tree.delete(todelete);
		
			}
			else if(choice==4)
			{
				tree.inorder(tree.root);
				System.out.println();
			}
			else if(choice==5)
			{
				tree.preorder(tree.root);
				System.out.println();
			}
			else if(choice==6)
			{
				tree.postorder(tree.root);
				System.out.println();
			}
			
				
		}
		while(choice!=7);
	}
}
