package Population;

import Virus.IVirus;

public class Sick extends Person {
    
    private long m_contagiousTime;
    private IVirus m_virus;

    public Sick(Person p, long t, IVirus v){
        super(p);
        m_contagiousTime = t;
        m_virus = v;
    }

    
    public Person recover() {
        return null;
    }

    public boolean tryToDie(){
        return false;
    }
}
