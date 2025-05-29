package dungeon.engine;

import javafx.scene.layout.StackPane;

public class Cell extends StackPane {

}

public class Cell {
    private Item item;

    public Cell(Item item) {
        this.item = item;
    }

    public Item getItem() {
        return item;
    }

    public void clearItem() {
        this.item = null;
    }

    public char getSymbol() {
        return (item != null) ? item.getSymbol() : '.';
    }
}
