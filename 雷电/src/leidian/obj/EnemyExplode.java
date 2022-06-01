package leidian.obj;

import java.awt.*;

public class EnemyExplode implements ExplodeListener{
    @Override
    public void createExplode(int shootAtx, int shootAty, ExplodeCollection explodeCollection, ExplodeEvent e, Graphics gImage) {

            explodeCollection.explodeVector.add(new Explode(shootAtx,shootAty,explodeCollection,gImage));

           // Explode explode = new Explode(shootAtx, shootAty,explodeCollection);

    }

}
