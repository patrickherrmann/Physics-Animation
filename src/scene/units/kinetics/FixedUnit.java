/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package scene.units.kinetics;

import animation.Viewport;
import java.awt.geom.Point2D;

/**
 *
 * @author patrick
 */
public abstract class FixedUnit extends KineticUnit {

    public FixedUnit(double x, double y) {
        super(x, y);
    }

    public FixedUnit(Point2D.Double location) {
        super(location);
    }

    public abstract void paint(Viewport v);

    @Override
    public void update() {
        
    }
}
