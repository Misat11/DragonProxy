package org.dragonet.proxy.network.translator;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.dragonet.common.data.inventory.Slot;
import org.dragonet.common.data.itemsblocks.ItemEntry;
import org.dragonet.common.data.nbt.tag.ListTag;
import org.dragonet.proxy.network.translator.BlockTranslator.FlowerPot;
import org.dragonet.proxy.network.translator.flattening.FlattedItem;
import org.dragonet.proxy.network.translator.flattening.FlatteningItemData;

import com.github.steveice10.mc.protocol.data.game.entity.metadata.ItemStack;
import com.github.steveice10.opennbt.tag.builtin.CompoundTag;
import com.github.steveice10.opennbt.tag.builtin.IntTag;
import com.github.steveice10.opennbt.tag.builtin.StringTag;
import com.github.steveice10.opennbt.tag.builtin.Tag;

public class ItemTranslator {
	public static final Map<FlattedItem, ItemEntry> PC_TO_PE_OVERRIDE = new HashMap<>();
	public static final Map<Integer, Map<Integer, FlattedItem>> PE_TO_PC_OVERRIDE = new HashMap<>();

	static {
		
		override("air", 0);

		override("stone", 1);
		override("granite", 1, 1);
		override("polished_granite", 1, 2);
		override("diorite", 1, 3);
		override("polished_diorite", 1, 4);
		override("andesite", 1, 5);
		override("polished_andesite", 1, 6);

		override("grass_block", 2);

		override("dirt", 3);
		override("coarse_dirt", 3, 1);

		override("podzol", 243);

		override("cobblestone", 4);

		override("oak_planks", 5);
		override("spruce_planks", 5, 1);
		override("birch_planks", 5, 2);
		override("jungle_planks", 5, 3);
		override("acacia_planks", 5, 4);
		override("dark_oak_planks", 5, 5);

		override("oak_sapling", 6);
		override("spruce_sapling", 6, 1);
		override("birch_sapling", 6, 2);
		override("jungle_sapling", 6, 3);
		override("acacia_sapling", 6, 4);
		override("dark_oak_sapling", 6, 5);

		override("bedrock", 7);

		override("sand", 12);
		override("red_sand", 12, 1);

		override("gravel", 13);

		override("gold_ore", 14);
		override("iron_ore", 15);
		override("coal_ore", 16);

		override("oak_log", 17);
		override("spruce_log", 17, 1);
		override("birch_log", 17, 2);
		override("jungle_log", 17, 3);
		override("acacia_log", 162);
		override("dark_oak_log", 162, 1);

		override("stripped_oak_log", 265);
		override("stripped_spruce_log", 260);
		override("stripped_birch_log", 261);
		override("stripped_jungle_log", 262);
		override("stripped_acacia_log", 263);
		override("stripped_dark_oak_log", 264);

		override("stripped_oak_wood", 265);
		override("stripped_spruce_wood", 260);
		override("stripped_birch_wood", 261);
		override("stripped_jungle_wood", 262);
		override("stripped_acacia_wood", 263);
		override("stripped_dark_oak_wood", 264); // TODO

		override("oak_wood", 17);
		override("spruce_wood", 17, 1);
		override("birch_wood", 17, 2);
		override("jungle_wood", 17, 3);
		override("acacia_wood", 162);
		override("dark_oak_wood", 162, 1); // TODO

		override("oak_leaves", 18);
		override("spruce_leaves", 18, 1);
		override("birch_leaves", 18, 2);
		override("jungle_leaves", 18, 3);
		override("acacia_leaves", 161);
		override("dark_oak_leaves", 161, 1);

		override("sponge", 19);
		override("wet_sponge", 19, 1);

		override("glass", 20);

		override("lapis_ore", 21);
		override("lapis_block", 22);

		override("dispenser", 23);

		override("sandstone", 24);
		override("chiseled_sandstone", 24, 1);
		override("cut_sandstone", 24, 2);

		override("note_block", 25);

		override("powered_rail", 27);
		override("detector_rail", 28);

		override("sticky_piston", 29);

		override("cobweb", 30);

		override("grass", 31);
		override("fern", 31, 1);
		override("dead_bush", 32);

		override("seagrass", 385);

		override("sea_pickle", 411);

		override("piston", 33);

		override("white_wool", 35);
		override("orange_wool", 35, 1);
		override("magenta_wool", 35, 2);
		override("light_blue_wool", 35, 3);
		override("yellow_wool", 35, 4);
		override("lime_wool", 35, 5);
		override("pink_wool", 35, 6);
		override("gray_wool", 35, 7);
		override("light_gray_wool", 35, 8);
		override("cyan_wool", 35, 9);
		override("purple_wool", 35, 10);
		override("blue_wool", 35, 11);
		override("brown_wool", 35, 12);
		override("green_wool", 35, 13);
		override("red_wool", 35, 14);
		override("black_wool", 35, 15);

		override("dandelion", 37);
		override("poppy", 38);
		override("blue_orchid", 38, 1);
		override("allium", 38, 2);
		override("azure_bluet", 38, 3);
		override("red_tulip", 38, 4);
		override("orange_tulip", 38, 5);
		override("white_tulip", 38, 6);
		override("pink_tulip", 38, 7);
		override("oxeye_daisy", 38, 8);

		override("brown_mushroom", 39);
		override("red_mushroom", 40);

		override("gold_block", 41);
		override("iron_block", 42);

		override("oak_slab", 158);
		override("spruce_slab", 158, 1);
		override("birch_slab", 158, 2);
		override("jungle_slab", 158, 3);
		override("acacia_slab", 158, 4);
		override("dark_oak_slab", 158, 5);
		override("stone_slab", 44);
		override("sandstone_slab", 44, 1);
		override("petrified_oak_slab", 44, 2);
		override("cobblestone_slab", 44, 3);
		override("brick_slab", 44, 4);
		override("stone_brick_slab", 44, 5);
		override("nether_brick_slab", 44, 6);
		override("quartz_slab", 44, 7);
		override("red_sandstone_slab", 182);
		override("purpur_slab", 182, 1);
		override("prismarine_slab", 182, 2);
		override("prismarine_brick_slab", 182, 3);
		override("dark_prismarine_slab", 182, 4);

		override("smooth_quartz", 155); // ?
		override("smooth_red_sandstone", 179); // ?
		override("smooth_sandstone", 24); // ?
		override("smooth_stone", 1); // ?

		override("bricks", 45);

		override("tnt", 46);

		override("bookshelf", 47);

		override("mossy_cobblestone", 48);

		override("obsidian", 49);

		override("torch", 50);

		override("end_rod", 208);
		override("chorus_plant", 240);
		override("chorus_flower", 200);
		override("purpur_block", 201);
		override("purpur_pillar", 201, 1);
		override("purpur_stairs", 203);

		override("spawner", 52);

		override("oak_stairs", 53);

		override("chest", 54);
		override("diamond_ore", 56);
		override("diamond_block", 57);
		override("crafting_table", 58);
		override("farmland", 60);
		override("furnace", 61);
		override("ladder", 65);
		override("rail", 66);
		override("cobblestone_stairs", 67);
		override("lever", 69);
		override("stone_pressure_plate", 70);
		override("oak_pressure_plate", 72);
		override("spruce_pressure_plate", 409);
		override("birch_pressure_plate", 406);
		override("jungle_pressure_plate", 408);
		override("acacia_pressure_plate", 405);
		override("dark_oak_pressure_plate", 407);
		override("redstone_ore", 73);
		override("redstone_torch", 76);
		
		/*override("stone_button");
		override("snow");
		override("ice");
		override("snow_block");
		override("cactus");
		override("clay");
		override("jukebox");
		override("oak_fence");
		override("spruce_fence");
		override("birch_fence");
		override("jungle_fence");
		override("acacia_fence");
		override("dark_oak_fence");
		override("pumpkin");
		override("carved_pumpkin");
		override("netherrack");
		override("soul_sand");
		override("glowstone");
		override("jack_o_lantern");
		override("oak_trapdoor");
		override("spruce_trapdoor");
		override("birch_trapdoor");
		override("jungle_trapdoor");
		override("acacia_trapdoor");
		override("dark_oak_trapdoor");
		override("infested_stone");
		override("infested_cobblestone");
		override("infested_stone_bricks");
		override("infested_mossy_stone_bricks");
		override("infested_cracked_stone_bricks");
		override("infested_chiseled_stone_bricks");
		override("stone_bricks");
		override("mossy_stone_bricks");
		override("cracked_stone_bricks");
		override("chiseled_stone_bricks");
		override("brown_mushroom_block");
		override("red_mushroom_block");
		override("mushroom_stem");
		override("iron_bars");
		override("glass_pane");
		override("melon");
		override("vine");
		override("oak_fence_gate");
		override("spruce_fence_gate");
		override("birch_fence_gate");
		override("jungle_fence_gate");
		override("acacia_fence_gate");
		override("dark_oak_fence_gate");
		override("brick_stairs");
		override("stone_brick_stairs");
		override("mycelium");
		override("lily_pad");
		override("nether_bricks");
		override("nether_brick_fence");
		override("nether_brick_stairs");
		override("enchanting_table");
		override("end_portal_frame");
		override("end_stone");
		override("end_stone_bricks");
		override("dragon_egg");
		override("redstone_lamp");
		override("sandstone_stairs");
		override("emerald_ore");
		override("ender_chest");
		override("tripwire_hook");
		override("emerald_block");
		override("spruce_stairs");
		override("birch_stairs");
		override("jungle_stairs");
		override("command_block");
		override("beacon");
		override("cobblestone_wall");
		override("mossy_cobblestone_wall");
		override("oak_button");
		override("spruce_button");
		override("birch_button");
		override("jungle_button");
		override("acacia_button");
		override("dark_oak_button");
		override("anvil");
		override("chipped_anvil");
		override("damaged_anvil");
		override("trapped_chest");
		override("light_weighted_pressure_plate");
		override("heavy_weighted_pressure_plate");
		override("daylight_detector");
		override("redstone_block");
		override("nether_quartz_ore");
		override("hopper");
		override("chiseled_quartz_block");
		override("quartz_block");
		override("quartz_pillar");
		override("quartz_stairs");
		override("activator_rail");
		override("dropper");
		override("white_terracotta");
		override("orange_terracotta");
		override("magenta_terracotta");
		override("light_blue_terracotta");
		override("yellow_terracotta");
		override("lime_terracotta");
		override("pink_terracotta");
		override("gray_terracotta");
		override("light_gray_terracotta");
		override("cyan_terracotta");
		override("purple_terracotta");
		override("blue_terracotta");
		override("brown_terracotta");
		override("green_terracotta");
		override("red_terracotta");
		override("black_terracotta");
		override("barrier");
		override("iron_trapdoor");
		override("hay_block");
		override("white_carpet");
		override("orange_carpet");
		override("magenta_carpet");
		override("light_blue_carpet");
		override("yellow_carpet");
		override("lime_carpet");
		override("pink_carpet");
		override("gray_carpet");
		override("light_gray_carpet");
		override("cyan_carpet");
		override("purple_carpet");
		override("blue_carpet");
		override("brown_carpet");
		override("green_carpet");
		override("red_carpet");
		override("black_carpet");
		override("terracotta");
		override("coal_block");
		override("packed_ice");
		override("acacia_stairs");
		override("dark_oak_stairs");
		override("slime_block");
		override("grass_path");
		override("sunflower");
		override("lilac");
		override("rose_bush");
		override("peony");
		override("tall_grass");
		override("large_fern");
		override("white_stained_glass");
		override("orange_stained_glass");
		override("magenta_stained_glass");
		override("light_blue_stained_glass");
		override("yellow_stained_glass");
		override("lime_stained_glass");
		override("pink_stained_glass");
		override("gray_stained_glass");
		override("light_gray_stained_glass");
		override("cyan_stained_glass");
		override("purple_stained_glass");
		override("blue_stained_glass");
		override("brown_stained_glass");
		override("green_stained_glass");
		override("red_stained_glass");
		override("black_stained_glass");
		override("white_stained_glass_pane");
		override("orange_stained_glass_pane");
		override("magenta_stained_glass_pane");
		override("light_blue_stained_glass_pane");
		override("yellow_stained_glass_pane");
		override("lime_stained_glass_pane");
		override("pink_stained_glass_pane");
		override("gray_stained_glass_pane");
		override("light_gray_stained_glass_pane");
		override("cyan_stained_glass_pane");
		override("purple_stained_glass_pane");
		override("blue_stained_glass_pane");
		override("brown_stained_glass_pane");
		override("green_stained_glass_pane");
		override("red_stained_glass_pane");
		override("black_stained_glass_pane");
		override("prismarine");
		override("prismarine_bricks");
		override("dark_prismarine");
		override("prismarine_stairs");
		override("prismarine_brick_stairs");
		override("dark_prismarine_stairs");
		override("sea_lantern");
		override("red_sandstone");
		override("chiseled_red_sandstone");
		override("cut_red_sandstone");
		override("red_sandstone_stairs");
		override("repeating_command_block");
		override("chain_command_block");
		override("magma_block");
		override("nether_wart_block");
		override("red_nether_bricks");
		override("bone_block");
		override("structure_void");
		override("observer");
		override("shulker_box");
		override("white_shulker_box");
		override("orange_shulker_box");
		override("magenta_shulker_box");
		override("light_blue_shulker_box");
		override("yellow_shulker_box");
		override("lime_shulker_box");
		override("pink_shulker_box");
		override("gray_shulker_box");
		override("light_gray_shulker_box");
		override("cyan_shulker_box");
		override("purple_shulker_box");
		override("blue_shulker_box");
		override("brown_shulker_box");
		override("green_shulker_box");
		override("red_shulker_box");
		override("black_shulker_box");
		override("white_glazed_terracotta");
		override("orange_glazed_terracotta");
		override("magenta_glazed_terracotta");
		override("light_blue_glazed_terracotta");
		override("yellow_glazed_terracotta");
		override("lime_glazed_terracotta");
		override("pink_glazed_terracotta");
		override("gray_glazed_terracotta");
		override("light_gray_glazed_terracotta");
		override("cyan_glazed_terracotta");
		override("purple_glazed_terracotta");
		override("blue_glazed_terracotta");
		override("brown_glazed_terracotta");
		override("green_glazed_terracotta");
		override("red_glazed_terracotta");
		override("black_glazed_terracotta");
		override("white_concrete");
		override("orange_concrete");
		override("magenta_concrete");
		override("light_blue_concrete");
		override("yellow_concrete");
		override("lime_concrete");
		override("pink_concrete");
		override("gray_concrete");
		override("light_gray_concrete");
		override("cyan_concrete");
		override("purple_concrete");
		override("blue_concrete");
		override("brown_concrete");
		override("green_concrete");
		override("red_concrete");
		override("black_concrete");
		override("white_concrete_powder");
		override("orange_concrete_powder");
		override("magenta_concrete_powder");
		override("light_blue_concrete_powder");
		override("yellow_concrete_powder");
		override("lime_concrete_powder");
		override("pink_concrete_powder");
		override("gray_concrete_powder");
		override("light_gray_concrete_powder");
		override("cyan_concrete_powder");
		override("purple_concrete_powder");
		override("blue_concrete_powder");
		override("brown_concrete_powder");
		override("green_concrete_powder");
		override("red_concrete_powder");
		override("black_concrete_powder");
		override("turtle_egg");
		override("dead_tube_coral_block");
		override("dead_brain_coral_block");
		override("dead_bubble_coral_block");
		override("dead_fire_coral_block");
		override("dead_horn_coral_block");
		override("tube_coral_block");
		override("brain_coral_block");
		override("bubble_coral_block");
		override("fire_coral_block");
		override("horn_coral_block");
		override("tube_coral");
		override("brain_coral");
		override("bubble_coral");
		override("fire_coral");
		override("horn_coral");
		override("dead_brain_coral");
		override("dead_bubble_coral");
		override("dead_fire_coral");
		override("dead_horn_coral");
		override("dead_tube_coral");
		override("tube_coral_fan");
		override("brain_coral_fan");
		override("bubble_coral_fan");
		override("fire_coral_fan");
		override("horn_coral_fan");
		override("dead_tube_coral_fan");
		override("dead_brain_coral_fan");
		override("dead_bubble_coral_fan");
		override("dead_fire_coral_fan");
		override("dead_horn_coral_fan");
		override("blue_ice");
		override("conduit");
		override("iron_door");
		override("oak_door");
		override("spruce_door");
		override("birch_door");
		override("jungle_door");
		override("acacia_door");
		override("dark_oak_door");
		override("repeater");
		override("comparator");
		override("structure_block");
		
		override("turtle_helmet");
		override("scute");
		override("iron_shovel");
		override("iron_pickaxe");
		override("iron_axe");
		override("flint_and_steel");
		override("apple");
		override("bow");
		override("arrow");
		override("coal");
		override("charcoal");
		override("diamond");
		override("iron_ingot");
		override("gold_ingot");
		override("iron_sword");
		override("wooden_sword");
		override("wooden_shovel");
		override("wooden_pickaxe");
		override("wooden_axe");
		override("stone_sword");
		override("stone_shovel");
		override("stone_pickaxe");
		override("stone_axe");
		override("diamond_sword");
		override("diamond_shovel");
		override("diamond_pickaxe");
		override("diamond_axe");
		override("stick");
		override("bowl");
		override("mushroom_stew");
		override("golden_sword");
		override("golden_shovel");
		override("golden_pickaxe");
		override("golden_axe");
		override("string");
		override("feather");
		override("gunpowder");
		override("wooden_hoe");
		override("stone_hoe");
		override("iron_hoe");
		override("diamond_hoe");
		override("golden_hoe");
		override("wheat_seeds");
		override("wheat");
		override("bread");
		override("leather_helmet");
		override("leather_chestplate");
		override("leather_leggings");
		override("leather_boots");
		override("chainmail_helmet");
		override("chainmail_chestplate");
		override("chainmail_leggings");
		override("chainmail_boots");
		override("iron_helmet");
		override("iron_chestplate");
		override("iron_leggings");
		override("iron_boots");
		override("diamond_helmet");
		override("diamond_chestplate");
		override("diamond_leggings");
		override("diamond_boots");
		override("golden_helmet");
		override("golden_chestplate");
		override("golden_leggings");
		override("golden_boots");
		override("flint");
		override("porkchop");
		override("cooked_porkchop");
		override("painting");
		override("golden_apple");
		override("enchanted_golden_apple");
		override("sign");
		override("bucket");
		override("water_bucket");
		override("lava_bucket");
		override("minecart");
		override("saddle");
		override("redstone");
		override("snowball");
		override("oak_boat");
		override("leather");
		override("milk_bucket");
		override("pufferfish_bucket");
		override("salmon_bucket");
		override("cod_bucket");
		override("tropical_fish_bucket");
		override("brick");
		override("clay_ball");
		override("sugar_cane");
		override("kelp");
		override("dried_kelp_block");
		override("paper");
		override("book");
		override("slime_ball");
		override("chest_minecart");
		override("furnace_minecart");
		override("egg");
		override("compass");
		override("fishing_rod");
		override("clock");
		override("glowstone_dust");
		override("cod");
		override("salmon");
		override("tropical_fish");
		override("pufferfish");
		override("cooked_cod");
		override("cooked_salmon");
		override("ink_sac");
		override("rose_red");
		override("cactus_green");
		override("cocoa_beans");
		override("lapis_lazuli");
		override("purple_dye");
		override("cyan_dye");
		override("light_gray_dye");
		override("gray_dye");
		override("pink_dye");
		override("lime_dye");
		override("dandelion_yellow");
		override("light_blue_dye");
		override("magenta_dye");
		override("orange_dye");
		override("bone_meal");
		override("bone");
		override("sugar");
		override("cake");
		override("white_bed");
		override("orange_bed");
		override("magenta_bed");
		override("light_blue_bed");
		override("yellow_bed");
		override("lime_bed");
		override("pink_bed");
		override("gray_bed");
		override("light_gray_bed");
		override("cyan_bed");
		override("purple_bed");
		override("blue_bed");
		override("brown_bed");
		override("green_bed");
		override("red_bed");
		override("black_bed");
		override("cookie");
		override("filled_map");
		override("shears");
		override("melon_slice");
		override("dried_kelp");
		override("pumpkin_seeds");
		override("melon_seeds");
		override("beef");
		override("cooked_beef");
		override("chicken");
		override("cooked_chicken");
		override("rotten_flesh");
		override("ender_pearl");
		override("blaze_rod");
		override("ghast_tear");
		override("gold_nugget");
		override("nether_wart");
		override("potion");
		override("glass_bottle");
		override("spider_eye");
		override("fermented_spider_eye");
		override("blaze_powder");
		override("magma_cream");
		override("brewing_stand");
		override("cauldron");
		override("ender_eye");
		override("glistering_melon_slice");
		override("bat_spawn_egg");
		override("blaze_spawn_egg");
		override("cave_spider_spawn_egg");
		override("chicken_spawn_egg");
		override("cod_spawn_egg");
		override("cow_spawn_egg");
		override("creeper_spawn_egg");
		override("dolphin_spawn_egg");
		override("donkey_spawn_egg");
		override("drowned_spawn_egg");
		override("elder_guardian_spawn_egg");
		override("enderman_spawn_egg");
		override("endermite_spawn_egg");
		override("evoker_spawn_egg");
		override("ghast_spawn_egg");
		override("guardian_spawn_egg");
		override("horse_spawn_egg");
		override("husk_spawn_egg");
		override("llama_spawn_egg");
		override("magma_cube_spawn_egg");
		override("mooshroom_spawn_egg");
		override("mule_spawn_egg");
		override("ocelot_spawn_egg");
		override("parrot_spawn_egg");
		override("phantom_spawn_egg");
		override("pig_spawn_egg");
		override("polar_bear_spawn_egg");
		override("pufferfish_spawn_egg");
		override("rabbit_spawn_egg");
		override("salmon_spawn_egg");
		override("sheep_spawn_egg");
		override("shulker_spawn_egg");
		override("silverfish_spawn_egg");
		override("skeleton_spawn_egg");
		override("skeleton_horse_spawn_egg");
		override("slime_spawn_egg");
		override("spider_spawn_egg");
		override("squid_spawn_egg");
		override("stray_spawn_egg");
		override("tropical_fish_spawn_egg");
		override("turtle_spawn_egg");
		override("vex_spawn_egg");
		override("villager_spawn_egg");
		override("vindicator_spawn_egg");
		override("witch_spawn_egg");
		override("wither_skeleton_spawn_egg");
		override("wolf_spawn_egg");
		override("zombie_spawn_egg");
		override("zombie_horse_spawn_egg");
		override("zombie_pigman_spawn_egg");
		override("zombie_villager_spawn_egg");
		override("experience_bottle");
		override("fire_charge");
		override("writable_book");
		override("written_book");
		override("emerald");
		override("item_frame");
		override("flower_pot");
		override("carrot");
		override("potato");
		override("baked_potato");
		override("poisonous_potato");
		override("map");
		override("golden_carrot");
		override("skeleton_skull");
		override("wither_skeleton_skull");
		override("player_head");
		override("zombie_head");
		override("creeper_head");
		override("dragon_head");
		override("carrot_on_a_stick");
		override("nether_star");
		override("pumpkin_pie");
		override("firework_rocket");
		override("firework_star");
		override("enchanted_book");
		override("nether_brick");
		override("quartz");
		override("tnt_minecart");
		override("hopper_minecart");
		override("prismarine_shard");
		override("prismarine_crystals");
		override("rabbit");
		override("cooked_rabbit");
		override("rabbit_stew");
		override("rabbit_foot");
		override("rabbit_hide");
		override("armor_stand");
		override("iron_horse_armor");
		override("golden_horse_armor");
		override("diamond_horse_armor");
		override("lead");
		override("name_tag");
		override("command_block_minecart");
		override("mutton");
		override("cooked_mutton");
		override("white_banner");
		override("orange_banner");
		override("magenta_banner");
		override("light_blue_banner");
		override("yellow_banner");
		override("lime_banner");
		override("pink_banner");
		override("gray_banner");
		override("light_gray_banner");
		override("cyan_banner");
		override("purple_banner");
		override("blue_banner");
		override("brown_banner");
		override("green_banner");
		override("red_banner");
		override("black_banner");
		override("end_crystal");
		override("chorus_fruit");
		override("popped_chorus_fruit");
		override("beetroot");
		override("beetroot_seeds");
		override("beetroot_soup");
		override("dragon_breath");
		override("splash_potion");
		override("spectral_arrow");
		override("tipped_arrow");
		override("lingering_potion");
		override("shield");
		override("elytra");
		override("spruce_boat");
		override("birch_boat");
		override("jungle_boat");
		override("acacia_boat");
		override("dark_oak_boat");
		override("totem_of_undying");
		override("shulker_shell");
		override("iron_nugget");
		override("knowledge_book");
		override("debug_stick");
		override("music_disc_13");
		override("music_disc_cat");
		override("music_disc_blocks");
		override("music_disc_chirp");
		override("music_disc_far");
		override("music_disc_mall");
		override("music_disc_mellohi");
		override("music_disc_stal");
		override("music_disc_strad");
		override("music_disc_ward");
		override("music_disc_11");
		override("music_disc_wait");
		override("trident");
		override("phantom_membrane");
		override("nautilus_shell");
		override("heart_of_the_sea");*/
	}

