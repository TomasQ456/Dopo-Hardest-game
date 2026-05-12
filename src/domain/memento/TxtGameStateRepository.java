package domain.memento;

import domain.exception.DhgDomainException;
import domain.math.Vector2;
import domain.skin.SkinRegistry;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class TxtGameStateRepository implements GameStateRepository {

    @Override
    public void saveGame(CheckpointCaretaker caretaker, String path) throws DhgDomainException {
        LevelMemento memento = caretaker.getCurrentMemento();
        if (memento == null) {
            throw new DhgDomainException("Nothing to save.");
        }

        Path filePath = Path.of(path);
        try (BufferedWriter writer = Files.newBufferedWriter(filePath)) {
            writer.write("levelId=" + valueOrEmpty(memento.getLevelId()));
            writer.newLine();

            writer.write("remainingSeconds=" + memento.getRemainingSeconds());
            writer.newLine();

            Set<String> collectedCoinIds = memento.getCollectedCoinIds();
            String collectedCoins = collectedCoinIds == null ? "" : String.join(",", collectedCoinIds);
            writer.write("collectedCoinIds=" + collectedCoins);
            writer.newLine();

            List<PlayerMemento> playerMementos = memento.getPlayerMementos();
            int playerCount = playerMementos == null ? 0 : playerMementos.size();
            writer.write("playerCount=" + playerCount);
            writer.newLine();

            for (int i = 0; i < playerCount; i++) {
                PlayerMemento pm = playerMementos.get(i);
                Vector2 position = pm.getPosition();
                String prefix = "player" + i + ".";

                writer.write(prefix + "deaths=" + pm.getDeaths());
                writer.newLine();
                writer.write(prefix + "score=" + pm.getScore());
                writer.newLine();
                writer.write(prefix + "x=" + (position == null ? 0.0 : position.x));
                writer.newLine();
                writer.write(prefix + "y=" + (position == null ? 0.0 : position.y));
                writer.newLine();
                writer.write(prefix + "skinName=" + (pm.getSkinName() == null ? "" : pm.getSkinName()));
                writer.newLine();
                writer.write(prefix + "shieldHits=" + pm.getShieldHits());
                writer.newLine();
            }

            List<EnemyMemento> enemyStates = memento.getEnemyStates();
            int enemyCount = enemyStates == null ? 0 : enemyStates.size();
            writer.write("enemyCount=" + enemyCount);
            writer.newLine();

            for (int i = 0; i < enemyCount; i++) {
                EnemyMemento em = enemyStates.get(i);
                String prefix = "enemy" + i + ".";
                writer.write(prefix + "type=" + valueOrEmpty(em.getEnemyType()));
                writer.newLine();
                writer.write(prefix + "id=" + valueOrEmpty(em.getEnemyId()));
                writer.newLine();
                writer.write(prefix + "x=" + em.getX());
                writer.newLine();
                writer.write(prefix + "y=" + em.getY());
                writer.newLine();
                writer.write(prefix + "speed=" + em.getSpeed());
                writer.newLine();
                writer.write(prefix + "directionX=" + em.getDirectionX());
                writer.newLine();
                writer.write(prefix + "directionY=" + em.getDirectionY());
                writer.newLine();

                List<Vector2> waypoints = em.getWaypoints();
                int waypointCount = waypoints == null ? 0 : waypoints.size();
                writer.write(prefix + "waypointCount=" + waypointCount);
                writer.newLine();
                for (int j = 0; j < waypointCount; j++) {
                    Vector2 waypoint = waypoints.get(j);
                    writer.write(prefix + "waypoint" + j + ".x=" + waypoint.x);
                    writer.newLine();
                    writer.write(prefix + "waypoint" + j + ".y=" + waypoint.y);
                    writer.newLine();
                }
                writer.write(prefix + "currentWaypointIndex=" + em.getCurrentWaypointIndex());
                writer.newLine();
            }

            List<CoinMemento> coinStates = memento.getCoinStates();
            int coinCount = coinStates == null ? 0 : coinStates.size();
            writer.write("coinCount=" + coinCount);
            writer.newLine();

            for (int i = 0; i < coinCount; i++) {
                CoinMemento cm = coinStates.get(i);
                String prefix = "coin" + i + ".";
                writer.write(prefix + "id=" + valueOrEmpty(cm.getCoinId()));
                writer.newLine();
                writer.write(prefix + "collected=" + cm.isCollected());
                writer.newLine();
                writer.write(prefix + "x=" + cm.getX());
                writer.newLine();
                writer.write(prefix + "y=" + cm.getY());
                writer.newLine();
            }

            List<CheckpointMementoData> checkpointStates = memento.getCheckpointStates();
            int checkpointCount = checkpointStates == null ? 0 : checkpointStates.size();
            writer.write("checkpointCount=" + checkpointCount);
            writer.newLine();

            for (int i = 0; i < checkpointCount; i++) {
                CheckpointMementoData cm = checkpointStates.get(i);
                String prefix = "checkpoint" + i + ".";
                writer.write(prefix + "id=" + valueOrEmpty(cm.getCheckpointId()));
                writer.newLine();
                writer.write(prefix + "active=" + cm.isActive());
                writer.newLine();
                writer.write(prefix + "x=" + cm.getX());
                writer.newLine();
                writer.write(prefix + "y=" + cm.getY());
                writer.newLine();
            }

            List<SpecialElementMemento> specialStates = memento.getSpecialElementStates();
            int specialCount = specialStates == null ? 0 : specialStates.size();
            writer.write("specialCount=" + specialCount);
            writer.newLine();

            for (int i = 0; i < specialCount; i++) {
                SpecialElementMemento sm = specialStates.get(i);
                String prefix = "special" + i + ".";
                writer.write(prefix + "type=" + valueOrEmpty(sm.getElementType()));
                writer.newLine();
                writer.write(prefix + "id=" + valueOrEmpty(sm.getElementId()));
                writer.newLine();
                writer.write(prefix + "active=" + sm.isActive());
                writer.newLine();
                writer.write(prefix + "x=" + sm.getX());
                writer.newLine();
                writer.write(prefix + "y=" + sm.getY());
                writer.newLine();
            }
        } catch (IOException e) {
            throw new DhgDomainException("Failed to save TXT game state: " + e.getMessage());
        }
    }

    @Override
    public CheckpointCaretaker loadGame(String path) throws DhgDomainException {
        Path filePath = Path.of(path);
        try {
            List<String> lines = Files.readAllLines(filePath);
            Map<String, String> values = new LinkedHashMap<>();
            for (String line : lines) {
                if (line == null || line.isBlank()) {
                    continue;
                }
                String[] parts = line.split("=", 2);
                String key = parts[0].trim();
                String value = parts.length > 1 ? parts[1] : "";
                values.put(key, value);
            }

            String levelId = values.getOrDefault("levelId", "");
            double remainingSeconds = Double.parseDouble(requireValue(values, "remainingSeconds"));
            Set<String> collectedCoinIds = new LinkedHashSet<>();
            String collectedCoinsValue = values.getOrDefault("collectedCoinIds", "");
            if (!collectedCoinsValue.isBlank()) {
                Arrays.stream(collectedCoinsValue.split(","))
                        .map(String::trim)
                        .filter(token -> !token.isEmpty())
                        .forEach(collectedCoinIds::add);
            }

            int playerCount = Integer.parseInt(requireValue(values, "playerCount"));
            List<PlayerMemento> playerMementos = new ArrayList<>();
            for (int i = 0; i < playerCount; i++) {
                String prefix = "player" + i + ".";
                int deaths = Integer.parseInt(requireValue(values, prefix + "deaths"));
                int score = Integer.parseInt(requireValue(values, prefix + "score"));
                double x = Double.parseDouble(requireValue(values, prefix + "x"));
                double y = Double.parseDouble(requireValue(values, prefix + "y"));
                String skinName = values.getOrDefault(prefix + "skinName", "");
                int shieldHits = Integer.parseInt(requireValue(values, prefix + "shieldHits"));

                PlayerMemento pm = new PlayerMemento(
                        deaths,
                        score,
                        new Vector2(x, y),
                        SkinRegistry.fromName(skinName.isBlank() ? null : skinName),
                        shieldHits
                );
                playerMementos.add(pm);
            }

            List<EnemyMemento> enemyStates = new ArrayList<>();
            int enemyCount = Integer.parseInt(values.getOrDefault("enemyCount", "0"));
            for (int i = 0; i < enemyCount; i++) {
                String prefix = "enemy" + i + ".";
                String enemyType = values.getOrDefault(prefix + "type", "");
                String enemyId = values.getOrDefault(prefix + "id", "");
                double x = Double.parseDouble(requireValue(values, prefix + "x"));
                double y = Double.parseDouble(requireValue(values, prefix + "y"));
                double speed = Double.parseDouble(requireValue(values, prefix + "speed"));
                double directionX = Double.parseDouble(values.getOrDefault(prefix + "directionX", "0.0"));
                double directionY = Double.parseDouble(values.getOrDefault(prefix + "directionY", "0.0"));
                int waypointCount = Integer.parseInt(values.getOrDefault(prefix + "waypointCount", "0"));
                List<Vector2> waypoints = new ArrayList<>();
                for (int j = 0; j < waypointCount; j++) {
                    double waypointX = Double.parseDouble(requireValue(values, prefix + "waypoint" + j + ".x"));
                    double waypointY = Double.parseDouble(requireValue(values, prefix + "waypoint" + j + ".y"));
                    waypoints.add(new Vector2(waypointX, waypointY));
                }
                int currentWaypointIndex = Integer.parseInt(values.getOrDefault(prefix + "currentWaypointIndex", "0"));

                enemyStates.add(new EnemyMemento(enemyType, enemyId, x, y, speed, directionX, directionY,
                        waypoints, currentWaypointIndex));
            }

            List<CoinMemento> coinStates = new ArrayList<>();
            int coinCount = Integer.parseInt(values.getOrDefault("coinCount", "0"));
            for (int i = 0; i < coinCount; i++) {
                String prefix = "coin" + i + ".";
                String coinId = values.getOrDefault(prefix + "id", "");
                boolean collected = Boolean.parseBoolean(values.getOrDefault(prefix + "collected", "false"));
                double x = Double.parseDouble(requireValue(values, prefix + "x"));
                double y = Double.parseDouble(requireValue(values, prefix + "y"));
                coinStates.add(new CoinMemento(coinId, collected, x, y));
            }

            List<CheckpointMementoData> checkpointStates = new ArrayList<>();
            int checkpointCount = Integer.parseInt(values.getOrDefault("checkpointCount", "0"));
            for (int i = 0; i < checkpointCount; i++) {
                String prefix = "checkpoint" + i + ".";
                String checkpointId = values.getOrDefault(prefix + "id", "");
                boolean active = Boolean.parseBoolean(values.getOrDefault(prefix + "active", "false"));
                double x = Double.parseDouble(requireValue(values, prefix + "x"));
                double y = Double.parseDouble(requireValue(values, prefix + "y"));
                checkpointStates.add(new CheckpointMementoData(checkpointId, active, x, y));
            }

            List<SpecialElementMemento> specialStates = new ArrayList<>();
            int specialCount = Integer.parseInt(values.getOrDefault("specialCount", "0"));
            for (int i = 0; i < specialCount; i++) {
                String prefix = "special" + i + ".";
                String type = values.getOrDefault(prefix + "type", "");
                String id = values.getOrDefault(prefix + "id", "");
                boolean active = Boolean.parseBoolean(values.getOrDefault(prefix + "active", "false"));
                double x = Double.parseDouble(requireValue(values, prefix + "x"));
                double y = Double.parseDouble(requireValue(values, prefix + "y"));
                specialStates.add(new SpecialElementMemento(type, id, active, x, y));
            }

            LevelMemento memento = new LevelMemento(levelId, playerMementos, collectedCoinIds, remainingSeconds,
                    enemyStates, checkpointStates, specialStates, coinStates);
            CheckpointCaretaker caretaker = new CheckpointCaretaker();
            caretaker.setCurrentMemento(memento);
            return caretaker;
        } catch (IOException e) {
            throw new DhgDomainException("Failed to load TXT game state: " + e.getMessage());
        } catch (NumberFormatException e) {
            throw new DhgDomainException("Failed to parse TXT game state: " + e.getMessage());
        }
    }

    private static String requireValue(Map<String, String> values, String key) {
        String value = values.get(key);
        if (value == null) {
            throw new NumberFormatException("Missing value for " + key);
        }
        return value;
    }

    private static String valueOrEmpty(String value) {
        return value == null ? "" : value;
    }
}


