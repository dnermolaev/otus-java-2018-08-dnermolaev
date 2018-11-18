package home;

@FunctionalInterface
public interface ATMCommand {

    void execute() throws IncorrectAmountException;

}
