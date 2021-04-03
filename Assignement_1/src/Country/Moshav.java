package Country;

import Location.Location;
import Population.Person;

import java.util.List;

public class Moshav extends Settlement {

    public Moshav(){}
    public Moshav(String name, Location location, int peopleNum, RamzorColor ramzorColor){super(name, location, peopleNum, ramzorColor);}
      

    @Override
    public String toString() { return "Moshav "; }
    
    @Override
    public RamzorColor calculateRamzorGrade() {

        double C = m_ramzorColor.getCoefficient();
        double P = this.contagiousPercent();

        double c = 0.3 + 3*Math.pow(Math.pow(1.2, C)*(P-0.35),5);

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
