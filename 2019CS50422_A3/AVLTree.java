// Class: Height balanced AVL Tree
// Binary Search Tree
public class AVLTree extends BSTree {
    
    private AVLTree left, right;     // Children. 
    private AVLTree parent;          // Parent pointer. 
    private int height;  // The height of the subtree
        
    public AVLTree() { 
        super();
        // This acts as a sentinel root node
        // How to identify a sentinel node: A node with parent == null is SENTINEL NODE
        // The actual tree starts from one of the child of the sentinel node !.
        // CONVENTION: Assume right child of the sentinel node holds the actual root! and left child will always be null.
        
    }

    public AVLTree(int address, int size, int key) { 
        super(address, size, key);
        this.height = 0;
    }

    // Implement the following functions for AVL Trees.
    // You need not implement all the functions. 
    // Some of the functions may be directly inherited from the BSTree class and nothing needs to be done for those.
    // Remove the functions, to not override the inherited functions.
    
    public AVLTree Insert(int address, int size, int key) 
    {
    AVLTree curr=this;
        if (curr==null){
            return null;
        }
        curr=curr.gethead();
        if (curr.right==null){
            AVLTree a= new AVLTree(address,size,key);
            curr.right=a;
            a.parent=curr;
            a.height=1;
            return a;
        }
        return curr.right.insertrec(address,size,key);
    }

    public boolean Delete(Dictionary e)
    {
        AVLTree curr=this.gethead().right;
        if(curr==null){
            return false;
        }
        return curr.deleterec(e);
        //return false;
    }
        
    public AVLTree Find(int k, boolean exact)
    {
        AVLTree curr=this.getFirst();
        if (curr!=null){    
            if (exact==true){
                while(curr!=null){

                    if (curr.key==k){
                        return curr;
                    }
                    curr=curr.getNext();
                } 
            }
            else{
                while(curr!=null){
                    if (curr.key>=k){
                        return curr;
                    }
                    curr=curr.getNext();
                }
            }
        } 
        return null;
       
    }

    public AVLTree getFirst()
    {
        AVLTree curr=this.gethead().right;
        if (curr==null){
            return null;
        }
        while (curr.left!=null){
            curr=curr.left;
        }
        return curr;
         
        //return null;
    }

    public AVLTree getNext()
    {
        AVLTree curr=this;
        if (curr!=null ){
            if (curr.right!=null){
                curr=curr.right;
                while(curr.left!=null){
                    curr=curr.left;
                }
                
                return curr;
            }
            else{
                AVLTree p=curr.parent;
                while (p!=null && curr!=p.left){
                    curr=p;
                    p=p.parent;
                }

                return p;
            }
        }
        return null;
    }

    public boolean sanity()
    {
        AVLTree curr=this.gethead();
        if (curr.key!=-1 || curr.address!=-1 || curr.size!=-1 || curr.parent!=null){
            return false;
        }
        if (curr.left!=null){
            return false;
        }
        curr=curr.right;
        if (curr !=null &&curr.chkbst()==false){
            return false;

        }
        return true;
        //return false;
    }

    private AVLTree gethead(){
        AVLTree curr=this;
        if (curr==null){
            return null;
        }
        while (curr.parent!=null){
            curr=curr.parent;
        }
        return curr;
    }

    private AVLTree insertrec(int address,int size,int key){
        AVLTree curr=this;
        if (curr==null){
            return null;
        }
        else if(key<curr.key){
            if(curr.left==null){
                AVLTree a=new AVLTree(address,size,key);
                a.parent=curr;
                curr.left=a;
                a.height=1;
                a.rebalance();
                return a;
            }
            return curr.left.insertrec(address,size,key);
        }
        else if(key>curr.key){
            if (curr.right==null){
                AVLTree a=new AVLTree(address,size,key);
                a.parent=curr;
                curr.right=a;
                a.height=1;
                a.rebalance();
                return a;

            } 
            return curr.right.insertrec(address,size,key);
        }
        else{
            if (address>curr.address){
                if (curr.right==null){
                    AVLTree a=new AVLTree(address,size,key);
                    a.parent=curr;
                    curr.right=a;
                    a.height=1;
                    a.rebalance();
                    return a;
                } 
                return curr.right.insertrec(address,size,key);
            }
            else{
                if (curr.left==null){
                    AVLTree a=new AVLTree(address,size,key);
                    a.parent=curr;
                    curr.left=a;
                    a.height=1;
                    a.rebalance();
                    return a;
                }
                return curr.left.insertrec(address,size,key);
            }
        }
    }
    private void  rebalance(){
        AVLTree x=this;
        if (x==null){
            return;
        }
        AVLTree y=x.parent;
        if (y==null){
            return;
        }
        y.height=y.ht();
        AVLTree z=y.parent;
        if(z==null){
            return;
        }
        if(z.parent==null){
            return;
        }
        
        while (z.parent!=null){
            int h=z.height;
            z.height=z.ht();
            if(h==z.ht()){
                return; 
            }
            if((z.left!=null && z.right!=null && Math.abs(z.left.height-z.right.height)>1)||(z.left==null && z.right!=null && z.right.height>1)||(z.left!=null && z.right==null && z.left.height>1)){
                if (x==y.left && y==z.left){
                    leftrot(x,y,z);
                    return;
                } 
                else if(x==y.right && y==z.right){
                    rightrot(x,y,z);
                    return;
                }
                else if(x==y.right && y==z.left){
                    leftrightrot(x,y,z);
                    return;
                }
                else{
                    rightleftrot(x,y,z);
                    return;            
                }
            }
            x=y;
            y=z;
            z=z.parent;
        }
        return;
    }
    private int ht(){
        AVLTree x=this;
        if(x.right!=null && x.left!=null){
            return Math.max(x.right.height,x.left.height)+1;
        }
        else if(x.left!=null){
            return x.left.height+1;
        }
        else if(x.right!=null){
            return x.right.height+1;
        }
        else{
            return 1;
        }

    }

