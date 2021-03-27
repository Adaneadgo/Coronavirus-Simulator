package Country;

import Location.Location;
import Population.Person;

import java.util.*;

public abstract class Settlement {

    private String m_name;
    private Location m_location;
    private List<Person> m_people;
    private RamzorColor m_ramzorColor;

    public Settlement(String name, Location location,int people, RamzorColor ramzorColor)
    {
        m_name = name;
        m_location = location;
        m_people = new ArrayList<Person>();
        Random ran = new Random();
        for(int i = 0; i < people ; i++)
        {

        }


    }



    public abstract RamzorColor calculateRamzorGrade();

    public double contagiousPercent(){return 0;}

    public Location randomLocation(){return null;}

    private boolean AddPerson(Person person){return false;}

    private boolean transfertPerson(Person person, Settlement settlement){ return false;}






}
