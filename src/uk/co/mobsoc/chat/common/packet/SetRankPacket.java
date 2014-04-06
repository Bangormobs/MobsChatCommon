package uk.co.mobsoc.chat.common.packet;

import java.util.UUID;

import uk.co.mobsoc.chat.common.MCInputStream;
import uk.co.mobsoc.chat.common.MCOutputStream;
/**
 * Unidirectional packet to signify a Users rank has to change.
 * @author triggerhapp
 *
 */
public class SetRankPacket extends Packet{
	public UUID uuid;
	public String rank;
	@Override
	public void send(MCOutputStream stream) {
		stream.writeUUID(uuid);
		stream.writeString(rank);
	}

	@Override
	public void recieve(MCInputStream stream) {
		uuid = stream.readUUID();
		rank = stream.readString();
	}

}
