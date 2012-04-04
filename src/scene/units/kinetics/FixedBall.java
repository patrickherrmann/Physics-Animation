/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package scene.units.kinetics;

import animation.Viewport;
import java.awt.Color;
import java.awt.Point;
import java.awt.geom.Point2D;

/**
 *
 * @author patrick
 */
public class FixedBall extends FixedUnit {

    private double radius = 10;

    public FixedBall(double x, double y) {
        super(x, y);
    }

    public FixedBall(Point2D.Double location) {
        super(location);
    }

    public void paint(Viewport v) {
        Point2D.Double point = getLocation();
        if (!v.isInBounds(point)) return;
        point.x -= radius;
        point.y += radius;
        Point pixel = v.getPixel(point);
        int diameter = v.getPixelLength(radius * 2);
        v.getGraphics().setColor(charge > 0 ? Color.RED : Color.BLUE);
        v.getGraphics().fillOval(pixel.x, pixel.y, diameter, diameter);
    }
}
