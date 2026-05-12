package domain.memento;

import java.io.Serializable;

/** Snapshot of a coin's runtime state. */
public class CoinMemento implements Serializable {
    private static final long serialVersionUID = 1L;

    private String coinId;
    private boolean collected;
    private double x;
    private double y;

    public CoinMemento(String coinId, boolean collected, double x, double y) {
        this.coinId = coinId;
        this.collected = collected;
        this.x = x;
        this.y = y;
    }

    public String getCoinId() { return this.coinId; }
    public boolean isCollected() { return this.collected; }
    public double getX() { return this.x; }
    public double getY() { return this.y; }
}

