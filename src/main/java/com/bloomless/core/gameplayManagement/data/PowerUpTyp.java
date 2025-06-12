package com.bloomless.core.gameplayManagement.data;

public enum PowerUpTyp {
    HP,DEF,REGEN,DMG,CRITDMG,CRITRATE,LIFESTEAL,
    LUCK,SPEED,BURN,POISON,BLEED,DOTRESIST,DOTBOOST,
    DOTDURATIONBOOST,DOTHEAL;

    @Override
    public String toString() {
        String name = name();
        name = name.replace("CRITDMG", "Crit DMG")
                .replace("CRITRATE", "Crit Rate")
                .replace("LIFESTEAL", "Life Steal")
                .replace("DOTRESIST", "DoT Resist")
                .replace("DOTBOOST", "DoT Boost")
                .replace("DOTDURATIONBOOST", "DoT Duration Boost")
                .replace("DOTHEAL", "DoT Heal")
                .replace("HP", "HP")
                .replace("DEF", "DEF")
                .replace("REGEN", "Regen")
                .replace("DMG", "DMG")
                .replace("LUCK", "Luck")
                .replace("SPEED", "Speed")
                .replace("BURN", "Burn")
                .replace("POISON", "Poison")
                .replace("BLEED", "Bleed");
        return name;
    }
}
