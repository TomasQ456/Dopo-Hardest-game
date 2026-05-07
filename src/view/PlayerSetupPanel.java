package view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class PlayerSetupPanel extends JPanel {

    private static final int IMAGE_WIDTH = 150;
    private static final int IMAGE_HEIGHT = 150;
    private static final int BORDER_THICKNESS = 2;

    private final JLabel characterImageLabel;
    private final JComboBox<String> skinComboBox;

    public PlayerSetupPanel(String playerLabel) {
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        
        JLabel nameLabel = new JLabel(playerLabel);
        nameLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        
        characterImageLabel = new JLabel();
        characterImageLabel.setPreferredSize(new Dimension(IMAGE_WIDTH, IMAGE_HEIGHT));
        characterImageLabel.setMinimumSize(new Dimension(IMAGE_WIDTH, IMAGE_HEIGHT));
        characterImageLabel.setMaximumSize(new Dimension(IMAGE_WIDTH, IMAGE_HEIGHT));
        characterImageLabel.setOpaque(true);
        characterImageLabel.setBackground(Color.GRAY);
        characterImageLabel.setBorder(BorderFactory.createLineBorder(Color.BLACK, BORDER_THICKNESS));
        characterImageLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        characterImageLabel.setHorizontalAlignment(SwingConstants.CENTER);
        
        JLabel skinLabel = new JLabel("Select Character");
        skinLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        
        skinComboBox = new JComboBox<>(new String[]{"Blinky", "Inky", "Clyde"});
        skinComboBox.setAlignmentX(Component.CENTER_ALIGNMENT);
        skinComboBox.setMaximumSize(new Dimension(IMAGE_WIDTH, 30));

        add(Box.createVerticalStrut(10));
        add(nameLabel);
        add(Box.createVerticalStrut(10));
        add(characterImageLabel);
        add(Box.createVerticalStrut(10));
        add(skinLabel);
        add(skinComboBox);
        add(Box.createVerticalStrut(10));
    }

    public String getSelectedSkin() {
        return (String) skinComboBox.getSelectedItem();
    }

    public void setCharacterImage(ImageIcon icon) {
        characterImageLabel.setIcon(icon);
        characterImageLabel.setBackground(null); // Optional: clear background when image is set
        characterImageLabel.revalidate();
        characterImageLabel.repaint();
    }

    public void addSkinSelectionListener(ActionListener l) {
        skinComboBox.addActionListener(l);
    }
}

