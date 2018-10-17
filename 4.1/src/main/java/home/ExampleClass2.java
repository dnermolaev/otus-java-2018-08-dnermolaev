package home;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class ExampleClass2 extends ExampleClass {

    //@Test
    public void test () throws ClassNotFoundException, IllegalArgumentException, IllegalAccessException{
        ExampleClass2 ex = new ExampleClass2();
        int result = ex.sum(10, 5);
        assertEquals (13, result);
        System.out.println("Result of ExampleClass2.TestMethod execution");
    }

    @Override
    public void BeforeMethod (){
        System.out.println("Result of ExampleClass2.BeforeMethod execution");
    }

}
