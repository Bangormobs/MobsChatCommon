package uk.co.mobsoc.chat.common.packet;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;

import uk.co.mobsoc.chat.common.MCInputStream;
import uk.co.mobsoc.chat.common.MCOutputStream;
/**
 * Abstract packet class. Must contain a list of all packets that this version of the protocol.
 * @author triggerhapp
 *
 */
public abstract class Packet {
	public static HashMap<Integer, Class<? extends Packet>> packetList = new HashMap<Integer, Class<? extends Packet>>();

    public static void init(){
    	addPacket(VersionPacket.class);
    	addPacket(ConnectionPacket.class);
    	addPacket(ActionPacket.class);
    	addPacket(HostUserListUpdatePacket.class);
    	addPacket(UserListUpdatePacket.class);
    	addPacket(PresetActionPacket.class);

    	addPacket(PingPacket.class);
    	addPacket(UpdatePacket.class);
    	addPacket(PrivateMessagePacket.class);
    	addPacket(ChatPacket.class);
    	addPacket(ServerInfoPacket.class);    	
    	addPacket(AccountAddPacket.class);
    	addPacket(SetRankPacket.class);
    	addPacket(PlayerJoinedPacket.class);

    }
    
    public static void addPacket(Class<? extends Packet> klass){
        System.out.println("Adding Packet "+klass+" at "+packetList.size());
        try {
            if(klass.newInstance() instanceof Packet){
                packetList.put(packetList.size(), klass);
            }else{
            }
        } catch (InstantiationException ex) {
            Logger.getLogger(Packet.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            Logger.getLogger(Packet.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
   
    public static int getPacketId(Packet packet){
        for(int i : packetList.keySet()){
            if(packetList.get(i) == packet.getClass()){
                return i;
            }
        }
        return -1;
    }
   
    public static Packet getPacketId(int id){
        Packet packet=null;
        try {
            packet = (Packet) Packet.packetList.get(id).newInstance();
        } catch (InstantiationException ex) {
            Logger.getLogger(Packet.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        } catch (IllegalAccessException ex) {
            Logger.getLogger(Packet.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        } catch (NullPointerException ex){
            return null;
        }
        return packet;
    }

    public abstract void send(MCOutputStream stream);
    public abstract void recieve(MCInputStream stream);

}
