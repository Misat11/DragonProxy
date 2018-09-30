/*
 * GNU LESSER GENERAL PUBLIC LICENSE
 *                       Version 3, 29 June 2007
 *
 * Copyright (C) 2007 Free Software Foundation, Inc. <http://fsf.org/>
 * Everyone is permitted to copy and distribute verbatim copies
 * of this license document, but changing it is not allowed.
 *
 * You can view LICENCE file for details. 
 *
 * @author The Dragonet Team
 */
package org.dragonet.proxy.network.translator;

import java.util.HashMap;
import java.util.Map;

import com.github.steveice10.mc.protocol.data.game.entity.metadata.ItemStack;
import com.github.steveice10.mc.protocol.data.game.world.block.BlockFace;
import com.github.steveice10.opennbt.tag.builtin.CompoundTag;
import com.github.steveice10.opennbt.tag.builtin.IntTag;
import com.github.steveice10.opennbt.tag.builtin.StringTag;
import com.github.steveice10.opennbt.tag.builtin.Tag;

import java.util.List;

import org.dragonet.common.data.itemsblocks.ItemEntry;
import org.dragonet.common.data.nbt.tag.ListTag;
import org.dragonet.proxy.network.translator.BlockTranslator.FlowerPot;
import org.dragonet.proxy.network.translator.flattening.FlattedBlockState;
import org.dragonet.proxy.network.translator.flattening.FlatteningBlockData;
import org.dragonet.common.data.blocks.GlobalBlockPalette;
import org.dragonet.common.data.inventory.Slot;

public class BlockTranslator {

	public static final int UNSUPPORTED_BLOCK_ID = 248;
	public static final String DRAGONET_COMPOUND = "DragonetNBT";
	public static final Map<FlattedBlockState, ItemEntry> PC_TO_PE_OVERRIDE = new HashMap<>();
	public static final Map<Integer, Map<Integer, FlattedBlockState>> PE_TO_PC_OVERRIDE = new HashMap<>();

