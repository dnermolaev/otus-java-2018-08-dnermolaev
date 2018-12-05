package home;

import java.time.temporal.TemporalAmount;
import java.util.ArrayList;
import java.util.EnumMap;
import java.util.List;
import java.util.Map;

public class ATM implements  ATMCommand{

    public Map<Cash.CashType, Integer> cashMap;
    public String ATMName;
    public Printer print;
    List<ATMCommand> commands;
    Save save;

    public ATM(String ATMName) {
        cashMap = new EnumMap<Cash.CashType, Integer>(Cash.CashType.class);
        //this.cashMap=cashMap;
        this.ATMName = ATMName;
        commands = new ArrayList<>();
        save = new Save();
    }

    public long getBalance (ATM atm){

        /*long balance = atm.cashMap.get(Cash.CashType.notes50)*50+atm.cashMap.get(Cash.CashType.notes100)*100
                +atm.cashMap.get(Cash.CashType.notes500)*500+atm.cashMap.get(Cash.CashType.notes1000)*1000
            + atm.cashMap.get(Cash.CashType.notes5000)*5000;*/
        long balance = 0;
        for (Map.Entry<Cash.CashType, Integer> entry : atm.cashMap.entrySet()) {

            Cash.CashType key = entry.getKey();
            int value = entry.getValue();
            int nominal = 0;
            switch (key) {
                case notes50:
                    nominal = 50;
                    break;
                case notes100:
                    nominal = 100;
                    break;
                case notes500:
                    nominal = 500;
                    break;
                case notes1000:
                    nominal = 1000;
                    break;
                case notes5000:
                    nominal = 5000;
                    break;
            }
            balance += nominal * value;
        }
        return balance;
    }

   @Override
    public void  execute(ATM atm) {
       print = new Printer();
       print.action(atm);}

    public void processCommands(ATM atm) throws IncorrectAmountException {

            for (ATMCommand command:commands) {
                command.execute(atm);
            }
            commands.clear();
        }


    public Memory saveToMemoryATM (ATM atm) {

        System.out.println("Saving " + atm.ATMName);
        System.out.println(atm.cashMap);

        Map<Cash.CashType, Integer> tmpCashMap = new EnumMap<Cash.CashType, Integer>(Cash.CashType.class);
        tmpCashMap.putAll(atm.cashMap);
        save.add(atm,new Memory(tmpCashMap));
        return new Memory(tmpCashMap);
    }

    public void restoreFromMemoryATM (ATM atm, Memory memory){
        memory=save.get(atm);
        atm.cashMap.putAll(memory.getSavedState());
        System.out.println("ATM " + atm.ATMName + " state after restore from memory " + atm.cashMap);
    }
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

