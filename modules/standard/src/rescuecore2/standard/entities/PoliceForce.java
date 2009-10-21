package rescuecore2.standard.entities;

import rescuecore2.worldmodel.Entity;
import rescuecore2.worldmodel.EntityID;

/**
   The PoliceForce object.
 */
public class PoliceForce extends Human {
    /**
       Construct a PoliceForce object with entirely undefined values.
       @param id The ID of this entity.
    */
    public PoliceForce(EntityID id) {
        super(id, StandardEntityURN.POLICE_FORCE);
    }

    /**
       PoliceForce copy constructor.
       @param other The PoliceForce to copy.
     */
    public PoliceForce(PoliceForce other) {
        super(other);
    }

    @Override
    protected Entity copyImpl() {
        return new PoliceForce(getID());
    }
}