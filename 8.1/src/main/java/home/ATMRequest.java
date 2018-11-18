package home;

public class ATMRequest implements ATMCommand  {

    protected static OperationType operationType;
    protected static ATM atm;
    private ATMRequestState state;
    protected static Cash.CashType notes;
    static int amount;


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
        this.atm = atm;
        this.amount = amount;
        this.operationType=operationType;
        this.notes=notes;
        request = ATMRequestState.Analysis;
    }


    @Override
    public void execute() throws IncorrectAmountException{
        request.doAction(this);
        request.doAction(this);
    }

}
