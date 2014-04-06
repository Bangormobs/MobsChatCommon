package uk.co.mobsoc.chat.common.packet;

import java.io.IOException;
import java.util.ArrayList;

import uk.co.mobsoc.chat.common.MCInputStream;
import uk.co.mobsoc.chat.common.MCOutputStream;
/**
 * Server to Client packet with a list of all connected Client and Users, used to see who is online
 * @author triggerhapp
 *
 */
public class UserListUpdatePacket extends Packet{
	public ArrayList<ServerData> servers = new ArrayList<ServerData>();
	
	public UserListUpdatePacket(){
		
	}

	@Override
	public void send(MCOutputStream stream) {
		try {
			stream.writeInt(servers.size());
			for(ServerData sd : servers){
				sd.writeTo(stream);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}

		
	}

	@Override
	public void recieve(MCInputStream stream) {
		try {
			int i = stream.readInt();
			servers.clear();
			for(int a = 0; a<i; a++){
				ServerData sd = new ServerData();
				sd.readFrom(stream);
				servers.add(sd);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}

		
	}

}
