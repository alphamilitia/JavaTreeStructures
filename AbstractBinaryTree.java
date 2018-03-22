import java.util.List;
import java.util.ArrayList;


public abstract class AbstractBinaryTree<T> extends AbstractTree<T> implements BinaryTree<T> {
	
	//Returns the Position of p's sibling (or null if no sibling exists)
	@Override
	public Position<T> sibling(Position<T> p) {
		Position<T> parent = parent(p);
	    if (parent == null) {return null;}                  // p must be the root
	    if (p == left(parent)){                            // p is a left child
	      return right(parent);                           // (right child might be null)
	    } else {                                              // p is a right child
	      return left(parent);                            // (left child might be null)
	    }
	}

	// Returns the number of children of Position p
	@Override
	public int numChildren(Position<T> p) {
		int count = 0;
	    if (left(p) != null) {
	      count++; 
	    }
	    if (right(p) != null) {
	      count++;
	    }
	    return count;
	}

	
	// Returns an iterable collection of the Positions representing p's children.
	@Override
	public Iterable<Position<T>> children(Position<T> p) {
		List<Position<T>> snapshot = new ArrayList<>(2);    // max capacity of 2
		if (left(p) != null) {
			snapshot.add(left(p));}
		if (right(p) != null) {
			snapshot.add(right(p));}
		return snapshot;
	}
	
  	// Adds positions of the subtree rooted at Position p to the given
  	// snapshot using an inorder traversal
  	private void inorderSubtree(Position<T> p, List<Position<T>> snapshot) {
  		if (left(p) != null) {
  			System.out.print("(");
  			inorderSubtree(left(p), snapshot);
  		}
  		System.out.print(p.getElement());
  		snapshot.add(p);
  		if (right(p) != null){
  			inorderSubtree(right(p), snapshot);
  			System.out.print(")");
  		}
  	}

  	// Returns an iterable collection of positions of the tree, reported in inorder
  	public Iterable<Position<T>> inorder() {
  		List<Position<T>> snapshot = new ArrayList<>();
  		if (!isEmpty())
  			inorderSubtree(root(), snapshot);   // fill the snapshot recursively
  		return snapshot;
  	}

  	// Returns an iterable collection of the positions of the tree using inorder traversal
  	@Override
  	public Iterable<Position<T>> positions() {
  		return inorder();
  	}
}

