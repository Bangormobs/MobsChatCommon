package uk.co.mobsoc.chat.common;

import java.io.IOException;
import java.io.InputStream;
import java.net.SocketException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

import uk.co.mobsoc.chat.common.packet.Packet;
/**
 * A class used by Connector. Run in a thread to collect all information sent by the peer of the related Connector.
 * @author triggerhapp
 *
 */
public class SocketListener implements Runnable{
    private final MCInputStream input;
    public boolean running=true;
    private Thread thread;
    public ArrayList<SocketCallbacks> callbacks = new ArrayList<SocketCallbacks>();
    Connector conn;
    
    public Thread getThread(){
    	return thread;
    }

    public SocketListener(Connector conn, InputStream input) {
        this.conn = conn;
        this.input = new MCInputStream(input);
        thread = new Thread(this);
        thread.start();
    }


    public void run() {
    	try {
    		while(running){
    			int type=0;
    			try {
    				type = input.readInt();
    			} catch (IOException ex) {
    				for(SocketCallbacks scb : callbacks){
    					scb.connectionLost(conn);
    				}
    				conn.destroy();
    				return;
    			}
    			if(type>=0){
    				Packet packet=Packet.getPacketId(type);
    				if(packet==null){
    					conn.debug("Unknown packet : "+type);
    					for(SocketCallbacks scb : callbacks){
    						scb.connectionLost(conn);
    					}
    					conn.destroy();
    					return;
    				}
    				if(Thread.interrupted()){ return; } 
    				packet.recieve(input);
    				boolean found=false;
    				while(callbacks.size()==0){
    					System.out.println("Blocking Thread until callbacks are added");
    					Thread.sleep(500);
    					
    				}
    				for(SocketCallbacks scb : callbacks){
    					if(scb.packetRecieved(conn, packet)){ found=true; break; }
    				}
    				if(!found){
    					Logger.getLogger(Connector.class.getName()).log(Level.SEVERE, "Packet "+packet.getClass()+" dropped!");
    				}
    			}
    			
    		}
    		input.close();
		} catch (InterruptedException e) {
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}


    }

}

