package adapter;

import java.util.Vector;
import java.util.Hashtable;
import java.util.Enumeration;
import java.util.NoSuchElementException;

/**
 * <p>Vector implementation of the {@code List} interface, backed by a {@code Hashtable} instance.
 * Implements all optional map operations, and permits all elements, <b>except</b>
 * {@code null}.
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

public class MapAdapter
implements Map
{
    private Hashtable HashInst2;

    /**
    * Constructs an empty MapAdapter with the default initial capacity (11) and the default load factor (0.75).
    * 
    */
    public MapAdapter()
    {
        HashInst2 = new Hashtable();
    }

    /**
    * Constructs an empty MapAdapter with the specified initial capacity and the default load factor (0.75).
    * 
    * @param initialCapacity the initial capacity.
    * 
    * @exception IllegalArgumentException if the initial capacity is negative.
	*/
    public MapAdapter(int initialCapacity)
    {
        if(initialCapacity<0)
        {
            throw new IllegalArgumentException();
        }
        HashInst2 = new Hashtable(initialCapacity);
    }

    /**
     * Removes all of the mappings from this map.
     * The map will be empty after this call returns.
     *
     */
    public void clear()
    {
        HashInst2.clear();
    }
    
    /**
     * Returns {@code true} if this map contains a mapping for the specified
     * key.  More formally, returns {@code true} if and only if
     * this map contains a mapping for a key {@code k} such that
     * {@code Objects.equals(key, k)}.  (There can be
     * at most one such mapping.)
     *
     * @param key key whose presence in this map is to be tested
     * @return {@code true} if this map contains a mapping for the specified
     *         key
     * @throws NullPointerException if the specified key is null
     */
    public boolean containsKey(Object key)
    {
        if(key == null)
        {
            throw new NullPointerException();
        }
        return HashInst2.containsKey(key);
    }
    
    /**
     * Returns {@code true} if this map maps one or more keys to the
     * specified value.  More formally, returns {@code true} if and only if
     * this map contains at least one mapping to a value {@code v} such that
     * {@code Objects.equals(value, v)}.  This operation
     * will probably require time linear in the map size for most
     * implementations of the {@code Map} interface.
     *
     * @param value value whose presence in this map is to be tested
     * @return {@code true} if this map maps one or more keys to the
     *         specified value
     * @throws NullPointerException if the specified value is null
     */
    public boolean containsValue(Object value)
    {
        if(value == null)
        {
            throw new NullPointerException();
        }
        return HashInst2.contains(value);
    }
    
    /**
     * Returns a {@link Set} view of the mappings contained in this map.
     * The set is backed by the map, so changes to the map are
     * reflected in the set, and vice-versa.  If the map is modified
     * while an iteration the result is always valid, in fact the iterator 
     * sees every change made. The set
     * supports element removal, which removes the corresponding
     * mapping from the map, via the {@code Iterator.remove},
     * {@code Set.remove}, {@code removeAll}, {@code retainAll} and
     * {@code clear} operations.  It does not support the
     * {@code add} or {@code addAll} operations.
     *
     * @return a set view of the mappings contained in this map
     */
    public Set entrySet()
    {
        return new entrySet();
    }
    private class entrySet
    extends SetAdapter
    {
        public boolean add(Object e)
        {
            throw new UnsupportedOperationException();
        }
        
        public boolean addAll(Collection c)
        {
            throw new UnsupportedOperationException();
        }
        
        public void clear()
        {
            HashInst2.clear();
        }
        
        public boolean contains(Object o)
        {
            Object key = ((Entry)o).getKey();
            return HashInst2.containsKey(key) && (HashInst2.get(key).equals(((Entry)o).getValue()));
        }

        public boolean isEmpty()
        {
            return HashInst2.isEmpty();
        }
        
        public Iterator iterator()
        {
            return new HashIteratorAdapter(HashInst2, 2); //values = 0, key =1, entry = 2
        }
        
        public boolean remove(Object o)
        {
            if(o ==null)
            {
                throw new NullPointerException();
            }
            if(!contains(o))
            {
                return false;
            }
            HashInst2.remove(((Entry)o).getKey());
            return true;
        }
        
        public boolean retainAll(Collection c)
        {
            if(c==null)
            {
                throw new NullPointerException();
            }
            boolean r = false;
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
            if(temp.size() != size())
            {
                r=true;
            }
            clear();
            for(int i=0 ; i<temp.size();i++)
            {
                HashInst2.put(((Map.Entry)temp.elementAt(i)).getKey(), ((Map.Entry)temp.elementAt(i)).getValue());
            }
            return r;
        }
        public int size()
        {        
            return HashInst2.size();
        }
    }
    
    /**
     * Compares the specified object with this entry for equality.
     * Returns {@code true} if the given object is also a map entry and
     * the two entries represent the same mapping.  More formally, two
     * entries {@code e1} and {@code e2} represent the same mapping
     * if<pre>
     *     (e1.getKey()==null ?
     *      e2.getKey()==null : e1.getKey().equals(e2.getKey()))  &amp;&amp;
     *     (e1.getValue()==null ?
     *      e2.getValue()==null : e1.getValue().equals(e2.getValue()))
     * </pre>
     * This ensures that the {@code equals} method works properly across
     * different implementations of the {@code Map.Entry} interface.
     *
     * @param o object to be compared for equality with this map entry
     * @return {@code true} if the specified object is equal to this map
     *         entry
     */
    public boolean equals(Object o)
    {
        if (o == this)
        {
            return true;
        }
        if (!(o instanceof Map))
        {
            return false;
        }
        Map m = (Map) o;
        if (m.size() != size())
        {
            return false;
        }
        try
        {
            Object[] k = entrySet().toArray();
            for (int i = 0; i<k.length; i++)
            {
                Entry e = (Entry)k[i];
                Object key = e.getKey();
                Object value = e.getValue();
                if (!value.equals(m.get(key)))
                {
                    return false;  
                }
            }
        }
        catch (ClassCastException | NullPointerException e)
        {
            return false;
        }
        return true;
    }
    
    /**
     * Returns the value to which the specified key is mapped,
     * or {@code null} if this map contains no mapping for the key.
     *
     * <p>More formally, if this map contains a mapping from a key
     * {@code k} to a value {@code v} such that
     * {@code Objects.equals(key, k)},
     * then this method returns {@code v}; otherwise
     * it returns {@code null}.  (There can be at most one such mapping.)
     *
     * @param key the key whose associated value is to be returned
     * @return the value to which the specified key is mapped, or
     *         {@code null} if this map contains no mapping for the key
     * @throws NullPointerException if the specified key is null
     */
    public Object get(Object key)
    {
        if(key == null)
        {
            throw new NullPointerException();
        }
        return HashInst2.get(key);
    }
    
    /**
     * Returns the hash code value for this map.  The hash code of a map is
     * defined to be the sum of the hash codes of each entry in the map's
     * {@code entrySet()} view.  This ensures that {@code m1.equals(m2)}
     * implies that {@code m1.hashCode()==m2.hashCode()} for any two maps
     * {@code m1} and {@code m2}, as required by the general contract of
     * {@link Object#hashCode}.
     *
     * @return the hash code value for this map
     * @see Map.Entry#hashCode()
     * @see Object#equals(Object)
     * @see #equals(Object)
     */
    public int hashCode()
    {
        int h = 0;
        Iterator i = entrySet().iterator();
        while (i.hasNext())
        {
            Object obj = i.next();
            h += obj.hashCode();
        }
        return h;
    }
    
    /**
     * Returns {@code true} if this map contains no key-value mappings.
     *
     * @return {@code true} if this map contains no key-value mappings
     */
    public boolean isEmpty()
    {
        return HashInst2.isEmpty();
    }
    
    /**
     * Returns a {@link Set} view of the keys contained in this map.
     * The set is backed by the map, so changes to the map are
     * reflected in the set, and vice-versa.  If the map is modified
     * while an iteration over the set is in progress the result is always 
     * valid, in fact the iterator sees every change made.
     * The set supports element removal,
     * which removes the corresponding mapping from the map, via the
     * {@code Iterator.remove}, {@code Set.remove},
     * {@code removeAll}, {@code retainAll}, and {@code clear}
     * operations.  It does not support the {@code add} or {@code addAll}
     * operations.
     *
     * @return a set view of the keys contained in this map
     */
    public Set keySet()
    {
        return new keySet();
    }
    private class keySet
    extends SetAdapter
    {
    public boolean add(Object e)
    {
        throw new UnsupportedOperationException();
    }
    
    public boolean addAll(Collection c)
    {
        throw new UnsupportedOperationException();
    }
    
    public void clear()
    {
        HashInst2.clear();
    }
    
    public boolean contains(Object o)
    {
        return HashInst2.containsKey(o);
    }

    public boolean isEmpty()
    {
        return HashInst2.isEmpty();
    }
    
    public Iterator iterator()
    {
        return new HashIteratorAdapter(HashInst2, 1); //values = 0, key =1, entry = 2
    }
    
    public boolean remove(Object o)
    {
        if(o ==null)
        {
            throw new NullPointerException();
        }
        if(HashInst2.remove(o) == null)
        {
            return false;
        }
        return true;
    }
    
    public boolean retainAll(Collection c)
    {
        if(c==null)
        {
            throw new NullPointerException();
        }
        boolean r = false;
        Object[] coll= c.toArray();
        Vector temp= new Vector();
        for(int i=0 ; i<coll.length;i++)
        {
            if(contains(coll[i]))
            {
                temp.addElement(new MyEntry(coll[i], HashInst2.get(coll[i])));
                remove(coll[i]);
            }
        }
        if(temp.size() != size())
        {
            r=true;
        }
        clear();
        for(int i=0 ; i<temp.size();i++)
        {
            HashInst2.put(((Map.Entry)temp.elementAt(i)).getKey(), ((Map.Entry)temp.elementAt(i)).getValue());
        }
        return r;
    }

    public int size()
    {        
        return HashInst2.size();
    }
    }
    
    /**
     * Associates the specified value with the specified key in this map.
     * If the map previously contained a mapping for
     * the key, the old value is replaced by the specified value.  (A map
     * {@code m} is said to contain a mapping for a key {@code k} if and only
     * if {@link #containsKey(Object) m.containsKey(k)} would return
     * {@code true}.)
     *
     * @param key key with which the specified value is to be associated
     * @param value value to be associated with the specified key
     * @return the previous value associated with {@code key}, or
     *         {@code null} if there was no mapping for {@code key}.
     *         (A {@code null} return can also indicate that the map
     *         previously associated {@code null} with {@code key},
     *         if the implementation supports {@code null} values.)
     * @throws NullPointerException if the specified key or value is null
     */
    public Object put(Object key, Object value)
    {
        if(key ==null || value == null)
        {
            throw new NullPointerException();
        }
        return HashInst2.put(key, value);
    }
    
    /**
     * Copies all of the mappings from the specified map to this map.
     * The effect of this call is equivalent to that
     * of calling {@link #put(Object,Object) put(k, v)} on this map once
     * for each mapping from key {@code k} to value {@code v} in the
     * specified map.  The behavior of this operation is undefined if the
     * specified map is modified while the operation is in progress.
     *
     * @param m mappings to be stored in this map
     * @throws NullPointerException if the specified map is null, or if the
     *         specified map contains null keys or values
     */
    public void putAll(Map m)
    {
        if(m==null)
        {
            throw new NullPointerException();
        }
        Set k = m.keySet();
        Object[] temp = k.toArray();
        for(int i=0; i < temp.length; i++)
        {
            put(temp[i], m.get(temp[i]));
        }
    }
    
    /**
     * Removes the mapping for a key from this map if it is present.
     * More formally, if this map contains a mapping
     * from key {@code k} to value {@code v} such that
     * {@code Objects.equals(key, k)}, that mapping
     * is removed.  (The map can contain at most one such mapping.)
     *
     * <p>Returns the value to which this map previously associated the key,
     * or {@code null} if the map contained no mapping for the key.
     *
     * <p>The map will not contain a mapping for the specified key once the
     * call returns.
     *
     * @param key key whose mapping is to be removed from the map
     * @return the previous value associated with {@code key}, or
     *         {@code null} if there was no mapping for {@code key}.
     * @throws NullPointerException if the specified key is null
     */
    public Object remove(Object key)
    {
        if(key ==null)
        {
            throw new NullPointerException();
        }
        return HashInst2.remove(key);
    }
    
    /**
     * Returns the number of key-value mappings in this map.  If the
     * map contains more than {@code Integer.MAX_VALUE} elements, returns
     * {@code Integer.MAX_VALUE}.
     *
     * @return the number of key-value mappings in this map
     */
    public int size()
    {
        return HashInst2.size();
    }
    
    /**
     * Returns a {@link Collection} view of the values contained in this map.
     * The collection is backed by the map, so changes to the map are
     * reflected in the collection, and vice-versa.  If the map is
     * modified while an iteration over the collection is in progress the result 
     * is always valid, in fact the iterator sees every change made. The collection
     * supports element removal, which removes the corresponding
     * mapping from the map, via the {@code Iterator.remove},
     * {@code Collection.remove}, {@code removeAll},
     * {@code retainAll} and {@code clear} operations.  It does not
     * support the {@code add} or {@code addAll} operations.
     *
     * @return a collection view of the values contained in this map
     */
    public Collection values()
    {
        return new values();
    }
    private class values
    extends CollectionAdapter
    {
        public boolean add(Object e)
        {
            throw new UnsupportedOperationException();
        }
        
        public boolean addAll(Collection c)
        {
            throw new UnsupportedOperationException();
        }
        public void clear()
        {
            HashInst2.clear();
        }
    
        public boolean contains(Object o)
        {
            return HashInst2.contains(o);
        }

        public boolean isEmpty()
        {
            return HashInst2.isEmpty();
        }
        
        public Iterator iterator()
        {
            return new HashIteratorAdapter(HashInst2, 0); //values = 0, key =1, entry = 2
        }

        public boolean remove(Object o)
        {
            if(o ==null)
            {
                throw new NullPointerException();
            }
            Object obj = getFirstKey(o);
            if(obj == null)
            {
                return false;
            }
            HashInst2.remove(getFirstKey(o));
            return true;
        }

        public boolean retainAll(Collection c)
        {
            if(c==null)
            {
                throw new NullPointerException();
            }
            boolean r=false;
            Object[] coll= c.toArray();
            Vector temp= new Vector();
            for(int i=0 ; i<coll.length;i++)
            {
                Object key = getFirstKey(coll[i]);
                if(key != null)
                {
                    temp.addElement(new MyEntry(key, coll[i]));
                    remove(coll[i]);
                }
            }
            if(temp.size() != size())
            {
                r=true;
            }
            clear();
            for(int i=0 ; i<temp.size();i++)
            {
                HashInst2.put(((Map.Entry)temp.elementAt(i)).getKey(), ((Map.Entry)temp.elementAt(i)).getValue());
            }
            return r;
        }

        public int size()
        {        
            return HashInst2.size();
        }

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
    private Object getFirstKey(Object value)
    {
        Iterator it = keySet().iterator();
        while(it.hasNext())
        {
            Object obj = it.next();
            if(get(obj).equals(value))
            {
                return obj;
            }
        }
        return null;
    }
}