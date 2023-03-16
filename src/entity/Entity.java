package entity;

import Main.GamePanel;
import math.Vec2D;

import java.awt.*;

public class Entity{
    protected double x;
    protected double y;
    protected double speed;
    protected double width;
    protected double height;
    public Vec2D location;
    private Rectangle hitBox;
    private int health;

    public double getX() {return x;}
    public void setX(double xKoordinate) {this.x = xKoordinate;}
    public double getY() {return y;}
    public void setY(double yKoordinate) {this.y = yKoordinate;}
    public double getWidth() {return width;}
    public void setWidth(double width) {this.width = width;}
    public double getHeight() {return height;}
    public void setHeight(double height) {this.height = height;}
    public Rectangle getHitBox() {return hitBox;}
    public void setHitBox(Rectangle hitBox) {this.hitBox = hitBox;}
    public double getSpeed() {return speed;}
    public void setSpeed(double speed) {this.speed = speed;}
    public int getHealth() {return health;}
    public void setHealth(int health) {this.health = health;}
    public Entity(double x, double y, GamePanel gamePanel) {
        this.x = x;
        this.y = y;

        location = new Vec2D(x, y);
        hitBox = new Rectangle((int)x, (int)y, (int)width, (int)height);
    }

    public void update(Entity player) {}

    public void render(Graphics2D g2) {}

    public void updateLocation() {

        location.setX(x);
        location.setY(y);
    }
    public void damageEntity (int damage) {
        setHealth(getHealth() - damage);
    }

}