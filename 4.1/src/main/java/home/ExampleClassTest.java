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


    public static void test1 (String className) throws ClassNotFoundException, IllegalAccessException, InstantiationException,
            InvocationTargetException {
        System.out.println();
        System.out.println("Execution with className");
        Class<?> myExample = Class.forName(className);
        System.out.println(className);
        Object obj1 = myExample.newInstance();

        Method[] methods = myExample.getMethods();
        for (Method method : methods) {

            Annotation[] annotations = method.getDeclaredAnnotations();
            if (annotations.length > 0) {
                for (Annotation annotation1 : annotations) {
                    if (annotation1 instanceof Before) {
                        method.invoke(obj1);

                    }
                    if (annotation1 instanceof Test) {
                        method.invoke(obj1);
                    }

                    if (annotation1 instanceof After) {
                        method.invoke(obj1);
                    }
                }
            }
        }
    }

    public static void test2 (String packageName) throws IOException, ClassNotFoundException, InvocationTargetException,
            InstantiationException, IllegalAccessException {
        System.out.println("Execution with packageName");
       Package pack = Package.getPackage(packageName);
       ImmutableSet classes = ClassPath.from(ExampleClassTest.class.getClassLoader()).getAllClasses();
       Object [] classNames =classes.toArray();

       for (Object o:classNames) {
          if(o.toString().contains(packageName))
              test1(o.toString());
       }

    }
}
