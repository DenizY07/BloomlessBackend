package com.bloomless.core.levelSystem;

import com.bloomless.core.shopManagement.data.DMGItem;
import com.bloomless.core.shopManagement.data.HPItem;
import com.bloomless.core.shopManagement.data.Item;
import com.bloomless.core.shopManagement.data.UpgradeItem;

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

    public Item levelUpItemWithUpgrade(Item item, UpgradeItem upgradeItem) {
        if (item == null || upgradeItem == null) {
            throw new IllegalArgumentException("Item oder UpgradeItem ist null");
        }
        if (!item.getRarity().equals(upgradeItem.getRarity())) {
            throw new IllegalArgumentException("UpgradeItem und Item haben nicht die gleiche Rarity!");
        }

        int currentLevel;
        int currentXp;
        if (item instanceof DMGItem dmg) {
            currentLevel = dmg.getLevel();
            currentXp = dmg.getXp();
            int newXp = currentXp + upgradeItem.getGivenXP();
            LevelUp.LevelUpResult result = LevelUp.addXpAndLevelUp(currentLevel, newXp, 0);
            dmg.setLevel(result.level);
            dmg.setXp(result.xp);
        } else if (item instanceof HPItem hp) {
            currentLevel = hp.getLevel();
            currentXp = hp.getXp();
            int newXp = currentXp + upgradeItem.getGivenXP();
            LevelUp.LevelUpResult result = LevelUp.addXpAndLevelUp(currentLevel, newXp, 0);
            hp.setLevel(result.level);
            hp.setXp(result.xp);
        } else {
            throw new IllegalArgumentException("Item-Typ nicht unterstützt!");
        }

        return item;
    }

}
