package home;

import java.util.*;

import static home.ATMRequest.OperationType.*;


public class Main {

    public static void main(String[] args) throws IncorrectAmountException {

        //Map<Cash.CashType, Integer> cashMap = new EnumMap<Cash.CashType, Integer>(Cash.CashType.class);

        ATM atm1 = new ATM("atm1");
        ATM atm2 = new ATM("atm2");
        ATM atm3 = new ATM("atm3");

        atm1.cashMap.put(CashType.notes50, 1000);
        atm1.cashMap.put(CashType.notes100, 1000);
        atm1.cashMap.put(CashType.notes500, 1000);
        atm1.cashMap.put(CashType.notes1000, 0);
        atm1.cashMap.put(CashType.notes5000, 0);

        atm2.cashMap.put(CashType.notes50, 1000);
        atm2.cashMap.put(CashType.notes100, 500);
        atm2.cashMap.put(CashType.notes500, 2000);
        atm2.cashMap.put(CashType.notes1000, 0);
        atm2.cashMap.put(CashType.notes5000, 0);

        atm3.cashMap.put(CashType.notes50, 1000);
        atm3.cashMap.put(CashType.notes100, 3000);
        atm3.cashMap.put(CashType.notes500, 1000);
        atm3.cashMap.put(CashType.notes1000, 0);
        atm3.cashMap.put(CashType.notes5000, 0);

        List<ATM> atmList = new ArrayList<>();
        ATMDepartment atmDepartment = new ATMDepartment(atmList);

        atmList.add(atm1);
        atmList.add(atm2);
        atmList.add(atm3);

        System.out.println("Total ATM's balance: ");

        atmDepartment.getBalances(atmList);

        atmDepartment.saveToMemory(atmList);

        atm1.commands.add(new ATMRequest(withdraw, atm1, 1650));
        atm1.processCommands(atm1);

        atmDepartment.restoreFromMemory(atmList);

    }
}
