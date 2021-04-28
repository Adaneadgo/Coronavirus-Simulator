package UI;

import Country.Map;

import javax.swing.*;
import java.awt.*;

public class MapWin extends JPanel {

    private Map map;

    public MapWin(Map map){
        this.map = map;
    }

    public void paintComponent(Graphics g){
        super.paintComponent(g);
        g.drawRect(100,50,1100,500);
    }

    public Dimension getPreferredSize(){
        return new Dimension( 1000 , 1000);
    }
}
