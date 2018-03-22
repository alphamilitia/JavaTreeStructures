import java.util.Iterator;


public interface Tree<T> extends Iterable<T> {
		
	/* Java interface cannot contain an implementation of the methods, 
	 * only the signature (name, parameters and exceptions) of the method. 
	 * You can use interfaces in Java as a way to achieve polymorphism
	 */
	
	/* Iterator: the process of scanning through a sequence of elements
	 * Java defines java.util.Iterator interface, it has methods: 
	 * hasNext():
	 * next(): NoSuchElementException
	 * remove(): 
	 * */
	
	/* Iterable: interface
	 * iterator(): 
	 * e.g., ArrayList<Double> data;
	 * 		 Iterator<Double> walk = data.iterator();
	 * */
	
	// Returns the number of nodes in the tree
	int size();

	// Returns true if the tree does not contain any positions and thus no elements
	boolean isEmpty();
	
	// Returns the root Position of the tree (or null if tree is empty)
	Position<T> root();
	
	// Returns the Position of p's parent (or null if p is root)
	Position<T> parent(Position<T> p) throws IllegalArgumentException;

	// Returns an iterable collection containing the children of position p if any 
	Iterable<Position<T>> children(Position<T> p) throws IllegalArgumentException;

	// Returns an iterator of the elements stored in the tree
	Iterator<T> iterator();

	// Returns an iterable collection of the positions of the tree.
	Iterable<Position<T>> positions();
	
	// Returns the number of children of Position p
	int numChildren(Position<T> p) throws IllegalArgumentException;

	// Returns true if Position p has one or more children.
	boolean isInternal(Position<T> p) throws IllegalArgumentException;

	//Returns true if Position p does not have any children.
	boolean isExternal(Position<T> p) throws IllegalArgumentException;

	//Returns true if Position p represents the root of the tree
	boolean isRoot(Position<T> p) throws IllegalArgumentException;
}