package leidian.obj;

import leidian.obj.Explode;
import leidian.obj.Role;
import leidian.utils.GameUtils;

import java.awt.*;
import java.util.Vector;

public class ExplodeCollection extends Role {
    Vector explodeVector = new Vector();
    int index = 0;

    public ExplodeCollection() {
    }

    public void paintExplode(Graphics gImage)
    {

        for (;index<explodeVector.size();index++)
        {
            Explode explode = (Explode) explodeVector.elementAt(index);
            if (explode.mark==false){
                explode.paintSelf(gImage);
                explode.mark=true;
            }
        }
    }}
