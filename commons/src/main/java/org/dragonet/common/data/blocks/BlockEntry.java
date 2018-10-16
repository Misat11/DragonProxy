package org.dragonet.common.data.blocks;

public class BlockEntry {
	
	private Integer id;
	private Integer data;
	private boolean waterlogged;
	
	public BlockEntry(Integer id) {
		this(id, 0);
	}

	public BlockEntry(Integer id, Integer data) {
		this(id, data, false);
	}

	public BlockEntry(Integer id, Integer data, boolean waterlogged) {
		this.id = id;
		this.data = data;
		this.waterlogged = waterlogged;
	}

	public Integer getId() {
		return id;
	}

	public Integer getData() {
		return data;
	}

	public boolean isWaterlogged() {
		return waterlogged;
	}
}
