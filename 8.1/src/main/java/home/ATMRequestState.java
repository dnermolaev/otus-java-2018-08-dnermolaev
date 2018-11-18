package home;

import home.ATMRequest.*;

import static home.ATMRequest.OperationType.withdraw;
import static home.ATMRequest.atm;
import static home.ATMRequest.notes;

public enum ATMRequestState {

    Analysis{
        public void doAction (ATMRequest request) {
            System.out.println();
            if (ATMRequest.operationType==withdraw)
                        ATMRequest.setRequest(WithdrawMoney);
                    else {ATMRequest.setRequest(DepositMoney);}
            }
        },


    WithdrawMoney{
        public void doAction (ATMRequest request) throws IncorrectAmountException {
            System.out.println(atm.ATMName);
            if ((atm.cashMap.get(notes)-ATMRequest.amount)<0){

                throw new IncorrectAmountException("Incorrect withdrawal amount");}
            else {atm.cashMap.put(notes, atm.cashMap.get(notes)-ATMRequest.amount);}

            long newBalance = atm.cashMap.get(Cash.CashType.notes50)*50+atm.cashMap.get(Cash.CashType.notes100)*100
                    +atm.cashMap.get(Cash.CashType.notes500)*500+atm.cashMap.get(Cash.CashType.notes1000)*1000
                    + atm.cashMap.get(Cash.CashType.notes5000)*5000;

            System.out.println("After operation balance : " + newBalance);
            System.out.println();
        }
    },
    DepositMoney {
        public void doAction (ATMRequest request){
            System.out.println(atm.ATMName);
            atm.cashMap.put(notes, atm.cashMap.get(notes)+ATMRequest.amount);

            long newBalance = atm.cashMap.get(Cash.CashType.notes50)*50+atm.cashMap.get(Cash.CashType.notes100)*100
                    +atm.cashMap.get(Cash.CashType.notes500)*500+atm.cashMap.get(Cash.CashType.notes1000)*1000
                    + atm.cashMap.get(Cash.CashType.notes5000)*5000;

            System.out.println("After operation balance : " + newBalance);
            System.out.println();
        }
    };

    public abstract void doAction(ATMRequest context) throws IncorrectAmountException;



}



