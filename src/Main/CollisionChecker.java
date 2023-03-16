package Main;

import entity.Entity;

public class CollisionChecker {

    GamePanel gamePanel;
    public CollisionChecker(GamePanel gamePanel) {
        this.gamePanel = gamePanel;
    }

    public boolean checkCollision(Entity entity1, Entity entity2) {

        return entity1.getHitBox().intersects(entity2.getHitBox());
    }
}
