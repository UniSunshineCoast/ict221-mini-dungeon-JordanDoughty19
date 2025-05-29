public class Player {
    private int x, y, hp = 100, score = 0, steps = 0;

    public Player(int startX, int startY) {
        this.x = startX;
        this.y = startY;
    }

    public void move(int dx, int dy) {
        this.x += dx;
        this.y += dy;
        this.steps++;
    }

    public int getX() { return x; }
    public int getY() { return y; }
    public int getHP() { return hp; }
    public int getScore() { return score; }
    public int getSteps() { return steps; }

    public void decreaseHP(int amount) { hp -= amount; }
    public void addScore(int points) { score += points; }
    public void heal(int amount) { hp += amount; }
}
