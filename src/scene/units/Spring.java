/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package scene.units;

import animation.Viewport;
import java.awt.Color;
import java.awt.Point;
import scene.Vector;
import scene.units.kinetics.KineticUnit;

/**
 *
 * @author patrick
 */
public class Spring extends Unit {

    private KineticUnit a;
    private KineticUnit b;
    private double k; // String constant
    private double length;

    public Spring(KineticUnit a, KineticUnit b, double length, double k) {
        this.a = a;
        this.b = b;
        this.length = length;
        this.k = k;
    }

    public void update() {
        double distance = a.getLocation().distance(b.getLocation());
        double magnitude = (distance - length) * k;
        Vector force = new Vector(a.getLocation(), b.getLocation());
        a.applyForce(force.setMagnitude(magnitude));
        b.applyForce(force.setMagnitude(-magnitude));
    }

    public void paint(Viewport v) {
        Point pa = v.getPixel(a.getLocation());
        Point pb = v.getPixel(b.getLocation());
        v.getGraphics().setColor(Color.BLACK);
        v.getGraphics().drawLine(pa.x, pa.y, pb.x, pb.y);
    }

}
