package Country;

import Location.Location;

public class City extends Settlement {

    //ctors
    public City(){} // default Constructor
    public City(String name, Location location, int peopleNum, RamzorColor ramzorColor){super(name, location, peopleNum, ramzorColor);}

    //toString
    @Override
    public String toString() { return "City " + super.toString(); }

    //methos
    @Override
    public RamzorColor calculateRamzorGrade() {
        /*
        Return the color that define how much the city is contagious
         */

        double P = this.contagiousPercent();
        m_coefficient = 0.2 * Math.pow(4, 1.25*P);
       
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