	static {
		override("granite", "stone", 1);
		override("polished_granite", "stone", 2);
		override("diorite", "stone", 3);
		override("polished_diorite", "stone", 4);
		override("andesite", "stone", 5);
		override("polished_andesite", "stone", 6);

		override("coarse_dirt", "dirt", 1);

		override("oak_planks", "planks");
		override("spruce_planks", "planks", 1);
		override("birch_planks", "planks", 2);
		override("jungle_planks", "planks", 3);
		override("acacia_planks", "planks", 4);
		override("dark_oak_planks", "planks", 5);

		override("oak_sapling", "sapling");
		override("oak_sapling", properties("stage", "1"), "sapling");
		override("spruce_sapling", "sapling", 1);
		override("spruce_sapling", properties("stage", "1"), "sapling", 1);
		override("birch_sapling", "sapling", 2);
		override("birch_sapling", properties("stage", "1"), "sapling", 2);
		override("jungle_sapling", "sapling", 3);
		override("jungle_sapling", properties("stage", "1"), "sapling", 3);
		override("acacia_sapling", "sapling", 4);
		override("acacia_sapling", properties("stage", "1"), "sapling", 4);
		override("dark_oak_sapling", "sapling", 5);
		override("dark_oak_sapling", properties("stage", "1"), "sapling", 5);

		overrideAgeable("water", "level", 15);
		overrideAgeable("lava", "level", 15);

		override("red_sand", "sand", 1);

		// Are logs correct? TODO
		override("oak_log", properties("axis", "x"), "log");
		override("oak_log", properties("axis", "y"), "log", 4);
		override("oak_log", properties("axis", "z"), "log", 8);

		override("spruce_log", properties("axis", "x"), "log", 1);
		override("spruce_log", properties("axis", "y"), "log", 5);
		override("spruce_log", properties("axis", "z"), "log", 9);

		override("birch_log", properties("axis", "x"), "log", 2);
		override("birch_log", properties("axis", "y"), "log", 6);
		override("birch_log", properties("axis", "z"), "log", 10);

		override("jungle_log", properties("axis", "x"), "log", 3);
		override("jungle_log", properties("axis", "y"), "log", 7);
		override("jungle_log", properties("axis", "z"), "log", 11);

		override("acacia_log", properties("axis", "x"), "log2");
		override("acacia_log", properties("axis", "y"), "log2", 2);
		override("acacia_log", properties("axis", "z"), "log2", 4);

		override("dark_oak_log", properties("axis", "x"), "log2", 1);
		override("dark_oak_log", properties("axis", "y"), "log2", 3);
		override("dark_oak_log", properties("axis", "z"), "log2", 5);

		// TODO add stripped logs and wood, stripped woods

		// TODO leaves

		override("wet_sponge", "sponge", 1);

		// TODO dispenser

		override("chiseled_sandstone", "sandstone", 1);
		override("cut_sandstone", "sandstone", 2);

		// TODO noteblock

		// TODO beds

		// TODO powered rail

		// TODO detector_rail

		// TODO sticky_piston

		override("cobweb", "web");
		override("tallgrass", "grass");
		override("fern", "grass", 1);

		override("dead_bush", "deadbush");

		override("tall_seagrass", properties("half", "upper"), "seagrass", 1);
		override("tall_seagrass", properties("half", "lower"), "seagrass", 2);

		// TODO Piston

		// TODO Piston Head

		override("white_wool", "wool");
		override("orange_wool", "wool", 1);
		override("magenta_wool", "wool", 2);
		override("light_blue_wool", "wool", 3);
		override("yellow_wool", "wool", 4);
		override("lime_wool", "wool", 5);
		override("pink_wool", "wool", 6);
		override("gray_wool", "wool", 7);
		override("light_gray_wool", "wool", 8);
		override("cyan_wool", "wool", 9);
		override("purple_wool", "wool", 10);
		override("blue_wool", "wool", 11);
		override("brown_wool", "wool", 12);
		override("green_wool", "wool", 13);
		override("red_wool", "wool", 14);
		override("black_wool", "wool", 15);

		// TODO Moving piston

		override("dandelion", "yellow_flower");
		override("poppy", "red_flower");
		override("blue_orchid", "red_flower", 1);
		override("allium", "red_flower", 2);
		override("azure_bluet", "red_flower", 3);
		override("red_tulip", "red_flower", 4);
		override("orange_tulip", "red_flower", 5);
		override("white_tulip", "red_flower", 6);
		override("pink_tulip", "red_flower", 7);
		override("oxeye_daisy", "red_flower", 8);

		override("bricks", "brick_block");

		override("torch", "torch", 5);
		override("wall_torch", properties("facing", "north"), "torch", 4);
		override("wall_torch", properties("facing", "south"), "torch", 3);
		override("wall_torch", properties("facing", "west"), "torch", 2);
		override("wall_torch", properties("facing", "east"), "torch", 1);

		// TODO fire

		override("spawner", "mob_spawner");

		overrideStairs("oak_stairs");

		// TODO chest

		// TODO redstone_wire

		overrideAgeable("wheat", "age", 7);

		overrideAgeable("farmland", "moisture", 7);

		// TODO furnace

		// TODO sign

		overrideDoor("oak_door");

		// TODO ladder

		// TODO rail

		overrideStairs("cobblestone_stairs", "stone_stairs");

		// TODO wall sign

		// TODO lever

		override("stone_pressure_plate", properties("powered", "true"), 1);

		overrideDoor("iron_door");

		override("oak_pressure_plate", properties("powered", "true"), 1);
		override("spruce_pressure_plate", properties("powered", "true"), 1);
		override("birch_pressure_plate", properties("powered", "true"), 1);
		override("jungle_pressure_plate", properties("powered", "true"), 1);
		override("acacia_pressure_plate", properties("powered", "true"), 1);
		override("dark_oak_pressure_plate", properties("powered", "true"), 1);

		override("redstone_ore", properties("lit", "true"), "lit_redstone_ore");

		override("redstone_torch", properties("lit", "true"), "redstone_torch", 5);
		override("redstone_torch", properties("lit", "false"), "unlit_redstone_torch", 5);
		override("redstone_wall_torch", properties("lit", "true", "facing", "north"), "redstone_torch", 4);
		override("redstone_wall_torch", properties("lit", "false", "facing", "north"), "unlit_redstone_torch", 4);
		override("redstone_wall_torch", properties("lit", "true", "facing", "east"), "redstone_torch", 1);
		override("redstone_wall_torch", properties("lit", "false", "facing", "east"), "unlit_redstone_torch", 1);
		override("redstone_wall_torch", properties("lit", "true", "facing", "south"), "redstone_torch", 3);
		override("redstone_wall_torch", properties("lit", "false", "facing", "south"), "unlit_redstone_torch", 3);
		override("redstone_wall_torch", properties("lit", "true", "facing", "west"), "redstone_torch", 2);
		override("redstone_wall_torch", properties("lit", "false", "facing", "west"), "unlit_redstone_torch", 2);

		overrideButton("stone_button");

		overrideAgeable("snow", "layers", 1, 8, "snow_layer", -1);

		override("snow_block", "snow");

		overrideAgeable("cactus", "age", 15);

		overrideAgeable("sugar_cane", "age", 15, "reeds");

		override("jukebox", properties("has_record", "true"), 1);

		// TODO is fence overrides correct?
		override("oak_fence", "fence");
		override("spruce_fence", "fence", 1);
		override("birch_fence", "fence", 2);
		override("jungle_fence", "fence", 3);
		override("acacia_fence", "fence", 4);
		override("dark_oak_fence", "fence", 5);

		override("pumpkin", properties("facing", "south"), 0);
		override("pumpkin", properties("facing", "west"), 1);
		override("pumpkin", properties("facing", "north"), 2);
		override("pumpkin", properties("facing", "east"), 3);

		override("nether_portal", "portal");

		override("carved_pumpkin", properties("facing", "south"), 0);
		override("carved_pumpkin", properties("facing", "west"), 1);
		override("carved_pumpkin", properties("facing", "north"), 2);
		override("carved_pumpkin", properties("facing", "east"), 3);

		override("jack_o_lantern", properties("facing", "south"), "lit_pumpkin", 0);
		override("jack_o_lantern", properties("facing", "west"), "lit_pumpkin", 1);
		override("jack_o_lantern", properties("facing", "north"), "lit_pumpkin", 2);
		override("jack_o_lantern", properties("facing", "east"), "lit_pumpkin", 3);

		overrideAgeable("cake", "bites", 6);

		// TODO repeater

		override("white_stained_glass", "stained_glass");
		override("orange_stained_glass", "stained_glass", 1);
		override("magenta_stained_glass", "stained_glass", 2);
		override("light_blue_stained_glass", "stained_glass", 3);
		override("yellow_stained_glass", "stained_glass", 4);
		override("lime_stained_glass", "stained_glass", 5);
		override("pink_stained_glass", "stained_glass", 6);
		override("gray_stained_glass", "stained_glass", 7);
		override("light_gray_stained_glass", "stained_glass", 8);
		override("cyan_stained_glass", "stained_glass", 9);
		override("purple_stained_glass", "stained_glass", 10);
		override("blue_stained_glass", "stained_glass", 11);
		override("brown_stained_glass", "stained_glass", 12);
		override("green_stained_glass", "stained_glass", 13);
		override("red_stained_glass", "stained_glass", 14);
		override("black_stained_glass", "stained_glass", 15);

		overrideTrapdoor("oak_trapdoor", "trapdoor");
		overrideTrapdoor("spruce_trapdoor");
		overrideTrapdoor("birch_trapdoor");
		overrideTrapdoor("jungle_trapdoor");
		overrideTrapdoor("acacia_trapdoor");
		overrideTrapdoor("dark_oak_trapdoor");

		override("infested_stone", "monster_egg");
		override("infested_cobblestone", "monster_egg", 1);
		override("infested_stone_bricks", "monster_egg", 2);
		override("infested_mossy_stone_bricks", "monster_egg", 3);
		override("infested_cracked_stone_bricks", "monster_egg", 4);
		override("infested_chiseled_stone_bricks", "monster_egg", 5);

		override("stone_bricks", "stonebrick");
		override("mossy_stone_bricks", "stonebrick", 1);
		override("cracked_stone_bricks", "stonebrick", 2);
		override("chiseled_stone_bricks", "stonebrick", 3);

		// TODO brown mushroom block

		// TODO red mushroom block

		// TODO mushroom stem

		// TODO iron bars

		// TODO glass pane

		override("melon", "melon_block");

		// TODO attached_pumpkin_stem

		// TODO attached_melon_stem

		overrideAgeable("pumpkin_stem", "age", 7);

		overrideAgeable("melon_stem", "age", 7);

		// TODO vine

		// TODO oak_fence_gate

		overrideStairs("brick_stairs");

		overrideStairs("stone_brick_stairs");

		override("lily_pad", "waterlily");

		override("nether_bricks", "nether_brick_block");

		// TODO nether fence

		overrideStairs("nether_brick_stairs");

		overrideAgeable("nether_wart", "age", 3);

		// TODO brewing stand

		overrideAgeable("cauldron", "level", 3);

		// TODO end_portal_frame

		override("redstone_lamp", properties("lit", "true"), "lit_redstone_lamp");

		// TODO cocoa

		overrideStairs("sandstone_stairs");

		// TODO ender_chest

		// TODO tripwire_hook

		// TODO tripwire

		overrideStairs("spruce_stairs");

		overrideStairs("birch_stairs");

		overrideStairs("jungle_stairs");

		// TODO command_block

		// TODO cobblestone_wall

		// TODO mossy_cobblestone_wall

		// TODO check flower pot
		override("potted_dandelion", "flower_pot", 1);
		override("potted_poppy", "flower_pot", 1);
		override("potted_blue_orchid", "flower_pot", 1);
		override("potted_allium", "flower_pot", 1);
		override("potted_azure_bluet", "flower_pot", 1);
		override("potted_red_tulip", "flower_pot", 1);
		override("potted_orange_tulip", "flower_pot", 1);
		override("potted_white_tulip", "flower_pot", 1);
		override("potted_pink_tulip", "flower_pot", 1);
		override("potted_oxeye_daisy", "flower_pot", 1);
		override("potted_oak_sapling", "flower_pot", 1);
		override("potted_spruce_sapling", "flower_pot", 1);
		override("potted_birch_sapling", "flower_pot", 1);
		override("potted_jungle_sapling", "flower_pot", 1);
		override("potted_acacia_sapling", "flower_pot", 1);
		override("potted_dark_oak_sapling", "flower_pot", 1);
		override("potted_fern", "flower_pot", 1);
		override("potted_dead_bush", "flower_pot", 1);
		override("potted_red_mushroom", "flower_pot", 1);
		override("potted_brown_mushroom", "flower_pot", 1);
		override("potted_cactus", "flower_pot", 1);
		
		overrideAgeable("carrots", "age", 7);
		overrideAgeable("potatoes", "age", 7);
		
		// TODO oak_button
		
		// TODO spruce_button
		
		// TODO birch_button
		
		// TODO jungle_button
		
		// TODO acacia_button
		
		// TODO dark_oak_button
		
		// TODO skeleton_wall_skull

		// TODO skeleton_skull
		
		// TODO witcher_skeleton_wall_skull

		// TODO witcher_skeleton_skull
		
		// TODO zombie_wall_head

		// TODO zombie_head
		
		// TODO player_wall_head

		// TODO player_head
		
		// TODO creeper_wall_head

		// TODO creeper_head
		
		// TODO dragon_wall_head

		// TODO dragon_head
		
		// TODO anvil
		
		// TODO chipped_anvil
		
		// TODO damaged_anvil
		
		// TODO trapped_chest
		
		overrideAgeable("light_weighted_pressure_plate", "power", 15);
		overrideAgeable("heavy_weighted_pressure_plate", "power", 15);
		
		// TODO comparator
		
		// TODO daylight_detector
		
		override("nether_quartz_ore", "quartz_ore");
		
		// TODO hopper
		
		override("chiseled_quartz_block", "quartz_block", 1);
		
		override("quartz_pillar", "quartz_block", 2); // TODO axis
		
		overrideStairs("quartz_stairs");
		
		// TODO activator_rail
		
		// TODO dropper
		
		override("white_terracotta", "stained_hardened_clay");
		override("orange_terracotta", "stained_hardened_clay", 1);
		override("magenta_terracotta", "stained_hardened_clay", 2);
		override("light_blue_terracotta", "stained_hardened_clay", 3);
		override("yellow_terracotta", "stained_hardened_clay", 4);
		override("lime_terracotta", "stained_hardened_clay", 5);
		override("pink_terracotta", "stained_hardened_clay", 6);
		override("gray_terracotta", "stained_hardened_clay", 7);
		override("light_gray_terracotta", "stained_hardened_clay", 8);
		override("cyan_terracotta", "stained_hardened_clay", 9);
		override("purple_terracotta", "stained_hardened_clay", 10);
		override("blue_terracotta", "stained_hardened_clay", 11);
		override("brown_terracotta", "stained_hardened_clay", 12);
		override("green_terracotta", "stained_hardened_clay", 13);
		override("red_terracotta", "stained_hardened_clay", 14);
		override("black_terracotta", "stained_hardened_clay", 15);
		
		// TODO all types of stained_glass_pane
		
		overrideStairs("acacia_stairs");
		overrideStairs("dark_oak_stairs");
		
		overrideTrapdoor("iron_trapdoor");
		
		override("dark_prismarine", "prismarine", 1);
		override("prismarine_bricks", "prismarine", 2);
		
		overrideStairs("prismarine_stairs");
		overrideStairs("prismarine_brick_stairs", "prismarine_bricks_stairs");
		overrideStairs("dark_prismarine_stairs");
		
		// TODO prismarine_slab
		
		// TODO prismarine_brick_slab
		
		// TODO dark_prismarine_slab
		
		override("sea_lantern", "sealantern");
		
		override("hay_block", properties("axis", "x"), 4); // ?
		override("hay_block", properties("axis", "y"), 0);
		override("hay_block", properties("axis", "z"), 8); // ?
		
		override("white_carpet", "carpet");
		override("orange_carpet", "carpet", 1);
		override("magenta_carpet", "carpet", 2);
		override("light_blue_carpet", "carpet", 3);
		override("yellow_carpet", "carpet", 4);
		override("lime_carpet", "carpet", 5);
		override("pink_carpet", "carpet", 6);
		override("gray_carpet", "carpet", 7);
		override("light_gray_carpet", "carpet", 8);
		override("cyan_carpet", "carpet", 9);
		override("purple_carpet", "carpet", 10);
		override("blue_carpet", "carpet", 11);
		override("brown_carpet", "carpet", 12);
		override("green_carpet", "carpet", 13);
		override("red_carpet", "carpet", 14);
		override("black_carpet", "carpet", 15);
		
		override("terracotta", "hardened_clay");
		
		override("sunflower", properties("half", "upper"), "double_plant", 0 | 0x08);  // ?
		override("sunflower", properties("half", "lower"), "double_plant", 0);
		
		override("lilac", properties("half", "upper"), "double_plant", 1 | 0x08); // ?
		override("lilac", properties("half", "lower"), "double_plant", 1);
		
		override("rose_bush", properties("half", "upper"), "double_plant", 4 | 0x08); // ?
		override("rose_bush", properties("half", "lower"), "double_plant", 4);
		
		override("peony", properties("half", "upper"), "double_plant", 5 | 0x08); // ?
		override("peony", properties("half", "lower"), "double_plant", 5);
		
		override("tall_grass", properties("half", "upper"), "double_plant", 2 | 0x08); // ?
		override("tall_grass", properties("half", "lower"), "double_plant", 2);
		
		override("large_fern", properties("half", "upper"), "double_plant", 3 | 0x08); // ?
		override("large_fern", properties("half", "lower"), "double_plant", 3);
		
		// TODO banners
		
		override("chiseled_red_sandstone", "red_sandstone", 1);
		override("cut_red_sandstone", "red_sandstone", 2);
		
		overrideStairs("red_sandstone_stairs");
		
		// TODO oak_slab
		
		// TODO spruce_slab
		
		// TODO birch_slab
		
		// TODO jungle_slab
		
		// TODO acacia_slab
		
		// TODO dark_oak_slab
		
		// TODO stone_slab
		
		// TODO sandstone_slab
		
		// TODO petrified_oak_slab
		
		// TODO cobblestone_slab
		
		// TODO brick_slab
		
		// TODO stone_brick_slab
		
		// TODO nether_brick_slab
		
		// TODO quartz_slab
		
		// TODO red_sandstone_slab
		
		// TODO purpur_slab
		
		// TODO smooth stone
		
		// TODO smooth_sandstone
		
		// TODO smooth_quartz
		
		// TODO smooth_red_sandstone
		
		// TODO spruce_fence_gate
		
		// TODO birch_fence_gate
		
		// TODO jungle_fence_gate
		
		// TODO acacia_fence_gate
		
		// TODO dark_oak_fence_gate
		
		// TODO spruce_fence
		
		// TODO birch_fence
		
		// TODO jungle_fence
		
		// TODO acacia_fence
		
		// TODO dark_oak_fence
		
		overrideDoor("spruce_door");
		overrideDoor("birch_door");
		overrideDoor("jungle_door");
		overrideDoor("acacia_door");
		overrideDoor("dark_oak_door");
		
		// TODO end rod
		
		// TODO chorus_plant
		
		overrideAgeable("chorus_flower", "age", 5);
		
		override("purpur_pillar", properties("axis", "x"), "purpur_block", 6); // ?
		override("purpur_pillar", properties("axis", "y"), "purpur_block", 2);
		override("purpur_pillar", properties("axis", "z"), "purpur_block", 10); // ?
		
		overrideStairs("purpur_stairs");
		
		override("end_stone_bricks", "end_bricks");
		
		overrideAgeable("beetroots", "age", 3, "beetroot");
		
		// TODO repeating_command_block
		
		// TODO chain_command_block
		
		overrideAgeable("frosted_ice", "age", 3);
		
		override("magma_block", "magma");
		
		override("red_nether_bricks", "red_nether_brick");
		
		override("bone_block", properties("axis", "x"), 4); // ?
		override("bone_block", properties("axis", "y"), 0);
		override("bone_block", properties("axis", "z"), 8); // ?
		
		override(FlatteningBlockData.fromNameDefault("structure_void"), new ItemEntry(217)); // ?
		
		// TODO observer
		
		// TODO shulker_box and dyed shulker box
		
		// TODO Glazed Terracotas
		
		override("white_concrete", "concrete");
		override("orange_concrete", "concrete", 1);
		override("magenta_concrete", "concrete", 2);
		override("light_blue_concrete", "concrete", 3);
		override("yellow_concrete", "concrete", 4);
		override("lime_concrete", "concrete", 5);
		override("pink_concrete", "concrete", 6);
		override("gray_concrete", "concrete", 7);
		override("light_gray_concrete", "concrete", 8);
		override("cyan_concrete", "concrete", 9);
		override("purple_concrete", "concrete", 10);
		override("blue_concrete", "concrete", 11);
		override("brown_concrete", "concrete", 12);
		override("green_concrete", "concrete", 13);
		override("red_concrete", "concrete", 14);
		override("black_concrete", "concrete", 15);
		
		override("white_concrete_powder", "concretepowder");
		override("orange_concrete_powder", "concretepowder", 1);
		override("magenta_concrete_powder", "concretepowder", 2);
		override("light_blue_concrete_powder", "concretepowder", 3);
		override("yellow_concrete_powder", "concretepowder", 4);
		override("lime_concrete_powder", "concretepowder", 5);
		override("pink_concrete_powder", "concretepowder", 6);
		override("gray_concrete_powder", "concretepowder", 7);
		override("light_gray_concrete_powder", "concretepowder", 8);
		override("cyan_concrete_powder", "concretepowder", 9);
		override("purple_concrete_powder", "concretepowder", 10);
		override("blue_concrete_powder", "concretepowder", 11);
		override("brown_concrete_powder", "concretepowder", 12);
		override("green_concrete_powder", "concretepowder", 13);
		override("red_concrete_powder", "concretepowder", 14);
		override("black_concrete_powder", "concretepowder", 15);
		
		override("kelp_plant", "kelp"); // TODO kelp and kelp_plant age
		
		// TODO turtle_eggs

		override("tube_coral_block", "coral_block");
		override("brain_coral_block", "coral_block", 1);
		override("bubble_coral_block", "coral_block", 2);
		override("fire_coral_block", "coral_block", 3);
		override("horn_coral_block", "coral_block", 4);
		override("dead_tube_coral_block", "coral_block", 5);
		override("dead_brain_coral_block", "coral_block", 6);
		override("dead_bubble_coral_block", "coral_block", 7);
		override("dead_fire_coral_block", "coral_block", 8);
		override("dead_horn_coral_block", "coral_block", 9);

		override("tube_coral", "coral");
		override("brain_coral", "coral", 1);
		override("bubble_coral", "coral", 2);
		override("fire_coral", "coral", 3);
		override("horn_coral", "coral", 4);
		 // Missing dead variants in bedrock version
		override("dead_tube_coral", "coral");
		override("dead_brain_coral", "coral", 1);
		override("dead_bubble_coral", "coral", 2);
		override("dead_fire_coral", "coral", 3);
		override("dead_horn_coral", "coral", 4);

		// TODO coral fans
		
		// TODO sea_pickles
		
		override("air", "air");
		override("void_air", "air");
		override("cave_air", "air");
		
		// TODO structure_block
	}

