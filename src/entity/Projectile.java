package entity;

import Main.GamePanel;
import math.Vec2D;

import java.awt.*;

public class Projectile extends Entity{

    Vec2D direction;
    private final double width = 10;
    private final double height = 10;
    private final double defaultSpeed = 10;
    private int damage = 1;
    public int getDamage() {
        return damage;
    }

    public double getWidth() {return width;}
    public double getHeight() {return height;}
    Vec2D root = new Vec2D(x , y); //player coordinate at shoot

    public Projectile(double x, double y, GamePanel gamePanel, Vec2D direction) {
        super(x, y, gamePanel);

        setDefaultValues();
        this.direction = direction.subtract(root);
    }
    private void setDefaultValues() {

        setSpeed(defaultSpeed);
    }
    public void update(Player player) {
        super.update(player);

        updateLocation();

        shoot();
        getHitBox().setRect(getX(), getY(), width, height);
    }

    public void render(Graphics2D g2) {

        g2.setColor(Color.YELLOW);
        g2.fillRect((int)x, (int)y, (int) width, (int) height);
    }
    public void updateLocation() {
        super.updateLocation();
    }

    private void shoot() {
        double distance = direction.length();

        setX(getX() + (direction.getX() * (defaultSpeed / distance)));
        setY(getY() + (direction.getY() * (defaultSpeed / distance)));
    }
}
