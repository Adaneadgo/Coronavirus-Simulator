package Population;


public class Healthy extends Person {

    public Healthy(){}

    @Override
    public double contagionProbability() { return 1; }
    
    public Person vaccinate(){ return new Vaccinated(this);}
    
}
