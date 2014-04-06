package uk.co.mobsoc.chat.common.packet;

import java.io.IOException;

import uk.co.mobsoc.chat.common.MCInputStream;
import uk.co.mobsoc.chat.common.MCOutputStream;
/**
 * Unidirectional packet used to check response time of clients
 * @author triggerhapp
 *
 */
public class PingPacket extends Packet{
	private int pingV;
	public long timestamp;
	
	public PingPacket(){
		
	}
	
	public PingPacket(int value){
		pingV = value;
	}
	
	public int getValue(){
		return pingV;
	}

	@Override
	public void send(MCOutputStream stream) {
		try {
			stream.writeInt(pingV);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}

	@Override
	public void recieve(MCInputStream stream) {
		try {
			pingV = stream.readInt();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
}
