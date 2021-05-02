package Country;

import Location.Location;

public class City extends Settlement {
    /**
     * Represent Settlement of type "City"
     */

    //ctors
    public City(){} // default Constructor
    public City(String name, Location location, int peopleNum, RamzorColor ramzorColor){super(name, location, peopleNum, ramzorColor);}

    //toString
    @Override
    public String toString() { return "City " + super.toString(); }

    //methos
    @Override
    public RamzorColor calculateRamzorGrade() {
        /**
         * Return: Return the color that define how much the city is contagious
         */

        //Calculate the constant for the equation
        double P = this.contagiousPercent();
        coefficient = 0.2 * Math.pow(4, 1.25*P);


        //Constant for equation
        if(coefficient < 0.4)
        return RamzorColor.Green;

        else if (coefficient < 0.6)
            return RamzorColor.Yellow;

        else if(coefficient < 0.8)
           return RamzorColor.Orange; 
        
        else          
            return RamzorColor.Red;
    
}
}