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



package de.hshannover.f4.trust.irongui.properties;




import java.awt.Color;
import java.io.IOException;
import java.util.HashMap;

import de.hshannover.f4.trust.irongui.communication.Connection;
import de.hshannover.f4.trust.irongui.communication.ConnectionFactory;
import de.hshannover.f4.trust.irongui.communication.ConnectionParameter;
import de.hshannover.f4.trust.irongui.communication.ConnectionRepository;
import de.hshannover.f4.trust.irongui.exception.ConnectionCreationException;

public class SynchronisationService {

	private PropertiesRepository mPropertiesRep;
	private ConnectionRepository mConnectionRep;
	private PublisherRepository mPublisherRep;

	public SynchronisationService(PropertiesRepository propRep,
			ConnectionRepository conRep, PublisherRepository pubRep) {
		mPropertiesRep = propRep;
		mConnectionRep = conRep;
		mPublisherRep = pubRep;
	}

	public void sync() {
		try {
			mPropertiesRep.storeProperties();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void clearConnections(){
		Connection[] cons = mConnectionRep.getConnections();
		if(cons != null){
			for(Connection c : cons){
				mConnectionRep.remove(c);
			}
			mPropertiesRep.clear();		
		}
	}
	
	public void remove(Connection c) throws ConnectionCreationException{
		if(c != null){
			mConnectionRep.remove(c);
			mPropertiesRep.clear();
			Connection[] cons = mConnectionRep.getConnections();
			for(Connection con : cons){
				add(con);
			}
		}
		
	}

	public void addPublisher(HashMap<String, Color> publisher, Color iColor, Color mColor){
		if(publisher != null && iColor != null && mColor != null){
			mPublisherRep.add(publisher);
			mPublisherRep.setIdentifierColor(iColor);
			mPublisherRep.setMetadataColor(mColor);
			
			int count = 0;
			
			// FIXME if not commented out, it will add publisher colors to config file, even if they are already stored; count will increase every time, "ok" is clicked inside the PublisherDialog 
//			String countStr = mPropertiesRep
//					.getProperty(Config.USER_PUBLISHER_COUNT.toString());
//			if (!countStr.equals("")) {
//				count = Integer.parseInt(countStr);
//			}
			
			mPropertiesRep.setProperty(Config.COLOR_IDENTIFIER_DEFAULT.toString(), 
					String.valueOf(iColor.getRGB()));
			mPropertiesRep.setProperty(Config.COLOR_METADATA_DEFAULT.toString(), 
					String.valueOf(mColor.getRGB()));
			
			for(String p : publisher.keySet()){
				String PREFIX = Config.USER_PUBLISHER_PREFIX.toString() + count;
				mPropertiesRep.setProperty(PREFIX + Config.USER_PUBLISHER_NAME, p);
				mPropertiesRep.setProperty(PREFIX + Config.USER_PUBLISHER_COLOR, 
						String.valueOf(publisher.get(p).getRGB()));				
				count++;
			}
			
			mPropertiesRep.setProperty(
					Config.USER_PUBLISHER_COUNT.toString(),
					String.valueOf(count));			
		}
	}
	
	public void add(Connection c) throws ConnectionCreationException {
		if (c != null) {
			int count = 0;
			String countStr = mPropertiesRep
					.getProperty(Config.USER_CONNECTIONS_COUNT.toString());
			if (!countStr.equals("")) {
				count = Integer.parseInt(countStr);
			}

			ConnectionParameter p = c.getConnectionParameters();
			String PREFIX = Config.USER_CONNECTIONS_PREFIX.toString() + count +".";
			mPropertiesRep.setProperty(PREFIX + Config.USER_CONNECTIONS_NAME,
					p.getName());
			mPropertiesRep.setProperty(PREFIX + Config.USER_CONNECTIONS_URL,
					p.getEndpoint());
			mPropertiesRep.setProperty(PREFIX
					+ Config.USER_CONNECTIONS_KEYSTORE, p.getKeystore());
			mPropertiesRep.setProperty(PREFIX
					+ Config.USER_CONNECTIONS_KEYSTORE_PASSWORD,
					p.getKeystorePass());
			mPropertiesRep.setProperty(PREFIX
					+ Config.USER_CONNECTIONS_TRUSTSTORE, p.getTruststore());
			mPropertiesRep.setProperty(PREFIX
					+ Config.USER_CONNECTIONS_TRUSTSTORE_PASSWORD,
					p.getTruststorePass());
			mPropertiesRep.setProperty(PREFIX
					+ Config.USER_CONNECTIONS_BASICAUTH_ENABLED,
					p.isBasicAuthEnabled() ? "true" : "false");
			mPropertiesRep.setProperty(PREFIX
					+ Config.USER_CONNECTIONS_BASICAUTH_USER,
					p.getBasicauthUser());
			mPropertiesRep.setProperty(PREFIX
					+ Config.USER_CONNECTIONS_BASICAUTH_PASS,
					p.getBasicauthPass());
			mPropertiesRep.setProperty(
					Config.USER_CONNECTIONS_COUNT.toString(),
					String.valueOf(count + 1));
			mPropertiesRep.setProperty(PREFIX
					+ Config.USER_CONNECTIONS_DUMP,
					p.isDump() ? "true" : "false");
			mPropertiesRep.setProperty(PREFIX
					+ Config.USER_CONNECTIONS_AUTOCONNECT,
					p.isAutoConnect() ? "true" : "false");
			mPropertiesRep.setProperty(PREFIX
					+ Config.USER_CONNECTIONS_MAX_POLL_SIZE, String.valueOf(p.getMaxPollSize()));
		}
		mConnectionRep.add(c);

	}

	public Connection[] getConnections() {
		return mConnectionRep.getConnections();
	}

	public HashMap<String, Color> getPublisherAndColor(){
		return mPublisherRep.getPublisherAndColor();
	}
	
	public synchronized Color getDefaultIdentifierColor() {
		return mPublisherRep.getIdentifierColor();
	}

	public synchronized Color getDefaultMetadataColor() {
		return mPublisherRep.getMetadataColor();
	}

	
	public void init() {
		// read connections and store them in repository
		String tmp = mPropertiesRep.getProperty(Config.USER_CONNECTIONS_COUNT
				.toString());
		if (tmp != null && !tmp.equals("")) {
			// we found connections
			int count = Integer.parseInt(tmp);
			for (int i = 0; i < count; i++) {
				try {
					ConnectionParameter param = createConnectionParam(i);
					Connection con = ConnectionFactory.createConnection(param);
					mConnectionRep.add(con);
				} catch (ConnectionCreationException e) {
					e.printStackTrace();
				}
			}
		}
		// read publishers and their colors
		String pCount = mPropertiesRep.getProperty(Config.USER_PUBLISHER_COUNT
				.toString());
		if(pCount != null && !pCount.equals("")){
			HashMap<String, Color> publisher = new HashMap<String, Color>();
			int count = Integer.parseInt(pCount);
			for (int i = 0; i < count; i++) {
				String PREFIX = Config.USER_PUBLISHER_PREFIX.toString() + i;
				String name = mPropertiesRep.getProperty(PREFIX + Config.USER_PUBLISHER_NAME);
				String c = mPropertiesRep.getProperty(PREFIX + Config.USER_PUBLISHER_COLOR);
				Color color = new Color(Integer.parseInt(c));
				publisher.put(name, color);				
			}
			String ic = mPropertiesRep.getProperty(Config.COLOR_IDENTIFIER_DEFAULT.toString());
			String mc = mPropertiesRep.getProperty(Config.COLOR_METADATA_DEFAULT.toString());
			Color ident = new Color(Integer.parseInt(ic));
			Color meta = new Color(Integer.parseInt(mc));
			
			mPublisherRep.add(publisher);
			mPublisherRep.setIdentifierColor(ident);
			mPublisherRep.setMetadataColor(meta);
		}
	}

	private ConnectionParameter createConnectionParam(int i)
			throws ConnectionCreationException {
		String PREFIX = Config.USER_CONNECTIONS_PREFIX.toString() + i +".";		
		ConnectionParameter param = ConnectionFactory
				.createConnectionParameter(mPropertiesRep.getProperty(PREFIX
						+ Config.USER_CONNECTIONS_NAME.toString()));
		param.setEndpoint(mPropertiesRep.getProperty(PREFIX
				+ Config.USER_CONNECTIONS_URL.toString()));
		param.setKeystore(mPropertiesRep.getProperty(PREFIX
				+ Config.USER_CONNECTIONS_KEYSTORE.toString()));
		param.setKeystorePass(mPropertiesRep.getProperty(PREFIX
				+ Config.USER_CONNECTIONS_KEYSTORE_PASSWORD.toString()));
		param.setTruststore(mPropertiesRep.getProperty(PREFIX
				+ Config.USER_CONNECTIONS_TRUSTSTORE.toString()));
		param.setTruststorePass(mPropertiesRep.getProperty(PREFIX
				+ Config.USER_CONNECTIONS_TRUSTSTORE_PASSWORD.toString()));
		try {
			param.setMaxPollSize(Integer.parseInt(mPropertiesRep.getProperty(PREFIX
					+ Config.USER_CONNECTIONS_MAX_POLL_SIZE.toString())));
		}
		catch(NumberFormatException err) {			
		}
		
		param.setBasicAuthEnabled(mPropertiesRep
				.getProperty(
						(PREFIX + Config.USER_CONNECTIONS_BASICAUTH_ENABLED
								.toString())).equals("true") ? true : false);
		param.setBasicauthUser(mPropertiesRep.getProperty(PREFIX
				+ Config.USER_CONNECTIONS_BASICAUTH_USER.toString()));
		param.setBasicauthPass(mPropertiesRep.getProperty(PREFIX
				+ Config.USER_CONNECTIONS_BASICAUTH_PASS.toString()));
		param.setDump(mPropertiesRep
				.getProperty(
						(PREFIX + Config.USER_CONNECTIONS_DUMP
								.toString())).equals("true") ? true : false);
		param.setAutoConnect(mPropertiesRep
				.getProperty(
						(PREFIX + Config.USER_CONNECTIONS_AUTOCONNECT
								.toString())).equals("true") ? true : false);
		return param;
	}
}
