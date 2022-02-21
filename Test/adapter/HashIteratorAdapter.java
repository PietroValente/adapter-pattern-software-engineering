package adapter;

import java.util.NoSuchElementException;
import java.util.Vector;
import java.util.Hashtable;
import java.util.Enumeration;

/**
 * <p>This class implements the {@code Iterator} interface. This class allows the 
 * creation of three different iterators based on the type passed in the constructor:
 * values = 0, key =1, entry = 2.
 * 
 * <p>The elements are scrolled in the reverse order established by the Enumeration returned 
 *    by the keys() method of the corresponding Hashtable.
 * 
 * <p>The iterators returned by this class's are <b>not fail-fast</b>.
 * 
 * <p>This class is a member of the <i>adapter package</i>.
 * 
 * @author  Pietro Valente
 */

public class HashIteratorAdapter
implements Iterator
{
    private Hashtable HashIterator;
    private Vector elements;
    private int type; //values = 0, key =1, entry = 2
    private Object prev;
    private int index;

    /**
    * Constructs an HashIteratorAdapter instance, which is in the first position,
    * before the first element.
    *
    * @param i is the reference to the Hashtable that is iterated.
    * @param t is the type of iterator wanted. Values = 0, key =1, entry = 2.
	*/
    public HashIteratorAdapter(Hashtable i, int t)
    {
        HashIterator = i;
        type = t;
        prev = null;
        validation();
        index=0;
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
        validation();
        return 	index<elements.size();
    }

    /**
    * Returns the next element in the iteration.
    * 
    * @return the next element in the iteration.
    * @exception NoSuchElementException if the iteration has no more elements.
	*/
    public Object next()
    {
        validation();
        if(!hasNext())
        {
            throw new NoSuchElementException();
        }
        prev = elements.elementAt(index++);
        if(type == 1)
        {
            return prev;
        }
        else if(type == 0)
        {
            return HashIterator.get(prev);
        }
        return new MyEntry(prev, HashIterator.get(prev));
    }

    /**
     * Removes from the underlying collection the last element returned
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
        validation();
        if(prev == null)
        {
            throw new IllegalStateException();
        }
        HashIterator.remove(prev);
        index--;
        prev = null;
    }

    private void validation()
    {
        elements = new Vector();
        Enumeration e;
        e = HashIterator.keys();
        while(e.hasMoreElements())
        {
            elements.insertElementAt(e.nextElement(),0);
        }
    }
}