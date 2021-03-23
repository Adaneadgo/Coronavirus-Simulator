package Location;

import java.util.Objects;

public class Location {

    private Point m_position;
    private Size m_size;

    public Location(Point p, Size s)
    {
        m_position = new Point(p);
        m_size = new Size(s);
    }

    public Point get_Point(){ return m_position;}
    public Size get_Size(){return m_size;}

    public boolean equals(Location l)
    {
        return this.m_position.equals(l.get_Point()) && this.m_size.equals(l.get_Size());
    }

    @Override
    public String toString() {
        return "Location{" + '\n' +
                "Position: " + m_position.toString() + '\n' +
                "Size: " + m_size.toString() + '\n' +
                '}';
    }
}
