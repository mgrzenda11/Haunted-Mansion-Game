import java.util.Iterator;
import java.lang.IndexOutOfBoundsException;
 
public class LinkedList<T> implements Iterable<T> {
 
    private class Node<T>  {
        T data;
        Node<T> next;
 
        public Node(T data){
            this.data = data;
        }
 
        public Node(T data, Node<T> next){
            this(data);
            this.next = next;
        }
 
		@Override
        public String toString(){
            return "" + data;
        }
    }
    
    public class LLIterator<U> implements Iterator<U> {
        LinkedList<U> ll;
        LinkedList<U>.Node<U> curr;
 
        public LLIterator(LinkedList<U> ll){
            this.ll = ll;
            curr = null;
        }
 
		@Override
        public boolean hasNext(){
            if (curr == ll.tail && ll.tail != null) return false;
			if(ll.count == 0) return false;
            return true;
        }
		
		@Override
        public U next(){
            if (curr == null) {
                curr = ll.head;
			}
            else{
				curr = curr.next;
			}
            return curr.data;
        }

    }
 
    private Node<T> head, tail;
    private int count;
 
    public LinkedList(){
        count = 0;
    }
 
    public void prepend (T i){
        if (count == 0){
            head = tail = new Node<T>(i);
        }
        else {
            head = new Node<T>(i, head);
        }
        count++;
    }
 
    public void append(T i){
        if (count == 0){
            head = tail = new Node<T>(i);
        }
        else {
            tail = tail.next = new Node<T>(i);
        }
        count++;
    }
	
	public void insert(T data, int index) {
		if(index> count || index <0) {
			throw new IndexOutOfBoundsException();
		}
		
		if(index == 0) {
			prepend(data);
			return;
		}
		
		if(index == count) {
			append(data);
			return;
		}
		else {
			int counter = 0;
			Node <T> temp = head;
			while(counter!= index-1&&counter<count) {
				temp = temp.next;
				counter++;
			}
			if(counter == index){
				Node <T> holder = temp.next;
				temp.next = new Node<T>(data, holder);
				count++;
			}
			else {
				append(data);
			}
		}
	}

	public boolean exists(T looking) {
		for(Node <T> temp = head; temp != null; temp =  temp.next) {
			if(temp.data.equals(looking)) {
				return true;
			}
		}
		return false;
	} 
	
	public boolean remove(T rem) {
		if(count == 0) {
			return false;
		}
		
		if(count == 1){
			if(head.data.equals(rem)) {
				head= tail = null;
				count--;
				return true;
			}
			else {
				return false;
			}
		}
		
		if(head.data.equals(rem)) {
			head = head.next;
			count--;
			return true;
		}
		
		Node <T> curr = head;
		Node <T> del = null;
		while(!curr.data.equals(rem)&&curr.next!=null) {
			del = curr;
			curr = curr.next;
		}
		
		if(curr.data.equals(rem)) {
			del.next = curr.next;
			count--;
			if(curr==tail) {
				tail = del;
			}
			return true;
		}
		else {
			return false; 
		}
	}
	
	public T get(int index) {
		if(index <0 || index > count) {
			throw new IndexOutOfBoundsException();
		}
		else{
			int counter = 0;
			Node <T> temp = head;
			while(counter != index) {
				temp = temp.next;
				counter++;
			}
			return temp.data;
		}
	}
		
    public int size(){
        return count;
    }
 
    public boolean isEmpty(){
        return head == null;
    }
 
    @Override	
    public String toString() {
        String retVal = "Linked list with " + count + " elements\nNodes:";
        //Node temp = head;
        //while(temp != null){
        //    retVal += temp + " ";
        //    temp = temp.next;
        //}
 
        for (Node<T> temp = head; temp != null; temp = temp.next)
            retVal += temp + " ";
        
        return retVal;
    }
 
	@Override
    public Iterator<T> iterator(){
        return new LLIterator<T>(this);
    }
}
