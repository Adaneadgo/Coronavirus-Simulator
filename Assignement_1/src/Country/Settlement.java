package Country;

import Location.*;
import Population.*;

import java.util.*;

public abstract class Settlement {

    private String m_name;
    private Location m_location;
    private List<Person> m_people;
    protected RamzorColor m_ramzorColor;



    // tostring
    @Override
    public String toString() { return "Name: " + m_name + ", Location: " + m_location + ", Ramzor Color: " + m_ramzorColor + "People: " + m_people; }


    //ctors
    public Settlement() {}
    public Settlement(String name, Location location, int peopleNum, RamzorColor ramzorColor)
    {

        Random xrand = new Random(); Random yrand = new Random();
        int y; int x;

        m_name = name;
        m_location = new Location(location);
        m_people = new ArrayList<Person>();
        for(int i = 0; i < peopleNum; i++){

            x = (int)(xrand.nextGaussian()* 6 + 9);
            y = yrand.nextInt(5);

            m_people.add( new Healthy( 5*x + y, new Location(randomLocation(), new Size()), this));
        }


        m_ramzorColor = ramzorColor;

    }
    
    public Settlement(Settlement other) { m_name = other.m_name; m_location = new Location(other.m_location); m_people = other.m_people; m_ramzorColor = other.m_ramzorColor; }


    //method
    public abstract RamzorColor calculateRamzorGrade();
    public double contagiousPercent(){

        int sicksNum = 0;

        for(Person p : m_people){

            if(p instanceof Sick)
                sicksNum ++;
        }

        return sicksNum / m_people.size();
    }
    public Point randomLocation(){

        Random rand = new Random();

        int x = rand.nextInt(m_location.getSize().getWidth()) + m_location.getPoint().getX();
        int y = rand.nextInt(m_location.getSize().getHeight()) + m_location.getPoint().getY();
    
        return new Point(x, y);
    }
    public boolean AddPerson(Person person){

        m_people.add(person);
        return true;
    }
    public boolean transfertPerson(Person person, Settlement settlement){
        
         m_people.remove(person);
         settlement.AddPerson(person);
         return true;
    }






}
