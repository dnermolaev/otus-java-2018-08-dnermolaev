package home;

import static home.ATMRequest.*;
import static home.ATMRequest.OperationType.withdraw;

public enum ATMRequestState {

    Analysis{
        public void doAction (ATMRequest request, ATM atm) {
            System.out.println();
            if (ATMRequest.operationType==withdraw)
                        ATMRequest.setRequest(WithdrawMoney);
                    else {ATMRequest.setRequest(DepositMoney);}
            }
        },

    WithdrawMoney {
        public void doAction(ATMRequest request, ATM atm) throws IncorrectAmountException {
            System.out.println(atm.getAtmName());

                if (atm.getBalance() < sum || sum%50!=0) {
                    throw new IncorrectAmountException("Incorrect withdrawal amount");
                } else {
                    while (sum>0) {
                    if (atm.getCashMap().get(CashType.notes5000) > 0 & sum / 5000 >= 1) {
                        atm.getCashMap().put(CashType.notes5000, atm.getCashMap().get(CashType.notes5000) - 1);
                        sum -= 5000;
                    } else {
                        if (atm.getCashMap().get(CashType.notes1000) > 0 & sum / 1000 >= 1) {
                            {
                                atm.getCashMap().put(CashType.notes1000, atm.getCashMap().get(CashType.notes1000) - 1);
                                sum -= 1000;
                            }
                        } else {
                            if (atm.getCashMap().get(CashType.notes500) > 0 & sum / 500 >= 1) {
                                {
                                    atm.getCashMap().put(CashType.notes500, atm.getCashMap().get(CashType.notes500) - 1);
                                    sum -= 500;

                                }
                            } else {
                                if (atm.getCashMap().get(CashType.notes100) > 0 & sum / 100 >= 1) {
                                    {
                                        atm.getCashMap().put(CashType.notes100, atm.getCashMap().get(CashType.notes100) - 1);
                                        sum -= 100;
                                    }
                                } else {
                                    if (atm.getCashMap().get(CashType.notes50) > 0 & sum / 50 >= 1) {
                                        atm.getCashMap().put(CashType.notes50, atm.getCashMap().get(CashType.notes50) - 1);
                                        sum -= 50;
                                    }
                                    if (atm.getCashMap().get(CashType.notes50) == 0 & sum / 50 >= 1)
                                    throw new IncorrectAmountException("Incorrect withdrawal amount");
                                }
                            }
                        }
                    }
                   //atm.cashMap.put(notes, atm.cashMap.get(notes) - ATMRequest.amount);
                }
            }
            long newBalance = atm.getBalance();
            System.out.println("After operation balance : " + newBalance);
            System.out.println(atm.getCashMap());
            System.out.println();
        }
    },

    DepositMoney {
        public void doAction (ATMRequest request, ATM atm){
            System.out.println(atm.getAtmName());
            atm.getCashMap().put(notes, atm.getCashMap().get(notes)+ amount);

            long newBalance = atm.getBalance();

            System.out.println("After operation balance : " + newBalance);
            System.out.println();
        }
    };

    public abstract void doAction(ATMRequest context, ATM atm) throws IncorrectAmountException;



}



