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

        double P = this.contagiousPercent();
        m_coefficient = 0.45 + Math.pow(Math.pow(1.5, m_coefficient)*(P-0.4),3);


         if(m_coefficient < 0.4)
            return RamzorColor.Green;

        else if (m_coefficient < 0.6)
            return RamzorColor.Yellow;

        else if(m_coefficient < 0.8)
            return RamzorColor.Orange; 
            
        else          
            return RamzorColor.Red;
    }
    
}
