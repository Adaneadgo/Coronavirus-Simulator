//Adane Adgo 315721969
//Elie Bracha 204795900
package Country;

import Location.*;
import Simulation.Clock;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

public class Map implements Iterable<Settlement>{
    /**
     * Represent the map that we create
     */

    private  final Settlement[] settlements;

    //Ctor
    public Map(String[][] args){
        SettlementFactory settlementFactory = new SettlementFactory();

         String[] neighbours = null;

        if(args[args.length - 1][0].charAt(0) == '#') {
            settlements = new Settlement[args.length - 1];
            neighbours = args[args.length - 1];
        }

        else
            settlements = new Settlement[args.length];

        for(int i = 0 ; i<settlements.length; i++)
            settlements[i] = settlementFactory.creatSettlement(args[i]);

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

    @Override
    public Iterator<Settlement> iterator() {

        return (Iterator<Settlement>) Arrays.asList(settlements).iterator();
    }
}
