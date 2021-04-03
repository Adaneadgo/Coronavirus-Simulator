package Country;

import java.util.Arrays;

public class Map {

    private Settlement[] m_settlements;
    private static Map instance = null;

    //Ctors
    protected Map(Settlement[] settlements) { m_settlements = settlements;}

    //Methods
    public static Map getInstance(Settlement[] settlements) {
        if(instance == null) {
            instance = new Map(settlements);
        }
        return instance;
    }

    @Override
    public String toString() {
        return "Map:" + "\n" +
                "m_settlements=" + Arrays.toString(m_settlements) + "\n";
    }


}
