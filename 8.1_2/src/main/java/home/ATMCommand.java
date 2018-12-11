package home;

@FunctionalInterface
public interface ATMCommand {

    void  execute(ATM atm) throws IncorrectAmountException;
}
