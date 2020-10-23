import java.util.ArrayList;
 
class BST <T extends Comparable <T>> {
 
    private class BSTNode <T> {
        T value;
        BSTNode <T> left;
        BSTNode <T> right;
 
        public BSTNode(T value){
            this.value = value;
        }
 
        // Adapted from Todd Davies answer to printing a BST on Stack Overflow.
        // https://stackoverflow.com/questions/4965335/how-to-print-binary-tree-diagram
        private StringBuilder toString(StringBuilder prefix, boolean isTail, StringBuilder sb) {
            if(right!=null) {
                right.toString(new StringBuilder().append(prefix).append(isTail ? "│   " : "    "), false, sb);
            }
            sb.append(prefix).append(isTail ? "└── " : "┌── ").append(value).append("\n");
            if(left!=null) {
                left.toString(new StringBuilder().append(prefix).append(isTail ? "    " : "│   "), true, sb);
            }
            return sb;
        }
 
        @Override
        public String toString() {
            return this.toString(new StringBuilder(), true, new StringBuilder()).toString();
        }
    }
 
    private BSTNode root;
 
    private BSTNode insert(BSTNode<T> curr, T val){
        if (curr == null)
            return new BSTNode<T> (val);
        if (curr.value.compareTo(val)<0)
            curr.right = insert(curr.right, val);
        else if (curr.value.compareTo(val)>0)
            curr.left = insert(curr.left, val); 
        return curr;
    }
 
    public void insert(T value){
        root = insert(root, value);
    }
 
    private boolean search(BSTNode <T> curr, T val){
        System.out.println("Visiting: " + (curr == null ? "null :(" : curr.value));
        if (curr == null||val == null) return false;
        if (curr.value.compareTo(val)==0) return true;
        if (val.compareTo(curr.value)>0) {
            return search(curr.right, val);
		}
		else {
			return search(curr.left, val);
		}
    }
 
    public boolean search(T value){
        return search(root, value);
    }
	
	public T get(T val) {
		return (T)(get(root, val));
	}
	
	private T get(BSTNode <T> curr, T val) {
		if(curr == null||val == null) return null;
		if(curr.value.compareTo(val)==0)return curr.value;
		if(val.compareTo(curr.value)>0){
			return get(curr.right, val);
		}
		else {
			return get(curr.left, val);
		}
	}
 
    private void printInOrder(BSTNode <T> curr){
        if (curr != null) {
            // Print everything in left subtree
            printInOrder(curr.left);
			
            // Print curr.value
            System.out.print(curr.value + "\n\n\n\n");
			
            // Print everything in right subtree
            printInOrder(curr.right);
			
        }
    }
 
    public void printInOrder(){
        printInOrder(root);
        System.out.println();
    }
 
    public void printTree(){
        System.out.println(root.toString());
    }
}
