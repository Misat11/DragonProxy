package org.dragonet.proxy.network.translator.flattening;

import com.google.gson.JsonObject;

public class FlattedBlockState {
	public final int id;
	public final FlattedBlock block;
	public final JsonObject properties;
	
	public FlattedBlockState(int id, FlattedBlock block, JsonObject properties) {
		this.id = id;
		this.block = block;
		this.properties = properties;
	}
}
