package Main;

import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;

public class MouseHandler implements java.awt.event.MouseListener, MouseMotionListener {

    private double x;
    private double y;
    public double getX() {
        return x;
    }
    public double getY() {
        return y;
    }
    public boolean mousePressed;
    private int currentMouseButton = -1;


    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {
        currentMouseButton = e.getButton();
        if (currentMouseButton == MouseEvent.BUTTON1) {
            mousePressed = true;

            x = e.getX();
            y = e.getY();
        }
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        mousePressed = false;
    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }

    @Override
    public void mouseDragged(MouseEvent e) {

        if (currentMouseButton == MouseEvent.BUTTON1) {
            mousePressed = true;

            x = e.getX();
            y = e.getY();
        }
    }

    @Override
    public void mouseMoved(MouseEvent e) {

    }
}
