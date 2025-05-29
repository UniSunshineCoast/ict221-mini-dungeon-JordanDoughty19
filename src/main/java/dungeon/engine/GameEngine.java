package dungeon.engine;

import javafx.scene.text.Text;

public class GameEngine {

    /**
     * An example board to store the current game state.
     *
     * Note: depending on your game, you might want to change this from 'int' to String or something?
     */
    private Cell[][] map;

    /**
     * Creates a square game board.
     *
     * @param size the width and height.
     */
    public GameEngine(int size) {
        map = new Cell[size][size];

        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                Cell cell = new Cell();
                Text text = new Text(i + "," + j);
                cell.getChildren().add(text);
                map[i][j] = cell;
            }
        }

        map[0][0].setStyle("-fx-background-color: #7baaa4");
        map[size-1][size-1].setStyle("-fx-background-color: #7baaa4");
    }

    /**
     * The size of the current game.
     *
     * @return this is both the width and the height.
     */
    public int getSize() {
        return map.length;
    }

    /**
     * The map of the current game.
     *
     * @return the map, which is a 2d array.
     */
    public Cell[][] getMap() {
        return map;
    }

    /**
     * Plays a text-based game
     */
    public static void main(String[] args) {
        GameEngine engine = new GameEngine(10);
        System.out.printf("The size of map is %d * %d\n", engine.getSize(), engine.getSize());
    }
}
import java.util.Random;
import java.util.Scanner;

public class GameEngine {
    private static final int SIZE = 10;
    private Cell[][] map = new Cell[SIZE][SIZE];
    private Player player;

    public GameEngine() {
        initializeMap();
        player = new Player(9, 0); // Entry point
    }

    private void initializeMap() {
        Random rand = new Random();
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                map[i][j] = new Cell(null);
            }
        }
        for (int i = 0; i < 5; i++) placeItemRandomly(new Gold());
        for (int i = 0; i < 5; i++) placeItemRandomly(new Trap());
    }

    private void placeItemRandomly(Item item) {
        Random rand = new Random();
        int x, y;
        do {
            x = rand.nextInt(SIZE);
            y = rand.nextInt(SIZE);
        } while (map[x][y].getItem() != null);
        map[x][y] = new Cell(item);
    }

    public void play() {
        Scanner scanner = new Scanner(System.in);
        while (player.getSteps() < 100 && player.getHP() > 0) {
            printMap();
            System.out.println("Move (w/a/s/d): ");
            String input = scanner.nextLine();

            int dx = 0, dy = 0;
            switch (input) {
                case "w": dx = -1; break;
                case "s": dx = 1; break;
                case "a": dy = -1; break;
                case "d": dy = 1; break;
                default: System.out.println("Invalid move"); continue;
            }

            int newX = player.getX() + dx;
            int newY = player.getY() + dy;
            if (isValidMove(newX, newY)) {
                Cell target = map[newX][newY];
                if (target.getItem() != null) {
                    target.getItem().interact(player);
                    target.clearItem();
                }
                player.move(dx, dy);
            } else {
                System.out.println("Blocked!");
            }
        }
        System.out.println("Game over! Final Score: " + player.getScore());
    }

    private boolean isValidMove(int x, int y) {
        return x >= 0 && x < SIZE && y >= 0 && y < SIZE;
    }

    private void printMap() {
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                if (i == player.getX() && j == player.getY()) {
                    System.out.print("P ");
                } else {
                    System.out.print(map[i][j].getSymbol() + " ");
                }
            }
            System.out.println();
        }
    }
}

