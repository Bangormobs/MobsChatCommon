package uk.co.mobsoc.chat.common.packet;

import java.util.UUID;

import uk.co.mobsoc.chat.common.MCInputStream;
import uk.co.mobsoc.chat.common.MCOutputStream;
/**
 * Client to Server packet to signify a MC Player has joined a server.
 * @author triggerhapp
 *
 */
public class PlayerJoinedPacket extends Packet{
	public UUID uuid;
	public String name;

	@Override
	public void send(MCOutputStream stream) {
		stream.writeUUID(uuid);
		stream.writeString(name);
	}

	@Override
	public void recieve(MCInputStream stream) {
		uuid = stream.readUUID();
		name = stream.readString();
	}

}
