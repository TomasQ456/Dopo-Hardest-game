package controller;

import domain.math.Direction;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class KeyboardInputHandler extends KeyAdapter {

    private static final int KEY_UP    = KeyEvent.VK_UP;
    private static final int KEY_DOWN  = KeyEvent.VK_DOWN;
    private static final int KEY_LEFT  = KeyEvent.VK_LEFT;
    private static final int KEY_RIGHT = KeyEvent.VK_RIGHT;

    private Direction player1Direction = Direction.NONE;

    public KeyboardInputHandler() {
    }

    @Override
    public void keyPressed(KeyEvent e) {
        switch (e.getKeyCode()) {
            case KEY_UP:    player1Direction = Direction.UP; break;
            case KEY_DOWN:  player1Direction = Direction.DOWN; break;
            case KEY_LEFT:  player1Direction = Direction.LEFT; break;
            case KEY_RIGHT: player1Direction = Direction.RIGHT; break;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        switch (e.getKeyCode()) {
            case KEY_UP:
            case KEY_DOWN:
            case KEY_LEFT:
            case KEY_RIGHT:
                player1Direction = Direction.NONE;
                break;
        }
    }

    public Direction getPlayer1Direction() {
        return player1Direction;
    }
}

