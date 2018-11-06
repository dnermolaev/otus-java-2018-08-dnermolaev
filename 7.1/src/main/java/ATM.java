public class ATM {

    public static void main(String[] args) throws AtmRequestState.IncorrectAmountException {
        ATMRequest atmRequest=new ATMRequest(123456, 1000, ATMRequest.OperationType.withdraw);
        atmRequest.doAction();
    }

}
