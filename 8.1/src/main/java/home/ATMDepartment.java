package home;

import java.util.ArrayList;
import java.util.EnumMap;
import java.util.List;
import java.util.Map;

public class ATMDepartment {

    private List<ATM> atmList;

    public ATMDepartment(List<ATM> atmList) {
        this.atmList = atmList;
    }


    public void getBalances() throws IncorrectAmountException {
        for (ATM atm : atmList) {
            atm.execute();
            }
    }

    public void saveToMemory () {
        for (ATM atm:atmList){
        atm.saveToMemoryATM();}
    }

    public void restoreFromMemory (){
        Save save = new Save();

        for (ATM atm:atmList) {
            atm.restoreFromMemoryATM(save.get(atm));
        }
    }
}
