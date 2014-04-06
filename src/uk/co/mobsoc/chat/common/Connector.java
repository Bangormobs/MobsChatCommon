package uk.co.mobsoc.chat.common;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

import uk.co.mobsoc.chat.common.packet.Packet;

/**
 * A basic two way, two threaded, communication over Sockets.
 * @author triggerhapp
 *
 */
public class Connector {
    // Every Connector of any sort must be able to send and recieve
    protected Socket        socket=null;
    protected InputStream   input = null;
    protected OutputStream  output=null;
    public SocketListener   socketListen;
    public SocketSender     socketSend;
	public boolean loopback;

	public Connector(){
	}
	
    //ArrayList<SocketCallbacks> callbacks = new ArrayList<SocketCallbacks>()

	public Connector(Socket Socket, String string) {
        socket = Socket;
        try {
            input = Socket.getInputStream();
            output = Socket.getOutputStream();
        } catch (IOException ex) {
            Logger.getLogger(Connector.class.getName()).log(Level.SEVERE, null, ex);
            return;
        }
        
        socketSend = new SocketSender(this,output);
       	socketListen = new SocketListener(this,input);
        loopback = true;
		// TODO Auto-generated constructor stub
	}

	public void send(Packet packet){
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        MCOutputStream out = new MCOutputStream(baos);
    	int id = Packet.getPacketId(packet);
        try {

            out.writeInt(id);
        } catch (IOException ex) {
            Logger.getLogger(Connector.class.getName()).log(Level.SEVERE, null, ex);
        }
        packet.send(out);
        if(out.size()>0){
            try {
                out.flush();
            } catch (IOException ex) {
                Logger.getLogger(Connector.class.getName()).log(Level.SEVERE, null, ex);
            }
            socketSend.addSend(baos);
        }


    }

    public void destroy() {
    	if(socketSend!=null){
    		socketSend.running=false;
    	}
    	if(socketListen!=null){
    		socketListen.running=false;
    		socketListen.getThread().interrupt();
    	}
    }

    public void addCallback(SocketCallbacks callback) {
        socketListen.callbacks.add(callback);
    }

    public void removeCallback(SocketCallbacks callback) {
        socketListen.callbacks.remove(callback);
    }

	
	public String getDebugName(){
		if(socket==null){ return "NULL"; }
		return this.socket.getInetAddress()+" "+this.socket.getPort();
	}
	
	public void debug(String s){
		//System.out.println("["+getDebugName()+"] "+s);
	}

}

