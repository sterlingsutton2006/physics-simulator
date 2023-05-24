public class Ball {
    float posX;
    float posY;
    float velX;
    float velY;
    float accX;
    float accY;
    float rad;
    float mass;
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

    public void update(float dt, float accG) {
        velX += accX * dt;
        velY += (accY + accG) * dt;
        posX += velX * dt + accX * 0.5 * dt * dt;
        posY += velY * dt + (accY + accG) * 0.5 * dt * dt;
    }
}
