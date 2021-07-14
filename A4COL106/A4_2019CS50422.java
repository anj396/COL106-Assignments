import java.io.*;
import java.util.ArrayList;
import java.util.Vector;
import java.util.LinkedList;
import java.util.HashMap;
import java.util.Scanner;
import java.util.Map;

public class A4_2019CS50422 {
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

	public static void sort1(ArrayList<String> node,int low,int high){
		if (low<high){
		
			int mid=(low+high)/2;
			sort1(node,low,mid);
			sort1(node,mid+1,high);
			merge1(node,low,mid,high);
		}
	}
	public static void merge1(ArrayList<String> node,int low,int mid,int high){
		int n1=mid-low+1;
		int n2=high-mid;
		String L[]=new String[n1];
		String R[]=new String[n2];
		for (int i1=0;i1<n1;i1++){
			L[i1]=node.get(low+i1);
		}
		for (int i2=0;i2<n2;i2++){
			R[i2]=node.get(mid+i2+1);
		}
		int a1=0,a2=0;
		int a3=low;
		while(a1<n1 && a2<n2){
			if(L[a1].compareTo(R[a2])<=0){
				node.set(a3,L[a1]);
				a1++;
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

	public static void sort2(ArrayList <ArrayList <String>>  node,int low,int high){
		if (low<high){
		
			int mid=(low+high)/2;
			sort2(node,low,mid);
			sort2(node,mid+1,high);
			merge2(node,low,mid,high);
		}
	}
	public static void merge2(ArrayList <ArrayList <String>> node,int low,int mid,int high){
		int n1=mid-low+1;
		int n2=high-mid;
		ArrayList <ArrayList <String>> L=new ArrayList <ArrayList <String>> (n1);
		ArrayList <ArrayList <String>> R=new ArrayList <ArrayList <String>> (n2);
		

		for (int i1=0;i1<n1;i1++){
			ArrayList<String> l1=new ArrayList<>() ;
			L.add(l1);
			L.set(i1,node.get(low+i1));
		}
		for (int i2=0;i2<n2;i2++){
			ArrayList<String> l2=new ArrayList<>() ;
			R.add(l2);
			R.set(i2,node.get(mid+i2+1));
		}
		int a1=0,a2=0;
		int a3=low;
		while(a1<n1 && a2<n2){
			if(L.get(a1).size()<R.get(a2).size()){
				node.set(a3,L.get(a1));
				a1++;
			}
			else if(L.get(a1).size()==R.get(a2).size()){
				if (L.get(a1).get(0).compareTo(R.get(a2).get(0))<0){
					node.set(a3,L.get(a1));
					a1++;
			
				}
				else{
					node.set(a3,R.get(a2));
					a2++;
				}
			
			}
			else{
				node.set(a3,R.get(a2));
				a2++;
			}
			a3++;
		}
		while(a1<n1){
			node.set(a3,L.get(a1));
			a1++;
			a3++;
		}
		while(a2<n2){
			node.set(a3,R.get(a2));
			a2++;
			a3++;
		}
		

	}



	public static void dfs(String v,boolean[] visited,ArrayList<String> indfs,HashMap <String,Integer> map,graph g1){
		visited[map.get(v)]=true;
		indfs.add(v);
		for (edge x:g1.adj[map.get(v)]){
			if(!visited[map.get(x.tgt)]){
				dfs(x.tgt, visited,indfs, map, g1);

			}
		}
	}
	public static  ArrayList <ArrayList <String>> concomp(HashMap <String,Integer> map,graph g1,ArrayList<vertex> node){
		boolean[] visited=new boolean[node.size()];
		ArrayList <ArrayList <String>> dfstory= new ArrayList<ArrayList <String>> ();
		for (int vert=0;vert<node.size();vert++){
			if(!visited[vert]){
				ArrayList<String> indfs=new ArrayList<String> ();
				dfstory.add(indfs);
				dfs(node.get(vert).label,visited,indfs,map,g1);
			}
		}
		return dfstory;
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
		if (func.equals("independent_storylines_dfs")){
			ArrayList <ArrayList <String>> dfstory=concomp(map,g,node);
			for (int bh=0;bh<dfstory.size();bh++){
				sort1(dfstory.get(bh),0,dfstory.get(bh).size()-1);
			}
			sort2(dfstory,0,dfstory.size()-1);
			for (int bh=0;bh<dfstory.size();bh++){
				for (int anj=0;anj<dfstory.get(dfstory.size()-1-bh).size()-1;anj++){
					System.out.print(dfstory.get(dfstory.size()-1-bh).get(dfstory.get(dfstory.size()-1-bh).size()-anj-1)+',');
				}
				System.out.println(dfstory.get(dfstory.size()-1-bh).get(0));
			}
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
			edge anjali=new edge();
			anjali.wt=arr.get(i).wt;
			anjali.src=arr.get(i).tgt;
			anjali.tgt=arr.get(i).src;
			adj[j].add(arr.get(i));
			adj[tindex].add(anjali);
			node.get(tindex).rank+=arr.get(i).wt;
			node.get(j).rank+=arr.get(i).wt;
		}
	}
} 