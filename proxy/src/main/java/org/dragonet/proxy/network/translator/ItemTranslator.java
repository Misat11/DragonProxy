package org.dragonet.proxy.network.translator;

import java.util.HashMap;
import java.util.Map;

import org.dragonet.common.data.itemsblocks.ItemEntry;
import org.dragonet.proxy.network.translator.flattening.FlattedItem;
import org.dragonet.proxy.network.translator.flattening.FlatteningItemData;

public class ItemTranslator {
	public static final Map<FlattedItem, ItemEntry> PC_TO_PE_OVERRIDE = new HashMap<>();
	public static final Map<Integer, Map<Integer, FlattedItem>> PE_TO_PC_OVERRIDE = new HashMap<>();
	
	static {
		// TODO
	}
	
	private static void override(String pcName, int id) {
		override(pcName, id, 0);
	}
	
	private static void override(String pcName, int id, int peData) {
		FlattedItem item = FlatteningItemData.fromName(pcName);
		override(item, new ItemEntry(id, peData));
	}
	
	private static void override(FlattedItem item, ItemEntry entry) {
		if(!PC_TO_PE_OVERRIDE.containsKey(item)) {
			PC_TO_PE_OVERRIDE.put(item, entry);
		}
		if(!PE_TO_PC_OVERRIDE.containsKey(entry.getId())) {
			PE_TO_PC_OVERRIDE.put(entry.getId(), new HashMap<>());
		}
		if(!PE_TO_PC_OVERRIDE.get(entry.getId()).containsKey(entry.getPEDamage())) {
			PE_TO_PC_OVERRIDE.get(entry.getId()).put(entry.getPEDamage(), item);
		}
	}
}
