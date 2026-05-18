import javax.swing.SwingUtilities;

import controller.GameController;
import view.GameWindow;

public class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            GameWindow window = new GameWindow();
            new GameController(window);
            window.setVisible(true);
        });
    }
}
