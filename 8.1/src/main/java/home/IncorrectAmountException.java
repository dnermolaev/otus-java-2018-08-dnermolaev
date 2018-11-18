package home;

public class IncorrectAmountException extends ATMException {
    private long amount;

    public long getAmount() {
        return amount;
    }

    public IncorrectAmountException(String message/*, long amount*/) {
        super(message);
        //this.amount = amount;
    }
}
