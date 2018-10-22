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
    public void TestMethod () throws ClassNotFoundException, IllegalArgumentException, IllegalAccessException{
        try {
            ExampleClass ex = new ExampleClass();
            int result = ex.sum(10, 5);
            assertEquals(13, result);
        } catch (IllegalArgumentException e){throw new IllegalAccessException(e.getCause().getMessage());
        }
    }

    @Before
    public void BeforeMethod () throws Exception {
        try {
            System.out.println("Result of BeforeMethod execution");
        } catch (Exception e) {
            throw new Exception(e.getCause().getMessage());
        }
    }

    @After
    public void AfterMethod () throws Exception{
        try{
            System.out.println("Result of AfterMethod execution");
        } catch (Exception e){
            throw new Exception(e.getCause().getMessage());
        }

    }

}
