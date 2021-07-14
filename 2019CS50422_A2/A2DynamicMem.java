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
        BSTree b=new BSTree();
        Dictionary f=freeBlk.getFirst();
        while (f!=null){
            b.Insert(f.address,f.size,f.address);
            f=f.getNext();
        }
        while (true){
        BSTree a=b.getFirst();
        while (a!=null){
            if(a.getNext()!=null){
            if(a.getNext().address==a.address+a.size){
               
                BSTree c=new BSTree(a.address,a.size+a.getNext().size,a.address);
                BSTree a1=new BSTree(a.address,a.size,a.size);
                BSTree a2=new BSTree(a.getNext().address,a.getNext().size,a.getNext().size);
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
}