package home;

import com.google.common.collect.ImmutableSet;
import com.google.common.reflect.ClassPath;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.lang.annotation.Annotation;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class ExampleClassTest {

    public static void test1 (String className) throws ClassNotFoundException, IllegalAccessException,
            InstantiationException, InvocationTargetException, NoSuchMethodException {
        System.out.println();
        System.out.println("Execution with className");
        Class<?> myExample = Class.forName(className);
        System.out.println(className);
        Object obj1 = myExample.newInstance();

        Method[] methods = myExample.getMethods();

        Annotation[] annotations = new Annotation[0];
        Method before = null;
        Method test = null;
        Method after = null;

        try {

        for (Method method : methods) {

            annotations = method.getDeclaredAnnotations();

            if (annotations.length > 0) {

                for (Annotation annotation1 : annotations) {
                    if (annotation1 instanceof Before) {
                        before = method;
                    }
                    if (annotation1 instanceof Test) {
                        test = method;
                    }
                    if (annotation1 instanceof After) {
                        after = method;
                    }
                }
            }
        }
            if (annotations.length > 0)
                    try {
                        before.invoke(myExample.newInstance());}
                     catch (NullPointerException e) {
                        System.out.println("There is no method with @Before annotation");
                    }
                    try {
                        test.invoke(myExample.newInstance());
                    }
                    catch (InvocationTargetException e) {
                        System.out.println("Test failed");
                    }
                    catch (NullPointerException e) {
                        System.out.println("There is no method with @Test annotation");
                    }
                        finally {
                        after.invoke(myExample.newInstance());
                    }
        }
        catch (NullPointerException e) {
            System.out.println("There is no method with @After annotation");
        }
        catch (InvocationTargetException e){
            System.out.println(e);
        }
    }


    public static void test2 (String packageName) throws IOException, ClassNotFoundException, InvocationTargetException,
            InstantiationException, IllegalAccessException, NoSuchMethodException {
        try {
            System.out.println("Execution with packageName");
            Package pack = Package.getPackage(packageName);
            ImmutableSet classes = ClassPath.from(ExampleClassTest.class.getClassLoader()).getAllClasses();
            Object[] classNames = classes.toArray();

            for (Object o : classNames) {
                if (o.toString().contains(packageName))
                    test1(o.toString());
            }

        } catch (InvocationTargetException e) {
            System.out.println(e);
            System.out.println(e.getCause().getMessage());
        }
    }
}
