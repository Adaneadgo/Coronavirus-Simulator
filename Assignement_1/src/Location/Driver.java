package Location;

public class Driver {
    public static void main(String[] args) {
        Point p = new Point(3,67);
        Size s = new Size(6,98);

        Point P1 = new Point(3,6);
        Size S1= new Size(6,98);
        Location l = new Location(p,s);
        Location l1 = new Location(P1,S1);


        System.out.println(l.equals(l1));
    }
}
