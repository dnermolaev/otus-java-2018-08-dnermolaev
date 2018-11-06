
    public enum AtmRequestState {

        Analysis{
            public void doAction (ATMRequest request) {
                System.out.println(ATMRequest.balance);

                if (ATMRequest.moneyAmount==0){
                    ATMRequest.setRequest(Balance);}
                    else {if (ATMRequest.operationType.equals("withdraw"))
                            ATMRequest.setRequest(WithdrawMoney);
                        else {ATMRequest.setRequest(DepositMoney);}}
            }
        },

        Balance {
            public void doAction (ATMRequest request) {
                System.out.println(ATMRequest.balance);
            }
        },
        WithdrawMoney{
            public void doAction (ATMRequest request) throws IncorrectAmountException {
                if (ATMRequest.moneyAmount>ATMRequest.balance)
                    throw new IncorrectAmountException("Incorrect withdrawal amount", ATMRequest.moneyAmount);
                ATMRequest.balance-= ATMRequest.moneyAmount;
                System.out.println(ATMRequest.balance);
            }
        },
        DepositMoney {
            public void doAction (ATMRequest request){

                ATMRequest.balance+= ATMRequest.moneyAmount;
                System.out.println(ATMRequest.balance);
            }
        };

        public abstract void doAction(ATMRequest context) throws IncorrectAmountException;

        public class IncorrectAmountException extends Exception {
            private long amount;
            public long getAmount () {
                return amount;
            }

            public IncorrectAmountException(String message, long amount) {
                super(message);
                this.amount = amount;
            }
        }
    }


