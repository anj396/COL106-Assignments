import java.io.*;
import java.util.ArrayList;
import java.util.Vector;
import java.util.LinkedList;
import java.util.HashMap;
import java.util.Scanner;

public class b{
	public static void sortmerge(ArrayList<String> node,HashMap<String,Integer> hm,int low,int high){
		if (low<high){
			int mid=(low+high)/2;
			sortmerge(node,hm,low,mid);
			sortmerge(node,hm,mid+1,high);
			merge(node,hm,low,mid,high);
		}
	}
	public static void merge(ArrayList<String> node,HashMap<String,Integer> hm,int low,int mid,int high){
		int n1=mid-low+1;
		int n2=high-mid;
		ArrayList<String> L=new ArrayList<String>(n1);
		ArrayList<String> R=new ArrayList<String>(n2);
		for (int i1=0;i1<n1;i1++){
			L.add(node.get(low+i1));
		}
		for (int i2=0;i2<n2;i2++){
			R.add(node.get(mid+i2+1));
		}
		int a1=0,a2=0;
		int a3=low;
		while(a1<n1 && a2<n2){
			if(hm.get(L.get(a1))<hm.get(R.get(a2))){
				node.set(a3,L.get(a1));
				a1++;
			}else if(hm.get(L.get(a1))==hm.get(R.get(a2))){
				if (L.get(a1).compareTo(R.get(a2))<0){
					node.set(a3,L.get(a1));
					a1++;	
				}else{
					node.set(a3,R.get(a2));
					a2++;
				}
			}else{
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

	public static void main(String[] args)throws Exception{
		Scanner input1 = new Scanner(new File(args[0]));
		String idinfo = input1.nextLine();
		String name;
		int number = 0;
		ArrayList<String> characters = new ArrayList<String>();
		input1.nextLine();
		while (input1.hasNextLine()){
			idinfo = input1.nextLine();
			String vertex = idinfo.split(",(?=(?:[^\"]*\"[^\"]*\")*[^\"]*$)", -1)[1];
			
				
					if (vertex.charAt(0)=='"'){
        				name = vertex.substring(1,vertex.length()-1);
        			}
        			else{
            			name = vertex;
            		}
            		characters.add(name);
				}
		
		
//**********************************************************************
		Scanner input2 = new Scanner(new File(args[1]));
		String data = input2.nextLine();
//Part a
		if (args[2].equals("average")){
			double count = 0;
			while (input2.hasNextLine()){
				data = input2.nextLine();
				count++;
        		
			}
			double avg = (2*count)/number;
			System.out.println(String.format("%.2f",avg));
//Part b
		}else if (args[2].equals("rank")){
			ArrayList<Integer> co_occurences = new ArrayList<Integer>(number);
			for (int i=0; i<number;i++){
				co_occurences.add(0);
			}
			while (input2.hasNextLine()){
				String source = "";
				String target = "";
				int weight = 0;
				int i = 1;
				data = input2.nextLine();
				String[] edge = data.split(",(?=(?:[^\"]*\"[^\"]*\")*[^\"]*$)", -1);
				for(String t: edge){
					if (i%3 == 1){
						if (t.charAt(0)=='"'){
        					source = t.substring(1,t.length()-1);
        				}else{
            				source = t;
            			}
					}else if (i%3 == 2){
						if (t.charAt(0)=='"'){
        					target = t.substring(1,t.length()-1);
        				}else{
            				target = t;
            			}						
					}else{
						weight = Integer.parseInt(t);
					}
					i++;
				}
				int ti = characters.indexOf(target);
				int si = characters.indexOf(source);	
				int tpv = co_occurences.get(ti);
				int spv = co_occurences.get(si);		
				co_occurences.set(ti, tpv+weight);
				co_occurences.set(si, spv+weight);
 			}
 			HashMap <String,Integer> hm=new HashMap<>();
			for (int i=0;i<number;i++){
				hm.put(characters.get(i),co_occurences.get(i));
			}

			sortmerge(characters,hm,0,number-1);

 			for (int i=number-1; i>=0; i--){
 				System.out.println(characters.get(i)+hm.get(characters.get(i)));
 				if (i!=number-1){
 					System.out.print(",");
 				}
 			}
		}
	}
}







