import org.junit.Test;
import org.junit.Before;
import org.junit.After;
import static org.junit.Assert.assertEquals;
import adapter.*;

public class MyEntryTest
{
    private MyEntry instance;

    @Before
    public void before()
    {
        instance = new MyEntry(2,"ciao");
    }

    //GETKEY

    @Test
    public void testGetKey()
    {
        assertEquals(2,instance.getKey());
    }

    //GETVALUE

    @Test
    public void testGetValue()
    {
        assertEquals("ciao",instance.getValue());
    }

    //SETVALUE

    @Test
    public void testSetValue()
    {
        assertEquals("ciao",instance.getValue());
        assertEquals("ciao",instance.setValue("ok"));
        assertEquals("ok",instance.getValue());
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
        MyEntry tmp = new MyEntry(2,"pluto");
        assertEquals(false,instance.equals(tmp));
    }

    @Test
    public void testEquals4()
    {
        MyEntry tmp = new MyEntry(3,"ciao");
        assertEquals(false,instance.equals(tmp));
    }

    @Test
    public void testEquals5()
    {
        MyEntry tmp = new MyEntry(2,"ciao");
        assertEquals(true,instance.equals(tmp));
    }

    @Test
    public void testEquals6()
    {
        assertEquals(false,instance.equals(null));
    }

    //HASHCODE
    @Test
    public void testHashCode()
    {
        int h = ((Integer)2).hashCode() ^ "ciao".hashCode();
        assertEquals(h,instance.hashCode());
    }
}