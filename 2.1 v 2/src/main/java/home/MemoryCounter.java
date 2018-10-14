package home;

import java.lang.instrument.Instrumentation;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.lang.*;


public class MemoryCounter {

        private static MemoryCounter memoryCounter;
        private static Instrumentation instrumentation;

        static MemoryCounter instance(){
                if(memoryCounter == null){
                        memoryCounter = new MemoryCounter();
                }
                return memoryCounter;
        }

        private static void premain(String args, Instrumentation instrumentation) {
                MemoryCounter.instrumentation = instrumentation;
                instance().instrumentation = instrumentation;
                }

        private static long getSize(Object obj) {
                if (instrumentation == null) {
                        throw new IllegalStateException("Agent not initialised");
                }
                return instrumentation.getObjectSize(obj);
        }

        Instrumentation getInstrumentation() {
                return instrumentation;
        }

        public static List<Object> calculated = new ArrayList<>();

        public static void printObjectSize(Object obj) {


                if (obj.getClass().isArray()) {

                    System.out.println("Array");
                    System.out.println(obj.getClass());
                    System.out.println(String.format("%s, size=%s", obj.getClass().getSimpleName(),
                            MemoryCounter.getSize(obj)));
                    System.out.println();

                } else {
                        Field[] fields = obj.getClass().getFields();

                        try {
                                for (Field field : fields) {
                                        Object value = (Object) obj.getClass().getField(field.getName()).get(obj);

                                        if (!calculated.contains(value) && value != null) {
                                                calculated.add(value);
                                            System.out.println("Class name : " + field.getName());
                                            System.out.println("Class type : " + field.getType().getName());
                                            System.out.println("Modifier :" + Modifier.isStatic(field.getModifiers())
                                                 + "  primitive=" + value.getClass().isPrimitive());
                                            System.out.println();

                                                if (!Modifier.isStatic(field.getModifiers())&& !value.getClass().
                                                        isPrimitive()){
                                                    System.out.println("Deeper");
                                                    System.out.println();
                                                    printObjectSize(value);
                                                }

                                                else {System.out.println(String.format("%s, size=%s", obj.getClass()
                                                        .getSimpleName(), MemoryCounter.getSize(value)));
                                                System.out.println();}
                                        }
                                        /*else {
                                            System.out.println("Already calculated");}*/
                                }
                        } catch (NoSuchFieldException | IllegalAccessException nsfe) {
                                throw new RuntimeException(nsfe);
                        }
                }

        }
}

