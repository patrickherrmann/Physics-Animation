/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package scene.units.kinetics;

import java.awt.geom.Point2D;
import scene.Vector;
import scene.units.Unit;

/**
 *
 * @author patrick
 */
public abstract class KineticUnit extends Unit {

    public static Kinetics kinetics = new Kinetics();
    
    protected double x;
    protected double y;
    protected double vx;
    protected double vy;
    protected double mass = 1;
    protected double charge = 0;

    public KineticUnit(double x, double y) {
        this.x = x;
        this.y = y;
        addToKineticUnitSet();
    }

    public KineticUnit(Point2D.Double location) {
        this.x = location.x;
        this.y = location.y;
        addToKineticUnitSet();
    }
    
    private void addToKineticUnitSet() {
        kinetics.addToKineticUnitSet(this);
    }
    
    private void removeFromKineticUnitSet() {
        kinetics.removeFromKineticUnitSet(this);
    }

    public double getX() { return x; }
    public double getY() { return y; }
    public double getVx() { return vx; }
    public double getVy() { return vy; }
    public double getMass() { return mass; }
    public double getCharge() { return charge; }

    public Point2D.Double getLocation() {
        return new Point2D.Double(x, y);
    }

    public Vector getVelocity() {
        return new Vector(vx, vy);
    }

    public void update() {
        //kinetics.applyGravitation(this);
        //kinetics.applyEletromagneticForce(this);
        kinetics.enforceBoundingBox(this);
        kinetics.applyDownwardGravity(this);
        x += vx;
        y += vy;
    }

    public void applyAcceleration(Vector acceleration) {
        vx += acceleration.x;
        vy += acceleration.y;
    }

    public void applyForce(Vector vector) {
        Vector acceleration = new Vector(vector);
        acceleration.scale(1 / mass);
        applyAcceleration(acceleration);
    }

    public void setMass(double mass) {
        this.mass = mass;
    }

    public void setCharge(double charge) {
        this.charge = charge;
    }

    @Override
    public void destroy() {
        super.destroy();
        removeFromKineticUnitSet();
    }
}
