/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package scene;

import scene.units.Unit;
import animation.Paintable;
import animation.Viewport;
import java.awt.Color;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
import scene.units.Spring;
import scene.units.kinetics.Ball;
import scene.units.kinetics.FixedBall;

/**
 *
 * @author patrick
 */
public final class Scene implements Paintable {

    private final Set<Unit> units = Collections.synchronizedSet(new HashSet<Unit>());

    public Scene() {
        FixedBall fixed = new FixedBall(0,0);
        fixed.setCharge(1.0);
        Ball ball = new Ball(50, 100);
        Ball ball2 = new Ball(100, 200);
        Spring spring = new Spring(fixed, ball, 200, 0.002);
        Spring spring2 = new Spring(ball, ball2, 200, 0.002);
        add(ball2);
        add(spring2);
        add(spring);
        add(fixed);
        add(ball);
    }

    public void add(Unit unit) {
        units.add(unit);
    }

    public void clearUnits() {
        units.clear();
    }

    public void update() {
        synchronized (units) {
            Iterator<Unit> iter = units.iterator();
            while (iter.hasNext()) {
                Unit unit = iter.next();
                if (unit.isDestroyed()) {
                    iter.remove();
                    break;
                }
                unit.update();
            }
        }
    }

    public void paint(Viewport v) {
        v.getGraphics().setColor(Color.WHITE);
        v.getGraphics().fillRect(0, 0, v.getImage().getWidth(null), v.getImage().getHeight(null));
        synchronized (units) {
            for (Unit unit : units) {
                unit.paint(v);
            }
        }
    }

}
