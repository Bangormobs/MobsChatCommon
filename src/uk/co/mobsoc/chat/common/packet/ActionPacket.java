package uk.co.mobsoc.chat.common.packet;

import uk.co.mobsoc.chat.common.Colour;
import uk.co.mobsoc.chat.common.MCInputStream;
import uk.co.mobsoc.chat.common.MCOutputStream;
/**
 * Unidirectional packet to signify a User or Server wishes to add an action to chat
 * @author triggerhapp
 *
 */
public class ActionPacket extends Packet{
	public String action;
	public Colour sourceColour;


	@Override
	public void send(MCOutputStream stream) {
		stream.writeString(action);
		stream.writeColour(sourceColour);
	}

	@Override
	public void recieve(MCInputStream stream) {
		action = stream.readString();
		sourceColour = stream.readColour();
	}
	
	@Override
	public String toString(){
		return "* "+action;
	}

}