	public static ItemEntry translateToPE(int stateID) {
		FlattedBlockState blockState = FlatteningBlockData.fromStateID(stateID);
		if (!PC_TO_PE_OVERRIDE.containsKey(blockState)) {
			FlattedBlockState defaultState = blockState.block.defaultState;
			if (!PC_TO_PE_OVERRIDE.containsKey(defaultState)) {
				return new ItemEntry(GlobalBlockPalette.getIDFromName(blockState.block.name), 0);
			}
			return PC_TO_PE_OVERRIDE.get(defaultState);
		}
		return PC_TO_PE_OVERRIDE.get(blockState);
	}

	public static FlattedBlockState translateToPC(int peId, int damage) {
		if (!PE_TO_PC_OVERRIDE.containsKey(peId)) {
			String name = GlobalBlockPalette.getNameFromID(peId);
			return FlatteningBlockData.fromNameDefault(name);
		}
		if (!PE_TO_PC_OVERRIDE.get(peId).containsKey(damage)) {
			String name = GlobalBlockPalette.getNameFromID(peId);
			return FlatteningBlockData.fromNameDefault(name);
		}
		return PE_TO_PC_OVERRIDE.get(peId).get(damage);
	}

	private static void override(String pcName, String peName) {
		override(pcName, peName, 0);
	}

