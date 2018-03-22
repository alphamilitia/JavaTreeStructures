import java.util.Iterator;
import java.util.List;         // for use as snapshot iterator
import java.util.ArrayList;    // for use as snapshot iterator
import java.util.Queue;
import java.util.LinkedList;

public abstract class AbstractTree<T> implements Tree<T> {
	/* implements means you are using the elements of a Java Interface in your class. 
	 * extends means that you are creating a subclass of the base class you are extending. 
	 * You can only extend one class in your child class, 
	 * but you can implement as many interfaces as you would like 
	 */
	
	/* Abstract Classes are a good fit if you want to provide implementation details to your children 
	 * but don't want to allow an instance of your class to be directly instantiated 
	 * (which allows you to partially define a class)
	 * */
	
	/* Override annotation:
	 * The compiler checks to make sure you actually are overriding a method when you think you are
	 * */
	
	 // Returns true if Position p has one or more children
	 @Override
	 public boolean isInternal(Position<T> p) { return numChildren(p) > 0;}

	 // Returns true if Position p does not have any children
	 @Override
	 public boolean isExternal(Position<T> p) { return numChildren(p) == 0; }

	 // Returns true if Position p represents the root of the tree
	 @Override
	 public boolean isRoot(Position<T> p) { return p == root(); }

	 // Returns the number of children of Position p.
	 @Override
	 public int numChildren(Position<T> p) {
		 int count = 0;
		 for (Position<T> child : children(p)) {count++;}
		 return count;
	 }

	 // Returns the number of nodes in the tree
	 @Override
	 public int size() {
	    int count=0;
	    for (Position<T> p : positions()) count++;
	    return count;
	 }

	 // Tests whether the tree is empty
	 @Override
	 public boolean isEmpty() { return size() == 0; }


	 // Returns the number of levels separating Position p from the root.
	 public int depth(Position<T> p) throws IllegalArgumentException {
	    if (isRoot(p)) {
	      return 0;
	    } else { 
	      return 1 + depth(parent(p));}
	 }

	 // Returns the height of the tree
	 // Note: This implementation works, but runs in O(n^2) worst-case time
	  private int heightBad() {             // works, but quadratic worst-case time
	    int h = 0;
	    for (Position<T> p : positions())
	      if (isExternal(p))                // only consider leaf positions
	        h = Math.max(h, depth(p));
	    return h;
	  }

	  // Returns the height of the subtree rooted at Position p
	  public int height(Position<T> p) throws IllegalArgumentException {
	    int h = 0;                          // base case if p is external
	    for (Position<T> c : children(p))
	      h = Math.max(h, 1 + height(c));
	    return h;
	  }

	  //---------------- nested ElementIterator class ----------------
	  /* This class adapts the iteration produced by positions() to return elements. */
	  private class ElementIterator implements Iterator<T> {
	    Iterator<Position<T>> posIterator = positions().iterator();
	    public boolean hasNext() { return posIterator.hasNext(); }
	    public T next() { return posIterator.next().getElement(); } // return element!
	    public void remove() { posIterator.remove(); }
	  }

	  // Returns an iterator of the elements stored in the tree
	  @Override
	  public Iterator<T> iterator() { return new ElementIterator(); }

	  // Returns an iterable collection of the positions of the tree
	  @Override
	  public Iterable<Position<T>> positions() { return preorder(); }

	  // Adds positions of the subtree rooted at Position p to the given
	  // snapshot using a preorder traversal
	  private void preorderSubtree(Position<T> p, List<Position<T>> snapshot) {
		  snapshot.add(p); // for preorder, we add position p before exploring subtrees
		  for (Position<T> c : children(p)){preorderSubtree(c, snapshot);}
	  }

	  // Returns an iterable collection of positions of the tree, reported in preorder
	  public Iterable<Position<T>> preorder() {
	    List<Position<T>> snapshot = new ArrayList<>();
	    if (!isEmpty())
	      preorderSubtree(root(), snapshot);   // fill the snapshot recursively
	    return snapshot;
	  }

	  // Adds positions of the subtree rooted at Position p to the given
	  // snapshot using a postorder traversal
	  private void postorderSubtree(Position<T> p, List<Position<T>> snapshot) {
	    for (Position<T> c : children(p))
	      postorderSubtree(c, snapshot);
	    snapshot.add(p);  // for postorder, we add position p after exploring subtrees
	  }

	  // Returns an iterable collection of positions of the tree, reported in postorder
	  public Iterable<Position<T>> postorder() {
	    List<Position<T>> snapshot = new ArrayList<>();
	    if (!isEmpty())
	      postorderSubtree(root(), snapshot);   // fill the snapshot recursively
	    return snapshot;
	  }
	  
	  // Returns an iterable collection of positions of the tree in breadth-first order
	  public Iterable<Position<T>> breadthfirst() {
	    List<Position<T>> snapshot = new ArrayList<>();
	    if (!isEmpty()) {
	      Queue<Position<T>> fringe = new LinkedList<Position<T>>();
	      fringe.add(root());                 // start with the root
	      while (!fringe.isEmpty()) {
	        Position<T> p = fringe.remove();     // remove from front of the queue
	        snapshot.add(p);                      // report this position
	        for (Position<T> c : children(p))
	          fringe.add(c);                  // add children to back of queue
	      }
	    }
	    return snapshot;
	  }
	  
}