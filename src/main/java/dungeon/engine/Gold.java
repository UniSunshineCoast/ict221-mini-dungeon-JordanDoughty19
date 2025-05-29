public class Gold implements Item {
    @Override
    public char getSymbol() {
        return 'G';
    }

    @Override
    public void interact(Player player) {
        player.addScore(10);
    }
}
