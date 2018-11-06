
public class ATMRequest  {

    public static long balance;
    public static long moneyAmount;
    public int accountNumber;
    public static OperationType operationType;

    private static AtmRequestState request;

    public static void setRequest(AtmRequestState request) {
        ATMRequest.request = request;
    }

    public static AtmRequestState getRequest() {
        return request;
    }

    public enum OperationType {
        withdraw,
        deposit,
    }


    public ATMRequest(int accountNumber) {
        this.accountNumber=accountNumber;
        request=AtmRequestState.Analysis;
    }

     public ATMRequest(int accountNumber, long moneyAmount, OperationType operationType){
        this.accountNumber = accountNumber;
        this.moneyAmount = moneyAmount;
        this.operationType=operationType;
        request = AtmRequestState.Analysis;
    }

    public void doAction () throws AtmRequestState.IncorrectAmountException {
        request.doAction(this);
    }


}
