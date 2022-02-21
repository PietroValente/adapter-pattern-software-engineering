import org.junit.Test;
import org.junit.Before;
import org.junit.After;
import static org.junit.Assert.assertEquals;
import adapter.*;

public class SetTest
{
    private SetAdapter instance;

    @Before
    public void before()
    {
        instance = new SetAdapter();
    }

    //CONSTRUCTOR

    @Test(expected = IllegalArgumentException.class)
    public void testConstructer()
    {
        instance = new SetAdapter(-1);
    }
    
    //ADD

    @Test(expected = NullPointerException.class)
    public void testAdd1()
    {
        instance.add(null);
    }

    @Test
    public void testAdd2()
    {
        assertEquals(true,instance.add(2));
    }

    @Test
    public void testAdd3()
    {
        instance.add(2);
        assertEquals(false,instance.add(2));
    }

    @Test
    public void testAdd4()
    {
        instance.add(2);
        assertEquals(false,instance.isEmpty());
    }

    @Test
    public void testAdd5()
    {
        instance.add(2);
        assertEquals(true,instance.contains(2));
    }

    @Test
    public void testAdd6()
    {
        assertEquals(0,instance.size());
        instance.add(2);
        assertEquals(1,instance.size());
    }

    //ADDALL

    @Test(expected = NullPointerException.class)
    public void testAddAll1()
    {
        instance.addAll(null);
    }

    @Test
    public void testAddAll2()
    {
        SetAdapter instance2 = new SetAdapter();
        for(int i = 0; i<10 ; i++)
        {
            instance.add(i);
            instance2.add(i);
        }
        assertEquals(false,instance.addAll(instance2));
    }

    @Test
    public void testAddAll3()
    {
        SetAdapter instance2 = new SetAdapter();
        for(int i = 0; i<10 ; i++)
        {
            instance.add(i);
            instance2.add(i);
        }
        instance.remove(2);
        assertEquals(false,instance.contains(2));
        assertEquals(true,instance.addAll(instance2));
        assertEquals(true,instance.contains(2));
    }

    @Test
    public void testAddAll4()
    {
        SetAdapter instance2 = new SetAdapter();
        for(int i = 0; i<10 ; i++)
        {
            instance2.add(i);
        }
        instance.addAll(instance2);
        assertEquals(10,instance.size());
    }

    @Test
    public void testAddAll5()
    {
        CollectionAdapter instance2 = new CollectionAdapter();
        for(int i = 0; i<10 ; i++)
        {
            instance2.add(i);
        }
        instance2.add(2);
        instance2.add(3);
        instance.addAll(instance2);
        assertEquals(10,instance.size());
    }

    @Test
    public void testAddAll6()
    {
        CollectionAdapter instance2 = new CollectionAdapter();
        for(int i = 0; i<10 ; i++)
        {
            instance2.add(i);
        }
        instance2.remove(9);
        instance2.remove(8);
        instance2.remove(7);
        instance2.add(2);
        instance2.add(3);
        instance.addAll(instance2);
        assertEquals(instance.size(), 7);
        Object[] c1 = instance.toArray();
        for(int i = 0; i<instance.size(); i++)
        {
            assertEquals(c1[i], i);
        }
    }

    //CONTAINS

    @Test(expected = NullPointerException.class)
    public void testContains()
    {
        instance.contains(null);
    }

    //CONTAINSALL

    @Test(expected = NullPointerException.class)
    public void testContainsAll1()
    {
        instance.containsAll(null);
    }

    @Test
    public void testContainsAll2()
    {
        SetAdapter instance2 = new SetAdapter();
        instance.add(1);
        instance2.add(1);
        instance2.add(2);
        assertEquals(false,instance.containsAll(instance2));
    }

    @Test
    public void testContainsAll3()
    {
        SetAdapter instance2 = new SetAdapter();
        instance.add(1);
        instance.add(2);
        instance.add(3);
        instance2.add(1);
        instance2.add(2);
        assertEquals(true,instance.containsAll(instance2));
    }

    @Test
    public void testContainsAll4()
    {
        CollectionAdapter instance2 = new CollectionAdapter();
        for(int i = 0; i<10 ; i++)
        {
            instance2.add(i);
            instance.add(i);
        }
        instance2.remove(9);
        instance2.remove(8);
        instance2.remove(7);
        instance2.add(2);
        instance2.add(3);
        assertEquals(false,instance.containsAll(instance2));
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
        SetAdapter tmp = new SetAdapter();
        tmp.add(1);
        assertEquals(false,instance.equals(tmp));
    }

    @Test
    public void testEquals4()
    {
        SetAdapter tmp = new SetAdapter();
        tmp.add(1);
        tmp.add(4);
        instance.add(1);
        instance.add(4);
        assertEquals(true,instance.equals(tmp));
    }

