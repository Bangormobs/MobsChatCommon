package uk.co.mobsoc.chat.common.packet;

import java.io.IOException;
import java.util.ArrayList;

import uk.co.mobsoc.chat.common.Colour;
import uk.co.mobsoc.chat.common.MCInputStream;
import uk.co.mobsoc.chat.common.MCOutputStream;
/**
 * Client to Server packet containing login credentials
 * @author triggerhapp
 *
 */
public class ConnectionPacket extends Packet {
	public String connectionName;
	public String password;
	public boolean isHost;
	public boolean isTrueColour;
	public ArrayList<Colour> colourList;
	
	public ConnectionPacket(){
		
	}
	
	public int getUserId(){
		return Integer.parseInt(connectionName);
	}

	public ConnectionPacket(String user, String pass, boolean isHost, boolean isTrueColour, ArrayList<Colour> colourList) {
		this.connectionName = user;
		this.password = pass;
		this.isHost = isHost;
		this.isTrueColour = isTrueColour;
		this.colourList = colourList;
	}

	@Override
	public void send(MCOutputStream stream) {
		stream.writeString(connectionName);
		stream.writeString(password);
		try {
			stream.writeBoolean(isHost);
			stream.writeBoolean(isTrueColour);
			stream.writeInt(colourList.size());
			for(Colour c : colourList){
				stream.writeColour(c);
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	@Override
	public void recieve(MCInputStream stream) {
		connectionName = stream.readString();
		password = stream.readString();
		try {
			isHost = stream.readBoolean();
			isTrueColour = stream.readBoolean();
			int total = stream.readInt();
			colourList = new ArrayList<Colour>();
			for(int i = 0; i < total; i++){
				colourList.add(stream.readColour());
			}
		} catch (IOException e) {
			e.printStackTrace();
		}


	}

}
