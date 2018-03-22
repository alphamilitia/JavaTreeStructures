import java.util.Arrays;

public class LinkedBinaryTree<T> extends AbstractBinaryTree<T> {
	/* As with class methods and variables, a static nested class 
	 * is associated with its outer class. And like static class methods, 
	 * a static nested class cannot refer directly to instance variables
	 * or methods defined in its enclosing class: 
	 * it can use them only through an object reference
	 */
	protected static class Node<T> implements Position<T> {
		private T element;
		private Node<T> parent;
		private Node<T> left;
		private Node<T> right;
		
		// constructors
		public Node(T e, Node<T> above, Node<T> leftChild, Node<T> rightChild) {
			element = e;
			parent = above;
			left = leftChild;
			right = rightChild;
		}
		
		// accessor methods
		public T getElement() { return element; }
		public Node<T> getParent() { return parent; }
		public Node<T> getLeft() {return left;}
		public Node<T> getRight() {return right;}
		
		// update methods
		public void setElement(T e) {element = e; }
		public void setParent(Node<T> parentNode) { parent = parentNode; }
		public void setLeft(Node<T> leftNode) {left = leftNode; }
		public void setRight(Node<T> rightNode) {right = rightNode; }
	}
	
	// create a new node to store element
	protected Node<T> createNode(T e, Node<T> parent, Node<T> left, Node<T> right) {
		Node<T> newNode = new Node<T>(e, parent, left, right);
		return newNode;
	}
	
	// instance variables
	protected Node<T> root = null;
	private int size = 0;
	
	// constructor 
	public LinkedBinaryTree() {}
	
	protected Node<T> validate(Position<T> p) throws IllegalArgumentException{
		if (!(p instanceof Node)) {
			throw new IllegalArgumentException("Not valid position type");
		}
		Node<T> node = (Node<T>) p; // safe cast
		if (node.getParent() == node) {
			throw new IllegalArgumentException("p is no longer for defunct node");
		}
		return node;
	}

	public int size(){
		return size;
	}
	
	public Position<T> root() {
		return root;
	}
	
	public Position<T> parent(Position<T> p) throws IllegalArgumentException {
		Node<T> node = validate(p);
		return node.getParent();
	}
	
	public Position<T> left(Position<T> p) throws IllegalArgumentException {
		Node<T> node = validate(p);
		return node.getLeft();
	}
	
	public Position<T> right(Position<T> p) throws IllegalArgumentException {
		Node<T> node = validate(p);
		return node.getRight();
	}
	
	// update methods
	public Position<T> addRoot(T e) throws IllegalStateException{
		if(!isEmpty()) {throw new IllegalStateException("Tree is not empty");}
		root = createNode(e, null, null, null);
		size = 1;
		return root;
	}

	public Position<T> addLeft(Position<T> p, T e) throws IllegalStateException {
		Node<T> parent = validate(p);
		if(parent.getLeft() != null) {throw new IllegalStateException("Tree already has a left child");}
		Node<T> child = createNode(e, parent, null, null);
		parent.setLeft(child);
		size++;
		return child;
	}

	public Position<T> addRight(Position<T> p, T e) throws IllegalStateException {
		Node<T> parent = validate(p);
		if(parent.getRight() != null) {throw new IllegalStateException("Tree already has a right child");}
		Node<T> child = createNode(e, parent, null, null);
		parent.setRight(child);
		size++;
		return child;
	}
	
	public T set(Position<T> p, T e) throws IllegalStateException {
		Node<T> node = validate(p);
		T temp = node.getElement();
		node.setElement(e);
		return temp;
	}
	
	public void attach(Position<T> p, LinkedBinaryTree<T> t1, LinkedBinaryTree<T> t2) throws IllegalArgumentException {
		Node<T> node = validate(p);
		if (isInternal(p)) {throw new IllegalArgumentException("P must be a leaf");}
		size += t1.size() + t2.size();
		if (!t1.isEmpty()){
			t1.root.setParent(node);
			node.setLeft(t1.root);
			t1.root = null;
			t1.size = 0;
		}
		if (!t2.isEmpty()){
			t2.root.setParent(node);
			node.setRight(t2.root);
			t2.root = null;
			t2.size = 0;
		}
	} 
	
	// removes the node at position p and replaces it with its child, if any
	public T remove(Position<T> p) throws IllegalArgumentException {
		Node<T> node = validate(p);
		if (numChildren(p) == 2) {throw new IllegalArgumentException("p has two children");}
		Node<T> child = (node.getLeft() != null ? node.getLeft():node.getRight());
		if (child != null) {child.setParent(node.getParent());} //child's grandparent becomes its parent
		if (node == root) {root = child;}
		else {
			Node<T> parent = node.getParent();
			if (node == parent.getLeft()) {parent.setLeft(child);}
			else {parent.setRight(child);}
		}
		size--;
		T temp = node.getElement();
		node.setElement(null);
		node.setLeft(null);
		node.setRight(null);
		node.setParent(node);
		return temp;
	}
	
	public void printTree() {
		System.out.println("The tree is:");
		for (Position<T> p: positions()){
			Node<T> node = (Node<T>) p; // safe cast
		}
	}
}
