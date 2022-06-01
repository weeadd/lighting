package leidian.obj;

import java.util.EventObject;

public class ExplodeEvent extends EventObject {
    /**
     * Constructs a prototypical Event.
     *
     * @param source The object on which the Event initially occurred.
     * @throws IllegalArgumentException if source is null.
     */

    boolean isHit = false;

    public ExplodeEvent(Object source,boolean isHit0) {
        super(source);
        boolean isHit=isHit0;
    }
}
