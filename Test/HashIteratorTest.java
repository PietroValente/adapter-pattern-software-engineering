import org.junit.Test;
import org.junit.Before;
import org.junit.After;
import static org.junit.Assert.assertEquals;

import java.util.Hashtable;

import adapter.*;

public class HashIteratorTest
{
    private HashIteratorAdapter instance;
    private Hashtable h;

    @Before
    public void before()
    {
        h = new Hashtable();
        h.put(1,"a");
        h.put(2,"b");
        h.put(3,"c");
        h.put(4,"d");
        h.put(5,"e");
    }

    //NEXT

    @Test
    public void testNext1()
    {
        instance = new HashIteratorAdapter(h,1);
        instance.next();
        instance.next();
        instance.next();
        assertEquals(4,instance.next());
    }

    @Test
    public void testNext2()
    {
        instance = new HashIteratorAdapter(h,0);
        instance.next();
        instance.next();
        instance.next();
        assertEquals("d",instance.next());
    }

    @Test
    public void testNext3()
    {
        instance = new HashIteratorAdapter(h,2);
        instance.next();
        instance.next();
        instance.next();
        Map.Entry tmp = (Map.Entry)instance.next();
        boolean r = (tmp.getKey().equals(4)) && (tmp.getValue().equals("d"));
        assertEquals(true, r);
    }

    //REMOVE

    @Test(expected = IllegalStateException.class)
    public void testRemove1()
    {
        instance = new HashIteratorAdapter(h,1);
        instance.next();
        instance.next();
        instance.remove();
        instance.remove();
    }

    @Test
    public void testRemove2()
    {
        instance = new HashIteratorAdapter(h,1);
        instance.next();
        instance.remove();
        assertEquals(false, h.containsKey(1));
    }

    @Test
    public void testRemove3()
    {
        instance = new HashIteratorAdapter(h,0);
        instance.next();
        instance.remove();
        assertEquals(false, h.containsKey(1));
    }

    @Test
    public void testRemove4()
    {
        instance = new HashIteratorAdapter(h,2);
        instance.next();
        instance.remove();
        assertEquals(false, h.containsKey(1));
    }
}