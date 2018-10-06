package org.dragonet.proxy.network.translator.pe;

import org.dragonet.protocol.packets.SetLocalPlayerAsInitializedPacket;
import org.dragonet.proxy.DragonProxy;
import org.dragonet.proxy.network.UpstreamSession;
import org.dragonet.proxy.network.translator.IPEPacketTranslator;

import com.github.steveice10.packetlib.packet.Packet;

public class PESetLocalPlayerAsInitializedPacketTranslator implements IPEPacketTranslator<SetLocalPlayerAsInitializedPacket>{

	@Override
	public Packet[] translate(UpstreamSession session, SetLocalPlayerAsInitializedPacket packet) {
		DragonProxy.getInstance().getLogger().info("player initialized!");
		return null;
	}

}
