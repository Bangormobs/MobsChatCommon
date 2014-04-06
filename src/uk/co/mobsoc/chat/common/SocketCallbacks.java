package uk.co.mobsoc.chat.common;

import uk.co.mobsoc.chat.common.packet.Packet;
/**
 * An interface to be used to read all incomming packets from a socket.
 * Each new packet sent by a peer is filled out and then sent to packetRecieved in full.
 * @author triggerhapp
 *
 */
public interface SocketCallbacks {
	/**
	 * This function is called every time a new packet is sent by a peer
	 * @param conn The connection that recieved the packet
	 * @param packet The packet
	 * @return true if packet was used, false if packet was not understood or used
	 */
    public boolean packetRecieved(Connector conn, Packet packet);
    /**
     * This function is called is the peer assosciated with this connector has lost connection for any reason
     * @param conn
     */
    public void connectionLost(Connector conn);
}