	private static void override(String pcName, String peName, int peData) {
		FlattedBlockState blockState = FlatteningBlockData.fromNameDefault("minecraft:" + pcName);
		int peId = GlobalBlockPalette.getIDFromName("minecraft:" + peName);
		override(blockState, new ItemEntry(peId, peData));
	}

	private static void override(String pcName, Map<String, String> properties, String peName) {
		override(pcName, properties, peName, 0);
	}

	private static void override(String pcName, Map<String, String> properties, String peName, int peData) {
		FlattedBlockState blockState = FlatteningBlockData.fromNameProperties("minecraft:" + pcName, properties);
		int peId = GlobalBlockPalette.getIDFromName("minecraft:" + peName);
		override(blockState, new ItemEntry(peId, peData));
	}

	private static void override(String pcName, Map<String, String> properties, int peData) {
		FlattedBlockState blockState = FlatteningBlockData.fromNameProperties("minecraft:" + pcName, properties);
		int peId = GlobalBlockPalette.getIDFromName("minecraft:" + pcName);
		override(blockState, new ItemEntry(peId, peData));
	}

	private static void override(FlattedBlockState blockState, ItemEntry entry) {
		if (!PC_TO_PE_OVERRIDE.containsKey(blockState)) {
			PC_TO_PE_OVERRIDE.put(blockState, entry);
		}
		if (!PE_TO_PC_OVERRIDE.containsKey(entry.getId())) {
			PE_TO_PC_OVERRIDE.put(entry.getId(), new HashMap<>());
		}
		if (!PE_TO_PC_OVERRIDE.get(entry.getId()).containsKey(entry.getPEDamage())) {
			PE_TO_PC_OVERRIDE.get(entry.getId()).put(entry.getPEDamage(), blockState);
		}
	}

