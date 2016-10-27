/*

q?. Define a point class and an arc class. Define a Graph class which represents graph as 
a collection of points and arc. write a method to find a minimum cost spanning tree in 
a graph and display it.


Details

Run by 
	javac MSTGUIFrame.java
	java MSTGUIFrame



input format 
	[No. of Nodes] [no. of edges]
	then each edges as 
	[from] [to] [weight]

NB. from and to are 0-indexed!
sample :

	5 6
	1 2 5
	3 2 6
	0 1 4
	2 0 6
	2 4 8
	0 4 1

output will be displayed, Try moving windows in case it is not displayed. 
ctrl+c on terminal to close :p

*/

// we start by importing necessary classes.
import java.awt.*;
import java.io.*;
import java.util.*;
// we define point class as given in question.
class Point{
	public int x,y,id;
	public Point(int x,int y,int id){
		this.x  = x;
		this.y  = y;
		this.id = id;
	}
}
// we define arc class as in question
// we need to implement Comparable interface to sort edges for Kruskal algorithm.
class Arc implements Comparable<Arc>{
	public int from,to,weight;
	public Arc(int from,int to,int weight){
		this.from   = from;
		this.to     = to;
		this.weight = weight;
	}
	// we need to sort edges by non decreasing order of weight. hence the compareTo function.
    public int compareTo(Arc o) {
        return this.weight - o.weight;
    }
}

//Graph class (with node array and edge array, as asked in question 
 class Graph{

 	// n = total number of nodes, e = total Number of edges.
	public int n,e;
	public Point nodes[];
	public Arc edges[];

	//  Graph constructor
	public Graph(int n,int e,Arc edges[]){

		this.n = n;
		this.e = e;
		this.edges = edges;
		
		nodes = new Point[this.n + 1];
		
		// circularly filing coordinates of nodes  , can also use random!.
		int angle = 0;
		for(int i=0; i < this.n ; i++){

			//little bit mathematics :p... to get circular coordinates , 
			// x = r*cos(angle)+j  
			// y = r*sin(angle)+k       for a circle with centre (j,k) and radius r. 
			int x = (int) (100 * Math.cos(angle * (Math.PI / 180)) + 100.0);
			int y = (int) (100 * Math.sin(angle * (Math.PI / 180)) + 100.0);
			nodes[i] = new Point(x,y,i);
			angle = angle + 360/n;
		}
	}

	// method mst to return edge list of MST. 
	// kruskals algorithm as seen  in CLRS ( cormen text )
	public int[] mst(){

		//  constructor for Union and find Data structure.
		UF    uf  = new UF(n);
		// result array
		int[] res = new int[e];

		// c = count of total edges in MST.
		int c = 0;
		Arrays.sort(edges);
		for(int i = 0; i < e; i++ ){
			if(uf.find(edges[i].from) != uf.find(edges[i].to)){
				res[c++]=i;
				uf.union(edges[i].from,edges[i].to);
			}
		}
		// copying result to new array of c size.
		int[] temp = new int[c];
		for(int i=0;i<c;i++)
			temp[i] = res[i];
		return temp;
	}
}


// Union and Find data structure is required for Kruskal's algorithm. ( makeset, find)
// THe algos are as from wikipedia.
// This is a basic UF structure without path compression,etc. 
// We can improve this by union by rank and find my path compression!( which is used in applet code )

	class UF{
		private int[] parent;
	private int total;   
	public UF(int n){
		this.total=n+1;
		parent = new int[n+1];

		// initial makeset on constructor
		for(int i=0;i<n+1;i++){
			parent[i]=i;
		}
	}
	// recursive find function
	public int find(int x){
		if(parent[x]==x)
			return x;
		else
			return find(parent[x]);
	}
	public void union(int x,int y){
		int xRoot = parent[x];
		int yRoot = parent[y];
		parent[xRoot] = yRoot;	
	}
}


// Our main GUI class
public class MSTGUIFrame extends Frame {

	// graph defined static to access from main
	private static Graph graph;
	public void paint(Graphics g){

		// defining our colours
		Color red = new Color(255,0,0);
		Color green = new Color(0,255,0);
		Color blue = new Color(0,0,255);
		Color black = new Color(0,0,0);

		g.setColor(black);
		g.drawString("Output Graph,MST in blue colour",100,80);
		// Drawing input graph
		// Drawing nodes
		for(int i=0; i < graph.n; i++){
			g.setColor(green);
			g.fillOval(100+graph.nodes[i].x,100+graph.nodes[i].y,20,20);
			g.setColor(black);
			g.drawString(i+" ",106+graph.nodes[i].x,115+graph.nodes[i].y);
		}

		// Drawing Edges
		for(int i=0 ; i < graph.e ;i++){
			g.setColor(red);
			int from=graph.edges[i].from;
			int to=graph.edges[i].to;
			int x1=110+graph.nodes[from].x;
			int y1=110+graph.nodes[from].y;
			int x2=110+graph.nodes[to].x;
			int y2=110+graph.nodes[to].y;
			g.drawLine(x1,y1,x2,y2);
			g.setColor(black);
			g.drawString(graph.edges[i].weight+" ",(x1+x2)/2,(y1+y2)/2);

		}

		// Drawing MST lines in blue
		int mst[] = graph.mst();
		g.setColor(blue);
		for(int i=0;i<mst.length;i++){
			int from=graph.edges[mst[i]].from;
			int to=graph.edges[mst[i]].to;
			int x1=110+graph.nodes[from].x;
			int y1=110+graph.nodes[from].y;
			int x2=110+graph.nodes[to].x;
			int y2=110+graph.nodes[to].y;
			g.drawLine(x1,y1,x2,y2);
		}
	}
	public static void main(String[] args){
		Scanner in = new Scanner(System.in);
		int n,e;
		System.out.println("Enter number of nodes");
		n = in.nextInt();
		System.out.println("Enter number of edges");
		e = in.nextInt();
		Arc edges[]=new Arc[e];
		System.out.println("Enter edges : \nfrom to weight ( 0-indexed ) ");
		for(int i = 0; i < e; i++ ){
			int from,to,weight;
			from = in.nextInt();
			to = in.nextInt();
			weight = in.nextInt();
			edges[i] = new Arc(from,to,weight);
		}
		graph = new Graph(n,e,edges);
		// starting GUI.
		MSTGUIFrame f =  new MSTGUIFrame();
		f.setSize(500,500);
		f.setVisible(true);
	}
}



// Bugs? mail irshadpi77@gmail.com