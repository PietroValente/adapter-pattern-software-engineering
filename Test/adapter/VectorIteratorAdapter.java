package adapter;

import java.util.Vector;

/**
 * <p>This class implements the {@code Iterator} interface. It is actually a 
 * {@code ListIteratorAdapter} instance limited to iterator functions.
 * 
 * <p>The elements are scrolled in order of insertion in the vector, 
 * from the first inserted and still present until the last.
 * 
 * <p>The peculiarity of this iterator that you can choose the start and 
 *    end indices. And therefore limit the number of elements that the iterator 
 *    can visit.
 * 
 * <p>The iterators returned by this class's are <b>not fail-fast</b>.
 *
 * <p>This class is a member of the <i>adapter package</i>.
 * 
 * @author  Pietro Valente
 */

public class VectorIteratorAdapter
implements Iterator
{
    private ListIteratorAdapter VectorIterator;

    /**
    * Constructs an VectorIteratorAdapter instance, which is in the first position,
    * before the fromIndex element, and his final bound is toIndex.
    *
    * @param type is the reference to the Vector that is iterated
    * @param fromIndex is the first element that @link #next} would return
    * @param toIndex is the final bound, the last element that @link #next} would return
	*/
    public VectorIteratorAdapter(Vector type, int fromIndex, int toIndex)
    {
        VectorIterator = new ListIteratorAdapter(type, fromIndex, toIndex);
    }

    /**
     * Returns {@code true} if the iteration has more elements.
     * (In other words, returns {@code true} if {@link #next} would
     * return an element rather than throwing an exception.)
     *
     * @return {@code true} if the iteration has more elements
	*/
    public boolean hasNext()
    {
       return VectorIterator.hasNext();
    }

    /**
    * Returns the next element in the iteration.
    * 
    * @return the next element in the iteration
    * @exception NoSuchElementException if the iteration has no more elements
	*/
    public Object next()
    {
        return VectorIterator.next();
    }

    /**
     * Removes from the vector the last element returned
     * by this iterator.  This method can be called
     * only once per call to {@link #next}.
     *
     * @throws IllegalStateException if the {@code next} method has not
     *         yet been called, or the {@code remove} method has already
     *         been called after the last call to the {@code next}
     *         method
	*/
    public void remove()
    {
        VectorIterator.remove();
    }
}