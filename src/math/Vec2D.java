package math;

public class Vec2D {
    public static final Vec2D XAxis = new Vec2D(1.0, 0.0);
    public static final Vec2D YAxis = new Vec2D(0.0, 1.0);
    public static final Vec2D Zero = new Vec2D(0.0, 0.0);
    private double x;
    private double y;


    public Vec2D(double x, double y){
        this.x = x;
        this.y = y;
    }

    public double getX() {return x;}
    public void setX(double x) {this.x = x;}
    public double getY() {return y;}
    public void setY(double y) {this.y = y;}

    public void print() {
        System.out.println("x: " + x + " | y: " + y);
    }
    public double length() {
        return Math.sqrt(Math.pow(getX(), 2) + Math.pow(getY(), 2));
    }
    public Vec2D subtract(Vec2D vec) {
        double newX = getX() - vec.getX();
        double newY = getY() - vec.getY();
        return new Vec2D(newX, newY);
    }
    public Vec2D add(Vec2D vec) {
        double newX = getX() + vec.getX();
        double newY = getY() + vec.getY();
        return new Vec2D(newX, newY);
    }
    public void normalize() {
        double length = length();
        setX((1 / length) * getX());
        setX((1 / length) * getY());
    }
}
