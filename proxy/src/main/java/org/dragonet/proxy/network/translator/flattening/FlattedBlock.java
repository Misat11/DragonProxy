package org.dragonet.proxy.network.translator.flattening;

import java.util.List;

public class FlattedBlock {
	
	public final String name;
	public final List<FlattedBlockState> states;
	public FlattedBlockState defaultState;

	public FlattedBlock(String name, List<FlattedBlockState> states) {
		this.name = name;
		this.states = states;
	}
}
