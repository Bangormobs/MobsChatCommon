package uk.co.mobsoc.chat.common.packet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.UUID;

import uk.co.mobsoc.chat.common.Colour;
import uk.co.mobsoc.chat.common.MCInputStream;
import uk.co.mobsoc.chat.common.MCOutputStream;
/**
 * Class containing data to be sent be UserListUpdatePacket
 * @author triggerhapp
 *
 */
public class ServerData {
	public Colour connectionColour=null;
	public String connectionName="";
	public ArrayList<String> userList = new ArrayList<String>();

	public void writeTo(MCOutputStream stream) {

		try {
			stream.writeInt(userList.size());
			for(String uuid: userList){
				stream.writeString(uuid);
			}
			stream.writeString(connectionName);
			stream.writeColour(connectionColour);		

		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	public void readFrom(MCInputStream stream) {
		try {
			userList.clear();
			int i = stream.readInt();
			for(int a = 0;a<i;a++){
				userList.add(stream.readString());
			}
			connectionName = stream.readString();
			connectionColour = stream.readColour();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}



	}

}
