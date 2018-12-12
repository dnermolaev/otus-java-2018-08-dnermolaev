package home;

import java.util.ArrayList;
import java.util.EnumMap;
import java.util.List;
import java.util.Map;

public class ATM {

    private Map<CashType, Integer> cashMap;
    private String atmName;
    private Printer print;
    private List<ATMCommand> commands;
    private Save save;

    public ATM(String atmName)  {
        cashMap = new EnumMap<CashType, Integer>(CashType.class);
        //this.cashMap=cashMap;
        this.atmName = atmName;
        commands = new ArrayList<>();
        save = new Save();
    }

    public String getAtmName() {
        return atmName;
    }

    public Map<CashType, Integer> getCashMap() {
        return cashMap;
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
        print.action(this);}

    public void processCommands() throws IncorrectAmountException {

            for (ATMCommand command:commands) {
                command.execute(this);
            }
            commands.clear();
        }

    public Memory saveToMemoryATM () {

        System.out.println("Saving " + this.atmName);
        System.out.println(this.cashMap);

        Map<CashType, Integer> tmpCashMap = new EnumMap<CashType, Integer>(CashType.class);
        tmpCashMap.putAll(this.cashMap);
        save.add(this,new Memory(tmpCashMap));
        return new Memory(tmpCashMap);
    }

    public void restoreFromMemoryATM (Memory memory){
        memory=save.get(this);
        this.cashMap.putAll(memory.getSavedState());
        System.out.println("ATM " + this.atmName + " state after restore from memory " + this.cashMap);
        System.out.println("Balance: " + this.getBalance());
    }

    public void addCommand(ATMRequest atmRequest) {
        commands.add(atmRequest);
    }

    public void putCash(CashType cashType, int i) {
        cashMap.put(cashType,i);
    }
}


