package Location;


public class Size {

    private int m_height;
    private int m_width;


    public Size() {m_height = 0; m_width = 0;}
    public Size(int height, int width) { this.m_height = height; this.m_width = width; }
    public Size(Size other) {this.m_height = other.m_height; this.m_width = other.m_width; }


    @Override
    public String toString() {return m_height + "X" + m_width;} 

    public boolean equals(Size other) { return m_height == other.m_height && m_width == other.m_width; }

    public int get_Height(){return m_height;}
    public int get_Width(){return m_width;}
 
}
