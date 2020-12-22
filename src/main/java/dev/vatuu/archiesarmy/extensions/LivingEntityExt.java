package dev.vatuu.archiesarmy.extensions;

public interface LivingEntityExt {

    default boolean isEnchantable(boolean already) {
        return isEnchantable() && isEnchanted() == already;
    }
    boolean isEnchantable();
    void setEnchanted(boolean b);
    boolean isEnchanted();
    default float getEnchantedScale() {
        return 1.2f;
    }
}