	private static void overrideStairs(String pcName) {
		overrideStairs(pcName, pcName);
	}

	private static void overrideStairs(String pcName, String peName) {
		// TODO
	}

	private static void overrideDoor(String pcName) {
		overrideDoor(pcName, pcName);
	}

	private static void overrideDoor(String pcName, String peName) {
		// TODO
	}

	private static void overrideTrapdoor(String pcName) {
		overrideTrapdoor(pcName, pcName);
	}

	private static void overrideTrapdoor(String pcName, String peName) {
		// TODO
	}

	private static void overrideButton(String pcName) {
		overrideButton(pcName, pcName);
	}

	private static void overrideButton(String pcName, String peName) {
		// TODO
	}

	private static void overrideAgeable(String pcName, String property, int end) {
		overrideAgeable(pcName, null, property, 0, end, pcName, 0);
	}

	private static void overrideAgeable(String pcName, String property, int end, String peName) {
		overrideAgeable(pcName, null, property, 0, end, peName, 0);
	}

	private static void overrideAgeable(String pcName, String property, int end, String peName, int peOffset) {
		overrideAgeable(pcName, null, property, 0, end, peName, 0);
	}

	private static void overrideAgeable(String pcName, Map<String, String> properties, String property, int end,
			String peName) {
		overrideAgeable(pcName, properties, property, 0, end, peName, 0);
	}

