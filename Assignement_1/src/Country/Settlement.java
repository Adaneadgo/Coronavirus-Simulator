package Country;

import Location.*;
import Population.*;
import Simulation.Clock;
import Virus.*;

import java.util.*;

public abstract class Settlement {

    private String m_name;
    private Location m_location;
    private List<Person> m_people;
    protected RamzorColor m_ramzorColor;
    protected double m_coefficient;

    //ctors
    public Settlement() {}
    public Settlement(String name, Location location, int peopleNum, RamzorColor ramzorColor)
    {

        // we use class Random for age
        Random xrand = new Random(); Random yrand = new Random();
        int y; int x;

        m_name = name;
        m_location = new Location(location);
        m_people = new ArrayList<Person>();
        int age;
        for(int i = 0; i < peopleNum; i++){
            
           //age validation
            do{ 
                x = (int)(xrand.nextGaussian()* 6 + 9);
                y = yrand.nextInt(5);
                age = 5*x + y;
            } while(age < 0 || age > 99);

            m_people.add( new Healthy( age, new Location(randomLocation(), new Size()), this));
        }

        m_ramzorColor = ramzorColor;
    }
    public Settlement(Settlement other) { m_name = other.m_name; m_location = new Location(other.m_location); m_people = other.m_people; m_ramzorColor = other.m_ramzorColor; }

    //toString
    public String toString() { return "Name: " + m_name + ", Location: " + m_location + ", Ramzor Color: " + m_ramzorColor + "\nnum of Pepole: "+m_people.size()+", contagious Percent: " + contagiousPercent()*100+"%.\n"; }
    
    //method
    public abstract RamzorColor calculateRamzorGrade();
    public double contagiousPercent(){

        /*
        Calculates the percentage of sick people in the locality
         */
        double sicksNum = 0;

        for(Person p : m_people){

            if(p instanceof Sick) // if p if Sick (downcasting)
                sicksNum ++;
        }

        return sicksNum / m_people.size();
    }
    public Point randomLocation(){ return m_location.getRandomPosition();}
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
        //In order to do Append
        StringBuilder str = new StringBuilder("\n");
        for(Person person: m_people)
            str.append(person).append("\n");
        return str.toString();

    }
  


    // Simulation Methods
    public void setSickPeopleSimulation(){

        // Array that handle all type of Virus
        IVirus[] viruses = { new ChineseVariant(), new BritishVariant(), new SouthAfricanVariant() };

        //contagious and tik of Clock
        for(int i = 0; i < (int)(m_people.size() / 100); i++){
            m_people.set(i, m_people.get(i).contagion(viruses[i % 3]));
            Clock.nextTick();
        }

        // re-calculate Ramzor Grade
        calculateRamzorGrade();

    }
    
    public void contagionSimulation(){


        // all variable needed for method
        Sick sick = (Sick)m_people.get(0);
        IVirus virus = sick.getVirus();
        int size = m_people.size();
        Random rand = new Random();

        //make the actual contagious
        for(int i = 0; i < 6; i++){

           int index = rand.nextInt(size);
           Person person = m_people.get(index);

           if(virus.tryToContagion(sick, person))
               m_people.set(index, person.contagion(virus));

            Clock.nextTick(); // tik
        }

         // re-calculate Ramzor Grade
        this.m_ramzorColor = calculateRamzorGrade();
    }

}
