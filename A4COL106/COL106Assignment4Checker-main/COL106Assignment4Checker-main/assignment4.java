import java.io.*;
import java.util.ArrayList;
import java.util.Vector;
import java.util.LinkedList;
import java.util.HashMap;
import java.util.Scanner;
import java.util.Map;

public class assignment4 {
	public static void sortmerge(ArrayList<vertex> node,int low,int high){
		if (low<high){
		
			int mid=(low+high)/2;
			sortmerge(node,low,mid);
			sortmerge(node,mid+1,high);
			merge(node,low,mid,high);
		}
	}
	public static void merge(ArrayList<vertex> node,int low,int mid,int high){
		int n1=mid-low+1;
		int n2=high-mid;
		vertex L[]=new vertex[n1];
		vertex R[]=new vertex[n2];
		for (int i1=0;i1<n1;i1++){
			L[i1]=node.get(low+i1);
		}
		for (int i2=0;i2<n2;i2++){
			R[i2]=node.get(mid+i2+1);
		}
		int a1=0,a2=0;
		int a3=low;
		while(a1<n1 && a2<n2){
			if(L[a1].rank<R[a2].rank){
				node.set(a3,L[a1]);
				a1++;
			}
			else if(L[a1].rank==R[a2].rank){
				if (L[a1].label.compareTo(R[a2].label)<0){
					node.set(a3,L[a1]);
					a1++;
			
				}
				else{
					node.set(a3,R[a2]);
					a2++;
				}
			
			}
			else{
				node.set(a3,R[a2]);
				a2++;
			}
			a3++;
		}
		while(a1<n1){
			node.set(a3,L[a1]);
			a1++;
			a3++;
		}
		while(a2<n2){
			node.set(a3,R[a2]);
			a2++;
			a3++;
		}
		

	}

	public static void main(String[] args)throws Exception{
		Scanner n = new Scanner(new File(args[0]));
		Scanner e = new Scanner(new File(args[1]));
		String func=args[2];
		ArrayList<vertex> node=new ArrayList<vertex>();
		HashMap <String,Integer> map=new HashMap<>();
		n.nextLine();
		int i=0;
		while (n.hasNextLine()){
			vertex v=new vertex();
			String vl=n.nextLine().split((",(?=(?:[^\"]*\"[^\"]*\")*[^\"]*$)"),-1)[1];
			if(vl.charAt(0)=='"'){
				v.label=vl.substring(1,vl.length()-1);
			}
			else{
				v.label=vl;
			}
			map.put(v.label,i);
			node.add(v);
			i++;
		}
		ArrayList <edge> arr=new ArrayList<edge>();
		e.nextLine();
		while (e.hasNextLine()){
			edge a=new edge();
			String[] token=e.nextLine().split((",(?=(?:[^\"]*\"[^\"]*\")*[^\"]*$)"),-1);
			a.wt=Integer.parseInt(token[2]);
			String source=token[0];
			String target=token[1];
			if(source.charAt(0)=='"'){
				a.src=source.substring(1,source.length()-1);
			}
			else{
				a.src=source;
			}
			if(target.charAt(0)=='"'){
				a.tgt=target.substring(1,target.length()-1);
			}
			else{
				a.tgt= target;
			}
			arr.add(a);
		}
		graph g=new graph(node,arr,map);

		if (func.equals("average")){
			double avg=0.00;
			for (int k=0;k<g.adj.length;k++){
				avg+=g.adj[k].size();
			}
			avg=avg/g.adj.length;
			System.out.println(String.format("%.2f",avg));
		}
		if (func.equals("rank")){
			sortmerge(node,0,node.size()-1);
			int b=0;
			int nodesize=node.size();
			for ( b=0;b<node.size()-1;b++){
				System.out.print(node.get(nodesize-b-1).label+',');
			}
			System.out.println(node.get(0).label);
			

		}

	}

}

class vertex{
	public int rank;
	public String label;
}	
class edge{
	public int wt;
	public String src;
	public String tgt;
}
class graph{
	public ArrayList<edge> [] adj;

	public graph(ArrayList<vertex> node,ArrayList <edge> arr,HashMap <String,Integer> map){
		int q=node.size();
		adj=new ArrayList[q];
		for (int nodeindex=0;nodeindex<q;nodeindex++){
			ArrayList<edge> l=new ArrayList<> ();
			adj[nodeindex]=l;

		}
		for (int i=0;i<arr.size();i++){
			int j=map.get(arr.get(i).src);
			int tindex=map.get(arr.get(i).tgt);
			adj[j].add(arr.get(i));
			adj[tindex].add(arr.get(i));
			node.get(tindex).rank+=arr.get(i).wt;
			node.get(j).rank+=arr.get(i).wt;
		}
	}
} 