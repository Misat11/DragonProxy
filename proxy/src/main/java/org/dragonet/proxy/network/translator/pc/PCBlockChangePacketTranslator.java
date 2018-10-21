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
package org.dragonet.proxy.network.translator.pc;

import com.github.steveice10.mc.protocol.data.game.entity.metadata.Position;
import com.github.steveice10.mc.protocol.data.game.world.block.BlockState;
import com.github.steveice10.mc.protocol.data.game.world.sound.BuiltinSound;
import com.github.steveice10.mc.protocol.packet.ingame.server.world.ServerBlockChangePacket;
import org.dragonet.proxy.network.UpstreamSession;
import org.dragonet.proxy.network.translator.IPCPacketTranslator;
import org.dragonet.proxy.network.translator.BlockTranslator;
import org.dragonet.common.data.blocks.BlockEntry;
import org.dragonet.common.data.blocks.GlobalBlockPalette;
import org.dragonet.protocol.PEPacket;
import org.dragonet.protocol.packets.LevelEventPacket;
import org.dragonet.protocol.packets.LevelSoundEventPacket;
import org.dragonet.protocol.packets.PlaySoundPacket;
import org.dragonet.protocol.packets.UpdateBlockPacket;
import org.dragonet.common.maths.BlockPosition;
import org.dragonet.common.maths.Vector3F;

public class PCBlockChangePacketTranslator implements IPCPacketTranslator<ServerBlockChangePacket> {

