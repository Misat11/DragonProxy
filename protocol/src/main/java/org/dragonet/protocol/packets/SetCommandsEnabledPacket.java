package org.dragonet.protocol.packets;

import org.dragonet.protocol.PEPacket;
import org.dragonet.protocol.ProtocolInfo;

public class SetCommandsEnabledPacket extends PEPacket {
	
	public boolean enabled;

	@Override
	public int pid() {
		return ProtocolInfo.SET_COMMANDS_ENABLED_PACKET;
	}

	@Override
	public void encodePayload() {
		putBoolean(enabled);
	}

	@Override
	public void decodePayload() {
		enabled = getBoolean();
	}

}
