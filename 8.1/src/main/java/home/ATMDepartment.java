package home;

import java.util.EnumMap;
import java.util.Map;

public class ATMDepartment implements ATMCommand {

    private ATM atm;
    private Printer print;

    public ATMDepartment(ATM atm, Printer print) {
        this.atm = atm;
        this.print = print;
    }

    public static Memory saveToMemory (ATM atm) {
        System.out.println("Saving " + atm.ATMName);
        System.out.println(atm.cashMap);

        Map<Cash.CashType, Integer> tmpCashMap = new EnumMap<Cash.CashType, Integer>(Cash.CashType.class);

        tmpCashMap.putAll(atm.cashMap);
        return new Memory(tmpCashMap);

    }

    public static void restoreFromMemory (ATM atm, Memory memory){

        atm.cashMap.putAll(memory.getSavedState());

        System.out.println("ATM " + atm.ATMName + " state after restore from memory " + atm.cashMap);
    }

    @Override
    public void  execute() {print.action(atm);}





}
