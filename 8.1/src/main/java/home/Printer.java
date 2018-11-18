package home;


public class Printer {

    public void action(ATM atm) {
        System.out.println(atm.ATMName);
        long balance = atm.cashMap.get(Cash.CashType.notes50)*50+atm.cashMap.get(Cash.CashType.notes100)*100
                +atm.cashMap.get(Cash.CashType.notes500)*500+atm.cashMap.get(Cash.CashType.notes1000)*1000
            + atm.cashMap.get(Cash.CashType.notes5000)*5000;
        System.out.println(balance);
    }
}
