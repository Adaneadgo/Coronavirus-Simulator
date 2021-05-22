//Adane Adgo 315721969
//Elie Bracha 204795900
package Country;

import IO.LogFile;
import Location.*;
import Location.Point;
import Population.*;
import Simulation.Clock;
import Virus.*;

import java.awt.*;
import java.io.IOException;
import java.util.*;
import java.util.List;
import java.util.logging.Level;

public abstract class Settlement {
    /**
     * Abstract class for creating real City, Moshav, Kibbutz
     */

    private String name;
    private Location location;
    private final List<Person> people = new ArrayList<Person>();
    protected RamzorColor ramzorColor;
    protected double coefficient;

    private int peopleLimit;
    private int vaccinesNumber = 0;
    private Settlement[] neighbors;
    private final List<Sick> sicksArray = new ArrayList<Sick>();
    private final List<Person> notSicksArray = new ArrayList<Person>();
    private int deathsNumber = 0;
    private int newDeathsNumber = 0;

    //Ctors
    public Settlement() {
    }
    public Settlement(String name, Location location, int peopleNum, RamzorColor ramzorColor) {

        // we use class Random for age
        Random xrand = new Random();
        Random yrand = new Random();
        int y;
        int x;

        this.name = name;
        this.location = new Location(location);
        int age;
        for (int i = 0; i < peopleNum; i++) {

            //age validation
            do {
                x = (int) (xrand.nextGaussian() * 6 + 9);
                y = yrand.nextInt(5);
                age = 5 * x + y;
            } while (age < 0 || age > 99);

            Person newPerson = new Healthy(age, new Location(randomLocation(), new Size()), this);

            people.add(newPerson);
            notSicksArray.add(newPerson);
        }

        this.ramzorColor = ramzorColor;
        peopleLimit = (int) (1.3 * peopleNum);
    }

    //toString
    public String toString() {
        return "Name: " + name + ", Location: " + location + ", Ramzor Color: "
                + ramzorColor + "\nnum of Pepole: " + people.size() + ", contagious Percent: "
                + contagiousPercent() * 100 + "%.\n" + "Neighbors: " + neighborsToString() + "\n";
    }
    private String neighborsToString() {
        /**
         * Return String of the neighbour name
         */

        if (neighbors == null)
            return "No neighbors";

        StringBuilder str = new StringBuilder("");
        for (Settlement neighbor : neighbors)
            str.append(neighbor.getName()).append(", ");

        return str.toString();
    }

    //getters
    public String getName() {
        return name;
    }
    public String getType() {return this.getClass().getSimpleName();}
    public RamzorColor getRamzorColor() { return ramzorColor; }
    public int getArea() {return location.getArea();}
    public float getOccupancy() {return (float)people.size()/peopleLimit; }
    public int getTotalNumber() { return people.size(); }
    public double getCoefficient() { return coefficient;}
    public float getSicksRatio() { return (float)sicksArray.size()/people.size();}
    public int getVaccinesNumber() { return vaccinesNumber; }
    public int getDeathsNumber() { return deathsNumber;}

    public String[] getCsvStats(){
        return new String[]{
        };



    }
    public Color getColorCode() {
        return ramzorColor.getColor();
    }
    public int getSicksNumber(){ return sicksArray.size();}
    public int getNotSickNumber() {return notSicksArray.size(); }
    public Settlement[] getNeighbors() {
        return neighbors;
    }
    public Location getLocation() {
        return location;
    }


    //setters
    public void setNeighbors(Settlement[] neighbors) {
        this.neighbors = neighbors;
    }
    public void addVaccines(int amountOfVaccines) {
        this.vaccinesNumber += amountOfVaccines;
    }

    //method
    public abstract RamzorColor calculateRamzorGrade();
    public double contagiousPercent() {
        /**
        Calculates the percentage of sick people in the locality
         */
        int numSicks = sicksArray.size();
        int numPeople = people.size();

        if (numSicks != 0 && numPeople != 0)
            return (float) numSicks / (float) numPeople;

        else
            return 0;
    }
    public Point randomLocation() {
        return location.getRandomPosition();
    }
    public boolean AddPerson(Person person) {
        /**
         * Add person to the settlement
         */

        if (people.size() >= peopleLimit)
            return false;

        if(person == null)
            return false;

        else {
            person.setM_settlement(this);
            people.add(person);
            if (person instanceof Sick)
                sicksArray.add((Sick) person);
            else
                notSicksArray.add(person);

            return true;

        }

    }
    public boolean transfertPerson(Person person, Settlement settlement) {
        /**
         * Transfer person to a another settlement
         */

        double p = ramzorColor.getProbability() * settlement.ramzorColor.getProbability();
        Random rand = new Random();

        if (rand.nextInt(100) < p * 100 && settlement.AddPerson(person)) {
            people.remove(person);

            if (person instanceof Sick)
                sicksArray.remove(person);
            else
                notSicksArray.remove(person);

            return true;
        } else
            return false;
    }


