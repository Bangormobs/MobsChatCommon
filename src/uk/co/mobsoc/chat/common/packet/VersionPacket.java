package uk.co.mobsoc.chat.common.packet;

import java.io.IOException;

import uk.co.mobsoc.chat.common.MCInputStream;
import uk.co.mobsoc.chat.common.MCOutputStream;
import uk.co.mobsoc.chat.common.Util;
/**
 * Client to Server packet containing the protocol version. Used by the server to avoid breaking errors
 * @author triggerhapp
 *
 */
public class VersionPacket extends Packet{
	public int Mv=0;
	public int mv=0;
	
	public VersionPacket(){
		this.Mv = Util.MajorVersion;
		this.mv = Util.MinorVersion;
	}
	@Override
	public void send(MCOutputStream stream) {
		try {
			stream.writeInt(Mv);
			stream.writeInt(mv);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}

	@Override
	public void recieve(MCInputStream stream) {
		Mv = 0;
		try {
			Mv = stream.readInt();
			mv = stream.readInt();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	

}
