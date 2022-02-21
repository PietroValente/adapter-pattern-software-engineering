import org.junit.runner.JUnitCore;
import org.junit.runner.Result;
import org.junit.runner.notification.Failure;

public class TestRunner
{
   public static void main(String[] args)
   {
      Result resultCollection = JUnitCore.runClasses(CollectionTest.class);
      for (Failure failure : resultCollection.getFailures())
      {
         System.out.println(failure.toString());
      }
      System.out.println("1.CollectionTest = " + resultCollection.wasSuccessful());

      Result resultList = JUnitCore.runClasses(ListTest.class);
      for (Failure failure : resultList.getFailures())
      {
         System.out.println(failure.toString());
      }
      System.out.println("2.ListTest = " + resultList.wasSuccessful());

      Result resultSet = JUnitCore.runClasses(SetTest.class);
      for (Failure failure : resultSet.getFailures())
      {
         System.out.println(failure.toString());
      }
      System.out.println("3.SetTest = " + resultSet.wasSuccessful());
      
      Result resultMap = JUnitCore.runClasses(MapTest.class);
      for (Failure failure : resultMap.getFailures())
      {
         System.out.println(failure.toString());
      }
      System.out.println("4.MapTest = " + resultMap.wasSuccessful());

      Result resultHashIterator = JUnitCore.runClasses(HashIteratorTest.class);
      for (Failure failure : resultHashIterator.getFailures())
      {
         System.out.println(failure.toString());
      }
      System.out.println("5.HashIteratorTest = " + resultHashIterator.wasSuccessful());

      Result resultListIterator = JUnitCore.runClasses(ListIteratorTest.class);
      for (Failure failure : resultListIterator.getFailures())
      {
         System.out.println(failure.toString());
      }
      System.out.println("6.ListIteratorTest = " + resultListIterator.wasSuccessful());

      Result resultMyEntry = JUnitCore.runClasses(MyEntryTest.class);
      for (Failure failure : resultMyEntry.getFailures())
      {
         System.out.println(failure.toString());
      }
      System.out.println("7.MyEntryTest = " + resultMyEntry.wasSuccessful());

   }
}