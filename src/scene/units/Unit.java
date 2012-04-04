/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package scene.units;

import animation.Paintable;

/**
 *
 * @author patrick
 */
public abstract class Unit implements Paintable {

    
    private boolean destroyed = false;

    public abstract void update();

    public void destroy() {
        destroyed = true;
    }

    public boolean isDestroyed() {
        return destroyed;
    }

}
