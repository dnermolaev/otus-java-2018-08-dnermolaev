package home;

public class Printer {

    public void action(ATM atm) {


        System.out.println(atm.getAtmName());

        System.out.println("Balance: "+atm.getBalance());
        System.out.println(atm.getCashMap());
        System.out.println();

    }
}
