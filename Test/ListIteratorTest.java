import org.junit.Test;
import org.junit.Before;
import org.junit.After;
import static org.junit.Assert.assertEquals;

import java.util.NoSuchElementException;
import java.util.Vector;

import adapter.*;

public class ListIteratorTest
{
    private ListIteratorAdapter instance;
    private Vector v;

    @Before
    public void before()
    {
        v = new Vector();
        for(int i = 0; i<10; i++)
        {
            v.addElement(i);
        }
    }

    //CONSTRUCTOR

    @Test(expected = IndexOutOfBoundsException.class)
    public void testConstructor1()
    {
        instance = new ListIteratorAdapter(v, -1, 2);
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void testConstructor2()
    {
        instance = new ListIteratorAdapter(v, 0, -2);
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void testConstructor3()
    {
        instance = new ListIteratorAdapter(v, 0, 20);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testConstructor4()
    {
        instance = new ListIteratorAdapter(null, 0, 20);
    }

    //HASNEXT

    @Test
    public void testHasNext1()
    {
        instance = new ListIteratorAdapter(v, 0, v.size()-1);
        assertEquals(true, instance.hasNext());
    }

    @Test
    public void testHasNext2()
    {
        instance = new ListIteratorAdapter(v, v.size()-1,v.size()-1);
        instance.next();
        assertEquals(false, instance.hasNext());
    }

    //NEXT

    @Test(expected = NoSuchElementException.class)
    public void testNext1()
    {
        instance = new ListIteratorAdapter(v, v.size()-1,v.size()-1);
        instance.next();
        instance.next();
    }

    @Test
    public void testNext2()
    {
        instance = new ListIteratorAdapter(v, 0,v.size()-1);
        for(int i = 0; i<10; i++)
        {
            assertEquals((Integer)i, (Integer)instance.next());

        }
    }

    //HASPREVOIUS

    @Test
    public void testHasPrevoius1()
    {
        instance = new ListIteratorAdapter(v, 0, v.size()-1);
        assertEquals(false, instance.hasPrevious());
    }

    @Test
    public void testHasPrevious2()
    {
        instance = new ListIteratorAdapter(v, v.size()-1,v.size()-1);
        instance.next();
        assertEquals(true, instance.hasPrevious());
    }

    //PREVIOUS

    @Test(expected = NoSuchElementException.class)
    public void testPrevoius1()
    {
        instance = new ListIteratorAdapter(v, v.size()-1,v.size()-1);
        instance.previous();
    }

    @Test
    public void testPrevoius2()
    {
        instance = new ListIteratorAdapter(v, 0,v.size()-1);
        boolean r = true;
        for(int i = 0; i<10; i++)
        {
            instance.next();
        }
        for(int i = 9; i>-1; i--)
        {
            r = r && ((Integer)instance.previous() == i);
        }
        assertEquals(true, r);
    }

    //REMOVE

    @Test(expected = IllegalStateException.class)
    public void testRemove1()
    {
        instance = new ListIteratorAdapter(v, 0,v.size()-1);
        instance.next();
        instance.next();
        instance.remove();
        instance.remove();
    }

    @Test
    public void testRemove2()
    {
        instance = new ListIteratorAdapter(v, 0,v.size()-1);
        instance.next();
        instance.remove();
        assertEquals(false, v.contains(0));
    }

    @Test
    public void testRemove3()
    {
        instance = new ListIteratorAdapter(v, 0,v.size()-1);
        instance.next();
        instance.next();
        instance.previous();
        instance.remove();
        assertEquals(false, v.contains(1));
    }

    //SET

    @Test(expected = IllegalStateException.class)
    public void testSet1()
    {
        instance = new ListIteratorAdapter(v, 0,v.size()-1);
        instance.set(2);
    }

    @Test
    public void testSet2()
    {
        instance = new ListIteratorAdapter(v, 0,v.size()-1);
        instance.next();
        instance.set(20);
        assertEquals(false, v.contains(0));
    }

    @Test
    public void testSet3()
    {
        instance = new ListIteratorAdapter(v, 0,v.size()-1);
        instance.next();
        instance.set(20);
        assertEquals(true, v.contains(20));
    }

    @Test
    public void testSet4()
    {
        instance = new ListIteratorAdapter(v, 0,v.size()-1);
        instance.next();
        instance.next();
        instance.previous();
        instance.set(20);
        assertEquals(true, v.contains(20));
    }

    @Test
    public void testSet5()
    {
        instance = new ListIteratorAdapter(v, 0,v.size()-1);
        instance.next();
        instance.next();
        instance.previous();
        instance.set(20);
        assertEquals(false, v.contains(1));
    }

    //ADD

    @Test
    public void testAdd1()
    {
        instance = new ListIteratorAdapter(v, 0,v.size()-1);
        instance.next();
        instance.next();
        instance.add(20);
        assertEquals(20,instance.previous());
    }

    @Test
    public void testAdd2()
    {
        instance = new ListIteratorAdapter(v, 0,v.size()-1);
        instance.next();
        instance.next();
        int r = instance.previousIndex() + instance.nextIndex();
        instance.add(20);
        int b = instance.previousIndex() + instance.nextIndex();
        assertEquals(r+2,b);
    }

    @Test(expected = IllegalStateException.class)
    public void testAdd3()
    {
        instance = new ListIteratorAdapter(v, 0,v.size()-1);
        instance.next();
        instance.next();
        instance.add(20);
        instance.remove();
    }

    @Test(expected = IllegalStateException.class)
    public void testAdd4()
    {
        instance = new ListIteratorAdapter(v, 0,v.size()-1);
        instance.next();
        instance.next();
        instance.add(20);
        instance.set(2);
    }
}