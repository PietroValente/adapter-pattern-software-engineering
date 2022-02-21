package adapter;

import java.util.Vector;

/**
 * <p>A collection represents a
 * group of objects, known as its elements. Some collections allow duplicate elements
 * and others do not. Some are ordered and others unordered. Therefore {@code Collecion} is a fairly 
 * general concept.
 * 
 * <p>This class implements the {@code Collecion} interface, backed by a {@code ListAdapter} instance. This 
 * Collection admits duplicates but <b>not</b> null elements.
 *
 * <p>The iterators returned by this class's {@link #iterator() iterator()} method are <b>not fail-fast</b>.
 * 
 * <p>This class uses Objects and not generics, for this reason it does not even check 
 * that the data present are all of the same type. This awareness lies with the user. 
 * The ClassCastException exception is not thrown.
 * 
 * <p>This class is <b>not thread-safe</b>.
 * 
 * <p>This class is a member of the <i>adapter package</i>.
 * 
 * @author Pietro Valente
 */

public class CollectionAdapter
implements Collection
{
    private ListAdapter ListInst;

    /**
	* Constructs an empty collection.
	*/
    public CollectionAdapter()
    {
        ListInst = new ListAdapter();
    }

    /**
     * Ensures that this collection contains the specified element.
     * Returns {@code true} if this collection changed as a
     * result of the call.<p>
     *
     * This collection does not accept the null value.
     *
     * @param e element to be appended to this list
     * @return {@code true} (as specified by {@link Collection#add})
     * @throws NullPointerException if the specified element is null
     */
    public boolean add(Object e)
    {
        return ListInst.add(e);
    }
    
    /**
     * Adds all of the elements in the specified collection to this collection.
     * The behavior of this operation is undefined if
     * the specified collection is modified while the operation is in progress.
     *
     * @param c collection containing elements to be added to this list
     * @return {@code true} if this list changed as a result of the call
     * @throws NullPointerException if the specified collection contains one
     *         or more null elements elements, or if the specified collection is null
     * @see #add(Object)
     */
    public boolean addAll(Collection c)
    {
        return ListInst.addAll(c);
    }
    
    /**
     * Removes all of the elements from this collection.
     * The collection will be empty after this method returns.
     */
    public void clear()
    {
        ListInst.clear();
    }
    
    /**
     * Returns {@code true} if this collection contains the specified element.
     * More formally, returns {@code true} if and only if this collection
     * contains at least one element {@code e} such that
     * {@code Objects.equals(o, e)}.
     *
     * @param o element whose presence in this collection is to be tested
     * @return {@code true} if this collection contains the specified
     *         element
     * @throws NullPointerException if the specified element is null
     */
    public boolean contains(Object o)
    {
        return ListInst.contains(o);
    }
    
    /**
     * Returns {@code true} if this list contains all of the elements of the
     * specified collection. If there are duplicate items, they must be contained 
     * the same number of times.
     *
     * @param  c collection to be checked for containment in this collection
     * @return {@code true} if this collection contains all of the elements
     *         in the specified collection
     * @throws NullPointerException if the specified collection contains one
     *         or more null elements or if the specified collection is null.
     * @see    #contains(Object)
     */
    public boolean containsAll(Collection c)
    {
        return ListInst.containsAll(c);
    }
    
    /**
     * Compares the specified object with this collection for equality. <p>
     *
     * The general contract for the {@code Object.equals} method states that
     * equals must be symmetric (in other words, {@code a.equals(b)} if and
     * only if {@code b.equals(a)}).  The contracts for {@code List.equals}
     * and {@code Set.equals} state that lists are only equal to other lists,
     * and sets to other sets.  Thus, a custom {@code equals} method for a
     * collection class that implements neither the {@code List} nor
     * {@code Set} interface must return {@code false} when this collection
     * is compared to any list or set.  (By the same logic, it is not possible
     * to write a class that correctly implements both the {@code Set} and
     * {@code List} interfaces.)
     *
     * @param o object to be compared for equality with this collection
     * @return {@code true} if the specified object is equal to this
     * collection
     *
     * @see Object#equals(Object)
     * @see Set#equals(Object)
     * @see List#equals(Object)
     */
    public boolean equals(Object o)
    {
        if (o == this)
        {
            return true;
        }
        if (!(o instanceof CollectionAdapter))
        {
            return false;
        }
        Collection c = (Collection)o;
        if(c.size() != size())
        {
            return false;
        }
        return containsAll(c);
    }
    
    /**
     * Returns the hash code value for this collection. In this case,
     * the hashCode() value is the same as the ListAdapter 
     * containing the elements.
     *
     * @return the hash code value for this collection
     *
     * @see Object#hashCode()
     * @see Object#equals(Object)
     */
    public int hashCode()
    {
        return ListInst.hashCode();
    }
    
    /**
     * Returns {@code true} if this collection contains no elements.
     *
     * @return {@code true} if this collection contains no elements
     */
    public boolean isEmpty()
    {
        return ListInst.isEmpty();
    }
    
    /**
     * Returns an iterator over the elements in this collection.  The iterator 
     * scrolls the elements from the first inserted and still present until the last.
     *
     * @return an {@code Iterator} over the elements in this collection
     */
    public Iterator iterator()
    {
        return ListInst.iterator();
    }
    
    /**
     * Removes a single instance of the specified element from this
     * collection, if it is present.  More formally,
     * removes an element {@code e} such that
     * {@code Objects.equals(o, e)}, if
     * this collection contains one or more such elements.  Returns
     * {@code true} if this collection contained the specified element (or
     * equivalently, if this collection changed as a result of the call).
     *
     * @param o element to be removed from this collection, if present
     * @return {@code true} if an element was removed as a result of this call
     * @throws NullPointerException if the specified element is null
     */
    public boolean remove(Object o)
    {
        return ListInst.remove(o);
    }
    
    /**
     * Removes all of this collection's elements that are also contained in the
     * specified collection. After this call returns,
     * this collection will contain no elements in common with the specified
     * collection.
     *
     * @param c collection containing elements to be removed from this collection
     * @return {@code true} if this collection changed as a result of the
     *         call
     * @throws NullPointerException if this collection contains one or more
     *         null elements or if the specified collection is null
     * @see #remove(Object)
     * @see #contains(Object)
     */
    public boolean removeAll(Collection c)
    {
        return ListInst.removeAll(c);
    }
    
    /**
     * Retains only the elements in this collection that are contained in the
     * specified collection. In other words, removes from
     * this collection all of its elements that are not contained in the
     * specified collection.
     *
     * @param c collection containing elements to be retained in this collection
     * @return {@code true} if this collection changed as a result of the call
     * @throws NullPointerException if this collection contains one or more
     *         null elements
     * 
     * @see #remove(Object)
     * @see #contains(Object)
     */
    public boolean retainAll(Collection c)
    {
        return ListInst.retainAll(c);
    }
    
    /**
     * Returns the number of elements in this collection.  If this collection
     * contains more than {@code Integer.MAX_VALUE} elements, returns
     * {@code Integer.MAX_VALUE}.
     *
     * @return the number of elements in this collection
     */
    public int size()
    {
        return ListInst.size();
    }
    
    /**
     * Returns an array containing all of the elements in this set.
     * In order from the first that was inserted into the set until the last inserted.
     *
     * <p>The returned array will be "safe" in that no references to it
     * are maintained by this set.  (In other words, this method must
     * allocate a new array even if this set is backed by an array).
     * The caller is thus free to modify the returned array.
     *
     * @return an array of Objects containing all of the elements in this collection
     */
    public Object[] toArray()
    {
        return ListInst.toArray();
    }
    
    /**
     * Returns an array containing all of the elements in this set; the
     * runtime type of the returned array is that of the specified array.
     * If the set fits in the specified array, it is returned therein.
     * Otherwise, a new array is allocated with the the size of this set.
     *
     * @param a the array into which the elements of this collection are to be
     *        stored, if it is big enough; otherwise, a new array of the same
     *        runtime type is allocated for this purpose.
     * @return an array containing all of the elements in this collection
     * @throws NullPointerException if the specified array is null
     */
    public Object[] toArray(Object[] a)
    {
        return ListInst.toArray(a);
    }
}