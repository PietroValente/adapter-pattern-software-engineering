package adapter;

import java.util.Vector;

/**
 * <p>This class implements the {@code List} interface, backed by a {@code Vector} instance.
 * 
 * <p>It is an ordered collection (also known as a <i>sequence</i>).  The user of this
 * class has precise control over where in the list each element is
 * inserted.  The user can access elements by their integer index (position in
 * the list), and search for elements in the list.
 *
 * <p>Unlike sets, this list allow duplicate elements.  More formally,
 * lists typically allow pairs of elements {@code e1} and {@code e2}
 * such that {@code e1.equals(e2)}.
 * 
 * <p>The iterators returned by this class's {@link #iterator() iterator()}, {@link #listIterator() listIterator()}
 * {@link #listIterator(int) listIterator(int)} methods are <b>not fail-fast</b>.
 * 
 * <p>This class does <b>not</b> permits the {@code null} element.
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

public class ListAdapter
implements List
{
    private Vector VecInst;

    /**
	* Constructs an empty list.
	*/
    public ListAdapter()
    {
        VecInst = new Vector();
    }

    /**
    * Constructs a list containing the elements of the specified collection, in the order
    * they are returned by the collection's iterator.
    * @param c the collection whose elements are to be placed into this list
    * @exception NullPointerException if the specified collection is null
	*/
    public ListAdapter(Collection c)
    {
        VecInst = new Vector();
        addAll(c);
    }

    /**
     * Inserts the specified element at the specified position in this list.
     * Shifts the element currently at that position
     * (if any) and any subsequent elements to the right (adds one to their
     * indices).
     *
     * @param index index at which the specified element is to be inserted
     * @param element element to be inserted
     * @throws NullPointerException if the specified element is null
     * @throws IndexOutOfBoundsException if the index is out of range
     *         ({@code index < 0 || index > size()})
     */
    public void add(int index, Object element)
    {
        if (element == null)
        {
            throw new NullPointerException();    
        }
        if(index < 0 || index > size())
        {
            throw new IndexOutOfBoundsException();
        }
        if(isEmpty())
        {
            VecInst.addElement(element);
            return;
        }
        VecInst.insertElementAt(element,index);
    }
    
    /**
     * Appends the specified element to the end of this list.
     *
     * @param e element to be appended to this list
     * @return {@code true} (as specified by {@link Collection#add})
     * @throws NullPointerException if the specified element is null
     */
    public boolean add(Object e)
    {
        if(e==null)
        {
            throw new NullPointerException();
        }
        VecInst.addElement(e);
        return true;
    }
    
    /**
     * Inserts all of the elements in the specified collection into this
     * list at the specified position.  Shifts the
     * element currently at that position (if any) and any subsequent
     * elements to the right (increases their indices).  The new elements
     * will appear in this list in the order that they are returned by the
     * specified collection's iterator.  The behavior of this operation is
     * undefined if the specified collection is modified while the
     * operation is in progress.
     *
     * @param index index at which to insert the first element from the
     *              specified collection
     * @param c collection containing elements to be added to this list
     * @return {@code true} if this list changed as a result of the call
     * @throws NullPointerException if the specified collection contains one
     *         or more null elements or if the specified collection is null
     * @throws IndexOutOfBoundsException if the index is out of range
     *         ({@code index < 0 || index > size()})
     */
    public boolean addAll(int index, Collection c)
    {
        if (c==null)
        {
            throw new NullPointerException();
        }
        if(index < 0 || index > size())
        {
            throw new IndexOutOfBoundsException();
        }
        if(c.isEmpty())
        {
            return false;
        }
        Iterator it = c.iterator();
        int i = 0;
        while(it.hasNext())
        {
            add(index+i,it.next());
            i++;
        }
        return true;
    }
    
    /**
     * Appends all of the elements in the specified collection to the end of
     * this list, in the order that they are returned by the specified
     * collection's iterator.  The behavior of this
     * operation is undefined if the specified collection is modified while
     * the operation is in progress.
     *
     * @param c collection containing elements to be added to this list
     * @return {@code true} if this list changed as a result of the call
     * @throws NullPointerException if the specified collection contains one
     *         or more null elements or if the specified collection is null
     * @see #add(Object)
     */
    public boolean addAll(Collection c)
    {
        if (c==null)
        {
            throw new NullPointerException();
        }
        if(c.isEmpty())
        {
            return false;
        }
        Iterator it = c.iterator();
        while(it.hasNext())
        {
            add(it.next());
        }
        return true;
    }

    /**
     * Removes all of the elements from this list.
     * The list will be empty after this call returns.
     */
    public void clear()
    {
        VecInst.removeAllElements();
    }

    /**
     * Returns {@code true} if this list contains the specified element.
     * More formally, returns {@code true} if and only if this list contains
     * at least one element {@code e} such that
     * {@code Objects.equals(o, e)}.
     *
     * @param o element whose presence in this list is to be tested
     * @return {@code true} if this list contains the specified element
     * @throws NullPointerException if the specified element is null
     */
    public boolean contains(Object o)
    {
        if(o==null)
        {
            throw new NullPointerException();
        }
        return VecInst.contains(o);
    }
    
    /**
     * Returns {@code true} if this list contains all of the elements of the
     * specified collection. If there are duplicate items, they must be contained 
     * the same number.
     *
     * @param  c collection to be checked for containment in this list
     * @return {@code true} if this list contains all of the elements of the
     *         specified collection
     * @throws NullPointerException if the specified collection contains one
     *         or more null elements
     *         or if the specified collection is null
     * @see #contains(Object)
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
     * Compares the specified object with this list for equality.  Returns
     * {@code true} if and only if the specified object is also a list, both
     * lists have the same size, and all corresponding pairs of elements in
     * the two lists are <i>equal</i>.  (Two elements {@code e1} and
     * {@code e2} are <i>equal</i> if {@code Objects.equals(e1, e2)}.)
     * In other words, two lists are defined to be
     * equal if they contain the same elements in the same order.
     *
     * @param o the object to be compared for equality with this list
     * @return {@code true} if the specified object is equal to this list
     */
    public boolean equals(Object o)
    {
        if (o == this)
        {
            return true;
        }
        if (!(o instanceof List))
        {
            return false;
        }
        if(((List) o).isEmpty() || isEmpty())
        {
            if(((List) o).isEmpty() && isEmpty())
            {
                return true;
            }
            return false;
        }
        ListIterator e1 = listIterator();
        ListIterator e2 = ((List) o).listIterator();
        while (e1.hasNext() && e2.hasNext())
        {
            Object o1 = e1.next();
            Object o2 = e2.next();
            if(!o1.equals(o2))
            {
                return false;
            }
        }
        return !(e1.hasNext() || e2.hasNext());
    }
    
    /**
     * Returns the element at the specified position in this list.
     *
     * @param index index of the element to return
     * @return the element at the specified position in this list
     * @throws IndexOutOfBoundsException if the index is out of range
     *         ({@code index < 0 || index >= size()})
     */
    public Object get(int index)
    {
        if(index < 0 || index >= size())
        {
            throw new IndexOutOfBoundsException();
        }
        return VecInst.elementAt(index);
    }
    
    /**
     * Returns the hash code value for this list.  The hash code of a list
     * is defined to be the result of the following calculation:
     * <pre>{@code
     *     int hashCode = 1;
     *     for (E e : list)
     *         hashCode = 31*hashCode + (e==null ? 0 : e.hashCode());
     * }</pre>
     *
     * @return the hash code value for this list
     * @see Object#equals(Object)
     * @see #equals(Object)
     */
    public int hashCode()
    {
        int h = 0;
        Iterator i = iterator();
        while (i.hasNext())
        {
            Object obj = i.next();
            h = 31*h + obj.hashCode();
        }
        return h;
    }
    
    /**
     * Returns the index of the first occurrence of the specified element
     * in this list, or -1 if this list does not contain the element.
     * More formally, returns the lowest index {@code i} such that
     * {@code Objects.equals(o, get(i))},
     * or -1 if there is no such index.
     *
     * @param o element to search for
     * @return the index of the first occurrence of the specified element in
     *         this list, or -1 if this list does not contain the element
     * @throws NullPointerException if the specified element is null
     */
    public int indexOf(Object o)
    {
        if(o==null)
        {
            throw new NullPointerException();
        }
        return VecInst.indexOf(o);
    }
    
    /**
     * Returns {@code true} if this list contains no elements.
     *
     * @return {@code true} if this list contains no elements
     */
    public boolean isEmpty()
    {
        return size()==0;
    }
    
    /**
     * Returns an iterator over the elements in this list in proper sequence.
     * 
     * <p>Any changes made by scrolling on the iterator are propagated to the list and vice versa.
     *
     * @return an iterator over the elements in this list in proper sequence
     */
    public Iterator iterator()
    {
        return new VectorIteratorAdapter(VecInst, 0, size()-1);
    }
    
    /**
     * Returns the index of the last occurrence of the specified element
     * in this list, or -1 if this list does not contain the element.
     * More formally, returns the highest index {@code i} such that
     * {@code Objects.equals(o, get(i))},
     * or -1 if there is no such index.
     *
     * @param o element to search for
     * @return the index of the last occurrence of the specified element in
     *         this list, or -1 if this list does not contain the element
     * @throws NullPointerException if the specified element is null
     */
    public int lastIndexOf(Object o)
    {
        if(o==null)
        {
            throw new NullPointerException();
        }
        return VecInst.lastIndexOf(o);
    }
    
    /**
     * Returns a list iterator over the elements in this list (in proper
     * sequence).
     * 
     * <p>Any changes made by scrolling on the listIterator are propagated to the list and vice versa.
     *
     * @return a list iterator over the elements in this list (in proper
     *         sequence)
     */
    public ListIterator listIterator()
    {
        return new ListIteratorAdapter(VecInst, 0, size()-1);
    }
    
    /**
     * Returns a list iterator over the elements in this list (in proper
     * sequence), starting at the specified position in the list.
     * The specified index indicates the first element that would be
     * returned by an initial call to {@link ListIterator#next next}.
     * An initial call to {@link ListIterator#previous previous} would
     * return the element with the specified index minus one.
     * 
     * <p>Any changes made by scrolling on the listIterator are propagated to the list and vice versa.
     *
     * @param index index of the first element to be returned from the
     *        list iterator (by a call to {@link ListIterator#next next})
     * @return a list iterator over the elements in this list (in proper
     *         sequence), starting at the specified position in the list
     * @throws IndexOutOfBoundsException if the index is out of range
     *         ({@code index < 0 || index > size()})
     */
    public ListIterator listIterator(int index)
    {
        return new ListIteratorAdapter(VecInst, index, size()-1);
    }

    /**
     * Removes the element at the specified position in this list. 
     * Shifts any subsequent elements to the left (subtracts one
     * from their indices).  Returns the element that was removed from the
     * list.
     *
     * @param index the index of the element to be removed
     * @return the element previously at the specified position
     * @throws IndexOutOfBoundsException if the index is out of range
     *         ({@code index < 0 || index >= size()})
     */
    public Object remove(int index)
    {
        if(index < 0 || index >= size())
        {
            throw new IndexOutOfBoundsException();
        }
        Object temp= VecInst.elementAt(index);
        VecInst.removeElementAt(index);
        return temp;
    }

    /**
     * Removes the first occurrence of the specified element from this list,
     * if it is present.  If this list does not contain
     * the element, it is unchanged.  More formally, removes the element with
     * the lowest index {@code i} such that
     * {@code Objects.equals(o, get(i))}
     * (if such an element exists).  Returns {@code true} if this list
     * contained the specified element (or equivalently, if this list changed
     * as a result of the call).
     *
     * @param o element to be removed from this list, if present
     * @return {@code true} if this list contained the specified element
     * @throws NullPointerException if the specified element is null
     */
    public boolean remove(Object o)
    {
        if(o==null)
        {
            throw new NullPointerException();
        }
        int index = indexOf(o);
        if(index == -1)
        {
            return false;
        }
        remove(index);
        return true;
    }
    
    /**
     * Removes from this list all of its elements that are contained in the
     * specified collection.
     *
     * @param c collection containing elements to be removed from this list
     * @return {@code true} if this list changed as a result of the call
     * @throws NullPointerException if this list contains a null element
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
            flag = remove((Object)it.next()) || flag;
        }
        return flag;
    }
    
    /**
     * Retains only the elements in this list that are contained in the
     * specified collection. In other words, removes
     * from this list all of its elements that are not contained in the
     * specified collection.If there are duplicate items, they must be contained 
     * the same number.
     *
     * @param c collection containing elements to be retained in this list
     * @return {@code true} if this list changed as a result of the call
     * @throws NullPointerException if this list contains a null element
     *         or if the specified collection is null
     * @see #remove(Object)
     * @see #contains(Object)
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
        boolean r=false;
        int size = size();
        Object[] coll= c.toArray();
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
     * Replaces the element at the specified position in this list with the
     * specified element.
     *
     * @param index index of the element to replace
     * @param element element to be stored at the specified position
     * @return the element previously at the specified position
     * @throws NullPointerException if the specified element is null
     * @throws IndexOutOfBoundsException if the index is out of range
     *         ({@code index < 0 || index >= size()})
     */
    public Object set(int index, Object element)
    {
        if(element == null)
        {
            throw new NullPointerException();
        }
        if(index < 0 || index >= size())
        {
            throw new IndexOutOfBoundsException();
        }
        Object temp = VecInst.elementAt(index);
        VecInst.setElementAt(element, index);
        return temp;
    }
    
    /**
     * Returns the number of elements in this list.  If this list contains
     * more than {@code Integer.MAX_VALUE} elements, returns
     * {@code Integer.MAX_VALUE}.
     *
     * @return the number of elements in this list
     */
    public int size()
    {
        return VecInst.size();
    }
    
    /**
     * Returns a view of the portion of this list between the specified
     * {@code fromIndex}, inclusive, and {@code toIndex}, inclusive.
     * The returned list is backed by this list, so non-structural
     * changes in the returned list are reflected in this list, and vice-versa.
     * The returned list supports all of the optional list operations supported
     * by this list.<p>
     *
     * This method eliminates the need for explicit range operations (of
     * the sort that commonly exist for arrays). Any operation that expects
     * a list can be used as a range operation by passing a subList view
     * instead of a whole list.  For example, the following idiom
     * removes a range of elements from a list:
     * <pre>{@code
     *      list.subList(from, to).clear();
     * }</pre>
     * Similar idioms may be constructed for {@code indexOf} and
     * {@code lastIndexOf}, and all of the algorithms in the
     * {@code Collections} class can be applied to a subList.<p>
     *
     * If the list is modified while an iteration over the collection is in 
     * progress the result is always valid, in fact the iterator sees every 
     * change made.
     *
     * @param fromIndex low endpoint (inclusive) of the subList
     * @param toIndex high endpoint (inclusive) of the subList
     * @return a view of the specified range within this list
     * @throws IndexOutOfBoundsException for an illegal endpoint index value
     *         ({@code fromIndex < 0 || toIndex > size ||
     *         fromIndex > toIndex})
     */
    public List subList(int fromIndex, int toIndex)
    {
        if(fromIndex < 0 || toIndex > size() || fromIndex > toIndex)
        {
            throw new IndexOutOfBoundsException();
        }
        return new subList(fromIndex, toIndex);
    }
    private class subList
    extends ListAdapter
    {
        private int from;
        private int to;

        public subList(int fromIndex, int toIndex)
        {
            from = fromIndex;
            to = toIndex;
        }

        public void add(int index, Object element)
        {
            if (element == null)
            {
                throw new NullPointerException();    
            }
            if(index < 0 || index > size())
            {
                throw new IndexOutOfBoundsException();
            }
            if(isEmpty())
            {
                VecInst.addElement(element);
                to++;
                return;
            }
            VecInst.insertElementAt(element,index+from);
            to++;
        }

        public boolean add(Object e)
        {
            if(e==null)
            {
                throw new NullPointerException();
            }
            VecInst.insertElementAt(e,++to);
            return true;
        }

        public void clear()
        {
            int size = size();
            for(int i = 0; i< size; i++)
            {
                remove(0);
            }
        }
        
        public boolean contains(Object o)
        {
            if(o==null)
            {
                throw new NullPointerException();
            }
            for(int i = from; i<=to; i++)
            {
                if(VecInst.elementAt(i).equals(o))
                {
                    return true;
                }
            }
            return false;
        }

        public Object get(int index)
        {
            if(index < 0 || index >= size())
            {
                throw new IndexOutOfBoundsException();
            }
            return VecInst.elementAt(index+from);
        }

        public int indexOf(Object o)
        {
            if(o==null)
            {
                throw new NullPointerException();
            }
            for(int i = from; i<=to; i++)
            {
                if(VecInst.elementAt(i).equals(o))
                {
                    return i-from;
                }
            }
            return -1;
        }

        public Iterator iterator()
        {
            return new VectorIteratorAdapter(VecInst, from, to);
        }

        public int lastIndexOf(Object o)
        {
            if(o==null)
            {
                throw new NullPointerException();
            }
            int r = -1;
            for(int i = from; i<=to; i++)
            {
                if(VecInst.elementAt(i).equals(o))
                {
                    r = i-from;
                }
            }
            return r;
        }

        public ListIterator listIterator()
        {
            return new ListIteratorAdapter(VecInst, from, to);
        }

        public ListIterator listIterator(int index)
        {

            return new ListIteratorAdapter(VecInst, index+from , to);
        }

        public Object remove(int index)
        {
            if(index < 0 || index >= size())
            {
                throw new IndexOutOfBoundsException();
            }
            Object temp= VecInst.elementAt(index+from);
            VecInst.removeElementAt(index+from);
            to--;
            return temp;
        }

        public boolean remove(Object o)
        {
            if(o==null)
            {
                throw new NullPointerException();
            }
            int index = indexOf(o);
            if(index == -1)
            {
                return false;
            }
            remove(index);
            return true;
        }

        public Object set(int index, Object element)
        {
            if(element == null)
            {
                throw new NullPointerException();
            }
            if(index < 0 || index >= size())
            {
                throw new IndexOutOfBoundsException();
            }
            Object temp = VecInst.elementAt(index+from);
            VecInst.setElementAt(element, index+from);
            return temp;
        }

        public int size()
        {
            return to-from+1;
        }
    }

    /**
     * Returns an array containing all of the elements in this list in proper
     * sequence (from first to last element).
     *
     * <p>The returned array will be "safe" in that no references to it are
     * maintained by this list.  (In other words, this method must
     * allocate a new array even if this list is backed by an array).
     * The caller is thus free to modify the returned array.
     *
     * @return an array containing all of the elements in this list in proper
     *         sequence
     * @see Arrays#asList(Object[])
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
     * Returns an array containing all of the elements in this list in proper 
     * sequence; the runtime type of the returned array is that of the specified 
     * array. Obeys the general contract of the Collection.toArray(Object[]) method.
     *
     * @param a the array into which the elements of this list are to
     *          be stored, if it is big enough; otherwise, a new array of the
     *          same runtime type is allocated for this purpose.
     * @return an array containing the elements of this list
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