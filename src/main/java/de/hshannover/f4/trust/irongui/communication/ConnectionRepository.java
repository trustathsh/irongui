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
 * This file is part of irongui, version 0.4.5,
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
