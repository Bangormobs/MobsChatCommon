package uk.co.mobsoc.chat.common.packet;

import java.util.UUID;

import uk.co.mobsoc.chat.common.MCInputStream;
import uk.co.mobsoc.chat.common.MCOutputStream;
/**
 * Packet signifying that a user is trying to add their MC account to their website/DB account
 * @author triggerhapp
 *
 */
public class AccountAddPacket extends Packet {
	public String id, code, mcName;
	public UUID uuid;

	@Override
	public void send(MCOutputStream stream) {
		stream.writeString(id);
		stream.writeString(code);
		stream.writeUUID(uuid);
		stream.writeString(mcName);
	}

	@Override
	public void recieve(MCInputStream stream) {
		id = stream.readString();
		code = stream.readString();
		uuid = stream.readUUID();
		mcName = stream.readString();
	}
}
