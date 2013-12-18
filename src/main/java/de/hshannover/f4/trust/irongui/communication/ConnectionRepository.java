package de.hshannover.f4.trust.irongui.communication;




import java.util.ArrayList;

public class ConnectionRepository {

	private ArrayList<Connection> mConnections = new ArrayList<Connection>();
	
	public synchronized void add(Connection c){
		if(!mConnections.contains(c)){
			mConnections.add(c);
		}
	}
	
	public synchronized void add(ConnectionParameter params){
		Connection c = new Connection(params);
		if(!mConnections.contains(c)){
			mConnections.add(c);
		}
	}
	
	public synchronized void remove(Connection c){
		mConnections.remove(c);
	}
	
	public synchronized Connection[] getConnections(){
		int length = mConnections.size();
		Connection[] cons = null;
		if(length > 0){
			cons = new Connection[length];
			for(int i = 0; i<length; i++){
				cons[i] = mConnections.get(i);
			}
		}
		return cons;
	}
}
