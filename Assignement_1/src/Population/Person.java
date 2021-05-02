package Population;

import Country.Settlement;
import Location.Location;
import Location.Point;
import Virus.IVirus;

public abstract class Person {

    /**
    This class is abstract because we handle only Healthy, Sick, Convalescent, Vaccinated people
     */
    private int m_age;
    private Location m_location;
    private Settlement m_settlement;


    //Ctors
    public Person(){}
    public Person(int age, Location location, Settlement settlement) {m_age = age; m_location = location; m_settlement = settlement; }
    public Person(Person other) { m_age = other.m_age; m_location = other.m_location; m_settlement = other.m_settlement;}

    //Getters
    public int getAge() { return m_age; }
    public Location getLocation() { return m_location; }
    public Settlement getSettlement() { return m_settlement; }

    //setters


    public void setM_settlement(Settlement m_settlement) {
        this.m_settlement = m_settlement;
    }

    // tostring & equals
    @Override
    public String toString() {return "Age: " + m_age + ", Location: " + m_location;}
    public boolean equals(Person other) { return m_age == other.m_age && m_location.equals(other.m_location); }

    //Methods
    public abstract double contagionProbability();
    public Sick contagion(IVirus virus) { return new Sick(this, virus.mutant()); }

    //auxiliary
    public Point getPoint(){ return this.m_location.getPoint();}



}
