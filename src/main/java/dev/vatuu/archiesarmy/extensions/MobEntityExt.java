package dev.vatuu.archiesarmy.extensions;

public interface MobEntityExt {

    default boolean isEnchantable(boolean already) {
        return isEnchantable() && isEnchanted() == already;
    }
    boolean isEnchantable();
    void setEnchanted(boolean b);
    boolean isEnchanted();
}
