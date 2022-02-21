import org.junit.Test;
import org.junit.Before;
import org.junit.After;
import static org.junit.Assert.assertEquals;
import adapter.*;

public class CollectionTest
{
    private CollectionAdapter instance;

    @Before
    public void before()
    {
        instance = new CollectionAdapter();
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
        SetAdapter tmp = new SetAdapter();
        assertEquals(false,instance.equals(tmp));
    }

    @Test
    public void testEquals3()
    {
        CollectionAdapter tmp = new CollectionAdapter();
        tmp.add(1);
        assertEquals(false,instance.equals(tmp));
    }

    @Test
    public void testEquals4()
    {
        CollectionAdapter tmp = new CollectionAdapter();
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

}