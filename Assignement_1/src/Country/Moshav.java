package Country;

import Location.Location;

public class Moshav extends Settlement {

    //ctors
    public Moshav(){}
    public Moshav(String name, Location location, int peopleNum, RamzorColor ramzorColor){super(name, location, peopleNum, ramzorColor);}
      
    //tosting
    @Override
    public String toString() { return "Moshav " + super.toString(); }
    
    //method
    @Override
    public RamzorColor calculateRamzorGrade() {
         /*
        Return the color that define how much the city is contagious
         */


        //Calculate the constant for the equation
        double P = this.contagiousPercent();
        m_coefficient = 0.3 + 3*Math.pow(Math.pow(1.2, m_coefficient)*(P-0.35),5);
        if(m_coefficient < 0.4)
        return RamzorColor.Green;


        //Constant values
        else if (m_coefficient < 0.6)
            return RamzorColor.Yellow;

        else if(m_coefficient < 0.8)
            return RamzorColor.Orange; 
        
        else          
            return RamzorColor.Red;
    }
    
}
