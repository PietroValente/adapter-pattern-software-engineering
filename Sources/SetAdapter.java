package adapter;

import java.util.Hashtable;
import java.util.Vector;

/**
 * This class implements the {@code Set} interface, backed by a hash table. This Hashtable is
 * built to have every Set values as Hashtable's key and value.
 *
 * <p>This class does <b>not</b> permits the {@code null} element (as element of the Set).
 * 
 * <p>The iterators returned by this class's {@link #iterator() iterator()} method are <b>not fail-fast</b>.
 *    The elements are scrolled in the reverse order established by the Enumeration returned by the keys() 
 *    method of the corresponding Hashtable.
 *
 * <p>This class uses Objects and not generics, for this reason it does not even check 
 * that the data present are all of the same type. This awareness lies with the user. 
 * The ClassCastException exception is not thrown.
 * 
 * <p>This class is <b>not thread-safe</b>.
 * 
 * <p>This class is a member of the <i>adapter package</i>.
 *
 * @author  Pietro Valente
 */

public class SetAdapter
implements Set
{
    private Hashtable HashInst1;

    //COSTRUTTORI

    /**
    * Constructs a new, empty set; the backing SetAdapter instance has default initial capacity (11) and load factor (0.75).
    * 
    */
    public SetAdapter()
    {
        HashInst1 = new Hashtable();
    }

    /**
    * Constructs a new, empty set; the backing SetAdapter instance has the specified initial capacity and default load factor (0.75).
    * 
    * @param initialCapacity the initial capacity.
    * @exception IllegalArgumentException if the initial capacity is negative.
	*/
    public SetAdapter(int initialCapacity)
    {
        if(initialCapacity<0)
        {
            throw new IllegalArgumentException();
        }
        HashInst1 = new Hashtable(initialCapacity);
    }

    /**
     * Constructs a new set containing the elements in the specified
     * collection.  The {@code SetAdapter} is created with default load factor
     * (0.75) and an initial capacity sufficient to contain the elements in
     * the specified collection.
     *
     * @param c the collection whose elements are to be placed into this set
     * @throws NullPointerException if the specified collection is null
     */
    public SetAdapter(Collection c)
    {
        HashInst1 = new Hashtable();
        addAll(c);
    }
    

    //METODI

    /**
     * Adds the specified element to this set if it is not already present.
     * More formally, adds the specified element
     * {@code e} to this set if the set contains no element {@code e2}
     * such that
     * {@code Objects.equals(e, e2)}.
     * If this set already contains the element, the call leaves the set
     * unchanged and returns {@code false}.  In combination with the
     * restriction on constructors, this ensures that sets never contain
     * duplicate elements.
     *
     * @param e element to be added to this set
     * @return {@code true} if this set did not already contain the specified
     *         element
     * @throws NullPointerException if the specified element is null and this
     *         set does not permit null elements
     */
    public boolean add(Object e)
    {
        if(e == null)
        {
            throw new NullPointerException();
        }
        if(!contains(e))
        {
            HashInst1.put(e,e);
            return true;
        }
        return false;
    }
    
    /**
     * Adds all of the elements in the specified collection to this set if
     * they're not already present. If the specified
     * collection is also a set, the {@code addAll} operation effectively
     * modifies this set so that its value is the <i>union</i> of the two
     * sets.  The behavior of this operation is undefined if the specified
     * collection is modified while the operation is in progress.
     *
     * @param  c collection containing elements to be added to this set
     * @return {@code true} if this set changed as a result of the call
     * @throws NullPointerException if the specified collection contains one
     *         or more null elements or if the specified collection is null
     * @see #add(Object)
     */
    public boolean addAll(Collection c)
    {
        if(c==null)
        {
            throw new NullPointerException();
        }
        if(c.isEmpty())
        {
            return false;
        }
        boolean r = false;
        Object[] temp = c.toArray();
        for(int i = 0; i<temp.length; i++)
        {
            r = add(temp[i]) || r;
        }
        return r;
    }
    
    /**
     * Removes all of the elements from this set.
     * The set will be empty after this call returns.
     */
    public void clear()
    {
        HashInst1.clear();
    }
    
    /**
     * Returns {@code true} if this set contains the specified element.
     * More formally, returns {@code true} if and only if this set
     * contains an element {@code e} such that
     * {@code Objects.equals(o, e)}.
     *
     * @param o element whose presence in this set is to be tested
     * @return {@code true} if this set contains the specified element
     * @throws NullPointerException if the specified element is null
     */
    public boolean contains(Object o)
    {
        if(o == null)
        {
            throw new NullPointerException();
        }
        return HashInst1.containsKey(o);
    }
    
    /**
     * Returns {@code true} if this set contains all of the elements of the
     * specified collection.  If the specified collection is also a set, this
     * method returns {@code true} if it is a <i>subset</i> of this set.
     *
     * @param  c collection to be checked for containment in this set
     * @return {@code true} if this set contains all of the elements of the
     *         specified collection
     * @throws NullPointerException if the specified collection contains one
     *         or more null elements or if the specified collection is null
     * @see    #contains(Object)
     */
    public boolean containsAll(Collection c)
    {
        if(c==null)
        {
            throw new NullPointerException();
        }
        if(c.size() > size())
        {
            return false;
        }
        if(c.isEmpty())
        {
            return true;
        }
        Object[] temp = c.toArray();
        Collection n = new CollectionAdapter();
        for(int i=0; i<temp.length;i++)
        {
            n.add(temp[i]);
        }
        Object[] elements = toArray();
        for(int i = 0; i <elements.length; i++)
        {
            n.remove(elements[i]);
        }
        return n.isEmpty();
    }
    
    /**
     * Compares the specified object with this set for equality.  Returns
     * {@code true} if the specified object is also a set, the two sets
     * have the same size, and every member of the specified set is
     * contained in this set (or equivalently, every member of this set is
     * contained in the specified set).
     *
     * @param o object to be compared for equality with this set
     * @return {@code true} if the specified object is equal to this set
     */
    public boolean equals(Object o)
    {
        if (o == this)
        {
            return true;
        }
        if (!(o instanceof Set))
        {
            return false;
        }
        Collection c = (Collection) o;
        if (c.size() != size())
            return false;
        try
        {
            return containsAll(c);
        }
        catch (ClassCastException | NullPointerException e)
        {
            return false;
        }
    }
    
    /**
     * Returns the hash code value for this set.  The hash code of a set is
     * defined to be the sum of the hash codes of the elements in the set.
     * This ensures that {@code s1.equals(s2)} implies that
     * {@code s1.hashCode()==s2.hashCode()} for any two sets {@code s1}
     * and {@code s2}, as required by the general contract of
     * {@link Object#hashCode}.
     *
     * @return the hash code value for this set
     * @see Object#equals(Object)
     * @see Set#equals(Object)
     */
    public int hashCode()
    {
        int h = 0;
        Iterator i = iterator();
        while (i.hasNext())
        {
            Object obj = i.next();
            h += obj.hashCode();
        }
        return h;
    }
    
    /**
     * Returns {@code true} if this set contains no elements.
     *
     * @return {@code true} if this set contains no elements
     */
    public boolean isEmpty()
    {
        return HashInst1.isEmpty();
    }
    
    /**
     * Returns an iterator over the elements in this set. The elements are scrolled in the reverse
     * order established by the Enumeration returned by the keys() method of the corresponding Hashtable.
     * 
     * <p>Any changes made by scrolling on the iterator are propagated to the list and vice versa.
     *
     * @return an iterator over the elements in this set
     */
    public Iterator iterator()
    {
        return new HashIteratorAdapter(HashInst1,1); //values = 0, key =1, entry = 2
    }
    
    /**
     * Removes the specified element from this set if it is present. 
     * More formally, removes an element {@code e}
     * such that
     * {@code Objects.equals(o, e)}, if
     * this set contains such an element. Returns {@code true} if this set
     * contained the element (or equivalently, if this set changed as a
     * result of the call).  (This set will not contain the element once the
     * call returns.)
     *
     * @param o object to be removed from this set, if present
     * @return {@code true} if this set contained the specified element
     * @throws NullPointerException if the specified element is null
     */
    public boolean remove(Object o)
    {
        if (o==null)
        {
            throw new NullPointerException();
        }
        if(contains(o))
        {
            HashInst1.remove(o);
            return true;
        }
        return false;
    }
    
    /**
     * Removes from this set all of its elements that are contained in the
     * specified collection. If the specified
     * collection is also a set, this operation effectively modifies this
     * set so that its value is the <i>asymmetric set difference</i> of
     * the two sets.
     *
     * @param  c collection containing elements to be removed from this set
     * @return {@code true} if this set changed as a result of the call
     * @throws NullPointerException if this set contains a null element
     *         or if the specified collection is null
     * @see #remove(Object)
     * @see #contains(Object)
     */
    public boolean removeAll(Collection c)
    {
        if(c==null)
        {
            throw new NullPointerException();
        }
        if(c.isEmpty())
        {
            return false;
        }
        boolean flag = false;
        Iterator it = c.iterator();
        while(it.hasNext())
        {
            flag = remove(it.next()) || flag;
        }
        return flag;
    }
    
    /**
     * Retains only the elements in this set that are contained in the
     * specified collection.  In other words, removes
     * from this set all of its elements that are not contained in the
     * specified collection.  If the specified collection is also a set, this
     * operation effectively modifies this set so that its value is the
     * <i>intersection</i> of the two sets.
     *
     * @param  c collection containing elements to be retained in this set
     * @return {@code true} if this list changed as a result of the call
     * @throws NullPointerException if this set contains a null element
     *         or if the specified collection is null
     * @see #remove(Object)
     */
    public boolean retainAll(Collection c)
    {
        if(c==null)
        {
            throw new NullPointerException();
        }
        if(c.isEmpty() || isEmpty())
        {
            if(isEmpty())
            {
                clear();
                return false;
            }
            clear();
            return true;
        }
        boolean r = false;
        Object[] coll= c.toArray();
        int size = size();
        Vector temp= new Vector();
        for(int i=0 ; i<coll.length;i++)
        {
            if(contains(coll[i]))
            {
                temp.addElement(coll[i]);
                remove(coll[i]);
            }
        }
        if(temp.size() != size)
        {
            r=true;
        }
        clear();
        for(int i=0 ; i<temp.size();i++)
        {
            add(temp.elementAt(i));
        }
        return r;
    }
    
    /**
     * Returns the number of elements in this set (its cardinality).  If this
     * set contains more than {@code Integer.MAX_VALUE} elements, returns
     * {@code Integer.MAX_VALUE}.
     *
     * @return the number of elements in this set (its cardinality)
     */
    public int size()
    {
        return HashInst1.size();
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
     * @return an array containing all the elements in this set
     */
    public Object[] toArray()
    {
        Object[] a = new Object[size()];
        Iterator it = iterator();
        int i=0;
        while(it.hasNext())
        {
            a[i++]=it.next();
        }
        return a;
    }
    
    /**
     * Returns an array containing all of the elements in this set; the
     * runtime type of the returned array is that of the specified array.
     * If the set fits in the specified array, it is returned therein.
     * Otherwise, a new array is allocated with the the size of this set.
     *
     * @param a the array into which the elements of this set are to be
     *        stored, if it is big enough; otherwise, a new array of the same
     *        runtime type is allocated for this purpose.
     * @return an array containing all the elements in this set, null if there are no elements in the array
     * @throws NullPointerException if the specified array is null
     */
    public Object[] toArray(Object[] a)
    {
        if(a==null)
        {
            throw new NullPointerException();
        }
        if(isEmpty())
        {
            return null;
        }
        if(a.length < size())
        {
            a = new Object[size()];
        }
        Iterator it = iterator();
        int i=0;
        while(it.hasNext())
        {
            a[i++]=it.next();
        }
        return a;
    }
}