    // Add Sicks functionality
    public void setSickPeopleSimulation() {
        /**
         * Set sick people in Simulation (1%)
         */

        // Array that handle all type of Virus
        IVirus[] viruses = {ChineseVariant.getInstance(), BritishVariant.getInstance(), SouthAfricanVariant.getInstance()};

        //contagious
        int counter = (int) (people.size() / 100);

        for (int i = 0; i < people.size(); i = i + 1) {

            if (people.get(i) instanceof Sick)
                continue;

            Sick newSick = people.get(i).contagion(viruses[i % 3]);
            people.set(i, newSick);
            sicksArray.add(newSick);
            notSicksArray.remove(people.get(i));
            counter--;

            if (counter == 0)
                break;

        }

        // re-calculate Ramzor Grade
        this.ramzorColor = calculateRamzorGrade();

    }

    //new simulation
    public void runSimulation() throws IOException {
        step1();
        step2();
        step3();
        step4();
        step5();
    }

    private synchronized void step1() {
        /**
         * Step1 : as instructed in the assignment
         * In every settlement 20% aur randomly sick
         */

        int counter = (int)sicksArray.size()/5;
        Random rand = new Random();

        List<Person> toRemove = new ArrayList<Person>();
        List<Sick> toAdd = new ArrayList<Sick>();

        for(int i = 0; i < counter; i++){
            Sick sick = sicksArray.get(rand.nextInt(sicksArray.size() - 1));
            IVirus virus = sick.getVirus();

            for(int j = 0; j< 3; j++){
                int index = rand.nextInt(notSicksArray.size() - 1);
                Person person = notSicksArray.get(index);

                if(virus.tryToContagion(sick,person) && virus.mutant() != null){
                    Sick newSick = person.contagion(virus.mutant());
                    toAdd.add(newSick);
                    toRemove.add(person);

                }

                }

            }

        people.removeAll(toRemove);
        notSicksArray.removeAll(toRemove);
        people.addAll(toAdd);
        notSicksArray.addAll(toAdd);

        this.ramzorColor = calculateRamzorGrade();


        }
    private synchronized void step2() {
        /**
         * Step2 : as instructed in the assignment
         * Set Convalescent people after 25 days
         */

        List<Sick> toRemove = new ArrayList<Sick>();

        for (Sick sick : sicksArray) {
            if (Clock.daysPass(sick.getContagiousTime()) >= 25) {

                Convalescent convalescent= new Convalescent(sick);
                toRemove.add(sick);
                notSicksArray.add(convalescent);
            }
        }

        sicksArray.removeAll(toRemove);
        this.ramzorColor = calculateRamzorGrade();

    }
    private synchronized void step3() {
        /**
         * Step3 : as instructed in the assignment
         * try to move 3% people
         */

        if (neighbors == null)
            return;

        int counter = (int) (3 * people.size()) / 100;
        Random rand = new Random();
        Settlement neighbor;
        while (counter > 0) {
            Person person = people.get(rand.nextInt(people.size() - 1));

            if(neighbors.length == 1)
                neighbor = neighbors[0];

            else
            neighbor = neighbors[rand.nextInt(neighbors.length - 1)];

            this.transfertPerson(person, neighbor);
            counter--;
        }
        this.ramzorColor = calculateRamzorGrade();

    }
    private synchronized void step4(){
        /**
         * Step4 : as instructed in the assignment
         * Vaccinate people
         */

        int length = notSicksArray.size();

        for(int i = 0; i < length; i++){

            if(vaccinesNumber <= 0)
                return;

            else if(notSicksArray.get(i) instanceof Healthy){
                notSicksArray.set(i, ((Healthy) notSicksArray.get(i)).vaccinate());
                vaccinesNumber--;
            }
        }
        this.ramzorColor = calculateRamzorGrade();

    }
    private synchronized void step5() throws IOException {

        for(Sick sick: sicksArray){
            if(sick.tryToDie()) {
                sicksArray.remove(sick);
                people.remove(sick);
                deathsNumber += 1;
                newDeathsNumber += 1;

                if(newDeathsNumber >= (int)people.size()/100 && LogFile.isInitialized()){
                    LogFile log = LogFile.getInstance();
                    assert log != null;
                    log.logger.info("\nName:" + getName() +"\nNumber of Sicks:" +sicksArray.size() +
                            "\nNumber of Deaths" +deathsNumber );
                }
            }

        }
    }





}
