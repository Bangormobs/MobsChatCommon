package uk.co.mobsoc.chat.common.packet;

import java.util.UUID;

import uk.co.mobsoc.chat.common.MCInputStream;
import uk.co.mobsoc.chat.common.MCOutputStream;
/**
 * Unidirectional packet containing a private message from one user to another.
 * @author triggerhapp
 *
 */
public class PrivateMessagePacket extends Packet{
	public String to, message;
	public String from;
	public Integer toI=null, fromI=null;
	
	public PrivateMessagePacket(){
		
	}
	
	//public PrivateMessagePacket(String to, UUID from, String message){
//		this.to = to; this.from = from; this.message = message;
//	}

	@Override
	public void send(MCOutputStream stream) {
		stream.writeString(from);
		stream.writeString(to);
		stream.writeString(message);
	}

	@Override
	public void recieve(MCInputStream stream) {
		from = stream.readString();
		to = stream.readString();
		message = stream.readString();
	}

}
