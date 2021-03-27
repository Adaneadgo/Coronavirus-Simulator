package Country;

public enum RamzorColor {
    Green(0.4), Yellow(0.6), Orange(0.8), Red(0.8);

    public final double m_color;
    
    private RamzorColor(final double d)
    {
        this.m_color = d;
    }

    @Override
    public String toString() {
        return "RamzorColor{" + this.name() + "(" + m_color + ")" +
                '}';
    }

}
