package home;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.assertEquals;

public class ExampleClass {
    public int a;
    public int b;

    public ExampleClass() {
        int a=5;
        int b=4;
    }

    public int sum (int a, int b){
        int sum=a+b;
        return sum;
    }

    @Test
    public void test () throws ClassNotFoundException, IllegalArgumentException, IllegalAccessException{
        ExampleClass ex = new ExampleClass();
        int result = ex.sum(10, 5);
        assertEquals (15, result);
    }

    @Before
    public void BeforeMethod (){
        System.out.println("Result of BeforeMethod execution");
    }

    @After
    public void AfterMethod (){
        System.out.println("Result of AfterMethod execution");
    }

}
