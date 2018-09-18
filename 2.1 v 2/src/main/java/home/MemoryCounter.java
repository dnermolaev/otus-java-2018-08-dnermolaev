package home;

import java.lang.instrument.Instrumentation;

public class MemoryCounter {

        private static MemoryCounter memoryCounter;
        private static Instrumentation instrumentation;

        private MemoryCounter(){}

        static MemoryCounter instance(){
                if(memoryCounter == null){
                        memoryCounter = new MemoryCounter();
                }
                return memoryCounter;
        }

                public static void premain(String args, Instrumentation instrumentation) {
                        MemoryCounter.instrumentation = instrumentation;
                        instance().instrumentation = instrumentation;

                        //System.out.println("Classes loaded: " + instrumentation.getAllLoadedClasses().length);

                        //System.out.println("String size: " + instrumentation.getObjectSize(new String(new char[100]))); //"shallow" size.
                        //System.out.println("int[10] size: " + instrumentation.getObjectSize(new int[10]));
                }

                public static long getSize(Object obj) {
                        if (instrumentation == null) {
                                throw new IllegalStateException("Agent not initialised");
                        }
                        return instrumentation.getObjectSize(obj);
                }
        Instrumentation getInstrumentation() {
                return instrumentation;
        }
        }

