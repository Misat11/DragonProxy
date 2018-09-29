package org.dragonet.proxy.network.translator.flattening;

import java.io.InputStreamReader;
import java.io.Reader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

public class FlatteningBlockData {
	private static final HashMap<Integer, FlattedBlockState> flattedBlockStates = new HashMap<Integer, FlattedBlockState>();
	private static final HashMap<String, FlattedBlock> stringFlattedBlocks = new HashMap<String, FlattedBlock>();
	
	static {
        Reader reader = new InputStreamReader(FlatteningBlockData.class.getResourceAsStream("/flattening/blocks.json"), StandardCharsets.UTF_8);
        JsonElement jelement = new JsonParser().parse(reader);
        JsonObject jobject = jelement.getAsJsonObject();
        for(Map.Entry<String, JsonElement> entry : jobject.entrySet()) {
        	JsonObject value = entry.getValue().getAsJsonObject();
        	List<FlattedBlockState> states = new ArrayList<FlattedBlockState>();
        	FlattedBlock block = new FlattedBlock(entry.getKey(), states);
        	for (JsonElement state : value.getAsJsonArray("states")) {
        		JsonObject stateObj = state.getAsJsonObject();
        		JsonObject properties = null;
        		if (stateObj.has("properties")) {
        			properties = stateObj.getAsJsonObject("properties");
        		}
        		FlattedBlockState flattedState = new FlattedBlockState(stateObj.get("id").getAsInt(), block, properties);
        		flattedBlockStates.put(flattedState.id, flattedState);
        		if (stateObj.has("default")) {
        			boolean asDefault = stateObj.get("default").getAsBoolean();
        			if (asDefault) {
        				block.defaultState = flattedState;
        			}
        		}
        		states.add(flattedState);
        	}
        	stringFlattedBlocks.put(entry.getKey(), block);
        }
	}
	
	public static FlattedBlockState fromStateID(int id) {
		return flattedBlockStates.get(id);
	}
	
	public static FlattedBlock fromName(String name) {
		return stringFlattedBlocks.get(name);
	}
	
	public static FlattedBlockState fromNameDefault(String name) {
		return stringFlattedBlocks.get(name).defaultState;
	}
	
	public static FlattedBlockState fromNameProperties(String name, Map<String, String> properties) {
		List<FlattedBlockState> list = stringFlattedBlocks.get(name).states;
		for (FlattedBlockState state : list) {
			if (state.properties != null) {
				boolean success = true;
				for (Map.Entry<String, String> entry : properties.entrySet()) {
					success = state.properties.has(entry.getKey());
					if (!success) {
						break;
					}
					success = state.properties.get(entry.getKey()).getAsString().equals(entry.getValue());
					if (!success) {
						break;
					}
				}
				if (success) {
					return state;
				}
			}
		}
		return null;
	}
}
