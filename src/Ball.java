public class Ball {
    float posX;
    float posY;
    float velX;
    float velY;
    float accX;
    float accY;
    float rad;

    public Ball(float posX, float posY, float velX, float velY, float accX, float accY, float rad, float mass) {
        this.posX = posX;
        this.posY = posY;
        this.velX = velX;
        this.velY = velY;
        this.accX = accX;
        this.accY = accY;
        this.rad = rad;
        this.mass = mass;
    }

    float mass;
}
