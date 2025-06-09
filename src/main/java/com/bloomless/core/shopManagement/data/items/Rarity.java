package com.bloomless.core.shopManagement.data.items;

public enum Rarity {
    COMMON{
        @Override
        public String toString() {
            return "Gewöhnlich";
        }
    },
    UNCOMMON{
        @Override
        public String toString() {
            return "Ungewöhnlich";
        }
    },
    RARE{
        @Override
        public String toString() {
            return "Selten";
        }
    },
    EPIC{
        @Override
        public String toString() {
            return "Episch";
        }
    },
    LEGENDARY{
        @Override
        public String toString() {
            return "Legendär";
        }
    }
}
