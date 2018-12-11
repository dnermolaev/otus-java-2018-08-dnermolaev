package home;

public class Printer {

    public void action(ATM atm) {


        System.out.println(atm.ATMName);

        System.out.println("Balance: "+atm.getBalance());
        System.out.println(atm.cashMap);
        System.out.println();

    }
}
