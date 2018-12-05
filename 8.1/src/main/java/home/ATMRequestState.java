package home;

import home.ATMRequest.*;

import java.util.Map;

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
            System.out.println(atm.ATMName);

                if (atm.getBalance(atm) < ATMRequest.sum || sum%50!=0) {
                    throw new IncorrectAmountException("Incorrect withdrawal amount");
                } else {
                    while (sum>0) {
                    if (atm.cashMap.get(Cash.CashType.notes5000) > 0 & sum / 5000 >= 1) {
                        atm.cashMap.put(Cash.CashType.notes5000, atm.cashMap.get(Cash.CashType.notes5000) - 1);
                        sum -= 5000;
                    } else {
                        if (atm.cashMap.get(Cash.CashType.notes1000) > 0 & sum / 1000 >= 1) {
                            {
                                atm.cashMap.put(Cash.CashType.notes1000, atm.cashMap.get(Cash.CashType.notes1000) - 1);
                                sum -= 1000;
                            }
                        } else {
                            if (atm.cashMap.get(Cash.CashType.notes500) > 0 & sum / 500 >= 1) {
                                {
                                    atm.cashMap.put(Cash.CashType.notes500, atm.cashMap.get(Cash.CashType.notes500) - 1);
                                    sum -= 500;

                                }
                            } else {
                                if (atm.cashMap.get(Cash.CashType.notes100) > 0 & sum / 100 >= 1) {
                                    {
                                        atm.cashMap.put(Cash.CashType.notes100, atm.cashMap.get(Cash.CashType.notes100) - 1);
                                        sum -= 100;
                                    }
                                } else {
                                    if (atm.cashMap.get(Cash.CashType.notes50) > 0 & sum / 50 >= 1) {
                                        atm.cashMap.put(Cash.CashType.notes50, atm.cashMap.get(Cash.CashType.notes50) - 1);
                                        sum -= 50;
                                    }
                                    if (atm.cashMap.get(Cash.CashType.notes50) == 0 & sum / 50 >= 1)
                                    throw new IncorrectAmountException("Incorrect withdrawal amount");
                                }
                            }
                        }
                    }
                   //atm.cashMap.put(notes, atm.cashMap.get(notes) - ATMRequest.amount);
                }
            }
            long newBalance = atm.getBalance(atm);

            System.out.println("After operation balance : " + newBalance);
            System.out.println(atm.cashMap);
            System.out.println();
        }
    },

    DepositMoney {
        public void doAction (ATMRequest request, ATM atm){
            System.out.println(atm.ATMName);
            atm.cashMap.put(notes, atm.cashMap.get(notes)+ATMRequest.amount);

            long newBalance = atm.getBalance(atm);

            System.out.println("After operation balance : " + newBalance);
            System.out.println();
        }
    };

    public abstract void doAction(ATMRequest context, ATM atm) throws IncorrectAmountException;



}