    private void rightrot(AVLTree x,AVLTree y,AVLTree z){
        AVLTree t=y.left;
        z.right=t;
        if(t!=null){
            t.parent=z;
        }
        AVLTree a=z.parent;
        if(a.right==z){
            a.right=y;
        }
        else if(a.left==z){
            a.left=y;
        }
        y.parent=a;
        z.parent=y;
        y.left=z;
        x.height=x.ht();
        z.height=z.ht();
        y.height=Math.max(x.height,z.height)+1;
        return;
    }
    private void leftrot(AVLTree x,AVLTree y,AVLTree z){
        AVLTree t=y.right;
        AVLTree a=z.parent;
        if(a.right==z){
            a.right=y;
        }
        else{
            a.left=y;
        }
        y.parent=a;
        z.left=t;
        y.right=z;
        z.parent=y;
        if (t!=null){
            t.parent=z;
        }
        x.height=x.ht();
        z.height=z.ht();
        y.height=Math.max(x.height,z.height)+1;
        return ;
    }
    private void leftrightrot(AVLTree x,AVLTree y,AVLTree z){
        AVLTree t1=x.left;
        AVLTree t2=x.right;
        y.right=t1;
        z.left=t2;
        AVLTree a=z.parent;
        if(a.right==z){
            a.right=x;
        }
        else{
            a.left=x;
        }
        x.parent=a;
        y.parent=x;
        z.parent=x;
        x.left=y;
        x.right=z;
        if(t1!=null){
            t1.parent=y;
        }
        if (t2!=null){
            t2.parent=z;
        }
        y.height=y.ht();
        z.height=z.ht();
        x.height=Math.max(y.height,z.height)+1;
        return ;
    }
    private void rightleftrot(AVLTree x,AVLTree y,AVLTree z){
        AVLTree t1=x.left;
        AVLTree t2=x.right;
        AVLTree a=z.parent;
        if(a.right==z){
            a.right=x;
        }
        else{
            a.left=x;
        }
        x.parent=a;
        z.right=t1;
        y.left=t2;
        x.left=z;
        x.right=y;
        z.parent=x;
        y.parent=x;
        if(t1!=null){
            t1.parent=z;
        }
        if(t2!=null){
            t2.parent=y;
        }
        y.height=y.ht();
        z.height=z.ht();
        x.height=Math.max(y.height,z.height)+1;
        return ;
    }
    private boolean deleterec(Dictionary e){
        AVLTree curr=this;
        if (e.key==curr.key){
            if(e.address==curr.address){
                AVLTree p=curr.parent;
                if(curr.left!=null && curr.right!=null){
                    AVLTree a=curr.getNext();
                    curr.address=a.address;
                    curr.size=a.size;
                    curr.key=a.key;
                    
                    return curr.right.deleterec(a);
                }
                else if(curr.left!=null){
                    AVLTree a=curr.left;
                    if (p.right==curr){
                        p.right=a;
                        a.parent=p;
                    }
                    else {
                        p.left=a;
                        a.parent=p;
                    }
                    curr.left=null;
                    curr.parent=null;
                    p.height=p.ht();
                    p.delbal();
                    return true;
                }
                else if(curr.right!=null){
                    AVLTree a=curr.right;
                    if(p.right==curr){
                        p.right=a;
                        a.parent=p;
                    }
                    else{
                        p.left=a;
                        a.parent=p;
                    }
                    curr.right=null;
                    curr.parent=null;
                    p.height=p.ht();
                    p.delbal();
                    return true;

                }
                else{
                    if (p.right==curr){
                        p.right=null;
                    }
                    else{
                        p.left=null;
                    }
                    curr.parent=null;
                    p.height=p.ht();
                    p.delbal();
                    return true;
                }
            }
            else if(curr.address>e.address){
                return curr.left.deleterec(e);
            }
            else{
                return curr.right.deleterec(e);
            }
        }
        else if (e.key<curr.key){
            return curr.left.deleterec(e);
        }
        else{
            return curr.right.deleterec(e);
        }
    }

