import org.junit.Test;
import org.junit.Before;
import org.junit.After;
import static org.junit.Assert.assertEquals;

import adapter.*;

public class ListTest
{
    private ListAdapter instance;

    @Before
    public void before()
    {
        instance = new ListAdapter();
    }

    //ADDINDEX

    @Test(expected = NullPointerException.class)
    public void testAddIndex1()
    {
        instance.add(2,null);
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void testAddIndex2()
    {
        instance.add(-1,0);
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void testAddIndex3()
    {
        instance.add(1,0);
    }

    @Test
    public void testAddIndex4()
    {
        instance.add(0,0);
        assertEquals(1,instance.size());
    }

    @Test
    public void testAddIndex5()
    {
        instance.add(1);
        instance.add(2);
        instance.add(3);
        instance.add(4);
        instance.add(2,20);
        assertEquals(20,instance.get(2));
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
        assertEquals(true,instance.add(2));
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
        instance.add(2);
        instance.add(2);
        assertEquals(2,instance.size());
    }

    //ADDALLINDEX

    @Test(expected = NullPointerException.class)
    public void testAddAllIndex1()
    {
        instance.add(2,null);
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void testAddAllIndex2()
    {
        ListAdapter instance2 = new ListAdapter();
        instance.add(2,instance2);
    }

    @Test
    public void testAddAllIndex3()
    {
        ListAdapter instance2 = new ListAdapter();
        for(int i = 0; i<10 ; i++)
        {
            instance.add(i);
            instance2.add(i);
        }
        assertEquals(true,instance.addAll(5,instance2));
        assertEquals(20,instance.size());
    }

    @Test
    public void testAddAllIndex4()
    {
        ListAdapter instance2 = new ListAdapter();
        for(int i = 0; i<10 ; i++)
        {
            instance.add(i);
            instance2.add(i);
        }
        instance.addAll(10,instance2);
        Object[] pippo = instance.toArray();
        for(int i = 0; i<10 ; i++)
        {
            assertEquals(i,pippo[i]);
        }
        for(int i = 10; i<20 ; i++)
        {
            assertEquals(i-10,pippo[i]);
        }
    }

    @Test
    public void testAddAllIndex5()
    {
        CollectionAdapter instance2 = new CollectionAdapter();
        for(int i = 0; i<10 ; i++)
        {
            instance2.add(i);
        }
        instance2.add(2);
        instance2.add(3);
        instance.addAll(0,instance2);
        assertEquals(12,instance.size());
    }

    @Test
    public void testAddAllIndex6()
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
        instance.addAll(0,instance2);
        assertEquals(19,instance.size());
        Object[] c1 = instance.toArray();
        for(int i = 0; i<7; i++)
        {
            assertEquals(i,c1[i]);
        }
        assertEquals(2,c1[7]);
        assertEquals(3,c1[8]);
        for(int i = 9; i<19; i++)
        {
            assertEquals(i-9,c1[i]);
        }
    }

    @Test
    public void testAddAllIndex7()
    {
        instance.add(0);
        instance.add(1);
        CollectionAdapter instance2 = new CollectionAdapter();
        assertEquals(false,instance.addAll(1,instance2));
    }

    //ADDALL

    @Test(expected = NullPointerException.class)
    public void testAddAll1()
    {
        instance.add(null);
    }

    @Test
    public void testAddAll2()
    {
        ListAdapter instance2 = new ListAdapter();
        for(int i = 0; i<10 ; i++)
        {
            instance.add(i);
            instance2.add(i);
        }
        assertEquals(true,instance.addAll(instance2));
        assertEquals(20,instance.size());
    }

    @Test
    public void testAddAll3()
    {
        ListAdapter instance2 = new ListAdapter();
        for(int i = 0; i<10 ; i++)
        {
            instance.add(i);
            instance2.add(i);
        }
        instance.addAll(instance2);
        Object[] pippo = instance.toArray();
        for(int i = 0; i<10 ; i++)
        {
            assertEquals(i,pippo[i]);
        }
        for(int i = 10; i<20 ; i++)
        {
            assertEquals(i-10,pippo[i]);
        }
    }

    @Test
    public void testAddAll4()
    {
        CollectionAdapter instance2 = new CollectionAdapter();
        for(int i = 0; i<10 ; i++)
        {
            instance2.add(i);
        }
        instance2.add(2);
        instance2.add(3);
        instance.addAll(instance2);
        assertEquals(12,instance.size());
    }

    @Test
    public void testAddAll5()
    {
        instance.add(0);
        instance.add(1);
        CollectionAdapter instance2 = new CollectionAdapter();
        assertEquals(false,instance.addAll(instance2));
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
        ListAdapter instance2 = new ListAdapter();
        instance.add(1);
        instance2.add(1);
        instance2.add(2);
        assertEquals(false,instance.containsAll(instance2));
    }

    @Test
    public void testContainsAll3()
    {
        ListAdapter instance2 = new ListAdapter();
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
        instance.add(2);
        instance.add(3);
        instance2.remove(9);
        instance2.remove(8);
        instance2.remove(7);
        instance2.add(2);
        instance2.add(3);
        assertEquals(true,instance.containsAll(instance2));
    }

    @Test
    public void testContainsAll5()
    {
        for(int i = 0; i<10 ; i++)
        {
            instance.add(i);
        }
        assertEquals(true,instance.containsAll(instance));
    }

    @Test
    public void testContainsAll6()
    {
        CollectionAdapter instance2 = new CollectionAdapter();

        instance.add(1);
        instance.add(2);
        instance.add(3);
        instance2.add(2);
        instance2.add(3);
        instance2.add(2);
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
        ListAdapter tmp = new ListAdapter();
        tmp.add(1);
        assertEquals(false,instance.equals(tmp));
    }

    @Test
    public void testEquals4()
    {
        ListAdapter tmp = new ListAdapter();
        tmp.add(1);
        tmp.add(4);
        instance.add(4);
        instance.add(1);
        assertEquals(false,instance.equals(tmp));
    }

    @Test
    public void testEquals5()
    {
        ListAdapter tmp = new ListAdapter();
        tmp.add(1);
        tmp.add(4);
        tmp.add(1);
        instance.add(1);
        instance.add(4);
        instance.add(1);
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
        int h = 31 * "pluto".hashCode() + "ciao".hashCode();
        instance.add("pluto");
        instance.add("ciao");
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
        assertEquals(false,instance.remove("A"));
    }

    @Test
    public void testRemove3()
    {
        instance.add("B");
        assertEquals(true,instance.remove("B"));
    }

    @Test
    public void testRemove4()
    {
        instance.add("A");
        instance.remove("A");
        assertEquals(true,instance.isEmpty());
    }

    @Test
    public void testRemove5()
    {
        instance.add("A");
        instance.add("B");
        instance.add("C");
        instance.remove("B");
        assertEquals(false,instance.contains("B"));
    }

    @Test
    public void testRemove6()
    {
        instance.add("A");
        instance.add("B");
        instance.add("C");
        instance.remove("B");
        assertEquals(2,instance.size());
    }

    //REMOVEINDEX

    @Test(expected = IndexOutOfBoundsException.class)
    public void testRemoveIndex1()
    {
        instance.remove(-1);
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void testRemoveIndex2()
    {
        instance.remove(0);
    }

    @Test
    public void testRemoveIndex3()
    {
        instance.add(4);
        assertEquals(4,instance.remove(0));
        assertEquals(false,instance.contains(4));
        assertEquals(true,instance.isEmpty());
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
        ListAdapter instance2 = new ListAdapter();
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
        ListAdapter instance2 = new ListAdapter();
        for(int i = 0; i<10 ; i++)
        {
            instance.add(i);
            instance2.add(i);
        }
        instance.removeAll(instance2);
        assertEquals(true,instance.isEmpty());
    }

    @Test
    public void testRemoveAll4()
    {
        ListAdapter instance2 = new ListAdapter();
        for(int i = 0; i<10 ; i++)
        {
            instance2.add(i);
            instance.add(i);
        }
        instance2.remove(5);
        instance2.remove(6);
        instance2.remove(7);
        instance2.add(20);
        instance.add(5);
        instance.removeAll(instance2);
        assertEquals(4,instance.size());
    }

    @Test
    public void testRemoveAll5()
    {
        CollectionAdapter instance2 = new CollectionAdapter();
        instance.add(1);
        instance.add(2);
        instance.removeAll(instance2);
        assertEquals(false,instance.removeAll(instance2));
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
        ListAdapter instance2 = new ListAdapter();
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
        ListAdapter instance2 = new ListAdapter();
        instance.add(1);
        assertEquals(true,instance.retainAll(instance2));
        assertEquals(true,instance.isEmpty());
    }

    @Test
    public void testRetainAll4()
    {
        ListAdapter instance2 = new ListAdapter();
        for(int i = 0; i<10 ; i++)
        {
            instance.add(i);
            instance2.add(i);
        }
        instance.add(2);
        instance.add(3);
        instance2.add(2);
        instance.retainAll(instance2);
        assertEquals(11,instance.size());
    }

    //SET

    @Test(expected = NullPointerException.class)
    public void testSet1()
    {
        instance.set(0,null);
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void testSet2()
    {
        instance.set(-1,12);
    }

    //SUBLIST

    @Test(expected = IndexOutOfBoundsException.class)
    public void testSubList1()
    {
        instance.subList(0, -1);
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void testSubList2()
    {
        instance.subList(1, 0);
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void testSubList3()
    {
        instance.subList(-1, 0);
    }

    @Test
    public void testSubList4()
    {
        for(int i = 0; i<10 ; i++)
        {
            instance.add(i);
        }
        List sub = instance.subList(2,6);
        assertEquals(5,sub.size());
        assertEquals(10,instance.size());
        sub.add(2, 10);
        assertEquals(6,sub.size());
        assertEquals(11,instance.size());
        assertEquals(10,instance.get(4));
        assertEquals(10,sub.get(2));
    }

    @Test
    public void testSubList5()
    {
        List sub = instance.subList(0,0);
        sub.add(0, 10);
        assertEquals(10,instance.get(0));
        assertEquals(10,sub.get(0));
    }

    @Test
    public void testSubList6()
    {
        for(int i = 0; i<10 ; i++)
        {
            instance.add(i);
        }
        List sub = instance.subList(2,6);
        assertEquals(5,sub.size());
        assertEquals(10,instance.size());
        sub.add(10);
        assertEquals(6,sub.size());
        assertEquals(11,instance.size());
        assertEquals(10,instance.get(7));
        assertEquals(10,sub.get(5));
    }

    @Test
    public void testSubList7()
    {
        for(int i = 0; i<10 ; i++)
        {
            instance.add(i);
        }
        List sub = instance.subList(2,6);
        sub.clear();
        assertEquals(true,sub.isEmpty());
        assertEquals(5,instance.size());
    }

    @Test
    public void testSubList8()
    {
        CollectionAdapter instance2 = new CollectionAdapter();
        for(int i = 0; i<10 ; i++)
        {
            instance2.add(i);
            instance.add(i);
        }
        instance2.add(2);
        instance2.add(3);
        List sub = instance.subList(2,6);
        sub.addAll(instance2);
        assertEquals(22,instance.size());
        assertEquals(17,sub.size());
        assertEquals(0,sub.get(5));
        assertEquals(0,instance.get(7));
    }

    @Test
    public void testSubList9()
    {
        CollectionAdapter instance2 = new CollectionAdapter();
        for(int i = 0; i<10 ; i++)
        {
            instance2.add(i);
            instance.add(i);
        }
        instance2.add(2);
        instance2.add(3);
        List sub = instance.subList(2,6);
        sub.addAll(2,instance2);
        assertEquals(22,instance.size());
        assertEquals(17,sub.size());
        assertEquals(0,sub.get(2));
        assertEquals(0,instance.get(4));
    }

    @Test
    public void testSubList10()
    {
        for(int i = 0; i<10 ; i++)
        {
            instance.add(i);
        }
        List sub = instance.subList(2,6);
        assertEquals(true,sub.contains(2));
        assertEquals(false,sub.contains(1));
        assertEquals(true,sub.contains(6));
        assertEquals(false,sub.contains(7));
    }

    @Test
    public void testSubList11()
    {
        for(int i = 0; i<10 ; i++)
        {
            instance.add(i);
        }
        List sub = instance.subList(2,6);
        CollectionAdapter instance2 = new CollectionAdapter();
        instance2.add(1);
        instance2.add(3);
        instance2.add(2);
        assertEquals(false,sub.containsAll(instance2));
        instance2.remove(1);
        instance2.add(4);
        assertEquals(true,sub.containsAll(instance2));
    }

    @Test
    public void testSubList12()
    {
        for(int i = 0; i<10 ; i++)
        {
            instance.add(i);
        }
        List sub = instance.subList(2,6);
        ListAdapter instance2 = new ListAdapter();
        for(int i = 2; i<7 ; i++)
        {
            instance2.add(i);
        }
        assertEquals(true,sub.equals(instance2));
        instance2.add(7);
        assertEquals(false,sub.equals(instance2));
    }

    @Test
    public void testSubList13()
    {
        for(int i = 0; i<10 ; i++)
        {
            instance.add(i);
        }
        List sub = instance.subList(2,6);
        assertEquals(2,sub.get(0));
    }

    @Test
    public void testSubList14()
    {
        for(int i = 0; i<10 ; i++)
        {
            instance.add(i);
        }
        List sub = instance.subList(2,6);
        sub.add(6);
        assertEquals(4,sub.indexOf(6));
        assertEquals(-1,sub.indexOf(7));
    }

    @Test
    public void testSubList15()
    {
        for(int i = 0; i<10 ; i++)
        {
            instance.add(i);
        }
        List sub = instance.subList(2,6);
        sub.add(6);
        assertEquals(5,sub.lastIndexOf(6));
        assertEquals(-1,sub.lastIndexOf(7));
    }

    @Test
    public void testSubList16()
    {
        for(int i = 0; i<10 ; i++)
        {
            instance.add(i);
        }
        List sub = instance.subList(2,6);
        assertEquals(2,sub.remove(0));
        assertEquals(4,sub.remove(1));
    }

    @Test
    public void testSubList17()
    {
        instance.add("A");
        instance.add("B");
        instance.add("C");
        instance.add("D");
        instance.add("E");
        List sub = instance.subList(2,4);
        sub.add("E");
        assertEquals(true,sub.remove("E"));
        assertEquals(true,sub.remove("E"));
        assertEquals(false,sub.remove("E"));
        assertEquals(2,sub.size());
        assertEquals(4,instance.size());
    }

    @Test
    public void testSubList18()
    {
        ListAdapter instance2 = new ListAdapter();
        for(int i = 0; i<10 ; i++)
        {
            instance2.add(i);
            instance.add(i);
        }
        instance2.remove((Object)5);
        instance2.remove((Object)6);
        instance2.remove((Object)7);
        List sub = instance.subList(2,6);
        instance2.add(20);
        sub.add(5);
        assertEquals(true,sub.removeAll(instance2));
        assertEquals(3,sub.size());
    }

    @Test
    public void testSubList19()
    {
        ListAdapter instance2 = new ListAdapter();
        for(int i = 0; i<10 ; i++)
        {
            instance.add(i);
            instance2.add(i);
        }
        List sub = instance.subList(2,6);
        instance2.remove((Object)5);
        instance2.remove((Object)6);
        sub.add(2);
        sub.add(3);
        instance2.add(2);
        instance2.add(2);
        assertEquals(true,sub.retainAll(instance2));
        assertEquals(4,sub.size());
    }

    @Test
    public void testSubList20()
    {
        for(int i = 0; i<10 ; i++)
        {
            instance.add(i);
        }
        List sub = instance.subList(2,6);
        assertEquals(4,sub.set(2, 10));
        assertEquals(10,sub.get(2));
    }

    @Test
    public void testSubList21()
    {
        Object[] tmp = new Object[10];
        for(int i=0; i<10;i++)
        {
            instance.add(i);
            tmp[i] = i;
        }
        List sub = instance.subList(2,6);
        Object[] i = sub.toArray();
        assertEquals(5,i.length);
        for(int k=0; k<i.length;k++)
        {
            assertEquals(i[k],tmp[k+2]);
        }
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