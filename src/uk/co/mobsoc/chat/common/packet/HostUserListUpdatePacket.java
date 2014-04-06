package uk.co.mobsoc.chat.common.packet;

import java.io.IOException;
import java.util.ArrayList;

import uk.co.mobsoc.chat.common.MCInputStream;
import uk.co.mobsoc.chat.common.MCOutputStream;
/**
 * Unidirectional packet containing a list of users from the server.
 * @author triggerhapp
 *
 */
public class HostUserListUpdatePacket extends Packet{
	public ArrayList<String> userList = new ArrayList<String>();

	@Override
	public void send(MCOutputStream stream) {
		try {
			stream.writeInt(userList.size());
			for(String s: userList){
				stream.writeString(s);
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		
	}

	@Override
	public void recieve(MCInputStream stream) {
		int i=0;
		try {
			i = stream.readInt();
			userList.clear();
			for(int a = 0; a<i; a++){
				userList.add(stream.readString());
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		
	}

}
