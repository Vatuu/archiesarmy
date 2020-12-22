package dev.vatuu.archiesarmy.client.extensions;

import net.minecraft.util.Identifier;

public interface EnchantableEntityRenderer<T> {
	Identifier getEnchantedTexture(T entity);
	Identifier getEmissiveTexture();
}
