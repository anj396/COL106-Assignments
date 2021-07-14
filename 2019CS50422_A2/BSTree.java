// Class: Implementation of BST in A2
// Implement the following functions according to the specifications provided in Tree.java

public class BSTree extends Tree {

    private BSTree left, right;     // Children.
    private BSTree parent;          // Parent pointer.
        
    public BSTree(){  
        super();
        // This acts as a sentinel root node
        // How to identify a sentinel node: A node with parent == null is SENTINEL NODE
        // The actual tree starts from one of the child of the sentinel node!.
        // CONVENTION: Assume right child of the sentinel node holds the actual root! and left child will always be null.
    }    

    public BSTree(int address, int size, int key){
        super(address, size, key); 
    }

    public BSTree Insert(int address, int size, int key) 
    {
    	BSTree curr=this;
        if (curr==null){
            return null;
        }
    	curr=curr.gethead();
    	if (curr.right==null){
    		BSTree a= new BSTree(address,size,key);
            curr.right=a;
            a.parent=curr;
    		return a;
    	}
    	return curr.right.insertrec(address,size,key);
        //return null;
    }

    public boolean Delete(Dictionary e)
    { 
    	
        BSTree root =this.gethead().right;
        BSTree curr=root.getFirst();
        if(e==null && curr==null){
            return false;
        }
        while(e!=null && curr!=null){
            if(curr.key==e.key && curr.address==e.address && curr.size==e.size){
                BSTree p=curr.parent;
                if (curr.left==null && curr.right==null){
                    if(p.right==curr){
                        p.right=null;
                    }
                    else{
                        p.left=null;
                    }
                    return true;
                }
                else if (curr.left==null){
                    if(p.right==curr){
                        p.right=curr.right;
                        curr.right.parent=p;
                    }
                    else{
                        p.left=curr.right;
                        curr.right.parent=p;
                    }
                    return true;
                }
                else if(curr.right==null){
                    if (p.right==curr){
                        p.right=curr.left;
                        curr.left.parent=p;
                    }
                    else{
                        p.left=curr.left;
                        curr.left.parent=p;
                    }
                    return true;
                }
                else{
                    BSTree a=curr.getNext();
                    curr.address=a.address;
                    curr.key=a.key;
                    curr.size=a.size;
                    p=a.parent;
                    if(p==null){
                        return true;
                    }
                    if (p.right==a && a.right!=null){
                        p.right=a.right;
                        a.right.parent=p;
                        //return true;

                    }
                    
                    else if (a.right==null){
                        p.left=null;
                        a.parent=null;
                        a=null;
                    }
                    else{
                        p.left=a.right;
                        a.right.parent=p;
                    }
                    return true;
                    

                }

            }
            curr=curr.getNext();

        }
        return false;
    }
        
    public BSTree Find(int key, boolean exact)
    {
        BSTree curr=this.getFirst();
        if (curr!=null){    
            if (exact==true){
                while(curr!=null){

                    if (curr.key==key){
                        return curr;
                    }
                    curr=curr.getNext();
                } 
            }
            else{
                while(curr!=null){
                    if (curr.key>=key){
                        return curr;
                    }
                    curr=curr.getNext();
                }
            }
        }
    return null;
    }

    public BSTree getFirst()
    { 
    	BSTree curr=this.gethead().right;
        if (curr==null){
            return null;
        }
        while (curr.left!=null){
            curr=curr.left;
        }
        return curr;
        
    }

    public BSTree getNext()
    { 
    	BSTree curr=this;
    	if (curr!=null ){
    		if (curr.right!=null){
                curr=curr.right;
    			while(curr.left!=null){
                    curr=curr.left;
                }
                
                return curr;
    		}
    		else{
    			BSTree p=curr.parent;
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
        BSTree curr=this.gethead();
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
        BSTree b=this.gethead().right;
        if(b!=null && b.cyc()==false){
            return false;
        }
        return true;
    }

    private BSTree insertrec(int address,int size,int key){
    	BSTree curr=this;
        if (curr==null){
            return null;
        }
    	
    	if (key<curr.key){
            if (curr.left==null){
                BSTree a=new BSTree(address,size,key);
                a.parent=curr;
                curr.left=a;
                return a;
            }
    		return curr.left.insertrec(address,size,key);
		}
		else if(key>curr.key){
            if (curr.right==null){
                BSTree a=new BSTree(address,size,key);
                a.parent=curr;
                curr.right=a;
                return a;

            } 
    		return curr.right.insertrec(address,size,key);
		}
  		else{
			if (address>curr.address){
                if (curr.right==null){
                    BSTree a=new BSTree(address,size,key);
                    a.parent=curr;
                    curr.right=a;
                    return a;
                } 
    			return curr.right.insertrec(address,size,key);
			}
 			else{
                if (curr.left==null){
                    BSTree a=new BSTree(address,size,key);
                    a.parent=curr;
                    curr.left=a;
                    return a;
                }
 				return curr.left.insertrec(address,size,key);
    		}

    	}
    }

    private BSTree gethead(){
    	BSTree curr=this;
    	if (curr==null){
    		return null;
    	}
    	while (curr.parent!=null){
    		curr=curr.parent;
    	}
    	return curr;
    }
    private boolean chkbst(){
        BSTree curr=this;
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
    private boolean cyc(){
        BSTree curr=this;
        if(curr.left!=null && curr.right!=null){
            if(curr.left.parent==curr && curr.right.parent==curr){
                return curr.left.cyc() || curr.right.cyc();
            }
            else {
                return false;
            }
        }
        else if(curr.right!=null){
            if(curr.right.parent==curr){
                return curr.right.cyc();
            }

            else{
                return false;
            }
        }
        else if(curr.left!=null){
            if (curr.left.parent==curr){
                return curr.left.cyc();
            }
            else{
                return false;
            }
        }
        else{
            return true;
        }
    }


}


 


