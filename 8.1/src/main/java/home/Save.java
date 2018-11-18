package home;

import java.util.*;

import static home.ATMRequest.atm;

public class Save {

    //protected static final Map<String, Memory> savedAtmStates = new HashMap<>();
    protected static final List <Memory> savedAtmStatesList = new ArrayList<>();

    public void add (Memory memory){
        savedAtmStatesList.add(memory);
    }

    public Memory get (int index){
        return savedAtmStatesList.get(index);}
}
