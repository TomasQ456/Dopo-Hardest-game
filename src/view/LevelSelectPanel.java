package view;

import javax.swing.*;
import java.awt.*;

public class LevelSelectPanel extends JPanel {

    private static final Color COLOR_BACKGROUND = new Color(30, 144, 255);
    private static final Color COLOR_BTN_BG = Color.LIGHT_GRAY;
    private static final Color COLOR_BTN_PLAY = Color.RED;
    
    private static final int PLAYER_PANEL_W = 200;
    private static final int LEVEL_BTN_SIZE = 70;
    private static final int BUTTON_WIDTH = 180;
    private static final int BUTTON_HEIGHT = 50;
    private static final int BORDER_THICKNESS = 2;

    public interface LevelSelectListener {
        void onLevelSelected(int levelIndex);
        void onPlaySelected();
        void onGameModeChanged(String modeName);
        void onSkinChanged(int playerIndex, String skinName);
    }

    private LevelSelectListener listener;

    private final JComboBox<String> gameModeCombo;
    private final PlayerSetupPanel player1Panel;
    private final PlayerSetupPanel player2Panel;
    private final JButton[] levelButtons = new JButton[5];
    private int selectedLevel = 0;

    public LevelSelectPanel() {
        setLayout(new BorderLayout(5, 5));
        setBackground(COLOR_BACKGROUND);

        // NORTH
        JPanel northPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        northPanel.setBackground(COLOR_BACKGROUND);
        northPanel.add(new JLabel("Select Mode:"));
        gameModeCombo = new JComboBox<>(new String[]{"Single Player", "PvP", "PvM"});
        gameModeCombo.addActionListener(e -> {
            updatePlayer2Visibility();
            if (listener != null) {
                listener.onGameModeChanged(getSelectedGameMode());
            }
        });
        northPanel.add(gameModeCombo);
        add(northPanel, BorderLayout.NORTH);

        // WEST
        player1Panel = new PlayerSetupPanel("Player 1");
        player1Panel.setPreferredSize(new Dimension(PLAYER_PANEL_W, 0));
        add(player1Panel, BorderLayout.WEST);

        // EAST
        player2Panel = new PlayerSetupPanel("Player 2");
        player2Panel.setPreferredSize(new Dimension(PLAYER_PANEL_W, 0));
        player2Panel.setVisible(false); // Default
        add(player2Panel, BorderLayout.EAST);

        // CENTER
        JPanel centerContainer = new JPanel(new BorderLayout());
        centerContainer.setBackground(COLOR_BACKGROUND);
        
        JLabel selectLevelLabel = new JLabel("Select Level", SwingConstants.CENTER);
        selectLevelLabel.setFont(new Font("SansSerif", Font.BOLD, 16));
        centerContainer.add(selectLevelLabel, BorderLayout.NORTH);
        
        JPanel levelButtonsPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10));
        levelButtonsPanel.setBackground(COLOR_BACKGROUND);
        
        for (int i = 0; i < 5; i++) {
            final int levelIndex = i;
            JButton btn = new JButton(String.valueOf(i + 1));
            btn.setBackground(COLOR_BTN_BG);
            btn.setPreferredSize(new Dimension(LEVEL_BTN_SIZE, LEVEL_BTN_SIZE));
            btn.setBorder(BorderFactory.createLineBorder(Color.BLACK, BORDER_THICKNESS, true));
            btn.addActionListener(e -> {
                selectedLevel = levelIndex;
                if (listener != null) {
                    listener.onLevelSelected(levelIndex);
                }
            });
            levelButtons[i] = btn;
            levelButtonsPanel.add(btn);
        }
        centerContainer.add(levelButtonsPanel, BorderLayout.CENTER);
        add(centerContainer, BorderLayout.CENTER);

        // SOUTH
        JPanel southPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        southPanel.setBackground(COLOR_BACKGROUND);
        
        JButton playButton = new JButton("Play");
        playButton.setBackground(COLOR_BTN_PLAY);
        playButton.setForeground(Color.WHITE);
        playButton.setFont(new Font("SansSerif", Font.BOLD, 16));
        playButton.setPreferredSize(new Dimension(BUTTON_WIDTH, BUTTON_HEIGHT));
        playButton.setBorder(BorderFactory.createLineBorder(Color.BLACK, BORDER_THICKNESS, true));
        playButton.addActionListener(e -> {
            if (listener != null) {
                listener.onPlaySelected();
            }
        });
        
        southPanel.add(playButton);
        add(southPanel, BorderLayout.SOUTH);

        // Listeners for skin changes
        player1Panel.addSkinSelectionListener(e -> {
            if (listener != null) listener.onSkinChanged(1, player1Panel.getSelectedSkin());
        });
        player2Panel.addSkinSelectionListener(e -> {
            if (listener != null) listener.onSkinChanged(2, player2Panel.getSelectedSkin());
        });
    }

    public void setListener(LevelSelectListener listener) {
        this.listener = listener;
    }

    private void updatePlayer2Visibility() {
        String mode = (String) gameModeCombo.getSelectedItem();
        boolean show = !"Single Player".equals(mode);
        player2Panel.setVisible(show);
        revalidate();
        repaint();
    }

    public String getSelectedGameMode() {
        return (String) gameModeCombo.getSelectedItem();
    }

    public String getPlayer1Skin() {
        return player1Panel.getSelectedSkin();
    }

    public String getPlayer2Skin() {
        return player2Panel.getSelectedSkin();
    }

    public int getSelectedLevel() {
        return selectedLevel;
    }

    public void highlightLevel(int index) {
        for (int i = 0; i < levelButtons.length; i++) {
            if (i == index) {
                levelButtons[i].setBackground(Color.YELLOW);
            } else {
                levelButtons[i].setBackground(COLOR_BTN_BG);
            }
        }
    }
}