	private static void override(String pcName, int id) {
		override(pcName, id, 0);
	}

	private static void override(String pcName, int id, int peData) {
		FlattedItem item = FlatteningItemData.fromName("minecraft:" + pcName);
		override(item, new ItemEntry(id, peData));
	}

	private static void override(FlattedItem item, ItemEntry entry) {
		if (!PC_TO_PE_OVERRIDE.containsKey(item)) {
			PC_TO_PE_OVERRIDE.put(item, entry);
		}
		if (!PE_TO_PC_OVERRIDE.containsKey(entry.getId())) {
			PE_TO_PC_OVERRIDE.put(entry.getId(), new HashMap<>());
		}
		if (!PE_TO_PC_OVERRIDE.get(entry.getId()).containsKey(entry.getPEDamage())) {
			PE_TO_PC_OVERRIDE.get(entry.getId()).put(entry.getPEDamage(), item);
		}
	}

	public static ItemEntry translateToPE(int protocolID) {
		FlattedItem item = FlatteningItemData.fromID(protocolID);
		if (!PC_TO_PE_OVERRIDE.containsKey(item)) {
			return new ItemEntry(1);
		}
		return PC_TO_PE_OVERRIDE.get(item);
	}

	public static FlattedItem translateToPC(int peId, int damage) {
		if (!PE_TO_PC_OVERRIDE.containsKey(peId)) {
			return FlatteningItemData.fromID(1);
		}
		if (!PE_TO_PC_OVERRIDE.get(peId).containsKey(damage)) {
			return FlatteningItemData.fromID(1);
		}
		return PE_TO_PC_OVERRIDE.get(peId).get(damage);
	}