	private static void overrideAgeable(String pcName, Map<String, String> properties, String property, int end,
			String peName, int peOffset) {
		overrideAgeable(pcName, properties, property, 0, end, peName, 0);
	}

	private static void overrideAgeable(String pcName, String property, int start, int end, String peName) {
		overrideAgeable(pcName, null, property, start, end, peName, 0);
	}

	private static void overrideAgeable(String pcName, Map<String, String> properties, String property, int start,
			int end, String peName) {
		overrideAgeable(pcName, properties, property, start, end, peName, 0);
	}

	private static void overrideAgeable(String pcName, String property, int start, int end, String peName,
			int peOffset) {
		overrideAgeable(pcName, null, property, start, end, peName, 0);
	}

	private static void overrideAgeable(String pcName, Map<String, String> properties, String property, int start,
			int end, String peName, int peOffset) {
		if (properties == null) {
			properties = new HashMap<>();
		}
		for (int k = start; k <= end; k++) {
			Map<String, String> itemProperties = new HashMap<>(properties);
			itemProperties.put(property, Integer.toString(k));
			override(pcName, itemProperties, peName, peOffset + k);
		}
	}

	private static Map<String, String> properties(String... args) {
		Map<String, String> map = new HashMap<>();
		String last = null;
		for (String string : args) {
			if (last == null) {
				last = string;
			} else {
				map.put(last, string);
				last = null;
			}
		}
		return map;
	}

