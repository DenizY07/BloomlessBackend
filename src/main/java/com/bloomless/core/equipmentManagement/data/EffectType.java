package com.bloomless.core.equipmentManagement.data;

public enum EffectType {
    BURN{
        @Override
        public String toString() {
            return "brennt";
        }
    },
    POISON{
        @Override
        public String toString() {
            return "vergiftet";
        }
    },
    BLEED{
        @Override
        public String toString() {
            return "blutet";
        }
    },
}
