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
		
		// TODO stained glass
		
		// TODO
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
		FlattedBlockState state = translateToPC(slot.id, slot.damage);
		return new ItemStack(state.id, slot.count); // TODO NBT
	}

	public static ItemStack translateToPC(ItemEntry itemEntry) {
		return new ItemStack(itemEntry.getId(), 1);
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

	public static CompoundTag newTileTag(String id, int x, int y, int z) {
		CompoundTag t = new CompoundTag(null);
		t.put(new StringTag("id", id));
		t.put(new IntTag("x", x));
		t.put(new IntTag("y", y));
		t.put(new IntTag("z", z));
		return t;
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
}
