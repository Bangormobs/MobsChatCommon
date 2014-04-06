package uk.co.mobsoc.chat.common.packet;

import uk.co.mobsoc.chat.common.MCInputStream;
import uk.co.mobsoc.chat.common.MCOutputStream;
/**
 * Client to Server packet to signify a User or Server has performed a pre-set action. Contains one or two strings which will be placed inside the final action string
 * @author triggerhapp
 *
 */
public class PresetActionPacket extends Packet{
	public String action, s1, s2;
	public PresetActionPacket(){
	}
	public PresetActionPacket(String action, String s1) {
		this(action, s1, "");
	}

	public PresetActionPacket(String action, String s1, String s2) {
		this.action = action; this.s1 = s1; this.s2 = s2;
	}

	@Override
	public void send(MCOutputStream stream) {
		stream.writeString(action);
		stream.writeString(s1);
		stream.writeString(s2);
	}

	@Override
	public void recieve(MCInputStream stream) {
		action = stream.readString();
		s1 = stream.readString();
		s2 = stream.readString();
	}

}
