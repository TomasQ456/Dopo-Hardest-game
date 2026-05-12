package domain.memento;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import domain.entity.AcceleratedEnemy;
import domain.entity.Coin;
import domain.entity.Enemy;
import domain.entity.Entity;
import domain.entity.LinearEnemy;
import domain.entity.Player;
import domain.entity.SpecialElement;
import domain.entity.WaypointEnemy;
import domain.level.Level;
import domain.level.Checkpoint;
import domain.exception.DhgDomainException;
import domain.math.Vector2;
import domain.skin.SkinRegistry;

/**
 * Manages the current saved snapshot (Memento) of the game.
 */
public class CheckpointCaretaker implements Serializable {
    private static final long serialVersionUID = 1L;
    private LevelMemento currentMemento;

    /**
     * Creates a new Memento snapshot from the live Level and stores it.
     * @param level The level to snapshot.
     */
    public void save(Level level) throws DhgDomainException {
        String levelId = level.getId();
        List<PlayerMemento> playerMementos = new ArrayList<>();
        List<Player> players = level.getPlayers();

        for (Player player : players) {
            PlayerMemento pm = new PlayerMemento(
                player.getDeaths(),
                player.getScore(),
                player.getPosition(),
                player.getSkinBehavior(),
                player.getShieldHits()
            );
            playerMementos.add(pm);
        }

        List<EnemyMemento> enemyStates = new ArrayList<>();
        List<CoinMemento> coinStates = new ArrayList<>();
        List<SpecialElementMemento> specialElementStates = new ArrayList<>();
        for (Entity entity : level.getEntities()) {
            if (entity instanceof Enemy) {
                enemyStates.add(snapshotEnemy((Enemy) entity));
            } else if (entity instanceof Coin) {
                coinStates.add(snapshotCoin((Coin) entity));
            } else if (entity instanceof SpecialElement) {
                specialElementStates.add(snapshotSpecialElement((SpecialElement) entity));
            }
        }

        List<CheckpointMementoData> checkpointStates = new ArrayList<>();
        for (Checkpoint checkpoint : level.getCheckpoints()) {
            checkpointStates.add(snapshotCheckpoint(checkpoint));
        }

        double remainingSeconds = level.getTimeController().getRemainingSeconds();
        Set<String> collectedCoinIds = new LinkedHashSet<>();
        for (CoinMemento coinState : coinStates) {
            if (coinState.isCollected()) {
                collectedCoinIds.add(coinState.getCoinId());
            }
        }

        this.currentMemento = new LevelMemento(levelId, playerMementos, collectedCoinIds, remainingSeconds,
                enemyStates, checkpointStates, specialElementStates, coinStates);
    }

    /**
     * Overwrites the live Level state with the data from the stored Memento.
     * @param level The level to restore into.
     */
    public void restore(Level level) throws DhgDomainException {

        if (this.currentMemento == null) {
            return;
        }

        if (this.currentMemento.getLevelId() != null) {
            level.setId(this.currentMemento.getLevelId());
        }

        List<Player> players = level.getPlayers();
        List<PlayerMemento> playerMementos = this.currentMemento.getPlayerMementos();

        if (playerMementos != null) {
            for (int i = 0; i < players.size() && i < playerMementos.size(); i++) {
                Player player = players.get(i);
                PlayerMemento pm = playerMementos.get(i);

                if (pm.getPosition() != null) {
                    player.setPosition(pm.getPosition());
                }
                player.setDeaths(pm.getDeaths());
                player.setScore(pm.getScore());
                player.setShieldHits(pm.getShieldHits());
                player.applySkin(SkinRegistry.fromName(pm.getSkinName()));
            }
        }

        restoreEnemies(level);
        restoreCoins(level);
        restoreCheckpoints(level);
        restoreSpecialElements(level);

        level.getTimeController().setLimit(this.currentMemento.getRemainingSeconds());
    }

    private EnemyMemento snapshotEnemy(Enemy enemy) throws DhgDomainException {
        Vector2 position = enemy.getPosition();
        double x = position == null ? 0.0 : position.x;
        double y = position == null ? 0.0 : position.y;
        double speed = enemy.getSpeed();
        double directionX = 0.0;
        double directionY = 0.0;
        List<Vector2> waypoints = new ArrayList<>();
        int currentWaypointIndex = 0;

        if (enemy instanceof LinearEnemy) {
            Vector2 direction = ((LinearEnemy) enemy).getDirection();
            if (direction != null) {
                directionX = direction.x;
                directionY = direction.y;
            }
        } else if (enemy instanceof WaypointEnemy) {
            List<Vector2> sourceWaypoints = ((WaypointEnemy) enemy).getWaypoints();
            if (sourceWaypoints != null) {
                waypoints = new ArrayList<>(sourceWaypoints);
            }
            currentWaypointIndex = ((WaypointEnemy) enemy).getCurrentWaypointIndex();
        }

        return new EnemyMemento(enemy.getClass().getSimpleName(), enemy.getId(), x, y, speed,
                directionX, directionY, waypoints, currentWaypointIndex);
    }

    private CoinMemento snapshotCoin(Coin coin) throws DhgDomainException {
        Vector2 position = coin.getPosition();
        return new CoinMemento(coin.getId(), coin.isCollected(),
                position == null ? 0.0 : position.x,
                position == null ? 0.0 : position.y);
    }

