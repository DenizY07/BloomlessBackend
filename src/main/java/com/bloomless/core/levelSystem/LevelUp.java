package com.bloomless.core.levelSystem;

public class LevelUp {

    public static int getRequiredXpForNextLevel(int currentLevel) {
        // z.B. Basis 100, exponentiell steigend
        return (int) (100 * Math.pow(1.5, currentLevel - 1));
    }

    // Gibt zurück, wie viele XP noch bis zum nächsten Level fehlen
    public static int getXpToNextLevel(int currentLevel, int currentXp) {
        int required = getRequiredXpForNextLevel(currentLevel);
        return Math.max(0, required - currentXp);
    }

    // Fügt XP hinzu, levelt ggf. mehrfach auf und gibt das neue Level und die verbleibenden XP zurück
    public static LevelUpResult addXpAndLevelUp(int currentLevel, int currentXp, int xpToAdd) {
        int xp = currentXp + xpToAdd;
        int level = currentLevel;

        while (xp >= getRequiredXpForNextLevel(level)) {
            xp -= getRequiredXpForNextLevel(level);
            level++;
        }

        int xpToNext = getRequiredXpForNextLevel(level) - xp;

        return new LevelUpResult(level, xp, xpToNext);
    }

    // Hilfsklasse für das Ergebnis
    public static class LevelUpResult {
        public final int level;
        public final int xp;
        public final int xpToNextLevel;

        public LevelUpResult(int level, int xp, int xpToNextLevel) {
            this.level = level;
            this.xp = xp;
            this.xpToNextLevel = xpToNextLevel;
        }
    }

}
