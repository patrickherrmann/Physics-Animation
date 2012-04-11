/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package scene.units.kinetics;

import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;
import java.util.HashSet;
import java.util.Set;
import scene.Vector;

/**
 *
 * @author patrick
 */
public class Kinetics {

    private Rectangle2D.Double boundingBox;
    private Set<KineticUnit> kunits = new HashSet<KineticUnit>();

    private static final double G = 100.0;
    private static final double K = 100.0;
    private static final Vector FG = new Vector(0, -0.1);

    public boolean isInBounds(KineticUnit kunit) {
        if (kunit == null) throw new IllegalArgumentException();
        if (boundingBox == null) return true;
        return boundingBox.contains(kunit.getLocation());
    }

    public void setBoundingBox(Rectangle2D.Double boundingBox) {
        this.boundingBox = boundingBox;
    }

    public Rectangle2D.Double getBoundingBox() {
        return boundingBox;
    }

    public void addToKineticUnitSet(KineticUnit kunit) {
        kunits.add(kunit);
    }
    
    public void removeFromKineticUnitSet(KineticUnit kunit) {
        kunits.remove(kunit);
    }

    public void enforceBoundingBox(KineticUnit kunit) {
        if (!isInBounds(kunit)) {
            kunit.destroy();
        }
    }

    public void applyGravitation(KineticUnit kunit) {
        Vector netAcc = new Vector();
        Point2D.Double location = kunit.getLocation();
        for (KineticUnit other : kunits) {
            if (other == kunit) continue;
            double distance2 = location.distanceSq(other.getLocation());
            if (distance2 < 400) continue;
            double magnitude = G * (kunit.getMass() + other.getMass()) / distance2;
            Vector vector = new Vector(location, other.getLocation());
            vector = vector.setMagnitude(magnitude);
            netAcc = netAcc.plus(vector);
        }
        kunit.applyAcceleration(netAcc);
    }

    public void applyEletromagneticForce(KineticUnit kunit) {
        Vector netForce = new Vector();
        Point2D.Double location = kunit.getLocation();
        for (KineticUnit other : kunits) {
            if (other == kunit) continue;
            double distance2 = location.distanceSq(other.getLocation());
            if (distance2 < 400) continue;
            double magnitude = -K * (kunit.getCharge() * other.getCharge()) / distance2;
            Vector vector = new Vector(location, other.getLocation());
            vector = vector.setMagnitude(magnitude);
            netForce = netForce.plus(vector);
        }
        kunit.applyAcceleration(netForce);
    }

    public void applyDownwardGravity(KineticUnit kunit) {
        kunit.applyAcceleration(FG);
    }
    
    public void applyDamping(KineticUnit kunit) {
        kunit.applyForce(kunit.getVelocity().getOpposite().setMagnitude(0.01));
    }
    
}
