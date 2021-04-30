package Country;

import Location.*;
import Location.Point;
import Population.*;
import Virus.*;

import java.awt.*;
import java.util.*;
import java.util.List;

public abstract class Settlement {

    private String name;
    private Location location;
    private final List<Person> people = new ArrayList<Person>();
    protected RamzorColor ramzorColor;
    protected double coefficient;

    private int peopleLimit;
    private int vaccinesNum = 0;
    private Settlement [] neighbors;
    private final List<Person> sicksArray = new ArrayList<Person>();
    private final List<Person> notSicksArray = new ArrayList<Person>();
    private int deathsNum = 0;

    //C tors
    public Settlement() {}
    public Settlement(String name, Location location, int peopleNum, RamzorColor ramzorColor) {

        // we use class Random for age
        Random xrand = new Random(); Random yrand = new Random();
        int y; int x;

        this.name = name;
        this.location = new Location(location);
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

        this.ramzorColor = ramzorColor;
        peopleLimit = (int)(1.3 * peopleNum);
    }

    //toString
    public String toString() { return "Name: " + name + ", Location: " + location + ", Ramzor Color: "
            + ramzorColor + "\nnum of Pepole: "+ people.size()+", contagious Percent: "
            + contagiousPercent()*100+"%.\n" +"Neighbors: " + neighborsToString() +"\n";
    }

    private String neighborsToString(){

        if(neighbors == null)
            return "No neighbors";

        StringBuilder str = new StringBuilder("");
        for(Settlement neighbor: neighbors)
            str.append(neighbor.getName() +", ");

        return str.toString();
    }

    //getters
    public String getName() {
        return name;
    }
    public Settlement[] getNeighbors() { return neighbors; }
    public Location getLocation() { return location; }
    public Color getColor(){ return ramzorColor.getColor();}

    //setters
    public void setNeighbors(Settlement[] neighbors) {
        this.neighbors = neighbors;
    }

    public void addVaccines(int amountOfVaccines) {
        this.vaccinesNum += amountOfVaccines;
    }

    //Statistics
    public String[] getStatistics(){

        String ramzor = ramzorColor.toString();
        String type = this.getClass().getSimpleName();
        String area = String.valueOf(location.getArea());
        String vaccines = String.valueOf(vaccinesNum);
        String a_p_h = String.valueOf((float)location.getArea()/people.size());
        String coff = String.valueOf(coefficient);
        String t_num = String.valueOf(people.size());
        String SicksS = String.valueOf(sicksArray.size());
        String sickPre = String.valueOf((float)sicksArray.size()/people.size());
        String deaths = String.valueOf(deathsNum);

        return new String[]{name,type, ramzor,area,a_p_h,vaccines,coff,t_num,SicksS,sickPre,deaths};
    }




    //method
    public abstract RamzorColor calculateRamzorGrade();
    public double contagiousPercent(){

        /*
        Calculates the percentage of sick people in the locality
         */
        int numSicks = sicksArray.size();
        int numPeople = people.size();

        if(numSicks != 0 && numPeople != 0)
            return (float)numSicks / (float)numPeople;

        else
            return 0;
    }
    public Point randomLocation(){ return location.getRandomPosition();}
    public boolean AddPerson(Person person){

        if(people.size() >= peopleLimit)
            return false;

        else {
            person.setM_settlement(this);
            people.add(person);
            if(person instanceof Sick)
                sicksArray.add(person);
            else
                notSicksArray.add(person);

            return true;

        }

    }
    public boolean transfertPerson(Person person, Settlement settlement){

        double p = ramzorColor.getProbability() * settlement.ramzorColor.getProbability();
        Random rand = new Random();

        if (rand.nextInt(100) < p * 100 && settlement.AddPerson(person)){
            people.remove(person);

            if(person instanceof Sick)
                sicksArray.remove(person);
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
        IVirus[] viruses = { ChineseVariant.getInstance(), BritishVariant.getInstance(), SouthAfricanVariant.getInstance() };

        //contagious
        int counter = (int)(people.size()/100);
        int total = people.size();

        for(int i = 0; i < total; i = i + 1 ){

            if(people.get(i) instanceof Sick)
                continue;

            people.set(i, people.get(i).contagion(viruses[i % 3]));
            sicksArray.add(people.get(i));
            counter --;

            if(counter == 0)
                break;

        }

        // re-calculate Ramzor Grade
        this.ramzorColor = calculateRamzorGrade();

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
               sicksArray.add(people.get(index));
           }

        }

         // re-calculate Ramzor Grade
        this.ramzorColor = calculateRamzorGrade();
    }

}