    private CheckpointMementoData snapshotCheckpoint(Checkpoint checkpoint) throws DhgDomainException {
        Vector2 respawnPosition = checkpoint.getRespawnPosition();
        return new CheckpointMementoData(checkpoint.getId(), checkpoint.isActive(),
                respawnPosition == null ? 0.0 : respawnPosition.x,
                respawnPosition == null ? 0.0 : respawnPosition.y);
    }

    private SpecialElementMemento snapshotSpecialElement(SpecialElement element) throws DhgDomainException {
        Vector2 position = element.getPosition();
        return new SpecialElementMemento(element.getClass().getSimpleName(), element.getId(), element.isActive(),
                position == null ? 0.0 : position.x,
                position == null ? 0.0 : position.y);
    }

    private void restoreEnemies(Level level) throws DhgDomainException {
        List<EnemyMemento> enemyStates = this.currentMemento.getEnemyStates();
        if (enemyStates == null || enemyStates.isEmpty()) {
            return;
        }

        Map<String, Enemy> enemiesById = new HashMap<>();
        for (Entity entity : level.getEntities()) {
            if (entity instanceof Enemy) {
                enemiesById.put(entity.getId(), (Enemy) entity);
            }
        }

        for (EnemyMemento state : enemyStates) {
            Enemy enemy = enemiesById.get(state.getEnemyId());
            if (enemy == null) {
                continue;
            }

            enemy.setPosition(new Vector2(state.getX(), state.getY()));
            enemy.setSpeed(state.getSpeed());

            if (enemy instanceof LinearEnemy) {
                ((LinearEnemy) enemy).setDirection(new Vector2(state.getDirectionX(), state.getDirectionY()));
            } else if (enemy instanceof WaypointEnemy) {
                WaypointEnemy waypointEnemy = (WaypointEnemy) enemy;
                waypointEnemy.setWaypoints(state.getWaypoints() == null ? new ArrayList<>() : new ArrayList<>(state.getWaypoints()));
                waypointEnemy.setCurrentWaypointIndex(state.getCurrentWaypointIndex());
            } else if (enemy instanceof AcceleratedEnemy) {
                ((AcceleratedEnemy) enemy).reset();
                enemy.setSpeed(state.getSpeed());
            }
        }
    }

    private void restoreCoins(Level level) throws DhgDomainException {
        List<CoinMemento> coinStates = this.currentMemento.getCoinStates();
        Set<String> collectedCoinIds = this.currentMemento.getCollectedCoinIds();

        Map<String, Coin> coinsById = new HashMap<>();
        for (Entity entity : level.getEntities()) {
            if (entity instanceof Coin) {
                coinsById.put(entity.getId(), (Coin) entity);
            }
        }

        if (coinStates != null && !coinStates.isEmpty()) {
            for (CoinMemento state : coinStates) {
                Coin coin = coinsById.get(state.getCoinId());
                if (coin == null) {
                    continue;
                }

                coin.reset();
                coin.setPosition(new Vector2(state.getX(), state.getY()));
                if (state.isCollected()) {
                    coin.setCollected(true);
                    coin.deactivate();
                }
            }
            return;
        }

        if (collectedCoinIds == null || collectedCoinIds.isEmpty()) {
            return;
        }

        for (Coin coin : coinsById.values()) {
            coin.reset();
            if (collectedCoinIds.contains(coin.getId())) {
                coin.setCollected(true);
                coin.deactivate();
            }
        }
    }

    private void restoreCheckpoints(Level level) throws DhgDomainException {
        List<CheckpointMementoData> checkpointStates = this.currentMemento.getCheckpointStates();
        if (checkpointStates == null || checkpointStates.isEmpty()) {
            return;
        }

        Map<String, Checkpoint> checkpointsById = new HashMap<>();
        for (Checkpoint checkpoint : level.getCheckpoints()) {
            checkpointsById.put(checkpoint.getId(), checkpoint);
        }

        level.setActiveCheckpoint(null);
        for (CheckpointMementoData state : checkpointStates) {
            Checkpoint checkpoint = checkpointsById.get(state.getCheckpointId());
            if (checkpoint == null) {
                continue;
            }

            checkpoint.setRespawnPosition(new Vector2(state.getX(), state.getY()));
            if (state.isActive()) {
                checkpoint.activate();
                level.setActiveCheckpoint(checkpoint);
            } else {
                checkpoint.deactivate();
            }
        }
    }

    private void restoreSpecialElements(Level level) throws DhgDomainException {
        List<SpecialElementMemento> specialStates = this.currentMemento.getSpecialElementStates();
        if (specialStates == null || specialStates.isEmpty()) {
            return;
        }

        Map<String, SpecialElement> specialElementsById = new HashMap<>();
        for (Entity entity : level.getEntities()) {
            if (entity instanceof SpecialElement) {
                specialElementsById.put(entity.getId(), (SpecialElement) entity);
            }
        }

        for (SpecialElementMemento state : specialStates) {
            SpecialElement element = specialElementsById.get(state.getElementId());
            if (element == null) {
                continue;
            }

            element.setPosition(new Vector2(state.getX(), state.getY()));
            if (state.isActive()) {
                element.activate();
            } else {
                element.deactivate();
            }
        }
    }

    /**
     * Checks if a snapshot exists.
     * @return true if a save state is available.
     */
    public boolean hasMemento() throws DhgDomainException {
        return this.currentMemento != null;
    }

    public LevelMemento getCurrentMemento() {
        return this.currentMemento;
    }

    public void setCurrentMemento(LevelMemento currentMemento) {
        this.currentMemento = currentMemento;
    }
}
