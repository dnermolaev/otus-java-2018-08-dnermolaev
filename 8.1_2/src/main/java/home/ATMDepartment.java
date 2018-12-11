package home;

import java.util.ArrayList;
import java.util.EnumMap;
import java.util.List;
import java.util.Map;

public class ATMDepartment {

    List<ATM> atmList;

    public ATMDepartment(List<ATM> atmList) {
        this.atmList = atmList;
    }


    public void getBalances(List<ATM> atmList) throws IncorrectAmountException {
        for (ATM atm : atmList) {
            atm.execute();
            }
    }

    public void saveToMemory (List<ATM> atmList) {
        for (ATM atm:atmList){
        atm.saveToMemoryATM();}
    }

    public void restoreFromMemory (List<ATM> atmList){
        Save save = new Save();

        for (ATM atm:atmList) {
            atm.restoreFromMemoryATM(save.get(atm));
        }
    }
}
