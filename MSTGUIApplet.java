/*
Run by 
javac MSTGUIApplet.java
appletviewer MSTGUIApplet.java


Define a point class and an arc class. Define a Graph class which represents graph as 
a collection of points and arc. write a method to find a minimum cost spanning tree in 
a graph and display it.

input format 
[No. of Nodes] [no. of edges]
each edges as 
[from] [to] [weight]

NB. from and to are 0-indexed!
sample :

5 6
1 2 5
3 2 6
0 1 4
2 3 6
2 4 8
0 4 1

output will be displayed below,(ignore the output field) MST with blue lines.

<applet code="MSTGUIApplet.class" width=800 height=800>
</applet>
*/
import java.applet.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.*;
class Point{
	public int x,y,id;
	public Point(int x,int y,int id){
		this.x=x;
		this.y=y;
		this.id=id;
		
	}
}
class Arc implements Comparable<Arc>{
	public int from,to,weight;
	public Arc(int from,int to,int weight){
		this.from=from;
		this.to=to;
		this.weight=weight;
	}
    public int compareTo(Arc o) {
        return this.weight-o.weight;
    }
}


class UF{
  	private int[] parent; 
    private int[] rank;  
    private int count;    
    public UF(int n){
    	this.count=n+1;
    	parent = new int[n+1];
    	rank = new int[n+1];
    	for(int i=0;i<n+1;i++){
    		parent[i]=i;
    		rank[i]=0;
    	}
    }
    public int find(int p) {
        while (p != parent[p]) {
            parent[p] = parent[parent[p]];    
            p = parent[p];
        }
        return p;
    }
    public void union(int p, int q) {
        int rootP = find(p);
        int rootQ = find(q);
        if (rootP == rootQ) return;

        if      (rank[rootP] < rank[rootQ])
        	parent[rootP] = rootQ;
        else if (rank[rootP] > rank[rootQ])
       		parent[rootQ] = rootP;
        else {
            parent[rootQ] = rootP;
            rank[rootP]++;
        }
        count--;
    }

}
 class Graph{
	public int N,E;
	public Point nodes[];
	public Arc edges[];
	public Graph(int n,int e,Scanner in){
		this.N = n;
		this.E = e;
		nodes = new Point[n];
		edges = new Arc[e];
		int j=20;
		Random rnd=new Random();
		for(int i=0;i<n;i++){
			nodes[i] = new Point(Math.abs((i%3)*70+rnd.nextInt()%40),j+Math.abs(rnd.nextInt()%40),i);
			if(i%3==2)
				j+=80;
		}
		for(int i=0;i<e;i++){
			int from = in.nextInt();
			int to = in.nextInt();
			int dist =  in.nextInt();
			edges[i]=new Arc(from,to,dist);
		}
	}


	public int[] mst(){
		UF uf = new UF(this.N);
		int[] res= new int[this.E];
		int c=0;
		Arrays.sort(this.edges);
		for(int i=0;i<this.E;i++){
			if(uf.find(edges[i].from) != uf.find(edges[i].to)){
				res[c++]=i;
				uf.union(edges[i].from,edges[i].to);
			}
		}
		int[] toreturn = new int[c];
		for(int i=0;i<c;i++)
			toreturn[i]=res[i];
		return toreturn;
	}

	public String toString(){
		String r="";
		r+="Number of Nodes : "+N+"\n";
		r+="Number of Edges : "+E+"\n";
		r+="Edges : \n";
		for(int i=0;i<E;i++){
			r+=(edges[i].from+" "+edges[i].to+"\n");
		}
		r+="Nodes : \n";
		for(int i=0;i<N;i++){
			r+=(nodes[i].x+" "+nodes[i].y+" "+nodes[i].id+"\n");
		}
		return r;
	}
}
public class MSTGUIApplet extends Applet implements ActionListener  {
	TextArea input,output;
	Graph graph;
	public void init(){
		input=new TextArea(10,20);
		output=new TextArea(10,20);
		add(input);
		add(output);
		
		Button enter=new Button("Display");
		add(enter);
		enter.addActionListener(this);


	}
	public void actionPerformed(ActionEvent evt) {
		Button b = (Button)evt.getSource();
		String str=input.getText();
		InputStream is = new ByteArrayInputStream(str.getBytes());
		Scanner in=new Scanner(is);
		int n=in.nextInt();
		int e=in.nextInt();
		graph=new Graph(n,e,in);
		output.setText(graph.toString());
		repaint();
	}

	public void paint(Graphics g)
	{
		Color green=new Color(55,255,200),black=new Color(0,0,0);
		Color red=new Color(255,0,0),blue=new Color(0,20,200);
		// g.drawRect(250+i,250+i,100+i,100+i);
		// g.fillOval(100+i,100+i,50+i,50+i);
		// g.drawLine(50+i,20+i,10+i,10+i);

		for(int i=0;i<graph.N;i++){
			g.setColor(green);
			g.fillOval(100+graph.nodes[i].x,200+graph.nodes[i].y,20,20);
			g.setColor(black);
			g.drawString(i+" ",106+graph.nodes[i].x,215+graph.nodes[i].y);
		}
		for(int i=0;i<graph.E;i++){
			g.setColor(red);
			int from=graph.edges[i].from;
			int to=graph.edges[i].to;
			int x1=110+graph.nodes[from].x;
			int y1=210+graph.nodes[from].y;
			int x2=110+graph.nodes[to].x;
			int y2=210+graph.nodes[to].y;
			g.drawLine(x1,y1,x2,y2);
			g.setColor(black);
			g.drawString(graph.edges[i].weight+" ",(x1+x2)/2,(y1+y2)/2);

		}
		int mst[] = graph.mst();
		g.setColor(blue);
		for(int i=0;i<mst.length;i++){
			int from=graph.edges[mst[i]].from;
			int to=graph.edges[mst[i]].to;
			int x1=110+graph.nodes[from].x;
			int y1=210+graph.nodes[from].y;
			int x2=110+graph.nodes[to].x;
			int y2=210+graph.nodes[to].y;
			g.drawLine(x1,y1,x2,y2);

		}

	}
}