    private void delbal(){
        AVLTree z=this;
        if (z==null){
            return ;
        }
        while (z.parent!=null){
            int h=z.height;
            z.height=z.ht();
            if (z.left!=null && z.right!=null){
                if (z.left.height-z.right.height>1){
                    AVLTree y=z.left;
                    if(y.left!=null && y.right!=null){
                        if(y.left.height-y.right.height>=0){
                            AVLTree x=y.left;
                            leftrot(x,y,z);
                            if(h==y.ht()){
                                return;
                            }
                        }
                        else{
                            AVLTree x=y.right;
                            leftrightrot(x,y,z);
                            if(h==x.ht()){
                                return;
                            }
                        }
                    }
                    else if(y.left!=null){
                        AVLTree x=y.left;
                        leftrot(x,y,z);
                        if(h==y.ht()){
                            return;
                        }
                    }
                    else if(y.right!=null){
                        AVLTree x=y.right;
                        leftrightrot(x,y,z);
                        if(h==x.ht()){
                            return;
                        }
                    }
                }
                else if(z.right.height-z.left.height>1){
                    AVLTree y=z.right;
                    if(y.left!=null && y.right!=null){
                        if(y.left.height-y.right.height>0){
                            AVLTree x=y.left;
                            rightleftrot(x,y,z);
                            if(h==x.ht()){
                                return;
                            }
                        }
                        else{
                            AVLTree x=y.right;
                            rightrot(x,y,z);
                            if(h==y.ht()){
                                return;
                            }
                        }
                    }
                    else if(y.left!=null){
                        AVLTree x=y.left;
                        rightleftrot(x,y,z);
                        if(h==x.ht()){
                            return;
                        }
                    }
                    else if(y.right!=null){
                        AVLTree x=y.right;
                        rightrot(x,y,z);
                        if(h==y.ht()){
                            return;
                        }
                    }
                }
            }
            else if(z.left!=null){
                if(z.left.height>1){
                    AVLTree y=z.left;
                    if(y.left!=null && y.right!=null){
                        if(y.left.height-y.right.height>=0){
                            AVLTree x=y.left;
                            leftrot(x,y,z);
                            if(h==y.ht()){
                                return;
                            }
                        }
                        else{
                            AVLTree x=y.right;
                            leftrightrot(x,y,z);
                            if(h==x.ht()){
                                return;
                            }
                        }
                    }
                    else if(y.left!=null){
                        AVLTree x=y.left;
                        leftrot(x,y,z);
                        if(h==y.ht()){
                            return;
                        }
                    }
                    else if(y.right!=null){
                        AVLTree x=y.right;
                        leftrightrot(x,y,z);
                        if(h==x.ht()){
                            return;
                        }
                    }

                }
            }
            else if(z.right!=null){
                if (z.right.height>1){
                    AVLTree y=z.right;
                    if(y.left!=null && y.right!=null){
                        if(y.left.height-y.right.height>0){
                            AVLTree x=y.left;
                            rightleftrot(x,y,z);
                            if(h==x.ht()){
                                return;
                            }
                        }
                        else{
                            AVLTree x=y.right;
                            rightrot(x,y,z);
                            if(h==y.ht()){
                                return;
                            }
                        }
                    }
                    else if(y.left!=null){
                        AVLTree x=y.left;
                        rightleftrot(x,y,z);
                        if(h==x.ht()){
                            return;
                        }
                    }
                    else if(y.right!=null){
                        AVLTree x=y.right;
                        rightrot(x,y,z);
                        if(h==y.ht()){
                            return;
                        }
                    }
                }
            }
        z=z.parent;
        }
        return;
    }
    private boolean chkbst(){
        AVLTree curr=this;
        if(curr==null){
            return true;
        }
        if(curr.left!=null && curr.right!=null){
            if (curr.left.key<=curr.key && curr.key<=curr.right.key && curr.left.address<=curr.address && curr.address<curr.right.address){
                return curr.left.chkbst() || curr.right.chkbst();
            }
            else{
                return false;
            }
        }
        else if(curr.right!=null){
            if (curr.key<=curr.right.key && curr.address<curr.right.address){
                return curr.right.chkbst();
            }
            else{
                return false;
            }
        }
        else if(curr.left!=null){
            if (curr.left.key<=curr.key && curr.left.address<=curr.address){
                return curr.left.chkbst();
            }
            else{
                return false;
            }
        }
        return true;

    }

}
