package Virus;

import java.util.Random;

public class VirusManager {
    private static VirusManager instance;
    private final Boolean[][] variants;
    private final IVirus[] virus;


    private VirusManager() {
        variants = new Boolean[3][3];
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++)
                variants[i][j] = i == j;
        }
        virus = new IVirus[]{ChineseVariant.getInstance(), BritishVariant.getInstance(), SouthAfricanVariant.getInstance()};
    }

    public Boolean[][] getTable() {
        return variants;
    }

    public static VirusManager getInstance() {
        if (instance == null)
            instance = new VirusManager();
        return instance;
    }

    public void addMutation(int i, int j) {
        variants[i][j] = true;
    }

    public void removeMutation(int i, int j) {
        variants[i][j] = false;
    }

    public IVirus getMutation(IVirus virus) {

      if(virus instanceof ChineseVariant)
          return getRandomMutation(0);

      else if (virus instanceof BritishVariant)
          return getRandomMutation(1);


      else if( virus instanceof SouthAfricanVariant)
          return getRandomMutation(2);

      else
          return null;


    }

    private IVirus getRandomMutation(int i) {


       if(variants[i][0])
           return virus[0];

       else if(variants[i][1])
           return virus[1];

       else if(variants[i][2])
           return virus[2];

       else
           return null;

    }

    public IVirus getRandomVirus(){
        return virus[new Random().nextInt(3)];
    }

}
