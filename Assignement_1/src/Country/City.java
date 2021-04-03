package Country;

import Location.Location;

public class City extends Settlement {


    public City(){}
    public City(String name, Location location, int peopleNum, RamzorColor ramzorColor){super(name, location, peopleNum, ramzorColor);}

    @Override
    public String toString() { return "City " + super.toString(); }

    @Override
    public RamzorColor calculateRamzorGrade() {

        double P = this.contagiousPercent();
        double c = 0.2 * Math.pow(4, 1.25*P);

        if(c < 0.1)
        return RamzorColor.Red;
        else if(c < 0.4)
            return RamzorColor.Green;
        else if (c < 0.6)
            return RamzorColor.Yellow;
        else          
            return RamzorColor.Orange;
    }
    
}
