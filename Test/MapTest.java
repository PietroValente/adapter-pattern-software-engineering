import org.junit.Test;
import org.junit.Before;
import org.junit.After;
import static org.junit.Assert.assertEquals;
import adapter.*;

public class MapTest
{
    private MapAdapter instance;

    @Before
    public void before()
    {
        instance = new MapAdapter();
    }

    //CONTAINSKEY

    @Test(expected = NullPointerException.class)
    public void testContainsKey()
    {
        instance.containsKey(null);
    }

    //CONTAINSVALUE

    @Test(expected = NullPointerException.class)
    public void testContainsValue()
    {
        instance.containsValue(null);
    }

    //ENTRYSET
    @Test
    public void testEntrySet()
    {
        instance.put(1,6);
        instance.put(2,7);
        instance.put(3,8);
        instance.put(4,9);
        instance.put(5,10);
        Set tmp = instance.entrySet();
        Object[] o = tmp.toArray();
        for(int i = 0; i<o.length; i++)
        {
            assertEquals(o[i],new MyEntry(i+1, i+6));
        }
    }

    //EQUALS

    @Test
    public void testEquals1()
    {
        assertEquals(true,instance.equals(instance));
    }

    @Test
    public void testEquals2()
    {
        CollectionAdapter tmp = new CollectionAdapter();
        assertEquals(false,instance.equals(tmp));
    }

    @Test
    public void testEquals3()
    {
        MapAdapter tmp = new MapAdapter();
        tmp.put(1,1);
        assertEquals(false,instance.equals(tmp));
    }

    @Test
    public void testEquals4()
    {
        MapAdapter tmp = new MapAdapter();
        tmp.put(1,2);
        tmp.put(4,5);
        instance.put(1,2);
        instance.put(4,5);
        assertEquals(true,instance.equals(tmp));
    }

    @Test
    public void testEquals5()
    {
        MapAdapter tmp = new MapAdapter();
        tmp.put(1,3);
        tmp.put(4,8);
        instance.put(1,3);
        instance.put(4,9);
        assertEquals(false,instance.equals(tmp));
    }

    @Test
    public void testEquals6()
    {
        assertEquals(false,instance.equals(null));
    }

    @Test
    public void testEquals7()
    {
        MapAdapter tmp = new MapAdapter();
        tmp.put(1,3);
        tmp.put(8,4);
        instance.put(1,3);
        instance.put(9,4);
        assertEquals(false,instance.equals(tmp));
    }

    //GET

    @Test(expected = NullPointerException.class)
    public void testGet()
    {
        instance.get(null);
    }

    //HASHCODE

    @Test
    public void testHashCode()
    {
        MyEntry A = new MyEntry(1,"ciao");
        MyEntry B = new MyEntry(2,"pippo");
        MyEntry C = new MyEntry(3,"pluto");
        int h = A.hashCode() + B.hashCode() + C.hashCode();
        instance.put(1,"ciao");
        instance.put(2,"pippo");
        instance.put(3,"pluto");
        assertEquals(h,instance.hashCode());
    }

    //KEYSET
    @Test
    public void testKeySet()
    {
        instance.put(1,6);
        instance.put(2,7);
        instance.put(3,8);
        instance.put(4,9);
        instance.put(5,10);
        Set tmp = instance.keySet();
        Object[] o = tmp.toArray();
        for(int i = 0; i<o.length; i++)
        {
            assertEquals(o[i],i+1);
        }
    }

    //PUT

    @Test(expected = NullPointerException.class)
    public void testPut1()
    {
        instance.put(null,1);
    }

    @Test(expected = NullPointerException.class)
    public void testPut2()
    {
        instance.put(1,null);
    }

    //REMOVE

    @Test(expected = NullPointerException.class)
    public void testRemove()
    {
        instance.remove(null);
    }

    //VALUES
    @Test
    public void testValues()
    {
        instance.put(1,6);
        instance.put(2,7);
        instance.put(3,8);
        instance.put(4,9);
        instance.put(5,10);
        instance.put(6,6);
        Collection tmp = instance.values();
        Object[] o = tmp.toArray();
        boolean r = true;
        for(int i = 0; i<o.length-1; i++)
        {
            r = r && (o[i].equals(i+6));
        }
        r = r && (o[o.length-1].equals(6));
        assertEquals(true,r);
    }
}