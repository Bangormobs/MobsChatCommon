package uk.co.mobsoc.chat.common.packet;

import java.io.IOException;

import uk.co.mobsoc.chat.common.MCInputStream;
import uk.co.mobsoc.chat.common.MCOutputStream;
/**
 * Client to Server packet containing data about the connection IP and port, and optionally the map port.
 * @author triggerhapp
 *
 */
public class ServerInfoPacket extends Packet{
	public String hostname="", userlist="", name="Unknown Name"; 
	public int port=-1, mapport=-1;
	public boolean isConnect=false;

	@Override
	public void send(MCOutputStream stream) {
		try {
			stream.writeString(hostname);
			stream.writeInt(port);
			stream.writeInt(mapport);
			stream.writeString(userlist);
			stream.writeBoolean(isConnect);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void recieve(MCInputStream stream) {
		try {
			hostname = stream.readString();
			port = stream.readInt();
			mapport = stream.readInt();
			userlist = stream.readString();
			isConnect = stream.readBoolean();
		} catch (IOException e) {
			e.printStackTrace();
		}

		
		
	}

}
