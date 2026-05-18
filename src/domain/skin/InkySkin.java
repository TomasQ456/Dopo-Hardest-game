package domain.skin;
import domain.entity.Player;
import domain.exception.DhgDomainException;

/** Specific skin implementation altering player traits. */
public class InkySkin implements SkinBehavior {
    @Override public double getSpeedMultiplier() throws DhgDomainException { return 1.0; }
    @Override public double getSizeMultiplier() throws DhgDomainException { return 0.8; }
    @Override public void onHit(Player player) throws DhgDomainException {
        if (player == null)
            throw new DhgDomainException(DhgDomainException.ERR_NULL_PLAYER);
        player.registerDeath();
    }
}
