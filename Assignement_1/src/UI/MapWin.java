//Adane Adgo 315721969
//Elie Bracha 204795900
package UI;

import Country.Map;
import Country.Settlement;
import IO.SimulationFile;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.Line2D;

public class MapWin extends JPanel {
    /**
     * Map Windows for Statistics Window
     */


    private final SimulationFile simulationFile;
    private StatisticsWin statisticsWin = null;

    //Ctor
    public MapWin(SimulationFile simulationFile){

        this.simulationFile = simulationFile;
    }

    public void paintComponent(Graphics g){
        /**
         * Create Location for settlement
         */

        if(simulationFile == null)
            return;

        super.paintComponent(g);
        wipeScreen(g);
        setLines(g);
        setRecButtons();
    }

    private void setLines(Graphics g){
        /**
         * Create Location for settlement
         */

        Graphics2D g2d = (Graphics2D)g;

        //Lines
        Settlement[] settlements = simulationFile.getMap().getSettlements();
        int x1, y1, x2, y2;

        for(Settlement settlement: settlements){
            x1 = settlement.getLocation().getPoint().getX();
            y1 = settlement.getLocation().getPoint().getY();

            Settlement[] neighbors = settlement.getNeighbors();

            if (neighbors == null)
                continue;

            for(Settlement neighbor: neighbors){
                x2 = neighbor.getLocation().getPoint().getX();
                y2 = neighbor.getLocation().getPoint().getY();

                if(settlement.getColorCode() == Color.RED)
                    System.out.println("HEY");

                Line2D basicLine = new Line2D.Double(x1, y1, x2, y2);
                DecoratorLine2D colorLine = new DecoratorLine2D(basicLine,settlement.getColorCode(),neighbor.getColorCode());
                g2d.setColor(colorLine.getColor());
                g2d.draw(colorLine);


            }

        }

    }

    private void setRecButtons(){

        Map map = simulationFile.getMap();

        Settlement[] settlements = map.getSettlements();
        int x,y,w,h;
        for(Settlement settlement: settlements) {
            x = settlement.getLocation().getPoint().getX();
            y = settlement.getLocation().getPoint().getY();
            w = settlement.getLocation().getSize().getWidth();
            h = settlement.getLocation().getSize().getHeight();

            JButton button = new JButton(settlement.getName());
            button.setLocation(x,y);
            button.setSize(w,h);
            button.setBackground(settlement.getColorCode());

            button.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    statisticsWin = new StatisticsWin(simulationFile,MapWin.this,map.getSettlementIndex(settlement));
                }
            });

            this.add(button);
        }


    }

    public Dimension getPreferredSize(){
        return new Dimension( 1000 , 1000);
    }

    public void wipeScreen(Graphics g){
        removeAll();
        g.setColor(Color.white);
        g.drawRect(0,0,1000,1000);

    }


}

