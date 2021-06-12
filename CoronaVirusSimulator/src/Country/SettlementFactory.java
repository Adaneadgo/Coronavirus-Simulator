package Country;

import Location.*;

public class SettlementFactory {
    public Settlement creatSettlement(String[] args) {

        switch (args[0]){

            case "Moshav":
                return new Moshav(args[1], new Location(new Point(Integer.parseInt(args[2]), Integer.parseInt(args[3])), new Size(Integer.parseInt(args[4]), Integer.parseInt(args[5]))), Integer.parseInt(args[6]), RamzorColor.Green);

            case "Kibbutz":
                return new Kibbutz(args[1], new Location(new Point(Integer.parseInt(args[2]), Integer.parseInt(args[3])), new Size(Integer.parseInt(args[4]), Integer.parseInt(args[5]))), Integer.parseInt(args[6]), RamzorColor.Green);

            case "City":
                return new City(args[1], new Location(new Point(Integer.parseInt(args[2]), Integer.parseInt(args[3])), new Size(Integer.parseInt(args[4]), Integer.parseInt(args[5]))), Integer.parseInt(args[6]), RamzorColor.Green);

            default:
                return null;
        }
    }
}
