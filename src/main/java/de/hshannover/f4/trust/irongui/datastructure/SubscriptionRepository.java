package de.hshannover.f4.trust.irongui.datastructure;




import java.util.HashMap;
import java.util.Set;

import de.hshannover.f4.trust.irongui.communication.Connection;
import de.hshannover.f4.trust.irongui.util.CryptoUtil;

public class SubscriptionRepository {

	private HashMap<Connection, HashMap<String, IdentifierData>> mSubscriptions = null;
	private static SubscriptionRepository mInstance;
	
	private SubscriptionRepository() {
		mSubscriptions = new HashMap<Connection, HashMap<String, IdentifierData>>();
	}
	
	public static SubscriptionRepository getInstance(){
		if(mInstance == null){
			mInstance = new SubscriptionRepository();
		}
		return mInstance;
	}
	
	public void addSubscription(Connection con, IdentifierData ident){
		String uuid = CryptoUtil.generateMD5BySize(ident.getRequestObject().toString(), 16);
		HashMap<String, IdentifierData> map = mSubscriptions.get(con);
		if(map == null){
			map = new HashMap<String, IdentifierData>();			
		}
		map.put(uuid, ident);
		mSubscriptions.put(con, map);
	}
	
	public void removeSubscriptions(Connection con, String[] keys){
		HashMap<String, IdentifierData> map = mSubscriptions.get(con);
		if(map != null){
			for(String s : keys) {
				map.remove(s);		
			}
		}		
	}		
	
	public void removeSubscriptions(Connection con, Set<String> set){
		HashMap<String, IdentifierData> map = mSubscriptions.get(con);
		if(map != null){
			for(String s : set) {
				map.remove(s);		
			}
		}		
	}
	
	public HashMap<String, IdentifierData> getSubscriptions(Connection con) {
		return mSubscriptions.get(con);
	}

	public boolean isAlreadySubscribed(Connection con, IdentifierData ident){
		String uuid = CryptoUtil.generateMD5BySize(ident.getRequestObject().toString(), 16);
		HashMap<String, IdentifierData> map = mSubscriptions.get(con);
		if(map != null){
			if(map.get(uuid) != null){				
				return true;
			}
		}
		return false;
	}
	
	public boolean isAlreadySubscribed(Connection con, String uuid){		
		HashMap<String, IdentifierData> map = mSubscriptions.get(con);
		if(map != null){
			if(map.get(uuid) != null){
				return true;
			}
		}
		return false;
	}
	
	public IdentifierData getIdentifierDataBySubscriptionName(Connection con, String uuid){
		HashMap<String, IdentifierData> map = mSubscriptions.get(con);
		if(map != null){
			return map.get(uuid);			
		}
		return null;
	}
	
	public String getSubscriptionNameByIdentifier(Connection con, IfmapDataType ident){
		String name = null;
		HashMap<String, IdentifierData> map = mSubscriptions.get(con);
		if(map != null){
			if(map.containsValue(ident)){
				Set<String> keys = map.keySet();
				for(String key : keys){
					if(map.get(key).equals(ident)){
						name = key;
					}
				}					
			}
		}		
		return name;
	}
}