	public static Slot translateSlotToPE(ItemStack item) {
		if (item == null || item.getId() == 0)
			return null;
		Slot slot = new Slot();

		ItemEntry entry = translateToPE(item.getId());
		slot.id = entry.getId();
		slot.damage = entry.getPEDamage() != null ? entry.getPEDamage() : 0;
		slot.count = item.getAmount();
		slot.tag = translateItemNBT(item.getId(), item.getNBT(), null);
		return slot;
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

	public static org.dragonet.common.data.nbt.tag.CompoundTag translateItemNBT(int id, Tag input,
			org.dragonet.common.data.nbt.tag.CompoundTag target) {
		if (input == null)
			return null;
		// do the magic
		org.dragonet.common.data.nbt.tag.CompoundTag output = translateRawNBT(id, input, target);
		// 298 leather_helmet CompoundTag { {color=IntTag(color) { 7039851 }} }
		// 299 leather_chestplate
		// 300 leather_leggings
		// 301 leather_boots
		// 397 skull CompoundTag { {=ListTag { [CompoundTag {
		// {Signature=StringTag(Signature) {
		// Dfpupzf34kyZaa52cSxbbJhrT2KQ+H3DXEz0Ivsws75/pK3RMglcQrT8MMvfcax79DPFsiVHLvO9TD7AH76Oev5+VxjpJKkx9vnSI1Dnl4cpQ/160cHkc1gJaJaVyQhG7x1epH7h1u87U1yiHLw07ri4YkyLk7Zqa4RgGrgNIOrpXgexJ6gepgb14kxO3y+C6mW/9QIKjjlyXXc1XVQc3kYkoWwHB1qatTzYmq4ZB0u50OyIMzcXR7CcrYelm0u+DAIYIcbhwmcpUE6sLkieNLCdlRB9Qi7Bs/tIKs5nVAD8TgEJEWVxxS7iGCirjcjZuk7GoTqleX0TMFvHXyQ6K86VxRafNT9u3znuiKGev40Wl3969/93HtVHvFisH9bdquW5r00zd49h3kQs8xa7gQ5oE3uZ2xWPzOTuHrjEiZu2U3MlT3bnTRuQGIPzDCsRHRseZPEKzObIgyN+UB+EUO2EEiPED42jiFv6j4QFFAYkqxahAbnGlXXJPZ0Vq2GmwPwlIBvxmPuMZT/U9ECEg+j2TsIE766eYCm4GBZwC46Q9061eSKzPMPPrrSapo7i30yeKPuL1M8SnXGTGPVpDEpr63yqmHOEu0HJwjoSSeyViP0nphXOdVGEhHja6MIqyUl6gB9pAaiIDKVWQpFNO6z000FPGw+GcaDT8rc1pdQ=
		// }, Value=StringTag(Value) {
		// eyJ0aW1lc3RhbXAiOjE1MTI5MjM1NTc4MjAsInByb2ZpbGVJZCI6ImVjNTYxNTM4ZjNmZDQ2MWRhZmY1MDg2YjIyMTU0YmNlIiwicHJvZmlsZU5hbWUiOiJBbGV4Iiwic2lnbmF0dXJlUmVxdWlyZWQiOnRydWUsInRleHR1cmVzIjp7fX0=
		// }} }] }, Id=StringTag(Id) { ec561538-f3fd-461d-aff5-086b22154bce },
		// Name=StringTag(Name) { Alex }} }

		boolean handle = false;
		org.dragonet.common.data.nbt.tag.CompoundTag display = new org.dragonet.common.data.nbt.tag.CompoundTag(
				"display");
		if (output.contains("Name")) {
			display.putString("Name", output.getString("Name"));
			output.remove("Name");
			handle = true;
		}
		if (output.contains("Lore")) {
			display.putByteArray("Lore", output.getByteArray("Lore"));
			output.remove("Lore");
			handle = true;
		}

		if (!display.isEmpty())
			output.putCompound("display", display);

		// if (!handle)
		// DragonProxy.getInstance().getLogger().debug("Item NBT not handled : id " + id
		// + ", NBT : " + output.toString());
		return output;
	}

	public static ItemStack translateToPC(Slot slot) {
		FlattedItem item = translateToPC(slot.id, slot.damage);
		return new ItemStack(item.id, slot.count); // TODO NBT
	}

	public static ItemStack translateToPC(ItemEntry itemEntry) {
		return new ItemStack(itemEntry.getId(), 1);
	}

	public static CompoundTag newTileTag(String id, int x, int y, int z) {
		CompoundTag t = new CompoundTag(null);
		t.put(new StringTag("id", id));
		t.put(new IntTag("x", x));
		t.put(new IntTag("y", y));
		t.put(new IntTag("z", z));
		return t;
	}
}
