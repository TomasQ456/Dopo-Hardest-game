package domain.level;

import domain.entity.Enemy;
import domain.entity.LinearEnemy;
import domain.entity.Player;
import domain.entity.YellowCoin;
import domain.exception.DhgDomainException;
import domain.input.HumanController;
import domain.math.HitBox;
import domain.math.Rect;
import domain.math.Vector2;
import domain.skin.BlinkySkin;

public class TestLevelFactory {

    private static final int COLS = 20;
    private static final int ROWS = 13;

    // Map layout: W=Wall, F=Floor, S=Start, G=Goal
    private static final String[] MAP = {
        "WWWWWWWWWWWWWWWWWWWW",
        "WFFFFFFFFFFFFFFFFFFFW",
        "WFFFFFFFFFFFFFFFFFFFFW",
        "WFSFFFFFFFFFFFFFFGFFW",
        "WFFFFFFFFFFFFFFFFFFFFW",
        "WFFFFFFFFFFFFFFFFFFFFW",
        "WFFFFFFFFFFFFFFFFFFFFW",
        "WFFFFFFFFFFFFFFFFFFFFW",
        "WFFFFFFFFFFFFFFFFFFFFW",
        "WFFFFFFFFFFFFFFFFFFFFW",
        "WFFFFFFFFFFFFFFFFFFFFW",
        "WFFFFFFFFFFFFFFFFFFFFW",
        "WWWWWWWWWWWWWWWWWWWW"
    };

    public static Level build() throws DhgDomainException {
        // Step 1 — Build TileMap
        Tile[][] grid = new Tile[COLS][ROWS];
        for (int row = 0; row < ROWS; row++) {
            String rowStr = MAP[row].trim();
            for (int col = 0; col < COLS; col++) {
                char ch = rowStr.length() > col ? rowStr.charAt(col) : ' ';
                if (ch == 'W') {
                    grid[col][row] = new WallTile();
                } else if (ch == 'S') {
                    grid[col][row] = new StartTile();
                } else if (ch == 'G') {
                    grid[col][row] = new GoalTile();
                } else {
                    grid[col][row] = new FloorTile();
                }
            }
        }
        TileMap tileMap = new TileMap(COLS, ROWS, grid);

        // Step 2 — Build Player 1
        Vector2 spawnPos = new Vector2(2 * Level.TILE_SIZE, 3 * Level.TILE_SIZE);
        HitBox hitBox = new HitBox(new Rect(0, 0, 30, 30));
        Player player = new Player(5.0); // example speed
        player.setPosition(spawnPos);
        player.setHitBox(hitBox);
        player.applySkin(new BlinkySkin());
        player.setController(new HumanController());

        // Step 3 — Build Enemies (2 LinearEnemies)
        Enemy e1 = new LinearEnemy(3.0, new Vector2(1, 0));
        e1.setPosition(new Vector2(8 * Level.TILE_SIZE, 3 * Level.TILE_SIZE));
        e1.setHitBox(new HitBox(new Rect(0, 0, 30, 30)));
        
        Enemy e2 = new LinearEnemy(3.0, new Vector2(0, 1));
        e2.setPosition(new Vector2(12 * Level.TILE_SIZE, 6 * Level.TILE_SIZE));
        e2.setHitBox(new HitBox(new Rect(0, 0, 30, 30)));

        // Step 4 — Build Coins (5 YellowCoins)
        YellowCoin[] coins = new YellowCoin[5];
        int[] coinCols = {5, 7, 9, 11, 13};
        for (int i = 0; i < coins.length; i++) {
            coins[i] = new YellowCoin();
            coins[i].setPosition(new Vector2(coinCols[i] * Level.TILE_SIZE, 5 * Level.TILE_SIZE));
            coins[i].setHitBox(new HitBox(new Rect(0, 0, 15, 15)));
        }

        // Step 5 — Build Level
        Level level = new Level();
        level.setTileMap(tileMap);
        level.addEntity(player);
        level.addEntity(e1);
        level.addEntity(e2);
        for (YellowCoin coin : coins) {
            level.addEntity(coin);
        }
        level.getTimeController().setLimit(120.0);

        return level;
    }
}
