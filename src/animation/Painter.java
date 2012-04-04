/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package animation;

import javax.swing.JFrame;
import scene.Scene;

/**
 *
 * @author patrick
 */
public final class Painter extends Thread {
    
    public static final int DEFAULT_FPS = 30;

    private int fps = DEFAULT_FPS;
    private Scene scene;
    private Viewport viewport;
    private boolean running;
    private JFrame frame;

    public Painter(Scene scene, Viewport viewport, JFrame frame) {
        super("Painter thread");
        this.scene = scene;
        this.viewport = viewport;
        this.frame = frame;
    }

    @Override
    public void start() {
        super.start();
        running = true;
    }

    @Override
    public void run() {
        long period = 1000 / fps;
        long before, diff;
        while (running) {
            before = System.currentTimeMillis();

            viewport.update();
            scene.paint(viewport);
            frame.repaint();

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

    public Viewport getViewport() {
        return viewport;
    }
}
