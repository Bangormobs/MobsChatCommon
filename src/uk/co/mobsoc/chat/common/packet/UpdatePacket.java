package uk.co.mobsoc.chat.common.packet;

import java.io.IOException;
import java.sql.Timestamp;

import uk.co.mobsoc.chat.common.MCInputStream;
import uk.co.mobsoc.chat.common.MCOutputStream;
/**
 * Mostly harmless.
 * @author triggerhapp
 *
 */
public class UpdatePacket extends Packet{
	public Timestamp t = new Timestamp(0);
	@Override
	public void send(MCOutputStream stream) {
		try {
			stream.writeLong(t.getTime());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public void recieve(MCInputStream stream) {
		long l = 0;
		try {
			l= stream.readLong();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		t = new Timestamp(l);
	}

}
