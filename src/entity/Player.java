package entity;

import Main.GamePanel;
import Main.KeyHandler;
import Main.MouseHandler;
import math.Vec2D;

import java.awt.*;

public class Player extends Entity{

    GamePanel gamePanel;
    KeyHandler keyH;
    MouseHandler mouseH;
    private final double defaultX = 500;
    private final double defaultY = 500;
    private final double defaultSpeed = 4;
    private double width = 25;
    private double height = 25;
    private int middelX = 0;
    private int middelY = 0;
    private int defaultHealth = 5;
    public int invincibleTimer;
    private double attackSpeed = 10;
    private double nextShoot;
    private double shootCounter;
    private boolean canShoot;

    public Player(double x, double y, GamePanel gamePanel, KeyHandler keyH, MouseHandler mouseH) {
        super(x ,y, gamePanel);

        this.gamePanel = gamePanel;
        this.keyH = keyH;
        this.mouseH = mouseH;

        nextShoot = gamePanel.FPS;
        setDefaultValues();
    }
    private void setDefaultValues() {

        setX(defaultX);
        setY(defaultY);
        setSpeed(defaultSpeed);
        setHealth(defaultHealth);
    }
    public void update(Entity player) {

        if(invincibleTimer >= 0) invincibleTimer --;
        keyListener();
        canShoot();
        mouseListener();
        super.update(player);

        updateLocation();

        getHitBox().setRect(getX(), getY(), width, height);
    }
    private void keyListener() {

        if (keyH.upPressed) moveUp();
        if (keyH.downPressed) moveDown();
        if (keyH.leftPressed) moveLeft();
        if (keyH.rightPressed) moveRight();
    }
    private void mouseListener() {
        if (mouseH.mousePressed) shoot();
    }
    private void moveUp(){
        if (getY() >= 0) y -= getSpeed();
    }
    private void moveDown(){
        if (getY() <= gamePanel.getHEIGHT() - height) y += getSpeed();
    }
    private void moveLeft(){
        if (getX() >= 0) x -= getSpeed();
    }
    private void moveRight(){
        if (getX() <= gamePanel.getWIDTH() - width) x += getSpeed();
    }

    private void shoot() {
        if (canShoot) {
            Vec2D direction = new Vec2D(mouseH.getX(), mouseH.getY());
            Projectile projectile = new Projectile(middelX, middelY, gamePanel, direction);
            projectile.setX(projectile.getX() - projectile.getWidth() / 2);
            projectile.setY(projectile.getY() - projectile.getHeight() / 2);


            for (int i = 0; i < gamePanel.projectiles.length; i++) {
                if (gamePanel.projectiles[i] == null) {
                    gamePanel.projectiles[i] = projectile;
                    break;
                }
            }
            shootCounter = 0;
        }
    }
    private void canShoot() {
        if (shootCounter >= nextShoot) {
            canShoot = true;
        } else {
            shootCounter += attackSpeed;
            canShoot = false;
        }
    }
    public void render(Graphics2D g2) {

        g2.setColor(Color.WHITE);
        g2.fillRect((int)x, (int)y, (int)width, (int)height);
    }
    public void updateLocation() {
        super.updateLocation();

        middelX = (int) (x + (width / 2));
        middelY = (int) (y + (height / 2));
    }
}
