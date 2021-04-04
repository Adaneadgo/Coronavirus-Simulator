package Country;

import Location.*;
import Population.*;
import Virus.*;

import java.util.*;

public abstract class Settlement {

    private String m_name;
    private Location m_location;
    private List<Person> m_people;
    protected RamzorColor m_ramzorColor;

    //ctors
    public Settlement() {}
    public Settlement(String name, Location location, int peopleNum, RamzorColor ramzorColor)
    {

        Random xrand = new Random(); Random yrand = new Random();
        int y; int x;

        m_name = name;
        m_location = new Location(location);
        m_people = new ArrayList<Person>();
        for(int i = 0; i < peopleNum; i++){

            x = (int)(xrand.nextGaussian()* 6 + 9);
            y = yrand.nextInt(5);

            m_people.add( new Healthy( 5*x + y, new Location(randomLocation(), new Size()), this));
        }
        m_ramzorColor = ramzorColor;
    }
    public Settlement(Settlement other) { m_name = other.m_name; m_location = new Location(other.m_location); m_people = other.m_people; m_ramzorColor = other.m_ramzorColor; }

    //toString
    public String toString() { return "Name: " + m_name + ", Location: " + m_location + ", Ramzor Color: " + m_ramzorColor + "\nPopulation:" + PeopletoString(); }
    
    //method
    public abstract RamzorColor calculateRamzorGrade();
    public double contagiousPercent(){

        int sicksNum = 0;

        for(Person p : m_people){

            if(p instanceof Sick)
                sicksNum ++;
        }

        return sicksNum / m_people.size();
    }
    public Point randomLocation(){

        Random rand = new Random();

        int x = rand.nextInt(m_location.getSize().getWidth()) + m_location.getPoint().getX();
        int y = rand.nextInt(m_location.getSize().getHeight()) + m_location.getPoint().getY();
    
        return new Point(x, y);
    }
    public boolean AddPerson(Person person){

        m_people.add(person);
        return true;
    }
    public boolean transfertPerson(Person person, Settlement settlement){
        
         m_people.remove(person);
         settlement.AddPerson(person);
         return true;
    }

    //auxiliary
    private String PeopletoString(){
        StringBuilder str = new StringBuilder("\n");
        for(Person person: m_people)
            str.append(person).append("\n");
        return str.toString();

    }

    // Simulation Methods
    public void setSickPeopleSimulation(){

        IVirus[] viruses = { new ChineseVariant(), new BritishVariant(), new SouthAfricanVariant() };

        for(int i = 0; i < (int)(m_people.size() / 100); i++)
            m_people.set(i, m_people.get(i).contagion(viruses[i % 3]));

    }
    
    public void contagionSimulation(){

        Sick sick = (Sick)m_people.get(0);
        IVirus virus = sick.getVirus();
        int size = m_people.size();
        Random rand = new Random();

        for(int i = 0; i < 6; i++){

           int index = rand.nextInt(size);
           Person person = m_people.get(index);

           if(virus.tryToContagion(sick, person))
               m_people.set(index, person.contagion(virus));
  
        }
    }

}
