// Class: A2DynamicMem
// Implements Degragment in A2. No other changes should be needed for other functions.

public class A2DynamicMem extends A1DynamicMem {
      
    public A2DynamicMem() {  super(); }

    public A2DynamicMem(int size) { super(size); }

    public A2DynamicMem(int size, int dict_type) { super(size, dict_type); }

    // In A2, you need to test your implementation using BSTrees and AVLTrees. 
    // No changes should be required in the A1DynamicMem functions. 
    // They should work seamlessly with the newly supplied implementation of BSTrees and AVLTrees
    // For A2, implement the Defragment function for the class A2DynamicMem and test using BSTrees and AVLTrees. 
    //Your BST (and AVL tree) implementations should obey the property that keys in the left subtree <= root.key < keys in the right subtree. How is this total order between blocks defined? It shouldn't be a problem when using key=address since those are unique (this is an important invariant for the entire assignment123 module). When using key=size, use address to break ties i.e. if there are multiple blocks of the same size, order them by address. Now think outside the scope of the allocation problem and think of handling tiebreaking in blocks, in case key is neither of the two. 
   public void Defragment() {
   		Dictionary yo;
   		if (type==2){
   			Dictionary b=new BSTree();
        	Dictionary f=freeBlk.getFirst();
        while (f!=null){
            b.Insert(f.address,f.size,f.address);
            f=f.getNext();
        }
        while (true){
            Dictionary a=b.getFirst();
            while (a!=null){
                if(a.getNext()!=null){
                    if(a.getNext().address==a.address+a.size){
                       
                        Dictionary c=new BSTree(a.address,a.size+a.getNext().size,a.address);
                       	Dictionary a1= new BSTree(a.address,a.size,a.size);
                      	Dictionary a2= new BSTree(a.getNext().address,a.getNext().size,a.getNext().size);
                        b.Delete(a);
                        b.Delete(a.getNext());
                        freeBlk.Delete(a1);
                        freeBlk.Delete(a2);
                        b.Insert(c.address,c.size,c.address);
                        freeBlk.Insert(c.address,c.size,c.size);
                        break;
                    }
                }
                a=a.getNext();
        }
            if (a==null){
                break;
            }
    }
    b=null;
    return ;
   	}
   		else if(type==3){
	   			boolean t=false;
	   			Dictionary b=new AVLTree();
	        	Dictionary f=freeBlk.getFirst();
	        while (f!=null){
	            b.Insert(f.address,f.size,f.address);
	            f=f.getNext();
	        }
	        while (true){
	        	Dictionary a=b.getFirst();
	            while (a!=null){
	            	Dictionary a3=a.getNext();
	                if(a.getNext()!=null){
	                    if(a.getNext().address==a.address+a.size){
	                       
	                        Dictionary c=new AVLTree(a.address,a.size+a.getNext().size,a.address);
	                       	Dictionary a1= new AVLTree(a.address,a.size,a.size);
	                      	Dictionary a2= new AVLTree(a3.address,a3.size,a3.size);
	                      	
	                        b.Delete(a);
	                        b.Delete(a3);
	                        freeBlk.Delete(a1);
	                        freeBlk.Delete(a2);
	                        b.Insert(c.address,c.size,c.address);
	                        freeBlk.Insert(c.address,c.size,c.size);
	                        a=a3=a1=a2=null;
	                        break;
	                    }
	                }
	                a=a.getNext();
	                if(a==null){
	                	t=true;
	                }
	            }
	        if(t){
	        	break;
	        }
	            
	    }
	    b=null;
	    return ;

	   	}
        
    }
   
}