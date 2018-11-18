package home;

import java.util.ArrayList;
import java.util.List;

import static home.ATMRequest.OperationType.*;
import static home.Cash.*;
import static home.ATMDepartment.*;

public class Main {

    private static List<ATMCommand> commands = new ArrayList<>();

    public static void main(String[] args) throws IncorrectAmountException {

        cashMap1.put(Cash.CashType.notes50, 1000);
        cashMap1.put(Cash.CashType.notes100, 1000);
        cashMap1.put(Cash.CashType.notes500, 1000);
        cashMap1.put(Cash.CashType.notes1000, 0);
        cashMap1.put(Cash.CashType.notes5000, 0);

        cashMap2.put(Cash.CashType.notes50, 1000);
        cashMap2.put(Cash.CashType.notes100, 500);
        cashMap2.put(Cash.CashType.notes500, 2000);
        cashMap2.put(Cash.CashType.notes1000, 0);
        cashMap2.put(Cash.CashType.notes5000, 0);

        cashMap3.put(Cash.CashType.notes50, 1000);
        cashMap3.put(Cash.CashType.notes100, 3000);
        cashMap3.put(Cash.CashType.notes500, 1000);
        cashMap3.put(Cash.CashType.notes1000, 0);
        cashMap3.put(Cash.CashType.notes5000, 0);

        ATM atm1 = new ATM(cashMap1, "atm1");
        ATM atm2 = new ATM(cashMap2, "atm2");
        ATM atm3 = new ATM(cashMap3, "atm3");

        Main main = new Main();
        Save save = new Save();

        commands.add(new ATMDepartment(atm1, new Printer()));
        commands.add(new ATMDepartment(atm2, new Printer()));
        commands.add(new ATMDepartment(atm3, new Printer()));

        System.out.println("Total ATM's balance: ");

        main.processCommands();

        save.add(saveToMemory(atm1));
        save.add(saveToMemory(atm2));
        save.add(saveToMemory(atm3));

        commands.clear();
        commands.add(new ATMRequest(withdraw, atm1, CashType.notes50, 100));

        main.processCommands();

        restoreFromMemory(atm1, save.get(0));
        restoreFromMemory(atm2, save.get(1));
        restoreFromMemory(atm3, save.get(2));

        commands.clear();
        commands.add(new ATMDepartment(atm1, new Printer()));
        commands.add(new ATMDepartment(atm2, new Printer()));
        commands.add(new ATMDepartment(atm3, new Printer()));

        main.processCommands();
    }

    private void processCommands() throws IncorrectAmountException {
        for (ATMCommand command : commands) {
            command.execute();
        }
    }
}
