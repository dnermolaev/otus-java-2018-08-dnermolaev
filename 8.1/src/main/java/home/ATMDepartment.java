package home;

import java.util.ArrayList;
import java.util.EnumMap;
import java.util.List;
import java.util.Map;

public class ATMDepartment {

    List<ATM> atmList;
    List<ATMCommand> commands;

    public ATMDepartment(List<ATM> atmList) {
        this.atmList = atmList;
        commands = new ArrayList<>();
    }


    public void processCommands(List<ATM> atmList) throws IncorrectAmountException {
        for (ATM atm : atmList) {
            commands.add(atm);

            for (ATMCommand command:commands) {
                command.execute(atm);
            }
            commands.clear();
        }
    }

    public void saveToMemory (List<ATM> atmList) {
        for (ATM atm:atmList){
        atm.saveToMemoryATM(atm);}
    }

    public void restoreFromMemory (List<ATM> atmList){
        Save save = new Save();

        for (ATM atm:atmList) {
            atm.restoreFromMemoryATM(atm, save.get(atm));
        }
    }
}
