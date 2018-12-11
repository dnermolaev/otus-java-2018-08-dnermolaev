package home;

import java.util.*;


public class Save {

    protected static final Map<String, Memory> savedAtmStatesMap = new HashMap<>();
    //protected static final List <Memory> savedAtmStatesList = new ArrayList<>();

    public void add (ATM atm, Memory memory){savedAtmStatesMap.put(atm.atmName,memory);
        //savedAtmStatesList.add(memory);


    }

    public Memory get (ATM atm){
        return savedAtmStatesMap.get(atm.atmName);}
}
