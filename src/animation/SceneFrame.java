/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package animation;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import scene.units.kinetics.Ball;
import scene.Scene;

/**
 *
 * @author patrick
 */
public final class SceneFrame extends JFrame {

    private Animator animator;
    private Painter painter;
    private Scene scene;

    private JLabel canvas;
    
    public SceneFrame(Scene scene, int width, int height) {
        super("Spring");
        this.scene = scene;
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
        init();
    }

    private void init() {
        Viewport viewport = new Viewport(640, 480);
        animator = new Animator(scene);
        painter = new Painter(scene, viewport, this);
        canvas = new JLabel(new ImageIcon(viewport.getImage()));
        canvas.addMouseListener(new MouseHandler());
        canvas.addMouseWheelListener(new MouseWheelHandler());
        addKeyListener(new KeyHandler());
        add(canvas);
        pack();
        animator.start();
        painter.start();
    }

    /*
     * Entry point
     */
    public static void main(String[] args) {
        new SceneFrame(new Scene(), 640, 480);
    }

    /*
     * Listeners
     */

    private class MouseHandler implements MouseListener {
        
        public void mouseClicked(MouseEvent e) {
            
        }

        public void mousePressed(MouseEvent e) {
            Ball b = new Ball(painter.getViewport().getPoint(e.getPoint()));
            if (e.getButton() == MouseEvent.BUTTON1) {
                b.setCharge(1.0);
            } else if (e.getButton() == MouseEvent.BUTTON3) {
                b.setCharge(-1.0);
            }
            scene.add(b);
        }

        public void mouseReleased(MouseEvent e) {
            
        }

        public void mouseEntered(MouseEvent e) {
            
        }

        public void mouseExited(MouseEvent e) {
            
        }
        
    }

    private class MouseWheelHandler implements MouseWheelListener {

        private double magnification = painter.getViewport().getMagnification();
        private static final double SCROLL_AMOUNT = 1.04;

        public void mouseWheelMoved(MouseWheelEvent e) {
            magnification *= Math.pow(SCROLL_AMOUNT, -e.getWheelRotation());
            painter.getViewport().zoom(magnification);
        }

    }

    private class KeyHandler implements KeyListener {

        private static final double SPEED = 5;

        public void keyTyped(KeyEvent e) {

        }

        public void keyPressed(KeyEvent e) {
            Viewport v = painter.getViewport();
            switch (e.getKeyCode()) {
                case KeyEvent.VK_A:
                    v.setVx(-SPEED);
                    break;
                case KeyEvent.VK_D:
                    v.setVx(SPEED);
                    break;
                case KeyEvent.VK_W:
                    v.setVy(SPEED);
                    break;
                case KeyEvent.VK_S:
                    v.setVy(-SPEED);
                    break;
                case KeyEvent.VK_ESCAPE:
                    scene.clearUnits();
                    break;
            }
        }

        public void keyReleased(KeyEvent e) {
            Viewport v = painter.getViewport();
            switch (e.getKeyCode()) {
                case KeyEvent.VK_A:
                    v.setVx(0);
                    break;
                case KeyEvent.VK_D:
                    v.setVx(0);
                    break;
                case KeyEvent.VK_W:
                    v.setVy(0);
                    break;
                case KeyEvent.VK_S:
                    v.setVy(0);
                    break;
            }
        }

    }
}
