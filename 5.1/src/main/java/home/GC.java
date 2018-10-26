package home;

import java.lang.management.GarbageCollectorMXBean;
import java.lang.management.ManagementFactory;
import java.text.DecimalFormat;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

public class GC {

    static final Set<String> YOUNG_GC = new HashSet<String>(3);
    static final Set<String> OLD_GC = new HashSet<String>(3);

    static {
        // young generation GC names
        YOUNG_GC.add("PS Scavenge");
        YOUNG_GC.add("ParNew");
        YOUNG_GC.add("G1 Young Generation");

        // old generation GC names
        OLD_GC.add("PS MarkSweep");
        OLD_GC.add("ConcurrentMarkSweep");
        OLD_GC.add("G1 Old Generation");
    }

    public static void printGCMetrics() {

        long minorCount = 0;
        double minorTime = 0;
        long majorCount = 0;
        double majorTime = 0;
        long unknownCount = 0;
        long unknownTime = 0;

        List<GarbageCollectorMXBean> mxBeans
                = ManagementFactory.getGarbageCollectorMXBeans();
        for (GarbageCollectorMXBean gc : mxBeans) {
            long count = gc.getCollectionCount();
            if (count >= 0) {
                if (YOUNG_GC.contains(gc.getName())) {
                    minorCount += count;
                    minorTime += (gc.getCollectionTime()/60000.0);
                    majorCount += count;
                    majorTime += (gc.getCollectionTime()/60000.0);
                } else {
                    unknownCount += count;
                    unknownTime += gc.getCollectionTime()/60000.0;

                }
            }
        }

        StringBuilder sb = new StringBuilder();
        sb.append("Young GC -> MinorGC -> Count: ").append(minorCount)
                .append(", Time (min): ").append(new DecimalFormat("#0.000").format(minorTime))
                .append(", Old GC -> MajorGC -> Count: ").append(majorCount)
                .append(", Time (min): ").append(new DecimalFormat("#0.000").format(majorTime));

        if (unknownCount > 0) {
            sb.append(", UnknownGC -> Count: ").append(unknownCount)
                    .append(", Time (ms): ").append(unknownTime);
        }

        System.out.println(sb);
    }

}
