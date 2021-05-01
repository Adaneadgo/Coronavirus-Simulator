package UI;

import Country.Map;
import Country.Settlement;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MapWin extends JPanel {

    private final Map map;
    private StatisticsWin statsWin = null;

    public MapWin(Map map){
        this.map = map;
    }

    public void paintComponent(Graphics g){

        if(map == null)
            return;

        super.paintComponent(g);
        LINES(g);
        RECTS(g);
        setButtons();


    }

    private void LINES(Graphics g){

        //Lines
        Settlement[] settlements = map.getSettlements();
        int x1,y1,x2,y2;

        for(Settlement settlement: settlements){
            x1 = settlement.getLocation().getPoint().getX();
            y1 = settlement.getLocation().getPoint().getY();

            Settlement[] neighbors = settlement.getNeighbors();

            if (neighbors == null)
                continue;

            for(Settlement neighbor: neighbors){
                x2 = neighbor.getLocation().getPoint().getX();
                y2 = neighbor.getLocation().getPoint().getY();

                g.drawLine(x1,y1,x2,y2);

            }

        }

    }
    private  void RECTS(Graphics g){

        Settlement [] settlements = map.getSettlements();
        int x,y,w,h;
        for(Settlement settlement: settlements){
            x = settlement.getLocation().getPoint().getX();
            y = settlement.getLocation().getPoint().getY();
            w = settlement.getLocation().getSize().getWidth();
            h = settlement.getLocation().getSize().getHeight();
            g.setColor(settlement.getColor());
            g.fillRect(x,y,w,h);

            g.setColor(Color.BLACK);
            g.drawString(settlement.getName(),x ,y+ h/2 );

        }
    }
    private void setButtons(){

        Settlement [] settlements = map.getSettlements();
        int x,y,w,h;
        for(Settlement settlement: settlements) {
            x = settlement.getLocation().getPoint().getX();
            y = settlement.getLocation().getPoint().getY();
            w = settlement.getLocation().getSize().getWidth();
            h = settlement.getLocation().getSize().getHeight();

            JButton button = new JButton();
            button.setLocation(x,y);
            button.setSize(w,h);
            button.setOpaque(false);
            button.setContentAreaFilled(false);
            button.setBorderPainted(false);

            button.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    statsWin = new StatisticsWin(map,MapWin.this,map.getSettlementIndex(settlement));
                }
            });

            this.add(button);
        }


    }

    public Dimension getPreferredSize(){
        return new Dimension( 1000 , 1000);
    }


    @Override
    public void repaint() {
        super.repaint();

        if(statsWin != null)
            statsWin.refreshStatsWin();

    }
}

