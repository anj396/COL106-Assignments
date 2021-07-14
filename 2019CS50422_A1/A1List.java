// Implements Dictionary using Doubly Linked List (DLL)
// Implement the following functions using the specifications provided in the class List

public class A1List extends List {

    private A1List  next; // Next Node
    private A1List prev;  // Previous Node 

    public A1List(int address, int size, int key) { 
        super(address, size, key);
    }
    
    public A1List(){
        super(-1,-1,-1);
        // This acts as a head Sentinel

        A1List tailSentinel = new A1List(-1,-1,-1); // Intiate the tail sentinel
        
        this.next = tailSentinel;
        tailSentinel.prev = this;
    }

    public A1List Insert(int address, int size, int key)
    {
        if (this.next==null){
            return null;
        }
        A1List node=new A1List(address,size,key);
        A1List temp=this.next;
        node.prev=this;
        this.next=node;
        node.next=temp;
        temp.prev=node;
        
        
        return node;
    }

    public boolean Delete(Dictionary d) 
    {

        A1List curr=this;
        if (this.getFirst()==null){
            return false;
        }
        while(curr.next!=null){
            A1List pre =curr.prev;
            A1List nex= curr.next;
            if (curr.key==d.key && curr.address==d.address && curr.size==d.size){
                pre.next=nex;
                nex.prev=pre;
                return true;
            }
            curr=curr.next;
        }
        A1List current=this;
        while(current.prev!=null){
            A1List pre =current.prev;
            A1List nex= current.next;
            if (current.key==d.key && current.address==d.address && current.size==d.size){
                pre.next=nex;
                nex.prev=pre;
                return true;
            }
            current=current.prev;
        }

        return false;
    }

    public A1List Find(int k, boolean exact)
    { 
        A1List curr=this;
        if (this.getFirst()==null){
            return null;
        }
        
        while(curr.prev!=null){
            curr=curr.prev;
        }
        if (exact==true){
            while (curr.next!=null){
                if (curr.key==k){
                    return curr;
                }
                curr=curr.next;
            }
        }
        else{
            while(curr.next!=null){
                if (curr.key>=k){
                    return curr;
                }
                curr=curr.next;
            }
        }
        return null;
    }

    public A1List getFirst()
    {
        A1List curr=this;
        while (curr!=null && curr.prev!=null){
            curr=curr.prev;
        }
        if( curr!=null && curr.prev==null && curr.next!=null && curr.next.next!=null){
            return curr.next;
        }
        
        return null;
    }
    
    public A1List getNext() 
    {
        A1List curr=this;
        if (curr.next!=null && curr.next.next!=null){
            return curr.next;
        }
        return null;
    }

    public boolean sanity()
    {
        A1List l1=this;
        A1List l2=this;
        while (l1!=null && l2!=null && l2.next!=null && l2.next.next!=null){
            l1=l1.next;
            l2=l2.next.next;
            if (l1==l2){
                return false;
            }
        }
        A1List l3=this;
        A1List l4=this;
        while (l3!=null && l4!=null && l4.prev!=null && l2.prev.prev!=null){
            l3=l3.prev;
            l4=l4.prev.prev;
            if (l3==l4){
                return false;
            }


        }

        A1List h=this.getFirst();
        if (h==null){
            A1List head=this;
            if (head==null || head.prev!=null || head.key!=-1 || head.address!=-1 ||head.size!=-1){
                return false;
            }
            else if (head.next==null || head.next.next!=null || head.next.key!=-1 || head.next.address!=-1 ||head.next.size!=-1){
                return false;
            }
        }
        else{
            if (h!=null && h.prev.key!=-1 || h.prev.address!=-1 || h.prev.size!=-1){
                return false;    
            }
            while (h.prev!=null && h.next!=null){
                if (h.next.prev!=h || h.prev.next!=h){
                    return false;
                }
                h=h.next;
            }
            if (h!=null && h.key!=-1 || h.address!=-1 || h.size!=-1){
                return false;
            }

            return true;
        }
        return true;

    }
    
     

}


