/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package animation;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Point;
import java.awt.RenderingHints;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import scene.Vector;

/**
 *
 * @author patrick
 */
public final class Viewport {

    private static final double DEFAULT_BOUNDING_BOX = 100;
    
    private Image canvas;
    private Graphics2D g;

    private Rectangle2D.Double rectangle;
    private double boundingBox = DEFAULT_BOUNDING_BOX;
    private double magnification = 1.0;
    private Vector v = new Vector();

    public Viewport(int width, int height) {
        canvas = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        g = (Graphics2D) canvas.getGraphics();
        g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        rectangle = new Rectangle2D.Double(0.0, 0.0, width, height);
        center(new Point2D.Double(0.0, 0.0));
    }

    public void center(Point2D.Double point) {
        rectangle.x = point.x - rectangle.width / 2;
        rectangle.y = point.y - rectangle.height / 2;
    }

    public Point2D.Double getCenter() {
        return new Point2D.Double(rectangle.getCenterX(), rectangle.getCenterY());
    }

    public void zoom(double magnification) {
        this.magnification = magnification;
        magnify();
    }

    public boolean isInBounds(Point2D.Double point) {
        return point.x > rectangle.x - boundingBox &&
                point.x < rectangle.x + rectangle.width + boundingBox &&
                point.y > rectangle.y - boundingBox &&
                point.y < rectangle.y + rectangle.height + boundingBox;
    }

    public Point getPixel(Point2D.Double point) {
        int x = (int) (canvas.getWidth(null) * ((point.x - rectangle.x) / rectangle.width));
        int y = canvas.getHeight(null) - (int) (canvas.getHeight(null) * ((point.y - rectangle.y) / rectangle.height));
        return new Point(x, y);
    }

    public Point2D.Double getPoint(Point pixel) {
        double xratio = (double) pixel.x / canvas.getWidth(null);
        double yratio = (double) (canvas.getHeight(null) - pixel.y) / canvas.getHeight(null);
        return new Point2D.Double(rectangle.x + xratio * rectangle.width, rectangle.y + yratio * rectangle.height);
    }

    public void update() {
        rectangle.x += v.x / magnification;
        rectangle.y += v.y / magnification;
    }

    private void magnify() {
        double width = canvas.getWidth(null) / magnification;
        double height = canvas.getHeight(null) / magnification;
        rectangle.x =rectangle.getCenterX() - width / 2;
        rectangle.y = rectangle.getCenterY() - height / 2;
        rectangle.width = width;
        rectangle.height = height;
    }

    public double getMagnification() {
        return magnification;
    }

    public int getPixelLength(double length) {
        return (int)(length * magnification);
    }

    public Image getImage() {
        return canvas;
    }

    public Graphics2D getGraphics() {
        return g;
    }

    public void setVx(double vx) {
        v.x = vx;
    }

    public void setVy(double vy) {
        v.y = vy;
    }

    public double getVx() {
        return v.x;
    }

    public double getVy() {
        return v.y;
    }

}
