package home;

public class ATMRequest implements ATMCommand  {

    protected static OperationType operationType;
    private ATMRequestState state;
    protected static Cash.CashType notes;
    static int amount;
    static int sum;


    private static ATMRequestState request;

    public static void setRequest(ATMRequestState request) {
        ATMRequest.request = request;
    }

    public static ATMRequestState getRequest() {
        return request;
    }

    public enum OperationType {
        withdraw,
        deposit,
    }

    public ATMRequest(OperationType operationType, ATM atm, Cash.CashType notes, int amount){
        atm = atm;
        this.amount = amount;
        this.operationType=operationType;
        this.notes=notes;
        request = ATMRequestState.Analysis;
    }

    public ATMRequest(OperationType operationType, ATM atm, int sum){
        atm = atm;
        this.sum = sum;
        this.operationType=operationType;
        request = ATMRequestState.Analysis;
    }

    @Override
    public void execute(ATM atm) throws IncorrectAmountException{
        request.doAction(this, atm);
        request.doAction(this, atm);
    }

}
