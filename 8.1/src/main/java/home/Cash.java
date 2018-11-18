package home;

import java.util.EnumMap;
import java.util.HashMap;
import java.util.Map;

public class Cash{

    enum CashType {

    notes50,
    notes100,
    notes500,
    notes1000,
    notes5000
}

    static Map <CashType, Integer> cashMap1 = new EnumMap<CashType, Integer>(CashType.class);
    static Map <CashType, Integer> cashMap2 = new EnumMap<CashType, Integer>(CashType.class);
    static Map <CashType, Integer> cashMap3 = new EnumMap<CashType, Integer>(CashType.class);


}