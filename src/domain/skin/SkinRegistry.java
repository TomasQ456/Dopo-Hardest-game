package domain.skin;

import java.util.Map;

public class SkinRegistry {
    private static final Map<String, SkinBehavior> SKINS = Map.of(
            "BlinkySkin", new BlinkySkin(),
            "InkySkin", new InkySkin(),
            "ClydeSkin", new ClydeSkin()
    );

    public static SkinBehavior fromName(String name) {
        if (name == null) {
            return new BlinkySkin();
        }
        return SKINS.getOrDefault(name, new BlinkySkin());
    }
}

