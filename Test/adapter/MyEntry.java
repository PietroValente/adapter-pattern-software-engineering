package adapter;

/**
 * <p>This class is a really minimalist implementation of {@code Map.Entry} interface.
 * 
 * <p>This class is a member of the <i>adapter package</i>.
 * 
 * @author  Pietro Valente
 */

public class MyEntry
implements Map.Entry
{
    private final Object key;
    private Object value;

    /**
     * Constructs a new MyEntry instance.
     */
    public MyEntry(Object key, Object value)
    {
        this.key = key;
        this.value = value;
    }

    /**
     * Returns the key corresponding to this entry.
     *
     * @return the key corresponding to this entry
     */   
    public Object getKey()
    {
        return key;
    }

    /**
     * Returns the value corresponding to this entry.  If the mapping
     * has been removed from the backing map (by the iterator's
     * {@code remove} operation), the results of this call are undefined.
     *
     * @return the value corresponding to this entry
     */
    public Object getValue() {
        return value;
    }

    /**
     * Replaces the value corresponding to this entry with the specified
     * value. The
     * behavior of this call is undefined if the mapping has already been
     * removed from the map (by the iterator's {@code remove} operation).
     *
     * @param value new value to be stored in this entry
     * @return old value corresponding to the entry
     */
    public Object setValue(Object value) {
        Object old = this.value;
        this.value = value;
        return old;
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
     *
     * @param o object to be compared for equality with this map entry
     * @return {@code true} if the specified object is equal to this map
     *         entry
     */
    public boolean equals(Object o)
    {
        if(o == null)
        {
            return false;
        }
        if(!(o instanceof Map.Entry))
        {
            return false;
        }
        MyEntry tmp = (MyEntry) o;
        return (tmp.key.equals(key) && tmp.value.equals(value));
    }

    /**
     * Returns the hash code value for this map entry.  The hash code
     * of a map entry {@code e} is defined to be: <pre>
     *     (e.getKey()==null   ? 0 : e.getKey().hashCode()) ^
     *     (e.getValue()==null ? 0 : e.getValue().hashCode())
     * </pre>
     * This ensures that {@code e1.equals(e2)} implies that
     * {@code e1.hashCode()==e2.hashCode()} for any two Entries
     * {@code e1} and {@code e2}, as required by the general
     * contract of {@code Object.hashCode}.
     *
     * @return the hash code value for this map entry
     * @see Object#hashCode()
     * @see Object#equals(Object)
     * @see #equals(Object)
     */
    public int hashCode()
    {
        return getKey().hashCode() ^ getValue().hashCode();
    }
}