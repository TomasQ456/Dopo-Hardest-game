package domain.memento;

import domain.skin.BlinkySkin;
import domain.skin.ClydeSkin;
import domain.skin.InkySkin;
import domain.skin.SkinBehavior;

import java.util.Map;

public class SkinRegistry {
    private static final Map<String, SkinBehavior> SKINS = Map.of(
            "BlinkySkin", new BlinkySkin(),
            "InkySkin",   new InkySkin(),
            "ClydeSkin",  new ClydeSkin()
    );

    public static SkinBehavior fromName(String name) {
        return SKINS.getOrDefault(name, new BlinkySkin());
    }
}