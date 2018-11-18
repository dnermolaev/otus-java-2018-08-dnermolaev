package home;

import java.util.EnumMap;
import java.util.HashMap;
import java.util.Map;

import static home.Save.savedAtmStatesList;

public class ATM  {


    public Map<Cash.CashType, Integer> cashMap;
    public String ATMName;

    public ATM(Map<Cash.CashType, Integer> cashMap, String name) {
        this.cashMap=cashMap;
        this.ATMName=name;
    }

    /*public Memory saveToMemory (ATM atm) {
        System.out.println("Saving " + atm.ATMName);
        System.out.println(atm.cashMap);

        Map <Cash.CashType, Integer> tmpCashMap = new EnumMap<Cash.CashType, Integer>(Cash.CashType.class);

        tmpCashMap.putAll(atm.cashMap);
        return new Memory(tmpCashMap);

    }

    public void restoreFromMemory ( Memory memory){

        this.cashMap.putAll(memory.getSavedState());


        System.out.println("ATM " + ATMName + " state after restore from memory " + this.cashMap);
        //this.cashMap.putAll();
    }*/
}
