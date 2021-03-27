package Population;

import Country.Settlement;
import Location.Location;
import Virus.IVirus;

public abstract class Person {
    private int m_age;
    private Location m_location;
    private Settlement m_settlement;

    public abstract double contagionProbability();
    public abstract Person contagion(IVirus Virus);

}
