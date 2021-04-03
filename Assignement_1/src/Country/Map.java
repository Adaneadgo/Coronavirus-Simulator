package Country;

import java.util.Arrays;

public class Map {

    private Settlement[] m_settlements;
    private static Map instance = null;

    //Ctors
    protected Map(Settlement[] settlements) { m_settlements = settlements;}

    @Override
    public String toString() {
        StringBuilder str = new StringBuilder("\nMap Settlements: " + "\n" +"\n");
        for(Settlement settlement: m_settlements)
            str.append(settlement).append("\n");
        return str.toString();


    }

    //Methods
    public static Map getInstance(Settlement[] settlements) {
        if(instance == null) {
            instance = new Map(settlements);
        }
        return instance;
    }

    public void setSicks(){
        for(Settlement settlement: m_settlements)
            settlement.setSicks();
    }



}
