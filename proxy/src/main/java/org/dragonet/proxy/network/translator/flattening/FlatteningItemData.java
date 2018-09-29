package org.dragonet.proxy.network.translator.flattening;

import java.io.InputStreamReader;
import java.io.Reader;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

public class FlatteningItemData {

	private static final HashMap<String, FlattedItem> flattedItems = new HashMap<String, FlattedItem>();
	private static final HashMap<Integer, FlattedItem> flattedItemsIds = new HashMap<Integer, FlattedItem>();
	
	static {
        Reader reader = new InputStreamReader(FlatteningItemData.class.getResourceAsStream("/flattening/items.json"), StandardCharsets.UTF_8);
        JsonElement jelement = new JsonParser().parse(reader);
        JsonObject jobject = jelement.getAsJsonObject();
        for(Map.Entry<String, JsonElement> entry : jobject.entrySet()) {
        	JsonObject value = entry.getValue().getAsJsonObject();
        	FlattedItem item = new FlattedItem(value.get("protocol_id").getAsInt(), entry.getKey());
        	flattedItems.put(entry.getKey(), item);
        	flattedItemsIds.put(value.get("protocol_id").getAsInt(), item);
        }
	}
	
	public static FlattedItem fromName(String name) {
		return flattedItems.get(name);
	}
	
	public static FlattedItem fromID(int id) {
		return flattedItemsIds.get(id);
	}
}
