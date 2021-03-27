package Country;

import Location.Location;
import Population.Person;

import java.util.List;

public abstract class Settlement {

    private String m_name;
    private Location m_location;
    List<Person> people;
    RamzorColor ramzorColor;

    public abstract RamzorColor calculateRamzorGrade();

    public double contagiousPercent(){return 0;}

    public Location randomLocation(){return null;}

    private boolean AddPerson(Person person){return false;}

    private boolean transfertPerson(Person person, Settlement settlement){ return false;}






}
