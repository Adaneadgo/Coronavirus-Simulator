package Country;

public enum RamzorColor {


    //All values refer to a particular color
    Red(0.1), Green(0.4), Yellow(0.6), Orange(0.8);

    public final double m_coefficient;
    
    //Ctor
    private RamzorColor(final double num){ this.m_coefficient = num;}

    @Override
    public String toString() { return this.name();}
    public double getCoefficient() { return m_coefficient; }

}
