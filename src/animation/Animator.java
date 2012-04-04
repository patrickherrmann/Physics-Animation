/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package animation;

import scene.Scene;

/**
 *
 * @author patrick
 */
public final class Animator extends Thread {

    public static final int DEFAULT_UPS = 50;

    private int ups = DEFAULT_UPS;
    private Scene scene;
    private boolean running = false;

    public Animator(Scene scene) {
        super("Animator thread");
        this.scene = scene;
    }

    public void setUPS(int ups) {
        this.ups = ups;
    }

    public int getUPS() {
        return ups;
    }


    @Override
    public void start() {
        super.start();
        running = true;
    }

    @Override
    public void run() {
        long period = 1000 / ups;
        long before, diff;
        while (running) {
            before = System.currentTimeMillis();

            scene.update();

            diff = period - (System.currentTimeMillis() - before);
            if (diff > 0) {
                try {
                    Thread.sleep(diff);
                } catch (InterruptedException e) {
                    System.out.println(e);
                }
            }
        }
    }
}
