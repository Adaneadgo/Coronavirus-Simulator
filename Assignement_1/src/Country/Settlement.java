package Country;

import Location.*;
import Location.Point;
import Population.*;
import Simulation.Clock;
import Virus.*;

import java.awt.*;
import java.util.*;
import java.util.List;

public abstract class Settlement {

    private String m_name;
    private Location m_location;
    private final List<Person> people = new ArrayList<Person>();
    protected RamzorColor m_ramzorColor;
    protected double m_coefficient;

    private int limitNumOfPeople;
    private int amountOfVaccines = 0;
    private Settlement [] neighbors;
    private final List<Person> SicksArray = new ArrayList<Person>();
    private final List<Person> notSicksArray = new ArrayList<Person>();

    //C tors
    public Settlement() {}
    public Settlement(String name, Location location, int peopleNum, RamzorColor ramzorColor) {

        // we use class Random for age
        Random xrand = new Random(); Random yrand = new Random();
        int y; int x;

        m_name = name;
        m_location = new Location(location);
        int age;
        for(int i = 0; i < peopleNum; i++){
            
           //age validation
            do{ 
                x = (int)(xrand.nextGaussian()* 6 + 9);
                y = yrand.nextInt(5);
                age = 5*x + y;
            } while(age < 0 || age > 99);

            Person newPerson = new Healthy(age, new Location(randomLocation(), new Size()), this);

            people.add(newPerson);
            notSicksArray.add(newPerson);
        }

        m_ramzorColor = ramzorColor;
        limitNumOfPeople = (int)(1.3 * peopleNum);
    }

    //toString
    public String toString() { return "Name: " + m_name + ", Location: " + m_location + ", Ramzor Color: "
            + m_ramzorColor + "\nnum of Pepole: "+ people.size()+", contagious Percent: "
            + contagiousPercent()*100+"%.\n" +"Neighbors: " + neighborsToString() +"\n";
    }

    private String neighborsToString(){

        if(neighbors == null)
            return "No neighbors";

        StringBuilder str = new StringBuilder("");
        for(Settlement neighbor: neighbors)
            str.append(neighbor.getM_name() +", ");

        return str.toString();
    }

    //getters
    public String getM_name() {
        return m_name;
    }
    public Settlement[] getNeighbors() { return neighbors; }
    public Location getM_location() { return m_location; }
    public Color getColor(){ return m_ramzorColor.getColor();}

    //setters
    public void setNeighbors(Settlement[] neighbors) {
        this.neighbors = neighbors;
    }

    //Statistics
    public String getStatisticsCSV(){
        return m_name + "," + people.size() + "," + m_ramzorColor;
    }

    //method
    public abstract RamzorColor calculateRamzorGrade();
    public double contagiousPercent(){

        /*
        Calculates the percentage of sick people in the locality
         */
        int numSicks = SicksArray.size();
        int numPeople = people.size();

        if(numSicks != 0 && numPeople != 0)
            return (float)numSicks / (float)numPeople;

        else
            return 0;
    }
    public Point randomLocation(){ return m_location.getRandomPosition();}
    public boolean AddPerson(Person person){

        if(people.size() >= limitNumOfPeople)
            return false;

        else {
            person.setM_settlement(this);
            people.add(person);
            if(person instanceof Sick)
                SicksArray.add(person);
            else
                notSicksArray.add(person);

            return true;

        }

    }
    public boolean transfertPerson(Person person, Settlement settlement){

        double p = m_ramzorColor.getProbability() * settlement.m_ramzorColor.getProbability();
        Random rand = new Random();

        if (rand.nextInt(100) < p * 100 && settlement.AddPerson(person)){
            people.remove(person);

            if(person instanceof Sick)
                SicksArray.remove(person);
            else
                notSicksArray.remove(person);

            return true;
        }

        else
            return false;
    }


    // Simulation Methods
    public void setSickPeopleSimulation(){

        // Array that handle all type of Virus
        IVirus[] viruses = { new ChineseVariant(), new BritishVariant(), new SouthAfricanVariant() };

        //contagious
        for(int i = 0; i < (int)(people.size() / 100); i++){
            people.set(i, people.get(i).contagion(viruses[i % 3]));
            SicksArray.add(people.get(i));

        }

        // re-calculate Ramzor Grade
        this.m_ramzorColor = calculateRamzorGrade();

    }
    public void contagionSimulation(){
        // all variable needed for method
        Sick sick = (Sick) people.get(0);
        IVirus virus = sick.getVirus();
        int size = people.size();
        Random rand = new Random();

        //make the actual contagious
        for(int i = 0; i < 6; i++){

           int index = rand.nextInt(size);
           Person person = people.get(index);

           if(virus.tryToContagion(sick, person)) {
               people.set(index, person.contagion(virus));
               SicksArray.add(people.get(index));
           }

        }

         // re-calculate Ramzor Grade
        this.m_ramzorColor = calculateRamzorGrade();
    }

}
