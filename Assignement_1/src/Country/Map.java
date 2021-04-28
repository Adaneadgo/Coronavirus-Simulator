package Country;

import Location.*;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

public class Map {

    private  final Settlement[] m_settlements;

    //C tors
    public Map(String[][] args){

         String[] neighbours = null;


        if(args[args.length - 1][0].charAt(0) == '#') {
            m_settlements = new Settlement[args.length - 1];
            neighbours = args[args.length - 1];
        }
        else
            m_settlements = new Settlement[args.length];



        String[] arg;
        for(int i = 0 ; i<args.length; i++){

            arg = args[i]; //handle the object in index i himself

            switch (arg[0])  //('Moshav', 'Kibbutz' or 'City)
            {

                case "Moshav":
                    m_settlements[i] = new Moshav(arg[1], new Location(new Point(Integer.parseInt(arg[2]), Integer.parseInt(arg[3])), new Size(Integer.parseInt(arg[4]), Integer.parseInt(arg[5]))), Integer.parseInt(arg[6]), RamzorColor.Green);
                    break;
    
                case "Kibbutz":
                    m_settlements[i] = new Kibbutz(arg[1], new Location(new Point(Integer.parseInt(arg[2]), Integer.parseInt(arg[3])), new Size(Integer.parseInt(arg[4]), Integer.parseInt(arg[5]))), Integer.parseInt(arg[6]), RamzorColor.Green);
                    break;
    
                case "City":
                    m_settlements[i] = new City(arg[1], new Location(new Point(Integer.parseInt(arg[2]), Integer.parseInt(arg[3])), new Size(Integer.parseInt(arg[4]), Integer.parseInt(arg[5]))), Integer.parseInt(arg[6]), RamzorColor.Green);
                    break;
    
            }
        }


        if(neighbours != null)
            setNeighbours(neighbours);
    }

    public Settlement[] getM_settlements() {
        return m_settlements;
    }

    //toString
    @Override
    public String toString() {
        StringBuilder str = new StringBuilder("\n");
        for(Settlement settlement: m_settlements)
            str.append(settlement).append("\n");
        return str.toString();
    }

    // Simulation Methods
    public void setSickPeopleSimulation(){
        for(Settlement settlement: m_settlements)
            settlement.setSickPeopleSimulation();
    }

    public void contagionSimulation(){
        for(Settlement settlement: m_settlements)
             settlement.contagionSimulation();
    }

    // auxiliary
    public void setNeighbours(String[] args){

        String[] tag = new String[2];

        for(Settlement settlement: m_settlements){

            List<Settlement> neighbours = new ArrayList<>();


            for(String arg: args){
                tag = arg.substring(2).split(";",0);
                Settlement neighbour = null;

                if(tag[0].equals(settlement.getM_name()))
                    neighbour = getSettlmentByName(tag[1]);


                else if(tag[1].equals(settlement.getM_name()))
                    neighbour = getSettlmentByName(tag[0]);

                if(neighbour != null)
                    neighbours.add(neighbour);
            }

            if(neighbours.size() > 0)
                settlement.setNeighbors(neighbours.toArray(new Settlement[0]));

        }

    }

    private Settlement getSettlmentByName(String name){

        for(Settlement settlement: m_settlements){

            if(settlement.getM_name().equals(name))
                return settlement;
        }

        return null;
    }


}