	public static BlockFace translateToPC(int face) {
		return BlockFace.values()[Math.abs(face % 6)];
	}

	public static Integer translateFacing(int input) {
		// translate facing
		if (input == 0) // DOWN
			input = 0;
		else if (input == 1) // EAST
			input = 5;
		else if (input == 2) // WEST
			input = 4;
		else if (input == 3) // SOUTH
			input = 3;
		else if (input == 4) // NORTH
			input = 2;
		else if (input == 5) // UP
			input = 1;
		return input;
	}

	public static Integer invertVerticalFacing(int input) {
		// translate facing
		if (input == 5) // EAST
			input = 4;
		else if (input == 4) // WEST
			input = 5;
		else if (input == 3) // SOUTH
			input = 2;
		else if (input == 2) // NORTH
			input = 3;
		return input;
	}

	public enum FlowerPot {
		empty(0), red_flower(1), blue_orchid(2), allium(3), houstonia(4), red_tulip(5), orange_tulip(5), white_tulip(
				5), pink_tulip(5), oxeye_daisy(6), yellow_flower(7), sapling(8), spruce_sapling(8), birch_sapling(
						8), jungle_sapling(8), acacia_sapling(8), dark_oak_sapling(
								8), red_mushroom(9), brown_mushroom(10), deadbush(11), fern(12), cactus(13);

		private int id;

		private FlowerPot(int id) {

		}

		public int getId() {
			return this.id;
		}

		public static FlowerPot byName(String value) {
			for (FlowerPot entry : values())
				if (("minecraft:" + entry.name()).equals(value))
					return entry;
			// DragonProxy.getInstance().getLogger().debug("FlowerPot item not found : " +
			// value);
			return null;
		}
	}

