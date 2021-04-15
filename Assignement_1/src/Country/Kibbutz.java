package Country;

import Location.Location;

public class Kibbutz extends Settlement {


    //ctors
    public Kibbutz(){}
    public Kibbutz(String name, Location location, int peopleNum, RamzorColor ramzorColor){super(name, location, peopleNum, ramzorColor);}
    
    //toString
    @Override
    public String toString() { return "Kibutz " + super.toString(); }

    //methods
    @Override
    public RamzorColor calculateRamzorGrade() {
        /*
        Return the color that define how much the city is contagious
         */

        double C = m_ramzorColor.getCoefficient();
        double P = this.contagiousPercent();

        double Coefficient = 0.45 + Math.pow(Math.pow(1.5, C)*(P-0.4),3);

        if(Coefficient < 0.1)
            return RamzorColor.Red;
        else if(Coefficient < 0.4)
            return RamzorColor.Green;
        else if (Coefficient < 0.6)
            return RamzorColor.Yellow;
        else          
            return RamzorColor.Orange;
    }
    
}
