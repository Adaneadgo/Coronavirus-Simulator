package UI;

import Country.Map;
import Country.Settlement;

import javax.swing.*;
import java.awt.*;

public class MapWin extends JPanel {

    private Map map;

    public MapWin(Map map){
        this.map = map;
    }

    public void paintComponent(Graphics g){
        super.paintComponent(g);

        LINES(g);
        RECTS(g);





    }

    private void LINES(Graphics g){
        //Lines
        Settlement[] settlements = map.getM_settlements();
        int x1,y1,x2,y2;

        for(Settlement settlement: settlements){
            x1 = settlement.getM_location().getPoint().getX();
            y1 = settlement.getM_location().getPoint().getY();

            Settlement[] neighbors = settlement.getNeighbors();

            if (neighbors == null)
                continue;

            for(Settlement neighbor: neighbors){
                x2 = neighbor.getM_location().getPoint().getX();
                y2 = neighbor.getM_location().getPoint().getY();

                g.drawLine(x1,y1,x2,y2);

            }

        }

    }
    private  void RECTS(Graphics g){
        Settlement [] settlements = map.getM_settlements();
        int x,y,w,h;
        for(Settlement settlement: settlements){
            x = settlement.getM_location().getPoint().getX();
            y = settlement.getM_location().getPoint().getY();
            w = settlement.getM_location().getSize().getWidth();
            h = settlement.getM_location().getSize().getHeight();
            g.setColor(settlement.getColor());
            g.fillRect(x,y,w,h);
            g.setColor(Color.BLACK);
            g.drawString(settlement.getM_name(),x ,y+ h/2 );


        }
    }

    public Dimension getPreferredSize(){
        return new Dimension( 1000 , 1000);
    }
}
