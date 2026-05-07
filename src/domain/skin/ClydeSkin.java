package domain.skin;
import domain.entity.Player;
import domain.exception.DhgDomainException;

/** Specific skin implementation altering player traits. */
public class ClydeSkin implements SkinBehavior {
    @Override public double getSpeedMultiplier() throws DhgDomainException { return 1.0; }
    @Override public double getSizeMultiplier() throws DhgDomainException { return 1.0; }
    @Override public void onHit(Player player) throws DhgDomainException {
        // ClydeSkin provides shield protection
        boolean absorbed = player.absorbHit();
        if (absorbed) {
            player.reduceSpeedAfterHit();
        } else {
            player.registerDeath();
        }
    }
}
