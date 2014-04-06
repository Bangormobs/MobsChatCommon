package uk.co.mobsoc.chat.common;

import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.UUID;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * An extension of a basic input stream with some convenience functions
 * @author triggerhapp
 *
 */
public class MCInputStream extends DataInputStream{
    public MCInputStream(InputStream in){
        super(in);
    }
    /**
     * Read a UUID as two Longs from stream
     * @return
     */
    public UUID readUUID(){
    	long m=0,l=0;
    	try {
			m = readLong();
	    	l = readLong();
		} catch (IOException e) {
			e.printStackTrace();
		}
    	return new UUID(m,l);
    }
    
    /**
     * Read an int for string length, then as many characters as stated from stream
     * @return
     */
    public String readString(){
        try {
        	StringBuilder sb = new StringBuilder();
            int len = readInt();
            for(int i = 0; i < len; i++){
            	sb.append(readChar());
            }
            return sb.toString();
        } catch (IOException ex) {
            Logger.getLogger(MCInputStream.class.getName()).log(Level.SEVERE, "String reading exception", ex);
        }
        return "";
    }
    
    /**
     * Read a Colour in string form
     * @return
     */
    public Colour readColour(){
    	return new Colour(readString());
/*    	int r = 0, g = 0, b = 0;
    	boolean bold=false, italic=false, underline=false, strikethrough=false, magic=false;
    	String format="";
    	try{
    		format = readString();
    		r = readInt();
    		g = readInt();
    		b = readInt();
    		bold = readBoolean();
    		italic = readBoolean();
    		underline = readBoolean();
    		strikethrough = readBoolean();
    		magic = readBoolean();
    	} catch (IOException ex){
            Logger.getLogger(MCInputStream.class.getName()).log(Level.SEVERE, null, ex);
    	}
    	return new Colour(format,r,g,b,bold,italic,underline,strikethrough,magic);*/
    }

}

