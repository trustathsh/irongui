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



import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Collections;
import java.util.Enumeration;
import java.util.Properties;
import java.util.Vector;
import java.util.logging.Logger;

/**
 * Reads properties from a file
 * 
 * @author Tobias
 * 
 */
public class PropertiesRepository {
	private static Logger logger = Logger.getLogger("PropertiesRepository");
	private static Properties props;
	private static String mFileName = "irongui.properties";
	private static String defaultConfig =
			"#Properties automatically stored by IRONGUI. DO NOT MANUALLY EDIT IT!\n" +
			"#Thu Feb 14 13:37:00 CET 2014\n" +
			"irongui.user.connection.0.basicauth.enabled=true\n" +
			"irongui.user.connection.0.basicauth.pass=visual\n" +
			"irongui.user.connection.0.basicauth.user=visual\n" +
			"irongui.user.connection.0.dumping.enabled=false\n" +
			"irongui.user.connection.0.autoconnect.enabled=false\n" +			
			"irongui.user.connection.0.keystore=/keystore/irongui.jks\n" +
			"irongui.user.connection.0.keystorepass=irongui\n" +
			"irongui.user.connection.0.name=default-irond-basicauth\n" +
			"irongui.user.connection.0.truststore=/keystore/irongui.jks\n" +
			"irongui.user.connection.0.truststorepass=irongui\n" +
			"irongui.user.connection.0.url=https\\://localhost\\:8443/\n" +
			"irongui.user.connection.1.basicauth.enabled=false\n" +
			"irongui.user.connection.1.basicauth.pass=\n" +
			"irongui.user.connection.1.basicauth.user=\n" +
			"irongui.user.connection.1.dumping.enabled=false\n" +
			"irongui.user.connection.1.autoconnect.enabled=false\n" +
			"irongui.user.connection.1.keystore=/keystore/irongui.jks\n" +
			"irongui.user.connection.1.keystorepass=irongui\n" +
			"irongui.user.connection.1.name=default-irond-cert\n" +
			"irongui.user.connection.1.truststore=/keystore/irongui.jks\n" +
			"irongui.user.connection.1.truststorepass=irongui\n" +
			"irongui.user.connection.1.url=https\\://localhost\\:8444/\n" +
			"irongui.user.connectioncount=2\n";

	public PropertiesRepository() {
		props = new Properties(){
			private static final long serialVersionUID = -6945543252078449251L;

			@SuppressWarnings({ "rawtypes", "unchecked" })
			@Override
			public synchronized Enumeration keys() {
				Enumeration<Object> keys = super.keys();
				Vector<String> arr = new Vector<String>();
				while(keys.hasMoreElements()){
					arr.add((String)keys.nextElement());
				}
				Collections.sort(arr);
				return arr.elements();
			}
		};
		// actual path
		try {
			loadProperties(mFileName);
		} catch (Exception e) {
			String home = System.getProperty("user.home");
			String seperator = System.getProperty("file.separator");
			String path = home + seperator + mFileName;
			try {
				mFileName = path;
				loadProperties(mFileName);				
			} catch (FileNotFoundException e1) {
				e1.printStackTrace();
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		}

	}

	public PropertiesRepository(String path) {
		mFileName = path;
	}

	/**
	 * Sets the mFileName to the properties file.
	 */
	public void setPath(String p) {
		mFileName = p;
	}

	public void setProperty(String key, String value) {
		if (key != null && value != null) {
			props.setProperty(key, value);
		}
	}

	/**
	 * Loads the properties to memory
	 * 
	 * @param mFileName
	 *            the property file
	 * @throws IOException 
	 */
	public void loadProperties(String path) throws IOException {
		FileInputStream in = null;
		try {
			in = new FileInputStream(path);
		} catch (FileNotFoundException e) {
			logger.info("Configuration file '" + path + "' not found. Will create it now ...");
			FileWriter file = new FileWriter(path);
			BufferedWriter out = new BufferedWriter(file);
			out.write(defaultConfig);
			out.close();
			in = new FileInputStream(path);
		}
		// now try to read
		props.load(in);
		in.close();	
	}

	public void storeProperties() throws IOException {
		storeProperties(mFileName);
	}
	
	public void clear(){
		props.clear();
	}

	public void storeProperties(String path) throws IOException {
		FileOutputStream out = new FileOutputStream(path, false);
		
		props.store(out,
				"Properties automatically stored by irongui. DO NOT MANUALLY EDIT IT!");
		out.close();		
	}

	/*private void initConfig(Properties conf) {
		Config.MAPSERVER_URL = conf.getProperty("mapserver.url",
				"localhost:8443");
		Config.MAPSERVER_KEYSTORE_PATH = conf.getProperty(
				"mapserver.keystore.path", ".keystore");
		Config.MAPSERVER_KEYSTORE_PASSWORD = conf.getProperty(
				"mapserver.keystore.password", "mapdhcp2010");
		Config.MAPSERVER_TRUSTSTORE_PATH = conf.getProperty(
				"mapserver.truststore.path", ".truststore");
		Config.MAPSERVER_TRUSTSTORE_PASSWORD = conf.getProperty(
				"mapserver.truststore.password", "mapdhcp2010");
		Config.MAPSERVER_BASICAUTH_ENABLED = Boolean.parseBoolean(conf
				.getProperty("mapserver.basicauth.enabled", "false"));
		Config.MAPSERVER_BASICAUTH_USER = conf.getProperty(
				"mapserver.basicauth.user", "test");
		Config.MAPSERVER_BASICAUTH_PASSWORD = conf.getProperty(
				"mapserver.basicauth.password", "test");
	}*/

	/**
	 * Gets a specific property
	 * 
	 * @param ident
	 *            The property name
	 * @return the value of a property
	 * @throws IOException
	 * @throws FileNotFoundException
	 */
	public String getProperty(String key) {		
		return props.getProperty(key, "");
	}
}
