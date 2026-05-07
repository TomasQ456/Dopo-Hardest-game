package view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainMenuPanel extends JPanel {

    private static final Color COLOR_BACKGROUND = new Color(173, 216, 230);
    private static final Color COLOR_BUTTON = Color.RED;
    private static final Color COLOR_TITLE = Color.RED;
    
    private static final int BUTTON_WIDTH = 200;
    private static final int BUTTON_HEIGHT = 60;
    private static final int BORDER_THICKNESS = 3;

    public interface MainMenuListener {
        void onPlay();
        void onImport();
        void onExport();
        void onSave();
        void onLoad();
    }

    private MainMenuListener listener;

    public MainMenuPanel() {
        setLayout(new BorderLayout());
        setBackground(COLOR_BACKGROUND);

        JPanel centerPanel = new JPanel(new GridBagLayout());
        centerPanel.setOpaque(false);

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.insets = new Insets(20, 20, 50, 20);
        gbc.anchor = GridBagConstraints.CENTER;

        JLabel titleLabel = new JLabel("THE DOPO HARDEST GAME");
        titleLabel.setFont(new Font("SansSerif", Font.BOLD, 28));
        titleLabel.setForeground(COLOR_TITLE);
        centerPanel.add(titleLabel, gbc);

        gbc.gridy = 1;
        gbc.insets = new Insets(0, 0, 0, 0);
        JButton playButton = new JButton("Play");
        playButton.setBackground(COLOR_BUTTON);
        playButton.setForeground(Color.BLACK);
        playButton.setFont(new Font("SansSerif", Font.BOLD, 18));
        playButton.setPreferredSize(new Dimension(BUTTON_WIDTH, BUTTON_HEIGHT));
        playButton.setBorder(BorderFactory.createLineBorder(Color.BLACK, BORDER_THICKNESS, true));

        playButton.addActionListener(e -> {
            if (listener != null) {
                listener.onPlay();
            }
        });

        centerPanel.add(playButton, gbc);
        add(centerPanel, BorderLayout.CENTER);
    }

    public void setListener(MainMenuListener listener) {
        this.listener = listener;
    }

    public JMenuBar buildMenuBar() {
        JMenuBar menuBar = new JMenuBar();
        JMenu menu = new JMenu("Menu");

        JMenuItem importItem = new JMenuItem("Import");
        importItem.addActionListener(e -> { if (listener != null) listener.onImport(); });
        
        JMenuItem exportItem = new JMenuItem("Export");
        exportItem.addActionListener(e -> { if (listener != null) listener.onExport(); });
        
        JMenuItem saveItem = new JMenuItem("Save");
        saveItem.addActionListener(e -> { if (listener != null) listener.onSave(); });
        
        JMenuItem loadItem = new JMenuItem("Load");
        loadItem.addActionListener(e -> { if (listener != null) listener.onLoad(); });
        
        JMenuItem exitItem = new JMenuItem("Exit");
        exitItem.addActionListener(e -> System.exit(0));

        menu.add(importItem);
        menu.add(exportItem);
        menu.add(saveItem);
        menu.add(loadItem);
        menu.addSeparator();
        menu.add(exitItem);

        menuBar.add(menu);
        return menuBar;
    }
}

