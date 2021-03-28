package Country;

import Location.*;
import Population.*;

import java.util.*;

public abstract class Settlement {

    private String m_name;
    private Location m_location;
    private List<Person> m_people;
    private RamzorColor m_ramzorColor;

    public Settlement() {}
    public Settlement(String name, Location location, int numOfPeople, RamzorColor ramzorColor)
    {
        m_name = name;
        m_location = location;
        m_people = new ArrayList<Person>();

    }
    public Settlement(Settlement other) { m_name = other.m_name; m_location = new Location(other.m_location); m_people = other.m_people; m_ramzorColor = other.m_ramzorColor; }


    public abstract RamzorColor calculateRamzorGrade();
    public double contagiousPercent(){return 0;}
    public Location randomLocation(){return null;}
    //private boolean AddPerson(Person person){return false;}
    //private boolean transfertPerson(Person person, Settlement settlement){ return false;}






}