    @Override
    public PEPacket[] translate(UpstreamSession session, ServerBlockChangePacket packet) {
        Position pos = packet.getRecord().getPosition();
        BlockState block = packet.getRecord().getBlock();
        if (session.getChunkCache().getBlock(pos) != null) {
            BlockEntry soundentry = BlockTranslator.translateToPE(session.getChunkCache().getBlock(pos).getId());
            if (soundentry.getId() == 0 && session.getChunkCache().translateBlock(pos).getId() != 0) {
                LevelEventPacket pk = new LevelEventPacket();
                pk.eventId = LevelEventPacket.EVENT_PARTICLE_DESTROY;
                pk.position = new Vector3F(pos.getX(), pos.getY(), pos.getZ());
                pk.data = GlobalBlockPalette.getOrCreateRuntimeId(soundentry.getId(), soundentry.getData());
                session.sendPacket(pk);
            } else if (isDoor(soundentry.getId())) {
                if ((soundentry.getData() & 0x4) == 0x4 && (session.getChunkCache().translateBlock(pos).getData() & 0x4) != 0x4) {
                    PlaySoundPacket psp = new PlaySoundPacket();
                    psp.blockPosition = new BlockPosition(pos);
                    psp.name = session.getProxy().getSoundTranslator()
                            .translate(block.getId() == 71 ? BuiltinSound.BLOCK_IRON_DOOR_CLOSE
                                    : BuiltinSound.BLOCK_WOODEN_DOOR_OPEN);
                    psp.volume = 1;
                    psp.pitch = 1;
                    session.sendPacket(psp);
                } else if ((soundentry.getData() & 0x4) != 0x4
                        && (session.getChunkCache().translateBlock(pos).getData() & 0x4) == 0x4) {
                    PlaySoundPacket psp = new PlaySoundPacket();
                    psp.blockPosition = new BlockPosition(pos);
                    psp.name = session.getProxy().getSoundTranslator()
                            .translate(block.getId() == 71 ? BuiltinSound.BLOCK_IRON_DOOR_CLOSE
                                    : BuiltinSound.BLOCK_WOODEN_DOOR_CLOSE);
                    psp.volume = 1;
                    psp.pitch = 1;
                    session.sendPacket(psp);
                }
            } else if (isGate(soundentry.getId())) {
                if ((soundentry.getData() & 0x4) == 0x4 && (session.getChunkCache().translateBlock(pos).getData() & 0x4) != 0x4) {
                    PlaySoundPacket psp = new PlaySoundPacket();
                    psp.blockPosition = new BlockPosition(pos);
                    psp.name = session.getProxy().getSoundTranslator().translate(BuiltinSound.BLOCK_FENCE_GATE_OPEN);
                    psp.volume = 1;
                    psp.pitch = 1;
                    session.sendPacket(psp);
                } else if ((soundentry.getData() & 0x4) != 0x4
                        && (session.getChunkCache().translateBlock(pos).getData() & 0x4) == 0x4) {
                    PlaySoundPacket psp = new PlaySoundPacket();
                    psp.blockPosition = new BlockPosition(pos);
                    psp.name = session.getProxy().getSoundTranslator().translate(BuiltinSound.BLOCK_FENCE_GATE_CLOSE);
                    psp.volume = 1;
                    psp.pitch = 1;
                    session.sendPacket(psp);
                }
            } else if (isTrapdoor(soundentry.getId())) {
                if ((soundentry.getData() & 0x4) == 0x4 && (session.getChunkCache().translateBlock(pos).getData() & 0x4) != 0x4) {
                    PlaySoundPacket psp = new PlaySoundPacket();
                    psp.blockPosition = new BlockPosition(pos);
                    psp.name = session.getProxy().getSoundTranslator()
                            .translate(block.getId() == 167 ? BuiltinSound.BLOCK_IRON_TRAPDOOR_OPEN
                                    : BuiltinSound.BLOCK_WOODEN_TRAPDOOR_OPEN);
                    psp.volume = 1;
                    psp.pitch = 1;
                    session.sendPacket(psp);
                } else if ((soundentry.getData() & 0x4) != 0x4
                        && (session.getChunkCache().translateBlock(pos).getData() & 0x4) == 0x4) {
                    PlaySoundPacket psp = new PlaySoundPacket();
                    psp.blockPosition = new BlockPosition(pos);
                    psp.name = session.getProxy().getSoundTranslator()
                            .translate(block.getId() == 167 ? BuiltinSound.BLOCK_IRON_TRAPDOOR_CLOSE
                                    : BuiltinSound.BLOCK_WOODEN_TRAPDOOR_CLOSE);
                    psp.volume = 1;
                    psp.pitch = 1;
                    session.sendPacket(psp);
                }
            } else if (block.getId() != 0 && session.getChunkCache().getBlock(pos).getId() != block.getId()) {
                build(session, pos, block);
            }
        } else {
            build(session, pos, block);
        }
        // update cache
        try {
            session.getChunkCache().update(pos, block);

            // Save glitchy items in cache
            // Position blockPosition = new Position(pk.blockPosition.x, pk.blockPosition.y,
            // pk.blockPosition.z);
            // session.getBlockCache().checkBlock(entry.getId(), entry.getPEDamage(),
            // blockPosition);
            BlockEntry entry = session.getChunkCache().translateBlock(pos);
            if (entry != null) {
                UpdateBlockPacket pk = new UpdateBlockPacket();
                pk.flags = UpdateBlockPacket.FLAG_NEIGHBORS;
                pk.data = entry.getData();
                pk.id = entry.getId();
                pk.blockPosition = new BlockPosition(pos);
                pk.dataLayer = UpdateBlockPacket.LAYER_NORMAL;
                session.putCachePacket(pk);
                if (entry.isWaterlogged() || entry.getId() == 0) {
                    UpdateBlockPacket water = new UpdateBlockPacket();
                    water.flags = UpdateBlockPacket.FLAG_NEIGHBORS;
                    water.data = 0;
                    water.id = 9;
                    water.blockPosition = new BlockPosition(pos);
                    water.dataLayer = UpdateBlockPacket.LAYER_LIQUID;
                    session.putCachePacket(water);
                }
            }
        } catch (Exception ex) {
            session.getProxy().getLogger().debug("Error when updating block [" + pos.getX() + "," + pos.getY() + ","
                    + pos.getZ() + "] " + block.toString());
            session.getProxy().getLogger().debug(ex.getMessage());
        }
        return null;
    }

    public void build(UpstreamSession session, Position pos, BlockState block) {
        LevelSoundEventPacket pk = new LevelSoundEventPacket();
        pk.sound = LevelSoundEventPacket.Sound.PLACE;
        pk.position = new Vector3F(pos.getX(), pos.getY(), pos.getZ());
        BlockEntry entry = BlockTranslator.translateToPE(block.getId());
        pk.extraData = GlobalBlockPalette.getOrCreateRuntimeId(entry.getId(), entry.getData());
        pk.isGlobal = false;
        pk.pitch = 1;
        session.sendPacket(pk);
    }

    public boolean isDoor(int id) {
        return id == 64 || id == 193 || id == 194 || id == 195 || id == 196 || id == 197 || id == 71;
    }

    public boolean isGate(int id) {
        return id == 107 || id == 183 || id == 184 || id == 185 || id == 186 || id == 187;
    }

    public boolean isTrapdoor(int id) {
        return id == 96 || id == 167;
    }
}
