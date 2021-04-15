package Country;

import Location.*;

public class Map {

    private Settlement[] m_settlements;

    //Ctors
    public Map(String[][] args){
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
                    m_settlements[i] =new City(arg[1], new Location(new Point(Integer.parseInt(arg[2]), Integer.parseInt(arg[3])), new Size(Integer.parseInt(arg[4]), Integer.parseInt(arg[5]))), Integer.parseInt(arg[6]), RamzorColor.Green);
                    break;
    
            }
        }
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

    

}
