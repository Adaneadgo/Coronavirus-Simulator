package Population;

import Country.Settlement;
import Location.Location;
import Virus.IVirus;

public abstract class Person {
    private int m_age;
    private Location m_location;
    private Settlement m_settlement;

    public Person(){ m_age =0; m_location = null; m_settlement = null;}
    public Person(int age, Location location, Settlement settlement) {m_age = age; m_location = location; m_settlement = settlement; }
    public Person(Person other) { m_age = other.m_age; m_location = other.m_location; m_settlement = other.m_settlement;}

    public int get_Age() { return m_age; }
    public Location get_Location() { return m_location; }
    public Settlement get_Settlement() { return m_settlement; }

    @Override
    public String toString() {return "Age: " + m_age + " Location: " + m_location + "Settlement: " + m_settlement;}

    // sdfwefwgwefewfwef
    public boolean equals(Person other) { return m_age == other.m_age && m_location.equals(other.m_location); }

    public double contagionProbability() {return 1;}
    public Person contagion(IVirus virus) { return new Sick(this, 0, virus); }



}
