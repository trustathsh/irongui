/*
 * #%L
 * =====================================================
 *   _____                _     ____  _   _       _   _
 *  |_   _|_ __ _   _ ___| |_  / __ \| | | | ___ | | | |
 *    | | | '__| | | / __| __|/ / _` | |_| |/ __|| |_| |
 *    | | | |  | |_| \__ \ |_| | (_| |  _  |\__ \|  _  |
 *    |_| |_|   \__,_|___/\__|\ \__,_|_| |_||___/|_| |_|
 *                             \____/
 * 
 * =====================================================
 * 
 * Hochschule Hannover
 * (University of Applied Sciences and Arts, Hannover)
 * Faculty IV, Dept. of Computer Science
 * Ricklinger Stadtweg 118, 30459 Hannover, Germany
 * 
 * Email: trust@f4-i.fh-hannover.de
 * Website: http://trust.f4.hs-hannover.de/
 * 
 * This file is part of irongui, version 0.4.4,
 * implemented by the Trust@HsH research group at the Hochschule Hannover.
 * %%
 * Copyright (C) 2010 - 2014 Trust@HsH
 * %%
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *      http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 * #L%
 */



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
