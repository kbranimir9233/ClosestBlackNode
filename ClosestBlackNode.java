import java.io.*;
import java.text.DecimalFormat;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
//import java.io.File;  // Import the File class
//import java.io.FileNotFoundException;  // Import this class to handle errors
import java.util.Scanner; // Import the Scanner class to read text files
//import java.io.FileWriter;
//import java.io.IOException;

class Graf3 {
    private int V;
    private LinkedList<Integer>[] adj;
    private boolean[] isBlack;
    LinkedList<Integer> vis = new LinkedList();

    public Graf3(int V, boolean[] isBlack) {
        this.V = V;
        adj = new LinkedList[V];
        for (int i = 0; i < V; i++) {
            adj[i] = new LinkedList<>();
        }
        this.isBlack = isBlack;
    }
    
    public void addEdge(int v, int w) {
        adj[v].add(w);
        adj[w].add(v);
    }
    
    public Integer[] BFS(int source) {
    	for (int i = 0; i < V; i++) {
            adj[i].sort(null);
        }
    	Integer[] defaultReturn = {-1, -1};
    	LinkedList<Integer>[] graph = adj;
    	LinkedList<Integer[]> Q = new LinkedList<>();
    	LinkedList<Integer> closestNodes = new LinkedList();
    	int minDistance=100;
    	
    	Integer[] fQueueInt= {source, 0}; 
    	Integer[] ndIntArr;
    	Integer[] Retv= {-1,-1};
    	Q.add(fQueueInt);
    	vis.add(source);
    	int node;
    	int distance;
    	while(!Q.isEmpty()) {
    		ndIntArr=Q.poll();
    		node=ndIntArr[0];
    		distance=ndIntArr[1];
    		
    		if(isBlack[node]) {
    			if(distance<=minDistance) {
    				minDistance = distance;
    				closestNodes.add(node);
    				
    			}
    			if(distance>minDistance) {
    				break;
    			}    			
    			/*vis.clear();
    			Q.clear();
    			return ndIntArr;*/
    		}
    		
    		//for neighbor in graph[node]: - TU SAM STAO!!!
    		for(int n=0; n< graph[node].size(); n++) {
    			int neighbor = graph[node].get(n);
    			if(!vis.contains(neighbor)) {
    				vis.add(neighbor);
    				Integer[] ndIntArr2 = {neighbor, distance+1};
    				Q.add(ndIntArr2);
    			}
    		}
    	}
    	
    	vis.clear();
		Q.clear();
		closestNodes.sort(null);
		
    	//return defaultReturn;
		if(closestNodes.isEmpty()) {
			return defaultReturn;
		}
		else {
			Retv[0] = closestNodes.getFirst();
			Retv[1] = minDistance;
			return Retv;
		} 
    }
}

public class ClosestBlackNode {
	
	static boolean[][] M;
    static boolean[] cvorjeCrn;
    static int N;
    
    public static void main(String[] args) {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String data1 = null;
	
	
	      //File myObj = new File("R.in");
	      //Scanner myReader = new Scanner(myObj);
	      		
			try {
				data1 = br.readLine();
			} catch (IOException ioe) {System.out.println(ioe);} 
	      
	      String[] firstLineArr = data1.split(" ");
	      int N = Integer.parseInt(firstLineArr[0]);
	      int e = Integer.parseInt(firstLineArr[1]) ;
	      
	      boolean [] cvorjeCrn = new boolean[N];
	      
	      int [][] izlaz = new int [N][2];
	      
	      String[] linija = new String[2];
    	  String linija2 = new String();
    	  String [] linija3 = new String[2];
	      
	      for(int i = 0; i<N; i++) {
	    	  try {
		    	  data1 = br.readLine();
		      } catch (IOException ioe) {System.out.println(ioe);} 
		      
	    	  linija = data1.split("\n");
	    	  linija2 = linija[0];
	    	  if(Integer.parseInt(linija2)==0) cvorjeCrn[i] = false;
	    	  else cvorjeCrn[i]=true;
	      }
	      
	      Graf3 graph = new Graf3(N, cvorjeCrn);
	      for(int i = 0; i<e; i++) {
	    	  try {
		    	  data1 = br.readLine();
		      } catch (IOException ioe) {System.out.println(ioe);} 
		      
	    	  linija = data1.split("\n");
	    	  linija2 = linija[0];
	    	  linija3 = linija2.split(" ");
	    	  graph.addEdge(Integer.parseInt(linija3[0]), Integer.parseInt(linija3[1]));
	      }
	      
	      Integer[] closestBlackNode;
	      
	      for(int i = 0; i<N; i++) {
	      	if(cvorjeCrn[i]==true) {
	    		  izlaz[i][0]=i;
	    		  izlaz[i][1]=0;
	    	  }
	    	  else {
	    		  int source = i;
	    		  closestBlackNode = graph.BFS(source);
	    		  izlaz[i][0]= closestBlackNode[0];
	    		  izlaz[i][1]= closestBlackNode[1];
	    		  
	    		  
	    	  }
	    	  
	      }
	      
	      for(int i = 0; i<N; i++) {
	    	  System.out.println(izlaz[i][0]+" "+izlaz[i][1]);
	      }
	      
	}		
	
}
