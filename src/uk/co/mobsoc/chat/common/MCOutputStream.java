package uk.co.mobsoc.chat.common;

import java.io.DataOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.UUID;
import java.util.logging.Level;
import java.util.logging.Logger;
/**
 * An extension of a basic output stream with some convenience functions
 * @author triggerhapp
 *
 */
public class MCOutputStream extends DataOutputStream{
    public MCOutputStream(OutputStream out){
        super(out);
    }
    /**
     * Write a UUID as two Longs to stream
     * @param uuid
     */
    public void writeUUID(UUID uuid){
    	if(uuid==null){
    		uuid = new UUID(0,0);
    	}
    	try {
        	writeLong(uuid.getMostSignificantBits());
			writeLong(uuid.getLeastSignificantBits());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }

    /**
     * Write an the string length as an int, followed by each character of the string
     * @param s
     */
    public void writeString(String s){
        try {
        	if(s==null){ writeInt(0); return; }
            writeInt(s.length());
            writeChars(s);
        } catch (IOException ex) {
            Logger.getLogger(MCOutputStream.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    /**
     * Write a colour in string form
     * @param c
     */
    public void writeColour(Colour c){
    	if(c==null){ c = Colour.none; }
    	writeString(c.toStream());
/*    	if(c == null){
    		c = new Colour("",255,255,255,false,false,false,false,false);
    	}
    	try{
    		writeString(c.getString());
    		writeInt(c.getRed());
    		writeInt(c.getGreen());
    		writeInt(c.getBlue());
    		writeBoolean(c.isBold());
    		writeBoolean(c.isItalic());
    		writeBoolean(c.isUnderline());
    		writeBoolean(c.isStrikethrough());
    		writeBoolean(c.isMagic());
    	} catch (IOException ex){
            Logger.getLogger(MCInputStream.class.getName()).log(Level.SEVERE, null, ex);
    	}*/
    }

}

