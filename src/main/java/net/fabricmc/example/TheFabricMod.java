package net.fabricmc.example;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.itemgroup.v1.*;
import net.fabricmc.fabric.api.registry.*;
import net.minecraft.entity.player.*;
import net.minecraft.item.*;
import net.minecraft.registry.*;
import net.minecraft.sound.*;
import net.minecraft.util.*;
import net.minecraft.world.*;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class TheFabricMod implements ModInitializer {
	// This logger is used to write text to the console and the log file.
	// It is considered best practice to use your mod id as the logger's name.
	// That way, it's clear which mod wrote info, warnings, and errors.
	private static final String namespace="the_fabric";

	public static final Logger LOGGER = LoggerFactory.getLogger(namespace);

	public static final FabricItem FABRIC_ITEM = Registry.register(Registries.ITEM,
			new Identifier(namespace, "fabric_item"),
			new FabricItem(new Item.Settings().maxCount(16)));

	public static final ItemGroup ITEM_GROUP = FabricItemGroup.builder(new Identifier(namespace,"the_fabric"))
			.icon(FABRIC_ITEM::getDefaultStack)
			.entries((context, entries) -> entries.add(FABRIC_ITEM))
			.build();

	@Override
	public void onInitialize() {
		// This code runs as soon as Minecraft is in a mod-load-ready state.
		// However, some things (like resources) may still be uninitialized.
		// Proceed with mild caution.
		LOGGER.info("Hello Fabric world!");

		FuelRegistry.INSTANCE.add(FABRIC_ITEM, 300);
	}

	public static class FabricItem extends Item {

		public FabricItem(Settings settings) {
			super(settings);
		}

		@Override
		public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand) {
			user.playSound(SoundEvents.BLOCK_WOOL_BREAK, 1.0F, 1.0F);
			return TypedActionResult.success(user.getStackInHand(hand));
		}
	}


}

