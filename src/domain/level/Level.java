package domain.level;

import domain.entity.Entity;
import domain.entity.Player;
import java.util.List;
import domain.exception.DhgDomainException;

import java.util.ArrayList;

/**
 * The core container representing a single playable stage.
 * Holds the map, all entities, players, and manages the local game simulation loop.
 */
public class Level {
    
    public static final int TILE_SIZE = 40;

    private String id;
    private TileMap tileMap;
    private List<Player> players;
    private List<Entity> entities;
    private TimeController timeController;
    private Checkpoint activeCheckpoint;

    /**
     * Constructs a Level with initial collections.
     */
    public Level() {
        this.players = new ArrayList<>();
        this.entities = new ArrayList<>();
        this.timeController = new TimeController();
    }

    /**
     * Ticks the physics and logic for all contained entities and the timer.
     * Interacts with all Updatable objects inside the level.
     * @param deltaSeconds The elapsed time in seconds.
     */
    public void update(double deltaSeconds) throws DhgDomainException {
        this.timeController.tick(deltaSeconds);

        // Update all active players and apply bounds
        for (Player player : this.players) {
            player.update(deltaSeconds);
            player.applyBounds(this.tileMap);
        }

        // Update all active entities
        for (Entity entity : new ArrayList<>(this.entities)) {
            if (entity.isActive()) {
                entity.update(deltaSeconds);
                if (entity instanceof domain.entity.Actor) {
                    ((domain.entity.Actor) entity).applyBounds(this.tileMap);
                }
            }
        }

        // Check collisions
        checkCollisions();
    }

    /**
     * Loops through entities and players, checking for bounding box intersections.
     * Interacts with entity.onContact(player) when intersections are found.
     */
    private void checkCollisions() throws DhgDomainException {
        for (Player player : this.players) {
            try {
                if (!player.isActive()) continue;
                for (Entity entity : new ArrayList<>(this.entities)) {
                    if (!entity.isActive()) continue;
                    if (player.intersects(entity)) {
                        entity.onContact(player);
                    }
                }
            } catch (DhgDomainException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * Registers a new entity into the active simulation.
     * Routes Player entities to the players list, others to entities list.
     * @param entity The entity to add.
     */
    public void addEntity(Entity entity) throws DhgDomainException {
        if (entity == null)
            throw new DhgDomainException(DhgDomainException.ERR_NULL_ENTITY);
        if (entity instanceof Player) {
            this.players.add((Player) entity);
        } else {
            this.entities.add(entity);
        }
    }

    /**
     * Sets the latest checkpoint reached by a player.
     * Used for respawning after death.
     * @param cp The newly activated checkpoint.
     */
    public void setActiveCheckpoint(Checkpoint cp) throws DhgDomainException {
        this.activeCheckpoint = cp;
    }

    /**
     * Retrieves the currently active checkpoint.
     * @return The active checkpoint.
     */
    public Checkpoint getActiveCheckpoint() throws DhgDomainException {
        return this.activeCheckpoint;
    }

    /**
     * Retrieves the time controller managing the level's countdown.
     * @return The time controller instance.
     */
    public TimeController getTimeController() throws DhgDomainException {
        return this.timeController;
    }

    /**
     * Adds a player to the level.
     * @param player The player to add.
     */
    public void addPlayer(Player player) throws DhgDomainException {
        if (player == null) {
            throw new DhgDomainException(DhgDomainException.ERR_NULL_OTHER);
        }
        this.players.add(player);
    }

    /**
     * Retrieves all players in the level.
     * @return The list of players.
     */
    public List<Player> getPlayers() throws DhgDomainException {
        return this.players;
    }

    public void setTileMap(TileMap tileMap) throws DhgDomainException {
        if (tileMap == null)
            throw new DhgDomainException(DhgDomainException.ERR_NULL_TILEMAP);
        this.tileMap = tileMap;
    }

    public TileMap getTileMap() throws DhgDomainException {
        if (this.tileMap == null)
            throw new DhgDomainException(DhgDomainException.ERR_NULL_TILEMAP);
        return this.tileMap;
    }

    public List<Entity> getEntities() {
        return this.entities;
    }

    /**
     * Finds and returns the StartZone in the entities list.
     * @return The StartZone, or null if not found.
     */
    private StartZone findStartZone() {
        return entities.stream()
            .filter(e -> e instanceof StartZone)
            .map(e -> (StartZone) e)
            .findFirst().orElse(null);
    }

    /**
     * Finds and returns the GoalZone in the entities list.
     * @return The GoalZone, or null if not found.
     */
    private GoalZone findGoalZone() {
        return entities.stream()
            .filter(e -> e instanceof GoalZone)
            .map(e -> (GoalZone) e)
            .findFirst().orElse(null);
    }
}

