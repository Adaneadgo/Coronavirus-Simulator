package Country;

public enum RamzorColor {

    Green(0.4), Yellow(0.6), Orange(0.8), Red(0.8);

    public final double m_color;
    
    //Ctor
    private RamzorColor(final double num){ this.m_color = num;}

    @Override
    public String toString() { return "RamzorColor: " + this.name();}

}
