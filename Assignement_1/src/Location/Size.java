package Location;

public class Size {

    private int m_height;
    private int m_width;

    //Ctors
    public Size(){}
    public Size(int height, int width) { this.m_height = height; this.m_width = width; }
    public Size(Size other) {this.m_height = other.m_height; this.m_width = other.m_width; }

    //Getters
    public int getHeight(){return m_height;}
    public int getWidth(){return m_width;}

    // tostring & equals
    @Override
    public String toString() {return m_height + "X" + m_width;} 
    public boolean equals(Size other) { return m_height == other.m_height && m_width == other.m_width; }

 
 
}
