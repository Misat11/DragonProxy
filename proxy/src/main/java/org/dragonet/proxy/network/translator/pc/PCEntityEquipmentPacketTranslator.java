package org.dragonet.proxy.network.translator.pc;

import com.github.steveice10.mc.protocol.data.game.entity.metadata.ItemStack;
import com.github.steveice10.mc.protocol.packet.ingame.server.entity.ServerEntityEquipmentPacket;
import org.dragonet.proxy.network.CacheKey;
import org.dragonet.proxy.network.UpstreamSession;
import org.dragonet.proxy.network.cache.CachedEntity;
import org.dragonet.proxy.network.translator.IPCPacketTranslator;
import org.dragonet.proxy.network.translator.BlockTranslator;
import org.dragonet.protocol.PEPacket;
import org.dragonet.protocol.packets.MobEquipmentPacket;

public class PCEntityEquipmentPacketTranslator implements IPCPacketTranslator<ServerEntityEquipmentPacket> {

    @Override
    public PEPacket[] translate(UpstreamSession session, ServerEntityEquipmentPacket packet) {
        CachedEntity entity = session.getEntityCache().getByRemoteEID(packet.getEntityId());
        if (entity == null) {
            if (packet.getEntityId() == (int) session.getDataCache().get(CacheKey.PLAYER_EID)) {
                entity = session.getEntityCache().getClientEntity();
            } else {
                return null;
            }
        }

        ItemStack items = packet.getItem();
        boolean handModified = false;

        switch (packet.getSlot()) {
            case HELMET:
                entity.helmet = BlockTranslator.translateSlotToPE(items);
                break;
            case CHESTPLATE:
                entity.chestplate = BlockTranslator.translateSlotToPE(items);
                break;
            case LEGGINGS:
                entity.leggings = BlockTranslator.translateSlotToPE(items);
                break;
            case BOOTS:
                entity.boots = BlockTranslator.translateSlotToPE(items);
                break;
            case MAIN_HAND:
                entity.mainHand = BlockTranslator.translateSlotToPE(items);
            case OFF_HAND:
                handModified = true;
                break;
        }
        entity.updateEquipment(session);

        if (handModified) {
            MobEquipmentPacket equipPacket = new MobEquipmentPacket();
            equipPacket.rtid = entity.proxyEid;
            equipPacket.item = entity.mainHand;
            equipPacket.inventorySlot = 0;
            equipPacket.hotbarSlot = 0;
            equipPacket.windowId = 0;
            session.sendPacket(equipPacket);
        }
        return null;

    }
}