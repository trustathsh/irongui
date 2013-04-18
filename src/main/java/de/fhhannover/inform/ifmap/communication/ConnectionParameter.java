package de.fhhannover.inform.ifmap.communication;

/*
 * #%L
 * ====================================================
 *   _____                _     ____  _____ _   _ _   _
 *  |_   _|_ __ _   _ ___| |_  / __ \|  ___| | | | | | |
 *    | | | '__| | | / __| __|/ / _` | |_  | |_| | |_| |
 *    | | | |  | |_| \__ \ |_| | (_| |  _| |  _  |  _  |
 *    |_| |_|   \__,_|___/\__|\ \__,_|_|   |_| |_|_| |_|
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
 * Website: http://trust.inform.fh-hannover.de/
 * 
 * This file is part of irongui, version 0.4.1, implemented by the Trust@FHH 
 * research group at the Hochschule Hannover, a program to visualize the content
 * of a MAP Server (MAPS), a crucial component within the TNC architecture.
 * 
 * The development was started within the bachelor
 * thesis of Tobias Ruhe at Hochschule Hannover (University of
 * Applied Sciences and Arts Hannover). irongui is now maintained
 * and extended within the ESUKOM research project. More information
 * can be found at the Trust@FHH website.
 * %%
 * Copyright (C) 2010 - 2013 Trust@FHH
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


public class ConnectionParameter {

	ConnectionParameter() {
		// TODO Auto-generated constructor stub
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEndpoint() {
		return endpoint;
	}

	public void setEndpoint(String endpoint) {
		this.endpoint = endpoint;
	}

	public String getKeystore() {
		return keystore;
	}

	public void setKeystore(String keystore) {
		this.keystore = keystore;
	}

	public String getTruststore() {
		return truststore;
	}

	public void setTruststore(String truststore) {
		this.truststore = truststore;
	}

	public String getKeystorePass() {
		return keystorePass;
	}

	public void setKeystorePass(String keystorePass) {
		this.keystorePass = keystorePass;
	}

	public String getTruststorePass() {
		return truststorePass;
	}

	public void setTruststorePass(String truststorePass) {
		this.truststorePass = truststorePass;
	}

	public boolean isBasicAuthEnabled() {
		return basicAuthEnabled;
	}

	public void setBasicAuthEnabled(boolean basicAuthEnabled) {
		this.basicAuthEnabled = basicAuthEnabled;
	}

	public String getBasicauthUser() {
		return basicauthUser;
	}

	public void setBasicauthUser(String basicauthUser) {
		this.basicauthUser = basicauthUser;
	}

	public String getBasicauthPass() {
		return basicauthPass;
	}

	public void setBasicauthPass(String basicauthPass) {
		this.basicauthPass = basicauthPass;
	}

	public String getDumpFilter() {
		return dumpFilter;
	}

	public void setDumpFilter(String dumpFilter) {
		this.dumpFilter = dumpFilter;
	}

	public boolean isAutoSubscribe() {
		return autoSubscribe;
	}

	public void setAutoSubscribe(boolean autoSubscribe) {
		this.autoSubscribe = autoSubscribe;
	}

	public int getMaxPollSize() {
		return maxPollSize;
	}

	public void setMaxPollSize(int maxPollSize) {
		this.maxPollSize = maxPollSize;
	}
	
	public ConnectionParameter clone() {
		ConnectionParameter param = new ConnectionParameter();
		param.setName(getName());
		param.setEndpoint(getEndpoint());
		param.setKeystore(getKeystore());
		param.setKeystorePass(getKeystorePass());
		param.setTruststore(getTruststore());
		param.setTruststorePass(getTruststorePass());
		param.setAutoSubscribe(isAutoSubscribe());
		param.setBasicAuthEnabled(isBasicAuthEnabled());
		param.setBasicauthUser(getBasicauthUser());
		param.setBasicauthPass(getBasicauthPass());
		param.setDumpFilter(getDumpFilter());
		param.setMaxPollSize(getMaxPollSize());
		return param;
	}

	@Override
	public String toString() {
		return name;
	};

	private String name;
	private String endpoint;
	private String keystore;
	private String truststore;
	private String keystorePass;
	private String truststorePass;
	private boolean basicAuthEnabled;
	private String basicauthUser;
	private String basicauthPass;
	private String dumpFilter;
	private boolean autoSubscribe;
	private int maxPollSize;	

}
