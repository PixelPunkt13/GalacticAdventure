package entity;

import Main.GamePanel;
import math.Vec2D;

import java.awt.*;

public class Enemy extends Entity{
    private double width = 25;
    private double height = 25;
    private final double defaultSpeed = 2;
    private int damage = 1;
    public int getDamage() {
        return damage;
    }

    private int defaultHealth = 5;

    public Enemy(double x, double y, GamePanel gamePanel) {
        super(x, y, gamePanel);

        setDefaultValues();
    }
    private void setDefaultValues() {

        setHealth(defaultHealth);
        setSpeed(defaultSpeed);
    }
    public void update(Entity player) {
        super.update(player);

        searchPlayer(player);
        updateLocation();
        getHitBox().setRect(getX(), getY(), width, height);
    }

    public void render(Graphics2D g2) {

        g2.setColor(Color.RED);
        g2.fillRect((int)x, (int)y, (int)width, (int)height);
    }
    public void updateLocation() {
        super.updateLocation();
    }

    private void searchPlayer(Entity player) {

        Vec2D direction = player.location.subtract(location);
        double distance = direction.length();

        setX(getX() + (direction.getX() * (defaultSpeed / distance)));
        setY(getY() + (direction.getY() * (defaultSpeed / distance)));
    }
    public boolean enemyGotDamaged(Projectile projectile) {
        this.setHealth(getHealth() - projectile.getDamage());

        return this.getHealth() <= 0;
    }
}