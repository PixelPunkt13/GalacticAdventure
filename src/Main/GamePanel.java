package Main;

import entity.Enemy;
import entity.Entity;
import entity.Player;
import entity.Projectile;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.*;

public class GamePanel extends JPanel implements Runnable{


    //Dimension dimension = new Dimension(Toolkit.getDefaultToolkit().getScreenSize());
    Dimension dimension = new Dimension(1000, 1000);
    private final int WIDTH = (int) dimension.getWidth(); //GamePanel Width
    public int getWIDTH() {return WIDTH;}
    private final int HEIGHT = (int) dimension.getHeight(); //GamePanel Height
    public int getHEIGHT() {return HEIGHT;}
    BufferedImage background;

    public final int FPS = 60; //FPS
    private final int ENEMYSPAWNCOUNT = 2;
    private int maxEnemys = 10;
    private final int MAXPROJECTILES = 22;
    KeyHandler keyH = new KeyHandler();
    MouseHandler mouseH = new MouseHandler();
    Thread gameThread;
    public CollisionChecker colChecker = new CollisionChecker(this);
    Player player = new Player(0, 0, this, keyH, mouseH);
    Enemy[] enemies = new Enemy[maxEnemys];
    public Projectile[] projectiles = new Projectile[MAXPROJECTILES];
    public GamePanel() {
        this.setPreferredSize(new Dimension(WIDTH, HEIGHT));


        getBackgroundImage();

        this.setBackground(Color.BLACK);

        this.setDoubleBuffered(true);
        this.setFocusable(true); //GamePanel can be "focused" to receive key input
        this.addKeyListener(keyH);
        this.addMouseListener(mouseH);
        this.addMouseMotionListener(mouseH);
    }
    private void getBackgroundImage() {
        try {
            background = ImageIO.read(getClass().getResourceAsStream("/background.jpg"));
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Image didn't load");
        }
    }
    public void startGameThread() {

        gameThread = new Thread(this);
        gameThread.start();

        spawnEnemy();
    }

    @Override
    public void run() { //gets automatically called, when gameThread starts

        double drawInterval = 1000000000 / FPS;
        double delta = 0;
        long lastTime = System.nanoTime();
        long currentTime;

        long timer = 0; //for showing FPS
        int drawCount = 1; //for showing FPS

        while (gameThread != null){ //as long as the gameThread exists

            currentTime = System.nanoTime();
            delta += (currentTime - lastTime) / drawInterval;
            timer += currentTime - lastTime; //for showing FPS
            lastTime = currentTime;

            if (delta >= 1) {
                update();
                repaint(); //calls paintComponent

                drawCount ++;
                delta --; //RESET DELTA
            }
            if (timer >= 1000000000) { //for showing FPS
                System.out.println("FPS: " + drawCount + " | " + projectiles.length);
                drawCount = 0;
                timer = 0;

            }
        }
    }
    private void update() {

        updatePlayer();
        updateEnemy();
        updateProjectile();
    }
    private void updatePlayer() {
        if (player.getHealth() <= 0) gameOver();
        player.update(player);
    }
    private void updateEnemy() {
        for (int i = 0; i < enemies.length; i++) {

            if (enemies[i] != null) {

                enemies[i].update(player);

                for (int j = 0; j < projectiles.length; j++) {
                    if (projectiles[j] != null && enemies[i] != null) {
                        if (colChecker.checkCollision(enemies[i], projectiles[j])) {
                            if (enemies[i].enemyGotDamaged(projectiles[j])) {  //Delete enemy and projektile
                                enemies[i] = null;
                            }
                            projectiles[j] = null;
                        }
                    }
                }
                if ((player.invincibleTimer <= 0) && (enemies[i] != null)) {
                    if (colChecker.checkCollision(player, enemies[i])) {
                        playerGotDamaged(enemies[i]);
                    }
                }

            } else {
                spawnEnemy();
            }
        }
    }
    private void updateProjectile() {

        for (int i = 0; i < projectiles.length ; i++) {
            if (projectiles[i] != null) {
                if (!inGamePanel(projectiles[i])) {
                    projectiles[i] = null; //Delete Projektile
                } else {
                    projectiles[i].update(player);
                }
            }
        }
    }
    private boolean inGamePanel(Entity entity) { //checks if the entity is in Game panel
        return (entity.getX() >= -100) && (entity.getX() <= WIDTH +100) && (entity.getY() >= -100) && (entity.getY() <= HEIGHT +100);
    }
    private void playerGotDamaged(Enemy entity) {
        System.out.println("You lost " + entity.getDamage() + " Health");
        player.damageEntity(entity.getDamage());
        player.invincibleTimer = 60;
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D)g; //change Class to Graphics2D

        g2.drawImage(background, 0, 0, WIDTH, HEIGHT, null); //scales Image automatically

        player.render(g2);

        for (Enemy enemy : enemies) {
            if (enemy != null) {
                enemy.render(g2);
            }
        }

        for (Projectile projectile : projectiles) {
            if (projectile != null) {
                projectile.render(g2);
            }
        }
        g2.dispose();
    }

    private void spawnEnemy() {

        for (int i = 0; i < ENEMYSPAWNCOUNT; i++) {
            if (enemies[i] == null) {
                enemies[i] = new Enemy(randomSpawnX(), randomSpawnY(), this);
            }
        }
    }
    private double randomSpawnX() {
        Random random = new Random();
        return random.nextDouble() * getWIDTH();
    }
    private double randomSpawnY() {
        Random random = new Random();
        return random.nextDouble() * getHEIGHT();
    }
    private void gameOver() {
        repaint();
        gameThread.stop();
    }
}