	// WIP
	public static org.dragonet.common.data.nbt.tag.CompoundTag translateBlockEntityToPE(
			com.github.steveice10.opennbt.tag.builtin.CompoundTag input) {
		if (input == null)
			return null;
		org.dragonet.common.data.nbt.tag.CompoundTag output = translateRawNBT(0, input, null);
		if (output.contains("id"))
			switch (output.getString("id")) {
			case "minecraft:bed":
				output.putString("id", "Bed");
				output.putByte("color", output.getInt("color")); // TODO check colors
				output.putByte("isMovable", 0x00);
				break;
			case "minecraft:chest":
				output.putString("id", "Chest");
				break;
			case "minecraft:ender_chest":
				output.putString("id", "EnderChest");
				output.putByte("isMovable", 0x00);
				break;
			case "minecraft:command_block":
				output.putString("id", "CommandBlock");
				break;
			case "minecraft:sign":
				output.putString("id", "Sign");
				if (output.contains("Text1") && output.contains("Text2") && output.contains("Text3")
						&& output.contains("Text4")) {
					StringBuilder signText = new StringBuilder();
					for (int line = 1; line <= 4; line++) {
						String lineText = output.getString("Text" + line);
						if (MessageTranslator.isMessage(lineText))
							signText.append(MessageTranslator.translate(lineText));
						else
							signText.append(lineText);
						if (line != 4)
							signText.append("\n");
						output.remove("Text" + line);
					}
					output.putString("Text", signText.toString());
				}
				break;
			case "minecraft:flower_pot":
				output.putString("id", "FlowerPot");
				output.putInt("data", output.getInt("Data"));
				output.remove("Data");
				FlowerPot item = FlowerPot.byName(output.getString("Item"));
				if (item != null)
					output.putShort("item", item.getId());
				else
					output.putShort("item", 0);
				output.remove("Item");
				output.putByte("isMovable", 0x00);
				break;
			case "minecraft:hopper":
				output.putString("id", "Hopper");
				break;
			case "minecraft:dropper":
				output.putString("id", "Dropper");
				break;
			case "minecraft:dispenser":
				output.putString("id", "Dispenser");
				break;
			case "minecraft:daylight_detector":
				output.putString("id", "DaylightDetector");
				break;
			case "minecraft:shulker_box":
				output.putString("id", "ShulkerBox");
				break;
			case "minecraft:furnace":
				output.putString("id", "Furnace");
				break;
			// case "minecraft:structure_block":
			// output.putString("id", "StructureBlock");
			// break;
			// case "minecraft:end_gateway":
			// output.putString("id", "EndGateway");
			// break;
			// case "minecraft:beacon":
			// output.putString("id", "Beacon");
			// break;
			case "minecraft:end_portal":
				output.putString("id", "EndPortal");
				break;
			// case "minecraft:mob_spawner":
			// output.putString("id", "MobSpawner");
			// break;
			case "minecraft:skull":
				output.putString("id", "Skull");
				output.putByte("isMovable", 0x00);
				break;
			// case "minecraft:banner":
			// output.putString("id", "Banner");
			// break;
			case "minecraft:comparator":
				output.putString("id", "Comparator");
				break;
			// case "minecraft:item_frame":
			// output.putString("id", "ItemFrame");
			// break;
			case "minecraft:jukebox":
				output.putString("id", "Jukebox");
				break;
			// case "minecraft:piston":
			// output.putString("id", "PistonArm");
			// break;
			case "minecraft:noteblock":
				output.putString("id", "Noteblock");
				break;
			case "minecraft:enchanting_table":
				output.putString("id", "EnchantTable");
				break;
			case "minecraft:brewing_stand":
				output.putString("id", "BrewingStand");
				break;
			default:
				// DragonProxy.getInstance().getLogger().debug("Block entitiy not handled : " +
				// output.getString("id") + " " + output.toString());
				return null;
			}
		// if (output.getString("id") == "Sign") //debug
		// System.out.println("translateBlockEntityToPE " + output.toString());
		return output;
	}
	
	@SuppressWarnings("unchecked")
	public static org.dragonet.common.data.nbt.tag.CompoundTag translateRawNBT(int id, Tag pcTag,
			org.dragonet.common.data.nbt.tag.CompoundTag target) {
		if (pcTag != null) {
			String name = pcTag.getName() != null ? pcTag.getName() : "";
			if (target == null)
				target = new org.dragonet.common.data.nbt.tag.CompoundTag(name);
			switch (pcTag.getClass().getSimpleName()) {
			case "ByteArrayTag":
				target.putByteArray(name, (byte[]) pcTag.getValue());
				break;
			case "ByteTag":
				target.putByte(name, (byte) pcTag.getValue());
				break;
			case "DoubleTag":
				target.putDouble(name, (double) pcTag.getValue());
				break;
			case "FloatTag":
				target.putFloat(name, (float) pcTag.getValue());
				break;
			case "IntArrayTag":
				target.putIntArray(name, (int[]) pcTag.getValue());
				break;
			case "IntTag":
				target.putInt(name, (int) pcTag.getValue());
				break;
			case "LongTag":
				target.putLong(name, (long) pcTag.getValue());
				break;
			case "ShortTag":
				target.putShort(name, (short) pcTag.getValue());
				break;
			case "StringTag":
				target.putString(name, (String) pcTag.getValue());
				break;
			case "CompoundTag":
				for (String subName : ((CompoundTag) pcTag).getValue().keySet())
					translateRawNBT(0, ((CompoundTag) pcTag).getValue().get(subName), target);
				break;
			case "ListTag":
				ListTag listTag = new ListTag();
				for (Tag subTag : (List<Tag>) pcTag.getValue())
					listTag.add(translateRawNBT(0, subTag, new org.dragonet.common.data.nbt.tag.CompoundTag()));
				target.putList(listTag);
				break;
			default:
				System.out.println("TAG not implemented : " + pcTag.getClass().getSimpleName());
				break;
			}
		}
		return target;
	}
}
