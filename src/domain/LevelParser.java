package domain;

import domain.level.Level;
import domain.exception.DhgDomainException;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

/**
 * Responsible for translating text/configuration files into live Level objects.
 */
public class LevelParser {
    
    /**
     * Reads a file and instantiates a full Level with a TileMap and Entities.
     * Interacts with all Entity and Tile constructors.
     * @param filePath The path to the level configuration file.
     * @return A fully populated Level object.
     */
    public Level parse(String filePath) throws DhgDomainException {
        if (filePath == null || filePath.isEmpty()) {
            throw new DhgDomainException("Level file path must not be null or empty");
        }

        try {
            BufferedReader reader = new BufferedReader(new FileReader(filePath));
            Level level = new Level();

            // Simple parser: read line by line
            // Format: each line represents a configuration element
            // For now, create a basic empty level
            // In a real implementation, this would parse TileMap, entities, etc.

            reader.close();
            return level;
        } catch (IOException e) {
            throw new DhgDomainException("Failed to parse level file: " + e.getMessage());
        }
    }
}
