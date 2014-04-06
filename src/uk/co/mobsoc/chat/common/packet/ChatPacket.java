package uk.co.mobsoc.chat.common.packet;

import java.util.UUID;

import uk.co.mobsoc.chat.common.Colour;
import uk.co.mobsoc.chat.common.MCInputStream;
import uk.co.mobsoc.chat.common.MCOutputStream;
/**
 * Unidirectional packet signifying a User has spoken in chat
 * @author triggerhapp
 *
 */
public class ChatPacket extends Packet{
	public String message, prefix="", name="";

	public Colour sourceColour;

	@Override
	public void send(MCOutputStream stream) {
		stream.writeString(message);
		stream.writeString(name);
		//stream.writeString(prefix);

		stream.writeColour(sourceColour);
		
	}

	@Override
	public void recieve(MCInputStream stream) {
		message = stream.readString();
		name = stream.readString();
		//prefix = stream.readString();
		sourceColour = stream.readColour();

	}
	
	@Override
	public String toString(){
		return name+" : "+message;
	}

}
