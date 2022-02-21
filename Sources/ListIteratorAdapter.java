package adapter;

import java.util.NoSuchElementException;
import java.util.Vector;

/**
 * An iterator for lists that allows the programmer
 * to traverse the list in either direction, modify
 * the list during iteration, and obtain the iterator's
 * current position in the list. A {@code ListIterator}
 * has no current element; its <I>cursor position</I> always
 * lies between the element that would be returned by a call
 * to {@code previous()} and the element that would be
 * returned by a call to {@code next()}.
 * An iterator for a list of length {@code n} has {@code n+1} possible
 * cursor positions, as illustrated by the carets ({@code ^}) below:
 * <PRE>
 *                      Element(0)   Element(1)   Element(2)   ... Element(n-1)
 * cursor positions:  ^            ^            ^            ^                  ^
 * </PRE>
 * 
 * <p>The peculiarity of this iterator that you can choose the start and 
 *    end indices. And therefore limit the number of elements that the iterator 
 *    can visit.
 * 
 * <p>The iterators returned by this class's are <b>not fail-fast</b>.
 * 
 * <p>This class is a member of the <i>adapter package</i>.
 * 
 * @author Pietro Valente
 */

public class ListIteratorAdapter
implements ListIterator
{
    private Vector VectorIterator;
    private int to;
    private int from;
    private int index;
    private boolean next;
    private boolean prev;


    /**
    * Constructs a new ListIteratorAdapter pointing to the position before the fromIndex element, the 
    * ListIteratorAdapter will be valid till toIndex element.
    *
    * @param type is the instance on which ListIteratorAdapter operates.
    * @param fromIndex index of the first element to be returned from the list iterator.
    * @param toIndex index of the last element to be returned from the list iterator.
    * @throws IllegalArgumentException if instance parameter is null.
    * @throws IndexOutOfBoundsException for an illegal endpoint index value
    *         ({@code fromIndex < 0 || toIndex > size || fromIndex > toIndex})
	*/
    public ListIteratorAdapter(Vector type, int fromIndex, int toIndex)
    {
        if(type == null)
        {
            throw new IllegalArgumentException();
        }
        if(fromIndex < 0 || fromIndex > toIndex || toIndex > type.size())
        {
            throw new IndexOutOfBoundsException();
        }
        VectorIterator = type;
        next = false;
        prev = false;
        from = index = fromIndex;
        to = toIndex;
    }

    /**
     * Returns {@code true} if this list iterator has more elements when
     * traversing the list in the forward direction. (In other words,
     * returns {@code true} if {@link #next} would return an element rather
     * than throwing an exception.)
     *
     * @return {@code true} if the list iterator has more elements when
     *         traversing the list in the forward direction
     */
    public boolean hasNext()
    {
        return (index < to+1);
    }

    /**
     * Returns the next element in the list and advances the cursor position.
     * This method may be called repeatedly to iterate through the list,
     * or intermixed with calls to {@link #previous} to go back and forth.
     * (Note that alternating calls to {@code next} and {@code previous}
     * will return the same element repeatedly.)
     *
     * @return the next element in the list
     * @throws NoSuchElementException if the iteration has no next element
     */
    public Object next()
    {
        if(!hasNext())
        {
            throw new NoSuchElementException();
        }
        next = true;
        prev = false;
        return VectorIterator.elementAt(index++);
    }

    /**
     * Returns {@code true} if this list iterator has more elements when
     * traversing the list in the reverse direction.  (In other words,
     * returns {@code true} if {@link #previous} would return an element
     * rather than throwing an exception.)
     *
     * @return {@code true} if the list iterator has more elements when
     *         traversing the list in the reverse direction
     */
    public boolean hasPrevious()
    {
        return index > from;
    }

    /**
     * Returns the previous element in the list and moves the cursor
     * position backwards.  This method may be called repeatedly to
     * iterate through the list backwards, or intermixed with calls to
     * {@link #next} to go back and forth.  (Note that alternating calls
     * to {@code next} and {@code previous} will return the same
     * element repeatedly.)
     *
     * @return the previous element in the list
     * @throws NoSuchElementException if the iteration has no previous
     *         element
     */
    public Object previous()
    {
        if(!hasPrevious())
        {
            throw new NoSuchElementException();
        }
        next = false;
        prev = true;
        return VectorIterator.elementAt(--index);
    }

    /**
     * Returns the index of the element that would be returned by a
     * subsequent call to {@link #next}. (Returns list size if the list
     * iterator is at the end of the list.)
     *
     * @return the index of the element that would be returned by a
     *         subsequent call to {@code next}, or list size if the list
     *         iterator is at the end of the list
     */
    public int nextIndex()
    {
        return index;
    }

    /**
     * Returns the index of the element that would be returned by a
     * subsequent call to {@link #previous}. (Returns -1 if the list
     * iterator is at the beginning of the list.)
     *
     * @return the index of the element that would be returned by a
     *         subsequent call to {@code previous}, or -1 if the list
     *         iterator is at the beginning of the list
     */
    public int previousIndex()
    {
        return index-1;
    }

    /**
     * Removes from the list the last element that was returned by {@link
     * #next} or {@link #previous} (optional operation).  This call can
     * only be made once per call to {@code next} or {@code previous}.
     * It can be made only if {@link #add} has not been
     * called after the last call to {@code next} or {@code previous}.
     *
     * @throws IllegalStateException if neither {@code next} nor
     *         {@code previous} have been called, or {@code remove} or
     *         {@code add} have been called after the last call to
     *         {@code next} or {@code previous}
     */
    public void remove()
    {
        if(next)
        {
            VectorIterator.removeElementAt(--index);
            next = false;
        }
        else if(prev)
        {
            VectorIterator.removeElementAt(index);
            prev = false;
        }
        else
        {
            throw new IllegalStateException("No previous or next call");
        }
        to--;
    }

    /**
     * Replaces the last element returned by {@link #next} or
     * {@link #previous} with the specified element.
     * This call can be made only if neither {@link #remove} nor {@link
     * #add} have been called after the last call to {@code next} or
     * {@code previous}.
     *
     * @param e the element with which to replace the last element returned by
     *          {@code next} or {@code previous}
     * @throws IllegalStateException if neither {@code next} nor
     *         {@code previous} have been called, or {@code remove} or
     *         {@code add} have been called after the last call to
     *         {@code next} or {@code previous}
     */
    public void set(Object e)
    {
        if(next)
        {
            VectorIterator.setElementAt(e, index-1);
        }
        else if(prev)
        {
            VectorIterator.setElementAt(e, index);
        }
        else
        {
            throw new IllegalStateException("No previous or next call");
        }
    }

    /**
     * Inserts the specified element into the list.
     * The element is inserted immediately before the element that
     * would be returned by {@link #next}, if any, and after the element
     * that would be returned by {@link #previous}, if any.  (If the
     * list contains no elements, the new element becomes the sole element
     * on the list.)  The new element is inserted before the implicit
     * cursor: a subsequent call to {@code next} would be unaffected, and a
     * subsequent call to {@code previous} would return the new element.
     * (This call increases by one the value that would be returned by a
     * call to {@code nextIndex} or {@code previousIndex}.)
     *
     * @param e the element to insert
     */
    public void add(Object e)
    {
        VectorIterator.insertElementAt(e, index++);
        prev = next = false;
        to++;
    }
}