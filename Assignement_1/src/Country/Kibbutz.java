package Country;

import Location.Location;

public class Kibbutz extends Settlement {


    public Kibbutz(){}
    public Kibbutz(String name, Location location, int peopleNum, RamzorColor ramzorColor){super(name, location, peopleNum, ramzorColor);}
    public String toString() { return "Kibutz " + super.toString(); }

    @Override
    public RamzorColor calculateRamzorGrade() {

        double C = m_ramzorColor.getCoefficient();
        double P = this.contagiousPercent();

        double c = 0.45 + Math.pow(Math.pow(1.5, C)*(P-0.4),3);

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
