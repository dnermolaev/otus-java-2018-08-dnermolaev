package home;

import java.util.ArrayList;
import java.util.EnumMap;
import java.util.List;
import java.util.Map;

public class ATM implements Cloneable{

    public Map<CashType, Integer> cashMap;
    public String atmName;
    public Printer print;
    List<ATMCommand> commands;
    Save save;
    ATM atm;

    public ATM(String atmName)  {
        cashMap = new EnumMap<CashType, Integer>(CashType.class);
        //this.cashMap=cashMap;
        this.atmName = atmName;
        commands = new ArrayList<>();
        save = new Save();
        atm=clone();
    }

    public ATM getAtm() {
        return atm;
    }

    @Override
    public ATM clone() {
        try {
            return (ATM)super.clone();
        }
        catch( CloneNotSupportedException ex ) {
            throw new InternalError();
        }
    }
    public long getBalance (){
        long balance = 0;

        for (Map.Entry<CashType, Integer> entry : this.cashMap.entrySet()) {
            CashType key = entry.getKey();
            int value = entry.getValue();
            int nominal = key.getNominal();
             balance += nominal * value;
        }
        return balance;
    }

     public void execute() {
        //ATM atm=this.getAtm();
        print = new Printer();
        print.action(atm);}

    public void processCommands() throws IncorrectAmountException {

            for (ATMCommand command:commands) {
                command.execute(atm);
            }
            commands.clear();
        }

    public Memory saveToMemoryATM () {

        System.out.println("Saving " + atm.atmName);
        System.out.println(atm.cashMap);

        Map<CashType, Integer> tmpCashMap = new EnumMap<CashType, Integer>(CashType.class);
        tmpCashMap.putAll(atm.cashMap);
        save.add(atm,new Memory(tmpCashMap));
        return new Memory(tmpCashMap);
    }

    public void restoreFromMemoryATM (Memory memory){
        memory=save.get(atm);
        atm.cashMap.putAll(memory.getSavedState());
        System.out.println("ATM " + atm.atmName + " state after restore from memory " + atm.cashMap);
        System.out.println("Balance: " + atm.getBalance());
    }

    public void addCommand(ATMRequest atmRequest) {
        commands.add(atmRequest);
    }

    public void putCash(CashType cashType, int i) {
        cashMap.put(cashType,i);
    }
}


