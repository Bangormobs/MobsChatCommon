package uk.co.mobsoc.chat.common;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.Socket;
import java.net.SocketException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
/**
 * A class used by Connector. Run in a thread to send all information to the peer of the related Connector.
 * @author triggerhapp
 *
 */
public class SocketSender implements Runnable{
    Thread thread;
    private List<ByteArrayOutputStream> list;
    boolean running=true;
    OutputStream output;
    Connector conn;
    
    public Thread getThread(){
    	return thread;
    }
    
    public SocketSender(Connector conn,OutputStream output) {
        this.conn=conn;
        list = new ArrayList<ByteArrayOutputStream>();
        //list = Collections.synchronizedList(new Vector<ByteArrayOutputStream>());
        this.output = output;
        thread = new Thread(this);
        thread.start();
    }

    @Override
    public void run() {
		try{    	
			while(running){
				Thread.sleep(100);
				synchronized(list){
					for(ByteArrayOutputStream baos : list){
						try {
							output.write(baos.toByteArray());
							output.flush();
							baos.close();
						} catch (SocketException ex){
							Logger.getLogger(SocketSender.class.getName()).log(Level.WARNING, null, ex);
							conn.destroy();
						} catch (IOException ex) {
							Logger.getLogger(SocketSender.class.getName()).log(Level.WARNING, null, ex);
							conn.destroy();
						}
					}
					list.clear();
				}
			}
			output.close();
		}catch (InterruptedException e){
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

    }

    /**
     * Add another byte stream corresponding to a packet
     * @param out
     */
    public void addSend(ByteArrayOutputStream out) {
        synchronized(list){
            list.add(out);
        }
    }

}

