package net.fabricmc.example;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.example.basis.BaseStatusEffect;
import net.minecraft.entity.effect.StatusEffectCategory;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.potion.Potion;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class AlchemistsDream implements ModInitializer {
	// This logger is used to write text to the console and the log file.
	// It is considered best practice to use your mod id as the logger's name.
	// That way, it's clear which mod wrote info, warnings, and errors.
	public static final Logger LOGGER = LogManager.getLogger("alchemists_dream");

	public static final BaseStatusEffect TRUE_INVISIBILITY = new BaseStatusEffect(StatusEffectCategory.BENEFICIAL, 0x444455);
	public static final Potion TRUE_INVISIBILITY_POTION = new Potion(new StatusEffectInstance(TRUE_INVISIBILITY, 10 * 60 * 20, 1, false, false));

	@Override
	public void onInitialize() {
		// This code runs as soon as Minecraft is in a mod-load-ready state.
		// However, some things (like resources) may still be uninitialized.
		// Proceed with mild caution.

		LOGGER.info("Initializing Alchemist's Dream!");

		var true_invisibility_id = new Identifier("alchemists_dream", "true_invisibility");
		Registry.register(Registry.STATUS_EFFECT, true_invisibility_id, TRUE_INVISIBILITY);
		Registry.register(Registry.POTION, true_invisibility_id, TRUE_INVISIBILITY_POTION);
	}
}
