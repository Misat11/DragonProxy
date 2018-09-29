package org.dragonet.proxy.network.translator.flattening;

public class FlattedItem {

	public final String name;
	public final int id;
	
	public FlattedItem(int id, String name) {
		this.id = id;
		this.name = name;
	}
}