    @Test
    public void testEquals5()
    {
        assertEquals(false,instance.equals(null));
    }

    //HASHCODE

    @Test
    public void testHashCode()
    {
        int h = "ciao".hashCode() + "pippo".hashCode() + "pluto".hashCode();
        instance.add("pippo");
        instance.add("ciao");
        instance.add("pluto");
        assertEquals(h,instance.hashCode());
    }

    //REMOVE

    @Test(expected = NullPointerException.class)
    public void testRemove1()
    {
        instance.remove(null);
    }

    @Test
    public void testRemove2()
    {
        assertEquals(false,instance.remove(10));
    }

    @Test
    public void testRemove3()
    {
        instance.add(5);
        assertEquals(true,instance.remove(5));
    }

    @Test
    public void testRemove4()
    {
        instance.add(2);
        instance.add(3);
        instance.add(5);
        instance.remove(3);
        assertEquals(2,instance.size());
        assertEquals(false,instance.contains(3));
    }

    //REMOVEALL

    @Test(expected = NullPointerException.class)
    public void testRemoveAll1()
    {
        instance.addAll(null);
    }

    @Test
    public void testRemoveAll2()
    {
        SetAdapter instance2 = new SetAdapter();
        for(int i = 0; i<10 ; i++)
        {
            instance.add(i);
            instance2.add(i);
        }
        assertEquals(true,instance.removeAll(instance2));
        assertEquals(true,instance.isEmpty());
    }

    @Test
    public void testRemoveAll3()
    {
        SetAdapter instance2 = new SetAdapter();
        for(int i = 0; i<10 ; i++)
        {
            instance2.add(i);
            instance.add(i);
        }
        instance2.remove(5);
        instance2.remove(5);
        instance2.remove(6);
        instance2.remove(7);
        instance2.add(20);
        instance.removeAll(instance2);
        assertEquals(3,instance.size());
    }

    @Test
    public void testRemoveAll4()
    {
        CollectionAdapter instance2 = new CollectionAdapter();
        for(int i = 0; i<10 ; i++)
        {
            instance2.add(i);
            instance.add(i);
        }
        instance2.add(2);
        instance2.add(3);
        instance2.remove(5);
        instance2.remove(5);
        instance2.remove(6);
        instance2.remove(7);
        instance2.add(20);
        instance.removeAll(instance2);
        assertEquals(3,instance.size());
    }

    //RETAINALL

    @Test(expected = NullPointerException.class)
    public void testRetainAll1()
    {
        instance.retainAll(null);
    }

    @Test
    public void testRetainAll2()
    {
        SetAdapter instance2 = new SetAdapter();
        for(int i = 0; i<10 ; i++)
        {
            instance.add(i);
            instance2.add(i);
        }
        assertEquals(false,instance.retainAll(instance2));
    }

    @Test
    public void testRetainAll3()
    {
        SetAdapter instance2 = new SetAdapter();
        for(int i = 0; i<10 ; i++)
        {
            instance2.add(i);
            instance.add(i);
        }
        instance2.remove(5);
        instance2.remove(5);
        instance2.remove(6);
        instance2.remove(7);
        instance2.add(20);
        instance.retainAll(instance2);
        assertEquals(7,instance.size());
    }

    @Test
    public void testRetainAll4()
    {
        CollectionAdapter instance2 = new CollectionAdapter();
        for(int i = 0; i<10 ; i++)
        {
            instance2.add(i);
            instance.add(i);
        }
        instance2.add(2);
        instance2.add(3);
        instance2.remove(5);
        instance2.remove(5);
        instance2.remove(6);
        instance2.remove(7);
        instance2.add(20);
        instance.retainAll(instance2);
        assertEquals(7,instance.size());
    }

    //TOARRAY

    @Test
    public void testToArray()
    {
        Object[] tmp = new Object[10];
        for(int i=0; i<10;i++)
        {
            instance.add(i);
            tmp[i] = i;
        }
        Object[] i = instance.toArray();
        boolean r = (i.length == 10);
        for(int k=0; k<10;k++)
        {
            r = r && tmp[k].equals(i[k]);
        }
        assertEquals(true,r);
    }

    //TOARRAYOBJECTS

    @Test
    public void testToArrayObjects()
    {
        Object[] tmp = new Object[10];
        for(int i=0; i<10;i++)
        {
            instance.add(i);
            tmp[i] = i;
        }
        Object[] i = instance.toArray(new Object[20]);
        boolean r = (i.length == 20);
        for(int k=0; k<10;k++)
        {
            r = r && tmp[k].equals(i[k]);
        }
        assertEquals(true,r);
    }

}