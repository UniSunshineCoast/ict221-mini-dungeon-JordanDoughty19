public class Trap implements Item {
    @Override
    public char getSymbol() {
        return 'T';
    }

    @Override
    public void interact(Player player) {
        player.decreaseHP(10);
    }
}
