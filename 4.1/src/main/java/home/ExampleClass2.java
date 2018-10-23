package home;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class ExampleClass2 extends ExampleClass {

    @Test
    public void TestMethod () throws ClassNotFoundException, IllegalArgumentException, IllegalAccessException{
        ExampleClass2 ex = new ExampleClass2();
        int result = ex.sum(10, 5);
        assertEquals (13, result);
        System.out.println("Result of ExampleClass2.TestMethod execution");
    }

    @Test
    public void TestMethod2 () throws ClassNotFoundException, IllegalArgumentException, IllegalAccessException {
        ExampleClass2 ex = new ExampleClass2();
        int result = ex.sum(10, 5);
        assertEquals(10, result);
        System.out.println("Result of ExampleClass2.TestMethod2 execution");
    }

    @Test
    public void TestMethod3 () throws ClassNotFoundException, IllegalArgumentException, IllegalAccessException {
        ExampleClass2 ex = new ExampleClass2();
        int result = ex.sum(10, 5);
        assertEquals(9, result);
        System.out.println("Result of ExampleClass2.TestMethod2 execution");
    }

    @Override
    public void BeforeMethod (){
        System.out.println("Result of ExampleClass2.BeforeMethod execution");
    }

    @Override
    public void AfterMethod () {}


}
