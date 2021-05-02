package Country;

import Location.*;
import Simulation.Clock;

import java.util.ArrayList;
import java.util.List;

public class Map {
    /**
     * Represent the map that we create
     */

    private  final Settlement[] settlements;

    //Ctors
    public Map(String[][] args){

         String[] neighbours = null;


        if(args[args.length - 1][0].charAt(0) == '#') {
            settlements = new Settlement[args.length - 1];
            neighbours = args[args.length - 1];
        }
        else
            settlements = new Settlement[args.length];



        String[] arg;
        for(int i = 0 ; i<args.length; i++){

            arg = args[i]; //handle the object in index i himself

            switch (arg[0])  //('Moshav', 'Kibbutz' or 'City)
            {

                case "Moshav":
                    settlements[i] = new Moshav(arg[1], new Location(new Point(Integer.parseInt(arg[2]), Integer.parseInt(arg[3])), new Size(Integer.parseInt(arg[4]), Integer.parseInt(arg[5]))), Integer.parseInt(arg[6]), RamzorColor.Green);
                    break;
    
                case "Kibbutz":
                    settlements[i] = new Kibbutz(arg[1], new Location(new Point(Integer.parseInt(arg[2]), Integer.parseInt(arg[3])), new Size(Integer.parseInt(arg[4]), Integer.parseInt(arg[5]))), Integer.parseInt(arg[6]), RamzorColor.Green);
                    break;
    
                case "City":
                    settlements[i] = new City(arg[1], new Location(new Point(Integer.parseInt(arg[2]), Integer.parseInt(arg[3])), new Size(Integer.parseInt(arg[4]), Integer.parseInt(arg[5]))), Integer.parseInt(arg[6]), RamzorColor.Green);
                    break;
    
            }
        }


        if(neighbours != null)
            setNeighbours(neighbours);
    }

    public Settlement[] getSettlements() { return settlements; }

    //toString
    @Override
    public String toString() {
        StringBuilder str = new StringBuilder("\n");
        for(Settlement settlement: settlements)
            str.append(settlement).append("\n");
        return str.toString();
    }

    // Simulation Methods
    public void setSickPeopleSimulation(){
        /**
         * In the Simulation we create Sick people
         */
        for(Settlement settlement: settlements)
            settlement.setSickPeopleSimulation();
    }

    public void contagionSimulation(){
        /**
         * In the Simulation we try to contagion
         */
        for(Settlement settlement: settlements)
             settlement.contagionSimulation();
    }

    // auxiliary
    public void setNeighbours(String[] args){
        /**
         * When we read from file we set Neighbours between Settlement
         */

        String[] tag = new String[2];

        for(Settlement settlement: settlements){

            List<Settlement> neighbours = new ArrayList<Settlement>();
            String currName = settlement.getName();

            for(String arg: args){
                tag = arg.substring(2).split(";",0);
                Settlement neighbour = null;

                if(tag[0].equals(currName))
                    neighbour = getSettlementByName(tag[1]);


                else if(tag[1].equals(currName))
                    neighbour = getSettlementByName(tag[0]);

                if(neighbour != null)
                    neighbours.add(neighbour);
            }

            if(neighbours.size() > 0)
                settlement.setNeighbors(neighbours.toArray(new Settlement[0]));

        }

    }

    public Settlement getSettlementByName(String name){
        /**
         * Return the settlement by the given name
         */

        for(Settlement settlement: settlements){

            if(settlement.getName().equals(name))
                return settlement;
        }

        return null;
    }


    public int getSettlementIndex(Settlement settlement){
        /**
         * Return the Index by the given Settlement
         */
        for(int i = 0; i<settlements.length; i++){
            if(settlement == settlements[i])
                return i;
        }

        return -1;

    }

    // New Simulation
    public void Simulation()  {
        /**
         * Run Single simulation
         */
        for(Settlement settlement: settlements){
            settlement.step1();
            settlement.step2();
            settlement.step3();
            settlement.step4();
        }
    }
}
