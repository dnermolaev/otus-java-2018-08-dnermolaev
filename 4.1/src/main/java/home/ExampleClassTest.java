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
import java.util.ArrayList;
import java.util.List;

public class ExampleClassTest {

    public static void testClass(String className) throws Exception {
        try {
            System.out.println();
            System.out.println("Execution with className");
            Class<?> myExample = Class.forName(className);
            System.out.println(className);
            Object obj1 = myExample.newInstance();

            Method[] methods = myExample.getMethods();

            Annotation[] annotations = new Annotation[0];
            List<Method> before = new ArrayList<>();
            List<Method> test = new ArrayList<>();
            List<Method> after = new ArrayList<>();

            for (Method method : methods) {

                annotations = method.getDeclaredAnnotations();

                if (annotations.length > 0) {

                    for (Annotation annotation1 : annotations) {
                        if (annotation1 instanceof Before) {
                            before.add(method);
                        }
                        if (annotation1 instanceof Test) {
                            test.add(method);
                        }
                        if (annotation1 instanceof After) {
                            after.add(method);
                        }
                    }
                }
            }

            Object obj = new Object();
            if (annotations.length > 0) {

                if (test != null)
                    obj = myExample.newInstance();

                if (before != null)
                    for (Method method : before)
                        try {
                            method.invoke(obj);
                        } catch (InvocationTargetException e) {
                            System.out.println("Error in @Before method");
                        }

                if (test != null)
                    for (Method method : test)
                        try {
                            method.invoke(obj);
                        } catch (InvocationTargetException e) {
                            System.out.println("Error in @Test method");
                        }

                if (after != null)
                    for (Method method : after)
                        try {
                            method.invoke(obj);
                        } catch (InvocationTargetException e) {
                            System.out.println("Error in @After method");
                        }
            }
        } catch (NullPointerException | ClassNotFoundException |
                InstantiationException | IllegalAccessException e) {
            throw new Exception(
                    "Error while performing method");
        }
    }


    public static void testPackage(String packageName) throws Exception {
        try {
            System.out.println("Execution with packageName");
            Package pack = Package.getPackage(packageName);
            ImmutableSet classes = ClassPath.from(ExampleClassTest.class.getClassLoader()).getAllClasses();
            Object[] classNames = classes.toArray();

            for (Object o : classNames) {
                if (o.toString().contains(packageName))
                    testClass(o.toString());
            }

        } catch (NullPointerException | ClassNotFoundException |
                InstantiationException | IllegalAccessException | InvocationTargetException e) {
            throw new Exception(
                    "Error while performing method");
        }
    }
}

