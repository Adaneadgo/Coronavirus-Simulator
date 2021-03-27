package Population;

import Country.Settlement;
import Location.Location;
import Virus.IVirus;

public abstract class Person {
    private int m_age;
    private Location m_location;
    private Settlement m_settlement;

    public Person(){}
    public Person(int a, Location l, Settlement s){
        m_age = a;
        m_location = l;
        m_settlement =s;
    }
    public Person(Person other){
        m_age = other.m_age;
        m_location = other.m_location;
        m_settlement = other.m_settlement;
    }

    public double contagionProbability(){return 1;}

    public Person contagion(IVirus virus){
        return new Sick(this, 0, virus);
    }

    public int get_Age() {
        return m_age;
    }

}
